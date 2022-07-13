<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
	<%@ page import="com.bidding.model.BiddingVO"%>
<%@ page import="com.scar.model.ScarVO"%>
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

    

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

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
		
		<h2 class="mb-8" style="text-align: center;">中古車得標管理</h2>
		<c:if test="${not empty requestScope.message}">
			<p style="color: red; text-align: center;">${requestScope.message}</p>
		</c:if>
		<div id="message" style="color:red;text-align: center;"></div>
		<!-- <div class="container" style="width: 800px; height: 800px;"> -->

		<table class="table table-hover"
			style="margin-left: auto; margin-right: auto; width: 95%;text-align: center;">
			<thead>
			  <tr class="table-success" style="text-align: center;">
			  		<th scope="col">門市</th>
                    <th scope="col">得標編號</th>
                    <th scope="col">車型</th>
                    <th scope="col">得標會員</th>
                    <th scope="col">競標價</th>
                    <th scope="col">得標日期</th>
                    <th scope="col">得標順位</th>
                    <th scope="col">付款狀態</th>
                    <th scope="col">操作</th>
					<!-- <th scope="col">查看場次</th> -->
				</tr>
			</thead>
			<tbody id="sbtbody">
				<c:forEach items="${requestScope.sblist}" var="sb">
						<tr id="${sb.scar_no}">
						<td>
						<c:if test="${sb.scarVO.st_no eq 'KH'}">高雄</c:if>
						<c:if test="${sb.scarVO.st_no eq 'TPE'}">台北</c:if>
						<c:if test="${sb.scarVO.st_no eq 'TC'}">台中</c:if>
						</td>
						  <td >${sb.sb_no}</td>
						 <td>${sb.scarVO.scar_model}</td>
                        <td>${sb.memberVO.meb_name}</td>
						<td><fmt:formatNumber value="${sb.sb_price}" pattern="#,###"/></td>

						
						<td><fmt:formatDate value="${sb.sb_win_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${sb.sb_rank}</td>
						<td id="sb_non_paying" >${sb.sb_non_paying eq 0?"未付款":"已付款"}</td>
						<!-- <td>
                            <select class="select" name="sb_non_paying" id="sb_non_paying">
                                <option value="0">未付款</option>
                                <option value="1">已付款</option>
                            </select>
                        </td> -->
						
						<td style="text-align: center;" class="exclude">
							<button type="button" id="btn"  class="btn  btn-sm "
								onClick="button('${sb.sb_no}','${sb.meb_no}','${sb.sb_non_paying}')">更新付款</button>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
	</main>
	 <!-- Modal -->
    <div class="modal" id="Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg" >
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" >競標紀錄</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p scope="col" id="scar_no"></p> 
                    <table class="table table-hover" style="margin-left:auto;margin-right:auto;width:95%;">
                        <thead  style="text-align: center;">
                            <tr >
                                <th scope="col">競拍編號</th>
                                <th scope="col">會員</th>
                                <th scope="col">競標價</th>
                                <th scope="col">下標日期</th>
                            </tr>
                        </thead>
                        <tbody id="bidtbody" style="text-align: center;">
                                                
                              
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                </div>
            </div>
        </div>
    </div>
</body>
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
    //click not working
    //#sbtbody tr td:not(.exclude)
    $(document).on('click', '#sbtbody tr td:not(.exclude)', function (e) {
    		const id = $(this).parent().attr('id');
    	//	alert(id);
    	 $('#scar_no').empty();
    	 $('#bidtbody').empty();
    	 $('#scar_no').append("中古車編號  :"+id);
    	//alert($(this).attr('id'));
        $.ajax({
                type: "POST",
                url: "/CGA102G4/bidListbyscarno",
                data: {
                    scar_no: id,
                },
                success: function (data) {
                	  data = JSON.parse(data);
                  //  console.log(data)
                   jQuery.each(data, function(index, bid) {
                	//   console.log(new Date(bid.bid_time));
                	    bid.bid_time=new Date((+new Date(bid.bid_time)+ 8 * 3600 * 1000)).toISOString().replace("T"," ").split('.')[0];
                	   let price = bid.bid_price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");              	 
                	//  console.log(price);
                	   let html = `<tr>
                                    <td>`+bid.bid_no+`</td>
                                    <td>`+bid.meb_no+`</td>
                                    <td>`+price+`</td>
                                    <td>`+bid.bid_time+`</td>
                                </tr>`;
                	$('#bidtbody').append(html);
          		//	console.log();
        });
                  
                  
                //    if (data == 1)
                 //       console.log("編輯成功!")
                  //  else
                  //  console.log("編輯失敗")
                }
            });

        //  var Modal = new bootstrap.Modal(document.getElementById('exampleModal'));
        //  Modal.show();
     
        $('#Modal').modal('show');

    })
</script>
	
	
	<script>
		$('.lss').click(function(e) {
			const target = e.target;
			if (target.classList.contains('lss')) {
				$(this).children().slideToggle(500);
			}
			console.log(target);

		})
		function button(sb_no, meb_no, sb_non_paying) {
			//		let paying = document.getElementById("sb_non_paying");
			//		let sb_no = document.getElementById("sb_no").textContent;
			//		let meb_no = document.getElementById("meb_no").textContent
			//	console.log(paying.textContent)
		//	console.log(sb_no)
		//	console.log(meb_no)
			let payValue = parseInt(sb_non_paying)
			//更改成 0->1
			let changeValue;
			//let payVale = parseInt(paying.textContent)
			if (payValue === 0)
				changeValue = 1;
			if (payValue === 1 ){
				$('#message').empty();
				$('#message').append("已付款不可再變更");
				changeValue = 0;
				return
				//alert("付款不可再變更");
			}
			$('#message').empty();
			//1->
			$.ajax({
				type : "POST",
				url : "/CGA102G4/sbupdatepaying",
				data : {
					sb_no : sb_no,
					meb_no : meb_no,
					sb_non_paying : changeValue,
				},
				success : function(data) {
					console.log(data)
					if (data == 200){
						console.log("編輯成功!");
						window.location.reload();
					}else{
						$('#message').append(data);
					}
				//		alert(data);
					 //重新整理頁面
					//	if (payValue === "未付款")
					//		paying.textContent =  "已付款";
					//	if (payValue === "已付款")
					//		paying.textContent =  "未付款";
					//	else
					//	conloe.log("編輯失敗!")
				}
			});

		}
	</script>


</html>