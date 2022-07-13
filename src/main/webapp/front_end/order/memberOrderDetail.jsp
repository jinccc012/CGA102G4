<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>
<%@ page import="com.rcarorder.model.*"%>
<%
HttpSession ss = request.getSession();
MemberVO member = (MemberVO) ss.getAttribute("account");
%>

<!doctype html>
<html lang="zh-tw">

<head>
<title>Family Rent</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/html/icon/pngkey.com-gps-icon-png-5131691.png"
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
	href="<%=request.getContextPath()%>/front_end/order/memberOrderDetail.css">
<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<%-- js --%>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
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


					 <nav class="site-navigation text-right ml-auto d-none d-lg-block" role="navigation">
            <ul class="site-menu main-menu js-clone-nav ml-auto ">
              <li><a href="<%= request.getContextPath()%>/index" class="nav-link menuStyle">首頁</a></li>
              <li id="ch">
                <a href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">租車</a>
              </li>
              <li>
                <a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">中古車</a>
              </li>
              <li><a href="${pageContext.request.contextPath}/event/eventShowList" class="nav-link menuStyle">活動資訊</a></li>
              <li><a href="${pageContext.request.contextPath}/front_end/member/ContactList.jsp" class="nav-link menuStyle" role="button">聯絡我們</a></li>
              <li><a href="${pageContext.request.contextPath}/front_end/qa/QA.jsp" class="nav-link menuStyle" role="button">Q&A</a></li>
             <c:if test="${account == null}">
              	<li><a href="<%=request.getContextPath()%>/front_end/Login/Login.html" class="nav-link menuStyle">登入</a></li>
              </c:if>
              <c:if test="${account != null}">
              	  <li>
                  <%-- <a href="#" class="nav-link menuStyle"></a> --%>
                  <a href="#" style="padding-bottom: 0px;" class="nav-link menuStyle"  id="dropdownMenuLink" role="button" data-bs-toggle="dropdown">${account.meb_name}</a>
                    <ul class="dropdown-menu " aria-labelledby="dropdownMenuLink">
                      <li><a class="dropdown-item" href="<%=request.getContextPath()%>/memberedit?meb_no=${account.meb_no}">會員資料</a></li>
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/order/memberOrdersDesc">查看訂單</a></li>
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/bidding/MemberBidding">中古車競拍紀錄</a></li>
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/scar/scarMebReserve">中古車預約紀錄</a></li>
                      <li><a class="dropdown-item" href="#">Line綁定</a></li>
                    </ul>
                  </li>
            	  <li><a href="<%=request.getContextPath()%>/login" class="nav-link menuStyle">登出</a></li>
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
								<strong>租車</strong>
							</h1>
							<div class="custom-breadcrumbs">
								<a href="<%= request.getContextPath()%>/index">首頁</a>
								<span class="mx-2">/</span>
								<a href="${pageContext.request.contextPath}/order/memberOrdersDesc">訂單列表</a>
								<span class="mx-2">/</span>
								<strong>訂單詳細資訊</strong>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section bg-light">

		<div class="Order_Detail_div" style="width: 800px;">
		<div style="display: flex; align-items: center; justify-content: space-between;">
			<h1>訂單明細</h1>
			<div class="order_buttons">
				<!-- 按鈕 -->
				<button data-bs-toggle="modal" data-bs-target="#cancleModal"
					class="btn btn-secondary" onclick="window.history.back()">返回</button>
					<c:if test="${rcar_OrderVO.rcaro_status == 0}">
						<button data-bs-toggle="modal" data-bs-target="#cancleModal" class="btn btn-danger cancelorderbtn">取消訂單</button>
					</c:if>
			</div>
				</div>
			<!-- 跳出視窗內容 -->
			<div class="modal fade" id="cancleModal">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<!-- Header -->
						<div class="modal-header">
							<h1>取消訂單</h1>
						</div>
						<!-- Body -->
						<div class="modal-body">
							<div>請問您真的要取消訂單嗎? 取消訂單將僅退還部分訂金</div>
						</div>
						<!-- Footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-secondary"
								data-bs-dismiss="">保留訂單</button>
							<form action="${pageContext.request.contextPath}/order/cancel"
								method="post">
								<input type="hidden" name="rcaro_no"
									value="${rcar_OrderVO.rcaro_no}">
								<button type="submit" class="btn btn-danger" name="action"
									value="cancelOrder">我要取消</button>
							</form>
						</div>
					</div>
				</div>
			</div>

			<div class="customer_info">
				<br>

				<div class="div_order_id left_side">
					<label for="">訂單編號:</label><br> <input type="text"
						value="${rcar_OrderVO.rcaro_no}" id="order_id" disabled>
				</div>

				<div class="div_member_id right_side">
					<label for="">會員編號:</label><br> <input type="text"
						value="${rcar_OrderVO.meb_no}" id="member_id" disabled>
				</div>

				<div class="div_memeber_name left_side">
					<label for="">承租人姓名:</label><br> <input type="text"
						value="${rcar_OrderVO.lessee_name}" id="member_name" disabled>
				</div>

				<div class="div_member_phone right_side">
					<label for="">租賃車型:</label><br> <input type="text"
						value="${rcar_OrderVO.model_no}" id="member_phone" disabled>
				</div>

				<div class="div_member_phone left_side">
					<label for="">出車金額:</label><br> <input type="text"
						value="${rcar_OrderVO.rcaro_pay}" id="member_phone" disabled>
				</div>

				<div class="div_start_store right_side">
					<label for="">起租站點:</label><br> <select name="start_store"
						id="" disabled>
						<option value="${rcar_OrderVO.rcaro_pickuploc}" <c:if test="${rcar_OrderVO.rcaro_pickuploc eq 'KH'}">selected</c:if>>高雄</option>
						<option value="${rcar_OrderVO.rcaro_pickuploc}" <c:if test="${rcar_OrderVO.rcaro_pickuploc eq 'TPE'}">selected</c:if>>台北</option>
						<option value="${rcar_OrderVO.rcaro_pickuploc}" <c:if test="${rcar_OrderVO.rcaro_pickuploc eq 'TC'}">selected</c:if>>台中</option>
					</select>
				</div>

				<div class="div_return_store left_side">
					<label for="">歸還站點:</label><br> <select name="return_store"
						id="" disabled>
							<option value="${rcar_OrderVO.rcaro_returnloc}" <c:if test="${rcar_OrderVO.rcaro_returnloc eq 'KH'}">selected</c:if>>高雄</option>
						<option value="${rcar_OrderVO.rcaro_returnloc}" <c:if test="${rcar_OrderVO.rcaro_returnloc eq 'TPE'}">selected</c:if>>台北</option>
						<option value="${rcar_OrderVO.rcaro_returnloc}" <c:if test="${rcar_OrderVO.rcaro_returnloc eq 'TC'}">selected</c:if>>台中</option>
					</select>
				</div>

				<div class="div_real_return_store right_side">
					<label for="">實際歸還站點:</label><br> <select
						name="real_return_store" id="" disabled>
						<option value="" <c:if test="${rcar_OrderVO.rcaro_returnloc_actual eq ''}">selected</c:if>></option>
					<option value="${rcar_OrderVO.rcaro_returnloc_actual}" <c:if test="${rcar_OrderVO.rcaro_returnloc_actual eq 'KH'}">selected</c:if>>高雄</option>
						<option value="${rcar_OrderVO.rcaro_returnloc_actual}" <c:if test="${rcar_OrderVO.rcaro_returnloc_actual eq 'TPE'}">selected</c:if>>台北</option>
						<option value="${rcar_OrderVO.rcaro_returnloc_actual}" <c:if test="${rcar_OrderVO.rcaro_returnloc_actual eq 'TC'}">selected</c:if>>台中</option>
					</select>
				</div>

				<div class="corss_line"></div>

				<div class="div_rcar_dispatched left_side">
					<label for="">出租車號:</label><br> <input type="text"
						value="${rcar_OrderVO.rcar_no}" id="rcar_dispatched" disabled>
				</div>

				<div class="div_order_status right_side">
					<label for="">訂單狀態:</label><br> <input type="text"
						value='<c:if test="${rcar_OrderVO.rcaro_status == 0}">訂單成立</c:if><c:if test="${rcar_OrderVO.rcaro_status == 1}">出車中</c:if><c:if test="${rcar_OrderVO.rcaro_status == 2}">已結案</c:if><c:if test="${rcar_OrderVO.rcaro_status == 3}">未結案</c:if><c:if test="${rcar_OrderVO.rcaro_status == 4}">訂單已取消</c:if>'
						id="order_status" disabled>
				</div>

				<div class="div_order_car_time left_side">
					<label for="">取車時間:</label><br> <input
						value="${rcar_OrderVO.rcaro_ppicktime}" type="text"
						id="order_car_time" disabled>
				</div>

				<div class="div_order_return_time right_side">
					<label for="">還車時間:</label><br> <input
						value="${rcar_OrderVO.rcaro_pprettime}" type="text"
						id="order_return_time" disabled>
				</div>

				<div class="div_give_car_real_time left_side">
					<label for="">實際取車時間:</label><br> <input
						value="${rcar_OrderVO.rcaro_rpicktime}" type="text"
						id="give_car_real_date" disabled>
				</div>

				<div class="div_return_car_real_time right_side">
					<label for="">實際還車時間:</label><br> <input
						value="${rcar_OrderVO.rcaro_rrettime}" type="text"
						id="return_car_real_date" disabled>
				</div>

				<div class="div_give_car_real_time left_side">
					<label for="">額外收費:</label><br> <input
						value="${rcar_OrderVO.rcaro_extra_pay}" type="text"
						id="give_car_real_date" disabled>
				</div>

				<div class="div_return_car_real_time right_side">
					<label for="">額外收費狀態:</label><br> <input
						value="<c:if test="${rcar_OrderVO.rcaro_extra_pay_status == 0}">未付款</c:if><c:if test="${rcar_OrderVO.rcaro_extra_pay_status == 1}">已付款</c:if>"
						type="text" id="return_car_real_date" disabled>
				</div>

			</div>
		</div>

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

	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>





</body>

</html>



