if ('WebSocket' in window) {

    let store = JSON.parse(sessionStorage.getItem('employee')).st_no;

	let host = window.location.host;
	let path = window.location.pathname;
	let webCtx = path.substring(0, path.indexOf('/', 1));

    websocket = new WebSocket(`ws://`+host+webCtx+`/websockets/${store}`);


    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket連線錯誤");
    };
    //连接成功建立的回调方法
    websocket.onopen = function () {
        // setMessageInnerHTML("WebSocket連線成功");
    }
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }
    //连接关闭的回调方法
    websocket.onclose = function () {
        // setMessageInnerHTML("WebSocket連線關閉");
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('notice').innerHTML += `
            <div class=" fade show toast " role="alert" aria-live="assertive" aria-atomic="true" autohide= 'false'>
                <div class="toast-header">
                <strong class="me-auto">Family Rent</strong>
                <button type="button"  class="btn-close wbclose" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body">`+ innerHTML + `</div>
            </div>
            `;

    }
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }



}
else {
    alert('當前瀏覽器 Not support websocket')
}