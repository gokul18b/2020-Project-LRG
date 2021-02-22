/**
 * Created by gokul.b on 20-Feb-19.
 */

var slot_id = '';
$(document).ready(function () {
    update();
    getTokens();
});

function update() {
    getMall();
    //loadSlot();
}

function getMall() {


    $.ajax({
        type: "GET",
        url: "/api/get_mall", success: function (result) {
            var html = ``;
            for (var i in result) {
                var data = result[i];
                html += `
                 <li role="presentation" onclick="loadSlot('` + data.id + `')" class="brand-nav active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">` + data.name + `</a></li>`;
            }
            $("#menulist").html(html)
        }
    });
}


function loadSlot(mid) {
    $.ajax({
        type: "GET",
        url: "/api/get_slot/" + mid,
        success: function (result) {
            var html = ``;

            var tokens = [];
            for (var i in result) {
                var data = result[i];
                tokens.push(data.token)
            }


            for (var i = 1; i <= 100; i++) {
                if (tokens.indexOf(i.toString()) == -1) {

                    html += `<div onclick="updatecolor(` + i + `,[` + tokens + `],` + mid + `)" id="slot` + i + `" style="border: double;margin: 5px;cursor:pointer" class="col-sm-1">
                            <img style="height: 30px;width: 50px" src="/images/car.png">
                            <h4>T` + i + `</h4>
                            </div>`;
                }
                else {
                    html += `<div  id="slot` + i + `" style="border: double;margin: 5px;background-color:#FF5964" class="col-sm-1">
                            <img style="height: 30px;width: 50px" src="/images/car.png">
                            <h4>T` + i + `</h4>
                            </div>`;

                }
            }

            $("#slot").html(html)

        }
    })
    ;


}

function updatecolor(tid, tokens, mid) {
    slot_id = tid;
    for (var i = 1; i <= 100; i++) {
        if (tokens.indexOf(i) == -1) {
            if (tid == i) {
                $("#slot" + i).css("background-color", "#FF5964");
            } else {
                $("#slot" + i).css("background-color", "#FFFFFF");
            }
        } else {
            $("#slot" + i).css("background-color", "#FF5964");
        }
    }


    $.ajax({
        type: "POST",
        url: "/api/book_slot",
        data: {
            mid: mid,
            cid: getUrlParameter('id'),
            tid: tid,
        },
        success: function (result) {
            alert(result)
            getTokens()
        }
    });


}

function getTokens() {
    var cid = getUrlParameter('id');
    $.ajax({
        type: "GET",
        url: "/api/get_tokens/" + cid, success: function (result) {
            var html = ``;
            for (var i in result) {
                var data = result[i];
                var date = new Date(data.intime);

                var mydate = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();

                html += `<div style="border: double;margin: 10px" class="col-sm-3">
                        <center><h1>T` + data.token + `</h1></center>
                        <center><h4>` + mydate + `</h3></center>
                    </div>`;
            }
            $("#token_list").html(html)
        }
    });

    html = ``;

}


var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};