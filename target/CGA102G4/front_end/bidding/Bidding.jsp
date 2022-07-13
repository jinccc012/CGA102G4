<%@page import="com.bidding.model.BiddingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.scar.model.*"%>

<%
BiddingVO biddingVO = (BiddingVO) request.getAttribute("biddingVO");
%>
<%
HttpSession ss = request.getSession();
MemberVO member = (MemberVO) ss.getAttribute("account");
%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<title>Family Rent</title>
<link rel="icon" href="<%=request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png" type="image/x-icon" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">
<!-- 自己的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/head.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/order/memberOrders.css">
<link href="https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<!-- MAIN CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<%-- js --%>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
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
							<li><a href="<%= request.getContextPath()%>/index" class="nav-link menuStyle">首頁</a></li>
							<li id="ch">
								<a href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">租車</a>
							</li>
								<li>
									 <a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">中古車</a>
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
				                      <li><a class="dropdown-item" href="<%=request.getContextPath()%>/memberedit?meb_no=${account.meb_name}">會員資料</a></li>
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
								<a href="<%= request.getContextPath()%>/index">首頁</a> <span class="mx-2">/</span> <strong>中古車競拍查詢</strong>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section bg-light">

		<div class="container">


			<!-- ==============================從這裡開始寫============================== -->


			<div class="tablediv">
<%-- <%@ include file="page1.file" %> --%>
				<form method="post"
					action="<%=request.getContextPath()%>/MemberBidding">

					<table class="table">
						<thead class="thead" style="background-color: rgb(196, 218, 254);">
							<tr>
								<th style="text-align: center">競拍編號</th>
								<th style="text-align: center">中古車編號</th>
								<th style="text-align: center">會員編號</th>
								<th style="text-align: center">競標價</th>
								<th style="text-align: center">下標時間</th>
							</tr>
						</thead>
							<input type="submit" value="查詢" disabled="disabled" style="opacity:0;"> 
							<input type="hidden" value="getAll">
							
						<tbody class="tbody" style="background-color: rgb(243, 250, 253);">
							
							<c:forEach var="biddingVO" items="${getBidByMEB_NO}">
								<tr style="text-align:center;">
									<td>${biddingVO.bid_no}</td>
									<td>${biddingVO.scar_no}</td>
									<td>${biddingVO.meb_no}</td>
									<td><fmt:formatNumber value="${biddingVO.bid_price}" pattern="#,##0.000" maxFractionDigits='0'/></td>
									<td><fmt:formatDate value="${biddingVO.bid_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								</tr>
							</c:forEach>
							
							
							
							
							
						</tbody>
					</table>
				</form>
<%--  <%@ include file="page2.file"%>					 --%>
			</div>
		</div>
	</div>
	<!-- ==============================下面也是公版============================== -->
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


