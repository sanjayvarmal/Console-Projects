var obj=new XMLHttpRequest();
var load=document.querySelector("#load");
var k=0;
var ques=document.querySelector("#question");
var ques1=document.querySelector("#getquestion");
var ques2=document.querySelector("#book");
var temp;
var i1=document.querySelector(".i1");
var i2=document.querySelector(".i2");
var i3=document.querySelector(".i3");
let name=document.querySelector("#name");
var value=document.querySelector("#pass");
var namelabel=document.querySelector("#namelabel");
var valuelabel=document.querySelector("#passwordlabel");

function i11(){
	i1.style.display="block";
}
function i22(){
	i3.style.display="block";
}
value.addEventListener("focusin",function(){
		valuelabel.style.top="-25%";
});
name.addEventListener("focusin",function(){
		namelabel.style.top="-25%";
});
value.addEventListener("focusout",function(){
	if(value.value!=""){
		valuelabel.style.top="-25%";
	}
	else{
		valuelabel.style.top="6%";	
	}
});
name.addEventListener("focusout",function(){
	if(name.value!=""){
		namelabel.style.top="-25%";
	}
	else{
		namelabel.style.top="6%";	
	}
});
function ld(){
	if(name.value=="" || value.value=="" ){
		window.alert("Empty Spaces Not Allowed");
		return ;
	}
	load.style.display="flex";
	setTimeout(func(),1000);
}
obj.onreadystatechange=function(){
	if(obj.readyState==4){
		if(k==0){
			var a=obj.responseText;
			if(a==0){
				window.open("home.html","_self");
			}
			else if(a==1){
				window.open("admin.html","_self");
			}
			else{
				window.alert("Incorrect User name or password");
				window.open("Login.html","_self");
			}
		}
		else if(k==1){
			var a=JSON.parse(obj.responseText);
			temp=a;
			var str="<table><tr><td>Location</td><td>Slot</td><td>Price</td></tr>";
			let content=document.querySelector("#content");
			for(var i=0;i<a.length;i++){
				str+="<tr><td>"+a[i].loc+"</td><td>"+a[i].slot+"</td><td>"+a[i].price+"</td></tr>";
			}
			str+="</table>";
			content.innerHTML=str;
			ques.style.display="none";
			console.log(ques2);
			ques2.style.display="none";
			ques1.style.display="block";
		}
	}
};

function func(){
	k=0;

obj.open("POST","http://localhost:8081/CarParking/login");
obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
obj.send("val=0&name="+name.value+"&password="+pass.value);
}
function paste(){
	ques.style.display="none";
	ques2.style.display="block";
	ques1.style.display="none";
	let val=document.querySelector("#number").value;
	let loca=document.querySelector("#loc");
	loca.value=temp[+val-1].loc;
}
function getdet()
{
	k=1;
	let location=document.querySelector("#location").value;
	obj.open("GET","http://localhost:8081/CarParking/login?loc="+location);
	obj.send();	
}