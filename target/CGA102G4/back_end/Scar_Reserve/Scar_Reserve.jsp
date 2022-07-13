<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.scar_reserve.model.Scar_ReserveService"%>
<%@ page import="com.scar_reserve.model.Scar_ReserveVO"%>
<%@ page import="utils.jdbcUtilScarReserve"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%Scar_ReserveVO scar_ReserveVO = (Scar_ReserveVO) request.getAttribute("scar_ReserveVO");%>
<%@ page import="com.store.model.StoreService"%>
<%@ page import="com.store.model.StoreVO"%>

	<!-- ============================================================後台版型============================================================ -->

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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/back_end/Scar_Reserve/css/scarReserve.css"> <!-- 首頁CSS 測試用路徑 進專案要改 -->
	
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

	<%
    // 	Scar_ReserveService svc = new Scar_ReserveService();
    // 	List<Scar_ReserveVO> list = svc.getAll();
        %>
    
    
        <!-- **********錯誤訊息發送********** -->
        <c:forEach var="errorMsg" items="${requestScope.errorMsgs}">
            <p style="font-size: 30px; color: red">${errorMsg}</p>
        </c:forEach>
        <!-- **********錯誤訊息發送********** -->
        
<!--      <div class="content" >    -->
    <h2 class="mb-8" style="text-align: center;">中古車預約管理</h2>
    
        <form method="post"
            action="<%=request.getContextPath()%>/scar_ReserveServlet">
            
<!--                     <div class="container">  -->
<!--                     <div class="row " > -->
<!--                                 <div class="col-md-2"> -->
    		預約時間：<input name="sr_start_time" class="f_date1" id="srt1" type="text" style="width: 200px; height: 40px" placeholder="請選擇日期、時間">
    		&emsp;&emsp;～&emsp;&emsp;<input	name="sr_end_time" class="f_date1" id="srt2" type="text" style="width: 200px; height: 40px" placeholder="請選擇日期、時間"> 
    			 <input type="hidden" name="action" value="getByComposite">
<!--                     </div>  -->
<!--                        <div class="col-md-2"> -->
     <br>
            預約編號：<input type="search" placeholder="請輸入預約編號" style="width: 200px; height: 40px" name="sr_no"
     onkeyup="value=value.replace(/[^\d]/g,'')"> 
<!--                      </div> -->
<!--                                 <div class="col-md-2"> -->
            會員編號：<input type="search" placeholder="請輸入會員編號" style="width: 200px; height: 40px" name="meb_no"
            onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
<!--                      </div> -->
<!--                                                      <div class="col-md-2"> -->
      <br>      引擎編號：<input type="search" placeholder="請輸入中古車編號" style="width: 200px; height: 40px" name="scar_no"
      onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"> 
<!--                       </div> -->
<!--                       <div class="col-md-2"> -->
    <!-- 		門市編號：<input type="search" placeholder="請輸入門市編號" style="width: 200px; height: 20px" name="st_no"
    onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">  -->
                    門市選擇：<select name="st_no" value="" class="form-control " style="width: 200px; height: 40px; display:inline; text-align:center;">
                        <option value="">請選擇門市</option>
                         <option value="TPE" <c:if test="${st_no  eq 'TPE'}">selected</c:if> >台北</option>
                         <option value="TC" <c:if test="${st_no eq 'TC'}">selected</c:if> >台中</option>
                       <option value="KH" <c:if test="${ st_no eq 'KH'}">selected</c:if> >高雄</option>
                    </select>
<!--                 </div> -->
<!--                 <div class="col-md-2"> -->
                <input type="submit" value="查詢"> <input type="hidden" name="action" value="getByComposite">
<!--      </div> -->
<!--     </div> -->
<!--     </div> -->
    
            <table border="1" cellspacing="0" cellpadding="0" border-collapse: collapse align="center" 
            style="text-align: center; margin-top: 5px;margin-left:auto;margin-right:auto;width:95%;" border=""
            >
            <br>	
<%--                 <%@ include file="page1.file"%> --%>
                <thead>
                  <tr style="text-align: center;">
                    <th>預約編號</th>
                    <th>會員編號</th>
                    <th>中古車編號</th>
                    <th>門市編號</th>
                    <th>預約時間</th>
                  </tr>
                </thead>
    
                <c:forEach var="scar_ReserveVO"	items="${getByComposite}">
                    <tr>
                        <td style="height:40px">${scar_ReserveVO.sr_no}</td>
                        <td>${scar_ReserveVO.meb_no}</td>
                        <td>${scar_ReserveVO.scar_no}</td>
				 <td><c:if test = "${scar_ReserveVO.st_no eq 'TPE'}">台北</c:if> 
				<c:if test = "${scar_ReserveVO.st_no eq 'TC'}">台中</c:if> 
                       <c:if test = "${scar_ReserveVO.st_no eq 'KH'}">高雄</c:if> 
				
				</td>
                        <td><fmt:formatDate value="${scar_ReserveVO.sr_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
                    </tr>
                </c:forEach>
    
    
    
    
    
    
    
            </table>
    
        </form>
    
<%--         <%@ include file="page2.file"%> --%>
    	    <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
    
        <%
        java.sql.Timestamp sr_time = null;
        try {
            sr_time = scar_ReserveVO.getSr_time();
        } catch (Exception e) {
            sr_time = new java.sql.Timestamp(System.currentTimeMillis());
        }
        %>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
        <script src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.js"></script>
        <script	src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.full.js"></script>
    
    <style>
      .xdsoft_datetimepicker .xdsoft_datepicker {
               width:  300px; 
      }
      .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
               height: 150px;   
      }
    </style>
    
        <script>
        $.datetimepicker.setLocale('zh');
        $('.f_date1').datetimepicker({
           theme: '',              // 背景色
           timepicker:true,       // 開啟時分秒
           step: 60,                // (這是timepicker的預設間隔60分鐘)
           format:'Y-m-d H:00:00' ,     
<%--            value: '<%=sr_time%>',  // value:   new Date(), --%>
            });
    
            // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------
    
            //      以下為某一天之前的日期無法選擇
                 const somedate1 = new Date('2020-01-01');
                 $('#srt1').datetimepicker({
                     beforeShowDay: function(date) {
                         if (  date.getYear() <  somedate1.getYear() || 
                               (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
                               (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                         ) {
                              return [false, ""]
                         }
                         return [true, ""];
                 }});
        </script>
            
        </div>
    </main>
    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
</body>

</html>