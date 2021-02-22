var express = require('express');
var router = express.Router();
var knex = require('knex');

var knex = require('knex')({
    client: 'mysql',
    connection: {
        host: 'localhost',
        user: 'root',
        password: '',
        database: 'carparking'
    }
});


router.post('/login', function (req, res, next) {
    var query = "select count(*) as count from admin where username = '" + req.body.username + "' and password='" + req.body.password + "'";
    knex.raw(query).then((datas)=> {
        var count = datas[0][0].count;
        if (count == 0) {
            var query = "select count(*) as count , id as id from customer where mobile = '" + req.body.username + "' and password='" + req.body.password + "'";
            console.log(query)
            knex.raw(query).then((datas)=> {
                var cou = datas[0][0].count;
                if (cou != 0) {
                    var id = datas[0][0].id;
                    res.send(id + "")
                } else {
                    res.send('Invalid username and password')
                }

            });
        } else {
            res.send('admin')
        }

    })
});

/* GET home page. */
router.post('/add_customer', function (req, res, next) {

    var name = req.body.name;
    var mobile = req.body.mobile
    var password = req.body.password
    var vehicle = req.body.vehicle
    var idproof = req.body.idproof
    var rcno = req.body.rcno
    var address = req.body.address

    var query = "INSERT INTO `customer` (`id`, `name`, `mobile`,`password`, `vehicle`, `proof`, `rcno`, `address`) VALUES (NULL, '" + name + "', '" + mobile + "', '" + password + "', '" + vehicle + "', '" + idproof + "', '" + rcno + "', '" + address + "');";
    knex.raw(query).then((datas)=> {
        res.send('customer has been created');
    })
});

router.get('/get_mall', function (req, res, next) {
    var query = "Select * from mall";
    knex.raw(query).then((datas)=> {
        res.send(datas[0]);
    })
});

router.get('/get_slot/:mid', function (req, res, next) {
    var query = "Select * from booking where status=1 and mid=" + req.params.mid;
    knex.raw(query).then((datas)=> {
        res.send(datas[0]);
    })
});

router.get('/get_tokens/:cid', function (req, res, next) {
    var query = "Select * from booking as b left join mall as m on(m.id=b.mid) where b.status=1 and b.cid=" + req.params.cid;
    console.log(query)
    knex.raw(query).then((datas)=> {
        res.send(datas[0]);
    })
});

router.post('/book_slot', function (req, res, next) {
    var mid = req.body.mid;
    var cid = req.body.cid;
    ;
    var tid = req.body.tid;
    var query = "INSERT INTO `booking` (`id`, `mid`, `cid`, `token`, `intime`, `outtime`,`status`) VALUES (NULL, '" + mid + "', '" + cid + "', '" + tid + "', CURRENT_TIMESTAMP, NULL,1);";
    knex.raw(query).then((datas)=> {
        res.send("Sucessfully Booked Your Token No is T" + tid);
    })
});

router.get('/get_customers', function (req, res, next) {
    var query = "Select * from customer";
    console.log(query)
    knex.raw(query).then((datas)=> {
        res.send(datas[0]);
    })
});


router.get('/get_checkout_list/:mid', function (req, res, next) {
    var query = "select b.token,b.intime,c.name,c.mobile,c.vehicle,c.proof,c.rcno,c.address from booking as b left join customer as c on(c.id=b.cid) where b.mid=" + req.params.mid + " and status=1";
    knex.raw(query).then((datas)=> {
        res.send(datas[0]);
    })
});

router.get('/get_checkout_details_by_token/:token/:mid', function (req, res, next) {

    var query = "select b.id as bookid,b.token,b.intime,c.id,c.name,c.mobile,c.vehicle,c.proof,c.rcno,c.address from booking as b left join customer as c on(c.id=b.cid) where b.mid=" + req.params.mid + " and status=1 and b.token=" + req.params.token.substr(1);
    knex.raw(query).then((datas)=> {
        res.send(datas[0]);
    })
});

router.get('/get_billing/:mid', function (req, res, next) {

    var query = "select b.id as bookid,b.token,b.intime,b.outtime,c.id,c.name,c.mobile,c.vehicle,c.proof,c.rcno,c.address,b.days,b.amount from booking as b left join customer as c on(c.id=b.cid) where b.mid="+req.params.mid+" and status=0";
    knex.raw(query).then((datas)=> {
        res.send(datas[0]);
    })
});

router.post('/add_checkout', function (req, res, next) {

    var mid = req.body.mid;
    var no_of_days = req.body.no_of_days;
    var amount = req.body.amount;
    var checkout_time = req.body.checkout_time;
    var cid = req.body.cid;
    var bookid = req.body.bookid;
console.log(checkout_time)
    var query = "UPDATE `booking` SET `outtime` = CURRENT_TIMESTAMP,`days` = '"+no_of_days+"',`amount` = '"+amount+"', `status` = '0' WHERE `booking`.`id` = "+bookid;
    console.log(query)
    knex.raw(query).then((datas)=> {
        res.send("Checkout sucessfully completed");
    })
});


module.exports = router;

