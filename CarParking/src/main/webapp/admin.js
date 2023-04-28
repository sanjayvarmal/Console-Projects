var obj=new XMLHttpRequest();
var k=0,hi=0;
var select=document.querySelector("#select");
var lots=document.querySelector("#displaylots");
var searched=document.querySelector("#searched");

function ld(){
obj.onreadystatechange=function(){
		if(obj.readyState==4){
			var a=JSON.parse(obj.responseText);
			if(k==0){
				for(var i=0;i<Object.keys(a).length;i++){
				let sample=document.createElement("option");
					sample.text=a[i];
					select.add(sample);
				}
			}
			else if(k==1){	
				var element=""
				for(var i=0;i<a.length;i++){
						element+="<table><tr><td colspan='5'>Floor "+(i+1)+"</td></tr><tr><th>Row1</th><th>Row2</th><th>Row3</th><th>Row4</th><th>Row5</th></tr><tr>";
						for(var j=0;j<25;j++){
							if(j!=0 && j%5==0){
								element+="</tr>";
							if(j!=24)
								element+="<tr>";
							}
							element+="<td>"+(a[i][j]===null?"Empty":a[i][j])+"</td>";
						}
				}
				lots.innerHTML=element;
			}
			else if(k==2){
				if(a[0][0]=="null"){
					window.alert("vehicle Number Is Not Database");
				}
				else{
					var ele="<table><tr><th>Name</th><th>Location</th><th>Date</th><th>Phonenumber</th><th>Vehiclenumber</th></tr><tr>";
						for(var i=0;i<a.length;i++){
							ele+="<tr><td>"+a[i].name+"</td><td>"+a[i].location+"</td><td>"+a[i].date+"</td><td>"+a[i].phnum+"</td><td>"+a[i].vhnum+"</td></tr>"
						}
					searched.innerHTML=ele;
			        var sTable = searched.innerHTML;
			
			        var style = "<style>";
			        style = style + "table {width: 100%;font: 17px Calibri;}";
			        style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
			        style = style + "padding: 2px 3px;text-align: center;}";
			        style = style + "</style>";
			
			        // CREATE A WINDOW OBJECT.
			        var win = window.open('', '', 'height=700,width=700');
			
			        win.document.write('<html><head>');
			        win.document.write('<title>Profile</title>');   // <title> FOR PDF HEADER.
			        win.document.write(style);          // ADD STYLE INSIDE THE HEAD TAG.
			        win.document.write('</head>');
			        win.document.write('<body>');
			        win.document.write(sTable);         // THE TABLE CONTENTS INSIDE THE BODY TAG.
			        win.document.write('</body></html>');
			
			        win.document.close(); 	// CLOSE THE CURRENT WINDOW.
			
			        win.print();    // PRINT THE CONTENTS.	
				}
			}
			else if(k==3){
				if(a.val=="0"){
					window.alert("User Lot Deleted Successfully");
				}
				else{
					window.alert("Invalid Userid or password");
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
function dele(){
	k=3;
	let car=document.querySelector("#names").value.trim();
	obj.open("GET","http://localhost:8081/CarParking/admin?val=3&names="+car);
	obj.send();
}
function get(){
	k=2;
	let car=document.querySelector("#number").value;
	obj.open("GET","http://localhost:8081/CarParking/admin?val=2&number="+car);
	obj.send();
}
function view(){
	k=1;	
	hi++;
	if(hi%2==0){
	obj.open("GET","http://localhost:8081/CarParking/admin?val=1&select="+select.value);
	obj.send();
	}
}
window.onload=function(){
	ld();
	obj.open("GET","http://localhost:8081/CarParking/admin?val=0");
	obj.send();
};