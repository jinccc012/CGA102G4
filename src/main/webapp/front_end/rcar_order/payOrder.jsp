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
<%@ page import="com.carlevel.model.CarLeveService"%>
<%@ page import="com.carlevel.model.CarLevelVO"%>
<%@ page import="com.carmodel.model.CarModelService"%>
<%@ page import="com.carmodel.model.CarModelVO"%>

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
<link href="https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/rcar_order/css/payOrder.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<%-- js --%>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%-- 自己的js --%>
<script src="<%=request.getContextPath()%>/front_end/rcar_order/js/PointValidator.js"></script>

<style>
	@import
	url('https://fonts.googleapis.com/css2?family=Koulen&family=Teko:wght@300&display=swap');
	.xdsoft_datetimepicker .xdsoft_datepicker {
	           width:  300px;   /* width:  300px; */
	}
	.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	           height: 151px;   /* height:  151px; */
	}
</style>
<link   rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css" />
		<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.full.js"></script>
		<script>
		let count = 1234;
		let pay = count;
		$(document).ready(function() {
			let count = $("#originHiddenPay").val();
			$("#usePoint").change(function() {
				pay = count - this.value;
				$("#payDiv").text(pay);
				console.log("Pay is " + pay);
			});
		});
	
		$(document).ready(function() {
			if ($("#hasCar").val() == 'false') {
				// 	        		swal({title: "有車", icon: "success"});
			}
		});
	</script>
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
										<li><a class="dropdown-item" href="#">會員資料</a></li>
										<li><a class="dropdown-item" href="${pageContext.request.contextPath}/order/memberOrdersDesc">查看訂單</a></li>
										<li><a class="dropdown-item" href="#">中古車拍賣</a></li>
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

		<div class="container"
			style="width: 1200px; height: 1000px; background-color: #f8f9fa !important; font-size: 20px;">
			<div class="order_container">
				
				<div class="order_info">
					<div class="order_inner">
						<label>車種:</label> ${orderVO.level_no} <br>
<!-- 						orderVO.car_level -->
					</div>

					<div class="order_inner order_inner_1">
						<label>起租站點:</label> ${orderVO.rcaro_pickuploc}<br>
<!-- 						orderVO.start_store -->
					</div>

					<div class="order_inner order_inner_1">
						<label>歸還站點:</label> ${orderVO.rcaro_returnloc}<br>
					</div>

					<div class="order_inner order_inner_1">
						<label>取車時間:</label> ${orderVO.rcaro_ppicktime}<br>
					</div>

					<div class="order_inner order_inner_1">
						<label>還車時間:</label> ${orderVO.rcaro_pprettime}<br>
					</div>
					
					<div class="order_inner order_inner_1">
						<label>駕駛人姓名:</label> ${orderVO.lessee_name}<br>
					</div>

				</div>
				
				<div class="car_model" >
					<div>
						<label for="">車型:</label> ${param.car_model} <br>
						<img src="${pageContext.request.contextPath}/CarModel/CarModelPictureServlet?car_model=${orderVO.model_no}"
						style="width: 100%;">
					</div>
				</div>
				
			</div>
			
<!-- 			<div class="div_payment_info" style="border: 1px solid pink;"> -->
				<form
					action="${pageContext.request.contextPath}/RcarOrder/RcarOrderServlet"
					method="POST">
					<div class="div_payment_info" >
					<div class="credit_card">
						<label for="">信用卡卡號</label><br> <input type="text"
							name="card_no" value="${param.card_no}">
						<p style="color: red; font-weight: bold">${requestScope.errorMsgs.card_no}</p>
	
	
						<label for="">卡號到期日</label><br> <input type="text"
							placeholder="MM/YY" name="card_date" value="${param.card_date}">
						<p style="color: red; font-weight: bold">${requestScope.errorMsgs.card_date}</p>
	
						<label for="">卡片後三碼</label><br> <input type="text"
							name="card_validate" value="${param.card_validate}">
						<p style="color: red; font-weight: bold">${requestScope.errorMsgs.card_validate}</p>
					</div>
					
					<div class="money_div">
						
						<div class="event_div">
							<label>活動優惠</label><br>
							<div style="color: green; font-weight: bold">${eventTitle}</div>
						</div>
						
						
						
						<input type="hidden" value="${sessionScope.orderVO.rcaro_pay}">
	
	
						<%
						RcarOrderVO orderVO = (RcarOrderVO) session.getAttribute("orderVO");
						OrderPointService pointSVC = new OrderPointService(orderVO);
						pageContext.setAttribute("memberPoint", pointSVC.getMemberPoint());
						%>
						
						<div class="aviliable_point">
							<label>可用會員點數</label>
							<div>${memberPoint}</div>
						</div>
						
						<div class="consume_point">
							<label>選擇套用點數</label> <input type="hidden"
								value="${sessionScope.orderVO.rcaro_pay}" id="originHiddenPay">
							<input type="number" min="0" max="${memberPoint}" name="consumePoint" value="0" id="usePoint">
						</div>
						
						<div class="pay">
							
							<div class="original_pay">
								<br> <label for="">應付金額</label><br>
								<del>${requestScope.originalPay}</del>						
							</div>
						
						
							<div class="final_pay">
								<div style="font-size: 40px; color: blue;line-height: 93px;" id="payDiv">
									<input type="hidden" value="${sessionScope.orderVO.rcaro_pay}"
										id="originHiddenPay"> 
										${sessionScope.orderVO.rcaro_pay}
								</div>
								<div style="font-size: 40px; color: blue;line-height: 93px;" id="payDiv">元</div>						
							</div>
						
						</div>
						
						
						
					</div>
					
					 <input
						type="hidden" value="${errorMessage.no_car_to_rent}" id="hasCar">

					

					<input type="hidden" name="action" value="pay_order"> 
					<input type="hidden" name="orderVO" value="${orderVO}"> 
					
					</div>
					<input type="submit" id="form_submit">
				</form>
				
<!-- 			</div> -->
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
	<script src="js/bootstrap-datepicker.min.js"></script>
		
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



