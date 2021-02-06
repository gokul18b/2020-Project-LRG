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

  $uid=$_REQUEST['uid'];
  $lattitude=$_REQUEST['lattitude'];
  $longtitude=$_REQUEST['longtitude'];
  $address=$_REQUEST['address'];
  
  $sql="SELECT * from location where uid='$uid'";

if ($result=mysqli_query($conn,$sql))
  {
 
 $rowcount=mysqli_num_rows($result);
  
  mysqli_free_result($result);
 if($rowcount==0){
$sql = "INSERT INTO `bb`.`location` (`id`, `uid`, `lattitude`, `longtitude`, `lastupdate`, `address`) VALUES (NULL, '".$uid."', '".$lattitude."', '".$longtitude."', CURRENT_TIMESTAMP, '".$address."');";

echo $sql;
if ($conn->query($sql) === TRUE) {
    echo '{"data":{"status":'.$lattitude.',"msg":"Successfully Registered"}}';
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}
else{
	$sql_update="UPDATE `bb`.`location` SET `longtitude` = $lattitude,`longtitude`=$longtitude,`lastupdate`=CURRENT_TIMESTAMP, `address` = '$address' WHERE `location`.`uid` = $uid;";
	 if ($conn->query($sql_update) === TRUE) {
    echo '{"data":{"status":1,"msg":"Successfully Updated"}}';
} else {
    echo "Error: " . $sql_update . "<br>" . $conn->error;
}
}
  }
$conn->close();
?> 