<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8" />
<meta name="description" content="Male_Fashion Template" />
<meta name="keywords" content="Male_Fashion, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Male-Fashion | Template</title>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
	rel="stylesheet" />
<!-- Css Styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/elegant-icons.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/magnific-popup.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/nice-select.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/owl.carousel.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/slicknav.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css"
	type="text/css" />
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<!-- Offcanvas Menu Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="offcanvas-menu-wrapper">
		<div class="offcanvas__option">
			<div class="offcanvas__links">
				<a href="#">Sign in</a> <a href="#">FAQs</a>
			</div>
			<div class="offcanvas__top__hover">
				<span>Usd <i class="arrow_carrot-down"></i></span>
				<ul>
					<li>USD</li>
					<li>EUR</li>
					<li>USD</li>
				</ul>
			</div>
		</div>
		<div class="offcanvas__nav__option">
			<a href="#" class="search-switch"><img
				src="${pageContext.request.contextPath}/img/icon/search.png" alt="" /></a>
			<a href="#"><img
				src="${pageContext.request.contextPath}/img/icon/heart.png" alt="" /></a>
			<a href="#"><img
				src="${pageContext.request.contextPath}/img/icon/cart.png" alt="" />
				<span>0</span></a>
			<div class="price">$0.00</div>
		</div>
		<div id="mobile-menu-wrap"></div>
		<div class="offcanvas__text">
			<p>Free shipping, 30-day return or refund guarantee.</p>
		</div>
	</div>
	<!-- Offcanvas Menu End -->

	<!-- Header -->
	<jsp:include page="header.jsp" />

	<!-- Shop Details Section Begin -->
	<section class="breadcrumb-option">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb__text">
						<h4>Lịch sử đơn hàng</h4>
						<div class="breadcrumb__links">
							<a href="/">Trang chủ</a> <a href="/shop">Sản phẩm</a> <span>Lịch
								sử đơn hàng</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="order-history-wrapper mt-4">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 m-auto mt-4">
					<!-- <h4>Lịch Sử Đặt Hàng</h4> -->
					<c:if test="${not empty success}">
						<div class="alert alert-success alert-dismissible fade show"
							role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							${success}
						</div>
					</c:if>
					<hr />
					<div class="d-flex mt-3 mb-3">
						<form action="/shop/order-history/search" method="post"
							class="d-flex justify-content-center">
							<div class="p-0">
								<div class="input-group" style="width: 230px;">
									<div class="input-group-prepend">
										<label class="input-group-text" for="inputGroupSelect01">Tìm
											theo</label>
									</div>
									<select class="custom-select" id="inputGroupSelect01"
										name="search" onchange="handleSearchChange()">
										<option value="select">Chọn</option>
										<option value="id"
											${
													isId
													? 'selected'
													: ''
													}>Mã
											đơn hàng</option>
										<option value="date"
											${
													isCreateDate
													? 'selected'
													: ''
													}>Ngày
											đặt</option>
									</select>
								</div>
							</div>
							<div class="pl-2">
								<input class="search-bar bg-white text-dark form-control" ${ isSuscess ? 'disabled' : '' }
									placeholder="Search..." type="text" style="height: 40px;"
									id="searchInput" name="keyword">
							</div>
							<div class="mr-auto pl-2">
								<button type="submit" class="btn btn-primary"
									style="height: 42px;">Tìm</button>
							</div>
						</form>
						<div class="mr-auto pl-2">
							<a href="/shop/order-history"
								class="btn btn-secondary btn-lg active font-weight-bold px-4 ml-5"
								role="button" aria-pressed="true" style="height: 42px;">Làm
								mới</a>
						</div>
						<div class="pr-2 d-flex justify-content-center ">
							<form action="/shop/order-history/filter" method="post"
								class="d-flex justify-content-center">
								<div class="input-group" style="width: 250px;">
									<div class="input-group-prepend">
										<label class="input-group-text" for="inputGroupSelect01"
											style="height: 40px;">Lọc theo</label>
									</div>
									<select class="custom-select mr-2" id="inputGroupSelect01"
										name="status" style="height: 40px;">
										<option value="select">Chọn trạng thái</option>
										<option value="C"
											${
													isC
													? 'selected'
													: ''
													}>Đang
											chờ</option>
										<option value="XL"
											${
													isXl
													? 'selected'
													: ''
													}>Đang
											xử lý</option>
										<option value="G"
											${
													isG
													? 'selected'
													: ''
													}>Đang
											giao</option>
										<option value="DG"
											${
													isDg
													? 'selected'
													: ''
													}>Đã
											giao</option>
										<option value="H"
											${
													isH
													? 'selected'
													: ''
													}>Đã
											hủy</option>
									</select>
								</div>
								<button type="submit" class="btn btn-primary"
									style="height: 40px;">Lọc</button>
							</form>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table text-center">
							<thead class="bg-dark text-white">
								<tr>
									<th scope="col">Mã Đơn Hàng</th>
									<th scope="col">Ngày Đặt</th>
									<th scope="col">Tổng Tiền</th>
									<th scope="col">Giảm Giá</th>
									<th scope="col">Địa Chỉ</th>
									<th scope="col">Số Điện Thoại</th>
									<th scope="col">Trạng Thái</th>
									<th scope="col">Ghi Chú</th>
									<th scope="col">Chi Tiết</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${ orders }">
									<tr>
										<th class="pt-4">${ order.id }</th>
										<td class="pt-4">${ order.createDate }</td>
										<td class="pt-4"><fmt:formatNumber type="number"
												maxFractionDigits="3" value="${ order.totalPrice}" /><sup>đ</sup>
										</td>
										<td class="pt-4"><fmt:formatNumber type="number"
												maxFractionDigits="3"
												value="${(order.totalPrice / (1 - order.coupon.discountAmount / 100)) * (order.coupon.discountAmount / 100)}" />
											<sup>đ</sup></td>
										<td class="pt-4">${ order.address }</td>
										<td class="pt-4">${ order.phone }</td>
										<td class="pt-4"><c:choose>
												<c:when test="${order.status == 'C'}">
														Đang chờ
													</c:when>
												<c:when test="${order.status == 'XL'}">
														Đang xử lý
													</c:when>
												<c:when test="${order.status == 'G'}">
														Đang giao
													</c:when>
												<c:when test="${order.status == 'DG'}">
														Đã giao
													</c:when>
												<c:when test="${order.status == 'H'}">
														Đã hủy
													</c:when>
											</c:choose></td>
										<td class="pt-4">${ order.notes }</td>
										<td class="pt-3">
											<h5>
												<!-- Button to Open the Modal -->
												<button type="button"
													class="btn btn-light bg-white border-0" data-toggle="modal"
													data-target="#myModal${ order.id }">
													<h4>
														<i class="fa fa-eye text-info mr-2" aria-hidden="true"></i>
													</h4>
												</button>
											</h5>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:forEach var="order" items="${ orders }" varStatus="loop">
		<!-- The Modal -->
		<div class="modal fade" id="myModal${ order.id }">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">CHI TIẾT ĐƠN HÀNG</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<!-- Modal body -->
					<div class="modal-body">
						<h5 class="mb-3">
							Mã đơn hàng: <strong>${ order.id }</strong>
						</h5>
						<table class="table font-weight-bold">
							<thead class="bg-dark text-white text-center">
								<tr>
									<th scope="col" class="p-0">STT</th>
									<th scope="col" class="p-0">Ảnh</th>
									<th scope="col" class="p-0">Tên Sản Phẩm</th>
									<th scope="col" class="p-0">Đơn Giá</th>
									<th scope="col" class="p-0">Số Lượng</th>
									<th scope="col" class="p-0">Tổng Tiền</th>
								</tr>
							</thead>
							<tbody style="font-size: 15px;" class="text-center">
								<c:forEach var="orderdetail" items="${ order.orderDetails }"
									varStatus="loop">
									<tr class="align-middle">
										<th class="pt-4">${ loop.count }</th>
										<td><img
											src="${pageContext.request.contextPath }/img/product/${ orderdetail.product.image }"
											class="card-img-top" height="50px"></td>
										<td class="pt-4">
											<p>${ orderdetail.product.name }</p>
										</td>
										<td class="pt-4"><fmt:formatNumber
												value="${ orderdetail.product.price }" type="currency"
												currencySymbol="" /><sup>đ</sup></td>
										<td class="pt-4">${ orderdetail.quantity }</td>
										<td class="pt-4"><fmt:formatNumber
												value="${ orderdetail.product.price  * orderdetail.quantity }"
												type="currency" currencySymbol="" /> <sup>đ</sup></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<div class="container">
							<div class="row">
								<div class="col-6">
									<h4 class="mb-0">
										Tổng số lượng: <strong> <c:set var="totalQuantity"
												value="0" /> <c:forEach var="detail"
												items="${order.orderDetails}">
												<c:set var="totalQuantity"
													value="${totalQuantity + detail.quantity}" />
											</c:forEach> ${totalQuantity}
										</strong>
									</h4>
								</div>
								<div class="col-6 text-right">
									<h4 class="mb-0">
										Tổng tiền : <strong> <c:set var="total" value="0" />
											<c:forEach var="detail" items="${order.orderDetails}">
												<c:set var="total"
													value="${total + (detail.product.price  * detail.quantity)}" />
											</c:forEach> <fmt:formatNumber value="${ total }" type="currency"
												currencySymbol="" /><sup>đ</sup>
										</strong>
									</h4>
								</div>
							</div>
						</div>
					</div>

					<!-- 					<div class="modal-footer"> -->
					<!-- 						<h4> -->
					<%-- 							Tổng số lượng: <strong> <c:set var="totalQuantity" --%>
					<%-- 									value="0" /> <c:forEach var="detail" --%>
					<%-- 									items="${order.orderDetails}"> --%>
					<%-- 									<c:set var="totalQuantity" --%>
					<%-- 										value="${totalQuantity + detail.quantity}" /> --%>
					<%-- 								</c:forEach> ${totalQuantity} --%>
					<!-- 							</strong> -->
					<!-- 						</h4> -->
					<!-- 						<h4> -->
					<%-- 							Tổng tiền : <strong> <c:set var="total" value="0" /> <c:forEach --%>
					<%-- 									var="detail" items="${order.orderDetails}"> --%>
					<%-- 									<c:set var="total" --%>
					<%-- 										value="${total + (detail.product.price  * detail.quantity)}" /> --%>
					<%-- 								</c:forEach> <fmt:formatNumber value="${ total }" type="currency" --%>
					<%-- 									currencySymbol="" /><sup>đ</sup> --%>
					<!-- 							</strong> -->
					<!-- 						</h4> -->
					<!-- 					</div> -->
				</div>
			</div>
		</div>
	</c:forEach>
	<!-- Footer Section Begin -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__logo">
							<a href="/"><img
								src="${pageContext.request.contextPath}/img/main-logo.png"
								alt="" /></a>
						</div>
						<p>Khách hàng luôn ở trung tâm trong những giao dịch độc nhất
							của chúng tôi, bao gốm cả thiết kế.</p>
						<a href="#"><img
							src="${pageContext.request.contextPath}/img/payment.png" alt="" /></a>
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
								<input type="text" placeholder="Email của bạn" />
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
						<!-- Search Begin -->
						<div class="search-model">
							<div
								class="h-100 d-flex align-items-center justify-content-center">
								<div class="search-close-switch">+</div>
								<form class="search-model-form">
									<input type="text" id="search-input"
										placeholder="Search here.....">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<script>
		function handleSearchChange() {
			const searchInput = document.getElementById("searchInput");
			const searchOption = document.getElementById("inputGroupSelect01").value;
			if (searchOption === "date") {
				searchInput.setAttribute("type", "date");
			}
			if (searchOption === "id") {
				searchInput.setAttribute("type", "text");
			}
		}
	</script>

	<!-- Js Plugins -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/jquery.nice-select.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/jquery.nicescroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/jquery.countdown.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery.slicknav.js"></script>
	<script src="${pageContext.request.contextPath }/js/mixitup.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/main.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
</body>

</html>