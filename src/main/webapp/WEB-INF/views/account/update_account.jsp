<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>3MEMS - Update Profile</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	type="text/css" />
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
	background: linear-gradient(135deg, rgba(255, 255, 255, 0.1),
		rgba(255, 255, 255, 0));
	backdrop-filter: blur(10px);
	-webkit-backdrop-filter: blur(10px);
	border-radius: 10px;
	border: 1px solid rgba(255, 255, 255, 0.18);
	box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4);
}

.login__header__fixed {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
}

.image-preview {
	width: 300px;
	min-height: 100px;
	border: 2px solid gray;
	display: flex;
	align-items: center;
	justify-content: center;
}

.image-preview__image {
	display: none;
	width: 100%;
}

.msg_errols {
	color: red;
	font-style: "italic";
}
</style>
</head>

<body>
	<div class="account-information-wrapper">
		<div class="container mt-5 glassmorphism">
			<div class="row  align-items-center">
				<div class="col-lg-10  m-auto position-relative">
					<h2 class="font-weight-bold text-center mt-3">CẬP NHẬT TÀI
						KHOẢN</h2>
					<form:form action="/account/update" enctype="multipart/form-data"
						modelAttribute="account">
						<div class="container mb-5 mt-5">
							<div class="row">
								<div class="col-lg-3 border-right">
									<label for="file-ip-1">
										<center>
											<img
												class="border border-2 bg-light p-1 border-success rounded-circle w-100"
												style="height: 200px;"
												src="${pageContext.request.contextPath }/img/user-management/${ account.photo }"
												id="file-ip-1-preview">
										</center>
									</label>
									<p class="text-center mt-2 font-weight-normal">Thay Đổi
										Hình Ảnh</p>
									<div class="input-group">
										<div class="custom-file">
											<input type="file" id="file-ip-1" accept="image/*"
												name="photo_file" onchange="showPreview(event);"> <label
												class="custom-file-label" for="file-ip-1">Chọn Ảnh</label>
										</div>
									</div>
								</div>
								<div class="col-lg-9">
									<c:if test="${not empty success}">
										<div class="alert alert-success alert-dismissible fade show"
											${
												 success==null
												 ? 'hidden'
												 : ''
												 }
											role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											${success}
										</div>
									</c:if>
									<div class="container">
										<div class="row">
											<div class="col-lg-12">
												<div class="form-group">
													<label for="username" class="form-label fw-bold">Tên
														Đăng Nhập</label> <input type="text" class="form-control bg-light"
														id="username" readonly value="${ account.username }"
														name="username">
												</div>
											</div>
											<div class="col-lg-12">
												<div class="form-group">
													<label for="password" class="form-label fw-bold">Mật
														Khẩu</label>
													<form:input path="password" id="password" type="password"
														cssClass="form-control" />
													<form:errors path="password" cssClass="msg_errols"></form:errors>
												</div>
											</div>
											<div class="col-lg-12">
												<div class="form-group">
													<label for="fullname" class="form-label fw-bold">Họ
														Và Tên</label>
													<form:input path="fullname" id="fullname"
														cssClass="form-control" />
													<form:errors path="fullname" cssClass="msg_errols"></form:errors>
												</div>
											</div>
											<div class="col-lg-12">
												<div class="form-group">
													<label for="email" class="form-label fw-bold">Email</label>
													<form:input path="email" id="email" cssClass="form-control" />
													<form:errors path="email" cssClass="msg_errols"></form:errors>
												</div>
											</div>
											<input hidden type="text" name="photo"
												value="${ account.photo }"> <input hidden
												type="text" name="activated" value="${ account.activated }">
											<input hidden type="text" name="admin"
												value="${ account.admin }">
										</div>
										<div class="d-flex justify-content-center">
											<a href="/"
												class="btn btn-secondary mt-2 font-weight-bold w-50 mr-5">Quay
												lại trang chủ</a>
											<button type="submit"
												class="btn btn-success mt-2 font-weight-bold w-50 ml-5">Cập
												nhật thông tin</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			function showPreview(event) {
				if (event.target.files.length > 0) {
					var src = URL.createObjectURL(event.target.files[0]);
					var preview = document.getElementById("file-ip-1-preview");
					preview.src = src;
					preview.style.display = "block";
					upoad.style.display = "block";
				}
			}
		</script>

		<script
			src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
			integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
			crossorigin="anonymous"></script>
</body>

</html>