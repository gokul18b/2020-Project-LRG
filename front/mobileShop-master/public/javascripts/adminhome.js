/**
 * Created by gokul on 21-02-2018.
 */
/**
 * Created by gokul on 21-02-2018.
 */
$(document).ready(function () {
//feedbacklist();

$('#smobile').change(function(){
    var mobile = $("#smobile").val();
    $.ajax({
        url: '/api/searchMobile',
        type: 'POST',
        data: {
            mobile:mobile,
            
        },
        success: function (data) {
            alert(JSON.stringify(data));
        
            $("#sname").val(data[0].name);
            $("#smodel").val(data[0].model);
            $("#scompany").val(data[0].company);
            $("#sprice").val(data[0].price);
        }
    
});
});

    $('#upload').click(function () {
        var bookid = $("#mobileid").val().trim();
        var bookname = $("#companyname").val().trim();
        var authorname = $("#modelno").val().trim();
        var publishyear = $("#ssprice").val().trim();
        var filename = $("#upload-input").val().trim();
		
		
        if (bookid.length == 0 || bookname.length == 0 || authorname.length == 0 || publishyear.length == 0) {
            alert("Should Fill all datas")
        } else {
            if (filename.length == 0) {
                alert("Should upload file");
            } else {
                var files = $('#upload-input').get(0).files;
                if (files.length > 0) {
                    var formData = new FormData();
                    for (var i = 0; i < files.length; i++) {
                        var file = files[i];
                        formData.append('uploads[]', file, file.name);
                    }

                    $.ajax({
                        url: '/api/uploadDatas',
                        type: 'POST',
                        data: {
                            mobileid:bookid,
                            cname:bookname,
                            modelno:authorname,
                            price:publishyear,
                            img:file.name
                        },
                        success: function (data) {
                            if(data=="true") {
                                $.ajax({
                                    url: '/api/upload',
                                    type: 'POST',
                                    data: formData,
                                    processData: false,
                                    contentType: false,
                                    success: function (data) {
                                        console.log('upload successful!\n' + data);
                                        $("#mobileid").val("")
                                        $("#modelno").val("")
                                        $("#companyname").val("")
                                        $("#price").val("")
                                        $("#upload-input").val("")

                                        alert("Your book has been successfully updated...")
                                        MobileList();
                                    }
                                });
                            }else{
                                alert("The Book Already Uploaded")
                            }


                        }
                    });



                }
            }
        }



    });

    $('#download').click(function () {
        window.location.href = "/users";
    });

    MobileList();
});

function MobileList(){
    $.ajax({
        url: '/api/mobilelist',
        type: 'POST',
            success: function (data) {
                console.log(data);
                
                let html='';
                for(var i=0;i<data.length;i++){
                   var row= data[i];
                  
                 html =html+ ` <div class="col-sm-2">
                <center><img onclick="download('`+row.cname+`','`+row.modelno+`','`+row.price+`')" src="/uploads/`+row.img.trim()+`" style="height:80px;width:80px;cursor:pointer" class="img-thumbnail" alt="Cinque Terre"> </center>
                <center><h4 style="color:crimson">`+row.cname+" " + row.modelno+`</h4></center>
                <center><h6>`+row.price+`</h6></center>
            </div>`;
                }
            $("#list").html(html);
            
        }
    });
}

function download(cname,model,price){
    $("#model").val(model);
    $("#company").val(cname);
    $("#price").val(price);
}

function recharge(){
    var mobile =$("#rmobile").val();
    var amount = $("#ramount").val();

    $.ajax({
        url: '/api/recharge',
        data:{mobile:mobile,amount:amount},
        type: 'POST',
            success: function (data) {
                alert("Successfully Recharged")
                $("#rmobile").val("");
                $("#ramount").val("");
                
            }
    });


}
function pay(){
    var model =$("#model").val();
    var company = $("#company").val();
    var price =$("#price").val();
    var name = $("#name").val();
    var mobile = $("#mobile").val();

    $.ajax({
        url: '/api/addbill',
        data:{model:model,company:company,price:price,name:name,mobile:mobile},
        type: 'POST',
            success: function (data) {
                alert("Successfully Pay")
                $("#model").val("");
                $("#company").val("");
                $("#price").val("");
                $("#name").val("");
                $("#mobile").val("");
            }
    });

}

function service(){
    var model =$("#smodel").val();
    var company = $("#scompany").val();
    var price =$("#sprice").val();
    var name = $("#sname").val();
    var mobile = $("#smobile").val();

    $.ajax({
        url: '/api/addservice',
        data:{model:model,company:company,price:price,name:name,mobile:mobile},
        type: 'POST',
            success: function (data) {
                alert("Successfully Service Added")
                $("#smodel").val("");
                $("#scompany").val("");
                $("#sprice").val("");
                $("#sname").val("");
                $("#smobile").val("");
            }
    });

}

function logout(){
    window.location.href = "/";
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