let order_status = document.querySelector('#order_status');
let cancelorderbtn = document.querySelector('.cancelorderbtn');
if(order_status.value !== '訂單成立'){
    cancelorderbtn.setAttribute('disabled',true);
};