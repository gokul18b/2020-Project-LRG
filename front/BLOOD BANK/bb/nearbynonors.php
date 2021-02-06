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
  


$sql = "select user.id as uid,user.name as uname,user.mobile as mobile,user.bloodgroup as bloodgroup,loc.address as address, loc.lastupdate as lastupdate from location as loc left join user as user on(loc.uid=user.id) where uid!=".$id." and loc.uid not in()";

$sql = "select user.id as uid,user.name as uname,user.mobile as mobile,user.bloodgroup as bloodgroup,location.lattitude as lattitude,
location.longtitude as longtitude, location.address as address,
location.lastupdate as lastupdate from location left join user on(user.id=location.uid)
WHERE   location.uid NOT IN(select rid FROM bloodbank WHERE sid = ".$id." and sstatus='SEND' and rstatus='RECEIVE') AND location.uid!=".$id;
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
		$data['lattitude']=$row['lattitude'];	
		$data['longtitude']=$row['longtitude'];	
		$data['id']=$row['uid'];	
		$data['img']='';
		$data['name'] =$row['uname'];
		$data['mobile'] =$row['mobile'];
		$data['address'] =$row['address'];
		$data['lastupdate'] =$row['lastupdate'];
		$data['Blood'] =$row['bloodgroup'];
		$r['data'][]=$data;
			
    }
	$r['status']=1;
	echo json_encode($r);

}
 }
$conn->close();
?> 