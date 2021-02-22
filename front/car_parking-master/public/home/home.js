// Material Design example

var checkout_mall_id = '';
var billing_mall_id = '';
$(document).ready(function () {




    $('.dataTables_length').addClass('bs-select');

    update();


});

$('.nav-tabs-dropdown').each(function (i, elm) {

    $(elm).text($(elm).next('ul').find('li.active a').text());

});

$('.nav-tabs-dropdown').on('click', function (e) {

    e.preventDefault();

    $(e.target).toggleClass('open').next('ul').slideToggle();

});

$('#nav-tabs-wrapper a[data-toggle="tab"]').on('click', function (e) {

    e.preventDefault();

    $(e.target).closest('ul').hide().prev('a').removeClass('open').text($(this).text());

});

function update() {
    getCustomers();
    getMall();


}

function addCustomer() {

    var name = $("#name").val();
    var mobile = $("#mobile").val();
    var password = $("#password").val();
    var vehicle = $("#vehicle").val();
    var idproof = "";
    var rcno = "";
    var address = $("#address").val();

    $.ajax({
        type: "POST",
        data: {
            name: name,
            mobile: mobile,
            password: password,
            vehicle: vehicle,
            idproof: idproof,
            rcno: rcno,
            address: address,
        },
        url: "/api/add_customer", success: function (result) {
            alert(result)
            $("#name").val("");
            $("#mobile").val("");
            $("#password").val("");
            $("#vehicle").val("");
            // $("#idproof").val("");
            // $("#rcno").val("");
            $("#address").val("");
            update();
        }
    });
}

function getCustomers() {
    $.ajax({
        type: "GET",
        url: "/api/get_customers",
        success: function (result) {
            var html = ``;
            for (var i in result) {
                var data = result[i];
                html += `<tr>
                                <td>` + (++i) + `</td>
                                <td>` + data.name + `</td>
                                <td>` + data.mobile + `</td>
                                <td>` + data.vehicle + `</td>
                               
                                <td>` + data.address + `</td>
                            </tr>`;
            }
            $("#customer_body").html(html)
            $('#addcustomer_table').DataTable();
        }
    });

}

function getMall() {
    $.ajax({
        type: "GET",
        url: "/api/get_mall", success: function (result) {
            var html = ``;
            var billing_html = ``;
            checkout_mall_id = result[0].id
            loadTable(checkout_mall_id);
            for (var i in result) {
                var data = result[i];
                html += `
                 <li role="presentation" onclick="loadTable('` + data.id + `')" class="brand-nav active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">` + data.name + `</a></li>`;

                billing_html += `
                 <li role="presentation" onclick="loadBillingTable('` + data.id + `')" class="brand-nav active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">` + data.name + `</a></li>`;
            }
            $("#menulist").html(html)
            $("#billingmenulist").html(billing_html)
        }
    });
}

function loadTable(mid) {
    checkout_mall_id = mid;
    $.ajax({
        type: "GET",
        url: "/api/get_checkout_list/" + mid,
        success: function (result) {
            var html = ``;
            for (var i in result) {
                var data = result[i];
                var date = new Date(data.intime)
                var in_date_time = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();

                html += `
                            <tr>
                                <td>T` + data.token + `</td>
                                <td>` + in_date_time + `</td>
                                <td>` + data.name + `</td>
                                <td>` + data.mobile + `</td>
                                <td>` + data.vehicle + `</td>
                                
                                <td>` + data.address + `</td>
                            </tr>
                 `;
            }
            $("#checkout_body").html(html)
            $('#checkout_table').DataTable();
        }
    });
}

function loadBillingTable(mid) {

    billing_mall_id = mid;


    $.ajax({
        type: "GET",
        url: "/api/get_billing/" + billing_mall_id,
        success: function (result) {
            var html = ``;
            for (var i in result) {
                var data = result[i];
                //var date = new Date(data.intime)
                //var in_date_time = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();

                html += `
                            <tr>
                                    <td>`+data.token+`</td>
                                    <td>`+data.name+`</td>
                                    <td>`+data.mobile+`</td>
                                    <td>`+data.vehicle+`</td>
                                    
                                    <td>`+data.address+`</td>
                                    <td>`+data.intime+`</td>
                                    <td>`+data.outtime+`</td>
                                    <td>`+data.days+`</td>
                                    <td>`+data.amount+`</td>
                                </tr>
                 `;
            }



            $("#billing_body").html(html)
            $('#billing_table').DataTable();
        }
    });
}


function getCheckout_details() {

    var token = $("#co_token").val();
    $.ajax({
        type: "GET",
        url: "/api/get_checkout_details_by_token/" + token + "/" + checkout_mall_id,
        success: function (result) {
            var data = result[0];
            var date = new Date(data.intime);
            var new_date = new Date();


            var dt1 = new Date(date);
            var dt2 = new Date(new_date);

            var total_hours = diff_hours(dt1, dt2);
            var days = Math.round(total_hours / 24);
            var amount_perday = 100;
            var total_amount = 1;
            if (days < 1) {
                total_amount = amount_perday;
            } else {
                total_amount = days * amount_perday;
            }


            var in_date_time = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();

            $("#co_in_time").val(in_date_time);
            $("#co_days").val(days);
            $("#co_amount").val(total_amount);
            $("#co_name").val(data.name);
            $("#co_mobile").val(data.mobile);
            $("#co_vehicle").val(data.vehicle);

            $("#co_address").val(data.address);

            $("#addCheckout").click(function () {
                var mid = checkout_mall_id;
                var no_of_days = days;
                var amount = total_amount
                var checkout_time = new Date();
                var cid = data.id;


                $.ajax({
                    type: "POST",
                    data: {
                        mid: mid,
                        no_of_days: no_of_days,
                        amount: amount,
                        checkout_time: checkout_time.getFullYear()+"-"+(checkout_time.getMonth()+1)+"-"+checkout_time.getDate()+" "+checkout_time.getHours()+":"+checkout_time.getMinutes+":"+checkout_time.getSeconds(),
                        cid: cid,
                        bookid: data.bookid
                    },
                    url: "/api/add_checkout", success: function (result) {
                        alert(result)
                        $("#co_token").val("");
                        $("#co_in_time").val("");
                        $("#co_days").val("");
                        $("#co_amount").val("");
                        $("#co_name").val("");
                        $("#co_mobile").val("");
                        $("#co_vehicle").val("");

                        $("#co_address").val("");
                        update();
                    }
                });


            });
        }
    });


}


function diff_hours(dt2, dt1) {

    var diff = (dt2.getTime() - dt1.getTime()) / 1000;
    diff /= (60 * 60);
    return Math.abs(Math.round(diff));

}


