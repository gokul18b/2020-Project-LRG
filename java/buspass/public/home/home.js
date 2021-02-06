// Material Design example
$(document).ready(function () {
    $('#adduser_table').DataTable();
    $('#passgeneration').DataTable();
    $('#renewal_table').DataTable();
    $('#billing_table').DataTable();
    $('.dataTables_length').addClass('bs-select');

    $("#author2").on('change', function () {
        var author = $("#author2").val();

        $.ajax({
            type: 'POST',
            url: '/api/update_passenger',
            data: {
                author: author,
            },
        }).done(function (datas) {

            var data = datas[0];
            $("#name2").val(data.name);
            $("#mobile2").val(data.mobile);
            $("#author2").val(data.author);
            $("#gender2").val(data.gender);
            $("#address2").val(data.address);
            $("#age2").val(data.age);
            $("#pincode2").val(data.pincode);
        });
    })


    $("#author3").on('change', function () {
        var author = $("#author3").val();

        $.ajax({
            type: 'POST',
            url: '/api/update_passenger_renewal',
            data: {
                author: author,
            },
        }).done(function (datas) {

            var data = datas[0];
            $("#fromplace3").val(data.from_place);
            $("#toplace3").val(data.to_place);
            $("#amount3").val(data.amount);
            $("#name3").val(data.name);
            $("#mobile3").val(data.mobile);
            $("#gender3").val(data.gender);
            $("#address3").val(data.address);
            $("#age3").val(data.age);
            $("#pincode3").val(data.pincode);
        });
    })

    $("#adduser1").on('click', function () {
        var name = $("#name1").val();
        var mobile = $("#mobile1").val();
        var author = $("#author1").val();
        var address = $("#address1").val();
        var age = $("#age1").val();
        var pincode = $("#pincode1").val();
        var gender = $("#gender1 option:selected").html();


        $.ajax({
            type: 'POST',
            url: '/api/add_passenger',
            data: {
                name: name,
                mobile: mobile,
                author: author,
                address: address,
                age: age,
                pincode: pincode,
                gender: gender
            },
        }).done(function (datas) {
            alert(datas);
            $("#name1").val("");
            $("#mobile1").val("");
            $("#author1").val("");
            $("#address1").val("");
            $("#age1").val("");
            $("#pincode1").val("");
        });
    });

    $("#add2").on('click', function () {
        var author = $("#author2").val();
        var name = $("#name2").val();
        var mobile = $("#mobile2").val();
        var address = $("#address2").val();
        var age = $("#age2").val();
        var pincode = $("#pincode2").val();
        var gender = $("#gender2").val();
        var from = $("#from2").val();
        var to = $("#to2").val();
        var amount = $("#amount2").val();

        $.ajax({
            type: 'POST',
            url: '/api/add_pass',
            data: {
                author: author,
                name: name,
                mobile: mobile,
                address: address,
                age: age,
                pincode: pincode,
                gender: gender,
                from: from,
                to: to,
                amount: amount,

            },
        }).done(function (datas) {
            alert(datas);
            $("#name2").val("");
            $("#mobile2").val("");
            $("#author2").val("");
            $("#address2").val("");
            $("#age2").val("");
            $("#gender2").val("");
            $("#pincode2").val("");
            $("#from2").val("");
            $("#to2").val("");
            $("#amount2").val("");
        });
    });

    $("#add3").on('click', function () {
        var author = $("#author3").val();
        var from = $("#from3").val();

        var to = $("#to3").val();
        var from_place = $("#fromplace3").val();
        var to_place = $("#toplace3").val();
        var amount = $("#amount3").val();
        var name = $("#name3").val();
        var mobile = $("#mobile3").val();
        var gender = $("#gender3").val();
        var address = $("#address3").val();
        var age = $("#age3").val();
        var pincode = $("#pincode3").val();

        $.ajax({
            type: 'POST',
            url: '/api/add_renewal',
            data: {

                author: author,

                from: from,
                to: to,
                from_place: from_place,
                to_place: to_place,
                amount: amount,
                name: name,
                mobile: mobile,
                gender: gender,
                address: address,
                age: age,
                pincode: pincode,
            },
        }).done(function (datas) {
            alert(datas);
            $("#author3").val("");
            $("#from3").val("");
            $("#to3").val("");
            $("#fromplace3").val("");
            $("#toplace3").val("");
            $("#amount3").val("");
            $("#name3").val("");
            $("#mobile3").val("");
            $("#gender3").val("");
            $("#address3").val("");
            $("#age3").val("");
            $("#pincode3").val("");
        });
    });

    $("#search").on('click', function () {

        var author = $("#author4").val();

        $.ajax({
            type: 'POST',
            url: '/api/search_passenger',
            data: {

                author: author,

            },
        }).done(function (datas) {
            
            var html = ``;
            for (var i in datas) {
                var data = datas[i];
                html += ` <div class="col-xs-12 col-sm-6 col-md-4">
                    <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                        <div class="mainflip">
                            <div class="frontside">
                                <div class="card">
                                    <div class="card-body text-center">
                                    
                                        <h4 class="card-title">NAME : `+data.name+`</h4>
                                        <h4 class="card-text">MOBILE : `+data.mobile+`</h4>
                                        <h4 class="card-text">GENDER : `+data.gender+`</h4>
                                        <h4 class="card-text">ADDRESS : `+data.address+`</h4>
                                        <h4 class="card-text">AUTHOR : `+data.author+`</h4>
                                        <a href="#" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="backside">
                                <div class="card">
                                    <div class="card-body text-center">
                                        
                                        <h4 class="card-text">FROM DATE : `+data.from_date+`</h4>
                                        <h4 class="card-text">TO DATE : `+data.to_date+`</h4>
                                        <h4 class="card-text">FROM PLACE : `+data.from_place+`</h4>
                                        <h4 class="card-text">TO PLACE : `+data.to_place+`</h4>
                                        <h4 class="card-text">AMOUNT : `+data.amount+`</h4>
                                        <a href="#" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`;
            }
            $("#search_passenger").html(html)
        });
    });


    update();
});

function update() {
    view_passengers();
    view_pass();
    view_renewal();
}

function view_passengers() {
    $.ajax({
        type: 'GET',
        url: '/api/passenger_list',
    }).done(function (datas) {
        var html = ``;
        for (var i in datas) {
            var row = datas[i];
            html += ` <tr>
            <td>`+ (++i) + `</td>
            <td>`+ row.name + `</td>
            <td>`+ row.mobile + `</td>
            <td>`+ row.author + `</td>
            <td>`+ row.age + `</td>
            <td>`+ row.gender + `</td>
            <td>`+ row.address + `</td>
            <td>`+ row.pincode + `</td>
        </tr>`;
        }

        $("#passenger_body").html(html)
    });

}

function view_pass() {
    $.ajax({
        type: 'GET',
        url: '/api/pass_list',
    }).done(function (datas) {
        var html = ``;
        for (var i in datas) {
            var row = datas[i];
            html += ` <tr>
            <td>`+ (++i) + `</td>
            <td>`+ row.name + `</td>
            <td>`+ row.mobile + `</td>
            <td>`+ row.author + `</td>
            <td>`+ row.age + `</td>
            <td>`+ row.gender + `</td>
            <td>`+ row.address + `</td>
            <td>`+ row.pincode + `</td>
            <td>`+ row.from_place + `</td>
            <td>`+ row.to_place + `</td>
            <td>`+ row.amount + `</td>
        </tr>`;
        }

        $("#pass_body").html(html)
    });

}

function view_renewal() {
    $.ajax({
        type: 'GET',
        url: '/api/renewal_list',
    }).done(function (datas) {
        var html = ``;
        for (var i in datas) {
            var row = datas[i];
            html += ` <tr>
            <td>`+ (++i) + `</td>
            <td>`+ row.name + `</td>
            <td>`+ row.author + `</td>
            <td>`+ row.from_date + `</td>
            <td>`+ row.to_date + `</td>
            <td>`+ row.from_place + `</td>
            <td>`+ row.to_place + `</td>
            <td>`+ row.amount + `</td>
          
        </tr>`;


        }

        $("#renewal_body").html(html)
    });

}


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