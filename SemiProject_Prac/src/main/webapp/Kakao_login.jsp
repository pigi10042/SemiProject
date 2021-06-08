<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <img src="kakao_login_medium_wide.png" id = login>
    <script src = "https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
       
    window.onload = function(){
    	
       let subm = function(form,name,value){
       	var objs;
       	objs = document.createElement('input'); // 값이 들어있는 녀석의 형식
       	objs.setAttribute('type', 'hidden'); // 값이 들어있는 녀석의 type
       	objs.setAttribute('name', name); // 객체이름
       	objs.setAttribute('value',value); //객체값
       	form.appendChild(objs);
       	form.setAttribute('method', 'post'); //get,post 가능
       	form.setAttribute('action', "prac"); //보내는 url
       }
       
       document.getElementById("login").addEventListener("click",function(){
    	   Kakao.init("1eb5e9c899e99de2033fc38ce5354d2d");
    	   Kakao.Auth.login({
               scope:'account_email',
               success :function(authObj){
                   console.log(authObj);
                   Kakao.API.request({
                       url:'/v2/user/me',
                       success: function(res){
                        var form = document.createElement('form');                   
                        subm(form,"email",res.kakao_account['email']);
                       	subm(form,"nickname",res.properties['nickname']);
                    	document.body.appendChild(form);
                       	form.submit(); 
                         },
                       fail:function(error){
                           console.log("에러페이지"+error);
                       }
                   })
               }

           })
       })
    }

    </script> 
</body>
</html>