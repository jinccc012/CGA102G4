<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>

<% 
StoreService sc = new StoreService();
List<StoreVO> all = sc.getAll();
%>

<!doctype html>
<html lang="zh-tw">

<head>
  <title>Family Rent</title>
  <link rel="icon" href="<%= request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png" type="image/x-icon" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/bootstrap-datepicker.css">
  <!-- 自己的css -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/head.css">
  <!-- MAIN CSS -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/style.css">
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/jquery.datetimepicker.css">
    <!-- toastr -->
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Koulen&family=Teko:wght@300&display=swap');
  </style>
   <!-- toastr 預設值 -->
  <script>
    
</script>
  <script type="text/javascript">
   let MyPoint;
  <c:if test="${empty account.meb_no}">
 	  MyPoint ="/websocket/visitor."
	</c:if>
 	 <c:if test="${not empty account.meb_no}">
	  MyPoint ="/websocket/member.${account.meb_no}"
	</c:if>
</script>
<script src="<%=request.getContextPath() %>/front_end/index/WebSocket.js"></script>
</head>
<body onload="connect();" onunload="disconnect();">

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
                <a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" role="button" style="padding-bottom: 0px;">中古車</a>
              </li>
              <li><a href="${pageContext.request.contextPath}/event/eventShowList" class="nav-link menuStyle">活動資訊</a></li>
              <li><a href="${pageContext.request.contextPath}/front_end/member/ContactList.jsp" class="nav-link menuStyle" role="button">聯絡我們</a></li>
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


    <div class="hero" style="background-image: url('<%=request.getContextPath()%>/resources/img/hero_1_a.jpg');">

      <div class="container">
        <div class="row align-items-center justify-content-center">
          <div class="col-lg-8">
            <div class="row mb-5">
              <div class="col-lg-8 intro">
                <h1>租車</h1>
                <h2>更快，更方便，您的租車新選擇</h2>
              </div>
            </div>
			<jsp:useBean id = "dao" class="com.store.model.StoreService" />
            <div class="re-form" >
              <div class="row" style="align-items: center;flex-wrap: nowrap; ">
                <div style="width: 150px;">
                <p style="margin-bottom: 0px;">取車地點</p>
                  <select name="" id="pickStore" class="custom-select form-control">
                    <option value="">取車地點</option>
                 <c:forEach items="${dao.all}" var="store">
                 	<option value="${store.st_no}" class="${store.st_no}">${store.st_name}</option>
                 </c:forEach>
                  </select>
                </div>
                <div style="width: 150px;">
                <p style="margin-bottom: 0px;">還車地點</p>
                  <select name="" id="backStore" class="custom-select form-control">
                    <option value="">還車車地點</option>
                 <c:forEach items="<%=all%>" var="store">
                 	<option value="${store.st_no}" class="${store.st_no}">${store.st_name}</option>
                 </c:forEach>
                  </select>
                </div>
                <div style="width: 200px;">
                  <div class="form-control-wrap">
                  <p style="margin-bottom: 0px;">租車時間</p>
                    <input type="text" id="start" placeholder="租車時間" class="form-control datepicker px-3">
                    <span class="icon icon-date_range"></span>

                  </div>
                </div>
                <div style="width: 200px;">
                  <div class="form-control-wrap">
                  <p style="margin-bottom: 0px;">還車時間</p>
                    <input type="text" id="end" placeholder="還車時間" class="form-control datepicker px-3" required>
                    <span class="icon icon-date_range"></span>
                  </div>
                </div>
                <div style="width: 150px;">
                  <input type="submit" id="send" value="我要租車" class="btn btn-primary btn-block py-3" style="border-radius: 40px; margin-top: 20px;" disabled>
                </div>
              </div>

            </div>

          </div>
        </div>
      </div>
    </div>

  </div>
  <div class="site-section">
    <div class="container">
      <h2 class="section-heading"><strong>如何租車?</strong></h2>
      <p class="mb-5">讓你有更好租車體驗</p>

      <div class="row mb-5">
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="step">
            <span>1</span>
            <div class="step-inner">
              <span class="number text-primary">01.</span>
              <h3>選擇你想要的車輛</h3>
              <p>依照你喜好選擇車輛，時間，地點</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="step">
            <span>2</span>
            <div class="step-inner">
              <span class="number text-primary">02.</span>
              <h3>送出你的訂單</h3>
              <p>可以查看訂單詳情，費用計算</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="step">
            <span>3</span>
            <div class="step-inner">
              <span class="number text-primary">03.</span>
              <h3>付款</h3>
              <p>使用信用卡付款</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="site-section bg-light">
    <div class="container">
      <div class="row">
        <div class="col-lg-7">
          <h2 class="section-heading" style="font-size: 40px;font-weight:bold;">車輛</h2>
          <p class="mb-5" style="font-size: 15px;font-weight:bold;">選擇你要的車輛</p>
        </div>
      </div>

    <div class="row">
    
    
    
	
    <c:forEach items="${carList}" var="car">
    	<div class="col-md-6 col-lg-4 mb-4">
                <div class="listing d-block  align-items-stretch">
                  <div class="listing-img h-100 mr-4">
                    <img src="<%=request.getContextPath()%>/index?model_no=${car.model_no}" alt="Image" class="img-fluid" style="width: 500px; height:250px">
                  </div>
                  <div class="listing-contents h-100">
                    <h3>${car.model_name} ${car.model_no}</h3>
                    <div class="rent-price">
                      <strong>${car.model_price}</strong><span class="mx-1">/</span>日
                    </div>
                    <div class="d-block d-md-flex mb-3 border-bottom pb-3"
                      style="padding-top: 16px; justify-content: space-between;">
                      <div>
                        <span>行李:</span>
                        <span class="number">${car.model_baggage}</span>
                      </div>
                      <div>
                        <span>CC數:</span>
                        <span class="number">${car.model_cc}</span>
                      </div>
                      <div>
                        <span>人數:</span>
                        <span class="number">${car.model_seats}</span>
                      </div>
                    </div>
                    <div>
                      <p style="height: 150px;">
                      ${car.car_info}
                      </p>
                      <p><a href="front_end/rcar_order/makeOrder.jsp" class="btn btn-primary btn-sm">租車</a></p>
                    </div>
                  </div>
                </div>
              </div>
    </c:forEach>
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

  


  
  <script src="<%= request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
  <script src="<%= request.getContextPath()%>/resources/js/jquery.datetimepicker.full.min.js"></script>
 
  <script src="<%= request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
  <script>

    $('#start').datetimepicker({
      minDate: '-1970/01/01',//設定只能選取現在日期
      maxDate: '+1970/03/03',//設定只能選取三個月內日期
      format:'Y-m-d H:00'
    })
    $('#end').datetimepicker({
      minDate: new Date() ,//設定只能選取現在日期
      maxDate: '+1970/03/03',//設定只能選取三個月內日期
      format:'Y-m-d H:00'
    })
    $.datetimepicker.setLocale('zh-TW');// 設定中文

    

   $('#start').on('change', function(){
    nextDate =new Date($(this).val())
      nextDate.setDate(nextDate.getDate()+1);
      $('#end').datetimepicker({
      minDate: nextDate //設定只能選取現在日期
      })
    })
  
    let pickstore = false;
    let backstore = false;
    let Start = false;
    let End = false;

    $('#pickStore,#backStore,#start,#end').blur(function(){
    	console.log('ddd')
      if($(this).val().trim().length !==0){
        if($(this).attr('id') == 'pickStore'){
          pickstore = true;
        }else if($(this).attr('id') == 'backStore'){
          backstore = true;
        }else if($(this).attr('id') == 'start'){
          Start = true;
        }else if($(this).attr('id') == 'end'){
          End = true;
        }
      }

      if(pickstore && backstore && Start && End){
        $('#send').prop('disabled',false)
      }
    })


    $('#send').click(function(){
      let i = {
        pickStore:$('#pickStore').val(),
        backStore:$('#backStore').val(),
        start:$('#start').val(),
        end:$('#end').val()
      }

      sessionStorage.setItem('rent',JSON.stringify(i));
      
      window.location.href="front_end/rcar_order/makeOrder.jsp"
    })
    
   $('.TPEHO').remove();//移除總公司
  </script>
  
</body>

</html>



