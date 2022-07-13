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
<link rel="icon" href="<%=request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png" type="image/x-icon" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">

<!-- 自己的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/head.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/scar/scarAuctionDetail.css">
<link href="https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<!-- MAIN CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<%-- js --%>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
	@import
	url('https://fonts.googleapis.com/css2?family=Koulen&family=Teko:wght@300&display=swap');
</style>
</head>

<body onload="connect();" onunload="disconnect();">
	<div class="site-wrap" id="home-section">
		<header class="site-navbar site-navbar-target" id="head" role="banner">
			<div class="container">
				<div class=" align-items-center logo">
					<div class="site-logo">
						<a href="<%=request.getContextPath()%>/index" class="nav-link">
							<h1 style="font-family: 'Koulen', cursive; font-size: 50px;">Family Rent</h1>
						</a>
					</div>
					<nav class="site-navigation text-right ml-auto d-none d-lg-block"
						role="navigation">
						<ul class="site-menu main-menu js-clone-nav ml-auto ">
							<li><a href="<%=request.getContextPath()%>/index"
								class="nav-link menuStyle">首頁</a></li>
							<li id="ch"><a href="" class="nav-link menuStyle"
								id="dropdownMenuLink" role="button" data-bs-toggle="dropdown"
								style="padding-bottom: 0px;">租車</a>
								<ul class="dropdown-menu " aria-labelledby="dropdownMenuLink">
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp">立即預約</a></li>
									<li><a class="dropdown-item" href="#">預約賞車</a></li>
								</ul></li>
							<li><a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" id="dropdownMenuLink" role="button" 
                  style="padding-bottom: 0px;">中古車</a>
<!-- 								<ul class="dropdown-menu " aria-labelledby="dropdownMenuLink"> -->
<%-- 									<li><a class="dropdown-item" href="<%=request.getContextPath()%>/scar/scarAuctionAll">拍賣車輛</a></li> --%>
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
									data-bs-toggle="dropdown">${account.meb_name}</a>
									<ul class="dropdown-menu " aria-labelledby="dropdownMenuLink">
				                      <li><a class="dropdown-item" href="<%=request.getContextPath()%>/memberedit?meb_no=<%=member.getMeb_no()%>" >會員資料</a></li>
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
								<a href="index.html">首頁</a> <span class="mx-2">/</span> <strong>租車</strong>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section bg-light" style="position: relative;">

		<!-- 下方區塊start -->
		<div class='divunder'>
			<!-- 圖片 -->
			<div>
				<img class='scarimg'
					src="${pageContext.request.contextPath}/scar/scarimg?scar_no=${scarVO.scar_no}">
			</div>
			<!-- 文字 -->
			<div class="divtext">
				<!-- 左側區塊 -->
				<div class='divl'>
					<span class='text-r'>品牌: ${scarVO.scar_brand}</span><br> <span
						class='text-r'>車型: ${scarVO.scar_model}</span><br> <span
						class='text-r'>排氣量: ${scarVO.scar_cc}</span><br> <span
						class='text-r'>變數系統: ${scarVO.scar_trans}</span><br> <span
						class='text-r'>燃料: ${scarVO.scar_fuel}</span><br> <span
						class='text-r'>最大乘載人數: ${scarVO.scar_carrying}</span><br> <span
						class='text-r'>最大乘載行李數: ${scarVO.scar_carringpkg}</span><br>
				</div>
				<!-- 右側區塊 -->
				<div class='divr'>
					<span class='text-rt'>拍賣時間剩餘: </span>
					<span class='text-rt' id="getting-started"></span><br>
					<span class='text-r'>里程數: ${scarVO.scar_miles}公里</span><br>
					<span class='text-r'>年分: ${scarVO.scar_year}</span><br>
					<span class='text-r'>所在站點: ${scarVO.st_no}</span><br>
					<!-- 第一次連線的時候需要抓資料庫，之後從server推送資訊過來，改變標籤內容 -->
					<span class='text-r2'>目前價格:</span>
					<span class='text-r2' id='currentprice'>
						<c:if test="${scarVO.scar_maxprice == 0}">${scarVO.scar_startprice}</c:if>
						<c:if test="${scarVO.scar_maxprice != 0}">${scarVO.scar_maxprice}</c:if>
					</span><br>
					<!-- 出價的地方 -->
					<form class='formdo' action="${pageContext.request.contextPath}/scar/offerPrice">
						<input class='inputdo' id="inputprice" type="number" min="0" max="200000000" step="1000" placeholder="${errorMessages.bid_price}" name="bid_price">
						<input type="hidden" name="scar_no" value="${scarVO.scar_no}">
						<input class="btn btn-warning inputdobtn" type="submit" name="sub" value="我要出價" style="font-size: 15px; color: white; font-weight: 900; border-radius: 10px;">
						<input class="btn btn-warning inputdobtn" type="hidden" name="action" value="iwanttobuy">
						<i class="fa fa-question-circle fado" aria-hidden="true" title="有任何問題請撥打客服專線: 0800-000-000"></i>
					</form>

				</div>
			</div>
<!-- 			顯示最高價的人 -->
			<div style="text-align: center;">
				<c:if test="${mebName != null}">
					<span style="color: red; font-weight: 900; font-size: 24px">目前出價最高者為: ${mebName}</span>
				</c:if>
			</div>
		</div>
		<!-- 下方區塊end -->

		<!-- 競拍規則區塊 -->
		<div class="laws" style="height: 200px; width: 800px; background-color: #FFFAF2; border: 2px solid #FFBA3B; border-radius:10px ; position:relative; left:360px; top:-80px; color:black; font-family: 微軟正黑體; font-weight: 900;">
			<p>
			&nbsp;&nbsp;拍賣規則:<br>
			&nbsp;&nbsp;1. 出價最低需以<span style="color:red;">1000</span>為單位，並且出價金額須高於目前競拍之最高金額<br>
			&nbsp;&nbsp;2. 拍賣時不公開底價。<br>
 			&nbsp;&nbsp;3. 競價金額最高且高於底價者之得標者為拍定人。<br>
 			&nbsp;&nbsp;4. 得標後即成立訂單。<br>
 			&nbsp;&nbsp;5. 拍定人須在三天內付款，如未付款則表示棄標。<br>
			&nbsp;&nbsp;6. 棄標則取消該會員永久競拍資格。<br>
			&nbsp;&nbsp;7. 競價金額未達底價即為流標。<br>
			</p>
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

	<script src="${pageContext.request.contextPath}/resources/js/jquery.countdown.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.countdown.min.js"></script>
		
	<!--   websocket -->
	<script type="text/javascript">
	let MyPoint = "/front_end/${account.meb_no}"; // 這個要與websocket的ServerEndpoint名字相符
	let host = window.location.host;
	let path = window.location.pathname;
	let webCtx = path.substring(0, path.indexOf('/', 1));
	let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	let webSocket;
	
	/* 開啟連線(網頁載入時)----------------------------------------------------------------------------- */
	function connect(){
		// 建立一個新的websocket，連到位於endPointURL的伺服器
		webSocket = new WebSocket(endPointURL);
		// 開啟連線
		webSocket.onopen = function(event) {
			console.log("開啟連線");
		}
		
		// 接收推送過來的訊息並顯示
		webSocket.onmessage = function(event){
			let spanprice = document.getElementById('currentprice'); // 要推送過來顯示的位置
			let jsonObj = JSON.parse(event.data); // 字串轉物件
			let priceonweb = parseInt((spanprice.innerText).trim()); // 當前頁面價錢(String -> int)
			let getprice = jsonObj.price; // 推送過來的價錢(int)
			let id = "${scarVO.scar_no}";
			if(jsonObj.id === id && getprice > priceonweb)
				spanprice.innerText = jsonObj.price;
		}
		
		// 我的關閉連線是直接離開網頁，所以不需做任何頁面資訊修改
		webSocket.onclose = function(event) {
		// console.log("關閉連線");
		}
		
		// 發送訊息到server
		webSocket.addEventListener('open', function (event) {		
				inputprice.focus();
				let id = "${scarVO.scar_no}";
				let price = ${scarVO.scar_maxprice};
				if (price === "" || price == "0"){
				}else{
					var jsonObj = {
							"id": id,
							"price": price
					};
					webSocket.send(JSON.stringify(jsonObj));
				}
			});
	}
	// 關閉連線(離開網頁時)--------
	function disconnect(){
		webSocket.close(); // 這時候觸發onclose
	}
	
	</script>
	
	<script>
		let em = '${errorMessages.bid_price}';
		if(em !== null){
			if(em === '出價不能為空'){
				swal(em,'請輸入價格','warning');
			}else if(em === '最高價是你了!'){
				swal(em,'請勿重複出價','warning');
			}else if(em === '出價須1000為單位且高於目前價格'){
				swal(em,'請重新出價','warning');
			}		
		}
	</script>
  
  	<script type="text/javascript">
	    $("#getting-started")
	    .countdown("${scarVO.scar_endtime}", function(event) {
	      $(this).text(
	        event.strftime('%D天%H:%M:%S')
	      );
	    });
  	</script>
  	
	<script>
		window.setInterval(function(){
			if((new Date().getTime())-(Date.parse('${scarVO.scar_endtime}')) > 0){
				swal('拍賣結束', '感謝參與本次拍賣', 'warning');
				setTimeout(function(){
					window.location.href = '/CGA102G4//scar/scarAuctionAll';
				},1500);
			}
		}, 1000);
	</script>
  	
</body>

</html>



