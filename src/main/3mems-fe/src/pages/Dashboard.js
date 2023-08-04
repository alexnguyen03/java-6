import React, { useCallback, useEffect, useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck, faCog, faHome, faSearch } from '@fortawesome/free-solid-svg-icons';
import { Col, Row, Form, Button, ButtonGroup, Breadcrumb, InputGroup, Dropdown } from '@themesberg/react-bootstrap';

import { TransactionsTable } from "../components/Tables";

const url = 'http://localhost:8080/admin/index';

export default () => {
  const [topTenOrderDetails, setTopTenOrderDetails] = useState([]);
  const [bestSellingProduct, setBestSellingProduct] = useState([]);
  const [statusProduct, setStatusProduct] = useState([]);
  const [productNewest, setProductNewest] = useState([]);
  const [totalReneue, setTotalReneue] = useState([]);
  const [totalProductSoldToday, setTotalProductSoldToday] = useState([]);

  // fetchBestingSelling
  const fetchBestSellingProduct = async () => {
    try {
      const response = await fetch(`${url}/bestSellingProduct`)
      const data = await response.json()
      const bestSelling = data

      if (bestSelling) {
        const newbestSelling = bestSelling.map((item) => {
          const { id, name, image, price, createDate, avaialable, quantity } = item
          return {
            id: id,
            name: name,
            image: image,
            price: price,
            createDate: createDate,
            available: avaialable,
            quantity: quantity,
          }
        })
        setBestSellingProduct(newbestSelling);
      } else {
        setBestSellingProduct([]);
      }
    } catch (error) {
      console.log('Error: ' + error)
    }
  }

  // ProductNewset
  const fetchProductNewest = async () => {
    try {
      const response = await fetch(`${url}/selectProductNewest`)
      const data = await response.json()
      const newproductNewest = data
      if (newproductNewest.length === 0) {
        setProductNewest(0);
        console.log(productNewest);
      } else {
        setProductNewest(newproductNewest);
      }
    } catch (error) {
      console.log('Error: ' + error)
    }
  }

  // TotalRenue
  const fetchtotalRenue = async () => {
    try {
      const response = await fetch(`${url}/totalRevenue`)
      const data = await response.json()
      const newTotalRenue = data
      if (newTotalRenue === null) {
        setTotalReneue(0);
        console.log(totalReneue);
      } else {
        setTotalReneue(newTotalRenue);
      }
    } catch (error) {
      console.log('Error: ' + error)
    }
  }

  // TotalRenue
  const fetchTotalProductsSoldToday = async () => {
    try {
      const response = await fetch(`${url}/totalProductsSoldToday`)
      const data = await response.json()
      const newTotalProductSoldToday = data
      setTotalProductSoldToday(newTotalProductSoldToday);
    } catch (error) {
      console.log('Error: ' + error)
    }
  }

  useEffect(() => {
    fetchBestSellingProduct();
    fetchProductNewest();
    fetchtotalRenue();
    fetchTotalProductsSoldToday();
  }, [])

  return (
    <>
      <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center py-4">
        <div className="d-block mb-4 mb-md-0">
          <Breadcrumb className="d-none d-md-inline-block" listProps={{ className: "breadcrumb-dark breadcrumb-transparent" }}>
            <Breadcrumb.Item><FontAwesomeIcon icon={faHome} /></Breadcrumb.Item>
            <Breadcrumb.Item active>Trang Chủ</Breadcrumb.Item>
          </Breadcrumb>
          <h3>Chào mừng trở lại, <strong>YourAdminName</strong></h3>
          {/* <p className="mb-0">Your web analytics dashboard template.</p> */}
        </div>

        <div className="btn-toolbar mb-2 mb-md-0">
          <ButtonGroup>
            <Button variant="outline-primary" size="sm">Share</Button>
            <Button variant="outline-primary" size="sm">Export</Button>
          </ButtonGroup>
        </div>
      </div>

      {/* statistic concept*/}
      <div className="row gap-2">
        {/* Item */}
        <div className="card text-center col-3" style={{ background: "#effcef" }}>
          <div className="card-body">
            <h3 className="card-title fw-bold fs-3">{productNewest}</h3>
            <p className="text-dark fw-semibold">Sản phẩm vừa thêm</p>
          </div>
        </div>
        {/* Item */}
        <div className="card text-center col-3" style={{ background: "#e6f5f9" }}>
          <div className="card-body">
            <h3 className="card-title fw-bold fs-3">{totalReneue}$</h3>
            <p className="text-dark fw-semibold">Tổng doanh thu</p>
          </div>
        </div>
        {/* Item */}
        <div className="card text-center col-3" style={{ background: "#f4f6fa" }}>
          <div className="card-body">
            <h3 className="card-title fw-bold fs-3">{totalProductSoldToday}</h3>
            <p className="text-dark fw-semibold">Tổng sản phẩm bán được trong ngày</p>
          </div>
        </div>
      </div>

      {/* Content */}
      <div className="main-content mt-4">
        <div className="row">
          {/* Item */}
          <div className="col-8">
            <div className="card-full-height">
              <div className="card h-100">
                <div className="card-body">
                  <h5 className="card-title font-weight-bold mb-4">
                    <i className='bx bxs-right-arrow mr-2'></i>Giao dịch
                  </h5>
                  <div className="row">
                    {/* {TopTenOrderDetails.map((orderDetail) => (
                      <div className="col-12 mb-4" key={orderDetail.order.id}>
                        <div className="d-flex justify-content-between align-items-center flex-wrap">
                          <div className="p-2 d-flex justify-content-center align-items-center" style={{ background: '#dc3545', width: '35px', height: '35px', borderRadius: '50%' }}>
                            <i className='bx bx-headphone' style={{ color: '#fff' }}></i>
                          </div>
                          <div className="font-weight-bold d-flex flex-column">
                            <h6 className="text-truncate" style={{ maxWidth: '250px' }}>{orderDetail.product.name}</h6>
                            <p className="text-muted">{orderDetail.order.createDate}</p>
                          </div>
                          <div className="font-weight-bold d-flex flex-column">
                            <h6 style={{ color: '#dc3545' }}>
                              {orderDetail.order.status === 'C' ? 'Đang chờ' : ''}
                              {orderDetail.order.status === 'XL' ? 'Đang xử lý' : ''}
                              {orderDetail.order.status === 'G' ? 'Đang giao' : ''}
                              {orderDetail.order.status === 'ĐG' ? 'Đã giao' : ''}
                            </h6>
                            <p className="text-muted">{orderDetail.order.id}</p>
                          </div>
                        </div>
                        <hr className="m-0 p-0" />
                      </div>
                    ))} */}
                  </div>
                  {/* Item */}
                </div>
              </div>
            </div>
          </div>

          {/* Item */}
          <div className="col-4">
            <div className="card h-100">
              <div className="card-body">
                <h5 className="card-title mb-4 font-weight-bold">
                  <i className='bx bx-trophy mr-2' style={{ color: '#dc3545' }}></i>Sản phẩm bán chạy nhất
                </h5>
                {bestSellingProduct.map((p) => (
                  <div className="row my-2" key={p.id}>
                    <div className="col-12 text-truncate text-dark">
                      <h6>
                        <i className='bx bxs-star-half'></i> {p.name}
                      </h6>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>


      {/* Setting Table  */}
      {/* <div className="table-settings mb-4">
        <Row className="justify-content-between align-items-center">
          <Col xs={12} md={12} xl={12} className="ps-md-0 text-end">
            <Dropdown as={ButtonGroup}>
              <Dropdown.Toggle split as={Button} variant="link" className="text-dark m-0 p-0">
                <span className="icon icon-sm icon-gray">
                  <FontAwesomeIcon icon={faCog} />
                </span>
              </Dropdown.Toggle>
              <Dropdown.Menu className="dropdown-menu-xs dropdown-menu-right">
                <Dropdown.Item className="fw-bold text-dark">Show</Dropdown.Item>
                <Dropdown.Item className="d-flex fw-bold">
                  10 <span className="icon icon-small ms-auto"><FontAwesomeIcon icon={faCheck} /></span>
                </Dropdown.Item>
                <Dropdown.Item className="fw-bold">20</Dropdown.Item>
                <Dropdown.Item className="fw-bold">30</Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </Col>
        </Row>
      </div> */}

      {/* <TransactionsTable /> */}
    </>
  );
};
