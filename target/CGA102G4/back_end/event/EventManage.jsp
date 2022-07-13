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
		<h2 class="mb-8" style="text-align: center;">行銷活動管理</h2>
	
		<c:if test="${not empty message}">
		<ul style="list-style:non;">
				<li style="color:red;text-align: center;list-style:none;">${message}</li>
				<c:remove var="message" scope="session" />
		
		</ul>
		
		
		</c:if>
		<table class="table table-hover  text-center"
			style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; margin-left: auto; margin-right: auto; width: 90%;"
			border="">
			<thead>
				<tr class="table-success" >
					<th scope="col">活動編號</th>
					<th scope="col">活動標題</th>
					<th scope="col">活動起算時間</th>
					<th scope="col">活動結束時間</th>
					<th scope="col">車型</th>
					<th scope="col">折扣內容</th>
					<th scope="col">操作</th>
					<th scope="col">查看</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.events}" var="event">
					<tr>
						<td scope="row">${event.event_no}</td>
						<td>${event.event_title}</td>
						<td>${event.event_start}</td>
						<td>${event.event_end}</td>
						<td>${event.model_no}</td>
						<td>${event.event_discount}</td>
						<td>
						<a href="${pageContext.request.contextPath}/event/eventEdit?event_no=${event.event_no}" class="btn btn-outline-success">編輯</a>			
						</td>
						<td><a href="${pageContext.request.contextPath}/event/eventShow?event_no=${event.event_no}"
							class="btn   btn-outline-success">查看</a></td>
					</tr>

				</c:forEach>


			</tbody>
		</table>

		<!-- </div> -->
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

	<script>
        $('.lss').click(function(e){
            const target = e.target;
            if(target.classList.contains('lss')){
                $(this).children().slideToggle(500);
            }
            console.log(target);
            
        })

   </script>
</body>

</html>