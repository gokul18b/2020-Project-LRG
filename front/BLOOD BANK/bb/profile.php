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

  $id=$_GET['id'];
  
 
  
  
  
$sql="SELECT * from user where id=".$id;
$res = $conn->query($sql);

if ($result=mysqli_query($conn,$sql))
  {
  $rowcount=mysqli_num_rows($result);
  mysqli_free_result($result);
	if($rowcount==0){
		echo '{"data":{"status":2,"msg":"Invalid id"}}';
	}
	else{
		while($row = $res->fetch_assoc()) {
			echo '{"data":{"status":'.$row["usertype"].',"id":'.$row['id'].',"name":"'.$row['name'].'","age":"'.$row['age'].'","bloodgroup":"'.$row['bloodgroup'].'","mobile":"'.$row['mobile'].'","username":"'.$row['username'].'","city":"'.$row['city'].'","district":"'.$row['district'].'","pincode":"'.$row['pincode'].'","address":"'.$row['address'].'"}}';
    }
		 
		
		
		
		
	}
 }
$conn->close();
?> 