var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/signin', function(req, res, next) {
    res.render('signIn', { title: 'Express' });
});


router.get('/adminhome', function(req, res, next) {
    res.render('adminhome', { title: 'Express' });
});

router.get('/userhome', function(req, res, next) {
    res.render('userhome', { title: 'Express' });
});
module.exports = router;
