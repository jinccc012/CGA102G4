<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>
<%@ page import="com.rcarorder.model.RcarOrderService"%>
<%@ page import="com.rcarorder.model.*"%>

<%
	HttpSession ss = request.getSession();
	MemberVO member = (MemberVO)ss.getAttribute("account");
%>

<!doctype html>
<html lang="zh-tw">

<head>
<title>Family Rent</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png"
	type="image/x-icon" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap"
	rel="stylesheet">
<!-- 自己的css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/head.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/scar/scarAuctionAll.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<%-- js --%>
<!-- <script -->
<!-- 	src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script> -->
<!-- <script -->
<!-- 	src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script> -->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>

@import
	url('https://fonts.googleapis.com/css2?family=Koulen&family=Teko:wght@300&display=swap')
	;
</style>

</head>

<body>


	<div class="site-wrap" id="home-section">
		<header class="site-navbar site-navbar-target" id="head" role="banner">
			<div class="container">
				<div class=" align-items-center logo">
					<div class="site-logo">
						<a href="<%=request.getContextPath()%>/index" class="nav-link">
							<h1 style="font-family: 'Koulen', cursive; font-size: 50px;">Family
								Rent</h1>
						</a>
					</div>

					<nav class="site-navigation text-right ml-auto d-none d-lg-block"
						role="navigation">
						<ul class="site-menu main-menu js-clone-nav ml-auto ">
							<li><a href="<%=request.getContextPath()%>/index"
								class="nav-link menuStyle">首頁</a></li>
							<li id="ch">
								<a href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">租車</a>
							</li>
							<li><a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" id="dropdownMenuLink" role="button" 
                  style="padding-bottom: 0px;">中古車</a>
								</li>
							<li><a href="${pageContext.request.contextPath}/event/eventShowList" class="nav-link menuStyle">活動資訊</a></li>
							<li><a href="${pageContext.request.contextPath}/front_end/member/ContactList.jsp" class="nav-link menuStyle" role="button">聯絡我們</a></li>
							<c:if test="${account == null}">
								<li><a
									href="<%=request.getContextPath()%>/front_end/Login/Login.html"
									class="nav-link menuStyle">登入</a></li>
							</c:if>
							<c:if test="${account != null}">
								<li>
									<%-- <a href="#" class="nav-link menuStyle"></a> --%> <a
									href="#" style="padding-bottom: 0px;"
									class="nav-link menuStyle" id="dropdownMenuLink" role="button"
									data-bs-toggle="dropdown">${account.meb_name}</a>
									<ul class="dropdown-menu " aria-labelledby="dropdownMenuLink">
				                      <li><a class="dropdown-item" href="<%=request.getContextPath()%>/memberedit?meb_no=<%=member.getMeb_no()%>" >會員資料</a></li>
				                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/order/memberOrdersDesc">查看訂單</a></li>
				                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/bidding/MemberBidding">中古車競拍紀錄</a></li>
				                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/scar/scarMebReserve">中古車預約紀錄</a></li>
				                      <li><a class="dropdown-item" href="#">Line綁定</a></li>
				                    </ul>
								</li>
								<li><a href="<%=request.getContextPath()%>/login"
									class="nav-link menuStyle">登出</a></li>
							</c:if>

						</ul>
					</nav>
				</div>
			</div>

		</header>
		<div class="hero inner-page"
			style="background-image: url('<%=request.getContextPath()%>/resources/img/hero_1_a.jpg'); ">
			<div class="container">
				<div class="row align-items-end ">
					<div class="col-lg-5">

						<div class="intro">
							<h1>
								<strong>中古車</strong>
							</h1>
							<div class="custom-breadcrumbs">
								<a href="<%= request.getContextPath()%>/index">首頁</a> <span class="mx-2">/</span> <strong>中古車</strong>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section bg-light" style="position: relative;">

		<!-- 使用foreach取出現在正在拍賣的車輛 -->
		<c:forEach var="scar" items="${list}" varStatus="i">
			<!-- 下方區塊start -->
			<div class='divunder'>
				<!-- 左側圖片 -->
				<div>
					<p class='text-startime' style='text-align: center; margin-top: 10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;起拍時間: <fmt:formatDate value="${scar.scar_startime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
					<img class='scarimg' src="${pageContext.request.contextPath}/scar/scarimg?scar_no=${scar.scar_no}">
				</div>
				<!-- 右側區塊 -->
				<div class='divr'>
					<span class='text-rt'>拍賣結束時間: <br> <fmt:formatDate value="${scar.scar_endtime}" pattern="yyyy-MM-dd HH:mm:ss" /></span><br>
					<span class='text-r'>品牌: ${scar.scar_brand}</span><br> <span
						class='text-r'>車型: ${scar.scar_model}</span><br> <span
						class='text-r'>里程數: ${scar.scar_miles}公里</span><br> <span
						class='text-r'>年分: ${scar.scar_year}</span><br> <span
						class='text-r'>所在站點: ${scar.st_no}</span><br>

					<form class='formdetail' action="${pageContext.request.contextPath}/scar/scarAuctionDetail">
						<span class='text-r2'>目前價格: 
							<c:if test="${scar.scar_maxprice == 0}">${scar.scar_startprice}</c:if>
							<c:if test="${scar.scar_maxprice != 0}">${scar.scar_maxprice}</c:if>
						</span>
						<input type="submit" id="submitx" value="詳細資訊" class="btn btn-warning inpdetail${scar.scar_status}" style="font-size: 15px; color: white; background-color: #FFA042; font-weight: 900; border-radius: 10px; width: 90%;">
						<input type="hidden" value="${scar.scar_endtime}">
						<input type="hidden" name="action" value="scardetail">
						<input type="hidden" name="scar_no" value="${scar.scar_no}">
					</form>

					<!-- 預約賞車按鈕 -->
					<input type="button" class="btn btn-warning reservationbtn" name=""
						value="預約賞車" id="${i.count}"
						style="position: relative; top: 10px; font-size: 15px; color: white; background-color: #FFA042; font-weight: 900; border-radius: 10px; width: 90%;">
				</div>
			</div>
			<!-- 下方區塊end -->

			<!-- 隱藏的預約表單 -->
			<div class="resform" id="reservation${i.count}" style="position: fixed; width: 240px; height: 270px; background-color: #FFF0D4; top: 30vh; left: 170vh; z-index: 2; display: none; border: 2px solid #FFBA3B; border-radius: 10px;">
				<form action="${pageContext.request.contextPath}/scar/scarReserve" method="post">
					<p style="text-align: center; font-weight: 900; margin: 0px;">會員編號:</p>
					<input name="meb_no" style="text-align: center; background-color: #FFFAF2; font-weight: 900; margin-left: 20px;" value="${account.meb_no}" readonly>
					
					<p style="text-align: center; font-weight: 900; margin: 0px;">車輛編號:</p>
					<input name="scar_no" style="text-align: center; background-color: #FFFAF2; font-weight: 900; margin-left: 20px;" value="${scar.scar_no}" readonly>
					
					<p style="text-align: center; font-weight: 900; margin: 0px;">賞車站點:</p>
					<input name="st_no" style="text-align: center; background-color: #FFFAF2; font-weight: 900; margin-left: 20px;" value="${scar.st_no}" readonly>
					
					<p style="text-align: center; font-weight: 900; margin: 0px;">預約日期:</p>
					<div style="justify-content: center; display: flex;">
						<input name="sr_time" class="f_date1" type="text" style="background-color: #FFFAF2; font-weight: 900; text-align: center;">
					</div>
	
					<div style="justify-content: center; display: flex; margin-top: 10px;">
						<input class="cancel btn btn-secondary" type="button" value="取消" style="width: 80px; height: 30px; padding: 0px; margin-right: 10px;">
						<input class="btn btn-warning" id="sub" type="submit" value="送出" style="width: 80px; height: 30px; padding: 0px;">
					</div>
				</form>
			</div>



		</c:forEach>

	</div>

	<footer class="site-footer">
		<div class="container">
			<div class="" style="display: flex; justify-content: center;">

				<div class="col-lg-8">
					<div class="row">
						<div class="col-lg-3">
							<h2 class="footer-heading mb-4">租車</h2>
							<ul class="list-unstyled">
								<li><a href="#">車款介紹</a></li>
								<li><a href="#">我要租車</a></li>
							</ul>
						</div>
						<div class="col-lg-3">
							<h2 class="footer-heading mb-4">中古車</h2>
							<ul class="list-unstyled">
								<li><a href="#">拍賣車輛</a></li>
								<li><a href="#">預約賞車</a></li>
							</ul>
						</div>
						<div class="col-lg-3">
							<h2 class="footer-heading mb-4">最新消息</h2>
							<ul class="list-unstyled">
								<li><a href="#">查看最新消息</a></li>
							</ul>
						</div>
						<div class="col-lg-3">
							<h2 class="footer-heading mb-4">聯絡我們</h2>
							<ul class="list-unstyled">
								<li><a href="#">顧客意見</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row pt-5 mt-5 text-center">
				<div class="col-md-12">
					<div class="border-top pt-5">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="icon-heart text-danger" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							<!--               Family Rent &copy; -->
							<!--               <script>document.write(new Date().getFullYear());</script> -->
						</p>
					</div>
				</div>

			</div>
		</div>
	</footer>
	
	<script>
		// 按這個按鈕的時候，如果(現在時間-結拍時間)>0，跳出訊息拍賣結束，然後重整;
		$(document).on('submit','.formdetail',function(e){
			let a = $.now();
			let b = Date.parse($('#submitx').next().val());
			if(a-b>0){
				swal('本拍賣已結束', '頁面即將重整', 'warning');
				setTimeout(function(){
					window.location.reload();
				},1500);
				return false;
			}else{
				return true;
			}
		})
		
		let inpdetail0 = document.querySelector('.inpdetail0');
		if (inpdetail0 != null){
			inpdetail0.setAttribute('disabled','true');
		}
	</script>
	
	<script
		src="<%=request.getContextPath()%>/front_end/order/memberOrders.js"></script>
	<!-- 註冊預約按鈕，顯示DIV -->

	<script>
		$(document).on('click', '.reservationbtn', function(e) {
			const id = $(this).attr('id');
			$('#reservation' + id).show();
		});
		$(document).on('click', '.cancel', function(e) {
			$(this).parent().parent().parent().hide();
		});
	</script>
	
	<script>
		$(document).on('click','#sub',function(e){
			alert('預約成功');
		});
	</script>

	<!-- 日期選擇 -->
	<script>
		$.datetimepicker.setLocale('zh'); // kr ko ja en
		$('.f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i',
			value : null,
			//		            disabledDates:    ['2022/06/08','2022/06/09','2022/06/10'], // 去除特定不含
			//		            startDate:	        '2022/07/10',  // 起始日
			minDate : '-1970-01-01', // 去除今日(不含)之前
			maxDate : '+1970-01-14' // 去除今日(不含)之後
		});
	</script>

</body>

</html>



