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

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.12.1/datatables.min.css" />

    <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.12.1/datatables.min.js"></script>

    
    <style>
        td,
        th {
            white-space: nowrap;
            text-align: center;
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
                    <button class="nav-link active" id="drRecord-tab" data-bs-toggle="tab" data-bs-target="#drRecord"
                        type="button" role="tab" aria-controls="drRecord" aria-selected="true">調度紀錄</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="drApply-tab" data-bs-toggle="tab" data-bs-target="#drApply"
                        type="button" role="tab" aria-controls="drApply" aria-selected="false">調度申請</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="applyrecord-tab" data-bs-toggle="tab" data-bs-target="#applyrecord"
                        type="button" role="tab" aria-controls="applyrecord" aria-selected="false">調度申請紀錄</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent" style="width: 99%;">
                <div class="tab-pane fade show active" id="drRecord" role="tabpanel" aria-labelledby="home-tab">
                    <!-- ////////////////////////// -->
                    <table class="table table-striped " id="drRecordTab">
                        <thead>
                            <tr>
                                <th scope="col">調度單號</th>
                                <th scope="col">申請門市</th>
                                <th scope="col">申請人</th>
                                <th scope="col">核准人</th>
                                <th scope="col">車牌號碼</th>
                                <th scope="col">取車時間</th>
                                <th scope="col">實際取車時間</th>
                                <th scope="col">實際還車時間</th>
                                <th scope="col">里程</th>
                                <th scope="col">還車里程</th>
                                <th scope="col">狀態</th>
                                <th scope="col">送出</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <!-- ///////////////////////////// -->
                <div class="tab-pane fade" id="drApply" role="tabpanel" aria-labelledby="profile-tab">
                    <!-- /////////////////// -->
                    <div class="row g-3" style="width:700px; margin: 0 auto; margin-top: 50px;">
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="applyEmp"  disabled>
                                    <label for="applyEmp">申請人</label>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="applySt" value="台中站" disabled>
                                    <label for="applySt">申請門市</label>
                                </div>
                            </div>
                        </div>
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <select class="form-select" id="approveSt"
                                        aria-label="Floating label select example">
                                        <option selected>門市</option>
                                    </select>
                                    <label for="approveSt">調度門市</label>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="datetime-local" class="form-control" id="drStartTime"
                                        placeholder="取車時間">
                                    <label for="drStartTime">取車日期</label>
                                </div>
                            </div>
                        </div>
                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="drRcar" placeholder="調度車輛">
                                    <label for="drRcar">調度車輛</label>
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-outline-primary"
                            style="width: 500px; margin: 0 auto; margin-top: 40px;">送出申請</button>
                    </div>
                </div>
                <!-- ///////////////////////////////////// -->
                <div class="tab-pane fade" id="applyrecord" role="tabpanel" aria-labelledby="contact-tab">
                    <!-- ///////////////////////////////////// -->
                    <table class="table table-striped " id="applyrecordTab">
                        <thead>
                            <tr>
                                <th scope="col" id="name">調度單號</th>
                                <th scope="col">調度門市</th>
                                <th scope="col">申請人</th>
                                <th scope="col">核准人</th>
                                <th scope="col">車牌號碼</th>
                                <th scope="col">取車時間</th>
                                <th scope="col">實際取車時間</th>
                                <th scope="col">實際還車時間</th>
                                <th scope="col">里程</th>
                                <th scope="col">還車里程</th>
                                <th scope="col">狀態</th>
                                <th scope="col">調度</th>
                                <th scope="col">還車</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>

            
        </div>
    </main>
    <!-- 還車視窗 -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">還車作業</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="drNO" placeholder="調度編號">
                        <label for="drNO">調度編號</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="drRcarNo" placeholder="調度車號">
                        <label for="drRcarNo">調度車號</label>
                    </div>
                    <div class="form-floating mb-3" >
                        <input type="text" class="form-control" id="drMiles" placeholder="還車里程">
                        <label for="drMiles">還車里程</label>
                    </div> 
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary send " data-bs-dismiss="modal">送出</button>
                </div>
            </div>
        </div>
    </div>

    <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>

    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="cardispatchrecord.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
</body>

</html>