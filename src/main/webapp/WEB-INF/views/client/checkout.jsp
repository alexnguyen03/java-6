<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zxx">

	<head>
		<meta charset="UTF-8" />
		<meta name="description"
			  content="Male_Fashion Template" />
		<meta name="keywords"
			  content="Male_Fashion, unica, creative, html" />
		<meta name="viewport"
			  content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible"
			  content="ie=edge" />
		<title>Male-Fashion | Template</title>
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

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

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
							<h4>Thanh toán đơn hàng</h4>
							<div class="breadcrumb__links">
								<a href="/">Trang chủ</a> <a href="/shop">Sản phẩm</a> <span>Thanh
									toán đơn hàng</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- Breadcrumb Section End -->
		<!-- Checkout Section Begin -->
		<div class="checkout">
			<div class="container mt-5 mb-5">
				<div class="row">
					<div class="col-lg-6 border-right">
						<h4 class="mb-4 text-center font-weight-bold">CHI TIẾT THANH
							TOÁN</h4>
						<form action="/shop/checkout/create"
							  method="post">
							<div class="form-group">
								<label for="fullname"
									   class="form-label">Họ và tên</label> <input type="text"
									   class="form-control"
									   id="fullname"
									   readonly
									   value="${ account.fullname }" />
							</div>
							<div class="container p-0">
								<div class="row">
									<div class="col-lg-7">
										<div class="form-group">
											<label for="email"
												   class="form-label">Email</label> <input type="email"
												   class="form-control"
												   id="email"
												   readonly
												   value="${ account.email }" />
										</div>
									</div>
									<div class="col-lg-5">
										<div class="form-group">
											<label for="phone"
												   class="form-label">Số điện thoại</label> <input type="text"
												   class="form-control"
												   id="phone"
												   name="phone" />
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="address"
									   class="form-label">Địa chỉ</label> <input type="text"
									   class="form-control"
									   id="address"
									   name="address" />
							</div>
							<a href="/shop/cart-detail"
							   class="btn btn-secondary mt-2 ">Quay
								lại giỏ hàng</a>
							<button type="submit"
									class="btn btn-info font-weight-bold mt-2 float-right w-50">Thanh
								toán</button>
						</form>
						<form method="post"
							  action="/pay">
							<input hidden
								   name="total"
								   value="${ provisional }"> <input hidden
								   name="sdt"
								   id="sdt"> <input hidden
								   name="dc"
								   id="dc">
							<button type="submit"
									class="btn btn-success font-weight-bold mt-2 float-right w-50">Thanh
								toán Paypal</button>
						</form>
						<c:if test="${not empty success}">
							<div class="alert alert-success alert-dismissible fade show mt-4"
								 role="alert">
								<button type="button"
										class="close"
										data-dismiss="alert"
										aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								${success}
							</div>
						</c:if>
					</div>
					<div class="col-lg-6">
						<h4 class="mb-4 text-center font-weight-bold">ĐƠN HÀNG CỦA BẠN</h4>
						<div class="container">
							<div class="row">
								<c:forEach var="list"
										   items="${ cartDetails }">
									<div class="col-lg-3 mb-3">
										<img src="${pageContext.request.contextPath}/img/product/${list.product.image}"
											 class="card-img-top"
											 height="80" />
									</div>
									<div class="col-lg-5">
										<span class="font-weight-bold">${list.product.name} |
											${list.product.price} ₫</span><br /> <span class="text-dark">Số
											Lượng: ${list.quantity}</span>
									</div>
									<div class="col-lg-4">
										<h4 class="mt-3 text-danger font-weight-bold">
											<fmt:formatNumber type="number"
															  maxFractionDigits="3"
															  value="${list.product.price * list.quantity}" />
											<sup>đ</sup>
										</h4>
									</div>
								</c:forEach>
								<div class="col-lg-12">
									<hr />
								</div>
								<!-- 							<form action="/shop/checkout" method="get" -->
								<!-- 								class="d-flex justify-content-center w-100"> -->
								<div class="col-lg-9">
									<input type="text"
										   class="form-control"
										   placeholder="Mã giảm giá"
										   name="couponId"
										   id="couponId" />
								</div>
								<div class="col-lg-3">
									<button type="button"
											class="btn btn-primary w-100"
											onclick="saveAjaxData()">Sử dụng</button>
								</div>
								<h5 id="thongbao"
									class="ml-3 mt-2 text-success"></h5>
								<!-- 							</form> -->
								<div class="col-lg-12">
									<hr />
								</div>
								<div class="col-lg-6">
									<p>Tạm tính</p>
									<p>Giảm giá</p>
								</div>
								<div class="col-lg-6 text-right">
									<h4 class="font-weight-bold"
										id="provisional">
										<fmt:formatNumber type="number"
														  maxFractionDigits="3"
														  value="${provisional}" />
										<sup>đ</sup>
									</h4>
									<h4 class="font-weight-bold mt-2"
										id="discount">
										0 <sup>đ</sup>
										<%-- 									<fmt:formatNumber type="number" maxFractionDigits="3" --%>
										<%-- 										value="${ provisional * discountAmount /100 }" /> --%>
									</h4>
								</div>
								<div class="col-lg-12">
									<hr />
								</div>
								<div class="col-lg-6 mb-3">
									<h3 class="font-weight-bold">Tổng cộng</h3>
								</div>
								<div class="col-lg-6 text-right">
									<h3 class="font-weight-bold"
										id="total">
										<fmt:formatNumber type="number"
														  maxFractionDigits="3"
														  value="${provisional - (discountAmount * provisional)}" />
										<sup>đ</sup>
									</h3>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Checkout Section End -->
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
							<p>Khách hàng luôn ở trung tâm trong những giao dịch độc nhất
								của chúng tôi, bao gốm cả thiết kế.</p>
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
								<p>Mọi thắc mắc về các dịch vụ trên trang web vui lòng gửi
									qua Email</p>
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
						</div>
					</div>
				</div>

				<script type="text/javascript">
					var phoneInput = document.getElementById("phone"); // tìm ô nhập liệu fullname bằng id
					var addressInput = document.getElementById("address"); // tìm ô nhập liệu address bằng id
					phoneInput.addEventListener("input", function () { // thêm sự kiện "input" cho ô nhập liệu fullname
						var sdtInput = document.getElementById("sdt");
						sdtInput.value = phoneInput.value;
					});
					addressInput.addEventListener("input", function () { // thêm sự kiện "input" cho ô nhập liệu fullname
						var dcInput = document.getElementById("dc");
						dcInput.value = addressInput.value;
					});
					function saveAjaxData() {
						// Lấy mã giảm giá từ input field
						var couponId = document.getElementById("couponId").value;

						// Gửi yêu cầu đến server sử dụng Ajax
						var xhttp = new XMLHttpRequest();
						xhttp.onreadystatechange = function () {
							if (this.status === 200) {
								// Nhận kết quả trả về từ server
								var response = JSON.parse(this.responseText);
								// Cập nhật các thông tin trên trang web của bạn sử dụng kết quả nhận được
								var provisional = document
									.getElementById("provisional").innerText;
								var total = document.getElementById("total");
								var discount = document.getElementById("discount");
								var thongbao = document.getElementById("thongbao");

								var discountValue = parseFloat(response.discount);
								var provisionaltValue = parseFloat(provisional);
								console.log(provisionaltValue);
								// 							alert(provisionaltValue);

								discount.innerHTML = formatNumber((discountValue / 100)
									* provisionaltValue)
									+ " <sup>đ</sup>";
								total.innerHTML = formatNumber(provisionaltValue
									- (provisionaltValue * (discountValue / 100)))
									+ " <sup>đ</sup>";

								thongbao.innerHTML = "Áp dụng giảm giá thành công";

							} else {
								var response = JSON.parse(this.responseText);
								var thongbao = document.getElementById("thongbao");
								var discount = document.getElementById("discount");
								thongbao.innerHTML = "Không tìm thấy giảm giá !!!";
								discount.innerHTML = "0 <sup>đ</sup>";
							}
						};
						xhttp.open("GET", "/apply-discount/" + couponId, true);
						xhttp.send();
					}
					function formatNumber(number) {
						const formattedNumber = number.toString().replace(
							/\B(?=(\d{3})+(?!\d))/g, ".");

						// Kiểm tra xem nếu không có dấu chấm nào trong chuỗi số thì thêm .000 vào cuối
						if (!formattedNumber.includes('.')) {
							return formattedNumber + ".000";
						}

						return formattedNumber;
					}
				</script>

				<!-- Js Plugins -->
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
				<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
						integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
						crossorigin="anonymous"></script>
	</body>

</html>