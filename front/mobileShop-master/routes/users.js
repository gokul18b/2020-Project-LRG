var express = require('express');
var router = express.Router();

var fs= require('fs');

/* GET users listing. */
router.get('/:filename', function(req, res, next) {
    // var filePath = "./routes/uploads/test.pdf";
    // fs.readFile( filePath , function (err,data){
    //     res.contentType("pdf");
    //     res.send(data);
    // });


    var filename = req.params.filename;
    var filePath = "./routes/uploads/"+filename;
    console.log(filePath);
    fs.readFile( filePath , function (err,data){
        res.contentType("pdf");
        res.send(data);
    });




});
// function setResponseHeaders(res, filename) {
//   res.header('Content-disposition', 'inline; filename=' + filename);
//   res.header('Content-type', 'application/pdf');
// }

module.exports = router;
