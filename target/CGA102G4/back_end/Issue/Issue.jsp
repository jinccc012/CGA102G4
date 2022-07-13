<%@page import="com.issue.model.IssueVO"%>
<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.issue.model.IssueService"%>
<%@ page import="com.issue.model.IssueVO"%>
<%@ page import="utils.jdbcUtilIssue"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
IssueVO issueVO = (IssueVO) request.getAttribute("issueVO");
%>
<!-- ============================================================後台版型============================================================ -->

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/Background_Home.css">
<!-- 首頁CSS 測試用路徑 進專案要改 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/Scar_Reserve/css/scarReserve.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/Issue/css/Issue.css">
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
					<input id="path" value="<%=request.getContextPath() %>" style="display: none;">
		</ul>
	</nav>
	<!-- <iframe src="back_end_Header.jsp" frameborder="0" style="width: 100%;" class="header"></iframe> -->
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

			<!-- ============================================================查詢============================================================ -->

			<h2 class="mb-8" style="text-align: center;">客服管理</h2>

			<form method="post"
				action="<%=request.getContextPath()%>/issueServlet">

				問題編號：<input type="search" placeholder="請輸入問題編號"
					style="width: 200px; height: 40px" name="issue_no"
					onkeyup="value=value.replace(/[^\d]/g,'')">
         
         				<input type="submit" value="查詢">
         				<input type="hidden" value="getByComposite">
         			
         				
            <table border="1" cellspacing="0" cellpadding="0" border-collapse: collapse align="center" 
            style="text-align: center; margin-top: 5px;margin-left:auto;margin-right:auto;width:95%;" border=""
            >
            
			<br><br>
			
			<div class="col-lg-7">
              <span id="order_status0" class="mb-5" style="font-size: 1rem;"><a href="/CGA102G4/OpenIssueList" style="display: inline;">未結案&ensp;</a></span>
              <span class="mb-5" style="font-size: 1rem;"> | </span>
              <span id="order_status1" class="mb-5" style="font-size: 1rem;"><a href="/CGA102G4/ClosedIssueList" style="display: inline;">已結案&ensp;</a></span>
            </div>
					
			<thead>
                  <tr style="text-align: center;">
                    <th>問題編號</th>
                    <th>姓&emsp;&emsp;名</th>
                    <th>電&emsp;&emsp;話</th>
                    <th>電子郵件</th>
                    <th>意見內容</th>
                    <th>處理狀態</th>
                    <th>操&emsp;&emsp;作</th>
                  </tr>
                </thead>		
					
					<tbody>
					<c:forEach var="set" begin="1" end="1" step="1"> 
						
						<c:forEach var="issueVO" varStatus="setClass" items="${getByComposite}">
						<tr >
							<!--用varStatus.count計次放進class順序 -->
							<td class="td${setClass.count}" id="issue_no" style="height: 40px">${issueVO.issue_no}</td>
							<td class="td${setClass.count}">${issueVO.issue_name}</td>
							<td class="td${setClass.count}">${issueVO.issue_tel}</td>
							<td class="td${setClass.count}" title="${issueVO.issue_mail}">${issueVO.issue_mail}</td>
							<td class="td${setClass.count}" title="${issueVO.issue_content}" style="text-align:left; padding:5px;">${issueVO.issue_content}</td>
							<c:if test="${issueVO.issue_process_state eq '0'}"><td class="td${setClass.count}">未結案</td></c:if>
							<c:if test="${issueVO.issue_process_state eq '1'}"><td class="td${setClass.count}">已結案</td></c:if>
							<c:if test="${issueVO.issue_process_state eq '0'}"><td><input type="button" class="btn" value="確認結案"></td></c:if>
							<c:if test="${issueVO.issue_process_state != '0'}"><td></td></c:if>
						</tr>
						</c:forEach>
					</c:forEach>
					</tbody>
				</table>					
					
		</form>				
					
			






<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/Issue/js/tableLink.js"></script>

















		</div>
	</main>
	
	    <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
	
	
	
	
	
	<script
		src="<%=request.getContextPath()%>/resources/js/Background_Home.js"></script>
	<script src="<%=request.getContextPath()%>/backgroundHome.js"></script>
	<script>
// 		sessionStorage.setItem('employee', `${employeejson}`)
	</script>
	    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
	
	
	
</body>
</html>