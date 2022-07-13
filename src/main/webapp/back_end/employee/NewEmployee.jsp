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

    <style>
        div.SignUp {
            /* background-color: rgba(160, 193, 210, 0.5); */
            border-radius: 5px;
        }

        button {
            border: 0;
            background-color: rgba(0, 95, 40, 0.6);
            color: #fff;
            border-radius: 3px;
            cursor: pointer;
            font-weight: bold;
        }

        .mb-3 {
            font-size: 35px;
            font-weight: bold;
        }

        .err {
        border: 2px solid red;
        }

        .ok {
        border: 2px solid green;
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
            <div class="SignUp row gx-5" style="width:100% ;height: 100%; margin:0;padding:0 100px 100px 100px;">
                <h1 class="mb-3" style="display:flex;justify-content:center ">新增員工資料</h1>
                <div class="needs-validation" style="display: flex;flex-wrap: wrap;justify-content: space-between;">
                    <!-- 姓名!!!! -->
                    <div class="name" style="width:45%;">
                        <label for="Name1">姓名:</label>
                        <input type="text" class="form-control" id="Name1" placeholder="請輸入姓名" value="" required>

                    </div>
                    <!-- 員工編號!!! -->
                    <div class="ID" style="width:45%;">
                        <label for="id">員工編號:</label>
                        <input type="text" class="form-control" id="id" placeholder="請輸入員工編號" value="" required>
                        <div id="idhelp" class="form-text">員工編號只能是數字</div>
                    </div>
                    <!-- 生日 -->
                    <div class="BD" style="width:45% ;">
                        <label for="bd1">生日:</label>
                        <input type="date" class="form-control" id="bd1" placeholder="" value="" required>
                    </div>
                    <!-- 性別 -->
                    <div class="gender1" style="width:45%;">
                        <div class="mb-4" style="height:58px;">
                            <label for="ID2">性別:</label>
                            <select class="form-contro1" aria-label=".form-select-lg example" id="gender"
                                style="width: 100%;height: 70%;/* display: block; */">
                                <option class="grndert">請選擇</option>
                                <option value="0">女</option>
                                <option value="1">男</option>
                                <option value="2">其他</option>
                            </select>
                        </div>
                    </div>
                    <!-- 您的設定密碼 -->
                    <div class="password1" style="width:45%;">
                        <label for="password">您的設定密碼:</label>
                        <input type="password" class="form-control" id="password" placeholder="請輸入密碼" value="" required>
                        <div id="passhelp" class="form-text">密碼長度請填寫6～12</div>
                    </div>
                    <!-- 門市編號 -->
                    <div class="storenumber" aria-label=".form-select-lg example" style="width:45%;height: 58px;">
                        <label for="str2">門市編號:</label>
                        <select class="form-contro1" id="store1" style="width: 100%;height: 70%;/* display: block; */">
                            <option class="str1">請選擇</option>
                            <c:forEach items="${dao.all}" var="store">
                                <option value="${store.st_no}">${store.st_name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- 信箱 -->
                    <div class="email1" style="width:45%;">
                        <label for="email">電子信箱:</label>
                        <input type="text" class="form-control" id="email" placeholder="請填寫E-mail" value="" required>
                    </div>
                    <!-- 手機號碼 -->
                    <div class="phone1" style="width:45%;">
                        <label for="phone">行動電話:</label>
                        <input type="text" class="form-control" id="phone" placeholder="請輸入手機號碼" value="" required>
                    </div>
                    <!-- 通訊地址 -->
                    <label for="city" style="width:45% ;">通訊地址
                        <div id="twzipcode"></div>
                        <input type="text" class="form-control" id="street" name="street" maxlength="30"
                            placeholder="詳細地址">
                    </label>
                    <!-- 職稱 -->
                    <div style="width: 45%;">
                        <br>
                        <div class="title">
                            <label for="tit0">員工職稱:</label>
                            <input type="text" class="form-control" id="tit1" placeholder="請輸入職稱" value="" required>
                        </div>
                    </div>

                    <!-- button -->
                    <div class="button1" style="width:100%">
                        <button class="button3" style="width:100%;height:50px;">創建員工資料</button>
                    </div>
                </div>
            </div>
            
        </div>
    </main>
      <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
    
    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/jquery.twzipcode.js"></script>
    <script src="<%=request.getContextPath() %>/back_end/employee/NewEmployee.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
</body>

</html>