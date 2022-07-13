<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=request.getContextPath() %>/resources/icon/pngkey.com-gps-icon-png-5131691.png" type="image/x-icon" />
    <title>Family Rent || 後台管理系統</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/1d9dcf12aa.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css"> <!-- 測試用路徑 進專案要改 -->
    <script src="<%=request.getContextPath() %>/resources/js/bootstrap.bundle.js"></script> <!-- 測試用路徑 進專案要改 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/Background_Home.css"> <!-- 首頁CSS 測試用路徑 進專案要改 -->

</head>

<body>
    <jsp:useBean id = "dao" class="com.store.model.StoreService" />
    <!--     上面header欄位 -->
    <nav class="header">
        <h1>Family Rent 後台管理系統</h1>
        <ul class="store nav " id="emp">
            <label for=""></label>
            <c:forEach items="${dao.all}" var="store">
            	 <c:if test="${store.st_no == employee.st_no}" >
                 	<li>${store.st_name}</li>
                 </c:if>
            </c:forEach>
            <li>${employee.emp_name}<a href="<%=request.getContextPath() %>/EmployeeLogin" style="display: inline;"><i class="fa-solid fa-right-from-bracket"></i></a></li>
        	<input id="path" value="<%=request.getContextPath() %>" style="display: none;">
        </ul>
    </nav>
    <!-- 左側功能欄位 -->
    <main class="main">
        <aside class="aside;">
            <!-- 訂單管理 -->
            <nav class="nav-list1">
                <ul id="showFu">
                    
                </ul>
            </nav>
        </aside>

        <div class="content">
				
            	<div class="row justify-content-center text-center">

				<div class="col-10  ">
					<h1 style="margin-bottom: 0px">新增行銷活動</h1>
					<!-- 錯誤訊息 -->
					<div id="message" style="color: red">
						<ul>
						</ul>
					</div>
					<form id="eventform">
						<!-- onsubmit="return checkDate(this);"-->
						<div class="col-10 mx-auto">
						
							<div class="d-flex flex-row" style="font-size: 18px">
							<label for="event_title" class="text-left">活動標題</label>&emsp;
							<small style="color: red;font-weight: normal;"  class="msg"  id="event_titleMsg" ></small>
							</div>
							
							<div class="input-group">
								<input type="text" style="height: 10%;" class="form-control"
									value="" name="event_title">
							</div>	
						</div>

						<div class="col-10 mx-auto">
							<div class="d-flex flex-row" style="font-size: 18px">
							<label for="event_content">活動內容</label>&emsp;<small style="color: red;font-weight: normal;"  class="msg"  id="event_contentMsg" ></small>
							</div>
							<div class="input-group">
								<textarea type="textarea" class="form-control"
									id="event_content" name="event_content"
									style="width: 40%; height: 100px; resize: none;"></textarea>
							</div>
						</div>


						<!-- <div class="container"> -->
						<div class="row  g-5 justify-content-center">
							<div class=" col-5">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="event_start">活動起算時間</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg"  id="event_startMsg" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;"
										class="form-control datepicker" id="event_start" value=""
										name="event_start">
								</div>
							</div>

							<div class="col-5">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="event_end">活動結束時間</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg"  id="event_endMsg" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										value="" id="event_end" name="event_end" required>
								</div>

							</div>
						</div>
						<!-- </div> -->

<!-- 						<div class="container"> -->
<!-- 						<div class="row g-5 justify-content-center"> -->
<!-- 							<div class="col-5"> -->
<!-- 							<div class="d-flex flex-row" style="font-size: 18px"> -->
<!-- 								<label for="st_name_start">起租站點</label> -->
<!-- 								</div> -->
<!-- 								<div class="input-group"> -->
<!-- 									<select id="st_name_start" class="form-control" style="height: 10%;" -->
<!-- 										name="st_name_start"> -->
<!-- 										<option value=""></option> -->
										
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->


<!-- 							<div class="col-5"> -->
<!-- 							<div class="d-flex flex-row" style="font-size: 18px"> -->
<!-- 								<label for="st_name_end">歸還站點</label> -->
								
<!-- 									</div> -->
<!-- 								<div class="input-group"> -->
<!-- 									<select  id="st_name_end" class="form-control" style="height: 10%;" -->
<!-- 										name="st_name_end"> -->
<!-- 										<option value=""></option> -->
									
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 						</div> -->
						<!-- </div> -->

						<!-- <div class="container "> -->
						<div class="row g-5 justify-content-center ">
							<div class="col-5 ">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="model_no">車型</label>&emsp;
									<small style="color: red;font-weight: normal;"  class="msg"  id="model_noMsg" ></small>
								</div>
								<div class="input-group">
									<select class="form-control" style="height: 10%;" id="model_no"
										name="model_no" required>
										<option value="" ></option>
									</select>
								</div>
								
							</div>
							<div class="col-5 ">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="event_discount">優惠內容</label>&emsp;
									<small style="color: red;font-weight: normal;"  class="msg"  id="event_discountMsg" ></small>
									</div>
								<div class="input-group">
									<input type="text" class="form-control" style="height: 10%;"
										value="" name="event_discount" required>
								</div>
							</div>

							<!-- </div> -->
						</div>
						<div class="col-10  mx-auto">
						<div class="d-flex flex-row" style="font-size: 18px">
							<label for="event_photo">活動照片</label>&emsp;
							<small style="color: red;font-weight: normal;"  class="msg"  id="event_photoMsg" ></small>
								</div>
							<img id="frame"
								src="" hidden style="width: 600px;heigth:600px" />
							<!-- <div class="input-group gy-2"> -->
							<input type="file" class="form-control" id="event_photo"
								name="event_photo" onchange="preview();return false;"
								style="margin-top: 10px; margin-bottom: 10px; height: 10%;">
								
						</div>
				
						<!-- <div style="width: 100%;"> -->
						<button style="margin-top: 5px; width: 60%;" type="button"
							id="sub" class="btn btn-outline-success btn-lg">送出</button>
							
						<!-- </div> -->
					</form>

				</div>

			</div>
        </div>
    </main>
    
    <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
    <link rel="stylesheet" type="text/css" href="/CGA102G4/resources/datetimepicker/jquery.datetimepicker.css" />
	<script
		src="/CGA102G4/resources/datetimepicker/jquery.js"></script>
	<script
		src="/CGA102G4/resources/datetimepicker/jquery.datetimepicker.full.js"></script>




	<script>
         $('.lss').click(function (e) {
                const target = e.target;
                if (target.classList.contains('lss')) {
                    $(this).children('div.div2').slideToggle(500);
                }
            })
            function preview() {
                frame.src = URL.createObjectURL(event.target.files[0]);
                frame.hidden = false;
            }
            $.datetimepicker.setLocale('zh');

            $(function () {
                $('#event_start').datetimepicker({
                    format: 'Y-m-d',
                    onShow: function () {
                        this.setOptions({
                            minDate: '-1969-12-31',
                            maxDate: $('#start_date').val() ? $('#start_date').val() : false
                        })
                    },
                    timepicker: false
                });

                $('#event_end').datetimepicker({
                    format: 'Y-m-d',
                    onShow: function () {
                        this.setOptions({
                            minDate: $('#event_start').val() ? $('#event_start').val() : false
                        })
                    },
                    timepicker: false
                });
            });



            window.onload = function () {
                const select = document.getElementById("model_no");
                fetch('/CGA102G4/getmodel', { method: 'GET', })
                    .then(function (response) {
                        return response.json();
                    }).then(function (data) {
                        let list = data;
                        for (let l of list) {
                            const option = document.createElement("option");
                            option.innerText = l;
                            option.value = l;
                       
                            select.append(option)
                        }
                    });
                
                
//                 const st_name_start = document.getElementById("st_name_start");
//                 const st_name_end = document.getElementById("st_name_end");
//                 fetch('/CGA102G4/Getstoreall', { method: 'GET', })
//                     .then(function (response) {
//                         return response.json();
//                     }).then(function (data) {
//                         let list = data;
//                         for (let l of list) {
//                         	console.log(l);
//                         	if(l.st_no !== "TPEHO"){
//                         		console.log(l);
//     	                        const st_name_startSelected = '${requestScope.eventObject.st_name_start}';
//     	                        const optionstart = document.createElement("option");
//     	                        optionstart.innerText = l.st_name;
//     	                        optionstart.value = l.st_no;
//     	                        st_name_start.append(optionstart);
    	                        
//     	                        const st_name_endtSelected = '${requestScope.eventObject.st_name_end}';
//     	                        const option1 = document.createElement("option");
//     	                        option1.innerText = l.st_name;
//     	                        option1.value = l.st_no;
//     	                        st_name_end.append(option1);
//                         	}
//                         }
//                     });
            }


            const event_start = document.getElementById("event_start");
            const event_end = document.getElementById("event_end");
            function checkDate() {
                const today = new Date().toISOString().slice(0, 10);
                const start_value = event_start.value;
                const end_value = event_end.value;
                if (start_value == null) {
                    alert("活動起算時間不能為空");
                    return false;
                }
                if (start_value.valueOf() < today.valueOf()) {
                    alert("活動起算時間不能小於今天日期");
                    return false;
                }
                if (start_value.valueOf() > end_value.valueOf()) {
                    alert("活動起算時間不能大於活動結束日期");
                    return false;
                }

                return true;
            }
            $('#sub').click(function () {
                let passFrom = document.getElementById("eventform");
                let pform = new FormData(passFrom);
                $('.msg').empty();
                $.ajax({
                    type: "POST",
                    url: "/CGA102G4/event/eventAdd",
                    data: pform,
                    contentType: false,//must, tell jQuery not to process the data
                    processData: false,
                    success: function (data) {
                        console.log(data)
                        //JSON.stringify() : object to JSon
                        data = JSON.parse(data); //form json to javascriptelement
                        // console.log(jsonData)

                        $.each(data, function (Key, val) {
                            if (Key === "redirect") {
                                window.location.href = val;
                                return;
                            }
                            console.log(Key)
                            //	<font style="color:red">請修正以下錯誤:</font>
                          //  $('#message ul').append("<li>" + val + "</li>");
                          $('#'+Key).append(val);
                        });
                    }

                });
            });
    </script>
</body>

</html>