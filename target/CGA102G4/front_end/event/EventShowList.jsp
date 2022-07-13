<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>
<% 
HttpSession ss = request.getSession();
MemberVO member = (MemberVO)ss.getAttribute("account");
%>

<!doctype html>
<html lang="zh-tw">

<head>
  <title>Family Rent</title>
  <link rel="icon" href="<%= request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png" type="image/x-icon" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">

  <!-- 自己的css -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/head.css">
  <!-- MAIN CSS -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/style.css">
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/bootstrap.min.css">

  <%-- js --%>
  <script src="<%= request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
  <script src="<%= request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Koulen&family=Teko:wght@300&display=swap');
  </style>

</head>

<body>


  <div class="site-wrap" id="home-section">
    <header class="site-navbar site-navbar-target" id="head" role="banner">
      <div class="container">
        <div class=" align-items-center logo">
          <div class="site-logo">
            <a href="<%= request.getContextPath()%>/index" class="nav-link">
              <h1 style="font-family: 'Koulen', cursive;font-size: 50px;">Family Rent</h1>
            </a>
          </div>


          <nav class="site-navigation text-right ml-auto d-none d-lg-block" role="navigation">
            <ul class="site-menu main-menu js-clone-nav ml-auto ">
              <li><a href="<%= request.getContextPath()%>/index" class="nav-link menuStyle">首頁</a></li>
              <li id="ch">
                <a href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">租車</a>
              </li>
              <li>
               <a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" id="dropdownMenuLink" role="button" 
                  style="padding-bottom: 0px;">中古車</a>
              </li>
              <li><a href="${pageContext.request.contextPath}/event/eventShowList" class="nav-link menuStyle">活動資訊</a></li>
              <li><a href="${pageContext.request.contextPath}/front_end/member/ContactList.jsp" class="nav-link menuStyle" role="button">聯絡我們</a></li>
             <c:if test="${account == null}">
              	<li><a href="login.html" class="nav-link menuStyle">登入</a></li>
              </c:if>
              <c:if test="${account != null}">
              	  <li>
                  <%-- <a href="#" class="nav-link menuStyle"></a> --%>
                  <a href="#" style="padding-bottom: 0px;" class="nav-link menuStyle"  id="dropdownMenuLink" role="button" data-bs-toggle="dropdown"><%=member.getMeb_name()%></a>
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
    <div class="hero inner-page" style="background-image: url('${pageContext.request.contextPath}/resources/img/hero_1_a.jpg'); ">
      <div class="container">
        <div class="row align-items-end ">
          <div class="col-lg-5">

            <div class="intro">
              <h1><strong>租車</strong></h1>
              <div class="custom-breadcrumbs">
              <a href="<%= request.getContextPath()%>/index">首頁</a> <span class="mx-2">/</span> <strong>活動資訊</strong>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="site-section bg-light"> 
    
 <div class="container">
			<div class="row">
				<h1 style="text-align: center;">活動列表</h1>
		
		
			  <c:forEach items="${requestScope.events}" var="event">
			  
				<div class="col-md-4 eventlist" id="${event.event_no}">
					<div class="card mb-4 box-shadow" style="overflow: hidden;" style="width:320px;height:320px;">
						<img class="card-img-top" src="${pageContext.request.contextPath}/images?event_no=${event.event_no}" alt="Card image cap" style="width: 100%; height: 180px;">
						<div class="card-body">
							<div
								style="font-size: 20px;">
								<span class="text-muted" style="background-color: orange;">日租車</span>
								<span><strong>${event.event_start}~${event.event_end}</strong></span>
							</div>
							<h5 class="card-title">${event.event_title}</h5>
						</div>
					</div>
				</div>

   				 </c:forEach>
<!-- 				<div class="col-md-4 eventlist" id="event2"> -->
<!-- 					<div class="card mb-4 box-shadow" style="overflow: hidden;" -->
<!-- 						style="width:320px;height:320px;"> -->
<!-- 						<img class="card-img-top" -->
<!-- 							src="https://wd.car-plus.com.tw/ImgAdmin/News/mobile/20220505111646.jpg" -->
<!-- 							alt="Card image cap" style="width: 100%; height: 180px;"> -->
<!-- 						style="display:flex; align-items: center;justify-content: space-between;" -->
<!-- 						<div class="card-body"> -->
<!-- 							<div style="font-size: 20px;"> -->
<!-- 								<span class="text-muted" style="background-color: orange;">日租車</span> -->
<!-- 								<span><strong>2021-07-01~2022-08-01</strong></span> -->
<!-- 							</div> -->
<!-- 							<h5 class="card-title">服務升級不加價！機場←→租車～免費接駁！</h5> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
		</div>

		<script>
			$('.eventlist').click(function(event) {
				//alert($(event.target).attr('id')); //alerts directFilter 
				//alert(this.id)
				//傳送url+id 到頁面詳情 
				location.href = "${pageContext.request.contextPath}/event/eventShow?event_no=" + this.id;

			});
		</script>
    
  </div>


  <footer class="site-footer">
    <div class="container">
      <div class="" style="display:flex; justify-content: center;">

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
              Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart text-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank" >Colorlib</a>
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
<!--               Family Rent &copy; -->
<!--               <script>document.write(new Date().getFullYear());</script> -->
            </p>
          </div>
        </div>

      </div>
    </div>
  </footer> 

  



  
</body>

</html>



