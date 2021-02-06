// Material Design example
$(document).ready(function () {
	
    update()
});

function update() {
    showCustomerList();
    viewCourier();
	viewBill();
}
function addCustomer() {
    var firstname = $("#firstname1").val();
	var lastname = $("#lastname1").val();
    var mobile = $("#mobile1").val();
	var email = $("#email1").val();
	var age = $("#age1").val();
	var gender = $("#gender1").val();
    var address = $("#address1").val();

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/addCustomer/'+firstname+'/'+lastname+'/'+mobile+'/'+email+'/'+age+'/'+gender+'/'+address,
        data:{
            'name': name,
            'mobile': mobile,
            'address': address
        },
    }).done(function (datas) {
        alert(datas);
        $("#firstname1").val("");
        $("#lastname1").val("");
        $("#mobile1").val("");
		$("#email1").val("");
        $("#age1").val("");
        $("#address1").val("");
		

        update();
    });
}

function showCustomerList() {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/viewCustomer'

    }).done(function (datas) {

        var html = ``;
        for (var i in datas) {
            var data = datas[i];

            html += ` <tr>
                        <th scope="row">`+(++i)+`</th>
                            <td>`+data.firstname+`</td>
                            <td>`+data.lastname+`</td>
                            <td>`+data.mobile+`</td>
							<td>`+data.email+`</td>
							<td>`+data.age+`</td>
							<td>`+data.gender+`</td>
							<td>`+data.address+`</td>`;
        }
        $("#customerbody").html(html)
    });
}

function viewBill(){
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/viewBill'

    }).done(function (datas) {

        var html = ``;
        for (var i in datas) {
            var data = datas[i];

            html += ` <tr>
                        <th scope="row">`+(++i)+`</th>
                            <td>`+data.name+`</td>
                            <td>`+data.amount+`</td>
							<td>`+data.weight+`</td>
							<td>`+data.discount+`%</td>
                            <td>`+data.date+`</td>`;
        }
        $("#billbody").html(html)
    });
}

function addCourier() {
    var name = $("#name3").val();
    var mobile = $("#mobile3").val();
    var address = $("#address3").val();
    var sendlocation = $("#send3").val();
	var amount = $("#amount3").val();
	var weight = $("#weight").val();
	var discount = $("#discount").val();
	


    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/addCourier/'+name+'/'+mobile+'/'+sendlocation+'/'+address+'/'+amount+'/'+weight+'/'+discount,
        data: {
            name: name,
            mobile: mobile,
            sendlocation: sendlocation,
            fulladdress:address
        }
    }).done(function (datas) {
        alert(datas);
        $("#name3").val("");
        $("#mobile3").val("");
        $("#address3").val("");
        $("#send3").val("");
        $("#weight").val("");
        $("#discount").val("");
	$("#amount3").val("");	
        update();
    });
}

function viewCourier() {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/viewCourier',

    }).done(function (datas) {

        var html = ``;
        for (var i in datas) {
            var data = datas[i];

            html += ` <tr>
                        <th scope="row">`+data.id+`</th>
                            <td>`+data.name+`</td>
                            <td>`+data.mobile+`</td>
                            <td>`+data.address+`</td>
                            <td>`+data.sendlocation+`</td>
                            <td>`+data.currentlocation+`</td>
                        </tr>`;
        }
        $("#courierbody").html(html)
    });
}

function updateCourier() {

    var courierno = $("#courierno").val();
    var currentlocation = $("#currentlocation").val();

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/updateCourier/'+courierno+'/'+currentlocation,
        data: {
            courierno: courierno,
            currentlocation: currentlocation
        }
    }).done(function (datas) {
        alert(datas);
        $("#courierno").val("");
        $("#currentlocation").val("");


        update();
    });
}