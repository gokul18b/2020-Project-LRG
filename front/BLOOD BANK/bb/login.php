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

  $username=$_REQUEST['username'];
  $password=$_REQUEST['password'];
 
  
  
  
$sql="SELECT * from user where username='$username' and password='$password'";
$res = $conn->query($sql);

if ($result=mysqli_query($conn,$sql))
  {
  $rowcount=mysqli_num_rows($result);
  mysqli_free_result($result);
	if($rowcount==0){
		echo '{"data":{"status":2,"msg":"Invalid username or password"}}';
	}
	else{
		while($row = $res->fetch_assoc()) {
			echo '{data:{"status":'.$row["usertype"].',"id":'.$row['id'].'}}';
    }
		 
		
		
		
		
	}
 }
$conn->close();
?> 