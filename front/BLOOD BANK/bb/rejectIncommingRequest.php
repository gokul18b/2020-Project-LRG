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



$bbid = $_REQUEST['bbid'];

		
		$sql = "UPDATE `bb`.`bloodbank` SET `rstatus` = 'REJECT' WHERE `bloodbank`.`id` =".$bbid;
		
		if ($conn->query($sql) === TRUE) {
			echo 'sucess';
}
		




							


								
  $conn->close();
?> 