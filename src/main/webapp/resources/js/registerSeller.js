$(function(){
    //設置驗證旗幟
    let u_flag = false;
    let u_check_flag = false;
    let p_flag = false;
    let re_p_flag = false;
    let n_flag = false;
    let phone_flag = false;
    let shopname_flag = false;
    let street_flag = false;
    //確認旗幟均為true按鈕才能按
    function checkFlag(){
        if(u_flag && u_check_flag && p_flag && re_p_flag && n_flag && phone_flag && shopname_flag && street_flag){
            $("#register").removeAttr("disabled") 
        }else{
            $("#register").attr("disabled", "disabled") 
        }
    }

    //監聽帳號格式是否正確
    $('#username').on('input',function(){
        $('#u-prompt').text("");
        if(validateUsername()){
            $('#username').css('border','2px solid #27da80')
            u_flag = true;
        }else{
            $('#u-prompt').text("請輸入有效的電子郵件地址");
            $('#u-prompt').css('color','red');
            $('#u-prompt').css('font-size','10px');
            $('#username').css('border','2px solid red')
            u_flag = false;
        }
        checkFlag();
    });

    //監聽密碼格式是否正確
    $('#password').on('input',function(){
        $('#p-prompt').text("");
        if(validatePassword()){
            $('#password').css('border','2px solid #27da80')
            p_flag = true;
        }else{
            $('#p-prompt').text("密碼長度限制6-20");
            $('#p-prompt').css('color','red');
            $('#p-prompt').css('font-size','10px');
            $('#password').css('border','2px solid red')
            p_flag = false;
        }
        if($('#re-password').val()!=""){
            checkPassword();
        }
        checkFlag();
    });

    //監聽確認密碼格式是否正確
    $('#re-password').on('input',function(){
        checkPassword();
        checkFlag();
    });

    //監聽會員名稱格式是否正確
    $('#name').on('input',function(){
        if($('#name').val() != ""){
            $('#name').css('border','2px solid #27da80');
            n_flag = true;
        }else{
            $('#name').css('border','2px solid #f1a7c0');
            n_flag = false;
        }
        checkFlag();
    });

    //監聽電話格式是否正確
    $('#phone').on('input',function(){
        $('#phone-prompt').text("");
        if(validatePhone()){
            $('#phone').css('border','2px solid #27da80')
            phone_flag = true;
        }else{
            $('#phone-prompt').text("請輸入有效的手機號碼");
            $('#phone-prompt').css('color','red');
            $('#phone-prompt').css('font-size','10px');
            $('#phone').css('border','2px solid red')
            phone_flag = false;
        }
        checkFlag();
    });

    //商店名稱格式是否正確
    $('#shopname').on('input',function(){
        if($('#shopname').val() != ""){
            $('#shopname').css('border','2px solid #27da80');
            shopname_flag = true;
        }else{
            $('#shopname').css('border','2px solid #f1a7c0');
            shopname_flag = false;
        }
        checkFlag();
    });

    //詳細地址格式是否正確
    $('#street').on('input',function(){
        if($('#street').val() != ""){
            $('#street').css('border','2px solid #27da80');
            street_flag = true;
        }else{
            $('#street').css('border','2px solid #f1a7c0');
            street_flag = false;
        }
        checkFlag();
    });

    //送出註冊表單
    $('#register').on('click',function(){
        let username = $('#username').val();
        let password = $('#password').val();
        let name = $('#name').val();
        let phone = $('#phone').val();
        let role = $('#role').val();
        let shopname = $('#shopname').val();
        let city = $('select[name="city"]').val();
        let cityarea = $('select[name="cityarea"]').val();
        let street = $('#street').val();

        $.ajax({
            type:"post",
            url:"../../member/registerSellerServlet",
            data:{
                "username":username,
                "password":password,
                "name":name,
                "phone":phone,
                "role":role,
                "shopname":shopname,
                "city":city,
                "cityarea":cityarea,
                "street":street
            },
            success:function (result) {
                if(result=="1"){
                    window.location.href="../../index.html";
                }else{
                    $('#u-prompt').text("註冊失敗");
                    $('#u-prompt').css('color','red');
                    $('#u-prompt').css('font-size','10px');
                    $('#username').css('border','2px solid red')
                }
            }
        });
    });

    //驗證註冊帳號是否可用
    $('#username').on('blur',function(){
        if(!validateUsername()){
            return;
        }
        $('#u-prompt').text("");
        let username = $('#username').val();
        $.ajax({ type:"post",
        url:"../../member/memberCheckServlet",
        data:{
            "username":username,
        },
        success:function (result) {
            if(result=="1"){
                $('#username').css('border','2px solid #27da80')
                u_check_flag = true;
            }else{
                $('#u-prompt').text("此Email已經有人使用");
                $('#u-prompt').css('color','red');
                $('#u-prompt').css('font-size','10px');
                $('#username').css('border','2px solid red')
                u_check_flag = false;
            }
            checkFlag();
        }})
    });


    //帳號正則表達式驗證
    function validateUsername() {
        let username = $('#username').val();
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(username);
    }

    //密碼正則表達式驗證
    function validatePassword(){
        let password = $('#password').val();
        const re = /^[0-9A-Za-z]{6,20}$/;
        return re.test(password);
    }

    //確認密碼是否相同
    function checkPassword(){
        $('#re-p-prompt').text("");
        if($('#password').val() == $('#re-password').val()){
            $('#re-password').css('border','2px solid #27da80')
            re_p_flag = true;
        }else{
            $('#re-p-prompt').text("密碼不相同");
            $('#re-p-prompt').css('color','red');
            $('#re-p-prompt').css('font-size','10px');
            $('#re-password').css('border','2px solid red')
            re_p_flag = false;
        }
    }

    //電話正則表達式驗證
    function validatePhone(){
        let phone = $('#phone').val();
        const re = /^09[0-9]{8}$/;
        return re.test(phone);
    }

});