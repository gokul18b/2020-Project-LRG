var express = require('express');
var router = express.Router();

var knex = require('knex')({
    client: 'mysql',
    connection: {
        host: '127.0.0.1',
        user: 'root',
        password: '',
        database: 'mobileshop'
    }
});



router.post('/signin', function (req, res, next) {

    var mobile = req.body.mobile;
    var password = req.body.password;

    var query = "select * from user where mobile='" + mobile + "' and password ='" + password + "'"
    knex.raw(query).then(function (result) {
        //console.log(result[0][0].id)
        console.log(result[0])
        res.send(result[0]);
    });

});

//INSERT INTO `feedback` (`id`, `uid`, `description`, `datetime`) VALUES (NULL, '12', 'sdf', CURRENT_TIMESTAMP);

router.post('/mobilelist', function (req, res) {
    
    var query = "Select * from mobiles";
    knex.raw(query).then(function (result) {
       res.send(result[0])
    });


});
router.post('/feedbackentry', function (req, res) {
    var uid= req.body.uid;
    var description = req.body.description;
    var query = "INSERT INTO `feedback` (`id`, `uid`, `description`, `datetime`) VALUES (NULL, '"+uid+"', '"+description+"', CURRENT_TIMESTAMP);";
    knex.raw(query).then(function (result) {
       res.send("Feedback updated...")
    });
});


router.post('/searchMobile', function (req, res) {
    var mobile= req.body.mobile;
    
    var query = "select * from bill where mobile ="+mobile;
    knex.raw(query).then(function (result) {
        res.send(result[0])
    });
});

router.post('/feedbacklist', function (req, res) {
    
    var query = "Select fb.datetime as datetime, fb.description as description,user.name as name from feedback as fb left join user on(user.id=fb.uid)";
    knex.raw(query).then(function (result) {
       res.send(result[0])
    });


});

router.post('/uploadDatas', function (req, res) {
    var mobileid = req.body.mobileid;
    var cname = req.body.cname;
    var modelno = req.body.modelno;
    var img = req.body.img;
    var price = req.body.price;

    var findBookQuery = "Select * from mobiles where mobileid="+mobileid;
    knex.raw(findBookQuery).then(function (result) {
        if(result[0].length==0) {
            var query = "INSERT INTO `mobiles` (`id`, `mobileid`, `cname`, `modelno`, `img`, `price`) VALUES (NULL, '"+mobileid+"', '"+cname+"', '"+modelno+"', '"+img+"', '"+price+"');";
            knex.raw(query).then(function (result) {
                res.send("true");
            });
        }else{
            res.send("false")
        }
    });


});

router.post('/upload', function (req, res) {
    var path = require('path');
    var formidable = require('formidable');
    var fs = require('fs');

    // create an incoming form object
    var form = new formidable.IncomingForm();

    // specify that we want to allow the user to upload multiple files in a single request
    form.multiples = true;

    // store all uploads in the /uploads directory
    form.uploadDir = path.join(__dirname, '/uploads');

    // every time a file has been uploaded successfully,
    // rename it to it's orignal name
    form.on('file', function(field, file) {
        fs.rename(file.path, path.join(form.uploadDir, file.name));



    });

    // log any errors that occur
    form.on('error', function(err) {
        console.log('An error has occured: \n' + err);
    });

    // once all the files have been uploaded, send a response to the client
    form.on('end', function() {
        res.end('success');
    });

    // parse the incoming request containing the form data
    form.parse(req);
});


router.get('/downl', function (req, res) {
    var fs = require('fs');
   var filePath = "/uploads/test.pdf";
   fs.readFile(filePath,function (err,data) {
       res.contentType("application/pdf");
       res.send(data);
   });

});

router.post('/addbill', function (req, res) {
    var model = req.body.model;
    var company = req.body.company;
    var price = req.body.price;
    var name = req.body.name;
    var mobile = req.body.mobile;

    var query = "INSERT INTO `bill` (`id`, `model`, `company`, `price`, `name`, `mobile`) VALUES (NULL, '"+model+"', '"+company+"', '"+price+"', '"+name+"', '"+mobile+"');";
    knex.raw(query).then(function (result) {
        res.send("true");
    });

});


router.post('/recharge', function (req, res) {
    var amount = req.body.amount;
    var mobile = req.body.mobile;

    var query = "INSERT INTO `recharge` (`id`, `mobile`, `amount`) VALUES (NULL, '"+mobile+"', '"+amount+"');";
    knex.raw(query).then(function (result) {
        res.send("true");
    });

});

router.post('/addservice', function (req, res) {
    var model = req.body.model;
    var company = req.body.company;
    var price = req.body.price;
    var name = req.body.name;
    var mobile = req.body.mobile;

    var query = "INSERT INTO `service` (`id`, `model`, `company`, `name`, `mobile`) VALUES (NULL, '"+model+"', '"+company+"', '"+name+"', '"+mobile+"');";
    knex.raw(query).then(function (result) {
        res.send("true");
    });

});



module.exports = router;
