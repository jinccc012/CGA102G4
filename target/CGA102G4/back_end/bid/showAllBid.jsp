<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
	<%@ page import="com.bidding.model.BiddingVO"%>
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
	
		<h2 class="mb-8" style="text-align: center;">中古車競標紀錄</h2>
		<c:if test="${not empty requestScope.message}">
			<p style="color: red; text-align: center;">${requestScope.message}</p>
		</c:if>
		
		 <form method="POST" action="${pageContext.request.contextPath}/bid/bidShow"> 
         <div class="container">
        <div class="row "  >
        
			<div class="col-md-9 ">
			  <div class="row ">
			  <div class="col-md-1">
			  </div>
		         <div class="col-md-3">
		                  <input type="text" class="form-control" name="scar_no" placeholder="請輸入中古車編號">
		          </div>
		        
        	  <div class="col-md-3	">
	                <input type="text"class="form-control"  name="meb_no" placeholder="請輸入會員編號">
	            </div>
   
              <div class="col-md-2">
                <button type="submit"  class="btn btn-outline-success" style="height: 100%;weight:100%">搜尋</button>
            </div>

            </div>
            </div>
            
             <div class="col-md-3" > 
             	<%@ include file="bidpage1.file" %> 
            </div> 
        </div>
        </div>
		  </form>
		<table class="table table-hover  text-center tablesorter" id="myTable"  data-toggle="table" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;margin-left:auto;margin-top:10px;margin-right:auto;width:95%;" border="">
			<thead>
				<tr style="text-align: center;">
				
					<th style="font-size:16px;">競拍紀錄編號</th>
					<th  style="font-size:16px;">中古車編號</th>	
					<th  style="font-size:16px;">會員編號</th>
					<th  style="font-size:16px;">競標價</th>
					<th  style="font-size:16px;">出價日期</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${bidlist}" var="bid" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						
						<td scope="row">${bid.bid_no}</td>
						<td>${bid.scar_no}</td>
						<td>${bid.meb_no}</td>
						<td><fmt:formatNumber value="${bid.bid_price}" pattern="#,###"/></td>
						
						<td><fmt:formatDate value="${bid.bid_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
	<%@ include file="bidpage2.file" %> 
		<!-- </div> -->
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
		  	

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css"></link>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/js/jquery.tablesorter.min.js"></script>
<script>
	$("#myTable").tablesorter({
	theme: "blue",
	widgets: ['zebra']
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
       
	</script>
</body>

</html>