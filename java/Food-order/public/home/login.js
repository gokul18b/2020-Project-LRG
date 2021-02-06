$(document).ready(function () {
    $("#login").on('click',function(){
        var username = $("#username").val();
        var password = $("#password").val();

        $.ajax({
            type: 'POST',
            url: '/api/login',
            data: { username:username,password: password },
        }).done(function (datas) {
    
            if(datas[0].count==0){
                alert("Invalid username and password")
            }else{
                window.location="/waiter_home"
            }
            
        });
    })
});