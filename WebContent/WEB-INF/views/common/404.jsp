<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style>
body { text-align: center; }

#goBack404{
     color:rgb(241, 105, 128); 
     text-decoration-line: none;
     font-size: 25px;
     font-family: 'Jua', sans-serif;

}
</style>
</head>
<body>
    <div id="404ImageDiv" style="margin-top: 150px;">
        <img id="404Image" src="https://i.ibb.co/dQ7zbHL/404.png" style="width: 900px;" alt="">
    </div>
	<p><a id="goBack404" href="<%= request.getContextPath() %>" ><span><홈으로 돌아가기</span><img style="width: 60px;" src="https://i.ibb.co/yNWnxfF/Lovepik-com-400202184-a-house-with-a-warm-house.png" alt=""><span>></span></a></p>
</body>
</html>