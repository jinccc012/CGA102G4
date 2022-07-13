<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <link rel="icon" href="<%= request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png"
    type="image/x-icon" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">

  <!-- 自己的css -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/head.css">
  <!-- MAIN CSS -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/style.css">
  <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/bootstrap.min.css">

  <%-- js --%>
  	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
    <div class="hero inner-page" style="background-image: url('<%= request.getContextPath()%>/resources/img/hero_1_a.jpg'); ">
      <div class="container">
        <div class="row align-items-end ">
          <div class="col-lg-5">

            <div class="intro">
              <h1><strong>租車</strong></h1>
              <a href="<%= request.getContextPath()%>/index">首頁</a> <span class="mx-2">/</span>
                <strong>聯絡我們</strong>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="site-section bg-light">

    <div class="container">
      <div class="row justify-content-center text-center">
      <div class="col-7 text-center mb-5">
        <h2>聯絡表單</h2>
        <p>請填寫下列資料，我們將盡快與您聯絡</p>
      </div>
    </div>
      <div class="row">
        <div class="col-lg-8 mb-5" >
          <form   id="test">
            <div class="form-group row">
              <div class="col-md-6 mb-4 mb-lg-0">
              <p>姓名*</p>
              <input type="text" class="form-control" name="issue_name" placeholder="請填寫全名">
              </div>
              <div class="col-md-6">
              <p>連絡電話*</p>
                <input type="text" class="form-control" name="issue_tel" placeholder="請填寫連絡電話">
              </div>
            </div>

            <div class="form-group row">
              <div class="col-md-12" style="margin-top: 20px;">
              <p>電子信箱*</p>
              <input type="text" class="form-control" name="issue_mail" placeholder="請填寫電子信箱">
              </div>
            </div>

            <div class="form-group row">
              <div class="col-md-12" style="margin-top: 20px;">
               <p>問題描述*</p>
                <textarea name="issue_content"  class="form-control"  placeholder="請留下您的寶貴意見!" cols="30" rows="10"  ></textarea>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-md-6 mr-auto">
                <input type="submit" id="send" class="btn btn-block btn-primary text-white py-3 px-5" value="確認送出"  style="margin-top: 50px;margin-left: 50%;width: 100%;">
              </div>
            </div>
          </form>
        </div>
        <div class="col-lg-4 ml-auto" style="margin-top: 43px;" >
          <div class="bg-white p-3 p-md-5">
            <h3 class="text-black mb-4">聯絡方式</h3>
            <ul class="list-unstyled footer-link">
              <li class="d-block mb-3">
                <span class="d-block text-black">總公司地址:</span>
                <span>台北市信義區信義路888號</span></li>
              <li class="d-block mb-3"><span class="d-block text-black">聯絡專線:</span><span>0800-234-888</span></li>
              <li class="d-block mb-3"><span class="d-block text-black">Email:</span><span>family-rent@gmail.com</span></li>
            </ul>
          </div>
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
                <li><a href="ContactList.jsp">顧客意見</a></li>
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
              <script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made
              with <i class="icon-heart text-danger" aria-hidden="true"></i> by <a href="https://colorlib.com"
                target="_blank">Colorlib</a>
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
              <!--               Family Rent &copy; -->
              <!--               <script>document.write(new Date().getFullYear());</script> -->
            </p>
          </div>
        </div>

      </div>
    </div>
  </footer>

<script >

$("#send").click(function (e) {
	  e.preventDefault();
	
	    if($('.ok').length == 4){
	    	//ajax傳到後端
		    let from = $("#test").serialize();
		    console.log($("#test").serialize());
		    $.ajax({
		      type: "post",
		      url: "../../IssueController",
		      data: from,
		      success: function (resp) {
		        if (resp === "true") {
		          swal({
		            title: "感謝您的寶貴意見，稍後會有專人跟您聯絡!",
		            icon: "success",
		          });
		        }
		      }
		    });
	    }else {
	    swal({ title: "請確認輸入資料是否完整!", icon: "error" });
	  }
	});

$('.form-control').blur(function (){
	const val = $(this).val();
	if($.trim(val).length !== 0){
		$(this).attr('class', 'form-control ok')
	}else {
		$(this).val(null).attr({
			'placeholder': '欄位不可為空',
			'class': 'form-control err'
		})
	}
})
</script>
</body>

</html>