<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원가입</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script
    src="https://code.jquery.com/jquery-3.6.0.js"
    integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
    crossorigin="anonymous"></script>
	<style>
		.container{border:1px solid #ddd;max-width:700px;}
		h2{text-align: center;margin-bottom:45px;}
		input, button, select{font-size:0.9rem!important;}
		div[class*="col-"] span{font-size:13px;}
		.form-row:last-of-type input{font-weight: bold;}
		.pw-check span{display:none;}
	</style>
	
	<c:choose>
		<c:when test="${Kakao_email != null || Kakao_nickname != null }">
			<script>
				$(function(){
					alert("회원가입으로 진행합니다.");
					$("#idinput").attr('readonly', true);
					$("#checkid").css('display',"none");
				})
			</script>
		</c:when>
	</c:choose>
	<script>
	</script>
</head>
<body>
	
	
	<div class="container p-5 mt-5 shadow bg-white rounded">
		<h2>회원가입</h2>
		<form name = frmdate action = "auth.do" method="post">
			<div class="form-row align-items-center">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="inlineFormInput">아이디</label>
				</div>
				<div class="col-8 col-sm-auto mb-2">
					<input type="text" class="form-control" id="idinput" name = "email" placeholder="아이디를 입력해주세요." value = "${Kakao_email}" required ="required">
				</div>
				<div class="col-4 col-sm-auto mb-2">
					<button type="button" id = "checkid" name = "idCheck" class="btn btn-dark w-100" >중복체크</button>
				</div>
			</div>
			<div class="form-row align-items-cente">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="">비밀번호</label>
				</div>
				<div class="col-12 col-sm-auto mb-2">
					<input type="password" class="form-control" id="inp_pw01" name = "pw" placeholder="비밀번호를 입력해주세요." required ="required">
				</div>
			</div>
			<div class="form-row align-items-center">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="">비밀번호 확인</label>
				</div>
				<div class="col-12 col-sm-auto mb-2">
					<input type="password" class="form-control" id="inp_pw02" placeholder="비밀번호를 입력해주세요." required ="required">
				</div>
				<div class="col-12 col-sm-auto pw-check mb-2">
					<span class="text-danger" id="pw-danger">비밀번호가 일치하지 않습니다!</span>
					<span class="text-success" id="pw-success">비밀번호가 일치합니다.</span>
				</div>
			</div>
			<div class="form-row align-items-center">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="">이름</label>
				</div>
				<div class="col-12 col-sm-auto mb-2">
					<input type="text" class="form-control" id="" name = nickname placeholder="닉네임을 입력해주세요." value = "${Kakao_nickname}" pattern ="[ㄱ-ㅎ가-힣\D{1,30}" required ="required">
				</div>
			</div>
			<div class="form-row align-items-center">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="">전화번호</label>
				</div>
				<div class="col-4 col-sm-auto mb-2">
					<select class="form-control" name = "contact1" id="">
						<option>02</option>
						<option>010</option>
						<option>011</option>
					</select>
				</div>
				<div class="col-4 col-sm-auto mb-2">
					<input type="text" name = "contact2" class="form-control" id="" maxlength=4 pattern ="\d{3,4}" placeholder ="숫자만 입력" required ="required">
				</div>
				<div class="col-4 col-sm-auto mb-2">
					<input type="text" name = "contact3" class="form-control" id="" maxlength=4 pattern ="\d{4}" placeholder ="숫자만 입력" required="required">
				</div>
			</div>
			<!-- <div class="form-row align-items-center">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="">우편번호</label>
				</div>
				<div class="col-8 col-sm-auto mb-2">
					<input type="text" class="form-control" name = "address1" id="postcode">
				</div>
				<div class="col-4 col-sm-auto mb-2">
					<button type="button" class="btn btn-dark w-100" id="search">찾기</button>
				</div>
			</div>
			<div class="form-row align-items-center">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="">주소1</label>
				</div>
				<div class="col-12 col-sm-10 mb-2">
					<input type="text" class="form-control" id="address1" name = "address2" placeholder="주소를 입력해 주세요.">
				</div>
			</div>
			<div class="form-row align-items-center">
				<div class="col-12 col-sm-2 mb-2">
					<label class="mb-0" for="">주소2</label>
				</div>
				<div class="col-12 col-sm-10 mb-2">
					<input type="text" class="form-control" id=""  name = "address3" placeholder="주소를 입력해 주세요." required>
				</div>
			</div> -->
			<div class="form-row align-items-center mt-5">
				<div class="col-6 text-center">
					<input type="submit" class="btn btn-outline-primary w-100" value="회원가입">
				</div>
				<div class="col-6 text-center">
					<input type="reset" class="btn btn-outline-secondary w-100" value="다시입력">
				</div>
			</div>
			<input type = hidden name = "snscheck" value = "${snscheck}">
		</form>
	</div>
	<script>
		document.getElementById("inp_pw02").onkeyup = function(){
			let chkPW = document.getElementById("inp_pw01").value;
			if(chkPW === this.value){
				document.getElementById("pw-success").setAttribute("style","display:block");
				document.getElementById("pw-danger").setAttribute("style","display:none");
			}else{
				document.getElementById("pw-success").setAttribute("style","display:none");
				document.getElementById("pw-danger").setAttribute("style","display:block");
			}
		}
	/* 	document.getElementById("checkid").onclick = function(){
            document.getElementById("checkid").value = document.getElementById("idinput").value;
            window.open("","test","toolbar=no,directories=no,scrollbars=no,resizable=no,status=no,menubar=no,width=240, height=200, top=0,left=20");
            frmdate.target ="test";
            frmdate.method = "post";
            frmdate.action= "IDdupliCheck.jsp";
            frmdate.submit();
        } */
		
		
	</script>

</body>

</html>