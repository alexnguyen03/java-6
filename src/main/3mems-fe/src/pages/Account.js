import React, { useEffect, useState } from "react";
import axios from "axios";

import DataTable from "react-data-table-component";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenSquare } from "@fortawesome/free-solid-svg-icons";
import { Button, Card, Col, Form, Row } from "@themesberg/react-bootstrap";

const Account = () => {
  const [userName, setUserName] = useState("");
  const [fullname, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [activated, setActivated] = useState(true);

  const columns = [
    {
      name: "Tên đăng nhập",
      selector: (row) => row.username,
    },
    {
      name: "Họ và tên",
      selector: (row) => row.fullname,
      sortable: true,
    },
    {
      name: "Email",
      selector: (row) => row.email,
      sortable: true,
    },
    {
      name: "Trạng thái",
      selector: (row) => displayStatus(row.activated),
      sortable: true,
    },
    {
      name: "Hình ảnh",
      sortable: true,
      selector: (row) => row.photo,
    },

    {
      button: true,
      name: "Thao tác",
      cell: (row) => (
        <button
          className="btn btn-warning btn-xs"
          onClick={() => {
            console.log("Dataaaa: ", row);
            handleGetCouponDetails(row);
          }}
        >
          <FontAwesomeIcon
            icon={faPenSquare}
            size="lg"
            className="text-white"
          />
        </button>
      ),
    },
  ];

  const handleGetCouponDetails = (row) => {
    setShowForm(false);
    setUserName(row.username);
    setFullName(row.fullname);
    setEmail(row.email);
    setIsUpdate(true);
    setActivated(activated)
    console.log(row)
  };

  const [showForm, setShowForm] = useState(true);

  const displayStatus = (status) => {
    return status ? "Đang hoạt động" : "Không hoạt động";
  };

  const [account, setAccount] = useState([{}]);

  const [loading, setLoading] = useState(false);

  const [showAddBtn, setShowAddBtn] = useState(true);

  const getAllAccount = async () => {
    try {
      const res = await axios.get("http://localhost:8080/admin/user");
      setAccount(res.data);
      setLoading(false);

      console.log("hello", res.data);
    } catch (error) {}
  };

  useEffect(() => {
    getAllAccount();
  }, []);

  const handleUserName = (e) => {
    setUserName(e.target.value);
  };

  const handleFullName = (e) => {
    setFullName(e.target.value);
  };

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handleOnChangeSelect = (e) => {
    setActivated(e.target.value);
  };

  const [isUpdate, setIsUpdate] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const data = new FormData(event.currentTarget);

    const search = {
      userName: data.get("userName"),
      fullname: data.get("fullName"),
      email: data.get("email"),
      activated: activated
    };

    console.log(search);

    try {
      const res = await axios.post("http://localhost:8080/admin/users", search);
      if (res != null) {
        getAllAccount();
      }
    } catch (error) {}
  };

  return (
    <Row>
      {showAddBtn ? (
        <Col xl={12}>
          <Button
            style={{ marginTop: 20, marginBottom: 20 }}
            className="float-end"
            onClick={() => {
              setShowForm(false);
              setShowAddBtn(!showAddBtn);
              setIsUpdate(false);
            }}
            variant="success"
          >
            Thêm tài khoản
          </Button>
        </Col>
      ) : null}

      {showForm ? (
        <Col xl={12}>
          <DataTable
            // expandableRows
            columns={columns}
            data={account}
            pagination
            highlightOnHover
            progressPending={loading}
            fixedHeader
            fixedHeaderScrollHeight="500px"
          />
        </Col>
      ) : null}

      {showForm === false ? (
        <>
          <Col xl={12}>
            <Card border="light" className="bg-white shadow-sm mb-4">
              <Card.Body>
                <h5 className="mb-4">Thông tin khuyến mãi</h5>
                <form onSubmit={handleSubmit}>
                  <Row>
                    <Col md={6} className="mb-3">
                      <Form.Group id="userName">
                        <Form.Label>Tên đăng nhập</Form.Label>
                        <Form.Control
                          name="userName"
                          required
                          value={userName}
                          onChange={handleUserName}
                          type="text"
                          placeholder="Tên đăng nhập"
                        />
                      </Form.Group>
                    </Col>
                    <Col md={6} className="mb-3">
                      <Form.Group id="fullName">
                        <Form.Label>Họ và tên</Form.Label>
                        <Form.Control
                          value={fullname}
                          onChange={handleFullName}
                          required
                          type="text"
                          name="fullName"
                          placeholder="Họ và tên"
                        />
                      </Form.Group>
                    </Col>
                  </Row>

                  <Row>
                    <Col md={6} className="mb-3">
                      <Form.Group id="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                          required
                          value={email}
                          onChange={handleEmail}
                          type="text"
                          placeholder="Email"
                          name="email"
                        />
                      </Form.Group>
                    </Col>

                    <Col md={6} className="mb-3">
                      <Form.Group id="gender">
                        <Form.Label>Trạng thái</Form.Label>
                        <Form.Select
                          defaultValue={account.activated}
                          onChange={handleOnChangeSelect}
                        >
                          <option value="true">Đang hoạt động</option>
                          <option value="false">Không hoạt động</option>
                        </Form.Select>
                      </Form.Group>
                    </Col>
                  </Row>

                  <div
                    className="mt-3"
                    style={{
                      display: "flex",
                      alignItems: "center",
                      justifyContent: "space-between",
                      width: 300,
                    }}
                  >
                    <Button variant="info" disabled={!isUpdate} type="submit">
                      Cập nhật
                    </Button>

                    <Button variant="gray" type="reset">
                      Làm mới
                    </Button>

                    {/* <Button  variant="gray" disabled={isUpdate} type="submit">
                      Thêm mới
                    </Button> */}
                  </div>
                </form>
              </Card.Body>
            </Card>
          </Col>
          <Row>
            <Col xl={12}>
              <DataTable
                // expandableRows
                columns={columns}
                data={account}
                pagination
                highlightOnHover
                progressPending={loading}
                fixedHeader
                fixedHeaderScrollHeight="500px"
              />
            </Col>
          </Row>
        </>
      ) : null}
    </Row>
  );
};

export default Account;
