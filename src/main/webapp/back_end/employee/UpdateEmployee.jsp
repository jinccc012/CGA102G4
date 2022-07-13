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
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.12.1/datatables.min.css" />

    <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.12.1/datatables.min.js"></script>
    
    <style>
        tr input {
            border: 0px !important;
            /* background-color: transparent !important; */
            color: black;
        }

        .adrstd,
        .emailtd {
            width: 20px;
        }

        input {
            border-radius: 5px;
        }
        /* add */
        .form-switch {
            width: 168px;
        }

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
    <!-- 左側功能欄位 -->
    <main class="main">
        <aside class="aside;">
            <nav class="nav-list1">
                <ul id="showFu">
                    
                </ul>
            </nav>
        </aside>

        <div class="content">
            <!-- <caption>修改員工資料 </caption> -->
            <div style="width: 99%;">
                <table class="table table-striped caption-top table-hover table-sm" id="empTable" >


                    <!-- 欄位 -->
                    <thead>
                        <tr class="field">
                            <th scope="col">欄位</th>
                            <th scope="col">員工編號</th>
                            <th scope="col">員工姓名</th>
                            <th scope="col">門市編號</th>
                            <th scope="col">電話</th>
                            <th scope="col">地址</th>
                            <th scope="col">電子郵件</th>
                            <th scope="col">職稱</th>
                            <th scope="col">員工狀態</th>
                            <th scope="col">權限設定</th>
                            <th scope="col">修改</th>
                        </tr>
                    </thead>


                    <tbody>
                        <!-- 預留動態生成 -->

                        <!-- 內容 -->
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
                            tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel">員工權限設定</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close" ></button>
                                    </div>
                                    <div class="modal-body" style="
                                        display: flex;
                                        flex-wrap: wrap;
                                        justify-content: space-between;
                                    ">
                                        <input type="text" id="empNo" style="display: none;">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">關閉</button>
                                        <button type="button" class="btn btn-primary " id="send">設定完成</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
     <%-- 提示彈穿 --%>
    <div id="notice" class="toast-container position-absolute bottom-0 end-0 p-3 " style="z-index: 7;">
        
    </div>

    <script src="<%=request.getContextPath() %>/resources/js/Background_Home.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/getEmpPr.js"></script>
    <script src="UpdateEmployee.js"></script>
    <script>
        
       sessionStorage.setItem('employee',`${employeejson}`)
        
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/websocket.js"></script>
</body>

</html>