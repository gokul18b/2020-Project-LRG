var express = require('express');
var router = express.Router();

var knex = require('knex')({
    client: 'mysql',
    connection: {
        host: '127.0.0.1',
        user: 'root',
        password: '',
        database: 'buspass'
    }
});


/* GET home page. */
router.post('/add_passenger', function (req, res, next) {
    console.log(req.body)
    console.log(req.body.author)
    var a = req.body;
    console.log(a.name + "-" + a.mobile + "-" + a.author + "-" + a.age + "-" + a.gender + "-" + a.address + "-" + a.pincode);


    var insert_passenger = "INSERT INTO `passenger` (`id`, `name`, `mobile`, `author`, `age`, `gender`, `address`,`pincode`, `image`) VALUES (NULL, '" + a.name + "', '" + a.mobile + "', '" + a.author + "', '" + a.age + "', '" + a.gender + "', '" + a.address + "', '" + a.pincode + "', 'img');";
    // res.send('Passenger has been inserted');
    console.log(insert_passenger);
    knex.raw(insert_passenger).then((datas) => {
        res.send('Passenger has been inserted');
    });
});



router.get('/passenger_list', function (req, res, next) {
    var query = `select * from passenger`
    knex.raw(query).then((datas) => {
        res.send(datas[0]);
    });
});

router.get('/pass_list', function (req, res, next) {
    var query = "SELECT * FROM `pass` left join passenger on(passenger.author=pass.author)";
    knex.raw(query).then((datas) => {
        res.send(datas[0]);
    });
});

router.get('/renewal_list', function (req, res, next) {
    var query = "SELECT * FROM `renewal` ";
    knex.raw(query).then((datas) => {
        res.send(datas[0]);
    });
});


router.post('/search_passenger', function (req, res, next) {
    var author = req.body.author;
    var query = "SELECT * FROM `renewal`  where author='"+author+"'";
    
    knex.raw(query).then((datas) => {
        res.send(datas[0]);
    });
});


router.post('/update_passenger', function (req, res, next) {
    console.log(req.body)
    var author = req.body.author;
    var query = `select * from passenger where author='` + author + "'";
    console.log(query)
    knex.raw(query).then((datas) => {
        res.send(datas[0]);
    });
});




router.post('/update_passenger_renewal', function (req, res, next) {
    console.log(req.body)
    var author = req.body.author;
    var query = "select pass.from_place,pass.to_place,pass.amount,passenger.name,passenger.mobile,passenger.age,passenger.gender,passenger.address,passenger.pincode  from passenger left join pass on (pass.author=passenger.author) where passenger.author='" + author + "'";
    console.log(query)
    knex.raw(query).then((datas) => {
        res.send(datas[0]);
    });
});


router.post('/add_pass', function (req, res, next) {

    var author = req.body.author;
    var name = req.body.name;
    var mobile = req.body.mobile;
    var address = req.body.address;
    var age = req.body.age;
    var pincode = req.body.pincode;
    var gender = req.body.gender;
    var from = req.body.from;
    var to = req.body.to;
    var amount = req.body.amount;

    var query = "INSERT INTO `pass` (`id`, `author`, `from_place`, `to_place`, `amount`) VALUES (NULL, '" + author + "', '" + from + "', '" + to + "', '" + amount + "');"
    knex.raw(query).then((datas) => {
        res.send("Pass succesfully created");
    });
    console.log(req.body)
});


router.post('/add_renewal', function (req, res, next) {

    var author = req.body.author;
    
    var from = req.body.from;
    var to = req.body.to;
    var from_place = req.body.from_place;
    var to_place = req.body.to_place;
    var amount = req.body.amount;
    var name = req.body.name;
    var mobile = req.body.mobile;
    var gender = req.body.gender;
    var address = req.body.address;
    var age = req.body.age;
    var pincode = req.body.pincode;

    var query = "INSERT INTO `renewal` (`id`, `author`, `from_date`, `to_date`, `from_place`, `to_place`, `amount`, `name`, `mobile`, `gender`, `address`, `age`, `pincode`) VALUES (NULL, '"+author+"', '"+from+"', '"+to+"', '"+from_place+"', '"+to_place+"', '"+amount+"', '"+name+"', '"+mobile+"', '"+gender+"', '"+address+"', '"+age+"', '"+pincode+"');";
    knex.raw(query).then((datas) => {
        res.send("Pass succesfully Renewal");
    });
    console.log(req.body)
});


module.exports = router;
