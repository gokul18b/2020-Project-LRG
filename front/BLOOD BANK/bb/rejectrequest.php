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

$selectsql = "SELECT id FROM bloodbank where sid=".$sid." and rid=".$rid."  and sstatus='SEND' and rstatus='RECEIVE'";


$res = $conn->query($selectsql);

if ($result=mysqli_query($conn,$selectsql))
  {
  $rowcount=mysqli_num_rows($result);
  mysqli_free_result($result);
	
		
		while($row = $res->fetch_assoc()) {
		$data['id']=$row['id'];	
		
		$sql = "UPDATE `bb`.`bloodbank` SET `sstatus` = 'REJECT' WHERE `bloodbank`.`id` =".$row['id'];
		
		if ($conn->query($sql) === TRUE) {
			echo 'sucess';
}
		}
		
		



  }
		




							


								
  $conn->close();
?> 