$(document).ready(function () {
    const height = $('.main').height() * 0.81;//控制DataTable高度
    //const month = new Date().getMonth();//當月前一個月 計算從0開始
    let emp = JSON.parse(sessionStorage.getItem('employee'));
    let city = emp.st_no;// 初始顯示哪個門市 
    let now = new Date(); 
    // now.setMonth(new Date().getMonth() + 1);
    let current_month = now.toISOString().split('T')[0];
    // 頁面刷新印出所有站所
    const storexhr = new XMLHttpRequest();
    storexhr.open("get", "../../storeshow", true);
    storexhr.send(null);
    storexhr.onload = function () {
        if (storexhr.status == 200) {
            sessionStorage.setItem('store', storexhr.responseText)
            //let json = JSON.parse(storexhr.responseText);

            //初始顯示表格
            const xhr = new XMLHttpRequest();
            xhr.open('get', `../../storeshow?month=${new Date().toISOString().split('T')[0]}&&store=${city}`, true);
            xhr.send(null);
            xhr.onload = function () {
                if (xhr.status == 200) {
                    let json = JSON.parse(xhr.responseText);
                    console.log(json)
                    add(json, now.getMonth() + 1);
                    Storeshow(JSON.parse(sessionStorage.getItem('store')));
                    //初始顯示查詢調車紀錄
                    $.ajax({
                        method: 'post',
                        url: '../../getdispatchservlet',
                        dataType: 'json',
                        data: {
                            month: current_month,
                            store: emp.st_no,
                            status: 'selfstore'
                        },
                        success: function (res) {
                            console.log(res)
                            showDr(res);
                        }

                    })
                }
            }
        }
    }

    function showDr(json) {
            for (let dr of json) {
                let drDate = new Date(dr.dr_start_time);
                let date = drDate.getDate();
                for (let i = 1; i <= 2; i++) {
                    $(`#car_${dr.rcar_no} td:eq(${date + 3})`).text(`調${dr.dr_apply_st}`).addClass('distribution');
                    date++;
                }

            }
        }

    //創出表格
    function add(json, month) {
        const statusName = ['報廢', '空車', '出車', '還車', '維修', '中古車'];
        const dated = new Date(new Date().getFullYear(), month, 0).getDate();
        $('#cartable').append(`
            <thead>
                <tr >
                    <th >車籍</th>
                    <th >狀態</th>
                    <th >車型</th>
                    <th >車牌</th>
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

        for (let i = 1; i <= dated; i++) {
            $('thead tr').append(`
                        <th>${month}/${i}</th>
                    `);
            $('tbody tr').append(`
                        <td></td>
                    `);
        }

        for (const car of json) {
            let carNo = car.car_no;
            let storeindex = 0;//取得還車地點索引
            const allstore = JSON.parse(sessionStorage.getItem('store'));//取得sessionStorage中的門市資訊
            let endname;//存放當下訂單還車門市
            let stratname;//存放取車門市
            let endst_no;//存放還車門市NO
            let start_no//存放取車門市NO
            for (const j of car.allday) {
                for (const stno of allstore) {
                    if ((car.end_store[storeindex] === car.store) && (car.start_store[storeindex] === car.store) && (stno.st_no == car.store)) {
                        endname = '出車';
                        endst_no = stno.st_no;
                        continue;
                    }

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
                        if (j[day] === dated && car.end_store.length !== car.allday.length) { //判斷 還車時間在下個月
                            endname = stratname;
                            endst_no = start_no;
                            if (endst_no === car.store) {
                                endname = '出車'
                            }
                        }
                        $(`#car_${carNo} td:eq(${j[day] + 3})`).text(`${endname}`).addClass(endst_no);
                    } else if ((car.start_store[storeindex] !== car.store)) { // 取車,租車天
                        $(`#car_${carNo} td:eq(${j[day] + 3})`).text(`${stratname}`).addClass(start_no);
                    } else {
                        $(`#car_${carNo} td:eq(${j[day] + 3})`).text(`出車`).addClass(car.store);
                    }
                }
                storeindex++;
            }
            // for (const j of car.clean) {
            //     $(`#car_${car} td:eq(${j + 3})`).text("清潔");
            // }
        }

        //console.log(height)
        $('#cartable').DataTable({//將表格轉成套件顯示
            scrollY: height,
            scrollX: true,
            scrollCollapse: true,
            paging: false, //啟用或禁用表格分頁。
            //fixedHeader: true,
            fixedColumns: {
                left: 4
            },
            language: {
                "info": " _TOTAL_台車",
                "search": "搜尋:",
                "infoFiltered": "",
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

    function Storeshow(json) {
        const dated = new Date(new Date().getFullYear(), now.getMonth(), 0).getDate();
        $('#cartable_wrapper').prepend(`<div id="showcity" style="display: inline-block;"><select name="" id="month" class="month"></select><span>車輛配車表</span></div>`);
        let end = now.getMonth() + 4;
        let Storeshownow = new Date();
        for (let i = now.getMonth() ; i <= end; i++) {
            let nowmonth = i;


            if (i == 13) {
                Storeshownow.setFullYear(Storeshownow.getFullYear() + 1);
            }

            if (i > 12) {
                nowmonth = i - 12;
            }
            Storeshownow.setMonth(nowmonth - 1);
            if (i == new Date(current_month).getMonth() + 1) {
                $('#month').append(`
                <option value="${Storeshownow.toISOString().split('T')[0]}" selected="selected">${nowmonth}月</option>
            `)
            } else {
                $('#month').append(`
                <option value="${Storeshownow.toISOString().split('T')[0]}">${nowmonth}月</option>
            `)
            }
        }

        for (let allcity of json) {
            let checked = '';
            if (allcity.st_no === city && allcity.st_no != 'TPEHO') {
                checked = 'checked';
            }else if(allcity.st_no == 'TPEHO'){
                continue;
            }
            let input = `
            <label for="${allcity.st_name}" class="${allcity.st_no}">${allcity.st_name}<input type="radio"class="city" id="${allcity.st_name}" name="city" value="${allcity.st_no}" ${checked}></label>`;
            $('#showcity').prepend(input);
        }
    }

    $("#show").on('change', '#showcity', function (e) {
        const target = e.target;
        if (target.classList.contains('city')) {
            city = target.value;
        } else {
            current_month = target.value;
        }
        //切換門市觸發,顯示相對應內容
        const ch = new XMLHttpRequest();
        ch.open("get", `../../storeshow?click='ok'&store=${city}&changemonth=${current_month}`, true);
        ch.send(null);
        ch.onload = function () {
            if (ch.status == 200) {
                let json = JSON.parse(ch.responseText);
                //將原有表格刪除並重新新增
                $('#cartable_wrapper').remove();
                const table = `<table border="1" id="cartable" class="table table-striped table-bordered border-dark">
                    </table>`
                $('#show').append(table);
                let changemonth = new Date(current_month).getMonth() + 1; //將選取時間用 .getMonth() + 1 取得現在
                add(json, changemonth);
                console.log(json)
                Storeshow(JSON.parse(sessionStorage.getItem('store')));

                //切換 查詢調車紀錄
                $.ajax({
                    method: 'post',
                    url: '../../getdispatchservlet',
                    dataType: 'json',
                    data: {
                        month: current_month,
                        store: city,
                        status: 'selfstore'
                    },
                    success: function (res) {
                        console.log(res)
                        showDr(res);
                    }

                })
            }
        }
    })
})