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
		<div class="container" style="width: 95%;">
					<h1 style="margin-bottom: 0px;font-size: 2rem;padding-left: 0px;">新增中古車</h1>
			<div class="row justify-content-center text-center">
	
				<div class="col-9">

								<!-- 錯誤訊息 -->
								<div id="message" style="color:red">
									<ul>
									</ul>
								</div>		
					<form id="scarform">
						<div class="row  justify-content-center">
							<div class="col-6 ">
								<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_no">中古車輛編號</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg repeate" ></small>
									<small style="color: red;font-weight: normal;"  class="msg scar_no" ></small>
								</div>
								<div class="input-group ">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_no" name="scar_no" value="${scar.scar_no}">
								</div>
							</div>

							<div class="col-6">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="st_no">門市</label>&emsp;
									<small style="color: red;font-weight: normal;"  class="msg st_no" ></small>
								</div>
								<div class="input-group">
									<select class="form-control" id="st_no" style="height: 10%;" name="st_no"
										required>
										<option value=""></option>
									
									</select>
								</div>
							</div>
						</div>

						<div class="row   justify-content-center">
							<div class=" col-6">
								<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_brand">中古車品牌</label>&emsp;
									<small style="color: red;font-weight: normal;"  class="msg scar_brand" ></small>
								</div>
								<div class="input-group">
									<select style="height: 10%;" class="form-control"
										id="scar_brand" name="scar_brand" required>
									<option value=""></option>
									</select>
								</div>
							</div>

							<div class="col-6">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_model">中古車車型</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_model" ></small>
								</div>
								<div class="input-group"> 
									<select class="form-control" style="height: 10%;"
										id="scar_model"	 name="scar_model" required>
										<option value=""></option>
		
									</select>
								</div>
							</div>
						</div>
						<div class="row  g-1  justify-content-center">
							<div class="col-4">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_color">中古車顏色</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_color" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_color" name="scar_color" value="${scar.scar_color}" required>
								</div>
							</div>
							<div class="col-4">
								<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_year">中古車年份</label>&emsp;
									<small style="color: red;font-weight: normal;"  class="msg scar_year" ></small>
									</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_year" name="scar_year" value="${scar.scar_year}"required>
								</div>
							</div>
							<div class="col-4">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_cc">中古車排氣量</label>&emsp;
									<small style="color: red;font-weight: normal;"  class="msg scar_cc" ></small>
									</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_cc" name="scar_cc" value="${scar.scar_cc}">
								</div>
							</div>
						</div>

						<div class="row g-1 justify-content-center ">
							<div class="col-4">
								<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_trans">中古車變速系統</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_trans" ></small>
									</div>
								<div class="input-group">
									<select class="form-control" style="height: 10%;"
										name="scar_trans">
										<option value=""></option>
										<option value="自排" ${(scar.scar_trans eq "自排")?'selected':'' }>自排</option>
										<option value="手排" ${(scar.scar_trans eq "手排")?'selected':'' }>手排</option>
									</select>
								</div>
							</div>
							<div class="col-4">
							<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_fuel">中古車燃料</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_fuel" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_fuel" name="scar_fuel" value="${scar.scar_fuel}" >
								</div>
							</div>
							<div class="col-4">
								<div class="d-flex flex-row" style="font-size: 18px;font-weight: normal;">
								<label for="scar_miles">里程</label>&emsp;
									<small style="color: red;font-weight: normal;"  class="msg scar_miles" ></small>
								</div>
								<div class="input-group">
									<input type="number" style="height: 10%;" class="form-control"
										id="scar_miles" name="scar_miles" value="${scar.scar_miles}">
								</div>
							</div>

						</div>
								<div class="row g-1 justify-content-center ">
							<div class="col-6 ">
							<div class="d-flex flex-row" style="font-size: 18px;font-weight: normal;">
								<label for="scar_startime">起標日期</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_startime" ></small>
										<small style="color: red;font-weight: normal;"  class="msg startime_erro" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_startime" name="scar_startime" value="${scar.scar_startime}" required>
								</div>
							</div>
							<div class="col-6 ">
								<div class="d-flex flex-row" style="font-size: 18px">
								<label for="scar_endtime">結標日期</label>&emsp;
										<small style="color: red;font-weight: normal;"  class="msg scar_endtime" ></small>
										<small style="color: red;font-weight: normal;"  class="msg date_erro" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_endtime" name="scar_endtime" value="${scar.scar_endtime}">
								</div>
							</div>
						</div>
						<div class="row g-1 justify-content-center ">
							<div class="col-6 ">
								<div class="d-flex flex-row" style="font-size: 18px;font-weight: normal;">
								<label for="scar_carrying">乘載人數</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_carrying" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_carrying" name="scar_carrying" value="${scar.scar_carrying}" required>
								</div>
							</div>
							<div class="col-6 ">
								<div class="d-flex flex-row" style="font-size: 18px;font-weight: normal;">
								<label for="scar_carringpkg">乘載行李數</label>&emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_carringpkg" ></small>
								</div>
								<div class="input-group">
									<input type="text" style="height: 10%;" class="form-control"
										id="scar_carringpkg" name="scar_carringpkg" value="${scar.scar_carringpkg}" list="pkg"
										>
								</div>
								<datalist id="pkg">
									<option value="1大2小">
									<option value="2大1小">
									<option value="2大2小">
									<option value="2大3小">
									<option value="3大2小">
								</datalist>
							</div>
						</div>
						
						<div class="row g-1 justify-content-center ">
						<div class="col-6">
						<div class="d-flex flex-row" style="font-size: 18px;font-weight: normal;">
							<label for="scar_price">底標價格</label>&emsp;
							<small style="color: red;font-weight: normal;"  class="msg scar_price" ></small>
								</div>
							<div class="input-group">
								<input type="number" style="height: 10%;" class="form-control"
									id="scar_price" name="scar_price"
									value="${scar.scar_price}"
									>
							</div>
							</div>
							<div class="col-6">
							<div class="d-flex flex-row" style="font-size: 18px;">
							<label for="scar_price">起拍價格</label>&emsp;
							<small style="color: red;font-weight: normal;"  class="msg scar_startprice" ></small>
								</div>
							<div class="input-group">
								<input type="number" style="height: 10%;" class="form-control"
									id="scar_startprice" name="scar_startprice"
									value="${scar.scar_startprice}"
									>
							</div>
							</div>
							
						</div>
						<button style="margin-top: 5px; width: 40%;" type="button" id="sub"
							class="btn btn-outline-success btn-lg">送出</button>
				</div>
					
						<div class="col-3  mx-auto">
						<div class="d-flex flex-row" style="font-size: 18px">
							<label for="scar_photo">車輛照片</label> &emsp;
								<small style="color: red;font-weight: normal;"  class="msg scar_photo" ></small>
								</div>
							
							<br> <img id="frame"
								src="" hidden style="width: 325px; height: 300px;" />
							<!-- <div class="input-group gy-2"> -->
							<input type="file" class="form-control" id="scar_photo"
								name="scar_photo" onchange="preview();return false;" 
								style="margin-top: 10px;"
								>
						</div>
						<!-- <div style="width: 100%;"> -->
						
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
		
		
		
		
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/datetimepicker/jquery.datetimepicker.css" />
	<script src="${pageContext.request.contextPath}/resources/datetimepicker/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datetimepicker/jquery.datetimepicker.full.js"></script>	
	
	<script>
	   $('.lss').click(function (e) {
           const target = e.target;
           if (target.classList.contains('lss')) {
               $(this).children('div.div2').slideToggle(500);
           }
       })
		$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#scar_startime').datetimepicker({	
						step: 1,  
						format : 'Y-m-d H:i:00',
						onShow : function() {
							this.setOptions({
								minDate : '-1970/01/01',
								maxDate : $('#scar_endtime').val() ? $('#scar_endtime').val() : false
							})
						},
						timepicker : true
					});

		$('#scar_endtime').datetimepicker({
						step: 1,   
						format : 'Y-m-d H:i:00',
						onShow : function() {
							this.setOptions({
						minDate : $('#scar_startime').val() ? $('#scar_startime').val() : false
							})
						},
						timepicker : true
					});
	
	</script>


	<script>
		function preview() {
			frame.src = URL.createObjectURL(event.target.files[0]);
			frame.hidden = false;
		}
		
		 window.onload = function(){
			    const select = document.getElementById("scar_brand");
			    fetch('${pageContext.request.contextPath}/getcarModelname',{ method:'GET',})
			    .then(function(response){
			    		return response.json();
			    }).then(function(data){
			   console.log(data);
			    let list = data;
			    for(let l of list){
			    	const nameSelectd = '${scar.scar_brand}';
			            const option = document.createElement("option");
			            option.innerText = l;
			            option.value = l;
			            if (l === nameSelectd)
                         option.selected = true;
			            select.append(option)
			    }
			    });
			    
			    
			    //selected scar_model
			    const Selcetmodel = document.getElementById("scar_model");
			    const optionl = document.createElement("option");
			    const model ='${scar.scar_model}';
			    optionl.innerText = model;
			    optionl.value = model;
			    optionl.selected = true;
		         Selcetmodel.append(optionl)
		         
		         
		         const st_no = document.getElementById("st_no");
	                fetch('/CGA102G4/Getstoreall', { method: 'GET', })
	                    .then(function (response) {
	                        return response.json();
	                    }).then(function (data) {
	                        let list = data;
	                        for (let l of list) {
	                        	console.log(l);
	                        	if(l.st_no !== "TPEHO"){
	                        		console.log(l);
	    	                        const st_name_startSelected = '${requestScope.eventObject.st_name_start}';
	    	                        const optionstart = document.createElement("option");
	    	                        optionstart.innerText = l.st_name;
	    	                        optionstart.value = l.st_no;
	    	                        st_no.append(optionstart);

	                        	}
	                        }
	                    });
		         
		         
			    
			    }

			$('#scar_brand').change(function(){
				  const select = document.getElementById("scar_model");
				 fetch('${pageContext.request.contextPath}/getcarModelname?scar_model='+$(this).val(),
						 { method:'GET',
						 })
				    .then(function(response){
				    		return response.json();
				    }).then(function(data){
				//   console.log(data);
				    let list = data;
				    select.options.length = 0;
				    const option = document.createElement("option");
				    select.append(option)
				    for(let l of list){
				            const option = document.createElement("option");
				            option.innerText = l;
				            option.value = l;
				            select.append(option)
				    }
				    });
			});

		// const errormessage = document.getElementById("errormessage");

		// window.onload = function () {
		//     const select = document.getElementById("model_name");
		//     fetch('getModelJson', { method: 'GET', })
		//         .then(function (response) {
		//             return response.json();
		//         }).then(function (data) {
		//             let list = data;
		//             for (let l of list) {
		//                 //console.log(l);
		//                 const option = document.createElement("option");
		//                 option.innerText = l;
		//                 option.value = l;
		//                 select.append(option)
		//             }
		//         });

		// }

		// const event_start = document.getElementById("scar_startime");
		// const event_end = document.getElementById("scar_endtime");

		// $("#scar_endtime").change(function(){
		//   //  alert($(this).val())
		//   checkDate();
		// });
		// function checkDate() {
		//     const today = new Date().toLocaleTimeString
		//     const todaydate = new Date().toDateString
		//     const start_value = event_start.value;
		//     const end_value = event_end.value;
		//     alert(new Date().toLocaleTimeString());
		//     console.log(today.valueOf());
		//     console.log(start_value.valueOf());
		//     console.log(end_value.valueOf());
		//     if (!start_value) {
		//         alert("活動起算時間不能為空");
		//         return false;
		//     }
		//     if (start_value.valueOf() < today.valueOf()) {
		//         alert("活動起算時間不能小於當前");
		//         return false;
		//     }
		//     if (start_value.valueOf() > end_value.valueOf()) {
		//         alert("活動起算時間不能大於活動結束日期");
		//         return false;
		//     }

		//     return true;
		// }

		// const scar_startime = document.getElementById("scar_startime");
		// const scar_endtime = document.getElementById("scar_endtime");
		// function check() {
		//     let scar_no = document.getElementById('scar_no');
		//     scarRule = /[A-Z0-9]{17}/;
		//     if (scar_no.value.search(scarRule) === -1) {
		//         alert("引擎編號格式錯誤");
		//         return false;
		//     }
		//     if (scar_no.value.length !== 17) {
		//         console.log(scar_no.value.length);
		//         alert("引擎編號長度須為17");
		//         return false;
		//     }
		//     checkDate();
		//     return true;

		// }
		
		 $('#sub').click(function () {
            let passFrom = document.getElementById("scarform");
            let pform = new FormData(passFrom);
            $('.msg').empty();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}//scar/scarAdd",
                data: pform,
                contentType: false,//must, tell jQuery not to process the data
                processData: false,
                success: function (data) {
                    console.log(data) 
                    //JSON.stringify() : object to JSon
                     data = JSON.parse(data); //form json to javascriptelement
            		$.each(data, function(Key, val) {
            			if(Key==="redirect"){
            				console.log(val)
            				 window.location.href = val;
            				return;
            			}
            		 	$('.'+Key).append(val);

					});
                }

            });
        });
		
	</script>
</body>

</html>