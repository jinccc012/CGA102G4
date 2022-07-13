<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.QAService"%>
<%@ page import="com.qa.model.QAVO"%>
<%
    QAVO qaVO =(QAVO) request.getAttribute("qaVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="icon" href="icon/pngkey.com-gps-icon-png-5131691.png" type="image/x-icon" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Family Rent || 後台管理系統</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Koulen&family=Teko:wght@300&display=swap');
    </style>
<!--     <style> -->
/*         * { */
/*             box-sizing: border-box; */
/*             /* font-family: 思源黑體 !important; */ */
/*             font-weight: bold; */
/*             font-family: 'Montserrat', "Microsoft Yahei", "Noto Sans CJK SC", 'Noto Sans TC', "Droid Sans Fallback"; */
/*         } */

/*         /* @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css); */ */
/*         body { */
/*             font-family: 'Noto Sans TC', sans-serif !important; */
/*         } */

/*         html { */
/*             /*根元素*/ */
/*             /* font-size: 62.5%;  =10px */ */
/*             --header-height: 60px; */
/*             --asidr-width: 240px; */
/*         } */

/*         body { */
/*             max-width: 100%; */
/*             margin: 0 auto; */
/*             box-sizing: border-box; */
/*         } */

/*         header { */
/*             display: flex; */
/*             justify-content: space-between; */
/*             background-color: #A0c1d2; */
/*         } */

/*         h1 { */
/*             font-size: 25px; */
/*         } */

/*         .store { */
/*             display: flex; */
/*             width: 15%; */
/*             justify-content: space-between; */
/*         } */

/*         aside { */
/*             background-color: rgba(160, 193, 210, 0.6); */
/*             font-size: 20px; */
/*             position: fixed; */
/*             width: var(--asidr-width); */
/*             left: 0; */
/*             top: var(--header-height); */
/*             height: calc(100% - var(--header-height)); */
/*             /*公式調整,建議不寫死*/ */
/*             overflow-y: auto; */
/*             padding: 5px 15px; */
/*         } */

/*         main { */
/*             border: 3px solid whitesmoke; */
/*             height: 50%; */
/*             background-color: rgba(160, 193, 210, 0.4); */
/*             width: calc(100% - var(--asidr-width)); */
/*             margin-left: var(--asidr-width); */
/*             min-height: calc(100vh - var(--header-height)); */

/*             padding: 0px; */
/*         } */

/*         ul { */
/*             list-style: none; */
/*             padding: 5px; */

/*         } */

/*         li { */
/*             cursor: pointer; */
/*         } */
<!--     </style> -->
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
        <FORM METHOD="post" ACTION="../../back_end/qa.do" name="form">
          <div class="col-8" style="margin: 0 auto; margin-top: 10%;">
            <div class="row">
              <div class="col">
                <label for="formGroupExampleInput2" class="form-label">請輸入標題</label>
                <input type="text"name="title" class="form-control"  placeholder="標題" aria-label="Title">
              </div>
              <div class="col-md-2">
                <label for="formGroupExampleInput2" class="form-label">請輸入Tag</label>
                <input type="text" class="form-control" name="tag" placeholder="Tag" aria-label="Tag">
              </div>
            </div>
          <div class="mb-3">
            <label for="formGroupExampleIn put2" class="form-label">請輸入內容</label>
            <textarea name="editor"></textarea><br/>
           </div>
          <input type="hidden" name="action" value="insert"> 
           <input type="submit" id="setData" name="setData" value="新增資料">
          </div> 
          </FORM>
         </div> 
        
    </main>

    <script>
        $('.lss').click(function (e) {
            const target = e.target;
            if (target.classList.contains('lss')) {
                $(this).children('div.div2').slideToggle(500);
            }
        })
    </script>
    <script src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>  
    <script>  
      CKEDITOR.replace('editor');  
      function getData() {  
          //Get data written in first Editor   
          var editor_data = CKEDITOR.instances['editor'].getData();  
          
      }  
</script>  
</body>

</html>