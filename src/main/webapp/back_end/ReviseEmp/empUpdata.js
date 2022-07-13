let empDate;
$('#empSend').click(function () { //修改資料
    const name = $('#empName').val();
    const bir = $('#empBir').val();
    const mail = $('#empMail').val();
    const adrs = $('#empAdrs').val();
    const tel = $('#empTel').val();
    const gender = $('#gender').val();
    const id = $('#empNO').val();

    

    empDate = {
        'emp_name': name,
        'emp_bir': bir,
        'emp_mail': mail,
        'emp_adrs': adrs,
        'emp_tel': tel,
        'emp_gender': gender,
        'emp_no': id
    }

    const jsonEmp = JSON.stringify(empDate);
    console.log(jsonEmp);

    $.ajax({
        method: 'post',
        url: '../../empdata',
        data: {
            status: 'updata',
            json: jsonEmp
        },
        success: function (res) {
            if (res == 'true') {
                swal({
                    title: "修改成功!",
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        window.location.reload();
                    }
                })
            } else {
                swal({
                    title: "修改失敗!",
                    icon: "error",
                })
            }
        }
    })
})

$('#empName').blur(function(){ //姓名驗證
    if($(this).val().trim().length == 0){
        $(this).addClass('is-invalid');
        $('#empSend').prop('disabled',true);
    }else{
        const pattern = /^[\u4E00-\u9FA5]{1,6}$/;
        if ($(this).val().search(pattern) === -1) {
            $(this).addClass('is-invalid');
            $(this).val(null);
          return
        }
        $(this).removeClass('is-invalid');
        $('#empSend').prop('disabled',false);
    }
});


$('#empMail').blur(function(){ //信箱驗證
    if($(this).val().trim().length == 0){
        $(this).addClass('is-invalid');
        $('#empSend').prop('disabled',true);
    }else{
        const pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
        if ($(this).val().search(pattern) === -1) {
            $(this).addClass('is-invalid');
            $(this).val(null);
          return
        }
        $(this).removeClass('is-invalid');
        $('#empSend').prop('disabled',false);
    }
})

$('#empTel').blur(function(){ //手機認證
    if($(this).val().trim().length == 0){
        $(this).addClass('is-invalid');
        $('#empSend').prop('disabled',true);
    }else{
        const pattern = /^09[0-9]{8}$/;
        if ($(this).val().search(pattern) === -1) {
            $(this).addClass('is-invalid');
            $(this).val(null);
          return
        }
        $(this).removeClass('is-invalid');
        $('#empSend').prop('disabled',false);
    }
})

$('#empAdrs').blur(function(){ //地址認證
    if($(this).val().trim().length == 0){
        $(this).addClass('is-invalid');
        $('#empSend').prop('disabled',true);
    }else{
        $(this).removeClass('is-invalid');
        $('#empSend').prop('disabled',false);
    }
})



$('#empPass').blur(function () {
    if ($(this).val().trim().length !== 0) {
        $.ajax({
            method: 'post',
            url: '../../empdata',
            data: {
                status: 'checkpass',
                pass: $(this).val()
            },
            success: function (res) {
                if (res === 'true') {
                    $('#empPass').attr('class', 'form-control is-valid');
                } else {
                    $('#empPass').attr('class', 'form-control is-invalid').val(null);
                }
            }
        })
    } else {
        $('#empPass').attr('class', 'form-control is-invalid').val(null);
    }

})

$('#empCheckPass').blur(function () {
    const newPass = $('#empNewPass').val();
    const checkPass = $(this).val();
    if (checkPass.trim() != 0) {
        if (checkPass.length < 6 || checkPass.length > 12) {
            $(this).attr('class', 'form-control is-invalid').val(null);
            $(this).parent().find('.invalid-feedback').text('密碼長度需6~12位!');
        } else {
            if (newPass === checkPass) {
                $('#empNewPass').attr('class', 'form-control is-valid');
                $(this).attr('class', 'form-control is-valid');
            } else {
                $(this).attr('class', 'form-control is-invalid').val(null);
                $(this).parent().find('.invalid-feedback').text('密碼錯誤! 請確認!');
            }
        }

    } else {
        $(this).attr('class', 'form-control is-invalid').val(null);
        $(this).parent().find('.invalid-feedback').text('不可為空!');
    }
})

$('#empNewPass').blur(function () {
    const empPass = $('#empPass').val();
    const empNewPass = $(this).val();
    if (empNewPass.trim() != 0) {
        if (empNewPass.length < 6 || empNewPass.length > 12) {
            $(this).attr('class', 'form-control is-invalid').val(null);
            $(this).parent().find('.invalid-feedback').text('密碼長度需6~12位!');
        } else {
            if (empPass === empNewPass) {
                $(this).attr('class', 'form-control is-invalid').val(null);
                $('#empCheckPass').attr('class', 'form-control').val(null);
                $(this).parent().find('.invalid-feedback').text('不可與舊密碼相同!');
            } else {
                $(this).attr('class', 'form-control is-valid').val();
                $('#empCheckPass').attr('class', 'form-control').val(null);
            }
        }
    } else {
        $(this).attr('class', 'form-control is-invalid').val(null);
        $(this).parent().find('.invalid-feedback').text('不可為空!');
    }
})

$('#passSend').click(function () {
    const pass = $('#empNewPass').val();

    if ($('.is-valid').length === 3) {
        $.ajax({
            method: 'post',
            url: '../../empdata',
            data: {
                status: 'updatapass',
                pass: pass
            },
            success: function (res) {
                if (res == 'true') {
                    swal({
                        title: "修改成功! 將為你登出",
                        icon: "success",
                    }).then((willDelete) => {
                        if (willDelete) {
                            window.location.reload();
                        }
                    })
                } else {
                    swal({
                        title: "修改失敗!",
                        icon: "error",
                    })
                }
            }
        })
    } else {
        swal({
            title: "資料不完整!",
            icon: "error",
        })
    }
})