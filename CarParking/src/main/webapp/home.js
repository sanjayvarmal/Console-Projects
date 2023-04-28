var obj=new XMLHttpRequest();
var table=document.querySelector("#view");
var date=document.querySelector("#date");
var k=0;
var users=document.querySelector("#users");

function ld(){
obj.onreadystatechange=function(){
	if(obj.readyState==4){
	var a=JSON.parse(obj.responseText);
	if(k==0){
		for(var i=0;i<a.length;i++){
				var element="<table><tr><td colspan='5'>Floor "+(i+1)+"</td></tr><tr><th>Row1</th><th>Row2</th><th>Row3</th><th>Row4</th><th>Row5</th></tr><tr>";
				for(var j=0;j<25;j++){
					if(j!=0 && j%5==0){
						element+="</tr>";
					if(j!=24)
						element+="<tr>";
					}
					element+="<td>"+(a[i][j]===null?"Empty":a[i][j])+"</td>";
				}
			table.innerHTML+=element;
		}
	}
	else if(k==1){
		let ele="";
		for(var i=0;i<a.length;i++){
			ele+="<tr><td>"+a[i].name+"</td><td>"+a[i].location+"</td><td>"+a[i].date+"</td><td>"+a[i].phnumber+"</td><td>"+a[i].vehnum
			+"</td><td>"+a[i].parktime+"</td><td>"+a[i].endtime+"</td></tr>";
		}
		if(ele!==undefined)
		users.innerHTML+=ele;
	}
	else if(k==2){
		console.log(a.val);
		if(a.val==0){
			window.alert("Data Insertion Failed Check Condition");
		}
		else{
			window.alert("Data Successfully Inserted");
		}
	}
	else if(k==3){
		if(+a>0)
		window.alert("The amount "+a+" Paid");
		else{
			window.alert("Enter Correct Vehicle Number?");
		}
	}
	else if(k==4){
		window.open("samhome.html","_self");
	}
	}
};
}
function logout(){
	k=4;
	obj.open("GET","http://localhost:8081/CarParking/sessionDelete");
	obj.send();
}
function update(){
	k=2;
	var obj1=new XMLHttpRequest();
	var pass=document.querySelector("#pass");
	var lot=document.querySelector("#lot");
	
	obj1.open("POST","http://localhost:8081/CarParking/Home");
	obj1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	obj1.send("pass="+pass.value+"&lot="+lot.value+"&val=2");
	pass.value=null;
	lot.value=undefined;
}
function pay(){
	var num=document.querySelector("#vehnum").value;
	k=3;
	obj.open("GET","http://localhost:8081/CarParking/Home?val=3&num="+num);
	obj.send();
}
function search(){
	k=1;
	obj.open("GET","http://localhost:8081/CarParking/Home?date="+date.value+"&val=1");
	obj.send();
}
window.onload=function(){
	ld();
	obj.open("GET","http://localhost:8081/CarParking/Home?val=0");
	obj.send();
}