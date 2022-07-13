const statuslist = ['報廢','空車','出車','還車','維修']

$(`#carcontrol`).DataTable({
    language: { //國際化
        url: "https://cdn.datatables.net/plug-ins/1.12.1/i18n/zh-HANT.json",
    },
    "drawCallback": function () { //每次渲染一次都執行
        $('.pagination').addClass('pagination-sm');
        $('#carcontrol_length .form-select').prop('disabled',true)
    },
})

let car;
$('.modify').click(function(){
    const miles = $(this).parents('tr').find('.miles').text();
    const rcar_status = $(this).parents('tr').find('.carstatus').val();
    const rcar_no = $(this).parents('tr').find('.carstatus').attr('id');
    const st_no = $(this).parents('tr').find('.stName').val();
    const model_no = $(this).parents('tr').find('.modelNo').text();
    const rcar_loc = $(this).parents('tr').find('.rcarLoc').val();
    car={
        st_no,
        model_no,
        rcar_loc,
        miles,
        rcar_status,
        rcar_no
    }
    console.log(car);
    $('#miles').val(miles);
    $('#status').val(rcar_status);
})

$('#checkmodify').click(function(){
    car.miles = $('#miles').val();
    car.rcar_status = $('#status').val();
    
    const json = JSON.stringify(car);
    console.log(json);
    $.ajax({
        method:'post',
        url:'getdispatchservlet',
        data: {
            'status': 'upstatus',
            'json':json
        },
        success: function (res) {
            console.log(res);
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
                    text:"請檢查格式使否正確",
                    icon: "error",
                })
            }
        }
    })
})