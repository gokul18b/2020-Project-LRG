 <?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "bb";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$sid=$_REQUEST['sid'];
$rid = $_REQUEST['rid'];
$sstatus = "SEND";
$rstatus = "RECEIVE";


$sql = "INSERT INTO `bb`.`bloodbank` (`id`,`sid`, `rid`, `sstatus`, `rstatus`) VALUES (NULL,".$sid." ,'".$rid."', '".$sstatus."', '".$rstatus."');";
echo $sql;

if ($conn->query($sql) === TRUE) {
	echo 'sucess';
}
							


								
  $conn->close();
?> 