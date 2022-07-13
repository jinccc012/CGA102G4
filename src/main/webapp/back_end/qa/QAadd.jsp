<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.QAService"%>
<%@ page import="com.qa.model.QAVO"%>
<%
    QAVO qaVO =(QAVO) request.getAttribute("qaVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon"
	href="<%=request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png"
	type="image/x-icon" />
<title>Family Rent || 後台管理系統</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/1d9dcf12aa.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<!-- 測試用路徑 進專案要改 -->
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.js"></script>
<!-- 測試用路徑 進專案要改 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/Background_Home.css">
<!-- 首頁CSS 測試用路徑 進專案要改 -->

</head>

<body>
	<jsp:useBean id="dao" class="com.store.model.StoreService" />
	<!--     上面header欄位 -->
	<nav class="header">
		<h1>Family Rent 後台管理系統</h1>
		<ul class="store nav " id="emp">
			<label for=""></label>
			<c:forEach items="${dao.all}" var="store">
				<c:if test="${store.st_no == employee.st_no}">
					<li>${store.st_name}</li>
				</c:if>
			</c:forEach>
			<li>${employee.emp_name}<a
				href="<%=request.getContextPath()%>/EmployeeLogin"
				style="display: inline;"><i
					class="fa-solid fa-right-from-bracket"></i></a></li>
			<input id="path" value="<%=request.getContextPath()%>"
				style="display: none;">
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
			<FORM METHOD="post" ACTION="../../back_end/qa.do" name="form">
				<div class="col-8" style="margin: 0 auto; margin-top: 10%;">
					<div class="row">
						<div class="col">
							<label for="formGroupExampleInput2" class="form-label">請輸入標題</label>
							<input type="text" name="title" class="form-control"
								placeholder="標題" aria-label="Title">
						</div>
						<div class="col-md-2">
							<label for="formGroupExampleInput2" class="form-label">請輸入Tag</label>
							<input type="text" class="form-control" name="tag"
								placeholder="Tag" aria-label="Tag">
						</div>
					</div>
					<div class="mb-3">
						<label for="formGroupExampleIn put2" class="form-label">請輸入內容</label>
						<textarea name="editor"></textarea>
						<br />
					</div>
					<input type="hidden" name="action" value="insert"> <input
						type="submit" id="setData" name="setData" value="新增資料">
				</div>
			</FORM>
		</div>
	</main>

	<%-- 提示彈穿 --%>
	<div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;"></div>
	<script
		src="<%=request.getContextPath()%>/resources/js/Background_Home.js"></script>
	<script>
		sessionStorage.setItem('employee', `${employeejson}`)
	</script>
	<script src="<%=request.getContextPath()%>/resources/js/getEmpPr.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/websocket.js"></script>

    <script src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>  
    <script>  
      CKEDITOR.replace('editor');  
      function getData() {  
          //Get data written in first Editor   
          var editor_data = CKEDITOR.instances['editor'].getData();  
          
      }  
</script>  
</body>

</html>