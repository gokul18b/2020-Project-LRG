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

  $name=$_POST['name'];
  $age=$_POST['age'];
  $mobile=$_POST['mobile'];
  $username=$_POST['username'];
  $password=$_POST['password'];
  $bloodgroup=$_POST['bloodgroup'];
  $city=$_POST['city'];
  $district=$_POST['district'];
  $pincode=$_POST['pincode'];
  $fulladdress=$_POST['fulladdress'];
  
  
  
  $sql="SELECT * from user where username='$username'";

if ($result=mysqli_query($conn,$sql))
  {
 
 $rowcount=mysqli_num_rows($result);
  
  mysqli_free_result($result);
 if($rowcount==0){
$sql = "INSERT INTO user (usertype,name, age, bloodgroup,mobile,username,password,city,district,pincode,address)
VALUES (0,'$name', '$age','$bloodgroup', '$mobile','$username', '$password','$city', '$district', '$pincode','$fulladdress')";

if ($conn->query($sql) === TRUE) {
    echo '{"data":{"status":1,"msg":"Successfully Registered"}}';
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}
else{
	 echo '{"data":{"status":0,"msg":"Username Already Registered"}}';
}
  }
$conn->close();
?> 