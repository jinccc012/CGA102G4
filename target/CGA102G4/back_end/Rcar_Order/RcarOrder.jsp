<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.rcarorder.model.RcarOrderService"%>
<%@ page import="com.rcarorder.model.RcarOrderVO"%>
<%@ page import="utils.jdbcUtilRcarOrder"%>
<%@ page import="com.carrentable.model.*"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
RcarOrderVO rcarOrderVO = (RcarOrderVO) request.getAttribute("rcarOrderVO");
%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>
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
	href="<%=request.getContextPath()%>/back_end/Rcar_Order/css/RcarOrder.css">
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

			<!-- ============================================================複合查詢============================================================ -->
			<h2 class="mb-8" style="text-align: center;">租賃訂單管理</h2>

			<form method="post"	action="<%=request.getContextPath()%>/rcarOrderBackendServlet">
				訂單&emsp;起：<input name="rcaro_ppicktime" class="f_date1" id="rost"
					type="text" placeholder="請選擇日期、時間" style="width: 200px; height: 40px"> 
				訂單&emsp;訖：<input name="rcaro_pprettime" class="f_date1" id="roet"
				    type="text" placeholder="請選擇日期、時間" style="width: 200px; height: 40px"> <br> 
				訂單編號：<input type="search" placeholder="請輸入預約編號"	style="width: 200px; height: 40px" name="rcaro_no"
					onkeyup="value=value.replace(/[^\d]/g,'')"> 
				會員編號：<input type="search" placeholder="請輸入會員編號"	style="width: 200px; height: 40px" name="meb_no"
					onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"> <br>
<!-- 				車輛級距：<input type="search" placeholder="請輸入車輛級距"	style="width: 200px; height: 40px" name="level_no" -->
<!-- 					onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">  -->
				車&emsp;&emsp;型：<input type="search" placeholder="請輸入車型" style="width: 200px; height: 40px" name="model_no"> 
<!-- 				<br> -->

				車&emsp;&emsp;號：<input type="search" placeholder="請輸入車號" style="width: 200px; height: 40px" name="rcar_no"
					onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
					
				取車門市：<select name="rcaro_pickuploc" value="" class="form-control " style="width: 200px; height: 40px; display:inline; text-align:center;">
				                       	 <option value="">請選擇門市</option>
				                         <option value="TPE" <c:if test="${rcaro_pickuploc eq 'TPE'}">selected</c:if> >台北</option>
				                         <option value="TC" <c:if test="${rcaro_pickuploc eq 'TC'}">selected</c:if> >台中</option>
				                      	 <option value="KH" <c:if test="${rcaro_pickuploc eq 'KH'}">selected</c:if> >高雄</option>
                  				  </select>
					



<!-- 				&emsp;&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;&emsp;&emsp;&emsp; -->
<!-- 				&emsp;&emsp;&emsp;&ensp;  -->
				
<!-- 				<input type="text" value="select" name="d" style="display:none;">  -->
				<input type="submit" value="查詢"> 
				<input type="hidden" value="getByCompositeQuery">




				<table border="1" cellspacing="0" cellpadding="0" border-collapse:
					collapse align="center"
					style="text-align: center; margin-top: 5px; margin-left: auto; margin-right: auto; width: 95%;"
					border="">

						<br><br>
						
		    <div class="col-lg-7">
              <span id="order_status0" class="mb-5" style="font-size: 1rem;"><a href="/CGA102G4/OrderSuccessList" style="display: inline;">訂單成立&ensp;</a></span>
              <span class="mb-5" style="font-size: 1rem;"> | </span>
              <span id="order_status1" class="mb-5" style="font-size: 1rem;"><a href="/CGA102G4/OutCarList" style="display: inline;">出車中&ensp;</a></span>
              <span class="mb-5" style="font-size: 1rem;"> | </span>
              <span id="order_status2" class="mb-5" style="font-size: 1rem;"><a href="/CGA102G4/CloseOrderList" style="display: inline;">已結案&ensp;</a></span>
              <span class="mb-5" style="font-size: 1rem;"> | </span>
              <span id="order_status3" class="mb-5" style="font-size: 1rem;"><a href="/CGA102G4/OpenOrderList" style="display: inline;">未結案&ensp;</a></span>
              <span class="mb-5" style="font-size: 1rem;"> | </span>
              <span id="order_status4" class="mb-5" style="font-size: 1rem;"><a href="/CGA102G4/CancelOrderList" style="display: inline;">已取消&ensp;</a></span>
            </div>


						
					<thead>
						<tr style="text-align: center;">
							<th>訂單編號</th>
							<th>會員編號</th>
							<th>取車門市</th>
							<th>起租時間</th>
							<th>歸還時間</th>
<!-- 							<th>車輛級距</th> -->
							<th>訂單狀態</th>
							<th>車型</th>
							<th>車號</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="set" begin="1" end="1" step="1"> 
						
						<c:forEach var="rcarOrderVO" varStatus="setClass" items="${getByCompositeQuery}">
						<tr >
							<!--用varStatus.count計次放進class順序 -->
							<td class="transfer" id="rcaro_no"  style="height: 40px">${rcarOrderVO.rcaro_no}</td>
							<td class="transfer">${rcarOrderVO.meb_no}</td>
							<td><c:if test = "${rcarOrderVO.rcaro_pickuploc eq 'TPE'}">台北</c:if> 
									<c:if test = "${rcarOrderVO.rcaro_pickuploc eq 'TC'}">台中</c:if> 
                       				<c:if test = "${rcarOrderVO.rcaro_pickuploc eq 'KH'}">高雄</c:if></td>
							<td class="transfer"><fmt:formatDate value="${rcarOrderVO.rcaro_ppicktime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td class="transfer"><fmt:formatDate value="${rcarOrderVO.rcaro_pprettime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<c:if test="${rcarOrderVO.rcaro_status eq '0'}"><td class="transfer">訂單成立</td></c:if>
							<c:if test="${rcarOrderVO.rcaro_status eq '1'}"><td class="transfer">出車中</td></c:if>
							<c:if test="${rcarOrderVO.rcaro_status eq '2'}"><td class="transfer">已結案</td></c:if>
							<c:if test="${rcarOrderVO.rcaro_status eq '3'}"><td class="transfer">未結案</td></c:if>
							<c:if test="${rcarOrderVO.rcaro_status eq '4'}"><td class="transfer">已取消</td></c:if>
							<td class="transfer">${rcarOrderVO.model_no}</td>
							<td class="transfer">${rcarOrderVO.rcar_no}</td>
							<c:if test="${rcarOrderVO.rcaro_status eq '0'}"><td><input type="button" class="btn" value="取消訂單"></td></c:if>
							<c:if test="${rcarOrderVO.rcaro_status != '0'}"><td></td></c:if>
						</tr>
						</c:forEach>
					</c:forEach>
					</tbody>
				</table>

			</form>


	




<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/Rcar_Order/js/tableLink.js"></script>


 




			<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

			<%
			java.sql.Timestamp ro_time = null;
			try {
				ro_time = rcarOrderVO.getRcaro_time();
			} catch (Exception e) {
				ro_time = new java.sql.Timestamp(System.currentTimeMillis());
			}
			%>
			<link rel="stylesheet" type="text/css"
				href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
			<script src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.js"></script>
			<script
				src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.full.js"></script>

			<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 150px; /* height:  151px; */
}
</style>

			<script>
        $.datetimepicker.setLocale('zh');
        $('.f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:00:00' ,         //format:'Y-m-d H:i:s',
				});

				// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

				//      1.以下為某一天之前的日期無法選擇
				const somedate1 = new Date('2020-01-01');
				$('#rost')
						.datetimepicker(
								{
									beforeShowDay : function(date) {
										if (date.getYear() < somedate1
												.getYear()
												|| (date.getYear() == somedate1
														.getYear() && date
														.getMonth() < somedate1
														.getMonth())
												|| (date.getYear() == somedate1
														.getYear()
														&& date.getMonth() == somedate1
																.getMonth() && date
														.getDate() < somedate1
														.getDate())) {
											return [ false, "" ]
										}
										return [ true, "" ];
									}
								});

			</script>








		</div>
	</main>
<!-- 	<!-- Modal --> 
<!-- <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true"> -->
<!--   <div class="modal-dialog"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5> -->
<!--         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         <div class="row g-2"> -->
<!-- 		  <div class="col-md"> -->
<!-- 		    <div class="form-floating"> -->
<!-- 		      <input type="text" class="form-control" id="floatingInputGrid" placeholder="name@example.com" value="90" disabled> -->
<!-- 		      <label for="floatingInputGrid">訂單編號</label> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		  <div class="col-md"> -->
<!-- 		    <div class="form-floating"> -->
<!-- 		      <input type="text" class="form-control" id="floatingInputGrid" placeholder="name@example.com" value="B123456789" disabled> -->
<!-- 		      <label for="floatingInputGrid">姓名</label> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		</div> -->
<!-- 		<div class="row g-2"> -->
<!-- 		  <div class="col-md"> -->
<!-- 		    <div class="form-floating"> -->
<!-- 		      <input type="text" class="form-control" id="floatingInputGrid" placeholder="name@example.com" value="0988888888"> -->
<!-- 		      <label for="floatingInputGrid">電話</label> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		  <div class="col-md"> -->
<!-- 		    <div class="form-floating"> -->
<!-- 		      <input type="text" class="form-control" id="floatingInputGrid" placeholder="name@example.com" value="RTP-0001"> -->
<!-- 		      <label for="floatingInputGrid">出租車號</label> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		</div> -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button> -->
<!--         <button type="button" class="btn btn-primary">修改</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
  <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
	<script
		src="<%=request.getContextPath()%>/resources/js/Background_Home.js"></script>
	<script src="<%=request.getContextPath()%>/backgroundHome.js"></script>
	<script>
		sessionStorage.setItem('employee', `${employeejson}`)
	</script>
	    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
</body>

</html>