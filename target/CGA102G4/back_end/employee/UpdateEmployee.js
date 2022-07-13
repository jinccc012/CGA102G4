//欄位數量
let count = 1;
// 用ajex取得資料庫門市資料
if (sessionStorage.getItem('store') === null) {
    //要是sessionStorage以有門市資料 就不對後端請求
    $.ajax({
        method: 'post',
        url: '../../Getstoreall',
        dataType: 'text',
        success: function (e) {
            //console.log(e)
            sessionStorage.setItem('store', e)
            getAllEmp();
        }
    })
}else{
    getAllEmp();
}


//ajax取得員工資訊
function getAllEmp() {
    $.ajax({
        method: 'post',
        url: '../../UpdateEmployee',
        dataType: "json",
        data: {
            'status': 'getall'
        },
        success: function (resp) {
            for (let emp of resp) {
                addemp(emp);
            }
            console.log(resp.length)
            console.log(count)
            if (resp.length == (count - 1))
                $('#empTable').DataTable({
                    language: { //國際化
                        url: "https://cdn.datatables.net/plug-ins/1.12.1/i18n/zh-HANT.json",
                    },
                })
        }
    })
}






// 左側功能 下拉
$('.lss').click(function (e) {
    const target = e.target;
    if (target.classList.contains('lss')) {
        $(this).children('div.div2').slideToggle(500);
    }
})


//1.取得欄位值
let up = true;
$('tbody').on('click', '.click', function () {
    let all = $(this).parent().find('input,select')
    // let allselect = $(this).parent().find('select')
    let id = $(this).parent().find('th.empid').text()
    let name = $(this).parent().find('input.name').val()
    let storeno = $(this).parent().find('select.storeno').val()
    let birthday = $(this).parent().find('input.bday').val()
    let phone = $(this).parent().find('input.phone').val()
    let adrs = $(this).parent().find('input.adrs').val()
    let email = $(this).parent().find('input.email').val()
    let title = $(this).parent().find('input.title').val()
    let status = $(this).parent().find('select.statue').val()
    let permission = $(this).parent().find('select.permission').val()

    if (all.prop('disabled') == true && up == true) {  //開啟修改
        all.prop('disabled', false)
        up = false;
    } else if (all.prop('disabled') == false && up == false) { //完成修改
        all.prop('disabled', true)
        up = true;
        console.log(name + ' ' + storeno + ' ' + phone + ' ' + adrs + " " + email + ' ' + title + " " + status + ' ' + id)

        //sweetAlert
        swal({
            title: "確認修改此筆資料?",
            text: "",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        method: 'Post',
                        url: '../../UpdateEmployee',
                        dataType: 'text',
                        data: {
                            'status': 'update',
                            'empno': id,
                            'name': name,
                            'storeno': storeno,
                            'birthday': birthday,
                            'phone': phone,
                            'adrs': adrs,
                            'email': email,
                            'title': title,
                            'statue': status,
                        },
                        success: function (res) {

                            if (res === 'true') {
                                swal("修改成功!", {
                                    icon: "success",
                                });
                            } else {
                                swal("修改成功!", {
                                    icon: "error",
                                });
                            }
                        }


                    })


                } else {
                    swal("已取消修改!")
                        .then((willDelete) => {
                            if (willDelete) {
                                window.location.reload();  //重整頁面
                            }
                        });
                }
            });



    } else {
        alert('一只能修改一筆資料')
    }
})

// 秀出資料庫員工資料
function addemp(emp) {
    $('tbody').append(`
        <tr id="${emp.emp_no}">
                <th scope="row">${count}</th>
                <th scope="row" class="empid">${emp.emp_no}</th>
                <td id="name"><input class="name" type="text" value="${emp.emp_name}" size="7" disabled></td>
                <td class="storenotd"></td>
                <td class="phonetd"><input class="phone" type="text" value="${emp.emp_tel}" size="10" disabled></td>
                <td class="adrstd"><input class="adrs" type="text" value="${emp.emp_adrs}" size=""
                        disabled></td>
                <td class="emailtd"><input class="email" type="text" value="${emp.emp_mail}" size=""
                        disabled></td>
                <td class="titletd"><input class="title" type="text" value="${emp.emp_title}" size="7" disabled></td>
                <td class="statustd"></td>
                <td class="permission"><i class="fa-solid fa-user-gear" data-bs-toggle="modal" data-bs-target="#staticBackdrop"></i></td>
                <td class="click"><i class="fa-solid fa-pen-to-square"></i></td>

            </tr>
    `)

    // 取得門市資訊
    //append <select>
    $(`#${emp.emp_no} > td.storenotd`).append(`<select name=""  id="" class="storeno form-select form-select-sm" disabled></select>`);
    //取出sessionStorage的門市資訊
    let sotreAll = JSON.parse(sessionStorage.getItem('store'));

    // console.log(JSON.stringify(sotreAll)) 轉json格式
    //將sotreAll用迴圈 取出每個門市資訊
    for (let store of sotreAll) {
        let storeSelected; //選取 員工門市
        if (store.st_no === emp.st_no) { //要是 門市 == 員工門市資訊
            storeSelected = 'selected'; //就選取此門市
        }
        //將門市新增到 <select>裡面
        $(`#${emp.emp_no}  select.storeno`).append(`<option value="${store.st_no}" ${storeSelected} >${store.st_name}</option>`)
    }

    // 員工在職狀態
    $(`#${emp.emp_no}  td.statustd`).append(`
        <select name="statue" id="" class="statue form-select form-select-sm" disabled>   
        </select>
    `)
    if (emp.emp_status === 0) {
        $(`#${emp.emp_no} select.statue`).append(`
            <option value="1" >在職中</option>
            <option value="0" selected>離職</option>
            `)
    } else {
        $(`#${emp.emp_no} select.statue`).append(`
            <option value="1" selected>在職中</option>
            <option value="0" >離職</option>
        `)
    }

    count++;
}
$.ajax({ //秀出所有權限
    method: 'post',
    url: '../../GetAllEmp',
    dataType: 'json',
    data: {
        'status': 'getall'
    },
    success: function (resp) {
        for (let empfu of resp){
            let disabled  = '';
            if(empfu.empf_no === 8 || empfu.empf_no === 12 ){
                disabled = 'disabled';
            }
            $('.modal-body').append(`
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" id="${empfu.empf_no}" value="${empfu.empf_no}" ${disabled}>
                 <label class="form-check-label" for="${empfu.empf_no}">${empfu.empf_name}</label>
            </div>
        `)
        }
            
    }
})

$('tbody').on('click', '.permission', function () { //選取員工擁有的權限
    let id = $(this).parent().find('th.empid').text()  //獲取點選員工的ID
    $('.form-switch .form-check-input').prop('checked', false);  //將所有權限check box初始化
    $('#empNo').val(id);  //將隱藏元素設定ID
    $.ajax({ //權限
        method: 'post',
        url: '../../getemppr',
        dataType: 'json',
        data: {
            'status': 'getemppr',
            'emp_no': id
        },
        success: function (resp) {  //使用迴圈將權限功能勾選
            sessionStorage.setItem('pr', JSON.stringify(resp))
            for (let empPr of resp) {
                $(`#${empPr.empf_no}`).prop('checked', true);
            }
        }
    })
})

$('#send').click(function () {  //員工權限設定
    const prAll = JSON.parse(sessionStorage.getItem('pr'));
    const id = $('#empNo').val()
    const check = $('.modal-body :checked');

    let checkAll = [];//////
    let add = [];
    let rom = [];
    check.each(function () {
        let result = $.map(prAll, function (pr, index) { //比對 新增功能
            return pr.empf_no;
        }).indexOf(parseInt($(this).val()));

        let checkobj = {
            'index': result,//?
            'val': parseInt($(this).val())
        }
        // console.log(checkobj)
        checkAll.push(checkobj)
        if (result == -1) { //= -1 代表新增功能
            let romObj = {
                'emp_no': id,
                'empf_no': $(this).val()
            }
            add.push(romObj)
        }
    })



    for (let a = 0; a < prAll.length; a++) { // 比對刪除功能

        let addorrom = $.map(checkAll, function (value, index) {
            return value.val;
        }).indexOf(prAll[a].empf_no);
        // console.log(prAll[a].empf_no)


        if (addorrom === -1) {
            console.log("取消權限:" + prAll[a].empf_no)
            let romObj = {
                'emp_no': id,
                'empf_no': prAll[a].empf_no
            }
            rom.push(romObj)
        }

    }

    console.log('add')
    console.log(add)
    console.log('=====================================')
    console.log('rom')
    console.log(rom)

    $.ajax({
        method: 'post',
        url: '../../getemppr',
        data: {
            'status': 'addorrom',
            'json': JSON.stringify(rom),
            'addjson': JSON.stringify(add)
        },success:function(resp){
            if (resp == 'true') {
                swal({
                    title: "修改權限成功!",
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        window.location.reload();
                    }
                })
            } else {
                swal({
                    title: "修改權限失敗!",
                    icon: "error",
                })
            }
        }
    })
})