<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="description"
          content="Male_Fashion Template" />
    <meta name="keywords"
          content="Male_Fashion, unica, creative, html" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible"
          content="${pageContext.request.contextPath}/ie=edge" />
    <title>Giỏ hàng</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet" />
    <!-- Css Styles -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css"
          type="text/css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/font-awesome.min.css"
          type="text/css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/elegant-icons.css"
          type="text/css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/magnific-popup.css"
          type="text/css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/nice-select.css"
          type="text/css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/owl.carousel.min.css"
          type="text/css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/slicknav.min.css"
          type="text/css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css"
          type="text/css" />
  </head>

  <body>
    <!-- Page Preloder -->
    <div id="preloder">
      <div class="loader"></div>
    </div>
    <!-- Header -->
    <jsp:include page="header.jsp" />
    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="breadcrumb__text">
              <h4>Giỏ hàng</h4>
              <div class="breadcrumb__links">
                <a href="/">Trang chủ </a>
                <a href="/shop">Sản phẩm</a>
                <span>Giỏ hàng</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Breadcrumb Section End -->
    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-8">
            <div class="shopping__cart__table">
              <table>
                <thead>
                  <tr>
                    <th>Sản phẩm </th>
                    <th>Số lượng</th>
                    <th>Tổng cộng</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td colspan="3">
                      <c:if test="${cartDetails.size() == 0}">
                        <div class="alert alert-info text-center">
                          Chưa có sản phẩm nào trong giỏ hàng của bạn !
                        </div>
                      </c:if>
                    </td>
                  </tr>
                  <c:forEach var="cd"
                             items="${cartDetails}"
                             varStatus="loop">
                    <tr id="cartdetail${cd.id}">
                      <td class="product__cart__item d-flex justify-content-space-bewteen">
                        <!-- <div class="form-check d-flex flex-column justify-content-center">
                          <input class="form-check-input item"
                                 type="checkbox"
                                 value=""
                                 id="defaultCheck1" />
                        </div> -->
                        <div class="product__cart__item__pic">
                          <img src="${pageContext.request.contextPath}/img/product/${cd.product.image}"
                               alt=""
                               width="90px"
                               height="90px"
                               class="" />
                        </div>
                        <div class="product__cart__item__text">
                          <h6><a href="/shop/product-detail?id=${cd.product.id}"
                               class="text-secondary">
                              ${cd.product.name}
                            </a>
                          </h6>
                          <h5>
                            <fmt:formatNumber type="number"
                                              maxFractionDigits="3"
                                              value="${cd.product.price} " /><sup>đ</sup>
                          </h5>
                        </div>
                      </td>
                      <td class="quantity__item">
                        <form action="/shop/cart-detail/update"
                              method="post">
                          <div class="quantity">
                            <div class="pro-qty-2">
                              <input type="hidden"
                                     name="cartdetailId"
                                     value="${cd.id}">
                              <input type="number"
                                     min="0"
                                     name="quantity"
                                     value="${cd.quantity}"
                                     class="input__quantity${cd.id}"
                                     onchange="const price = document.querySelector('#price${cd.id}').value ;
                                       document.querySelector('.cart__price${cd.id}').innerHTML =  parseFloat(price) * parseFloat(this.value) +'<sup>đ</sup>'"
                                     onblur="this.form.submit()"
                                     onload="const price1 = document.querySelector('#price${cd.id}').value ;
                             document.querySelector('.cart__price${cd.id}').innerHTML = parseFloat(price) * parseFloat(this.value) +'<sup>đ</sup>'" />
                            </div>
                          </div>
                        </form>
                      </td>
                      <td class="cart__price${cd.id}">
                        <fmt:formatNumber type="number"
                                          maxFractionDigits="3"
                                          value=" ${cd.product.price * cd.quantity}" /><sup>đ</sup>
                      </td>
                      <input type="text"
                             id="price${cd.id}"
                             hidden
                             value="${cd.product.price} ">
                      </td>
                      <td class="cart__close">
                        <a href="/shop/cart-detail/delete/${cd.id}"">
                            <i class="
                           fa
                           fa-close
                           text-danger"></i>
                        </a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="row">
              <!-- <div class="col-lg-6 col-md-6 col-sm-2 d-flex flex-column justify-content-center">
                <div class="form-check d-flex ">
                  <input class="form-check-input"
                         type="checkbox"
                         value=""
                         checked
                         id="checkAll"
                         onClick="selectAll(this)">
                  <label for="checkAll"
                         class="font-weight-bold">Chọn tất cả</label>
                </div>
              </div> -->
              <div class="col-lg-6 col-md-6 col-sm-5">
                <div class="continue__btn">
                  <a href="/shop">Tiếp tục mua sắm</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="cart__total rounded shadow-sm ">
              <h4 class="text-center mb-3">Thông tin đặt hàng</h4>
              <ul>
                <li>
                  Tổng sản phẩm <span><%= session.getAttribute("totalCart") %> sản phẩm</span>
                </li>
                <li>
                  Tổng tiền <span>

                    <fmt:formatNumber type="number"
                                      maxFractionDigits="3"
                                      value="${totalPrice}" /><sup>đ</sup>
                  </span>
                </li>
              </ul>
              <a href="/shop/checkout"
                 class="btn btn-success w-100">Đặt hàng</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Shopping Cart Section End -->
    <!-- Footer Section Begin -->
    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="footer__about">
              <div class="footer__logo">
                <a href="/"><img src="${pageContext.request.contextPath}/img/main-logo.png"
                       alt="" /></a>
              </div>
              <p>
                Khách hàng luôn ở trung tâm trong những giao dịch độc nhất của
                chúng tôi, bao gốm cả thiết kế.
              </p>
              <a href="#"><img src="${pageContext.request.contextPath}/img/payment.png"
                     alt="" /></a>
            </div>
          </div>
          <div class="col-lg-2 offset-lg-1 col-md-2 col-sm-6">
            <div class="footer__widget">
              <h6>Sản phẩm</h6>
              <ul>
                <li><a href="#">Tai nghe</a></li>
                <li><a href="#">Đồng hồ</a></li>
                <li><a href="#">Phụ kiện</a></li>
                <li><a href="#">Túi xách</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-2 col-md-3 col-sm-6">
            <div class="footer__widget">
              <h6>Thông tin</h6>
              <ul>
                <li><a href="#">Liên hệ chúng tôi</a></li>
                <li><a href="#">Hình thức thanh toán</a></li>
                <li><a href="#">Vận chuyển</a></li>
                <li><a href="#">Trả lại và trao đổi</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-3 offset-lg-1 col-md-6 col-sm-6">
            <div class="footer__widget">
              <h6>Phản hồi</h6>
              <div class="footer__newslatter">
                <p>
                  Mọi thắc mắc về các dịch vụ trên trang web vui lòng gửi qua
                  Email
                </p>
                <form action="#">
                  <input type="text"
                         placeholder="Email của bạn" />
                  <button type="submit">
                    <span class="icon_mail_alt"></span>
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="footer__copyright__text">
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
              <p>Copyright &copy; & Edited by 3MEMS Team</p>
              <p>
                <script>
                  document.write(new Date().getFullYear());
                </script>
                2022 All rights reserved
              </p>
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- Footer end -->
    <!-- Search Begin -->
    <div class="search-model">
      <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
          <input type="text"
                 id="search-input"
                 placeholder="Search here....." />
        </form>
      </div>
    </div>
    <!-- Search End -->
    <!-- Js Plugins -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.nicescroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.countdown.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath}/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script>
      if (document.getElementById('checkAll').checked) {
        selectAll(document.getElementById('checkAll'));
      }
      function selectAll(source) {
        const items = document.querySelectorAll(".item");
        for (var i = 0, n = items.length; i < n; i++) {
          items[i].checked = source.checked;
        }
      }
      // const items = document.querySelectorAll('.item')
      // var inputText = document.querySelectorAll('.input__quantity')
      // console.log(inputText);
      // for (var i = 0, n = items.length; i < n; i++) {
      //     items[i].addEventListener('change', function () {
      //         if (this.checked) {
      //             // textInput[i].disabled = false;
      //             console.log(i);
      //             console.log(inputText);
      //             console.log("Checkbox is checked.");
      //         } else {
      //             // textInput[i].disabled = true;
      //             console.log("Checkbox is not checked.");
      //         }
      //     });
      // }
    </script>
  </body>

</html>