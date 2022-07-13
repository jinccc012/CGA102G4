    toastr.options = {
            "closeButton": true,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-bottom-right",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }

    let host = window.location.host;//下兩行包含這行 == getConextPath()
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;  // ws://localhot:8081/WebSocketChatWe/TogetherWS/james
    let webSocket;
    /* 開啟連線(網頁載入時)----------------------------------------------------------------------------- */
    function connect() {
        // 建立一個新的websocket，連到位於endPointURL的伺服器
    webSocket = new WebSocket(endPointURL);
    //開起連線
    webSocket.connect = function(event){
        console.log("開啟連線");
            }
    // 接收推送過來的訊息並顯示
    webSocket.onmessage = function(event){
        console.log("接收訊息");
        let jsonObj = JSON.parse(event.data);
        console.log(jsonObj);
        console.log(jQuery.type(jsonObj));
        if(jQuery.type(jsonObj) === "string"){
        	 toastr.info(jsonObj);
			}
			if(jQuery.type(jsonObj) === "array"){
			for(let i of jsonObj)
				 toastr.info(i);
			}
    }
    // 我的關閉連線是直接離開網頁，所以不需做任何頁面資訊修改
    webSocket.onclose = function(event){
        console.log("關閉連線");
    }
    }

// 關閉連線(離開網頁時)--------------------------------------------------------------------------------
	function disconnect(){  
		webSocket.close(); // 這時候觸發onclose
	}

