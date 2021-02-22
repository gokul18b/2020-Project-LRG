var candidateList;
var voterList;
var final_id;
$(document).ready(function(){

var url_string = window.location.href;
var url = new URL(url_string);
final_id = url.searchParams.get("id");

$("#profileimg").attr('src',"/images/user/"+final_id+".jpg");


ViewCandidateList();
setProfile(final_id);
ViewVoterList();
setReport();

});

function setReport(){
	var chair='';
	var vicechair='';
	var collegesec='';
	var financesec='';
	var treasurer='';
	var sportssec='';
	$.ajax({
			type:"GET",
			url:"http://localhost:8080/api/votreport",
			success: function(datas) {
				var ch=0,vc=0,cs=0,fs=0,ts=0,ss=0;
				for(var i=0;i<datas.length;i++){
					var data = datas[i];
					if(data.postid==1){
						ch++;
						chair=chair+`<tr><td>`+ch+`</td>
											<td>`+data.firstname+`</td>
											<td>`+data.count+`</td></tr>`;
					}
					if(data.postid==2){
						vc++;
						vicechair=vicechair+`<tr><td>`+vc+`</td>
											<td>`+data.firstname+`</td>
											<td>`+data.count+`</td></tr>`;
					}
					if(data.postid==3){
						cs++;
						collegesec=collegesec+`<tr><td>`+cs+`</td>
											<td>`+data.firstname+`</td>
											<td>`+data.count+`</td></tr>`;
					}
					if(data.postid==4){
						fs++;
						financesec=financesec+`<tr><td>`+fs+`</td>
											<td>`+data.firstname+`</td>
											<td>`+data.count+`</td></tr>`;
					}
					if(data.postid==5){
						ts++;
						treasurer=treasurer+`<tr><td>`+ts+`</td>
											<td>`+data.firstname+`</td>
											<td>`+data.count+`</td></tr>`;
					}
					if(data.postid==6){
						ss++;
						sportssec=sportssec+`<tr><td>`+ss+`</td>
											<td>`+data.firstname+`</td>
											<td>`+data.count+`</td></tr>`;
					}
					
				}
				$("#chairman").html(chair);
				$("#vicechairman").html(vicechair);
				$("#collegesec").html(collegesec);
				$("#financesec").html(financesec);
				$("#treasurer").html(treasurer);
				$("#sportssec").html(sportssec);
				
			},
		});

}

function setProfile(id){
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/getprofile/"+id,
			data:{id:id},
			success: function(datas) {
				
				var data = datas[0];
				$("#proname").text(data['firstname']+" "+data['lastname']);
				$("#promobile").text(data['mobile']);
				$("#proemail").text(data['email']);
				$("#proaddress").text(data['address']);
				
				
				
			},
		});
}

function addCandidate(){
	
	swal({
  title: "Are you sure?",
  text: "You want to add Candidate!",
  type: "warning",
  showCancelButton: true,
  confirmButtonClass: "btn-danger",
  confirmButtonText: "Yes, Sumbit it!",
  cancelButtonText: "No, cancel!",
  closeOnConfirm: false,
  closeOnCancel: false
},
function(isConfirm) {
  if (isConfirm) {
	 var firstname=$("input[name=canfirstname]").val().trim();
	var lastname=$("input[name=canlastname]").val().trim();
	var mobile=$("input[name=canmobile]").val().trim();
	var email=$("input[name=canemail]").val().trim();
	var address=$("input[name=canaddress]").val().trim();
	var post=$('#canoption').val().trim();
	
	if(firstname.length==0 || lastname.length==0 || mobile.length==0 || email.length==0 || address.length==0 || post.length==0){
		swal( "Required Fields", 'Should Enter all fields!', 'warning');
	}else{
		$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/addCandidate/"+firstname+"/"+lastname+"/"+mobile+"/"+email+"/"+address+"/"+post,
			data:{firstname:firstname,lastname:lastname,mobile:mobile,email:email,address:address,post:post},
			success: function(datas) {
				swal("Success!", "Your Record has been deleted.", "success");
				$("input[name=canfirstname]").val("");
				$("input[name=canlastname]").val("");
				$("input[name=canmobile]").val("");
				$("input[name=canemail]").val("");
				$("input[name=canaddress]").val("");
				ViewCandidateList();
			},
		});
	}
    
  } else {
    swal("Cancelled", "Your record is note Added :)", "error");
  }
});


	
	
}

function ViewCandidateList(){
	//
	
		$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/viewCandidates",
			success: function(datas) {
				var html='';
				candidateList=datas;
				for(var i=0;i<datas.length;i++){
					var data = datas[i];
					html =html+ `<li><img onclick="deletecandidate('`+i+`')" style="float:right" src="/images/delete.png"></img><a onclick="modal('`+i+`','candidate')"  style="cursor:pointer" data-toggle="modal">`+data['firstname']+` `+data['lastname']+`</a></li>`;
				}
				
				$("#myUL").html(html);
				
			
			},
		});	
		
		
	
}
function modal(pos,path){
	var postname='';
	if(candidateList[pos]['post']==1){
		postname="Chairman";
	}else if(candidateList[pos]['post']==2){
		postname="Vice Chairman";
	}
	else if(candidateList[pos]['post']==3){
		postname="College Secretary";
	}
	else if(candidateList[pos]['post']==4){
		postname="Finance Secretary";
	}
	else if(candidateList[pos]['post']==5){
		postname="Treasurer";
	}
	else if(candidateList[pos]['post']==6){
		postname="Sports Secretary";
	}
	
	$("#modalimg").attr('src',"/images/"+path+"/"+candidateList[pos]['userid']+".jpg");
	$("#modname").text(candidateList[pos]['firstname']+' '+ candidateList[pos]['lastname']);
	$("#modmobile").text(candidateList[pos]['mobile']);
	$("#modpost").text(postname);
	$("#modemail").text(candidateList[pos]['email']);
	$("#modaddress").text(candidateList[pos]['address']);
	$('#myModal').modal('show'); 
	
}

function deletecandidate(pos){
swal({
  title: "Are you sure?",
  text: "You will not be able to recover Candidate detail!",
  type: "warning",
  showCancelButton: true,
  confirmButtonClass: "btn-danger",
  confirmButtonText: "Yes, delete it!",
  cancelButtonText: "No, cancel plx!",
  closeOnConfirm: false,
  closeOnCancel: false
},
function(isConfirm) {
  if (isConfirm) {
	  $.ajax({
			type:"POST",
			data:{id:candidateList[pos]['userid']},
			url:"http://localhost:8080/api/deleteCandidates/"+candidateList[pos]['userid'],
			success: function(datas) {
				swal("Deleted!", "Your imaginary file has been deleted.", "success");
				ViewCandidateList();
			},
		});	
	
    
  } else {
    swal("Cancelled", "Your record is safe :)", "error");
  }
});
	
	
	
}



function addVoter(){
	
	swal({
  title: "Are you sure?",
  text: "You want to add Voter!",
  type: "warning",
  showCancelButton: true,
  confirmButtonClass: "btn-danger",
  confirmButtonText: "Yes, Sumbit it!",
  cancelButtonText: "No, cancel!",
  closeOnConfirm: false,
  closeOnCancel: false
},
function(isConfirm) {
  if (isConfirm) {
	 var firstname=$("input[name=votfirstname]").val().trim();
	var lastname=$("input[name=votlastname]").val().trim();
	var mobile=$("input[name=votmobile]").val().trim();
	var email=$("input[name=votemail]").val().trim();
	var address=$("input[name=votaddress]").val().trim();
	
	
	if(firstname.length==0 || lastname.length==0 || mobile.length==0 || email.length==0 || address.length==0 ){
		swal( "Required Fields", 'Should Enter all fields!', 'warning');
	}else{
		$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/addVoter/"+firstname+"/"+lastname+"/"+"/"+mobile+"/"+email+"/"+address,
			data:{firstname:firstname,lastname:lastname,mobile:mobile,email:email,address:address},
			success: function(datas) {
				swal("Success!", "Your Record has been Register.", "success");
				$("input[name=votfirstname]").val("");
				$("input[name=votlastname]").val("");
				$("input[name=votmobile]").val("");
				$("input[name=votemail]").val("");
				$("input[name=votaddress]").val("");
				ViewVoterList();
			},
		});
	}
    
  } else {
    swal("Cancelled", "Your record is note Added :)", "error");
  }
});


	
	
}

function ViewVoterList(){
	//
	
		$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/viewVoter",
			success: function(datas) {
				var html='';
				voterList=datas;
				for(var i=0;i<datas.length;i++){
					var data = datas[i];
					html =html+ `<li><img onclick="deletevoter('`+i+`')" style="float:right" src="/images/delete.png"></img><a onclick="votmodal('`+i+`','user')"  style="cursor:pointer" data-toggle="modal">`+data['firstname']+` `+data['lastname']+`</a></li>`;
				}
				
				$("#filtervote").html(html);
				
			
			},
		});	
		
		
	
}


function deletevoter(pos){
swal({
  title: "Are you sure?",
  text: "You will not be able to recover Voter detail!",
  type: "warning",
  showCancelButton: true,
  confirmButtonClass: "btn-danger",
  confirmButtonText: "Yes, delete it!",
  cancelButtonText: "No, cancel plx!",
  closeOnConfirm: false,
  closeOnCancel: false
},
function(isConfirm) {
  if (isConfirm) {
	  $.ajax({
			type:"POST",
			data:{id:voterList[pos]['userid']},
			url:"http://localhost:8080/api/deleteVoter/"+voterList[pos]['userid'],
			success: function(datas) {
				swal("Deleted!", "Your imaginary file has been deleted.", "success");
				ViewVoterList();
			},
		});	
	
    
  } else {
    swal("Cancelled", "Your record is safe :)", "error");
  }
});
	
}

function votmodal(pos,path){
	var postname='';

	$("#modalimg").attr('src',"/images/"+path+"/"+candidateList[pos]['userid']+".jpg");
	$("#modname").text(voterList[pos]['firstname']+' '+ voterList[pos]['lastname']);
	$("#modmobile").text(voterList[pos]['mobile']);
	$("#modpost").text("Voter");
	$("#modemail").text(voterList[pos]['email']);
	$("#modaddress").text(voterList[pos]['address']);
	$('#myModal').modal('show'); 
	
}


function filterVoter() {
	
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("filtervote");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";

        }
    }
}

function myFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";

        }
    }
}




