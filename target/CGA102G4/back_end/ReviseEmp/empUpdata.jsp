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
        input {
            margin-top: 20px;
        }

        select {
            margin-top: 20px;
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
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home"
                        type="button" role="tab" aria-controls="home" aria-selected="true">會員資料</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile"
                        type="button" role="tab" aria-controls="profile" aria-selected="false">修改密碼</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div style="width: 60%; margin: 0 auto; margin-top: 10px;">
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="empNO" placeholder="員工編號" value="${employee.emp_no}"
                                        disabled>
                                    <label for="empNO">員工編號</label>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="empTitle" placeholder="職稱" value="${employee.emp_title}"
                                        disabled>
                                    <label for="empTitle">職稱</label>
                                </div>
                            </div>

                        </div>
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input id="empStNo" value="${employee.st_no}" style="display: none;">
                                    <c:forEach items="${dao.all}" var="store">
						            	 <c:if test="${store.st_no == employee.st_no}" >
						                 	<input type="text" class="form-control" id="storeName" placeholder="門市" value="${store.st_name}"
		                                        disabled>
		                                    <label for="storeName">門市</label>
						                 </c:if>
						            </c:forEach>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="empName" placeholder="員工姓名" value="${employee.emp_name}">
                                    <label for="empName">姓名</label>
                                </div>
                            </div>
                        </div>
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="date" class="form-control" id="empBir" placeholder="生日" value="${employee.emp_bir}">
                                    <label for="empBir">生日</label>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="empMail" placeholder="電子信箱"
                                        value="${employee.emp_mail}">
                                    <label for="empMail">電子信箱</label>
                                </div>
                            </div>
                        </div>
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="empAdrs" placeholder="地址"
                                        value="${employee.emp_adrs}">
                                    <label for="empAdrs">地址</label>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="empTel" placeholder="電話"
                                        value="${employee.emp_tel}">
                                    <label for="empTel">電話</label>
                                </div>
                            </div>
                        </div>
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <select class="form-select" id="gender" aria-label="Floating label select example">
                                        
                                        <c:if test="${employee.emp_gender == 0 }" >
						                 	<option value="0"  selected>女</option>
	                                        <option value="1">男</option>
	                                        <option value="2">其他</option>
						                 </c:if>
						                 <c:if test="${employee.emp_gender == 1 }" >
						                 	<option value="0">女</option>
	                                        <option value="1" selected>男</option>
	                                        <option value="2">其他</option>
						                 </c:if>
						                 <c:if test="${employee.emp_gender == 2 }" >
						                 	<option value="0">女</option>
	                                        <option value="1">男</option>
	                                        <option value="2" selected >其他</option>
						                 </c:if>
                                    </select>
                                    <label for="floatingSelectGrid">性別</label>
                                </div>
                            </div>
                            <div class="d-grid gap-2" style="margin-top: 15px;">
                                <button class="btn btn-primary" type="button" id="empSend">送出</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div style="width: 60%; margin: 0 auto; margin-top: 10px;">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="password" class="form-control" id="empPass" placeholder="密碼"
                                        value="">
                                    <label for="empPass">輸入舊密碼</label>
                                    <div  class="invalid-feedback">
                                        密碼錯誤! 請確認!
                                    </div>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="password" class="form-control" id="empNewPass" placeholder="密碼"
                                        value="">
                                    <label for="empPass">輸入新密碼</label>
                                    <div  class="invalid-feedback">
                                        不可與舊密碼相同!
                                    </div>
                                </div>
                                
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="password" class="form-control" id="empCheckPass" placeholder="密碼"
                                        value="">
                                    <label for="empPass">再次輸入新密碼</label>
                                    <div  class="invalid-feedback">
                                        密碼錯誤! 請確認!
                                    </div>
                                </div>
                            </div>
                            <div class="d-grid gap-2" style="margin-top: 15px;">
                                <button class="btn btn-primary" type="button" id="passSend">送出</button>
                            </div>
                        </div>

                    </div>
                </div>
            
        </div>
    </main>
     <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>
    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="empUpdata.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
</body>

</html>