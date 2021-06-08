<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
    src="https://code.jquery.com/jquery-3.6.0.js"
    integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
    crossorigin="anonymous"></script>
 <script src = "https://developers.kakao.com/sdk/js/kakao.js"></script>    
<style>
img{ width : 100%; }
</style>

<script>
	$(function(){
		let subm = function(form,name,value,url){
	       	var objs;
	       	objs = document.createElement('input'); // 값이 들어있는 녀석의 형식
	       	objs.setAttribute('type', 'hidden'); // 값이 들어있는 녀석의 type
	       	objs.setAttribute('name', name); // 객체이름
	       	objs.setAttribute('value',value); //객체값
	       	form.appendChild(objs);
	       	form.setAttribute('method', 'post'); //get,post 가능
	       	form.setAttribute('action', url); //보내는 url
	       }
		$("#kakao").on("click", function(){
			 Kakao.init("1eb5e9c899e99de2033fc38ce5354d2d");
	    	   Kakao.Auth.login({
	               scope:'account_email',
	               success :function(authObj){
	                   console.log(authObj);
	                   Kakao.API.request({
	                       url:'/v2/user/me',
	                       success: function(res){
	                        var form = document.createElement('form');                   
	                        subm(form,"email",res.kakao_account['email'],"sign.do");
	                       	subm(form,"nickname",res.properties['nickname'],"sign.do");
	                       	subm(form, "snsCheck", 1);
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
		
		$("#signup").on("click", function(){
			location.href = "signForm.jsp";
		})
	})

</script>
</head>
<body>
	<center>
	<form action = "login.do" method = post>
		<table width=200px>
			<tr>
				<td colspan =2><h2>Login Box</h2>
			</tr>
			<tr>
				<td width = 3> ID
				<td width =7> <input type =text name =id>
			</tr>
			<tr>
				<td width = 3> PW
				<td width =7> <input type =password name =pw>
			</tr>
			<tr>
				<td colspan=2> <button type = submit id = submit>로그인하기</button>
			</tr>	
			<tr>
				<td colspan=2> <button type = button id = signup>회원가입</button>
			</tr>	
			<tr>
				<td colspan=2><img src ="https://lh3.googleusercontent.com/proxy/AMglcnULDcti4CiqUX92xN7KVsFMeucHM3NIJnFHMDCllIkvE07uC--8ELGFEtHF_dPz6wGfBZ-43986Id5cWHXt" id = kakao></td>
			</tr>
		</form>
		</table>	
	</center>

</body>
</html>