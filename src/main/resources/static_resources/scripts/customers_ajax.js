/*
* Script for Ajax Requests
*/
const ajaxUrl = "http://localhost:8080/Spring_Training_Grounds/customer/async";

/********************************************
    Update Elements
*********************************************/
function updateTable(data){
    //console.log(data);
    let newHtml=`<thead class="thead-dark">\
            <tr>\
                <th scope="col">First Name</th>\
                <th scope="col">Last Name</th>\
                <th scope="col">Email</th>\
            </tr>\
        </thead>`;
    data.forEach(customer =>{
        newHtml +=
            `<tr>\
                <td>${customer["first_name"]}</td>\
                <td>${customer["last_name"]}</td>\
                <td>${customer["email"]}</td>\
            </tr>`;
    });
    $("#info-table").html(newHtml);
}

/********************************************
    Requests
*********************************************/
function sendAddRequest(customer){
    $.ajax({
        method : "post",
        url : ajaxUrl + "/add",
        contentType : "application/json",
        data : customer,
        success : (data)=>{
            updateTable(data);
            $("#addModal").modal("hide");
        }
    });
}

/********************************************
    After Document Loading
*********************************************/
$(document).ready(function(){
    $("#add_confirm_btn").click(()=>{
        let customer = JSON.stringify({
            "first_name" : $("#inp_add_first_name").val(),
            "last_name" : $("#inp_add_last_name").val(),
            "email" : $("#inp_add_email").val() 
        });
        console.log("Adding Customer : ", customer);
        sendAddRequest(customer);
    });
});
