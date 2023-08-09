import { faPenAlt, faTrashAlt } from "@fortawesome/free-solid-svg-icons";
import {
  Button,
  Form,
  Toast,
  ToastBody,
  ToastHeader,
} from "@themesberg/react-bootstrap";
import React, { useEffect, useState } from "react";
import DataTable from "react-data-table-component";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const ROOT_URL = "http://localhost:8080/admin/products";

const imageUrl = "http://localhost:8080/img/product";

export default () => {
  // Main variable
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);

  const [productName, setProductName] = useState("");
  const [productImage, setProductImage] = useState("");
  const [productPrice, setProductPrice] = useState(1);
  const [productCreateDate, setProductCreateDate] = useState(new Date());
  const [productAvailable, setProductAvailablA] = useState(true);
  const [productQuantity, setProductQuantity] = useState(1);
  const [productCategory, setProductCategory] = useState(null);

  // Variable for action
  const [showToast, setShowToast] = useState(false);
  const [messageModal, setMessageModal] = useState("");
  const [loading, setIsLoading] = useState(false);
  const [productID, setProductID] = useState("");
  const [isValidForm, setIsValidForm] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [isUpdate, setIsUpdate] = useState(false);

  const fetchProductData = async () => {
    const response = await fetch(`${ROOT_URL}`);
    const data = await response.json();
    const newProduct = data;

    try {
      if (filteredProducts.length === 0) {
        setFilteredProducts(newProduct);
      }
      setProducts(newProduct);
    } catch (error) {
      console.log("Error: " + error);
    }
  };

  const fetchCategoriesData = async () => {
    const response = await fetch("http://localhost:8080/admin/categories");
    const data = await response.json();

    const newCategories = data;
    try {
      setCategories(newCategories);
    } catch (error) {
      console.log("Error: " + error);
    }
  };

  // CRUD Action
  const [product, setProduct] = useState({
    productName: "",
    productImage: "",
    productPrice: 0,
    productCreateDate: new Date(),
    productAvailable: false,
    productQuantity: 0,
    productCategory: null,
  });

  //   Columns Data
  const columns = [
    {
      name: "Id",
      selector: (row) => row.id,
    },
    {
      name: "Tên sản phẩm",
      selector: (row) => row.name,
      sortable: true,
    },
    {
      name: "Hình ảnh",
      selector: (row) => (
        <img
          src={`${imageUrl}/` + row.image}
          style={{ width: "50px", height: "50px" }}
          alt={row.image}
        />
      ),
    },
    {
      name: "Giá sản phẩm",
      selector: (row) => row.price,
      sortable: true,
    },
    {
      name: "Ngày tạo",
      selector: (row) => row.createDate,
      sortable: true,
    },
    {
      name: "Số lượng",
      selector: (row) => row.quantity,
      sortable: true,
    },
    {
      name: "Trạng thái",
      selector: (row) => displayStatus(row.available),
      sortable: true,
    },
    {
      button: true,
      name: "Thao tác",
      cell: (row) => (
        <div className="d-flex">
          <Button
            variant="outline-tertiary"
            onClick={() => {
              console.log(row);
              handleGetProduct(row);
            }}
            className="m-1"
          >
            <FontAwesomeIcon icon={faPenAlt} />
          </Button>
          <Button variant="outline-danger" className="m-1">
            <FontAwesomeIcon icon={faTrashAlt} />
          </Button>
        </div>
      ),
    },
  ];

  const handleGetProduct = (row) => {
    console.log("hi");
    setProductName(row.name);
    setProductImage(row.image.values);
    setProductPrice(row.price);
    setProductCreateDate(row.createDate);
    setProductAvailablA(row.available);
    setProductQuantity(row.quantity);
    setProductCategory(categories.values());

    setProduct(row);
    console.log(product);
  };

  const displayStatus = (status) => {
    return status ? "Hoạt động" : "Ẩn";
  };

  // const handleSubmitForm = async (e) => {
  //   e.preventDefault();
  //   // Kiểm tra tính hợp lệ của biểu mẫu (ví dụ: tên sản phẩm không thể trống)
  //   // if (product.name.trim() === "") {
  //   //   // Hiển thị thông báo lỗi nếu biểu mẫu không hợp lệ
  //   //   setMessageModal("Please enter a product name.");
  //   //   setShowToast(true);
  //   //   return;
  //   // }
  //   console.log("hello");
  //   console.log(JSON.stringify(product));

  //   // Gửi yêu cầu API để thêm mới sản phẩm
  //   await fetch(`${ROOT_URL}`, {
  //     method: "POST",
  //     headers: {
  //       Accept: "application/json",
  //       "Content-Type": "application/json",
  //     },
  //     body: JSON.stringify(product),
  //   })
  //     .then((response) => {
  //       if (response.ok) {
  //         // Hiển thị thông báo thành công
  //         setShowToast(true);
  //         setMessageModal("Product added successfully.");
  //         console.log("success");
  //         // Cập nhật danh sách sản phẩm bằng cách gọi hàm fetchProductData()
  //         //   fetchProductData();

  //         // Đặt lại giá trị trong biểu mẫu
  //         setProduct({
  //           name: "",
  //           image: "",
  //           price: 0,
  //           createDate: new Date(),
  //           available: true,
  //           quantity: 0,
  //           category: null,
  //         });
  //       } else {
  //         // Hiển thị thông báo lỗi nếu không thành công
  //         setShowToast(true);
  //         setMessageModal("Failed to add product.");
  //         console.log("failed");
  //       }
  //     })
  //     .catch((error) => {
  //       // Hiển thị thông báo lỗi nếu có lỗi trong quá trình thêm mới sản phẩm
  //       setShowToast(true);
  //       setMessageModal("Failed to add product.");
  //       console.log("failed");
  //     });
  // };

  const handleSubmitProductForm = async (e) => {
    e.preventDefault();

    Object.assign(product, {
      productName: productName,
      productImage: productImage,
      productPrice: productPrice,
      productCreateDate: productCreateDate,
      productAvailable: productAvailable,
      productQuantity: productQuantity,
      productCategory: productCategory,
    });

    console.log("Hi");
    console.log(product);
    console.log(JSON.stringify(product));

    // handleResetForm();
    // console.log(coupon);
    try {
      const resp = await fetch(ROOT_URL, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(product),
      });
      const data = resp.json();
      setProducts({ ...products, data });
      setMessageModal("Thêm mới sản phẩm thành công");
      setShowToast(true);
    } catch (error) {
      console.log(error);
      setMessageModal("Thêm mới sản phẩm Thất bại");
      setShowToast(true);
    }
  };

  //   const handleSubmitForm = async (e, actionType) => {
  //     e.preventDefault();

  //     try {
  //       let url = `${ROOT_URL}`;
  //       if (actionType === "update") {
  //         url = `${ROOT_URL}/update/${productID}`;
  //       }

  //       const response = await fetch(url, {
  //         method: "POST", // Use POST for both create and update
  //         headers: {
  //           Accept: "application/json",
  //           "Content-Type": "application/json",
  //         },
  //         body: JSON.stringify(product),
  //       });
  //       const data = response.json();
  //       console.log(data);

  //       if (response.ok) {
  //         console.log(response.data);
  //         setShowToast(true);
  //         setMessageModal(
  //           actionType === "create"
  //             ? "Thêm mới sản phẩm thành công"
  //             : "Cập nhật sản phẩm thành công"
  //         );
  //       } else {
  //         setShowToast(true);
  //         setMessageModal(
  //           actionType === "create"
  //             ? "Thêm mới sản phẩm thất bại"
  //             : "Cập nhật sản phẩm thất bại"
  //         );
  //         console.log("Error:", response.statusText);
  //       }
  //     } catch (error) {
  //       setShowToast(true);
  //       setMessageModal(
  //         actionType === "create"
  //           ? "Thêm mới sản phẩm thất bại"
  //           : "Cập nhật sản phẩm thất bại"
  //       );
  //       console.log("Error:", error);
  //     }
  //   };

  //   Search Area
  // Searching Hanlde

  const handleSearch = () => {
    // Perform the search logic based on the searchTerm value
    const filteredData = products.filter((product) => {
      const productName = product.name.toLowerCase();
      const searchValue = searchTerm.toLowerCase();
      return productName.includes(searchValue);
    });

    // Update the data displayed in the table
    setFilteredProducts(filteredData);
  };

  //   FetchData
  useEffect(() => {
    const fetchData = async () => {
      try {
        setIsLoading(true);
        await Promise.all([fetchProductData(), fetchCategoriesData()]);
        setIsLoading(false);
      } catch (error) {
        console.log("error: " + error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const timeout = setTimeout(() => {
      setShowToast(false);
      setMessageModal("");
    }, 10000);
    return () => clearTimeout(timeout);
  }, [showToast]);

  return (
    <>
      {/* Toast */}
      <Toast
        show={showToast}
        onClose={() => setShowToast(false)}
        className="position-absolute end-0"
      >
        <ToastHeader>
          <i className="fa-solid fa-exclamation"></i>
          <strong className="me-auto ms-2 fw-bolder">Tin nhắn hệ thống</strong>
        </ToastHeader>
        <ToastBody>
          <span>{messageModal}</span>
        </ToastBody>
      </Toast>

      {loading ? (
        <h1 className="text-center">Loading...</h1>
      ) : (
        <>
          {/* Form section*/}
          <div className="container-fluid">
            <h3 className="my-5 fw-bolder">Quản lý sản phẩm</h3>
            <Form onSubmit={(e) => handleSubmitProductForm(e)} className="row">
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Tên sản phẩm</Form.Label>
                <Form.Control
                  type="text"
                  name="name"
                  value={productName}
                  onChange={(e) => setProductName(e.target.value)}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng nhập tên sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Hình ảnh sản phẩm</Form.Label>
                <Form.Control
                  type="file"
                  name="image"
                  value={productImage}
                  onChange={(e) => setProductImage(e.target.value)}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng chọn ảnh sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Giá sản phẩm</Form.Label>
                <Form.Control
                  type="number"
                  name="price"
                  value={productPrice}
                  onChange={(e) => setProductPrice(e.target.value)}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng nhập giá sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Số lượng sản phẩm</Form.Label>
                <Form.Control
                  type="number"
                  name="quantity"
                  value={productQuantity}
                  onChange={(e) => setProductQuantity(e.target.value)}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng chọn số lượng sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Danh mục sản phẩm</Form.Label>
                <Form.Select
                  onChange={(e) => setProductCategory(e.target.value)}
                  defaultValue={productCategory}
                >
                  <option>Danh mục</option>
                  {categories.map((ct) => (
                    <option key={ct.id} value={productCategory}>
                      {ct.name}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback>
                  Vui lòng chọn Danh mục sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              <div className="d-flex gap-2">
                <hr />
                <Button
                  type="submit"
                  variant="outline-tertiary"
                  className="m-1"
                >
                  Thêm mới
                </Button>
                <Button variant="outline-info" className="m-1">
                  Cập nhật
                </Button>
              </div>
            </Form>
          </div>

          {/* Table section */}
          <div className="container-fluid mt-5">
            <h3 className="fw-bolder">Danh sách sản phẩm</h3>

            {/* Searching input */}
            <div className="search-section d-flex">
              <div className="d-flex align-items-center mb-3">
                <input
                  type="text"
                  className="form-control me-2"
                  placeholder="Tìm kiếm theo tên..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
                <button
                  className="btn btn-outline-primary"
                  onClick={() => handleSearch()}
                >
                  Search
                </button>
              </div>
            </div>

            {/* Table area */}
            <DataTable
              columns={columns}
              data={filteredProducts}
              responsive
              pagination
              striped
            />
          </div>
        </>
      )}
    </>
  );
};
