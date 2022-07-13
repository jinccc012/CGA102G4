<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.StoreService" %>
<%@ page import="com.store.model.StoreVO" %>
<%@ page import="com.rcar.model.RcarService" %>
<%@ page import="com.rcar.model.RcarVO" %>
<!DOCTYPE html>
<html lang="tw">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=request.getContextPath() %>/resources/icon/pngkey.com-gps-icon-png-5131691.png"
        type="image/x-icon" />
    <title>Family Rent || 後台管理系統</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/1d9dcf12aa.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css">
    <!-- 測試用路徑 進專案要改 -->
    <script src="<%=request.getContextPath() %>/resources/js/bootstrap.bundle.js"></script>
    <!-- 測試用路徑 進專案要改 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/Background_Home.css">
    <!-- 首頁CSS 測試用路徑 進專案要改 -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.12.1/datatables.min.css" />
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.12.1/datatables.min.js"></script>
</head>

<body>
    <jsp:useBean id="dao" class="com.store.model.StoreService" />
    <jsp:useBean id="cardao" class="com.rcar.model.RcarService" />
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
            <li>${employee.emp_name}<a href="<%=request.getContextPath() %>/EmployeeLogin" style="display: inline;"><i
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
            <div id="show" style="width: 98%; margin: 0 auto;">
                <table class="table table-striped " id="carcontrol" >
                    <thead>
                        <tr>
                            <th scope="col">車牌</th>
                            <th scope="col">車籍門市</th>
                            <th scope="col">車型編號</th>
                            <th scope="col">車輛所在地</th>
                            <th scope="col">里程</th>
                            <th scope="col">車輛狀態</th>
                            <th scope="col">修改</th>
                            <!-- <th scope="col">實際還車時間</th>
                                <th scope="col">里程</th>
                                <th scope="col">還車里程</th>
                                <th scope="col">狀態</th>
                                <th scope="col">送出</th>  -->
                        </tr>
                    </thead>
                        <c:forEach items="${car}" var="car">
                            <tr>
                                <th scope="row">${car.rcar_no}</th>
                                <c:forEach items="${dao.all}" var="store">
                                    <c:if test="${store.st_no == car.st_no}">
                                        <td >${store.st_name} <input class="stName" value="${car.st_no}" style="display: none;"></td>
                                    </c:if>
                                </c:forEach>
                                <td class="modelNo">${car.model_no}</td>
                                <c:forEach items="${dao.all}" var="store">
                                    <c:if test="${store.st_no == car.rcar_loc}">
                                        <td >${store.st_name}<input class="rcarLoc" value="${car.rcar_loc}" style="display: none;"></td>
                                    </c:if>
                                </c:forEach>
                                <td class="miles">${car.miles}</td>
                                <td><input type="text" name="" class="carstatus" id="${car.rcar_no}" value="${car.rcar_status}" style="display: none;">
                                    <c:if test="${car.rcar_status == 0}">
                                        報廢
                                    </c:if>

                                    <c:if test="${car.rcar_status == 1}">
                                        空車
                                    </c:if>

                                    <c:if test="${car.rcar_status == 2}">
                                        出車
                                    </c:if>

                                    <c:if test="${car.rcar_status == 3}">
                                        還車
                                    </c:if>

                                    <c:if test="${car.rcar_status == 4}">
                                        維修
                                    </c:if>

                                    <c:if test="${car.rcar_status == 5}">
                                        中古車
                                    </c:if>
                                    </td>
                                <td>
                                    <c:if test="${car.rcar_status != 5}">
                                        <button type="button" class="btn btn-outline-secondary btn-sm modify" data-bs-toggle="modal" data-bs-target="#exampleModal">修改</button>
                                    </c:if>
                                    
                                </td>
                            </tr>  
                        </c:forEach>
                        
                    <tbody>
                    </tbody>
                </table>
            </div>

        </div>
    </main>
    <!-- 彈跳視窗 -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">修改里程狀態</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="miles" placeholder="里程數">
                    <label for="miles">里程數</label>
                </div>
                <div class="form-floating">
                    <div class="form-floating">
                        <select class="form-select" id="status" aria-label="Floating label select example">
                            <option  value="0">報廢</option>
                            <option value="1">空車</option>
<!--                             <option value="2">出車</option> -->
<!--                             <option value="3">還車</option> -->
                            <option selected value="4">維修</option>
                        </select>
                        <label for="status">車輛狀態</label>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
              <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="checkmodify">送出修改</button>
            </div>
          </div>
        </div>
      </div>
      <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script src="<%=request.getContextPath() %>/back_end/storecarcontrol/storecarcontrol.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>

        sessionStorage.setItem('employee', `${employeejson}`)

    </script>
     <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
     
	 <script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
</body>

</html>