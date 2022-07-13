<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>
<%
HttpSession ss = request.getSession();
MemberVO member = (MemberVO) ss.getAttribute("account");
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
<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/memberstyle.css">
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
<link
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'
	rel='stylesheet'></link>
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
							<li id="ch"><a href="" class="nav-link menuStyle"
								id="dropdownMenuLink1" role="button" data-bs-toggle="dropdown"
								style="padding-bottom: 0px;">租車</a>
								<ul class="dropdown-menu " aria-labelledby="dropdownMenuLink1">
									<li><a class="dropdown-item" href="#">車款介紹</a></li>
									<li><a class="dropdown-item" href="#">預約賞車</a></li>
								</ul></li>
							<li><a href="<%=request.getContextPath()%>/scar/scarAuctionAll" class="nav-link menuStyle" id="dropdownMenuLink" role="button" 
                  style="padding-bottom: 0px;">中古車</a>
								</li>
							<li><a href="${pageContext.request.contextPath}/event/eventShowList" class="nav-link menuStyle">活動資訊</a></li>
							<li><a href="${pageContext.request.contextPath}/front_end/member/ContactList.jsp" class="nav-link menuStyle" role="button">聯絡我們</a></li>
							<c:if test="${account == null}">
								<li><a href="login.html" class="nav-link menuStyle">登入</a></li>
							</c:if>
							<c:if test="${account != null}">
								<li>
									<%-- <a href="#" class="nav-link menuStyle"></a> --%> <a
									href="#" style="padding-bottom: 0px;"
									class="nav-link menuStyle" id="dropdownMenuLink3" role="button"
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
			style="background-image: url('resources/img/hero_1_a.jpg');">
			<div class="container">
				<div class="row align-items-end ">
					<div class="col-lg-5">

						<div class="intro">
							<h1>
								<strong>會員中心</strong>
							</h1>
							<div class="custom-breadcrumbs">
								<a href="<%= request.getContextPath()%>/index">首頁</a> <span class="mx-2">/</span> <strong>會員資料</strong>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section bg-light" style="padding-top: 0px;">
		<div class="container" style="height: 600px;">
			<div class="row" style="font-size: 20px">
				<div
					style="display: flex; align-items: center; justify-content: space-between;">
					<div></div>
					<h1 class="mb-3">會員資料修改</h1>
					<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
						<li class="nav-item" role="presentation">
							<button class="nav-link " id="editButton" data-bs-toggle="pill"
								data-bs-target="#mainform" type="button" role="tab"
								aria-controls="mainform" aria-selected="true">

								<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
									fill="currentColor" class="bi bi-pencil-fill"
									viewBox="0 0 16 16">
                                    <path
										d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z" />
                                </svg>
							</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="changepass" data-bs-toggle="pill"
								data-bs-target="#passform" type="button" role="tab"
								aria-controls="passform" aria-selected="false">修改密碼</button>
						</li>
					</ul>	 
				</div>


				<div class="tab-content" id="pills-tabContent">
					<div id="mainform" class="tab-pane fade show active"
						role="tabpanel" aria-labelledby="pills-home-tab">
						<form id="memform" action="updateMember" method="post"
							enctype="multipart/form-data" onsubmit="return check();">
							<div class="row">
								<div class="col-lg-2"></div>
								<div class="col-lg-6" id="MEBData">

									<div class="row">
										<div class="col-lg-6">
											<label for="username">會員編號</label>
											<div class="input-group">
												<input type="text" class="form-control" id="meb_no"
													name="meb_no" placeholder="" value=${mem.meb_no } readonly
													required>
											</div>
										</div>
										<div class="col-lg-6">
											<label for="username">會員姓名</label>
											<div class="input-group">
												<input type="text" class="form-control memb" id="meb_name"
													name="meb_name" placeholder=""
													value=${mem.meb_name
													} readonly required>
											</div>
											<div class="invalid-feedback" style="color: red"
												id="meb_nameMsgs"></div>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-lg-6">
											<label for="username">性別</label> <br>
											<div class="d-flex justify-content-around">
												<label> <input type="radio" name="meb_gender"
													value="0"
													<c:if test="${mem.meb_gender eq '0'}">checked</c:if>
													disabled> 女
												</label> <label> <input type="radio" name="meb_gender"
													value="1" disabled
													<c:if test="${mem.meb_gender eq '1'}">checked</c:if>>
													男
												</label> <label><input type="radio" name="meb_gender"
													value="2"
													<c:if test="${mem.meb_gender eq '2'}">checked</c:if>
													disabled> 其他</label>
											</div>
										</div>

										<div class="col-lg-6">
											<label for="username">生日</label>
											<div class="input-group">
												<input type="date" class="form-control memb" id="meb_bir"
													name="meb_bir" placeholder="" value=${mem.meb_bir }
													required readonly>
											</div>
											<div class="invalid-feedback" style="color: red"
												id="meb_birMsgs"></div>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-lg-6">
											<label for="phone">電話</label>
											<div class="input-group">
												<input type="number" class="form-control memb" id="meb_tel"
													name="meb_tel" value=${mem.meb_tel } readonly>
											</div>
											<div class="invalid-feedback" style="color: red"
												id="meb_telMsgs"></div>
										</div>

										<div class="col-lg-6">
											<label for="email">電子郵件</label>
											<div class="input-group">
												<input type="email" class="form-control memb" id="meb_mail"
													name="meb_mail" value=${mem.meb_mail } readonly>
											</div>
											<div class="invalid-feedback" style="color: red"
												id="meb_mailMsgs"></div>
										</div>
									</div>
									<br>
									<div class="mb-3">
										<label for="address">地址</label>
										<div class="input-group">
											<input type="text" class="form-control memb" id="meb_adrs"
												name="meb_adrs" value=${mem.meb_adrs } readonly required>
										</div>
										<div class="invalid-feedback" style="color: red"
											id="meb_adrsMsgs"></div>
									</div>


									<div class="mb-6" id="button"></div>
								</div>

								<div class=" col-lg-3">

									<div>
										<div id="photo">
											<div class="mb-3"
												style="width: 300px; height: 400px; overflow: hidden;">
												<div>
													<!-- 如果meb_profile == null 顯示 /img/hero_1_a.jpg 如果不是 顯示真正照片路徑 -->
													<img id="frame"
														src=<c:choose>
												      <c:when test="${empty mem.meb_profile}">resources/icon/person-fill.svg</c:when>
												      <c:otherwise>memberImage?meb_no=${mem.meb_no}</c:otherwise>
											      </c:choose>
														alt="icon/person-fill.svg" class="img-fluid"
														style="width: '200px'; height: 250px; border-radius: 30px;" />

													<label class="btn btn-info" id="labelimg" hidden
														style="margin-top: 10px; color: #fff;"> <input
														type="file" id="meb_profile" name="meb_profile"
														style="display: none;" onchange="preview();return false;">
														<i class="fa fa-photo"></i> 上傳個人照
													</label>

												</div>

											</div>
										</div>
										<div></div>
									</div>
								</div>
						</form>
					</div>

				</div>
				<div id="passform" class="tab-pane fade" role="tabpanel"
					aria-labelledby="pills-profile-tab">
					<div class="container">
						<div class="row">
							<div class="col-lg-2 h-2"></div>
							<div class="col-lg-6 h-2">
								<div id="msg" class="error" style="color: red"></div>
								<span style="color: red;" id="sucessmsg"> </span>
								<form id="changePform">
									<input type="hidden" value="${mem.meb_no}" name="meb_no">

									<div class="row">
										<div class="col-lg-6">

											<label for="password" class="form-label">舊密碼</label> <input
												class="form-control " type="password" name="oPassword"
												id="oPassword" />
											<div class="invalid-feedback oPassword" id="oPasswordmsg">
											</div>
										</div>
										<div class="col-lg-6"></div>
										</div>

										<div class="row">
											<div class="col-lg-6">


												<label for="npassword" class="form-label ">新密碼 </label> <input
													type="password" class="form-control nPassword"
													name="nPassword" id="nPassword" />
												<div class="invalid-feedback nPassword" id="nPasswordmsg">
												</div>
											</div>
												
													<div class="col-lg-6"></div>
											
										</div>
												<div class="row">
													<div class="col-lg-6">
														<label for="confirmPassword" class="form-label">確認新密碼
														</label> <input type="password" name="confirmPassword"
															class="form-control" id="confirmPassword" />
														<div class="invalid-feedback confirmPassword"
															id="confirmPasswordmsg"></div>
													</div>

													<div class="col-lg-6"></div>
												</div>
													<br>
													<button id="changepassbtn" type="button"
													class="btn btn-primary">送出</button>
											</div>
										
										</div>
											
								</form>
							</div>


							<div class="col-lg-4 h-2"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
            const button = document.getElementById("button");
            const form = document.getElementById("form");
            function clearImage() {
                document.getElementById('formFile').value = null;
                frame.src = "";
            }
            function preview() {
                frame.src = URL.createObjectURL(event.target.files[0]);
            }
          
            function showEdit() {
                let gn = document.querySelectorAll('[name="meb_gender"]');
                gn.forEach(element => element.removeAttribute("disabled"));
                let input = document.querySelectorAll('input');
                input.forEach(element => element.removeAttribute("readonly"));
                //show img button
                 $("#labelimg").attr("hidden",false);
                //meb_no disable again
                document.getElementById("meb_no").setAttribute("readonly", true);
                //再次點擊只有一個button
                if (document.getElementById("submitEdit") == null) {
                    const submit = document.createElement("button");
                    submit.setAttribute("class", "btn btn-primary btn-lg")
                    submit.setAttribute("style", "width:100%;")
                    submit.setAttribute("type", "button")
                    submit.innerText = "送出"
                    submit.setAttribute("id", "submitEdit");
                    button.appendChild(submit);
                }
                $("#submitEdit").attr("hidden",false);
                
//                 if (document.getElementById("changepass") == null) {
//                     pic = document.getElementById("pic");
//                     const a = document.createElement("button");
//                     pic.insertBefore(a, editButton);

//                 }
                return false;
            };
            
                
                
            function hideEdit() {
                	  $('[name="meb_gender"]').attr('disabled','disabled')
                	  $('.memb').attr('readonly',true);
                      //show img button
                      $('#labelimg').attr("hidden",true);
               
                      //meb_no disable again
                       $('#meb_no').attr("readonly", true);
                      //再次點擊只有一個button
               			$('#submitEdit').attr("hidden",true);

            };
                
                
            $(document).on('click',"#editButton",function (e) {
            		if($(this).hasClass("opended")){
            			
            			hideEdit();
            			$(this).removeClass("opended")
            			
            		}else{
            			$(this).addClass("opended")
            			showEdit();
            		}
            });
                
                
                

            $(document).on('click',"#submitEdit",function (e) {
                    let form = document.getElementById("updateMember"); //form表單id
                    let Dataform = new FormData(memform); //將form 表單轉成 FormData
                    $('.err').empty();//錯誤訊息清空
                    $('.is-invalid').removeClass('is-invalid');//錯誤提示框清空
                    $.ajax({
                        type: "POST",
                        url: "/CGA102G4/memberedit",
                        data: Dataform,//傳送表單
                        contentType: false,//must, tell jQuery not to process the data
                        processData: false,
                        success: function (data) {
                        	data = JSON.parse(data);//將回傳的資料轉成javascript型態
                        $.each(data, function(Key, val) {//回傳data foreach
                        	//失敗訊息
                            $('#'+Key).empty();
    						$('#'+Key).append(val);
    						$('#'+Key).prev().children("input:first-child").addClass('is-invalid');
    						$('#'+Key).attr( 'class' ,' err');
    						if(Key ==='sucess'){//如果成功
    							 swal({ title: "編輯成功", icon: "success" })
    				              .then((ok) => {
    				               
    				             	 window.location.reload();  
    				              						})
    											}
                           								});
                        							}
                    		});
               										 });
            
            const msg = document.querySelector('#msg');
            $('#changepassbtn').click(function () {

                $('.is-invalid').removeClass('is-invalid');
                $('#oPasswordmsg').empty();
                $('#nPasswordmsg').empty();
                $('#confirmPasswordmsg').empty();
                let passFrom = document.getElementById("changePform");
                let pform = new FormData(passFrom);

                $.ajax({
                    type: "POST",
                    url: "/CGA102G4/memberpassedit",
                    data: pform,
                    contentType: false,//must, tell jQuery not to process the data
                    processData: false,
                    success: function (data) {
                    console.log(data) 
                        //JSON.stringify() : object to JSon
                    data = JSON.parse(data); //form json to javascriptelement      
                		$.each(data, function(Key, val) {
                		//	console.log(Key)
                		$('#'+Key).addClass('is-invalid');
						$('.'+Key).append(val);		
						if(Key=== "sucessmsg"){
							   
				               swal({ title: "修改密碼成功", icon: "success" })
				               .then((xxx) => {
				               location.reload();
				               })
						}
						});
                    }

                });
            });


        </script>









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
							<script>document.write(new Date().getFullYear());</script>
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






</body>

</html>



