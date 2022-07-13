<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>
<%@ page import="com.rcarorder.model.RcarOrderService"%>
<%@ page import="com.rcarorder.model.*"%>

<%
HttpSession ss = request.getSession();
MemberVO member = (MemberVO) ss.getAttribute("account");
String mebNo = member.getMeb_no();
RcarOrderService ros = new RcarOrderService();
List<RcarOrderVO> list = ros.getMemberOrdersDesc(mebNo);
pageContext.setAttribute("list",list);
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
	href="<%=request.getContextPath()%>/front_end/order/memberOrders.css">
<link
	href="https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
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

					<nav class="site-navigation text-right ml-auto d-none d-lg-block"
						role="navigation">
						<ul class="site-menu main-menu js-clone-nav ml-auto ">
							<li><a href="<%=request.getContextPath()%>/index"
								class="nav-link menuStyle">首頁</a></li>
							<li id="ch">
								 <a href="" class="nav-link menuStyle" id="dropdownMenuLink" role="button" data-bs-toggle="dropdown" style="padding-bottom: 0px;">租車</a>
				                <ul class="dropdown-menu " aria-labelledby="dropdownMenuLink">
				                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp">立即預約</a></li>
				                  <li><a class="dropdown-item" href="#">預約賞車</a></li>
				                </ul>
							</li>
						 	<li><a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" id="dropdownMenuLink" role="button" 
                  style="padding-bottom: 0px;">中古車</a>
<!-- 								<ul class="dropdown-menu " aria-labelledby="dropdownMenuLink"> -->
<!-- 									<li><a class="dropdown-item" href="#">拍賣車輛</a></li> -->
<!-- 									<li><a class="dropdown-item" href="#">預約賞車</a></li> -->
<!-- 								</ul> -->
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
									data-bs-toggle="dropdown"><%=member.getMeb_name()%></a>
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
								<strong>會員中心</strong>
							</h1>
							<div class="custom-breadcrumbs">
								<a href="index.html">首頁</a> <span class="mx-2">/</span> <strong>租車訂單</strong>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section bg-light">

		<div class="container">
			<div class="" style="display: flex; justify-content: space-between; width:100%">
				<div class="col-lg-7">
					<span id="order_status777" class="mb-5" style="font-size: 20px;">全部訂單</span>
					<span class="mb-5" style="font-size: 20px;"> | </span> <span
						id="order_status0" class="mb-5" style="font-size: 20px;">訂單成立</span>
					<span class="mb-5" style="font-size: 20px;"> | </span> <span
						id="order_status1" class="mb-5" style="font-size: 20px;">出車中</span>
					<span class="mb-5" style="font-size: 20px;"> | </span> <span
						id="order_status2" class="mb-5" style="font-size: 20px;">已結案</span>
					<span class="mb-5" style="font-size: 20px;"> | </span> <span
						id="order_status3" class="mb-5" style="font-size: 20px;">未結案</span>
					<span class="mb-5" style="font-size: 20px;"> | </span> <span
						id="order_status4" class="mb-5" style="font-size: 20px;">訂單已取消</span>
				</div>
				<div><%@ include file="page1.file" %></div>
			</div>
			<!-- 顯示訂單的區塊 -->
			<div class="tablediv">
				<table class="table">
					<thead class="thead" style="background-color: rgb(196, 218, 254);">
						<tr>
							<th>訂單編號 <a href='<c:if test="${orderby != null}">${pageContext.request.contextPath}${orderby}</c:if>' id="a_order">
<%-- 							<i class="fa fa-caret-${imgtype}" aria-hidden="true" id='a_order'></i> --%>
							</a>
							</th>
							<th>下訂時間</th>
							<th>取車時間</th>
							<th>取車地點</th>
							<th>訂單狀態</th>
							<th>訂單詳情</th>
						</tr>
					</thead>
					<!-- 	要修改的資料 -->
					<tbody class="tbody" style="background-color: rgb(243, 250, 253);">
						<c:forEach var="member_order" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${member_order.rcaro_no}</td>
								<td><fmt:formatDate value="${member_order.rcaro_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${member_order.rcaro_ppicktime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${member_order.rcaro_pickuploc}</td>
								<c:choose>
									<c:when test="${member_order.rcaro_status == 0}">
										<td>訂單成立</td>
									</c:when>
									<c:when test="${member_order.rcaro_status == 1}">
										<td>出車中</td>
									</c:when>
									<c:when test="${member_order.rcaro_status == 2}">
										<td>已結案</td>
									</c:when>
									<c:when test="${member_order.rcaro_status == 3}">
										<td>未結案</td>
									</c:when>
									<c:when test="${member_order.rcaro_status == 4}">
										<td>訂單已取消</td>
									</c:when>
								</c:choose>
								<td><a
									href="${pageContext.request.contextPath}/order/memberOrderDetail?rcaro_no=${member_order.rcaro_no}"
									style='text-decoration: none; color: blue;'>詳細資訊</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div style="display: flex; justify-content: center;"><div><%@ include file="page2.file" %></div></div>
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
		src="<%=request.getContextPath()%>/front_end/order/memberOrders.js"></script>
</body>

</html>



