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

const ROOT_URL = "http://localhost:8080/admin/product";

const imageUrl = "http://localhost:8080/img/product";

export default () => {
  // Main variable
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);

  // Variable for action
  const [showToast, setShowToast] = useState(false);
  const [messageModal, setMessageModal] = useState("");
  const [loading, setIsLoading] = useState(false);
  const [productID, setProductID] = useState("");
  const [isValidForm, setIsValidForm] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredProducts, setFilteredProducts] = useState([]);

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
    name: "",
    image: "",
    price: 0,
    createDate: new Date(),
    available: false,
    quantity: 0,
    category: null,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prevProduct) => ({
      ...prevProduct,
      [name]: value,
    }));
  };

  const handleSubmitForm = (e) => {
    e.preventDefault();
    // Kiểm tra tính hợp lệ của biểu mẫu (ví dụ: tên sản phẩm không thể trống)
    if (product.name.trim() === "") {
      // Hiển thị thông báo lỗi nếu biểu mẫu không hợp lệ
      setMessageModal("Please enter a product name.");
      setShowToast(true);
      return;
    }

    // Gửi yêu cầu API để thêm mới sản phẩm
    fetch(`${ROOT_URL}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(product),
    })
      .then((response) => {
        if (response.ok) {
          // Hiển thị thông báo thành công
          setShowToast(true);
          setMessageModal("Product added successfully.");
          console.log("success");
          // Cập nhật danh sách sản phẩm bằng cách gọi hàm fetchProductData()
          //   fetchProductData();

          // Đặt lại giá trị trong biểu mẫu
          setProduct({
            name: "",
            image: "",
            price: 0,
            createDate: new Date(),
            available: false,
            quantity: 0,
            category: null,
          });
        } else {
          // Hiển thị thông báo lỗi nếu không thành công
          setShowToast(true);
          setMessageModal("Failed to add product.");
          console.log("failed");
        }
      })
      .catch((error) => {
        // Hiển thị thông báo lỗi nếu có lỗi trong quá trình thêm mới sản phẩm
        setShowToast(true);
        setMessageModal("Failed to add product.");
        console.log("failed");
      });
      console.log(product);
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
    }, 3000);
    return () => clearTimeout(timeout);
  }, [showToast]);

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
        />
      ),
    },
    {
      name: "Giá",
      selector: (row) => row.price,
      sortable: true,
    },
    {
      name: "Ngày tạo sản phẩm",
      selector: (row) => row.createDate,
      sortable: true,
    },
    {
      name: "Số lượng",
      selector: (row) => row.quantity,
      sortable: true,
    },
    {
      button: true,
      name: "Thao tác",
      selector: (row) => (
        <div className="d-flex">
          <Button
            variant="outline-tertiary"
            // handleOnclick={() => {
            //   setProductID(product.id);
            //   setProduct(productID);
            // }}
            // handleOnclick={() => {
            //   setProductID(row.product.id);
            //   setProduct(handleSetStatus(row.productID));
            // }}
            onClick={() => {
              setProductID(product.id);
              //   setProduct(handleSetStatus(productID));
              console.log("Here:" + productID);
            }}
            className="m-1"
          >
            <FontAwesomeIcon icon={faPenAlt} />
          </Button>
          <Button
            variant="outline-danger"
            handleOnclick={() => {
              setProductID(product.id);
            }}
            className="m-1"
          >
            <FontAwesomeIcon icon={faTrashAlt} />
          </Button>
        </div>
      ),
    },
  ];

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
            <Form onSubmit={handleSubmitForm} className="row">
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Tên sản phẩm</Form.Label>
                <Form.Control
                  isInvalid={isValidForm}
                  type="text"
                  name="name"
                  value={product.name}
                  onChange={handleChange}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng nhập tên sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Hình ảnh sản phẩm</Form.Label>
                <Form.Control
                  isValid={isValidForm}
                  type="file"
                  name="image"
                  value={product.image}
                  onChange={handleChange}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng chọn ảnh sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Giá sản phẩm</Form.Label>
                <Form.Control
                  isValid={isValidForm}
                  type="number"
                  name="price"
                  value={product.price}
                  onChange={handleChange}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng nhập giá sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Số lượng sản phẩm</Form.Label>
                <Form.Control
                  isValid={isValidForm}
                  type="number"
                  name="quantity"
                  value={product.quantity}
                  onChange={handleChange}
                />
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
                  Vui lòng chọn số lượng sản phẩm
                </Form.Control.Feedback>
              </Form.Group>
              {/* Item */}
              <Form.Group className="mb-3 col-6">
                <Form.Label>Danh mục sản phẩm</Form.Label>
                <Form.Select>
                  <option defaultValue>Danh mục</option>
                  {categories.map((ct) => (
                    <option key={ct.id} value={ct.name}>
                      {ct.name}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type={isValidForm ? "valid" : "inValid"}>
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
                <Button type="submit" variant="outline-info" className="m-1">
                  Cập nhật
                </Button>
              </div>
            </Form>
          </div>

          {/* Table section */}
          <div className="container-fluid mt-5">
            <h3 className="fw-bolder">Danh sách sản phẩm</h3>

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
                  className="btn btn-primary"
                  onClick={() => handleSearch()}
                >
                  Search
                </button>
              </div>
            </div>

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
