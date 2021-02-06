/**
 * Created by gokul.b on 31-Jan-19.
 */
var express = require('express');
var router = express.Router();
var knex = require('knex');

var knex = require('knex')({
    client: 'mysql',
    connection: {
        host: 'localhost',
        user: 'root',
        password: '',
        database: 'canteen'
    }
});


/* GET home page. */
router.post('/login', function (req, res, next) {
    var username = req.body.username;
    var password = req.body.password;
    
    var login = "select count(*) as count from admin where username='"+username+"' and password='"+password+"'";
console.log(login)
    knex.raw(login).then((datas) => {
        res.send(datas[0]);
    })

});


router.post('/create_menu', function (req, res, next) {
    var name = req.body.name;
    var amount = req.body.amount;
    var path = req.body.path;
    var insert_menu = "INSERT INTO `menu` (`id`, `name`, `amount`, `path`) VALUES (NULL, '" + name + "', '" + amount + "', '" + path + "');";
    knex.raw(insert_menu).then((datas) => {
        res.send("Menu Created");
    })

});

router.post('/update_order', function (req, res, next) {
    var id = req.body.id;
    
    var update_menu = "UPDATE `orders` SET `status` = '1' WHERE `orders`.`id` = "+id+";";

    console.log(update_menu)
    knex.raw(update_menu).then((datas) => {
        res.send("Your order was updated");
    })

});

router.get('/menu_list', function (req, res, next) {
    var insert_menu = "Select * from menu";
    knex.raw(insert_menu).then((datas) => {
        res.send(datas[0]);
    })
});


router.post('/addorder', function (req, res, next) {
    //{"tid":"2","data":[{
    //
    //    "menu_id":"1"
    //},
    //{
    //
    //    "menu_id":"2"
    //}]}

    console.log(JSON.parse(req.body.datas).tid)
    var tid = JSON.parse(req.body.datas).tid;
    var data = JSON.parse(req.body.datas).data;

    // var find_previous_order = "Select * from orders where tid=" + tid + " and status='0'";

    // knex.raw(find_previous_order).then((datas)=> {
    //     var order_count = datas[0].length;
    //     if (order_count == 0) {
    //         //new order

    //     } else {
    //         //old order
    //         res.send("You have already one order");
    //     }

    // });


    var add_order = '';
    for (var i = 0; i < data.length; i++) {
        var status = 0;
        var menu_id = data[i].id;
        var quantity = data[i].quantity;
        add_order = ` INSERT INTO orders (id, tid, status, menu_id,quantity) VALUES (NULL, '` + tid + `', '` + status + `', '` + menu_id + `', '` + quantity + `');`;
        knex.raw(add_order).then((datas, err) => {

        });

    }
    res.send("Your request successfully send")


});

router.get('/pending_orders', function (req, res, next) {
    var pending_orders = "select orders.id as id,orders.tid as tid,menu.name as name,menu.amount as amount from orders left join menu on (menu.id=orders.menu_id) where orders.status=0 order by orders.tid";
    knex.raw(pending_orders).then((datas) => {
        res.send(datas[0]);
    });

})


router.get('/completed_orders', function (req, res, next) {
    var completed_orders = "select orders.id as id,orders.tid as tid,menu.name as name,menu.amount as amount from orders left join menu on (menu.id=orders.menu_id) where orders.status=1 order by orders.tid";
    knex.raw(completed_orders).then((datas) => {
        res.send(datas[0]);
    });

})


module.exports = router;
