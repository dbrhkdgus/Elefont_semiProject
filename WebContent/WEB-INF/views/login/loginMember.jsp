<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
</head>
<body>
	<h2>로그인</h2>
	<button id="loginBtn" type="button">로그인 버튼</button>
<script>
		$(loginBtn).on("click", (e)=>{
			let login = confirm("로그인하시겠습니까?");
			console.log(login);
			if(login){
				alert("로그인되었습니다");
				location.href = "<%=request.getContextPath()%>/login/loginMember";
			}
		});
</script>
</body>
</html>