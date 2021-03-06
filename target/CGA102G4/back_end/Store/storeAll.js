$.ajax({
    method: 'post',
    url: `${path}/Getstoreall`,
    dataType: 'json',
    async: false,
    success: function (res) {
        $.each(res, function (index, store) {
            $('#StoeTable tbody').append(`
            <tr id="${store.st_no}">
                <th><label >${store.st_no}</label></th>
                <td><label class="stname">${store.st_name}</label></td>
                <td><label class="stadrs">${store.st_adrs}</label></td>
                <td><label class="sttel">${store.st_tel}</label></td>
                <td><i class="fa-solid fa-pen-to-square revise" data-bs-toggle="modal" data-bs-target="#exampleModal"></i></td>
            </tr>       
            `)
        })
    }
})

$('#StoeTable').DataTable({
    language: { //國際化
        url: "https://cdn.datatables.net/plug-ins/1.12.1/i18n/zh-HANT.json",
    },
    "drawCallback": function () { //每次渲染一次都執行
        $('.pagination').addClass('pagination-sm');
    },
});

// 修改門市資訊 👇👇
$('.revise').on('click',function(){
    const no = $(this).parents('tr').attr('id');
    const name = $(this).parents('tr').find('.stname').text();
    const adrs = $(this).parents('tr').find('.stadrs').text();
    const tel = $(this).parents('tr').find('.sttel').text();

    $('#stNo').val(no);
    $('#stName').val(name);
    $('#stAdrs').val(adrs);
    $('#stTel').val(tel);
})

$('#revisebut').click('click',function(){

    const st_no = $('#stNo').val();
    const st_name = $('#stName').val();
    const st_adrs = $('#stAdrs').val();
    const st_tel = $('#stTel').val();

    const obj = {
        st_no:st_no,
        st_name:st_name,
        st_adrs:st_adrs,
        st_tel:st_tel
    }
    $.ajax({
        method: 'post',
        url: `${path}/storeaccess`,
        dataType: 'json',
        async: false,
        data:{
            json:JSON.stringify(obj),
            status:'updata'
        },
        success: function (res) {
            if (res == true) {
                swal({
                    title: "修改成功!",
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        // window.location.reload();
                        $(`#${obj.st_no}`).find('.stname').text(obj.st_name)
                        $(`#${obj.st_no}`).find('.stadrs').text(obj.st_adrs)
                        $(`#${obj.st_no}`).find('.sttel').text(obj.st_tel)
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

// 修改門市資訊 👆👆

// 新增門市 👇👇

$('#add').on('click',function(){
    const st_no = $('#StoreNo').val();
    const st_name = $('#StoreName').val();
    const st_adrs = $('#StoreAdrs').val();
    const st_tel = $('#Storephon').val();

    const obj = {
        st_no:st_no,
        st_name:st_name,
        st_adrs:st_adrs,
        st_tel:st_tel
    }

    $.ajax({
        method: 'post',
        url: `${path}/storeaccess`,
        dataType: 'json',
        async: false,
        data:{
            json:JSON.stringify(obj),
            status:'insert'
        },
        success: function (res) {
            if (res == true) {
                swal({
                    title: "新增成功!",
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        window.location.reload();
                    }
                })
            } else {
                swal({
                    title: "新增失敗!",
                    icon: "error",
                })
            }
        }
    })
    
})

// 新增門市 👆👆