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
            <title>3MEMS | Log in </title>
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

                  .container>.row {

                        background-color: rgba(212, 207, 243, 0.342);
                  }
            </style>
      </head>

      <body class="">

            <img src="${pageContext.request.contextPath}/img/resetpassword/bg-resetpassword.jpg"
                 alt=""
                 class="img-fluid">
            <div class="container pt-5 ">
                  <div class="row p-5 align-items-center rounded shadow">
                        <!-- For Demo Purpose -->
                        <div class="col-md-6 pr-lg-6 mb-5 mb-md-0">
                              <img src="${pageContext.request.contextPath}/img/resetpassword/resetpassword.svg"
                                   alt=""
                                   class="img-fluid d-none d-md-block">
                        </div>

                        <!-- Registeration Form -->
                        <div class="col-md-6 col-lg-6 ml-auto my-3 ">
                              <form action="/account/updatePass"
                                    method="post"
                                    class="py-5 px-4 rounded">
                                    <div class="row">
                                          <div class="col-12">
                                                <div
                                                     class="${msg !=  null? 'alert' : ''} alert-danger text-center mt-3">
                                                      ${msg}
                                                </div>
                                          </div>
                                          <h2 class="text-center d-block w-100 mb-2">Quên mật khẩu</h2>
                                          <!-- Password -->
                                          <div class="input-group col-lg-12 my-4 ">
                                                <div class="input-group-prepend">
                                                      <span
                                                            class="input-group-text bg-white px-4 border-md border-right-0">
                                                            <i class="fa fa-lock text-muted"></i>
                                                      </span>
                                                </div>
                                                <input id="password"
                                                       type="password"
                                                       name="password"
                                                       placeholder="Mật khẩu mới"
                                                       class="form-control bg-white border-left-0 border-md">
                                          </div>

                                          <!-- Password Confirmation -->
                                          <div class="input-group col-lg-12 mb-4">
                                                <div class="input-group-prepend">
                                                      <span
                                                            class="input-group-text bg-white px-4 border-md border-right-0">
                                                            <i class="fa fa-lock text-muted"></i>
                                                      </span>
                                                </div>
                                                <input id="passwordConfirmation"
                                                       type="password"
                                                       name="passwordConfirmation"
                                                       placeholder="Xác nhận mật khẩu     "
                                                       class="form-control bg-white border-left-0 border-md">
                                          </div>

                                          <!-- Submit Button -->
                                          <div class="form-group col-lg-12 mx-auto mb-0 mt-4">
                                                <button class="btn btn-primary btn-block py-2">
                                                      <span class="font-weight-bold">Đặt lại mật khẩu</span>
                                                </button>
                                          </div>





                                    </div>
                              </form>
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