/*  ---------------------------------------------------
	Template Name: Male Fashion
	Description: Male Fashion - ecommerce teplate
	Author: Colorib
	Author URI: https://www.colorib.com/
	Version: 1.0
	Created: Colorib
---------------------------------------------------------  */

'use strict';

(function ($) {
	/*------------------
		  Preloader
	  --------------------*/
	$(window).on('load', function () {
		$('.loader').fadeOut();
		$('#preloder').delay(200).fadeOut('slow');

		/*------------------
				Gallery filter
			--------------------*/
		$('.filter__controls li').on('click', function () {
			$('.filter__controls li').removeClass('active');
			$(this).addClass('active');
		});
		if ($('.product__filter').length > 0) {
			var containerEl = document.querySelector('.product__filter');
			var mixer = mixitup(containerEl);
		}
	});

	let star = document.querySelectorAll('input');
	let showValue = document.querySelector('#rating-value');

	for (let i = 0; i < star.length; i++) {
		star[i].addEventListener('click', function () {
			i = this.value;
			showValue.value = i;
		});
	}

	const decreaseBtn = document.querySelector('.decrease');
	const increaseBtn = document.querySelector('.increase');
	const quantityInput = document.getElementById('quantity');
	let maxQuantity = parseInt(document.getElementById("maxQuantity").value);

	decreaseBtn.addEventListener('click', () => {
		let value = parseInt(quantityInput.value);
		value = isNaN(value) ? 0 : value;
		value--;
		if (value < 1) {
			value = 1;
		}
		quantityInput.value = value;
	});

	increaseBtn.addEventListener('click', () => {
		let value = parseInt(quantityInput.value);
		value = isNaN(value) ? 0 : value;
		value++;
		if(value > maxQuantity){
			alert("Số lượng tối đa cho sản phẩm này là " + maxQuantity);
		}else{
			quantityInput.value = value;
		}

	});
	
	quantityInput.addEventListener('change', function() {
	    // Lấy số lượng sản phẩm
	    var quantity = parseInt(quantityInput.value);
	    
	    // Kiểm tra nếu số lượng sản phẩm lớn hơn số lượng hàng có sẵn
	    if (quantity > maxQuantity) {
	    	alert("Số lượng tối đa cho sản phẩm này là " + maxQuantity);
	    	quantityInput.value = maxQuantity;
	    }else if (quantity <= 0) {
			alert("Số lượng phải lớn hơn 0 !!!");
			quantityInput.value = 1;
		}
  	});
  	
  	quantityInput.addEventListener('keypress', function(event) {
	    // Lấy mã ASCII của phím được nhấn
	    var keycode = event.keyCode || event.which;
	
	    // Kiểm tra nếu phím được nhấn là phím enter
	    if (keycode === 13) {
	        // Ngăn chặn submit mặc định của trình duyệt
	        event.preventDefault();
	    }
	});
	
//	let currentQuantity = parseInt(document.getElementById("quantity").value);
//	if (currentQuantity >= maxQuantity) {
//   		alert("Số lượng tối đa cho sản phẩm này là " + maxQuantity);
//   		document.getElementById("quantity").value = 1;
//	}


	/*------------------
		  Background Set
	  --------------------*/
	$('.set-bg').each(function () {
		var bg = $(this).data('setbg');
		$(this).css('background-image', 'url(' + bg + ')');
	});

	//Search Switch
	$('.search-switch').on('click', function () {
		$('.search-model').fadeIn(400);
	});

	$('.search-close-switch').on('click', function () {
		$('.search-model').fadeOut(400, function () {
			$('#search-input').val('');
		});
	});

	/*------------------
		  Navigation
	  --------------------*/
	$('.mobile-menu').slicknav({
		prependTo: '#mobile-menu-wrap',
		allowParentLinks: true,
	});

	/*------------------
		  Accordin Active
	  --------------------*/
	$('.collapse').on('shown.bs.collapse', function () {
		$(this).prev().addClass('active');
	});

	$('.collapse').on('hidden.bs.collapse', function () {
		$(this).prev().removeClass('active');
	});

	//Canvas Menu
	$('.canvas__open').on('click', function () {
		$('.offcanvas-menu-wrapper').addClass('active');
		$('.offcanvas-menu-overlay').addClass('active');
	});

	$('.offcanvas-menu-overlay').on('click', function () {
		$('.offcanvas-menu-wrapper').removeClass('active');
		$('.offcanvas-menu-overlay').removeClass('active');
	});

	/*-----------------------
		  Hero Slider
	  ------------------------*/
	$('.hero__slider').owlCarousel({
		loop: true,
		margin: 0,
		items: 1,
		dots: false,
		nav: true,
		navText: ["<span class='arrow_left'><span/>", "<span class='arrow_right'><span/>"],
		animateOut: 'fadeOut',
		animateIn: 'fadeIn',
		smartSpeed: 1200,
		autoHeight: false,
		autoplay: false,
	});

	/*--------------------------
		  Select
	  ----------------------------*/
	$('select').niceSelect();

	/*-------------------
		  Radio Btn
	  --------------------- */
	$('.product__color__select label, .shop__sidebar__size label, .product__details__option__size label').on('click', function () {
		$('.product__color__select label, .shop__sidebar__size label, .product__details__option__size label').removeClass('active');
		$(this).addClass('active');
	});

	/*-------------------
		  Scroll
	  --------------------- */
	$('.nice-scroll').niceScroll({
		cursorcolor: '#0d0d0d',
		cursorwidth: '5px',
		background: '#e5e5e5',
		cursorborder: '',
		autohidemode: true,
		horizrailenabled: false,
	});

	/*------------------
		  CountDown
	  --------------------*/
	// For demo preview start
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	if (mm == 12) {
		mm = '01';
		yyyy = yyyy + 1;
	} else {
		mm = parseInt(mm) + 1;
		mm = String(mm).padStart(2, '0');
	}
	var timerdate = mm + '/' + dd + '/' + yyyy;
	// For demo preview end

	// Uncomment below and use your date //
	// Uncomment below and use your date //

	/* var timerdate = "2020/12/30" */
	/* var timerdate = "2020/12/30" */

	$('#countdown').countdown(timerdate, function (event) {
		$(this).html(event.strftime("<div class='cd-item'><span>%D</span> <p>Days</p> </div>" + "<div class='cd-item'><span>%H</span> <p>Hours</p> </div>" + "<div class='cd-item'><span>%M</span> <p>Minutes</p> </div>" + "<div class='cd-item'><span>%S</span> <p>Seconds</p> </div>"));
	});

	/*------------------
		  Magnific
	  --------------------*/
	$('.video-popup').magnificPopup({
		type: 'iframe',
	});

	/*-------------------
		  Quantity change
	  --------------------- */

	/*------------------
		  Achieve Counter
	  --------------------*/
	$('.cn_num').each(function () {
		$(this)
			.prop('Counter', 0)
			.animate(
				{
					Counter: $(this).text(),
				},
				{
					duration: 4000,
					easing: 'swing',
					step: function (now) {
						$(this).text(Math.ceil(now));
					}
				}
			);
	});
})(jQuery);

//  ============================ Custom code
// Scroll top navbar
var lastScroll = 0;
var navbar = document.getElementById('header_scroll_top');
window.addEventListener('scroll', function () {
	var scrollTop = window.pageYOffset || document.documentElement.scrollTop;
	if (scrollTop > lastScroll) {
		navbar.style.top = '-150px';
	} else {
		navbar.style.top = '0';
	}
	lastScroll = scrollTop;
});
