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
$bbid = $_REQUEST['bbid'];

		
		$sql = "UPDATE `bb`.`bloodbank` SET `rstatus` = 'ACCEPT' WHERE `bloodbank`.`id` =".$bbid;
		
		if ($conn->query($sql) === TRUE) {
			echo 'sucess';
}
		




							


								
  $conn->close();
?> 