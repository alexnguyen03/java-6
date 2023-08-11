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
  // Main Variable
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);

  // Action Variable
  const [isLoading, setIsLoading] = useState(false);
  const [isShowToast, setIsShowToast] = useState(false);
  const [messageToast, setMessageToast] = useState("");

  // Form Variable
  const [id, setID] = useState("");
  const [name, setName] = useState("");
  const [image, setImage] = useState(null);
  const [price, setPrice] = useState(1);
  const [createDate, setcreateDate] = useState(new Date());
  const [available, setAvailable] = useState(true);
  const [quantity, setQuantity] = useState(1);
  const [productCategory, setProductCategory] = useState("");

  const [product, setProduct] = useState({
    name: "",
    image: "",
    price: 1,
    createDate: "",
    available: true,
    quantity: 1,
  });

  // DataTable Variable
  const columns = [
    {
      name: "ID",
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
      name: "Danh mục",
      selector: (row) => row.category.id,
      sortable: true,
    },
    {
      name: "Trạng thái",
      selector: (row) => displayAvailable(row.available),
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
              handleGetProductDetails(row);
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

  const displayAvailable = (status) => {
    return status ? "Hoạt động" : "Ẩn";
  };

  // Fetch Function
  const fetchProductData = async () => {
    try {
      const response = await fetch(ROOT_URL);
      const data = await response.json();
      const newProduct = data;
      {
        console.log(newProduct);
        setProducts(newProduct);
      }
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

  // Util function
  function formatDate(dateString) {
    var date = new Date(dateString);

    var year = date.getFullYear();
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);

    var formattedDate = year + "-" + month + "-" + day;

    return formattedDate;
  }

  // Form action
  const handleAddProduct = async (e) => {
    e.preventDefault();

    Object.assign(product, {
      available: available,
      category: productCategory,
      createDate: formatDate(createDate),
      image: image.name,
      name: name,
      price: Number(price),
      quantity: Number(quantity),
    });

    const formData = new FormData();
    formData.append("file", image);
    // formData.append("product", product);
    console.log(image.name);

    try {
      const resp = await fetch(`${ROOT_URL}/create`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "multipart/form-data",
        },
        body: formData,
      });
      const data = resp.json();
      // fetchProductData();
      setMessageToast("Thêm sản phẩm thành công!");
      setIsShowToast(true);
    } catch (error) {
      console.log(error);
      setMessageToast("Thêm sản phẩm thất bại!");
      setIsShowToast(true);
    }
    console.log("Add");

    console.log(JSON.stringify(product));
  };

  const handleGetProductDetails = (row) => {
    // setName(row.name);
    // setProduct(row);
  };

  //   Use Effect
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
      setIsShowToast(false);
      setMessageToast("");
    }, 7000);
    return () => clearTimeout(timeout);
  }, [isShowToast]);

  return (
    <>
      <div className="container-fluid">
        {/* Form Overview */}
        <h3 className="my-5 fw-bolder">Quản lý sản phẩm</h3>
        <Form className="row">
          {/* Item */}
          <Form.Group className="mb-3 col-6">
            <Form.Label>Tên sản phẩm</Form.Label>
            <Form.Control
              onChange={(e) => {
                setName(e.target.value);
                console.log(name);
              }}
              required
              type="text"
              vaiue={name}
            />
            <Form.Control.Feedback>
              Vui lòng nhập tên sản phẩm
            </Form.Control.Feedback>
          </Form.Group>
          {/* Item */}
          <Form.Group className="mb-3 col-6">
            <Form.Label>Hình ảnh sản phẩm</Form.Label>
            <Form.Control
              onChange={(e) => setImage(e.target.files[0])}
              required
              type="file"
              name="file"
              value={""}
            />
            <Form.Control.Feedback>
              Vui lòng chọn ảnh sản phẩm
            </Form.Control.Feedback>
          </Form.Group>
          {/* Item */}
          <Form.Group className="mb-3 col-6">
            <Form.Label>Giá sản phẩm</Form.Label>
            <Form.Control
              onChange={(e) => {
                setPrice(e.target.value);
                console.log(price);
              }}
              required
              type="number"
              value={price}
            />
            <Form.Control.Feedback>
              Vui lòng nhập giá sản phẩm
            </Form.Control.Feedback>
          </Form.Group>
          {/* Item */}
          <Form.Group className="mb-3 col-6">
            <Form.Label>Số lượng sản phẩm</Form.Label>
            <Form.Control
              onChange={(e) => {
                setQuantity(e.target.value);
                console.log(quantity);
              }}
              required
              value={quantity}
              type="number"
            />
            <Form.Control.Feedback>
              Vui lòng chọn số lượng sản phẩm
            </Form.Control.Feedback>
          </Form.Group>
          {/* Item */}
          <Form.Group className="mb-3 col-6">
            <Form.Label>Danh mục sản phẩm</Form.Label>
            <Form.Select
              defaultValue={productCategory}
              onChange={(e) => {
                setProductCategory(e.target.value);
                console.log(productCategory);
              }}
              value={productCategory}
            >
              <option>Danh mục</option>
              {categories.map((ct) => (
                <option key={ct.id} value={ct.id}>
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
              variant="outline-tertiary"
              className="m-1"
              onClick={(e) => handleAddProduct(e)}
            >
              Thêm mới
            </Button>
            <Button
              variant="outline-info"
              className="m-1"
              onClick={() => console.log("Update")}
            >
              Cập nhật
            </Button>
            <Button
              type="reset"
              variant="outline-primary"
              className="m-1"
              onClick={() => console.log("reset")}
            >
              Reset
            </Button>
          </div>
        </Form>

        {/* Table Overview */}
        <DataTable
          columns={columns}
          data={products}
          responsive
          pagination
          striped
        />

        {/* Toast */}
        <Toast
          show={isShowToast}
          onClose={() => setIsShowToast(false)}
          className="position-absolute end-0"
        >
          <ToastHeader>
            <i className="fa-solid fa-exclamation"></i>
            <strong className="me-auto ms-2 fw-bolder">
              Tin nhắn hệ thống
            </strong>
          </ToastHeader>
          <ToastBody>
            <span>{messageToast}</span>
          </ToastBody>
        </Toast>
      </div>
    </>
  );
};
