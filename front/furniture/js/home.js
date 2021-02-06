var customer_id;
$(document).ready(function(){
	
	load();
	
	
	$("#sal_mobile").change(function(){
		customer_id='';
		$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_customer/"+$("#sal_mobile").val(),
			success: function(datas) {
				alert(datas)
				customer_id=datas;
			},
		});	
	}); 
	
});

function load(){
	getCustomer();
	
	getProduct();
	getStock();
	getBill();
}

function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/login/"+username+"/"+password,
			success: function(datas) {
				if(datas){
					 window.location="/furniture/customer-registration.html";
				}else{
				alert('Invalid username and password')	
				}
			},
		});	
}

function addCustomer(){

	var name = $("#name").val();
	var mobile = $("#mobile").val();
	var alternate = $("#alternate").val();
	var address = $("#address").val();
	var gender = $("#gender").val();
	
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/add_customer/"+name+"/"+mobile+"/"+alternate+"/"+address+"/"+gender,
			success: function(datas) {
				alert(datas);
				$("#name").val("");
				$("#mobile").val("");
				$("#address").val("");
				$("#gender").val("");
				$("#alternate").val("");
				load();
			},
		});	
}

function getCustomer(){
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_customer",
			success: function(datas) {
				var html = ``;
				for (var i in datas) {
					var data = datas[i];

					html += ` <tr>
								<th scope="row">`+(++i)+`</th>
									<td>`+data.name+`</td>
									<td>`+data.mobile+`</td>
									<td>`+data.alternate+`</td>
									<td>`+data.address+`</td>
									<td>`+data.gender+`</td>
									
									`;
				}
				$("#table_body").html(html)
			},
		});	
}





function addProduct(){
	var company = $("#company").val();
	var model = $("#model").val();
	var price = $("#price").val();
	
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/add_product/"+company+"/"+model+"/"+price,
			success: function(datas) {
				alert(datas);
				$("#company").val("");
				$("#model").val("");
				$("#price").val("");
				
				load();
			},
		});	
}

function getProduct(){
	
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_product",
			success: function(datas) {
				var html = ``;
				for (var i in datas) {
					var data = datas[i];
					html+=`<option id=`+data.id+`>`+data.company+` - `+data.model+`</option>`;
					
				}
				$("#purchase_product").html(html)
				$("#sales_product").html(html)
				
			},
		});	
}


function addPurchase(){
	
	var product_id = $("#purchase_product").find('option:selected').attr('id');
	var quantity = $("#quantity").val();
	var details = $("#details").val();
	
	
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/add_purchase/"+product_id+"/"+quantity+"/"+details,
			success: function(datas) {
				alert(datas);
				$("#quantity").val("");
				$("#details").val("");
			
				
				load();
			},
		});	
}


function addSales(){
	if(customer_id!=''){
	
	var product_id = $("#sales_product").find('option:selected').attr('id');
	var quantity = $("#quantity").val();
	
	
	
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/add_sales/"+customer_id+"/"+product_id+"/"+quantity,
			success: function(datas) {
				alert(datas);
				$("#quantity").val("");
			
				$("#sal_mobile").val("");
				load();
			},
		});	
	}else{
		alert('invalid customer number')
	}
}


function getStock(){
	
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_stock",
			success: function(datas) {
				var html = ``;
				for (var i in datas) {
					var data = datas[i];
					html+=`<tr>
							  <th scope="row">`+(++i)+`</th>
							  <td>`+data.company_name+`</td>
							  <td>`+data.details+`</td>
							  <td>`+data.quantity+`</td>
							  
							</tr>`;
					
				}
				$("#stockbody").html(html)
				
				
			},
		});	
}

function getBill(){
	
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/get_billing",
			success: function(datas) {
				var html = ``;
				for (var i in datas) {
					var data = datas[i];
					html+=`<tr>
					  <th scope="row">`+(++i)+`</th>
					  <td>`+data.customer_name+`</td>
					  <td>`+data.mobile+`</td>
					  <td>`+data.company+`</td>
					  <td>`+data.model+`</td>
					  <td>`+data.quantity+`</td>
					  <td>`+(data.price*data.quantity)+`</td>
					</tr>`;
					
				}
				$("#billbody").html(html)
				
				
			},
		});	
}
