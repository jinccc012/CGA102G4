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
                <a href="" class="nav-link menuStyle" id="dropdownMenuLink" role="button" data-bs-toggle="dropdown"
                  style="padding-bottom: 0px;">租車</a>
                <ul class="dropdown-menu " aria-labelledby="dropdownMenuLink">
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/front_end/rcar_order/makeOrder.jsp">立即預約</a></li>
                  <li><a class="dropdown-item" href="#">預約賞車</a></li>
                </ul>
              </li>
              <li>
                <a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" id="dropdownMenuLink" role="button" 
                  style="padding-bottom: 0px;">中古車</a>
<!--                 <ul class="dropdown-menu " aria-labelledby="dropdownMenuLink"> -->
<!--                   <li><a class="dropdown-item" href="#">拍賣車輛</a></li> -->
<!--                   <li><a class="dropdown-item" href="#">預約賞車</a></li> -->
<!--                 </ul> -->
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
              <div class="custom-breadcrumbs"><a href="index.html">首頁</a> <span class="mx-2">/</span>
                <strong>租車</strong></div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="site-section bg-light"> 
    
   <div class="container-fluid" style="width: 80%; height: 60%;">
			<div class="row justify-content-center text-center">
				<div class="col-12   ">
					<h1 class="mb-3" style="text-align: center;">${requestScope.eventObject.event_title}</h1>
					<div class="row   justify-content-center align-items-center">
						<div class="col-6">
							<img class="card-img-top"
								src="${pageContext.request.contextPath}/images?event_no=${requestScope.eventObject.event_no}"
								alt="Card image cap" style="width: 500px; height: 300px;">
						</div>
						<div class="col-6">
							<ul 
								style="font-size: 24px; width: 100%; list-style: none;text-align:left;">
								<strong>
									<li>一、優惠期間：${requestScope.eventObject.event_start} ~
										${requestScope.eventObject.event_end}，以用車時間為準。</li>
									<li>二、預約方式：於活動期間內，線上預約，即享優惠方案價。</li>
									 <c:choose>
										<c:when test="${requestScope.eventObject.model_no eq '全部'}">
									<li>三、優惠內容：本活動指定車型為全部，折扣${fn:substring(requestScope.eventObject.event_discount,1,-1)}。</li>
										</c:when>
										<c:otherwise>
									<li>三、優惠內容：本活動指定車型為${requestScope.eventObject.model_no}，折扣${fn:substring(requestScope.eventObject.event_discount,1,-1)}。</li>
										</c:otherwise>
									</c:choose>
								</strong>
							</ul>
						</div>
					</div>
					<br>
					<br>
					<br>
					<br>
					<p class="text-break" style="font-size: 25px;">${requestScope.eventObject.event_content}</p>
					<br> <br> <a href="${pageContext.request.contextPath}/index"
						style="display: block; margin: 0 auto; width: 50%;"
						class="btn btn-primary btn-lg">預約租車</a> <br>
						<br>
					<br>
					<br>
		
					<p
						style="color: aliceblue; background-color: gray; opacity: 0.8; font-size: 20px;text-align:left;">
						注意事項：本活動指定車型為
						<c:choose>
							<c:when test="${requestScope.eventObject.model_no eq 'All'}">全部</c:when>
							<c:otherwise>${requestScope.eventObject.model_no}</c:otherwise>
						</c:choose>
						，依門市實車為主。上述所示優惠價適用於線上訂車。 <br>承租人需年滿20歲，且持有中華民國身分證正本及中華民國有效汽車駕照，並使用信用卡支付款項。<br>
						24歲(含)以下且持有學生證之學生，若須付現金，須出示學生證。若證件不符或有出車安全之考量，本公司得保留出車與否之權利。<br>
						本活動內容依公司公告為準，本公司保留一切修改活動的權利，毋須事前通知，並有權對本活動所有事宜作出解釋或裁決。 <br>如本活動因不可抗力之因素無法執行時，本公司有權決定取消、終止、修改或暫停本活動。
					</p>
				</div>
			</div>
		</div>
    
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



