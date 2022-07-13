const path = $('#path').val();

$.ajax({ //取得權限
    method: "post",
    url: `${path}/checkempprservlet`,
    dataType: "json",
    success: function (res) {
        const showFu = $('#showFu')
        sessionStorage.setItem('pr', JSON.stringify(res))
        console.log(res)
        //每個人都擁有
			$('#showFu').append(` 
            <li class="lss"><i class="fa fa-user" ></i>員工基本資料
                <div class="div2" style="display:none;">
                   <a href="${path}/back_end/ReviseEmp/empUpdata.jsp"><i class="fa-solid fa-circle-arrow-right"></i>員工資料</a>
                </div>
            </li>
        `)
        for (let pr of res) {
            if (pr.empf_no == 1) { 
                showFu.append(`
                <li class="lss"><i class="fa-solid fa-file-circle-plus"></i>訂單管理
                    <div class="div2" style="display:none;">
                        <a href="${path}/back_end/Rcar_Order/RcarOrder.jsp"><i class="fa-solid fa-circle-arrow-right"></i>訂單</a><br>
                    </div>
                </li>
                `)
            } else if (pr.empf_no == 2) { //不做此功能
                showFu.append(`
                <!-- 會員資料管理 -->
                <li class="lss"><i class="fa-solid fa-users"></i>會員資料管理
                    <div class="div2" style="display:none;">
                        <a href=""><i class="fa-solid fa-circle-arrow-right"></i></i>新增會員</a><br>
                        <a href=""><i class="fa-solid fa-circle-arrow-right"></i>修改會員資料</a><br>
                        <a href=""><i class="fa-solid fa-circle-arrow-right"></i>查詢會員資料</a>
                    </div>
                </li>
                `)
            } else if (pr.empf_no == 3) { // 不做此功能
                showFu.append(`
                <!-- 出車還車管理 --> 
                <li class="lss"><i class="fa fa-taxi" aria-hidden="true"></i>出車還車管理
                    <div class="div2" style="display:none;">
                        <a href=""><i class="fa-solid fa-circle-arrow-right"></i>出車</a><br>
                        <a href=""><i class="fa-solid fa-circle-arrow-right"></i>還車</a><br>
                        <a href=""><i class="fa-solid fa-circle-arrow-right"></i>未結案查詢</a>
                    </div>
                </li>
                `)
            } else if (pr.empf_no == 4) {
                showFu.append(`
                <li class="lss"><i class="fa-solid fa-arrow-right-arrow-left"></i>車輛調度管理
                    <div class="div2" style="display:none;">
                        <a href="${path}/back_end/cardispatchrecord/cardispatchrecord.jsp"><i class="fa-solid fa-circle-arrow-right"></i>站所調度作業</a><br>
                      
                    </div>
                </li>
                `)
            }else if (pr.empf_no == 5) {
                showFu.append(`
                <!-- 門市車輛管理-->
                <li class="lss"><i class="fa-solid fa-shop"></i>門市車輛管理
                    <div class="div2" style="display:none;">
                        <a href="${path}/getstoreallcarservlet"><i class="fa-solid fa-circle-arrow-right"></i>車輛管控</a><br>
                    </div>
                </li>
                `)
            }else if (pr.empf_no == 6) {
                showFu.append(`
                <!-- 配車管理 -->
                <li class="lss"><i class="fa-solid fa-car-side"></i>配車管理
                    <div class="div2" style="display:none;">
                        <a href="${path}/back_end/car_distribution/back_cardistribution.jsp"><i class="fa-solid fa-circle-arrow-right"></i>車輛配車表</a><br>
                        <a href="${path}/back_end/otherCarOrder/othercarorder.jsp"><i class="fa-solid fa-circle-arrow-right"></i>外站車輛表</a><br>
                        <a href="${path}/back_end/cardispatcher/DisCarJsp.jsp"><i class="fa-solid fa-circle-arrow-right"></i>訂單配車</a><br>
                    </div>
                </li>
                `)
            }else if (pr.empf_no == 7) {
                showFu.append(`
                <!-- 行銷管理 --> 
                    <li class="lss"><i class="fa-solid fa-person-rays"></i>行銷管理
                        <div class="div2" style="display:none;">
                            <a href="${path}/event/eventAdd"><i class="fa-solid fa-circle-arrow-right"></i>活動上架</a><br>
                            <a href="${path}/event/eventManage"><i class="fa-solid fa-circle-arrow-right"></i>活動管理</a><br>
                        </div>
                    </li>
                `)
            }else if (pr.empf_no == 8) {
                showFu.append(`
                <!-- 員工管理 -->
                    <li class="lss"><i class="fa fa-user" aria-hidden="true"></i>員工管理
                        <div class="div2" style="display:none;">
                            <a href="${path}/back_end/employee/NewEmployee.jsp"><i class="fa-solid fa-circle-arrow-right"></i>新增員工資料</a><br>
                            <a href="${path}/back_end/employee/UpdateEmployee.jsp"><i class="fa-solid fa-circle-arrow-right"></i>修改員工資料</a><br>
                        </div>
                    </li>
                `)
            }else if (pr.empf_no == 9) {//子泛
                showFu.append(`
                <!-- 客服管理 --> 
                    <li class="lss"><i class="fa-solid fa-comment-dots"></i>客服管理
                        <div class="div2" style="display:none;">
                            <a href="${path}/back_end/Issue/Issue.jsp"><i class="fa-solid fa-circle-arrow-right"></i>查詢過往客服紀錄</a><br>
                            <a href="${path}/back_end/qa/QA.jsp"><i class="fa-solid fa-circle-arrow-right"></i>查詢QA</a><br>
                            <a href="${path}/back_end/qa/QAadd.jsp"><i class="fa-solid fa-circle-arrow-right"></i>新增QA</a><br>
                        </div>
                    </li>
                `)
            }else if (pr.empf_no == 10) {//彥儒
                showFu.append(`
                <!-- 總公司車輛管理 --> 
                    <li class="lss"><i class="fa-solid fa-car-tunnel"></i>總公司車輛管理
                        <div class="div2" style="display:none;">
                            <a href="${path}/back_end/rcarManager/rcar.jsp"><i class="fa-solid fa-circle-arrow-right"></i>所有車輛</a><br>
                        </div>
                    </li>
                `)
            }else if (pr.empf_no == 11) {
                showFu.append(`
                <!-- 中古車拍賣管理 -->
                <li class="lss"><i class="fa-solid fa-gavel"></i>中古車拍賣管理
                    <div class="div2" style="display:none;">
                        <a href="${path}/scar/scarAdd"><i class="fa-solid fa-circle-arrow-right"></i>中古車上架</a><br>
                        <a href="${path}/scar/scarManage"><i class="fa-solid fa-circle-arrow-right"></i>中古車管理</a><br>
                        <a href="${path}/back_end/Scar_Reserve/Scar_Reserve.jsp"><i class="fa-solid fa-circle-arrow-right"></i>查詢中古車預約紀錄</a><br>
                        <a href="${path}/sbid/sbidManage"><i class="fa-solid fa-circle-arrow-right"></i>中古車得標紀錄</a><br>
                        <a href="${path}/bid/bidShow"><i class="fa-solid fa-circle-arrow-right"></i>中古車競標紀錄</a><br>
                    </div>
                </li>
                `)
            }else if (pr.empf_no == 12) {
                showFu.append(`
                <!-- 門市管理 -->
                <li class="lss"><i class="fa-solid fa-store"></i>門市管理
                    <div class="div2" style="display:none;">
                        <a href="${path}/back_end/Store/storeAll.jsp"><i class="fa-solid fa-circle-arrow-right"></i>門市</a><br>
                    </div>
                </li>
                `)
            }
        }
    }
})