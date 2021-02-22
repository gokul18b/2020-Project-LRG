var express = require('express');
var router = express.Router();

var knex = require('knex')({
    client: 'mysql',
    connection: {
        host: '127.0.0.1',
        user: 'root',
        password: '',
        database: 'criminal'
    }
});

router.get('/admin_login/:username/:password', function (req, res, next) {
    var username = req.params.username;
    var password = req.params.password;
    var admin_login = "select * from admin where username='" + username + "' and password='" + password + "'";
    knex.raw(admin_login).then((datas) => {
        res.json(datas[0]);
    });
});



router.post('/add_case', function (req, res, next) {

    var case_no = req.body.case_no;
    var case_name = req.body.case_name;
  

    var insert_customer = "INSERT INTO `criminalcase` (`id`, `caseno`, `name`) VALUES (NULL, '"+case_no+"', '"+case_name+"');";
    
    knex.raw(insert_customer).then((datas) => {
        res.send('Case has been created');
    });
});



router.post('/add_criminal', function (req, res, next) {
    
    var firstname = req.body.firstname;
    var lastname = req.body.lastname;
    var authornumber = req.body.authornumber;
    var mobile = req.body.mobile;
    var age = req.body.age;
    var gender = req.body.gender;
    var address = req.body.address;
    
    var insert_criminal = "INSERT INTO `criminal` (`id`, `firstname`, `lastname`, `authorno`, `mobile`, `age`, `gender`, `address`) VALUES     (NULL, '"+firstname+"', '"+lastname+"', '"+authornumber+"', '"+mobile+"', '"+age+"', '"+gender+"', '"+address+"');";
    
    knex.raw(insert_criminal).then((datas) => {
        res.send('Criminal has been created');
    });
});


router.get('/show_criminals', function (req, res, next) {

    var customer_list = "select * from criminal";
    knex.raw(customer_list).then((datas) => {
        res.json(datas[0]);
    });
});

router.post('/show_crimnal_details', function (req, res, next) {
    var authorno = req.body.authorno;
    var crimal_details = "select * from criminal where authorno='"+authorno+"'";
    knex.raw(crimal_details).then((datas) => {
        res.json(datas[0]);
    });
});

router.post('/show_history_details', function (req, res, next) {
    var authorno = req.body.authorno;
    var crimal_details = "select * from history where authorno='"+authorno+"'";
    knex.raw(crimal_details).then((datas) => {
        res.json(datas[0]);
    });
});

router.get('/set_case', function (req, res, next) {
    var purchase_list = "select * from criminalcase";
    knex.raw(purchase_list).then((datas) => {
        res.json(datas[0]);
    });
});

router.post('/allocate_case', function (req, res, next) {
    
    var authorno = req.body.authorno;
    var firstname = req.body.firstname;
    var lastname = req.body.lastname;
    var mobile = req.body.mobile;
    var age = req.body.age;
    var gender = req.body.gender;
    var address = req.body.address;
    var casename = req.body.case;
    var description = req.body.description;

    var insert_history = "INSERT INTO `history` (`id`, `authorno`, `firstname`, `lastname`, `mobile`, `age`, `gender`, `address`, `casename`, `description`) VALUES (NULL, '"+authorno+"', '"+firstname+"', '"+lastname+"', '"+mobile+"', '"+age+"', '"+gender+"', '"+address+"', '"+casename+"', '"+description+"');";
    knex.raw(insert_history).then((datas) => {
        res.json("Record has been inserted");
    });
});

router.get('/get_purchase/:pid', function (req, res, next) {
    var pid = req.params.pid;
    var purchase_list = "select * from purchase where pid=" + pid;
    knex.raw(purchase_list).then((datas) => {
        res.json(datas[0][0]);
    });
});

router.post('/test', function (req, res, next) {
    res.send('test')
});

router.post('/insertpurchase', function (req, res, next) {
    var pid = req.body.pid;
    var pname = req.body.pname;
    var price = req.body.price;
    var quantity = req.body.quantity;
    var brand = req.body.brand;
    var expiery = req.body.expiery;

    var query = "Select count(*) as count,quantity from `purchase` where pid=" + pid;
    console.log(query);
    knex.raw(query).then((datas) => {
        var count = datas[0][0].count;

        if (count == 0) {
            var insert_purchase = "INSERT INTO `purchase` (`id`, `pid`, `pname`, `price`, `quantity`, `brand`, `expiery`) VALUES (NULL, '" + pid + "', '" + pname + "', '" + price + "', '" + quantity + "', '" + brand + "', '" + expiery + "');";
            var insert_stock = "INSERT INTO `STOCK` (`id`, `pid`, `pname`, `price`, `quantity`, `brand`, `expiery`) VALUES (NULL, '" + pid + "', '" + pname + "', '" + price + "', '" + quantity + "', '" + brand + "', '" + expiery + "');";
            console.log(insert_stock)
            console.log(insert_purchase);
            knex.raw(insert_purchase).then((datas) => {

                knex.raw(insert_stock).then((datas) => {
                    res.send('Purchase Added succesfully completed');
                });
            });
        } else {
            var old_quantity = datas[0][0].quantity;
            console.log(old_quantity);
            var new_quantity = (old_quantity-0)+(quantity-0)
            var update_purchase = "UPDATE `purchase` SET `pname` = '" + pname + "', `price` = '" + price + "', `quantity` = '" + new_quantity + "', `brand` = '" + brand + "', `expiery` = '" + expiery + "' WHERE `purchase`.`pid` = " + pid + ";";
            
            var update_stock = "UPDATE `stock` SET `pname` = '" + pname + "', `price` = '" + price + "', `quantity` = '" + new_quantity + "', `brand` = '" + brand + "', `expiery` = '" + expiery + "' WHERE `stock`.`pid` = " + pid + ";";
           
            knex.raw(update_purchase).then((datas) => {
                
                knex.raw(update_stock).then((datas) => {
                    res.send('Purchase updated succesfully completed');
                });
            });
        }
    })


});

router.post('/insertsales', function (req, res, next) {

    var pid = req.body.pid;
    var name = req.body.name;
    var mobile = req.body.mobile;
    var price = req.body.price;
    var quantity = req.body.quantity;
    var amount = req.body.amount;
    var brand = req.body.brand;
    var expiery = req.body.expiery;


    var insert_sales = "INSERT INTO `sales` (`id`, `pid`, `mobile`, `pname`, `price`, `quantity`, `amount`, `brand`, `expiery`)  VALUES (NULL, '" + pid + "', '" + mobile + "', '" + name + "', '" + price + "', '" + quantity + "', '" + amount + "', '" + brand + "', '" + expiery + "');";
    knex.raw(insert_sales).then((datas) => {
       

    var query = "select quantity from stock where pid=" + pid;
    console.log(query)
    knex.raw(query).then((datas) => {
        var old_quan = datas[0][0].quantity;
        var new_auan = (old_quan-0)-(quantity-0)
        var update = "UPDATE `stock` SET `quantity` = " + new_auan + " WHERE `stock`.`pid` = " + pid + ";";
        console.log(query)
        knex.raw(update).then((datas) => {
            res.send('sales Added succesfully completed');
        })
    })
    
});
    console.log(insert_sales);





});

router.get('/home', function (req, res, next) {
    res.render('index', { title: 'Express' });
});

module.exports = router;
