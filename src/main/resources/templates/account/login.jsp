<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login</title>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

<style>
body {
	background:
		url(${pageContext.request.contextPath}/img/login/6764552_3433839.jpg)
		center/cover no-repeat fixed;
	width: 100wh;
	height: 100vh;
	overflow: hidden;
}

.glassmorphism {
	max-width: 23rem;
	background: linear-gradient(135deg, rgba(255, 255, 255, 0.1),
		rgba(255, 255, 255, 0));
	backdrop-filter: blur(5px);
	-webkit-backdrop-filter: blur(5px);
	border-radius: 5px;
	border: 1px solid rgba(255, 255, 255, 0.18);
	box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4);
}

.login__header__fixed {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
}

.toast {
	position: absolute;
	top: 0;
	right: 1.5rem;
	transform: translateX(100%);
	z-index: 100;
	top: 3.5rem;
	transition: all 0.5s;
}

.toast.show {
	transform: translateX(0);
}
</style>
</head>

<body class="d-flex justify-content-center align-items-center">
	<c:if test="${not empty messageShop}">
		<!-- toast msg  -->
		<div class="toast show" role="alert" aria-live="assertive"
			aria-atomic="true" data-delay="3000">
			<div class="toast-header bg-dark">
				<strong class="mr-auto font-weight-bold text-white text-center"> <i
					class='bx bxs-right-arrow mr-2'></i> 3MEMS Shop
				</strong>
				<button type="button" class="ml-2 mb-1 close text-white" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body font-weight-bold">
				<p class="m-0 p-0">${messageShop}</p>
			</div>
		</div>
		<!-- toast msg  -->

		<script>
        setTimeout(() => {
          const toast = document.querySelector(".toast");
          toast.classList.remove("show");
        }, 5000);
      </script>

		<%
		session.removeAttribute("messageProductManager");
		%>
		<%
		session.removeAttribute("messageShop");
		%>
	</c:if>

	<!-- Header -->
	<header class="container login__header__fixed">
		<nav class="navbar navbar-expand-sm container">
			<a class="navbar-brand text-dark font-weight-bold" href="/"> <img
				src="/img/main-logo.png" height="33px" alt="" />
			</a>
			<button class="navbar-toggler d-lg-none" type="button"
				data-toggle="collapse" data-target="#collapsibleNavId"
				aria-controls="collapsibleNavId" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavId">
				<ul class="navbar-nav ml-auto mt-2 mt-lg-0 text-dark">
					<a href="/shop" class="btn btn-outline-dark ml-3 font-weight-bold">XEM
						TRƯỚC SẢN PHẨM</a>
				</ul>
			</div>
		</nav>
	</header>

	<!-- Main content -->
	<div class="container glassmorphism">
		<div class="card text-center bg-transparent border-0">
			<div class="card-body">
				<h3 class="card-title my-3 font-weight-bold">Đăng nhập</h3>
				<h6 class="card-text font-italic">Nhập thông tin của bạn để
					đăng nhập vào tài khoản thành viên</h6>
				<form action="/account/login" method="post" class="mt-5">
					<c:if test="${not empty message}">
						<div class="text-danger text-left" role="alert">${message}</div>
					</c:if>
					<div class="form-group mt-3">
						<input id="username" name="username" type="text"
							value="${username}" class="form-control"
							aria-describedby="helpIdUsername" placeholder="Tên đăng nhập" />
						<small id="helpIdUsername" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<input id="password" name="password" type="password"
							class="form-control" aria-describedby="helpIdPassword"
							placeholder="Mật khẩu" /> <small id="helpIdPassword"
							class="form-text text-muted"></small>
					</div>

					<div class="form-check text-left mb-3">
						<input class="form-check-input" type="checkbox" name="remember"
							value="true" id="defaultCheck1" /> <label
							class="form-check-label font-weight-bold" for="defaultCheck1">
							Ghi nhớ đăng nhập? </label>
					</div>

					<p class="text-left">
						Không thể đăng nhập? <a href="/account/forgotpassword"
							class="font-weight-bold" style="color: #0c2b4b">Lấy lại mật
							khẩu</a>
					</p>
					<button
						class="btn site-btn my-4 w-100 text-white font-weight-bold p-3"
						type="submit">Đăng nhập</button>
				</form>

				<p>
					Không có tài khoản? <a href="/account/signup"
						class="font-weight-bold" style="color: #0c2b4b">Tạo tài khoản
						ngay!</a>
				</p>
			</div>
		</div>
	</div>

	<!-- Js Plugins -->
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>

	<script>
      $("#toast").on("show.bs.modal", (event) => {
        var button = $(event.relatedTarget);
        var modal = $(this);
        // Use above variables to manipulate the DOM
      });
    </script>
</body>
</html>
