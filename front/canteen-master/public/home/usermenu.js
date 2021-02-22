$(document).ready(function () {

    $("#order").on('click', function () {
        var data_obj = {};
        data_obj["tid"]=1;
        data_obj["data"]=cart_array;
      

        $.ajax({
            type: 'POST',
            url: '/api/addorder',
            data:{datas:JSON.stringify(data_obj)},
        }).done(function (datas) {
                alert(datas);
                $("#cart_body").html('');
                cart_array= [];
                updateCart();
        });
    
    });
    
    updateCart();
    showMenuList();
});

var cart_array = [];

function showMenuList() {
    $.ajax({
        type: 'GET',
        url: '/api/menu_list'
    }).done(function (datas) {

        var html = ``;

        for (var i in datas) {
            var data = datas[i];
            html += `<div class="col-md-4" style="border: groove;border-width: 1px;border-color: gray;margin: 5px">
                        <div class="card">
                            <div class="card-body">
                                <div class="col-md-3">
                                    <img src="/images/food.jpg"
                                         style="background-color:orange;height:50px;width:50px">
                                </div>

                                <div class="col-md-6">
                                    <p class="card-text"
                                       style="font-weight: 700;color:#404041;line-height:10px;font-size: 15px;">
                                        `+ data.name + `
                                    </p>

                                    <p class="card-text"
                                       style="font-weight: 700;color:rgba(0, 48, 221, 0.75);line-height:30px;font-size: 20px;">
                                        $`+ data.amount + `
                                    </p>
                                </div>
                                <div class="col-md-3">
                                    <img onclick="add_cart('`+ data.id + `','` + data.name + `','` + data.amount + `')" src="/images/add.png"
                                         style="float:right;height:30px;width:30px;cursor:pointer">
                                </div>
                            </div>
                        </div>
                    </div>`;
        }
        $("#menu_body").html(html)
    });
}

function add_cart(id, name, amount) {
    var cart_obj = {};
    cart_obj["id"] = id;
    cart_obj["name"] = name;
    cart_obj["amount"] = amount;
    cart_array.push(cart_obj);

    updateCart();
}

function updateCart() {

    var html = ``;
    var total=0;
    for (var i in cart_array) {
        var data = cart_array[i];
        total =Number(total)+Number(data.amount);
        html += ` <tr>
            <th scope="row">`+(++i)+`</th>
            <td>`+data.name+`</td>
            <td>$`+data.amount+`</td>
            </tr>
            `;
    }

    $("#cart_body").html(html+`<tr>
    <td colspan="2" style="color: #0056b3;font-size: 20px">Total</td>
    <td colspan="2" style="color: #0056b3;font-size: 20px">$`+total+`</td>
    </tr>`)

}