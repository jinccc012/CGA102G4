<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.scar.model.ScarVO"%>
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
		<div class="content" >
		<h2 class="mb-8" style="text-align: center;">中古車管理</h2>
		<br>
		<c:if test="${not empty sessionScope.message}">
			<p style="color: red; text-align: center;">${message}</p>
			<c:remove var="message" scope="session" />
		</c:if>
		 <form method="POST" action="${pageContext.request.contextPath}/scar/scarManage"> 
         <div class="container">
        <div class="row "  >
        	
			<div class="col-md-9 ">
			  <div class="row "  >
			  
			    <div class="col-md-1">
            </div>
            <div class="col-md-2 ">
                <select id="scar_brand" name="scar_brand"  style="width:150px;" class="form-control ">
                    <option value="" >請選擇車型</option>
                </select>
            </div>
            <div class="col-md-2 ">
                <select id="st_no" name="st_no" value="" class="form-control" style="width:150px">
                    <option value="">請選擇門市</option>
                
                </select>
            </div>
              <div class="col-md-2">
                <select name="scar_status" value="" class="form-control " style="width:150px">
                    <option value="">請選擇車輛狀態</option>
                   <option value="0" <c:if test="${ scar_status eq '0'}">selected</c:if> >下架中</option>
                     <option value="1" <c:if test="${scar_status  eq '1'}">selected</c:if> >上架中</option>
                     <option value="2" <c:if test="${scar_status eq '2'}">selected</c:if> >已結標</option>
                  <option value="3" <c:if test="${scar_status eq '3'}">selected</c:if> >已流標</option>
                <option value="4" <c:if test="${scar_status eq '4'}">selected</c:if> >已賣出</option>
                </select>
            </div>
       
             <div class="col-md-2" > 
                <input type="text"  style="width:150px"  class="form-control" name="scar_startime" id="scar_startime" value="${scar_startime}" placeholder="請輸入起標日">
            </div> 
            <div class="col-md-2" >
                <input type="text"  style="width:150px"  class="form-control" name="scar_endtime" id="scar_endtime" value="${scar_endtime}" placeholder="請輸入結標日">
            </div>
              <div class="col-md-1">
                <button type="submit"  class="btn btn-outline-success" style="height: 100%;weight:100%">搜尋</button>
            </div>
            </div>
            </div>
            
             <div class="col-md-3" > 
             	<%@ include file="page1.file" %> 
            </div> 
        </div>
        </div>
		  </form>
		<table class="table  table-hover" style="margin-top: 10px;margin-left:auto;margin-right:auto;width:95%;" border="">
			<thead>
			  <tr class="table-success" style="text-align: center;">
				<th scope="col">中古車輛編號</th>
				<th scope="col">門市</th>
				<th scope="col">車型</th>
				<th scope="col">年分</th>
				<th scope="col">底標價格</th>
				<th scope="col">起標價</th>
				<th scope="col">最高價</th>
				<th scope="col">起標日期</th>
				<th scope="col">結標日期</th>
				<th scope="col">車輛狀態</th>
				<th scope="col">操作</th>
			  </tr>
			</thead>
			
			<tbody style="text-align: center;">

			
			<c:forEach items="${scarList}" var="scar" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			   <tr>
				<td scope="row" >${scar.scar_no}</td>
				<td><c:if test = "${scar.st_no eq 'KH'}">高雄站</c:if> 
				<c:if test = "${scar.st_no eq 'TPE'}">台北站</c:if> 
				<c:if test = "${scar.st_no eq 'TC'}">台中站</c:if> 
				
				</td>
				<td>${scar.scar_model}</td>
				<td>${scar.scar_year}</td>
				<td><fmt:formatNumber value="${scar.scar_price}" pattern="#,###"/></td>
				<td><fmt:formatNumber value="${scar.scar_startprice}" pattern="#,###"/></td>
				<td><fmt:formatNumber value="${scar.scar_maxprice}" pattern="#,###"/></td>
				<td><fmt:formatDate value="${scar.scar_startime}" pattern="yy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${scar.scar_endtime}" pattern="yy-MM-dd HH:mm:ss"/></td>
				<td>
				<c:if test = "${scar.scar_status eq '0'}">下架中</c:if> 
					<c:if test = "${scar.scar_status eq '1'}">上架中</c:if> 
						<c:if test = "${scar.scar_status eq '2'}">已結標</c:if> 
						<c:if test = "${scar.scar_status eq '3'}">已流標</c:if> 
						<c:if test = "${scar.scar_status eq '4'}">已賣出</c:if> 
						
				</td>
				<td>
				  <a href="${pageContext.request.contextPath}/scar/scarEdit?scar_no=${scar.scar_no}" class="btn  btn-outline-success	" >編輯</a></td>
				</tr>
			 
			</c:forEach>
		   
			
			</tbody>
		  </table>

		  <%@ include file="page2.file" %>
		  	</div>
		  	
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
		$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#scar_startime').datetimepicker({	
						step: 60,  
						format : 'Y-m-d',
						onShow : function() {
							this.setOptions({
								//minDate : '-1969/12/31',
								maxDate : $('#scar_endtime').val() ? $('#scar_endtime').val() : false
							})
						},
						timepicker : false
					});

		$('#scar_endtime').datetimepicker({
//					step: 60,   
						format : 'Y-m-d',
						onShow : function() {
							this.setOptions({
						minDate : $('#scar_startime').val() ? $('#scar_startime').val() : false
							})
						},
						timepicker : false
					});
	
	</script>

	<script>
        $('.lss').click(function(e){
            const target = e.target;
            if(target.classList.contains('lss')){
                $(this).children().slideToggle(500);
            }
            console.log(target);
            
        })
        
        function onRemoveClick(){
        	if(!confirm('是否確定刪除?')){
        		return false ;
        	}
        	return true
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
		    	const nameSelectd = '${scar_brand}';
		            const option = document.createElement("option");
		            option.innerText = l;
		            option.value = l;
		            if (l === nameSelectd)
                     option.selected = true;
		            select.append(option)
		    }
		    });
		    
		    
		    
		    const st_no = document.getElementById("st_no");
            fetch('/CGA102G4/Getstoreall', { method: 'GET', })
                .then(function (response) {
                    return response.json();
                }).then(function (data) {
                    let list = data;
                    for (let l of list) {
                    	if(l.st_no !== "TPEHO"){
	                        const st_no_startSelected = '${st_no}';
	                        const optionstart = document.createElement("option");
	                        optionstart.innerText = l.st_name;
	                        optionstart.value = l.st_no;
	                        if (l.st_no === st_no_startSelected)
	                        	optionstart.selected = true;
	                        st_no.append(optionstart);

                    	}
                    }
                });
        };
   </script>
</body>
</html>