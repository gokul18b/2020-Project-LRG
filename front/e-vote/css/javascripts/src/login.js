$(document).ready(function(){

});
function login(){
	var username=$("input[name=username]").val().trim();
	var password=$("input[name=password]").val().trim();
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/login/"+username+"/"+password,
			data:{username:username,password:password},
			success: function(datas) {
				if(datas.length==0){
					swal( "Invalid Input", 'Enter valid username and password!', 'error');
				}else{
					var data = datas[0];
					if(data.usertype==1){
						var userid=data.userid;
						window.location.href="/e-vote/admin.html?id="+userid;
						
					}else if(data.usertype==0){
						var userid=data.userid;
						window.location.href="/e-vote/voter.html?id="+userid;
					}
				}
				
			},
			dataType: 'json',
		});
}