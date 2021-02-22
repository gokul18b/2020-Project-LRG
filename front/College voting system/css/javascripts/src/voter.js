var candidateList;
var final_id;

$(document).ready(function(){
	var url_string = window.location.href;
var url = new URL(url_string);
final_id = url.searchParams.get("id");

$("#proimg").attr('src',"/images/user/"+final_id+".jpg");
setProfile(final_id);	
ViewCandidateList();
setVoting();
setReport();



$( "#btn_chairman" ).click(function() {
	 $(this).prop("disabled",true);
  var post_id=1;
  var votid=final_id;
  var canid=$('#chairman_opt').val().trim();
  pullVote(post_id,canid,votid);
  setReport();
});

$( "#btn_vicechairman" ).click(function() {
	$(this).prop("disabled",true);
  var post_id=2;
  var votid=final_id;
  var canid=$('#vicechairman_opt').val().trim();
  pullVote(post_id,canid,votid);
  setReport();
});
$( "#btn_college" ).click(function() {
	$(this).prop("disabled",true);
  var post_id=3;
  var votid=final_id;
  var canid=$('#college_opt').val().trim();
  pullVote(post_id,canid,votid);
  setReport();
});
$( "#btn_finance" ).click(function() {
	$(this).prop("disabled",true);
  var post_id=4;
  var votid=final_id;
  var canid=$('#finance_opt').val().trim();
  pullVote(post_id,canid,votid);
  setReport();
});
$( "#btn_treasurer" ).click(function() {
	$(this).prop("disabled",true);
  var post_id=5;
  var votid=final_id;
  var canid=$('#treasurer_opt').val().trim();
  pullVote(post_id,canid,votid);
  setReport();
});
$( "#btn_sports" ).click(function() {
	$(this).prop("disabled",true);
  var post_id=6;
  var votid=final_id;
  var canid=$('#sports_opt').val().trim();
  pullVote(post_id,canid,votid);
  setReport();
});

});



function setVoting(){
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/validateVoting/"+final_id,
			data:{id:final_id},
			success: function(datas) {
				for(var i=0;i<datas.length;i++){
					var data = datas[i];
					if(data['postid']==1){
						$("#btn_chairman").prop("disabled",true);
					}
					if(data['postid']==2){
						$("#btn_vicechairman").prop("disabled",true);
					}
					if(data['postid']==3){
						$("#btn_college").prop("disabled",true);
					}
					if(data['postid']==4){
						$("#btn_finance").prop("disabled",true);
					}
					if(data['postid']==5){
						$("#btn_treasurer").prop("disabled",true);
					}
					if(data['postid']==6){
						$("#btn_sports").prop("disabled",true);
					}
				}
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

function setVotingDetails(){
	var opt_chairman='';
	var opt_vicechairman='';
	var opt_college='';
	var opt_finance='';
	var opt_treasurer='';
	var opt_sports='';
	for(var i=0;i<candidateList.length;i++){
		var data= candidateList[i];
		if(data['post']==1){
			opt_chairman+="<option value="+data['userid']+">"+data['firstname']+" "+data['lastname']+"</option>";
		}
		if(data['post']==2){
			opt_vicechairman+="<option value="+data['userid']+">"+data['firstname']+" "+data['lastname']+"</option>";
		}
		if(data['post']==3){
			opt_college+="<option value="+data['userid']+">"+data['firstname']+" "+data['lastname']+"</option>";
		}
		if(data['post']==4){
			opt_finance+="<option value="+data['userid']+">"+data['firstname']+" "+data['lastname']+"</option>";
		}
		if(data['post']==5){
			opt_treasurer+="<option value="+data['userid']+">"+data['firstname']+" "+data['lastname']+"</option>";
		}
		if(data['post']==6){
			opt_sports+="<option value="+data['userid']+">"+data['firstname']+" "+data['lastname']+"</option>";
		}
	}
	$("#chairman_opt").html(opt_chairman);
	$("#vicechairman_opt").html(opt_vicechairman);
	$("#college_opt").html(opt_college);
	$("#finance_opt").html(opt_finance);
	$("#treasurer_opt").html(opt_treasurer);
	$("#sports_opt").html(opt_sports);
}
function pullVote(postid,canid,votid){
	$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/pullvote/"+canid+"/"+votid+"/"+postid,
			data:{postid:postid,canid:canid,votid:votid},
			success: function(datas) {
				//refreshVoting();
			},
		});	
}

function setGraph(){
chairman();
vicechairman();
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
					html =html+ `<li><a onclick="modal('`+i+`')"  style="cursor:pointer" data-toggle="modal">`+data['firstname']+` `+data['lastname']+`</a></li>`;
				}
				setVotingDetails();
				$("#myUL").html(html);
				
			
			},
		});	
		
		
	
}
function modal(pos){
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
	
	$("#modalimg").attr('src',"/images/user/"+candidateList[pos]['userid']+".jpg");
	$("#modname").text(candidateList[pos]['firstname']+' '+ candidateList[pos]['lastname']);
	$("#modmobile").text(candidateList[pos]['mobile']);
	$("#modpost").text(postname);
	$("#modemail").text(candidateList[pos]['email']);
	$("#modaddress").text(candidateList[pos]['address']);
	$('#myModal').modal('show'); 
	
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

function chairman(){
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);

	// Draw the chart and set the chart values
	function drawChart() {
	  var data = google.visualization.arrayToDataTable([
	  ['Task', 'Hours per Day'],
	  ['Work', 1],
	  ['Eat', 1],
	  ['TV', 2],
	  ['Gym', 1],
	  ['Sleep', 2]
	]);

	  // Optional; add a title and set the width and height of the chart
	  var options = {'title':'Chairman', 'width':550, 'height':400};

	  // Display the chart inside the <div> element with id="piechart"
	  var chart = new google.visualization.PieChart(document.getElementById('chairman'));
	  chart.draw(data, options);
	}	
}

function vicechairman(){
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);

	// Draw the chart and set the chart values
	function drawChart() {
	  var data = google.visualization.arrayToDataTable([
	  ['Task', 'Hours per Day'],
	  ['Work', 1],
	  ['Eat', 1],
	  ['TV', 2],
	  ['Gym', 1],
	  ['Sleep', 2]
	]);

	  // Optional; add a title and set the width and height of the chart
	  var options = {'title':'Vice Chairman', 'width':550, 'height':400};

	  // Display the chart inside the <div> element with id="piechart"
	  var chart = new google.visualization.PieChart(document.getElementById('vicechairman'));
	  chart.draw(data, options);
	}	
}

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


