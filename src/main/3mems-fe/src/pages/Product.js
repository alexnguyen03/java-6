import { faPenAlt, faTrashAlt } from "@fortawesome/free-solid-svg-icons";
import {
  Button,
  ButtonGroup,
  Form,
  Modal,
  ModalBody,
  ModalFooter,
  Toast,
  ToastBody,
  ToastHeader,
} from "@themesberg/react-bootstrap";
import React, { useEffect, useState } from "react";
import DataTable from "react-data-table-component";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ModalHeader from "@themesberg/react-bootstrap/lib/esm/ModalHeader";

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
  const [showModal, setShowModal] = useState(false);
  const [modalsValue, setModalValue] = useState("");
  const handleCloseModal = () => setShowModal(false);

  // Form Variable
  const [productId, setProductID] = useState("");
  const [productName, setProductName] = useState("");
  const [productImage, setProductImage] = useState(null);
  const [productPrice, setProductPrice] = useState(1);
  const [createDate, setcreateDate] = useState(new Date());
  const [available, setAvailable] = useState(true);
  const [productQuantity, setProductQuantity] = useState(1);
  const [productCategory, setProductCategory] = useState("");
  const [isUpdate, setIsUpdate] = useState(false);

  const [product, setProduct] = useState({
    id: "",
    name: "",
    image: null,
    price: 1,
    createDate: "",
    available: true,
    quantity: 1,
    category: "",
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
          <Button
            variant="outline-danger"
            className="m-1"
            onClick={() => {
              setShowModal(true);
              console.log(row);
              handleGetProductDetails(row);
              setModalValue(row.id);
            }}
          >
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
      image: productImage,
      name: productName,
      price: Number(productPrice),
      quantity: Number(productQuantity),
    });

    const formData = new FormData();
    formData.append("file", productImage);
    formData.append("product", product);
    console.log(productImage.name);

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

  const handleUpdateProduct = async (e) => {
    e.preventDefault();
    Object.assign(product, {
      id: productId,
      name: productName,
      image: productImage,
      price: productPrice,
      createDate: createDate,
      available: available,
      quantity: productQuantity,
      category: productCategory,
    });

    console.log(JSON.stringify(product));

    try {
      const resp = await fetch(ROOT_URL + "/update" + { productId }, {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(product),
      });
      const data = resp.json();
      setProduct(data);
    } catch (error) {
      console.log(error);
    }

    fetchProductData();
    console.log("updated");
  };

  const handleDeleteProduct = async (e, row) => {
    e.preventDefault();
    Object.assign(product, {
      // id: row.id,
      name: row.name,
      image: row.image,
      price: row.price,
      createDate: row.createDate,
      available: !row.available,
      quantity: row.quantity,
      category: row.category,
    });

    console.log(JSON.stringify(product));
  };

  const handleGetProductDetails = (row) => {
    setIsUpdate(true);
    setProductID(row.id);
    setProductName(row.name);
    setProductImage(row.image);
    setProductPrice(row.price);
    setProductQuantity(row.quantity);
    setProductCategory(row.category.id);
    setAvailable(row.available);
    setcreateDate(row.createDate);
  };

  const handleResetForm = () => {
    setProductID("");
    setProductName("");
    setProductImage(null);
    setProductPrice("1");
    setProductQuantity("1");
    setProductCategory("");
    setAvailable(true);
    setcreateDate(formatDate(new Date()));

    setProduct([]);
    setIsUpdate(false);
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
                setProductName(e.target.value);
              }}
              required
              type="text"
              value={productName}
            />
            <Form.Control.Feedback>
              Vui lòng nhập tên sản phẩm
            </Form.Control.Feedback>
          </Form.Group>
          {/* Item */}
          <Form.Group className="mb-3 col-6">
            <Form.Label>Hình ảnh sản phẩm</Form.Label>
            <Form.Control
              onChange={(e) => {
                setProductImage(e.target.files[0]);
                console.log("Image Change: " + productImage);
              }}
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
                setProductPrice(e.target.value);
              }}
              required
              type="number"
              value={productPrice}
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
                setProductQuantity(e.target.value);
              }}
              required
              value={productQuantity}
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
              disabled={isUpdate}
            >
              Thêm mới
            </Button>
            <Button
              variant="outline-info"
              className="m-1"
              onClick={(e) => handleUpdateProduct(e)}
            >
              Cập nhật
            </Button>
            <Button
              type="reset"
              variant="outline-primary"
              className="m-1"
              onClick={() => handleResetForm()}
            >
              Reset
            </Button>
          </div>
        </Form>

        {/* Table Overview */}
        <h3 className="fw-bolder mt-5">Danh sách sản phẩm</h3>

        {/* Searching input */}
        {/* <div className="search-section d-flex">
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
             */}
        <DataTable
          columns={columns}
          data={products}
          responsive
          highlightOnHover
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

        {/* Modal */}
        <Modal
          as={Modal.Dialog}
          centered
          show={showModal}
          onHide={handleCloseModal}
        >
          <Modal.Header>
            <Modal.Title className="h6">
              Bạn có chắc chắn muốn xóa: {modalsValue}
            </Modal.Title>
            <Button
              variant="close"
              aria-label="Close"
              onClick={handleCloseModal}
            />
          </Modal.Header>
          <ModalBody>
            <img src={"http://localhost:8080/img/cat-delete.jpg"} alt="" />
          </ModalBody>
          <ModalFooter>
            <ButtonGroup>
              <Button
                variant="outline-primary"
                className="m-1"
                onClick={() => handleCloseModal()}
              >
                Trở lại
              </Button>
              <Button
                variant="danger"
                className="m-1"
                onClick={(e, row) => handleDeleteProduct(e, row)}
              >
                Xóa
              </Button>
            </ButtonGroup>
          </ModalFooter>
        </Modal>
      </div>
    </>
  );
};
