//       通訊地址(Jquery)  
$('#twzipcode').twzipcode({
    'zipcodeSel': '106',
    'countySel': '臺北市',
    'districtSel': '大安區',
    zipcodeIntoDistrict: true
});
// 左側功能 下拉
$('.lss').click(function (e) {
    const target = e.target;
    if (target.classList.contains('lss')) {
        $(this).children('div.div2').slideToggle(500);
    }
})

// 門市編號(jquery)
$('#store1').change(function () {
    $('.str1').remove();
    $(this).addClass('ok')
});
// 性別(jquery) 
$('#gender').change(function () {
    $('.grndert').remove();
    $(this).addClass('ok')
});

//抓取值傳到後端
$('.button3').click(function () {
    console.log('on in')
    if ($('.ok').length === 10) {
        console.log('in')
        //抓取各個input值
        let name = $('#Name1').val();
        let id = $('#id').val();
        let birthday = $('#bd1').val();
        let gender = $('#gender').val();
        let password = $('#password').val();
        let store = $('#store1').val();
        let email = $('#email').val();
        let phone = $('#phone').val();
        let city = $('select[name="city"]').val();
        let cityarea = $('select[name="cityarea"]').val();
        let street = city + cityarea + $('#street').val();
        let title = $('#tit1').val();

        //用ajax傳到後端
        $.ajax({
            method: 'Post',
            url: "../../NewEmployee",
            data: {
                'Name1': name,
                'id': id,
                'bd1': birthday,
                'gender': gender,
                'password': password,
                'store': store,
                'email': email,
                'phone': phone,
                'street': street,
                'title': title
            },
            success: function (resp) {
                if (resp === "true") {
                    swal({ title: "新增成功", icon: "success" });
                } else if (resp === "false") {
                    swal({ title: "新增失敗", icon: "error" });
                }
            }
        })
    } else {
        swal({ title: "請輸入完整資料!", icon: "error" });
    }
})

//欄位不可為空
$('.form-control').blur(function () {
    const val = $(this).val();
    const id = $(this).attr('id');
    if ($.trim(val)) {  //判斷是否為空值
        $(this).attr('class', 'form-control ok')
        if (id === 'password') {
            passlength($(this));
        } else if (id === 'Name1') {
            name1($(this));
        } else if (id === 'phone') {
            phone1($(this));
        } else if (id === 'bd1') {
            age($(this));
        }
    } else {
        $(this).val(null).attr({
            'placeholder': '欄位不可為空',
            'class': 'form-control err'
        })
    }
})
$('#gender').change(function () {
    $('.grndert').remove();
    $(this).addClass('ok')
})



//判斷密碼為6-12碼
function passlength(object) {
    if (object.val().length < 6 || object.val().length > 12) {
        object.attr({
            'placeholder': '密碼長度請填寫6～12',
            'class': 'form-control err'
        }).val(null)
    }
}

// 判斷姓名為中文
function name1(object) {
    const pattern = /^[\u4E00-\u9FA5]{1,6}$/;
    if (object.val().search(pattern) === -1) {
        object.attr({
            'placeholder': '請填寫正確姓名格式',
            'class': 'form-control err'
        }).val(null)

    }
}

//判斷電話是否09開頭
function phone1(object) {
    const pattern = /^09[0-9]{8}$/;
    if (object.val().search(pattern) === -1) {
        object.attr({
            'placeholder': '請填寫正確手機號碼',
            'class': 'form-control err'
        }).val(null)
    }
}

// 生日小於20歲不給新增
function age(object) {
    let now = new Date().getFullYear();  //現在日期
    let birthday = new Date($("#bd1").val()).getFullYear(); //選擇的日期
    if ((now - birthday) < 20) {
        swal({ title: "年齡須滿20歲!", icon: "error" });
        object.attr({
            'class': 'form-control err'
        }).val(null)
    }
}