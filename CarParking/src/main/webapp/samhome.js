const xhr = new XMLHttpRequest();
var obj = new XMLHttpRequest();
var search=document.querySelector("#input");
let content=document.querySelector("#innermap");
var k=-1;
var surroundings;

/* input up down effect code */
function effect(obj){
  if(obj.value!=""){
    obj.nextElementSibling.style.top="-25%";
  }
  else{
    obj.nextElementSibling.style.top="6%";  
  }
}
/* end here */

search.addEventListener("keyup",function(){
  let str="";
  for(let i=0;i<surroundings.length;i++){
      if(surroundings[i].loc.indexOf(search.value)>-1){
        str+="<div class=\"tags\" onclick=\"book(this.children[0].innerHTML)\"><h1>"+surroundings[i].loc+"</h1><span>No Of Lots Available :</span><span class=\"amount\">"+surroundings[i].slot+"</span><span>Hour Price (H/P):</span><span class=\"amount\">"+surroundings[i].price+"</span></div>";
      }
  }
  content.innerHTML=str;
});


let form=document.querySelectorAll(".loginform");
function login(){
form[0].style.display="flex";
form[1].style.display="none";
} 

function sign(){
form[0].style.display="none";
form[1].style.display="flex";
}

let body=document.querySelector("#body");
body.addEventListener("click",function(){
  form[0].style.display="none";
  form[1].style.display="none";
});

let password=document.querySelector("#pass3");
let orgpass=document.querySelector("#pass2");
password.addEventListener("keyup",function(){
  if(password.value!="" && password.value!=orgpass.value.substring(0,password.value.length)){
    alert("Not Correct Previous Password");
  }
});
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
      surroundings=a;
      let str="";
      let map=document.querySelector("#map");
      map.style.display="block";
      for(var i=0;i<a.length;i++){
        str+="<div class=\"tags\" onclick=\"book(this.children[0].innerHTML)\"><h1>"+a[i].loc+"</h1><span>No Of Lots Available :</span><span class=\"amount\">"+a[i].slot+"</span><span>Hour Price (H/P):</span><span class=\"amount\">"+a[i].price+"</span></div>";
      }
      content.innerHTML=str;
    }
    else if(k==2){
      var a=obj.responseText;
      if(a==0){
        window.open("home.html","_self");
      }
      else if(a==1){
        window.open("admin.html","_self");
      }
    }
  }
};

function book(location){
  document.querySelector("#loc").value=location;
  document.querySelector("#form").style.display="flex";
  let loc=document.querySelector("#loc");
  loc.value=location;
  loc.nextElementSibling.style.top="-25%"
  document.querySelector("#map").style.filter="blur(20px)";
}

function getdet()
{
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function showPosition(position) {
  k=1;
  const data = null;
  console.log(position.coords.latitude+" <======> "+position.coords.longitude);
  xhr.withCredentials = true;
  xhr.addEventListener("readystatechange", function () {
    if (this.readyState === this.DONE) {
        console.log(this.responseText);
      let sam=JSON.parse(this.responseText);
      let val="";
      for(let i=0;i<sam.length-1;i++){
        val+=sam[i].City+"@";
      }
      val+=sam[sam.length-1].City;
      
      console.log(val);
     obj.open("GET","http://localhost:8081/CarParking/login?val="+val);
    obj.send();
    } 
  });
  xhr.open("GET", "https://geocodeapi.p.rapidapi.com/GetNearestCities?latitude="+position.coords.latitude+"&longitude="+position.coords.longitude+"&range=0");
  xhr.setRequestHeader("X-RapidAPI-Key", "5f312d6a76msh134f062b4d6774dp190ce7jsn06be22d49426");
  xhr.setRequestHeader("X-RapidAPI-Host", "geocodeapi.p.rapidapi.com");
  
  xhr.send(data);
}
window.onload=function(){
  k=2;
  obj.open("GET","http://localhost:8081/CarParking/sessionCheck");
  obj.send();
};