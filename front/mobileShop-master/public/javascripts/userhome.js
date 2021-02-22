/**
 * Created by gokul on 21-02-2018.
 */
/**
 * Created by gokul on 21-02-2018.
 */
$(document).ready(function () {
    bookList();
    feedbacklist();

    
    $("#upload").click(function(){
        var uid =6;
        var description = $("#comment").val().trim();
        if(description.length!=0){
        $.ajax({
            url: '/api/feedbackentry',
            data:{uid:uid,description:description},
            type: 'POST',
                success: function (data) {
                    alert(data)
                    feedbacklist();
                    
                   
                
            }
        });
    }
    });
    
});

function bookList(){
    $.ajax({
        url: '/api/booklist',
        type: 'POST',
            success: function (data) {
                console.log(data);
                
                let html='';
                for(var i=0;i<data.length;i++){
                   var row= data[i];
                 html =html+ ` <div class="col-sm-2">
                <center><img onclick="download('`+row.filename+`')" src="/images/pdf.png" style="height:80px;width:80px;cursor:pointer" class="img-thumbnail" alt="Cinque Terre"> </center>
                <center><h4 style="color:crimson">`+row.bname+`</h4></center>
                <center><h6>`+row.bname+`</h6></center>
            </div>`;
                }
            $("#tab1danger").html(html);
            
        }
    });
}

function download(filename){
 window.location.href = "/users/"+filename;
}

function logout(){
    window.location.href = "/signin";
}


function feedbacklist(){
    var html='';

    $.ajax({
        url: '/api/feedbacklist',
        type: 'POST',
            success: function (data) {
                console.log(data);
                
                let html='';
                for(var i=0;i<data.length;i++){
                   var row= data[i];
                   html=html+`<div class="form-group">
                   <label style="color:gray" col-sm-12>`+row.description+`</label>
                   <br>
                   <label class="col-sm-12" style="color:dodgerblue;text-align:right" >`+row.datetime+`</label>
               </div>`;
                }
                $("#feedbacks").html(html);
            
        }
    });
   
   
}