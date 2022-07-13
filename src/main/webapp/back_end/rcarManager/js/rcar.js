$(document).ready(function () {
  const xhr = new XMLHttpRequest();
  xhr.open("get", "../../back_end/rcarList");
  xhr.send(null);
  xhr.onload = function () {
    if (xhr.status == 200) {
      let json = JSON.parse(xhr.responseText);
      console.log(json[0].rcar_no);
      add(json);
    }
    $('#example').DataTable();
  }

  function add(json) {
    for (let i = 0; i < json.length; i++) {
      console.log(json[i].rcar_no);
      $('tbody').append(`
        <tr>
          <td id="car${json[i].rcar_no}">${json[i].rcar_no}</td>
          <td id="store${json[i].rcar_no}" name="ss">${json[i].st_no}</td>
          <td id="model${json[i].rcar_no}">${json[i].model_no}</td>
          <td id="loc${json[i].rcar_no}">${json[i].rcar_loc}</td>
          <td id="miles${json[i].rcar_no}">${json[i].miles}</td>
          <td id="status${json[i].rcar_no}">${json[i].rcar_status}</td>
          <td>
          <button type="input" class="btn btn-primary" id="${json[i].rcar_no}" onclick="modify(this)">修改</button>
          </td>
        </tr>
            `)
    }
  }
});

function modify(car){
  console.log('store'+car.id);
  console.log(document.getElementById('store'+car.id).innerText);
  
  let storeInput =`<input type="text" id="storeText" value="`+document.getElementById('store'+car.id).innerText+`"></input>`;
  let modelInput =`<input type="text" id="modelText" value="`+document.getElementById('model'+car.id).innerText+`"></input>`;
  let locInput=`<input type="text" id="locText" value="`+document.getElementById('loc'+car.id).innerText+`"></input>`;
  let milesInput=`<input type="text" id="milesText" value="`+document.getElementById('miles'+car.id).innerText+`"></input>`;
  let statusInput=`<input type="text" id="statusText" value="`+document.getElementById('status'+car.id).innerText+`"></input>`;
  let buttonInput=`<button class="btn btn-danger" name="`+car.id+`" onclick="update(this)"> 確認修改 </button>`
  
  document.getElementById('store'+car.id).innerHTML=storeInput;
  document.getElementById('model'+car.id).innerHTML=modelInput;
  document.getElementById('loc'+car.id).innerHTML=locInput;
  document.getElementById('miles'+car.id).innerHTML=milesInput;
  document.getElementById('status'+car.id).innerHTML=statusInput+buttonInput;
  document.getElementById(car.id).style.display="none";
  // document.getElementById(car.id).innerHTML=buttonInput;
  // document.getElementById('store'+car.id).innerText="";
  // tr.appendChild(text);
 

}

function update(car){
  
  console.log(car.name);
 
  $.ajax({
    method: 'post',
    url: '../../back_end/rcar.do',
    dataType: 'json',
    data: {
        rcarno: car.name,
        storeno: document.getElementById('storeText').value,
        modelno: document.getElementById('modelText').value,
        loc:document.getElementById('locText').value,
        miles:document.getElementById('milesText').value,
        rcarStatus:document.getElementById('statusText').value,
        action: 'update'
    },
    success: function (res) {
      console.log('ggg')
      window.location.reload();
    }
  })
}

