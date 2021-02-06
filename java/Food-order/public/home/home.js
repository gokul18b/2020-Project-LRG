// Material Design example
$(document).ready(function () {

    $('#addcustomer_table').DataTable();
    $('#checkin_table').DataTable();
    $('#checkout_table').DataTable();
    $('#billing_table').DataTable();
    $('.dataTables_length').addClass('bs-select');


    $("#addmenu").on('click', function () {
        var name = $("#menuname").val();
        var amount = $("#amount").val();
        var path = '/path';

        $.ajax({
            type: 'POST',
            url: '/api/create_menu',
            data: { name: name, amount: amount, path: path },
        }).done(function (datas) {
            alert(datas);
            $("#menuname").val("");
            $("#amount").val("");
            showMenus();
        });
    })

    showMenus();
    showPending();
    showCompleted();
});

function showCompleted(){
    
    $.ajax({
        type: 'GET',
        url: '/api/completed_orders',
    }).done(function (datas) {
        var html = ``;
        for (var i in datas) {
            var data = datas[i];
            html += `<tr>
            <th scope="row">`+ (++i) + `</th>
            <td>`+data.tid+`</td>
            <td>`+data.name+`</td>
            <td>$`+data.amount+`</td>
            <td><button onclick="completeOrder('`+data.id+`')" type="button" class="btn btn-warning">Complete</button></td>
        </tr>`;

        }
        
        $("#completed_body").html(html);
    });
}
function showPending() {

    $.ajax({
        type: 'GET',
        url: '/api/pending_orders',
    }).done(function (datas) {
        var html = ``;
        for (var i in datas) {
            var data = datas[i];
            html += `<tr>
            <th scope="row">`+ (++i) + `</th>
            <td>`+data.tid+`</td>
            <td>`+data.name+`</td>
            <td>$`+data.amount+`</td>
            <td><button onclick="completeOrder('`+data.id+`')" type="button" class="btn btn-warning">Complete</button></td>
        </tr>`;

        }
        
        $("#pending_body").html(html);
    });

}

function completeOrder(id){
    $.ajax({
        type: 'POST',
        url: '/api/update_order',
        data: { id: id},
    }).done(function (datas) {
        alert(datas);
       
         showPending();
         showCompleted();
    });
}

function showMenus() {
    var html = ``;

    $.ajax({
        type: 'GET',
        url: '/api/menu_list',
    }).done(function (datas) {

        for (var i in datas) {
            var data = datas[i];
            html += `<tr>
                <th scope="row">`+ (++i) + `</th>
                <td>`+ data.name + `</td>
                <td>$ `+ data.amount + `</td>
                
            </tr>`;
            //<td><img src="/images/delete.png" style="height: 20px;width: 20px"></td>
        }
        $("#menu_body").html(html);
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