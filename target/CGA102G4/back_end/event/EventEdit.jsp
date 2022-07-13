<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		 <div class="container">
            <div class="row justify-content-center text-center" > 
                <div class="col-10 " style="font-size: 20px;">
                    <h1 style="margin-bottom:0px;">編輯行銷活動</h1>
                    <form class="needs-validation" method="post" action="editEvent" id="eventform" enctype="multipart/form-data"
                        onsubmit="return checkDate(this);">
                        <div class="col-10 mx-auto  ">
                            <input type="hidden" name="event_no" value="${requestScope.eventObject.event_no}">

                           <div class="d-flex flex-row" style="font-size: 18px">
							<label for="event_title" class="text-left">活動標題</label>&emsp;
							<small style="color: red;"  class="msg"  id="event_titleMsg" ></small>
							</div>
                            <div class="input-group ">
                                <input type="text" style="height:10%;" class="form-control" name="event_title" required  value="${requestScope.eventObject.event_title}">
                            </div>
                          
                        </div>

                        <div class="col-10 mx-auto ">
                            	<div class="d-flex flex-row" style="font-size: 18px">
							<label for="event_content">活動內容</label>&emsp;<small style="color: red;"  class="msg"  id="event_contentMsg" ></small>
							</div>
                            <div class="input-group">
                                <textarea type="textarea" class="form-control" id="event_content" name="event_content"
                                    required style="width: 40%;height:100px; resize: none;">${requestScope.eventObject.event_content}</textarea>
                            </div>
                           
                        </div>


                        <!-- <div class="container"> -->
                        <div class="row   justify-content-center">
                            <div class=" col-5">
                               <div class="d-flex flex-row" style="font-size: 18px">
								<label for="event_start">活動起算時間</label>&emsp;
								<small style="color: red;"  class="msg"  id="event_startMsg" ></small>
								</div>
                                <div class="input-group">
                                    <input type="text" style="height:10%;" class="form-control datepicker"
                                        id="event_start" name="event_start" required value="${requestScope.eventObject.event_start}">
                                </div>
                            </div>

                            <div class="col-5">
                             	<div class="d-flex flex-row" style="font-size: 18px">
								<label for="event_end">活動結束時間</label>&emsp;
								<small style="color: red;"  class="msg"  id="event_endMsg" ></small>
								</div>
                                <div class="input-group">
                                    <input type="text" style="height:10%;" class="form-control" id="event_end"
                                        name="event_end" required value="${requestScope.eventObject.event_end}">
                                </div>
                            </div>
                        </div>
                        <!-- </div> -->


                        <!-- <div class="container"> -->
<!--                         <div class="row  justify-content-center"> -->
<!--                             <div class="col-5"> -->
<!--                                 <label for="st_name_start">起租站點</label> -->
<!--                                 <div class="input-group"> -->
<!--                                     <select id="st_name_start" class="form-control" style="height:10%;" name="st_name_start" > -->
<!--                                         <option value=""></option> -->

<!--                                     </select> -->
<!--                                 </div> -->
<!--                             </div> -->


<!--                             <div class="col-5"> -->
<!--                                 <label for="st_name_end">歸還站點</label> -->
<!--                                 <div class="input-group"> -->
<!--                                     <select class="form-control" id="st_name_end" style="height:10%;" name="st_name_end"> -->
<!--                                         <option value=""></option> -->
<!--                                     </select> -->
<!--                                 </div> -->
<!--                             </div> -->

<!--                         </div> -->
                        <div class="row g-5 justify-content-center ">
                            <div class="col-5 ">
                               <div class="d-flex flex-row" style="font-size: 18px">
								<label for="model_no">車型</label>&emsp;
									<small style="color: red;"  class="msg"  id="model_noMsg" ></small>
								</div>
                                <div class="input-group">
                                    <select class="form-control" style="height:10%;" id="model_no" name="model_no"
                                        required>
                                        <option value=""></option>
                                    </select>
                                </div>                
                            </div>
                            <div class="col-5 ">
                             <div class="d-flex flex-row" style="font-size: 18px">
								<label for="event_discount">優惠內容</label>&emsp;
									<small style="color: red;"  class="msg"  id="event_discountMsg" ></small>
									</div>
                                <div class="input-group">
                                    <input type="text" style="height:10%;" class="form-control" name="event_discount"
                                        required value="${requestScope.eventObject.event_discount}">
                                </div>
                           
                            </div>
                        </div>
                        <div class="col-10  mx-auto">
                            <label for="event_photo">活動照片</label>
                            <br>
                            <img id="frame" src="${pageContext.request.contextPath}/images?event_no=${requestScope.eventObject.event_no}"  style="width: 60%;height:40%;" />
                            <input type="file" class="form-control" id="event_photo" name="event_photo"
                                onchange="preview();return false;"
                                style="margin-top: 10px;margin-bottom: 10px;height:10%;">
                                <span style="color:red" class="msg" id="event_photoMsg"></span>
                        </div>
                        <button style="margin-top: 5px;width: 80%;" type="button" id="sub"
                            class="btn btn-outline-success btn-lg">送出</button>

                    </form>
                </div>
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
        
        
        
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/datetimepicker/jquery.datetimepicker.css" />
	<script src="${pageContext.request.contextPath}/resources/datetimepicker/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datetimepicker/jquery.datetimepicker.full.js"></script>


    <script>
        $('.lss').click(function (e) {
            const target = e.target;
            if (target.classList.contains('lss')) {
                $(this).children().slideToggle(500);
            }
            console.log(target);

        })
    </script>
    <script>
        function preview() {
            frame.src = URL.createObjectURL(event.target.files[0]);
            frame.hidden = false;
        }

  $.datetimepicker.setLocale('zh'); 
        
        $(function(){
       	 $('#event_start').datetimepicker({
       	  format:'Y-m-d',
       	  onShow:function(){
       	   this.setOptions({
       		minDate: '-1970-01-01',
       	    maxDate:$('#start_date').val()?$('#start_date').val():false
       	   })
       	  },
       	  timepicker:false
       	 });
       	 
       	 $('#event_end').datetimepicker({
       	  format:'Y-m-d',
       	  onShow:function(){
       	   this.setOptions({
       	    minDate:$('#event_start').val()?$('#event_start').val():false
       	   })
       	  },
       	  timepicker:false
       	 });
       });
    

        const errormessage = document.getElementById("errormessage");

        window.onload = function () {
            const select = document.getElementById("model_no");
            fetch('/CGA102G4/getmodel', { method: 'GET', })
                .then(function (response) {
                    return response.json();
                }).then(function (data) {
                    let list = data;
                    for (let l of list) {
                        const nameSelectd = '${requestScope.eventObject.model_no}';
                        const option = document.createElement("option");
                        option.innerText = l;
                        option.value = l;
                        if (l === nameSelectd)
                            option.selected = true;

                        select.append(option)
                    }
                });
            
//             const st_name_start = document.getElementById("st_name_start");
//             const st_name_end = document.getElementById("st_name_end");
//             fetch('/CGA102G4/Getstoreall', { method: 'GET', })
//                 .then(function (response) {
//                     return response.json();
//                 }).then(function (data) {
//                     let list = data;
//                     for (let l of list) {
//                     	if(l.st_no !== "TPEHO"){
// 	                        const st_name_startSelected = '${requestScope.eventObject.st_name_start}';
// 	                        const option = document.createElement("option");
// 	                        option.innerText = l.st_name;
// 	                        option.value = l.st_no;
// 	                        if (l.st_no === st_name_startSelected)
// 	                            option.selected = true;
// 	                        st_name_start.append(option)
	                        
// 	                        const st_name_endtSelected = '${requestScope.eventObject.st_name_end}';
// 	                        const option1 = document.createElement("option");
// 	                        option1.innerText = l.st_name;
// 	                        option1.value = l.st_no;
// 	                        if (l.st_no === st_name_endtSelected)
// 	                        	option1.selected = true;
// 	                        st_name_end.append(option1)
//                     	}
//                     }
//                 });
            
            

        }

        const event_start = document.getElementById("event_start");
        const event_end = document.getElementById("event_end");

//         function checkDate() {
//             const today = new Date().toISOString().slice(0, 10);
//             const start_value = event_start.value;
//             const end_value = event_end.value;
//             console.log(start_value.valueOf());
//             console.log(end_value.valueOf());
//             if (start_value == null) {
//                 alert("活動起算時間不能為空");
//                 return false;
//             }
//             if (start_value.valueOf() < today.valueOf()) {
//                 alert("活動起算時間不能小於今天日期");
//                 return false;
//             }
//             if (start_value.valueOf() > end_value.valueOf()) {
//                 alert("活動起算時間不能大於活動結束日期");
//                 return false;
//             }

//             return true;
//         }
        
        
        $('#sub').click(function () {
            let passFrom = document.getElementById("eventform");
            let pform = new FormData(passFrom);
            $('.msg').empty();
            $.ajax({
                type: "POST",
                url: "/CGA102G4/event/eventEdit",
                data: pform,
                contentType: false,//must, tell jQuery not to process the data
                processData: false,
                success: function (data) {
                    console.log(data) 
                    //JSON.stringify() : object to JSon
                     data = JSON.parse(data); //form json to javascriptelement
            		$.each(data, function(Key, val) {
            			if(Key==="redirect"){
            				 window.location.href = val;
            				 return;
            			}
            			console.log(Key)
            		     $('#'+Key).append(val);
					});
                }

            });
        });
        

    </script>
</body>

</html>