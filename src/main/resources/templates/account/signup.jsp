<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">

      <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible"
                  content="IE=edge">
            <meta name="viewport"
                  content="width=device-width, initial-scale=1.0">
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
            <title>3MEMS | Sign in </title>
            <style>
                  body {
                        position: relative;
                  }

                  .img-fluid {
                        width: 100%;
                        height: 100%;
                        overflow: hidden;
                  }

                  .container {
                        position: absolute;
                        top: 50%;
                        left: 50%;
                        transform: translate(-50%, -50%);
                  }

                  .row {
                        background-color: rgba(255, 255, 255, 0.266);
                  }
            </style>
      </head>

      <body class="">
            <img src="${pageContext.request.contextPath}/img/signup/bg-signup.jpg"
                 alt=""
                 class="img-fluid">
            <div class="container">
                  <div class="row py-3 mt-3 align-items-center shadow p-5 rounded ">
                        <!-- For Demo Purpose -->
                        <div class="col-md-6 pr-lg-6 mb-5 mb-md-0">
                              <img src="https://bootstrapious.com/i/snippets/sn-registeration/illustration.svg"
                                   alt=""
                                   class="img-fluid mb-3 d-none d-md-block">
                              <h1 class="text-center pt-3">Tạo tài khoản</h1>
                        </div>
                        <!-- Registeration Form -->
                        <div class="col-md-6 col-lg-6 ml-auto mt-2">
                              <form:form action="/account/signup"
                                         method="post"
                                         enctype="multipart/form-data"
                                         modelAttribute="account">
                                    <div class="row  py-3 bg rounded ">
                                          <div class="col-12">
                                                <div
                                                     class="${msg !=  null? 'alert' : ''} alert-danger text-center mt-3">
                                                      ${msg}
                                                </div>
                                          </div>
                                          <div class="input-group col-lg-12 mt-2">
                                                <div class="input-group-prepend">
                                                      <span
                                                            class="input-group-text bg-white px-4 border-md border-right-0">
                                                            <i class="fa fa-user text-muted"></i>
                                                      </span>
                                                </div>
                                                <form:input id="username"
                                                            type="text"
                                                            path="username"
                                                            placeholder="Tên đăng nhập"
                                                            cssClass="form-control bg-white border-left-0 border-md" />
                                          </div>
                                          <div class="ml-4 mt-2 mb-3">
                                                <form:errors path="username"
                                                             cssClass="text-danger" />
                                          </div>
                                          <div class="input-group col-lg-12">
                                                <div class="input-group-prepend">
                                                      <span
                                                            class="input-group-text bg-white px-4 border-md border-right-0">
                                                            <i class="fa fa-user text-muted"></i>
                                                      </span>
                                                </div>
                                                <form:input id="fullname"
                                                            type="text"
                                                            path="fullname"
                                                            placeholder="Họ và tên"
                                                            cssClass="form-control bg-white border-left-0 border-md" />
                                          </div>
                                          <div class="ml-4 mt-2 mb-3">
                                                <form:errors path="fullname"
                                                             cssClass="text-danger" />
                                          </div>
                                          <!-- Email Address -->
                                          <div class="input-group col-lg-12">
                                                <div class="input-group-prepend">
                                                      <span
                                                            class="input-group-text bg-white px-4 border-md border-right-0">
                                                            <i class="fa fa-envelope text-muted"></i>
                                                      </span>
                                                </div>
                                                <form:input id="email"
                                                            type="text"
                                                            path="email"
                                                            placeholder="Email"
                                                            cssClass="form-control bg-white border-left-0 border-md" />
                                          </div>
                                          <div class="ml-4 mt-2 mb-3">
                                                <form:errors path="email"
                                                             cssClass="text-danger" />
                                          </div>
                                          <!-- Password -->
                                          <div class="input-group col-lg-12">
                                                <div class="input-group-prepend">
                                                      <span
                                                            class="input-group-text bg-white px-4 border-md border-right-0">
                                                            <i class="fa fa-lock text-muted"></i>
                                                      </span>
                                                </div>
                                                <form:input id="password"
                                                            type="password"
                                                            path="password"
                                                            placeholder="Mật khẩu"
                                                            cssClass="form-control bg-white border-left-0 border-md" />
                                          </div>
                                          <div class="ml-4 mt-2 mb-3">
                                                <form:errors path="password"
                                                             cssClass="text-danger" />
                                          </div>
                                          <!-- activated -->
                                          <form:input id="activated"
                                                      type="hidden"
                                                      path="activated"
                                                      value="true"
                                                      placeholder="Email"
                                                      cssClass="form-control bg-white border-left-0 border-md" />
                                          <!-- activated -->
                                          <!-- activated -->
                                          <form:input id="email"
                                                      type="hidden"
                                                      path="admin"
                                                      value="false"
                                                      placeholder="Email"
                                                      cssClass="form-control bg-white border-left-0 border-md" />
                                          <!-- activated -->
                                          <!-- Password Confirmation -->
                                          <div class="input-group col-lg-12">
                                                <div class="input-group-prepend">
                                                      <span
                                                            class="input-group-text bg-white px-4 border-md border-right-0">
                                                            <i class="fa fa-lock text-muted"></i>
                                                      </span>
                                                </div>
                                                <input id="passwordConfirmation"
                                                       type="password"
                                                       value="${confirmPass}"
                                                       name="confirmPass"
                                                       placeholder="Xác nhận mật khẩu     "
                                                       class="form-control bg-white border-left-0 border-md">
                                          </div>
                                          <div class="ml-4 mt-2 mb-3">
                                                <span class="text-danger">${confirmPassMsg}</span>
                                          </div>
                                          <div class="input-group col-lg-12 ">
                                                <div class="custom-file ">
                                                      <input type="file"
                                                             class="custom-file-input"
                                                             name="avt">
                                                      <label class="custom-file-label"
                                                             for="validatedCustomFile">Chọn ảnh đại diện</label>
                                                      <div class="invalid-feedback">Vui lòng chọn ảnh đại diện
                                                      </div>
                                                </div>
                                          </div>
                                          <div class="col-12 mb-3 mt-2 ml-2"><span class="text-danger">${ img_msg
                                                      }</span>
                                          </div>
                                          <!-- Submit Button -->
                                          <div class="form-group col-lg-12 mx-auto mb-0">
                                                <button type="submit"
                                                        class="btn btn-primary btn-block py-2">
                                                      <span class="font-weight-bold">Gửi mã xác nhận</span>
                                                </button>
                                          </div>
                                          <!-- Divider Text -->
                                          <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                                                <div class="border-bottom w-100 ml-5"></div>
                                                <span
                                                      class="px-2 small text-muted font-weight-bold text-muted">Hoặc</span>
                                                <div class="border-bottom w-100 mr-5"></div>
                                          </div>
                                          <!-- Already Registered -->
                                          <div class="text-center w-100">
                                                <p class="text-muted font-weight-bold">Bạn đã có tài khoản ? <a
                                                         href="/account/login"
                                                         class="text-primary ml-2">Đăng nhập</a></p>
                                          </div>
                                    </div>
                              </form:form>
                        </div>
                  </div>
            </div>
            <!-- Js Plugins -->
            <script src="js/jquery-3.3.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.nice-select.min.js"></script>
            <script src="js/jquery.nicescroll.min.js"></script>
            <script src="js/jquery.magnific-popup.min.js"></script>
            <script src="js/jquery.countdown.min.js"></script>
            <script src="js/jquery.slicknav.js"></script>
            <script src="js/mixitup.min.js"></script>
            <script src="js/owl.carousel.min.js"></script>
      </body>

</html>