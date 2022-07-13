<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<div class="col-8" style="margin: 0 auto; margin-top: 10%;">
                <div class="row">
                    <div class="col-md-2">
                        <label for="formGroupExampleInput2" class="form-label">車牌號碼</label>
                        <input type="text" id="rcarno" class="form-control" placeholder="XXX-XXXX"
                            aria-label="rcarno">
                    </div>
                    <div class="col-md-2">
                        <label for="formGroupExampleInput2" class="form-label">所屬門市</label>
                        <select class="form-select" id="store">
                            <option selected value="TPE">台北站</option>
                            <option value="KH">高雄站</option>
                            <option value="TC">台中站</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label for="formGroupExampleInput2" class="form-label">車型</label>
                        <select class="form-select" id="model">
                            <option selected>ALTIS</option>
                            <option>COLTPLUS</option>
                            <option>CX-5</option>
                            <option>FOCUS</option>
                            <option>KUGA</option>
                            <option>RAV4</option>
                            <option>SENTRA</option>
                            <option>Starex</option>
                            <option>TIIDA</option>
                            <option>YARIS</option>
                            <option>旅行家</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label for="formGroupExampleInput2" class="form-label">里程</label>
                        <input type="text" id="miles" value="0" aria-label="miles">
                    </div>
                </div>
                <div class="mb-3">

                </div>
                <button type="button" class="btn btn-primary" onclick="insert()">新增車輛</button>
            </div>
            
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
        function insert() {
            console.log(document.getElementById('rcarno').value);
            console.log(document.getElementById('store').value);
            console.log(document.getElementById('model').value);
            console.log(document.getElementById('miles').value);
            $.ajax({
                method: 'post',
                url: '../../back_end/rcar.do',
                dataType: 'json',
                data: {
                    rcarno: document.getElementById('rcarno').value,
                    storeno:document.getElementById('store').value,
                    modelno:document.getElementById('model').value,
                    miles:document.getElementById('miles').value,
                    action: 'insert'
                },
                success: function () {
                    console.log('ggg');
                    window.location.replace("rcar.jsp");
                }
            })
        }

    </script>
</body>

</html>