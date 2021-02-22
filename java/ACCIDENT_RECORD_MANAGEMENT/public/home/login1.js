$(document).ready(function () {
    
    $("#login").on('click',function(){
        var username=$("#username").val();
    var password =$("#password").val();
    
        if(username=="admin" && password=="admin"){
            window.location="/home";
        }else{
            alert("Invalid username or password")
            
        }
    })

});