$(document).ready(function () {
    const height = $('.main').height() * 0.81;//控制DataTable高度
    let now = new Date();
    const month = new Date().getMonth();//當月前一個月 計算從0開始
    let emp = JSON.parse(sessionStorage.getItem('employee'));
    let city = emp.st_no;// 初始顯示哪個門市 
    const storeName = $('#storeName').val();


    let current_month = now.toISOString().split('T')[0];

    // 頁面刷新印出所有站所
    const storexhr = new XMLHttpRequest();
    storexhr.open("get", "../../storeshow", true);
    storexhr.send(null);
    storexhr.onload = function () {
        if (storexhr.status == 200) {
            sessionStorage.setItem('store', storexhr.responseText)
        }
    }

    //查詢調車紀錄
    $.ajax({
        method: 'post',
        url: '../../getdispatchservlet',
        dataType: 'json',
        data: {
            month: current_month,
            store: city,
            status: 'othercar'
        },
        success: function (res) {
            console.log(res)

            //初始顯示表格
            const xhr = new XMLHttpRequest();
            xhr.open('get', `../../showothercar?date=${current_month}&&store=${city}`, true);
            xhr.send(null);
            xhr.onload = function () {
                if (xhr.status == 200) {
                    let json = JSON.parse(xhr.responseText);
                    console.log(json)
                    add(json, now);
                    showDr(res);
                }
            }

        }

    })



    //顯示調車 
    function showDr(json) {
        // const drDate = new Date(json.dr_start_time).getDate()
        for (let dr of json) {
            let drDate = new Date(dr.dr_start_time);
            for (let i = 1; i <= 2; i++) {
                let date = drDate.getDate();
                $(`#${dr.rcar_no}${date}`).text(`調${dr.dr_apply_st}`).addClass('distribution');
                drDate.setDate(drDate.getDate() + 1);
            }
        }
    }

    //創出表格
    function add(json, month) {
        const statusName = ['報廢', '空車', '出車', '還車', '維修', '中古車'];
        const startday = new Date(new Date().getFullYear(), month.getMonth(), month.getDate());
        //const dated = new Date(new Date().getFullYear(), month.getMonth(), 0).getDate();
        // dated.setDate(startday.getDate() + 7);

        $('#cartable').append(`
            <thead>
                <tr>
                    <th class="color">車籍</th>
                    <th class="color">狀態</th>
                    <th class="color">車型</th>
                    <th class="color">車牌</th>
                </tr>    
            </thead>
            <tbody></tbody>
        `)

        for (let i = 0; i < json.length; i++) {
            $('tbody').append(`
                <tr id="car_${json[i].car_no}"> </tr>
            `)
            $(`#car_${json[i].car_no}`).append(`
                <td >${storename(json[i].store)}</td>
                <td >${statusName[json[i].status]}</td>
                <td >${json[i].car_model}</td>
                <td >${json[i].car_no}</td>
            `)
        }
        let day = 1;
        while (day <= 7) {
            let carIndex = 0;
            $('thead tr').append(`
                        <th>${startday.getMonth() + 1}/${startday.getDate()}</th>
                    `);

            $('tbody tr').each(function () { //分開新增 設定對應ID
                $(this).append(`
                        <td id="${json[carIndex].car_no}${startday.getDate()}"></td>
                    `);
                if (carIndex < json.length) {
                    carIndex++
                }
            })

            startday.setDate(startday.getDate() + 1); //每一次迴圈 將日期加一天
            day++;
        }

        for (const car of json) {
            let carNo = car.car_no;
            let storeindex = 0;//租還車地點索引
            const allstore = JSON.parse(sessionStorage.getItem('store'));//取得sessionStorage中的門市資訊
            let endname;//存放當下訂單還車門市
            let stratname;//存放取車門市
            let endst_no;//存放還車門市NO
            let start_no//存放取車門市NO
            for (const j of car.allday) {
                for (const stno of allstore) {
                    if (car.end_store[storeindex] === stno.st_no) {//判斷取得門市中文名稱
                        endname = stno.st_name;//判斷相等 存入中文
                        endst_no = stno.st_no;//存入門市NO
                        continue;
                    }

                    if (car.start_store[storeindex] === stno.st_no) {
                        stratname = stno.st_name;
                        start_no = stno.st_no;
                        continue;
                    }
                }
                for (let day = 0; day < j.length; day++) {
                    if (j.length - 1 == day) { //還車天
                        $(`#${carNo}${j[day]}`).text(`${endname}`).addClass(endst_no);
                        continue;
                    } else if (day == 0) { // 取車,租車天
                        $(`#${carNo}${j[day]}`).text(`${stratname}`).addClass(start_no);
                        continue;
                    }
                    $(`#${carNo}${j[day]}`).text(`${stratname}`).addClass(start_no);
                }
                storeindex++;
            }
            // for (const j of car.clean) {
            //     $(`#car_${car} td:eq(${j + 3})`).text("清潔");
            // }
        }

        //console.log(height)
        $('#cartable').DataTable({//將表格轉成套件顯示
            // scrollY: height,
            // scrollX: true,
            // scrollCollapse: true,
            paging: true, //啟用或禁用表格分頁。
            //fixedHeader: true,
            // fixedColumns: {
            //     left: 4
            // },
            language: { //國際化
                url: "https://cdn.datatables.net/plug-ins/1.12.1/i18n/zh-HANT.json",
            },
            "drawCallback": function () { //每次渲染一次都執行
                $('.pagination').addClass('pagination-sm');
            },
            "initComplete": function (settings, json) {
                $('#cartable_length').prepend(`<div id="showcity" style="display: inline-block;">${storeName}:外站車輛</div> `);
            }
        });

    };

    function storename(st_no) {
        const allstore = JSON.parse(sessionStorage.getItem('store'));
        for (const stno of allstore) {
            if (st_no === stno.st_no) {
                return stno.st_name;
            }
        }
    }
})