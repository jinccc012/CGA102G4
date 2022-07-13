// 調度紀錄
let storeall;
const height = $('.main').height() * 0.72;//控制DataTable高度
if (sessionStorage.getItem('store') == null) { //取得門市資料
    $.ajax({
        method: 'post',
        url: '../../storeshow',
        dataType: 'text',
        success: function (res) {
            storeall = JSON.parse(res);
            sessionStorage.setItem('store', res)
            init();
        }
    })
} else {
    storeall = JSON.parse(sessionStorage.getItem('store'));
    init();
}


function init() {
    let empStName;
    let recordDate = new Date().toISOString().split('T')[0];
    // let storeall = JSON.parse(sessionStorage.getItem('store'))
    const emp = JSON.parse(sessionStorage.getItem('employee'));
    console.log(emp)
    for (let store of storeall) {
        if (store.st_no == emp.st_no) {
            empStName = store.st_name;
        }
    }
    getdate(recordDate);
    function getdate(date) { //申請，待審核 紀錄
        $.ajax({
            method: 'post',
            url: '../../getdispatchservlet',
            dataType: 'json',
            data: {
                status: 'record',
                store: emp.st_no,
                month: date
            },
            success: function (res) {
                console.log(res);
                if ($('#drRecordTab_wrapper').length != 0) {
                    $(`#drRecordTab`).DataTable().clear().draw();
                }
                adddispatch(res, 'drRecord');
            }
        })
    }

    applyrecord(recordDate);
    function applyrecord(date) {
        //自己門市申請調度紀錄
        $.ajax({
            method: 'post',
            url: '../../getdispatchservlet',
            dataType: 'json',
            data: {
                status: 'applyrecord',
                store: emp.st_no,
                month: date
            },
            success: function (res) {
                console.log(res);
                if ($('#drRecordTab_wrapper').length != 0) {
                    $(`#applyrecordTab`).DataTable().clear().draw();
                }
                adddispatch(res, 'applyrecord')
            }
        })
    }


    $('#applyEmp').val(emp.emp_no);
    $('#applySt').val(empStName);

    // $('#drRecord-tab').click(function () { //切換 頁籤刷新
    //     $('#drRecord tbody').empty();
    //     getdate();
    // })




    function adddispatch(json, id) {
        let status = ['申請中', '核准', '駁回', '出車', '完成'];
        for (let dispatch of json) {

            const storestr = JSON.parse(sessionStorage.getItem('store'));
            for (let store of storestr) {
                if (dispatch.dr_apply_st == store.st_no) {
                    dispatch.dr_apply_st = store.st_name;
                }

                if (dispatch.dr_approve_st == store.st_no) {
                    dispatch.dr_approve_st = store.st_name;
                }
            }

            if (dispatch.dr_approve_emp == null) {
                dispatch.dr_approve_emp = '';
            }

            if (dispatch.dr_start_time_actual == null) {
                dispatch.dr_start_time_actual = '';
            }

            if (dispatch.dr_end_time_actual == null) {
                dispatch.dr_end_time_actual = ''
            }

            dispatch.dr_start_time = new Date(dispatch.dr_start_time).toISOString().split('T')[0]

            if (dispatch.miles_after == 0) {
                dispatch.miles_after = ''
            }

            let optionstr;
            let index = status.length;

            if (dispatch.dr_approve === 0) { //待申請 把其他下拉選項 刪除
                index = 3;
            }

            for (let i = 0; i < index; i++) {
                if (dispatch.dr_approve === i) {
                    optionstr += `<option value="${i}" selected>${status[i]}</option>`;
                    continue;
                }
                optionstr += `<option value="${i}">${status[i]}</option>`
            }


            if ((dispatch.miles_before == 0 && dispatch.dr_approve === 0) || (dispatch.miles_before == 0 && dispatch.dr_approve === 1)) {
                dispatch.miles_before = '';
            }

            let Disabled = '';
            let button = '';
            let returncar = '';
            let recallBut = '<button type="button"  class="btn btn-outline-secondary btn-sm send applyrecord">取消</button>';
            if (dispatch.dr_approve === 1) {
                Disabled = 'disabled';
                button = `<button type="button" class="btn btn-outline-secondary btn-sm send drRecordout ">出車</button>`;
            } else if (dispatch.dr_approve == 3) {
                Disabled = 'disabled';

                //出車狀態 創建 還車按鈕
                returncar = `<button type="button" class="btn btn-outline-secondary btn-sm send returncar"  data-bs-toggle="modal" data-bs-target="#exampleModal">還車</button>`
                recallBut = '';
            } else if (dispatch.dr_approve == 4) {
                Disabled = 'disabled';
                returncar = '';
                recallBut = '';

            } else if (dispatch.dr_approve == 2) {
                recallBut = '';
            } else {

                button = `<button type="button" class="btn btn-outline-secondary btn-sm send drRecord " >送出</button>`;
            }

            if (id === 'drRecord') {
                $('#drRecord tbody').append(`
                    <tr id="${dispatch.dr_no}">
                        <th><label>${dispatch.dr_no}</label></th>
                        <td ><label class="racrLoc">${dispatch.dr_apply_st}</label></td>
                        <td><label>${dispatch.dr_apply_emp}</label></td>
                        <td><label>${dispatch.dr_approve_emp}</label></td>
                        <td ><label class="rcarNo">${dispatch.rcar_no}</label></td>
                        <td><label>${dispatch.dr_start_time}</label></td>
                        <td><label>${dispatch.dr_start_time_actual}</label></td>
                        <td><label>${dispatch.dr_end_time_actual}</label></td>
                        <td><label >${dispatch.miles_before}</label></td>
                        <td><label>${dispatch.miles_after}</label></td>
                        <td><select ${Disabled} class="form-select form-select-sm" aria-label="Default select example" style="
                        width: 90px;">
                        ${optionstr}
                        </select></td>
                        <td>${button}</td>
                    </tr>       
                `);
                if ($('#drRecordTab_wrapper').length != 0) {
                    let t = $(`#${id}Tab`).DataTable();
                    t.row.add([
                        `<label>${dispatch.dr_no}</label>`,
                        `<label class="racrLoc">${dispatch.dr_apply_st}</label>`,
                        `<label>${dispatch.dr_apply_emp}</label>`,
                        `<label>${dispatch.dr_approve_emp}</label>`,
                        `<label class="rcarNo">${dispatch.rcar_no}</label>`,
                        `<label>${dispatch.dr_start_time}</label>`,
                        `<label>${dispatch.dr_start_time_actual}</label>`,
                        `<label>${dispatch.dr_end_time_actual}</label>`,
                        `<label>${dispatch.miles_before}</label>`,
                        `<label>${dispatch.miles_after}</label`,
                        `<select ${Disabled} class="form-select form-select-sm" aria-label="Default select example" style="
                    width: 90px;">
                    ${optionstr}
                    </select>`,
                        `${button}`
                    ]).node().id = dispatch.dr_no;
                    t.draw(false);
                }
            }


            if (id === 'applyrecord') {
                $('#applyrecord tbody').append(`
                    <tr id="${dispatch.dr_no}">
                        <th><label>${dispatch.dr_no}</label></th>
                        <td><label>${dispatch.dr_approve_st}</label></td>
                        <td><label>${dispatch.dr_apply_emp}</label></td>
                        <td><label>${dispatch.dr_approve_emp}</label></td>
                        <td ><label class="rcarNo">${dispatch.rcar_no}</label></td>
                        <td><label>${dispatch.dr_start_time}</label></td>
                        <td><label>${dispatch.dr_start_time_actual}</label></td>
                        <td><label>${dispatch.dr_end_time_actual}</label></td>
                        <td><label class="miles_before">${dispatch.miles_before}</label></td>
                        <td><label>${dispatch.miles_after}</label></td>
                        <td><label>${status[dispatch.dr_approve]}</label></td>
                        <td>${recallBut}</td>
                        <td>${returncar}</td>
                    </tr>       
                `)

                if ($('#applyrecordTab_wrapper').length != 0) {
                    let t = $(`#${id}Tab`).DataTable();
                    t.row.add([
                        `<label>${dispatch.dr_no}</label>`,
                        `<label>${dispatch.dr_approve_st}</label>`,
                        `<label>${dispatch.dr_apply_emp}</label>`,
                        `<label>${dispatch.dr_approve_emp}</label>`,
                        `<label class="rcarNo">${dispatch.rcar_no}</label>`,
                        `<label>${dispatch.dr_start_time}</label>`,
                        `<label>${dispatch.dr_start_time_actual}</label>`,
                        `<label>${dispatch.dr_end_time_actual}</label>`,
                        `<label class="miles_before">${dispatch.miles_before}</label>`,
                        `<label>${dispatch.miles_after}</label`,
                        `<label>${status[dispatch.dr_approve]}</label>`,
                        `${recallBut}`,
                        `${returncar}`
                    ]).node().id = dispatch.dr_no;
                    t.draw(false);
                }
            }
        }

        let monthOpt = '';
        for (let i = 2; i <= 13; i++) { //月份選擇
            let selected = '';
            let date = new Date(new Date().getFullYear(), i - 1, 1).toISOString().split('T')[0];
            if (i - 1 === (new Date().getMonth() + 1)) {
                selected = 'selected';
            }
            monthOpt += `<option value="${date}" ${selected}>${i - 1}月</option>`
        }
        console.log($('#drRecordTab_wrapper').length)
        if ($('#drRecordTab_wrapper').length == 0) {
            $(`#${id}Tab`).DataTable({//將表格轉成套件顯示
                language: { //國際化
                    url: "https://cdn.datatables.net/plug-ins/1.12.1/i18n/zh-HANT.json",
                },
                order: [[0, 'desc']],//預設 使用第幾欄排序
                "drawCallback": function () { //每次渲染一次都執行
                    $('.pagination').addClass('pagination-sm');
                    // $('.page-item').removeClass('active');
                },
                "initComplete": function (settings, json) {
                    // 新增 月份切換
                    $(`#${id}Tab_wrapper > .row div:eq(0)`).append(`
                        <label for="month" style="font-weight:normal">月份:
                            <select name="" id="${id}" class="month" >
                                ${monthOpt}
                            </select>
                         </label>
                    `)


                },
                "lengthChange": false//取消給使用者顯示幾筆資料
            });
        } else {
            console.log($(`#${id}Tab`))
            if (id === 'drRecord') {

            }

        }


    };


    $('#myTabContent').on('change', '.month', function () { //切換頁面頁面更新資料
        let date = $(this).val();
        let id = $(this).attr('id');
        if (id === 'drRecord') {
            getdate(date);
        } else if (id === 'applyrecord') {
            applyrecord(date);
        }

    })

    // 更新審核
    $('table').on('click', '.drRecord', function () {
        const statusVal = $(this).parents('tr').find('.form-select').val();
        const id = $(this).parents('tr').attr('id');
        if (statusVal !== '0') {
            $.ajax({
                method: 'post',
                url: '../../getdispatchservlet',
                data: {
                    status: 'update',
                    id: id,
                    statusVal: statusVal,
                    emp: emp.emp_no
                },
                success: function (res) {
                    if (res == 'true') {
                        swal({
                            title: "送出成功!",
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                                window.location.reload();
                            }
                        })
                    } else {
                        swal({
                            title: "送出失敗!",
                            icon: "error",
                        })
                    }
                }
            })
        }

    })

    //取消調度
    $('table').on('click', '.applyrecord', function () {
        const id = $(this).parents('tr').attr('id');

        swal({
            title: "確定取消調度?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        method: 'post',
                        url: '../../getdispatchservlet',
                        data: {
                            'status': 'delete',
                            'id': id
                        },
                        success: function (res) {
                            if (res == 'true') {
                                swal({
                                    title: "取消調度!",
                                    icon: "success",
                                }).then((willDelete) => {
                                    if (willDelete) {
                                        window.location.reload();
                                    }
                                })
                            } else {
                                swal({
                                    title: "送出失敗!",
                                    icon: "error",
                                })
                            }
                        }
                    })
                } else {
                    swal({ title: "取消" });
                }
            });

    })

    //出車作業
    $('table').on('click', '.drRecordout', function () {
        const id = $(this).parents('tr').attr('id');
        const rcar_no = $(this).parents('tr').find('.rcarNo').text();
        let rcar_loc = $(this).parents('tr').find('.racrLoc').text();

        for (let store of storeall) {
            if (store.st_name === rcar_loc) {
                rcar_loc = store.st_no;
            }
        }

        swal({
            title: "確定出車!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        method: 'post',
                        url: '../../getdispatchservlet',
                        data: {
                            'status': 'outcar',
                            'id': id,
                            'rcarNo': rcar_no,
                            'rcar_loc': rcar_loc
                        },
                        success: function (res) {
                            console.log(res);
                            if (res == 'true') {
                                swal({
                                    title: "出車成功!",
                                    icon: "success",
                                }).then((willDelete) => {
                                    if (willDelete) {
                                        window.location.reload();
                                    }
                                })
                            } else {
                                swal({
                                    title: "出車失敗!",
                                    icon: "error",
                                })
                            }
                        }
                    })
                } else {
                    swal({ title: "取消" });
                }
            });

        console.log(id + " " + rcar_no + " " + rcar_loc);

    })

    //還車👇👇
    $('table').on('click', '.returncar', function () {
        const id = $(this).parents('tr').attr('id');
        const rcar_no = $(this).parents('tr').find('.rcarNo').text();
        const before = $(this).parents('tr').find('.miles_before').text();
        $('#drNO').val(id);
        $('#drRcarNo').val(rcar_no);
        $('#drMiles').val(null);
        $('#before').val(before)
    })
    $('.send').click(function () {
        $(this).attr('data-bs-dismiss', null);
        const id = $('#drNO').val();
        const rcarNo = $('#drRcarNo').val();
        const drMiles = $('#drMiles').val();
        const before =  $('#before').val();

        console.log(before)
        if (parseInt(drMiles) < parseInt(before)) {
            $('#drMiles').attr('class', 'form-control is-invalid').val(null);
        } else {
            $(this).attr('data-bs-dismiss', 'modal');
            $.ajax({
                method: 'post',
                url: '../../getdispatchservlet',
                data: {
                    'status': 'incar',
                    'id': id,
                    'rcarNo': rcarNo,
                    'drMiles': drMiles
                },
                success: function (res) {
                    if (res == 'true') {
                        swal({
                            title: "還車成功!",
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                                window.location.reload();
                            }
                        })
                    } else {
                        swal({
                            title: "還車失敗!",
                            icon: "error",
                        })
                    }
                }
            })
        }

    })
    //還車👆👆




    //調度申請
    let today = new Date();
    let yyyy = today.getFullYear();
    let MM = today.getMonth() + 1;
    let dd = today.getDate();
    let hh = today.getHours();
    MM = checkTime(MM);
    dd = checkTime(dd);
    hh = checkTime(hh);
    let min = yyyy + '-' + MM + '-' + dd + 'T' + hh + ':00';
    $('#drStartTime').attr('min', min);


    for (let store of storeall) {
        if (emp.st_no === store.st_no || store.st_no == 'TPEHO') {
            continue;
        }
        $('#approveSt').append(`<option value="${store.st_no}">${store.st_name}</option>`)
    }


    $('#drApply button').on('click', function () {
        let applyEmp = $('#applyEmp').val();
        let applySt = $('#applySt').val();
        let approveSt = $('#approveSt').val();
        let drStartTime = $('#drStartTime').val();
        let drRcar = $('#drRcar').val();

        for (let store of storeall) {
            if (store.st_name === applySt) {
                console.log(store.st_name)
                applySt = store.st_no
            }
        }

        $.ajax({
            method: 'post',
            url: '../../getdispatchservlet',
            data: {
                'status': 'insert',
                'applyEmp': applyEmp,
                'applySt': applySt,
                'approveSt': approveSt,
                'drStartTime': drStartTime,
                'drRcar': drRcar
            }, success: function (res) {
                if (res == 'true') {
                    swal({
                        title: "申請成功!",
                        icon: "success",
                    }).then((willDelete) => {
                        if (willDelete) {
                            window.location.reload();
                        }
                    })
                } else {
                    swal({
                        title: "送出失敗!",
                        icon: "error",
                    })
                }
            }
        })
    })

    function checkTime(i) {
        if (i < 10) {
            i = '0' + i
        }
        return i
    }

}
