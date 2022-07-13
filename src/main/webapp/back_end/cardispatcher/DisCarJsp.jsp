<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rcarorder.model.*"%>
<%@ page import="com.rcarorder.controller.*"%>
<%@ page import="com.rcar.model.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import= "java.text.SimpleDateFormat"%>
<%
  RcarOrderService rcoSvc = new RcarOrderService();
  List<RcarOrderVO> list= rcoSvc.getAll();
  Collections.reverse(list);
  pageContext.setAttribute("list",list);
  RcarService rSvc= new RcarService();
  List<RcarVO> cars= rSvc.getAll();
  
  DisCarService disCarSvc = new DisCarService();
  List<String> rentableCar= new ArrayList<String>();
%>

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
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<%--     <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css"> <!-- 測試用路徑 進專案要改 --> --%>
    <script src="<%=request.getContextPath() %>/resources/js/bootstrap.bundle.js"></script> <!-- 測試用路徑 進專案要改 -->
<style>
* {
    box-sizing: border-box;
    font-weight: bold;
    font-family: 'Montserrat', "Microsoft Yahei", "Noto Sans CJK SC", 'Noto Sans TC', "Droid Sans Fallback";
}

/* @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css); */
body {
    margin: 0;
}

html {
    /*根元素*/
    /* font-size: 62.5%;  =10px */
    --header-height: 60px;
    --asidr-width: 260px;
}

.header {
    display: flex;
    justify-content: space-between;
    background-color: #A0c1d2;
    height: var(--header-height);
    align-items: center;
}

h1 {
    padding-left: 15px;
    font-size: 25px;
    margin: 0px;
    line-height: var(--header-height);
}

.store {
    width: 200px;
    justify-content: space-between;
    align-items: baseline;
}

aside {
    background-color: rgba(160, 193, 210, 0.6);
    font-size: 21px;
    width: var(--asidr-width);
    height: calc(100vh - var(--header-height));
    /*公式調整,建議不寫死*/
    overflow-y: auto;
    padding: 5px 5px;
 
}
/* ::-webkit-scrollbar {
    display: none;
    } */

.div2 {
    font-size: 18px;
}

.content {
    border: 3px solid rgba(200, 203, 216, 1);
    background-color: whitesmoke;
    width: calc(100% - var(--asidr-width));
    height: calc(100vh - var(--header-height));
    overflow-y: auto;
}

main aside  a {
    padding-left: 10px;
    text-decoration: none;
    width: 100%;
    display: inline-block;
    color: black;
    margin-top: 5PX;
}

main a:hover {
    background-color: rgba(97, 140, 172, 0.5);
}

/* 6/25改動 aside ul 配合調度 */
aside ul {
    list-style: none;
    padding: 5px;
}

/* 6/25改動 aside li 配合調度 */
aside li { 
    margin-bottom: 10px;
    cursor: pointer;
}

main {
    display: flex;
}

.store i {
    /* display: inline-block; */
    margin-left: 10px;
    cursor: pointer;
}
</style>
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
        	   <%@ include file="pageSetting" %>
         <%
                   int pageNum=pageIndex+rowsPerPage;
                   if(pageNum>=list.size()){
                	   pageNum=list.size();
                   } 
               %>
        <%
          for(int i=pageIndex;i<pageNum;i++){
      	    for(int j=0; j<cars.size();j++){
      		    if(list.get(i).getModel_no().equals(cars.get(j).getModel_no())){
      			    rentableCar.add(cars.get(j).getRcar_no());
      		    }
      	    }
          }
        %> 
        <div class="col-8" style="margin: 0 auto; margin-top: 10%;">
            <P></P>
            <div class="accordion" id="accordionExample">
               <% for(int i=pageIndex;i<pageNum;i++) {%>
                <div class="accordion-item">
                    <h2 class="accordion-header" id=<%= "heading"+i%>>
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target=<%= "#collapse"+i%> aria-expanded="false" aria-controls=<%= "collapse"+i%>>
                                                                                                              訂單編號：<%= list.get(i).getRcaro_no() %>&nbsp;&nbsp;訂單成立日期：<% SimpleDateFormat sdatef = new SimpleDateFormat("yyyy-MM-dd");%><%= sdatef.format(list.get(i).getRcaro_time()) %>&nbsp;&nbsp;
                            <label id=<%= "orderstate"+i%>> <%= list.get(i).getRcar_no()==null ? "未處理" :"已處理"%></label>
                        </button>
                    </h2>                  
                     <div id=<%= "collapse"+i%> class="accordion-collapse collapse" aria-labelledby=<%= "heading"+i%> data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                         <FORM METHOD="post" ACTION="/CGA102G4/disCar.do" style="margin-bottom: 0px;">
                            
                                <div class="col-md-4">
                                    <label class="form-label">會員編號：</label>
                                    <label class="form-label"><%= list.get(i).getMeb_no()%></label>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">姓名：</label>
                                    <label for="inputPassword4" class="form-label"><%= list.get(i).getLessee_name()%></label>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">電話：</label>
                                    <label class="form-label">0912-345-678</label>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">預計取車時間：</label>
                                    <label class="form-label"><%= sdatef.format(list.get(i).getRcaro_ppicktime()) %></label>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">預計還車時間：</label>
                                    <label for="inputPassword4" class="form-label"><%= sdatef.format(list.get(i).getRcaro_pprettime()) %></label>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">預計還車地點：</label>
                                    <label class="form-label"><%= list.get(i).getRcaro_pickuploc()%></label>
                                </div>
                               
                                  <div class="col-md-5">
                                    <label for="inputStateOne" class="form-label">配給車輛</label>
                                    <label id="orderOneCar" for="inputStateOne" class="form-label"><%= list.get(i).getRcar_no()==null ? "未配車" : list.get(i).getRcar_no()%></label>
                                    <select id="inputStateOne" name="rcarno" class="form-select">
                                        <% 
                                           rentableCar=null;
                                           rentableCar=disCarSvc.rentableCar(list.get(i).getRcaro_no()); 
                                        %>
                                        <% for(int j=0;j<rentableCar.size();j++){%>
                                             <option><%= rentableCar.get(j) %></option>
                                        <% }; %>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <p></p>
                                      <input type="submit" class="btn btn-primary" value="確認">
                                      <input type="hidden" name="rcarO"  value="<%= list.get(i).getRcaro_no() %>">
                                      
                                </FORM>
                                
                                </div>
                        </div>
                        
                    </div>
                </div>
               <%} %>
            </div>

        <%@ include file="pages" %>
        </div>
    </main>
    
    <%-- 提示彈穿 --%>
    <div id="notice">
        
    </div>
    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
</body>

</html>