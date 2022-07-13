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
	RcarOrderVO orderVO = (RcarOrderVO)request.getAttribute("orderVO");
	
 	CarLeveService carLevelSVC = new CarLeveService();
	List<CarLevelVO> carLevels = carLevelSVC.getAll();
	pageContext.setAttribute("carLevels",carLevels);
	
	CarModelService carModelSVC = new CarModelService();
	List<CarModelVO> carModels = carModelSVC.getAll();
	pageContext.setAttribute("carModels", carModels);
	
	StoreService storeSVC = new StoreService();
	List<StoreVO> stores = storeSVC.getAll();
	pageContext.setAttribute("stores", stores);
	
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/rcar_order/css/form_style.css">
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
			$.datetimepicker.setLocale('zh'); // kr ko ja en
			$(function(){
				 $('#order_car_time').datetimepicker({
				  format:'Y-m-d H:i',
				  onShow:function(){
				   this.setOptions({
				    maxDate:$('#order_return_car_time').val()?$('#order_return_car_time').val():false
				   })
				  },
				  timepicker:true,
				  value: null,
				 });
				 
				 $('#order_return_car_time').datetimepicker({
				  format:'Y-m-d H:i',
				  
				  onShow:function(){
					  this.setOptions({
				    	minDate:$('#order_car_time').val()?$('#order_car_time'):false
				   	})
				  },
				  timepicker:true,
				  value: null,
				 });
			});
		</script>
		 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
	        	if($("#hasCar").val() == 'false'){
	        		swal({title: "有車", icon: "success"});
	        	}else if($("#hasCar").val() == 'true'){
	        		swal({title: "此時段或車型已無庫存，請選擇其他時段或車型", icon: "error"});
	        	}
	        });
		</script>
</head>

<body>
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
							<li><a href="<%=request.getContextPath()%>/index" class="nav-link menuStyle">首頁</a></li>
							<li id="ch">
								<a href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">租車</a>
							</li>
							<li><a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">中古車</a>
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
								<a href="<%= request.getContextPath()%>/index">首頁</a> <span class="mx-2">/</span> <strong>租車</strong>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section bg-light" style="position: relative;">

		<div class="container" style="width: 1200px; height: 1000px;">
      <form action="${pageContext.request.contextPath}/RcarOrder/RcarOrderServlet" method="post">
        <div class="Order_Detail_div">
          <h1>請填寫訂單</h1>
			<div class="customer_info">
				<br>
				<div class="div_start_store left_side" style="margin-left:10px;">
					<label for="">起租站點:</label><br>
					<select name="start_store" id="pickStore" >
						<option value="unpicked" >請選擇</option>
						<c:forEach var="store" items="${stores}">
							<option value = "${store.st_no}" ${store.st_no.equals(orderVO.rcaro_pickuploc) ? 'selected':''}>${store.st_name}</option>
						</c:forEach>
					</select>
					<label style = "color:red; font-weight: bold">${requestScope.errorMessage.startStore}</label>
				</div>
				
				<div class="div_return_store right_side">
					<label for="">歸還站點:</label><br>
					<select name="return_store" id="backStore"  >
						<option value="unpicked">請選擇</option>
						<c:forEach var="store" items="${stores}">
							<option value = "${store.st_no}" ${store.st_no == orderVO.rcaro_returnloc? 'selected':''}>${store.st_name}</option>
						</c:forEach>
					</select>
					<label style = "color:red; font-weight: bold">${requestScope.errorMessage.returnStore}</label>
				</div>
				
				<div class="corss_line">
					
				</div>
				
				<div class="div_order_car_time left_side">
					<label for="">取車時間:</label><br>
					<input type="text" id = "order_car_time" name="order_car_time" value = "${param.order_car_time }">
					<label style = "color:red; font-weight: bold">${requestScope.errorMessage.order_car_time}</label>
				</div>
				
				<div class="div_order_return_time right_side">
					<label for="">還車時間:</label><br>
					<input type="text" id="order_return_car_time" name="order_return_car_time" value = "${param.order_return_car_time }"> 
					<label style = "color:red; font-weight: bold">${requestScope.errorMessage.order_return_car_time}</label>
				</div>
				
				
				<div class="div_car_level left_side">
					<label for="">車種</label><br>
					<select name="car_level" id="">
					<option value="unpicked">請選擇</option>
						
						<c:forEach var="level" items="${carLevels}">
						<option value="${level.getLevel_no()}" ${param.car_level.equals(level.level_no)? 'selected':''}>${level.level_name}</option>
						</c:forEach>
						
					</select>
					<label style = "color:red; font-weight: bold">${requestScope.errorMessage.carLevel}</label>
				</div>
				
				<div class="div_car_model right_side">
					<label for="">車型</label><br>
					<select name="car_model" id="">
					<option value="unpicked">請選擇</option>
					<c:forEach var="model" items="${carModels}">
						<option value="${model.model_no}" ${model.model_no.equals(orderVO.model_no)? 'selected' : ''}>${model.model_no}</option>
						</c:forEach>
					</select>
					<label style = "color:red; font-weight: bold">${requestScope.errorMessage.carModel}</label>
				</div>
				
				<div class="div_lessee_name left_side">
					<label for="">駕駛人姓名</label><br>
					<input type="text" name="lessee_name" value = ${orderVO.lessee_name == null? '':orderVO.lessee_name}> 
					<label style = "color:red; font-weight: bold">${requestScope.errorMessage.lesseeName}</label>
				</div>
			</div>
			
			<div class="order_buttons">
				<!-- 按鈕 -->
				<input type = "submit" id="form_submit"> 
				
				<input type="hidden" value="${requestScope.errorMsgs.no_car_to_rent}" id="hasCar">
				
<%-- 				<input type = "hidden" name = "mebno" value = "${sessionScope.mebno}" }> --%>
				<input type = "hidden" name = "mebno" value = "${account.meb_no}">
				<input type = "hidden" name = "action" value = "try_make_an_order">
			</div>
      </div>	
      
        
      </form>
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
  	
  	<script>
  	 if(JSON.parse(sessionStorage.getItem('rent'))!== null){
  	      let test = JSON.parse(sessionStorage.getItem('rent'));

  	      $('#pickStore').val(test.pickStore),
  	      $('#backStore').val(test.backStore),
  	      $('#order_car_time').val(test.start),
  	      $('#order_return_car_time').val(test.end)
  	      
  	      sessionStorage.removeItem('rent');
  	    }
  	</script>
</body>

</html>



