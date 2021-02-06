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

  $id=$_REQUEST['id'];
  
 
$sql = "select bb.id as id,user.id as userid,user.name ,user.mobile as mobile,user.bloodgroup as bloodgroup, user.address as address from  bloodbank as bb left join user on(user.id=bb.rid) where bb.sstatus='SEND' and  bb.rstatus='ACCEPT' and bb.sid=".$id;
$res = $conn->query($sql);

if ($result=mysqli_query($conn,$sql))
  {
  $rowcount=mysqli_num_rows($result);
  mysqli_free_result($result);
	if($rowcount==0){
		echo '{"status":2,"msg":"Invalid id"}';
	}
	else{
		
		while($row = $res->fetch_assoc()) {
		$data['id']=$row['id'];	
		$data['userid']=$row['userid'];	
		$data['img']='';
		$data['name'] =$row['name'];
		$data['mobile'] =$row['mobile'];
		$data['Blood'] =$row['bloodgroup'];
		$data['address'] =$row['address'];
		$r['data'][]=$data;
			
    }
	$r['status']=1;
	echo json_encode($r);

}
 }
$conn->close();
?> 