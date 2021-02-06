

$(document).ready(function(){
		
	load();
});

function load(){
	getMaterial();
	getMaterialName();
	getService();
	getHealth();
	getMembers();
}


function login(){
	
	var username = $("#username").val();
	var password = $("#password").val();
	
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/login/"+username+"/"+password,
			success: function(datas) {
				if(datas){
					 window.location="/gym/member-registration.html";
				}else{
				alert('Invalid username and password')	
				}
			},
		});	
}

function getMembers(){
	
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_members",
			success: function(datas) {
				var html =``;
				var html1 =``;
				for(var i=0;i<datas.length;i++){
						
						var data = datas[i];
						html+=`<tr>
							<td>`+data.name+`</td>
							<td><button onclick="punch_in('`+data.id+`')"  type="button" class="btn btn-success">IN</button></td>
							<td><button onclick="punch_out('`+data.id+`')" type="button" class="btn btn-danger">OUT</button></td>
						  </tr>`;
						  html1+=`<tr>`;
						  html1+=`<td>`+data.name+`</td>`;
						  html1+=`<td>`+data.lastname+`</td>`;
						  html1+=`<td>`+data.mobile+`</td>`;
						  html1+=`<td>`+data.email+`</td>`;
						  html1+=`<td>`+data.age+`</td>`;
						  html1+=`<td>`+data.gender+`</td>`;
						  html1+=`<td>`+data.height+`</td>`;
						  html1+=`<td>`+data.weight+`</td>`;
						  html1+=`<td>`+data.address+`</td>`;
						  html1+=`</tr>`;
						  
						
					}
					$("#punch_body").html(html);
					$("#member_body").html(html1);
					
			}
			});
}

function punch_in(id){
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/punch_in/"+id,
			success: function(data) {
				alert(data);
			
			}
			});
}

function punch_out(id){
$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/punch_out/"+id,
			success: function(data) {
				alert(data);
			
			}
			});
}

function addMaterial(){
	var material_name = $("#material_name").val();
	var quantity = $("#quantity").val();
	var amount = $("#amount").val();
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/add_material/"+material_name+"/"+quantity+"/"+amount,
			success: function(data) {
				alert(data);
				$("#material_name").val("");
				$("#quantity").val("");
				$("#amount").val("");
				
				load();
			}
			});
}

function getMaterialName(){
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_material",
			success: function(datas) {
				var html =``;
				for(var i=0;i<datas.length;i++){
						
						var data = datas[i];
						html+=`<option>`+data.material_name+`</option>`;
						
					}
					$("#material_names").html(html);
			}
			});
}

function getMaterial(){

		$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_material",
			success: function(datas) {
				var html =``;
				for(var i=0;i<datas.length;i++){
						
						var data = datas[i];
						html+=`<tr>
								<td>`+data.material_name+`</td>
								<td>`+data.quantity+`</td>
								<td>`+data.amount+`</td>						
							  </tr>`;
						
					}
					$("#material_body").html(html);
					
					
			}
			});
}

function memberregistration(){
	var firstname = $("#firstname").val();
	var lastname = $("#lastname").val();
	var mobile = $("#mobile").val();
	var email = $("#email").val();
	var age = $("#age").val();
	var gender = $("#gender").val();
	var height = $("#height").val();
	var weight = $("#weight").val();
	var address = $("#address").val();
	
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/member_register/"+firstname+"/"+lastname+"/"+mobile+"/"+email+"/"+age+"/"+gender+"/"+height+"/"+weight+"/"+address,
			success: function(data) {
				alert(data);
				$("#firstname").val("");
				$("#lastname").val("");
				$("#mobile").val("");
				$("#email").val("");
				$("#age").val("");
				$("#address").val("");
				$("#height").val("");
				$("#weight").val("");
				
				load();
			}
			});
}

function addService(){
	var material_name = $("#material_names").val();
	var service_name = $("#service_name").val();
	var amount = $("#amount").val();
	var issue = $("#issue").val();
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/add_service/"+material_name+"/"+service_name+"/"+amount+"/"+issue,
			success: function(data) {
				alert(data);
				//$("#material_name").val("");
				$("#service_name").val("");
				$("#amount").val("");
				$("#issue").val("");
				
				load();
			}
			});
}
function searPunch(){
	var mobile = $("#sear_mobile").val();
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_punch_details/"+mobile,
			success: function(datas) {
				var html =``;
				for(var i=0;i<datas.length;i++){
						
						var data = datas[i];
						html+=`<tr>
							<td>`+data.date+`</td>
							<td>`+data.in+`</td>
							<td>`+data.out+`</td>						
							<td>`+data.total+`</td>						
						  </tr>`;
						
					}
					$("#punch_body1").html(html);
			}
			});
}
function getService(){

		$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_service",
			success: function(datas) {
				var html =``;
				for(var i=0;i<datas.length;i++){
						
						var data = datas[i];
						html+=`<tr>
								<td>`+data.material_name+`</td>
								<td>`+data.provider+`</td>
								<td>`+data.amount+`</td>						
								<td>`+data.issue+`</td>						
							  </tr>`;
						
					}
					$("#service_body").html(html);
			}
			});
}

function addHealth(){
	var title = $("#title").val();
	var description = $("#description").val();

	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/add_health/"+title+"/"+description,
			success: function(data) {
				alert(data);
				$("#title").val("");
				$("#description").val("");
				
				load();
				
			}
			});
}

function getHealth(){
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_health",
			success: function(datas) {
				var html =``;
				for(var i=0;i<datas.length;i++){
						
						var data = datas[i];
						html+=`<tr>
								<td>`+data.title+`</td>
								<td>`+data.description+`</td>
								<td>`+data.date+`</td>						
												
							  </tr>`;
						
					}
					$("#health_body").html(html);
			}
			});
}
function logout(){
	
	window.location.href='/car/index.html';
}




