<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.QAService"%>
<%@ page import="com.qa.model.QAVO"%>
<%
    QAService svc = new QAService();
    List<QAVO> list = svc.getAll(); 
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="icon" href="<%=request.getContextPath()%>/resources/icon/pngkey.com-gps-icon-png-5131691.png" type="image/x-icon" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Family Rent || 後台管理系統</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/1d9dcf12aa.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css"> <!-- 測試用路徑 進專案要改 -->
    <script src="<%=request.getContextPath() %>/resources/js/bootstrap.bundle.js"></script> <!-- 測試用路徑 進專案要改 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/Background_Home.css"> <!-- 首頁CSS 測試用路徑 進專案要改 -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Koulen&family=Teko:wght@300&display=swap');
    </style>
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
        <div class="col-8" style="margin: 0 auto; margin-top: 10%;">
           <%@ include file="pageSetting" %>
           <a href="QAadd.jsp"><button type="button" class="btn btn-primary">新增</button></a>
           <div class="accordion" id="accordionFlushExample">
           <% for(int i=0;i<list.size();i++){ %>
            <div class="accordion-item">
              <h2 class="accordion-header" id=<%= "header"+i %>>
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target=<%= "#collapse"+i %> aria-expanded="false" aria-controls=<%= "collapse"+i %>>
                  <h2><strong><%= list.get(i).getQa_title() %></strong></h2>
                </button>
              </h2>
              <div id=<%= "collapse"+i %> class="accordion-collapse collapse" aria-labelledby=<%= "header"+i %> data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <%= list.get(i).getQa_content() %>
                  <br/>
                    <FORM METHOD="post" ACTION="../qa.do" name="form">
                      <input type="hidden" name="action" value="modify"> 
                      <input type="hidden" name="qano" value="<%= list.get(i).getQa_no()%>">  
                      <input type="submit" id="upData" name="modify" value="修改">
                    </FORM>
                    <FORM METHOD="post" ACTION="../qa.do" name="form">
                      <input type="hidden" name="action" value="delet">
                      <input type="hidden" name="qano" value="<%= list.get(i).getQa_no()%>">  
                      <input type="submit" id="delData" name="delData" value="刪除">
                    </FORM>
                </div>
              </div>
            </div>
          <% }%>
          <%@ include file="pages" %>
        </div>
      </div>  
      </div>  
    </main>
	<div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
   <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
   <script src="<%=request.getContextPath() %>/resources/js/bootstrap-datepicker.min.js"></script>
</body>

</html>