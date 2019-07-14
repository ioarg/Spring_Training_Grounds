const ajaxUrl = "http://localhost:8080/Spring_Training_Grounds/streams_training/async";

function updateFilteredTable(data){
    //console.log(data);
    let newHtml=`<table class="table table-custom table-striped">\
        <thead class="thead-dark">\
            <tr>\
                <th scope="col">Name</th>\
                <th scope="col">Vegetarian</th>\
                <th scope="col">Calories</th>\
                <th scope="col">Type</th>\
            </tr>\
        </thead>`;
    data.forEach(dish =>{
        newHtml +=
            `<tr>\
                <td>${dish["name"]}</td>\
                <td>${dish["vegetarian"]}</td>\
                <td>${dish["calories"]}</td>\
                <td>${dish["type"]}</td>\
            </tr>`;
    });
    newHtml += `</table>`;
    $("#result-container").html(newHtml);
}

function sendMealFilterRequest(mealFilter){
    $.get({
        url : ajaxUrl,
        data : {"filter" : mealFilter},
        success : (data)=>{updateFilteredTable(data);}
    });
}

$(document).ready(function(){
    $("#high_cl_btn").click(()=>{
        sendMealFilterRequest("highCalories");
    });
});