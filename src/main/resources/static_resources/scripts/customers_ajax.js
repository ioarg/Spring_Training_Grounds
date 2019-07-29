/*
* Script for Ajax Requests
*/
const ajaxUrl = "http://localhost:8080/Spring_Training_Grounds/customer/async/";
let customer_id = 0;

/*************************************************************
    Update Customers Table
*************************************************************/
function updateTable(data){
    //console.log(data);
    let newHtml=`<thead class="thead-dark">\
            <tr>\
                <th scope="col">First Name</th>\
                <th scope="col">Last Name</th>\
                <th scope="col">Email</th>\
                <th scope="col">Action</th>\
            </tr>\
        </thead>`;
    data.forEach(customer =>{
        newHtml +=
            `<tr>\
                <td>${customer["first_name"]}</td>\
                <td>${customer["last_name"]}</td>\
                <td>${customer["email"]}</td>\
                <td>\
                    <div class="button-container">\
                        <div id="edb_${customer.id}" class="btn btn-info tb_small_btn edit_btn">Edit Customer</div>\
                    </div>\
                    <div class="button-container">\
                        <div id="dlb_${customer.id}" class="btn btn-info tb_small_btn delete_btn">Delete Customer</div>\
                    </div>\
                </td>\
            </tr>`;
    });
    $("#info-table").html(newHtml);
}

/*************************************************************
    Prepopulate Edit Modal With Retrieved Data
    And Open It
*************************************************************/
function updateEditModal(customer_data){
    $("#inp_edit_first_name").val(customer_data["first_name"]);
    $("#inp_edit_last_name").val(customer_data["last_name"]);
    $("#inp_edit_email").val(customer_data["email"]);
    $("#editModal").modal("show");
}

/***********************************************************
    Ajax Update Data Request 
        - (String) operation - {add, edit, delete}
        - (JSON) customer - object with payload
        - (String) modal_id - the id of the modal to hide
***********************************************************/
function sendUpdateRequest(operation, customer, modal_id){
    $.ajax({
        method : "post",
        url : ajaxUrl + operation,
        contentType : "application/json",
        data : customer,
        success : (data)=>{
            updateTable(data);
            $(modal_id).modal("hide");
        }
    });
}

/***********************************************************
    Ajax Get Data Request 
***********************************************************/
function getOrDeleteCustomerByID(relative_url, customer_id, callback){
    $.get({
        url : ajaxUrl + relative_url,
        data : customer_id,
        success : (data)=>{callback(data);}
    });
}

/*********************************************************
    After Document Loading
*********************************************************/
$(document).ready(function(){
    //Add a customer    
    $("#add_confirm_btn").click(()=>{
        let customer = JSON.stringify({
            "first_name" : $("#inp_add_first_name").val(),
            "last_name" : $("#inp_add_last_name").val(),
            "email" : $("#inp_add_email").val() 
        });
        sendUpdateRequest("add", customer, "#addModal");
    });
    /* Edit a customer 
    * 1) Get customer data when "edit" is pressed
    * 2) Populate customer data to Edit Modal
    * 3) Send new data to server on confirmation
    */
    $(".edit_btn").click((button)=>{
        customer_id = button.target.id.substring(4);
        getOrDeleteCustomerByID("/retrieve", "id="+customer_id, updateEditModal);
    });

    $("#edit_confirm_btn").click(()=>{
        let customer = JSON.stringify({
            "id" : customer_id, 
            "first_name" : $("#inp_edit_first_name").val(),
            "last_name" : $("#inp_edit_last_name").val(),
            "email" : $("#inp_edit_email").val() 
        });
        sendUpdateRequest("add", customer, "#editModal");
    });
    /* Delete Customer :
    * 1) Open delete prompt
    * 2) Send request to database
    */
   $(".delete_btn").click((button)=>{
        customer_id = button.target.id.substring(4);
        $("#deleteModal").modal("show");
   })

   $("#delete_confirm_btn").click(()=>{
        getOrDeleteCustomerByID("delete", "id="+customer_id, updateTable);
        $("#deleteModal").modal("hide");
   });
});
