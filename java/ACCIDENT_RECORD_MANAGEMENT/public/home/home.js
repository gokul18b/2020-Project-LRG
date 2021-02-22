$(document).ready(function () {
    $("#mytable #checkall").click(function () {
        if ($("#mytable #checkall").is(':checked')) {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", true);
            });

        } else {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", false);
            });
        }
    });

    $("[data-toggle=tooltip]").tooltip();

$("#search_criminal").on('click',function(){
var quthor_no = $("#search_author").val();

    $.ajax({
        type: 'POST',
        url: '/api/show_history_details',
        data: { authorno: quthor_no },

    }).done(function (datas) {

        var html = ``;
        for (var i in datas) {
            var data = datas[i];
            html += `<tr>
            
            <td>`+ data.firstname + `</td>
            <td>`+ data.lastname + `</td>
            <td>`+ data.casename + `</td>
            <td>`+ data.mobile + `</td>
            <td>`+ data.age + `</td>
            <td>`+ data.gender + `</td>
            <td>`+ data.address + `</td>
            
        </tr>`;
        }

        $("#criminal_search_body").html(html)
    });
})
    $("#add_criminal").on('click', function () {
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var authornumber = $("#authornumber").val();
        var mobile = $("#mobile").val();
        var age = $("#age").val();
        var gender = $("#gender option:selected").html();;
        var address = $("#address").val();

        $.ajax({
            type: 'POST',
            url: '/api/add_criminal',
            data: { firstname: firstname, lastname: lastname, authornumber: authornumber, mobile: mobile, age: age, gender: gender, address: address }
        }).done(function (datas) {
            alert(datas);
            $("#firstname").val("");
            $("#lastname").val("");
            $("#authornumber").val("");
            $("#mobile").val("");
            $("#age").val("");
            $("#gender option:selected").html();
            $("#address").val("");
            showCriminals();
        });


    });

    $("#add_case").on('click', function () {
        var case_no = $("#case_no").val();
        var case_name = $("#case_name").val();

        $.ajax({
            type: 'POST',
            url: '/api/add_case',
            data: { case_no: case_no, case_name: case_name }
        }).done(function (datas) {
            alert(datas);
            $("#case_no").val("")
            $("#case_name").val("")

            //showCaseList();
            setCase();

        });

    });
    $("#al_seacrh").on('click', function () {
        var quthor_no = $("#al_authorno").val();
        $.ajax({
            type: 'POST',
            url: '/api/show_crimnal_details',
            data: { authorno: quthor_no },

        }).done(function (datas) {
            var data = datas[0];

            $("#al_firstname").val(data.firstname)
            $("#al_lastname").val(data.lastname)
            $("#al_mobile").val(data.mobile)
            $("#al_age").val(data.age)
            $("#al_gender").val(data.gender)
            $("#al_address").val(data.address)


        });
    })

    $("#allocate_case").on('click', function () {
        var al_authorno = $("#al_authorno").val();
        var al_firstname = $("#al_firstname").val();
        var al_lastname = $("#al_lastname").val();
        var al_mobile = $("#al_mobile").val();
        var al_age = $("#al_age").val();
        var al_gender = $("#al_gender").val();
        var al_address = $("#al_address").val();
        var al_case = $("#al_case option:selected").html();
        var al_description = $("#al_description").val()

        $.ajax({
            type: 'POST',
            url: '/api/allocate_case',
            data: { authorno: al_authorno, firstname: al_firstname, lastname: al_lastname, mobile: al_mobile, age: al_age, gender: al_gender, address: al_address, case: al_case, description: al_description },
        }).done(function (datas) {
            alert(datas)
            $("#al_authorno").val("");
            $("#al_firstname").val("");
            $("#al_lastname").val("");
            $("#al_mobile").val("");
            $("#al_age").val("");
            $("#al_gender").val("");
            $("#al_address").val("");
            $("#al_description").val("")
        });

    });
    showCriminals();
    setCase();
});

function setCase() {

    $.ajax({
        type: 'GET',
        url: '/api/set_case',

    }).done(function (datas) {
        
        var html = ``;
        for (var i in datas) {
            var data = datas[i];
            html += `<option>` + data.name + `</option>`;
        }
        $('#al_case').html(html)
    });
}

function showCriminals() {
    $.ajax({
        type: 'GET',
        url: '/api/show_criminals',

    }).done(function (datas) {

        var html = ``;
        for (var i in datas) {
            var data = datas[i];
            html += `<tr>
            
            <td>`+ data.firstname + `</td>
            <td>`+ data.lastname + `</td>
            <td>`+ data.authorno + `</td>
            <td>`+ data.mobile + `</td>
            <td>`+ data.age + `</td>
            <td>`+ data.gender + `</td>
            <td>`+ data.address + `</td>
            
        </tr>`;
        }

        $("#criminal_body").html(html)
    });

}

function showCaseList() {
    $.ajax({
        type: 'GET',
        url: '/api/show_case',

    }).done(function (datas) {
        alert(datas);
    });
}
