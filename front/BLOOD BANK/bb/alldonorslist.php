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
  
 $sql1="Select distinct user.id as id,user.name as name,user.mobile as mobile,user.bloodgroup as bloodgroup,user.address as address from user 
LEFT JOIN bloodbank AS bb ON (bb.rid = user.id) 
WHERE user.id NOT IN(SELECT usr.id FROM user AS usr
JOIN bloodbank AS bl ON (bl.rid=usr.id AND bl.sstatus='SEND' AND bl.rstatus='RECEIVE') 
WHERE usr.id NOT IN(".$id.")) AND user.id!=".$id;

$sql2="Select distinct user.id as id,user.name as name,user.mobile as mobile,user.bloodgroup as bloodgroup,user.address as address from user 
LEFT JOIN bloodbank AS bb ON (bb.rid = user.id) 
WHERE user.id NOT IN(SELECT usr.id FROM user AS usr
LEFT JOIN bloodbank AS bl ON (bl.rid=usr.id AND bl.sstatus='SEND' AND bl.rstatus='RECEIVE') 
WHERE usr.id NOT IN(SELECT usr.id FROM user AS usr
LEFT JOIN bloodbank AS bl ON (bl.rid=usr.id AND bl.sstatus='SEND' AND bl.rstatus='RECEIVE') 
WHERE usr.id NOT IN(".$id.")))";

$sql = "select * from user 
WHERE id NOT IN(select rid FROM bloodbank WHERE sid = '".$id."' and sstatus='SEND' and rstatus='RECEIVE') AND id!='".$id."';";
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