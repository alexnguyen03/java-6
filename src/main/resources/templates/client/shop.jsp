<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8" />
<meta name="description" content="Male_Fashion Template" />
<meta name="keywords" content="Male_Fashion, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Sản phẩm</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
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
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
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
						<h4>Sản phẩm</h4>
						<div class="breadcrumb__links">
							<a href="/">Trang chủ</a> <span>Sản phẩm</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Shop Section Begin -->
	<section class="shop spad">
		<div class="container">
			<div class="row">
				<!--  Sidebar -->
				<div class="col-lg-3">
					<div class="shop__sidebar">
						<div class="shop__sidebar__search">
							<form action="/shop/shop-search-product" method="POST">
								<input type="text" name="keywords" value="${keywords}"
									placeholder="Tìm kiếm..." />
								<button type="submit">
									<span class="icon_search"></span>
								</button>
							</form>
						</div>
						<div class="shop__sidebar__accordion">
							<div class="accordion" id="accordionExample">
								<div class="card">
									<div class="card-heading">
										<a data-toggle="collapse" data-target="#collapseOne">Danh
											mục</a>
									</div>
									<div id="collapseOne" class="collapse show"
										data-parent="#accordionExample">
										<div class="card-body">
											<div class="shop__sidebar__categories">
												<ul class="nice-scroll">
													<c:forEach var="item" items="${categoryLst}">
														<li><a href="/shop/sort-by-category/${item.name}">${item.name}</a>
														</li>
													</c:forEach>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-heading">
										<a data-toggle="collapse" data-target="#collapseThree">Lọc
											theo giá</a>
									</div>
									<div id="collapseThree" class="collapse show"
										data-parent="#accordionExample">
										<div class="card-body">
											<div class="shop__sidebar__price">
												<ul>
													<li><a
														href="/shop/shop-search-product-by-price?startPrice=0.0&endPrice=1000000.0">Dưới
															1 triệu</a></li>
													<li><a
														href="/shop/shop-search-product-by-price?startPrice=1000000.0&endPrice=2000000.0">1
															- 2 triệu</a></li>
													<li><a
														href="/shop/shop-search-product-by-price?startPrice=2000000.0&endPrice=3000000.0">2
															- 3 triệu</a></li>
													<li><a
														href="/shop/shop-search-product-by-price?startPrice=3000000.0&endPrice=4000000.0">3
															- 4 triệu</a></li>
													<li><a
														href="/shop/shop-search-product-by-price?startPrice=5000000.0&endPrice=10000000.0">5
															- 10 triệu</a></li>
													<li><a
														href="/shop/shop-search-product-by-price?startPrice=10000000.0&endPrice=999999999.0">Trên
															10 triệu</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-heading">
										<a data-toggle="collapse" data-target="#collapseSix">Thẻ</a>
									</div>
									<div id="collapseSix" class="collapse show"
										data-parent="#accordionExample">
										<div class="card-body">
											<div class="shop__sidebar__tags">
												<a href="/shop">Sản phẩm</a> <a
													href="/shop/shop-search-product-by-tab?keywords=sạc dự phòng">Sạc
													dự phòng</a> <a
													href="/shop/shop-search-product-by-tab?keywords=tainghe">Tai
													nghe</a> <a
													href="/shop/shop-search-product-by-tab?keywords=earpod">EarPods</a>
												<a href="/shop/shop-search-product-by-tab?keywords=ốp lưng">Ốp
													lưng</a> <a
													href="/shop/shop-search-product-by-tab?keywords=loa">Loa</a>
												<a href="/shop/shop-search-product-by-tab?keywords=micro">Micro</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--   Product Row  -->
				<div class="col-lg-9">
					<!-- Product header sorrt -->
					<div class="shop__product__option">
						<div class="row">
							<!--  Item top header -->
							<div class="col-lg-6 col-md-6 col-sm-6">
								<div class="shop__product__option__left">
									<p>Hiển thị 1 – ${page.size} trên ${page.totalElements} kết
										quả.</p>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
								<form action="/shop/sort-by-price-product" method="POST">
									<div class="shop__product__option__right">
										<p>Sắp xếp theo giá:</p>
										<select name="product-header-sort"
											onchange="this.form.submit()" name="field">
											<option value="ASC">Thấp đến cao</option>
											<option value="DESC">Cao đến thấp</option>
										</select>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Product List -->
					<div class="row">
						<c:forEach var="item" items="${page.content}">
							<!-- Item -->
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div class="product__item sale">
									<a href="/shop/product-detail?id=${item.id}"
										class="product__item__pic"> <img
										src="${pageContext.request.contextPath}/img/product/${item.image}"
										class="img-fluid" alt="${item.name}" />
									</a>
									<div class="product__item__text">
										<h6>${item.name}</h6>
										<a href="/shop/cart-detail/add/${item.id}" class="add-cart">+
											Thêm vào giỏ hàng</a>
										<h5>
											<fmt:formatNumber value="${item.price}" type="currency"
												currencySymbol="" />
											<span class="text-danger" style="text-decoration: underline">đ</span>
										</h5>
									</div>
								</div>
							</div>
						</c:forEach>

						<c:if test="${empty page.content}">
							<div
								class="d-flex justify-content-center mt-5 align-items-center flex-column w-100">
								<img src="${pageContext.request.contextPath}/img/cat-delete.jpg"
									style="border-radius: 50%; width: 150px" alt="" />
								<h6 class="text-center mt-3 w-50 font-weight-bold">
									Rất tiếc, sản phẩm bạn tìm kiếm hiện <span>"chưa được
										lên kệ."</span>
								</h6>
							</div>
						</c:if>
					</div>

					<!-- Pagiantion -->
					<div class="row">
						<div class="col-lg-12">
							<div class="product__pagination">
								<c:if test="${page.totalPages > 1}">
									<c:forEach var="i" begin="0" end="${page.totalPages - 1}">
										<a class="${page.number==i?'active':''}" href="/shop?p=${i}">${i+1}</a>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Shop Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__logo">
							<a href="/"><img src="img/main-logo.png" alt="" /></a>
						</div>
						<p>Khách hàng luôn ở trung tâm trong những giao dịch độc nhất
							của chúng tôi, bao gốm cả thiết kế.</p>
						<a href="#"><img src="img/payment.png" alt="" /></a>
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
				<input type="text" id="search-input" placeholder="Tìm kiếm....." />
			</form>
		</div>
	</div>
	<!-- Search End -->

	<script>
		console.log("JavaScript code loaded");

		document.getElementById("mySelect").addEventListener("change",
				function() {
					document.getElementById("myForm").submit();
				});
	</script>

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>

	<!-- Js Plugins -->
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.nicescroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.countdown.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.slicknav.js"></script>
	<script src="${pageContext.request.contextPath}/js/mixitup.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
