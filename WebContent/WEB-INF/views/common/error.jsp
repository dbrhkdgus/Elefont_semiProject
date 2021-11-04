<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="shortcut icon" href="images/favicon/favicon.ico">
    <link rel="apple-touch-icon" sizes="144x144" type="image/x-icon" href="images/favicon/apple-touch-icon.png">

    <!-- All CSS Plugins -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/plugin.css">

    <!-- Main CSS Stylesheet -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

    <!-- Google Web Fonts  -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:400,300,500,600,700">
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>error</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style>
body { text-align: center; }

#goBackError{
     color:rgb(241, 105, 128); 
     text-decoration-line: none;
     font-size: 25px;
     font-family: 'Jua', sans-serif;

}
</style>
</head>
<body>
    <div id="errorImageDiv" style="margin-top: 80px;">
        <img id="errorImage" src="https://i.ibb.co/8r0Rwrz/error2.png" style="width: 1100px;" alt="">
    </div>
	<p><a id="goBackError" href="#" ><span><이전 페이지 돌아가기</span><img style="width: 60px;" src="https://i.ibb.co/yNWnxfF/Lovepik-com-400202184-a-house-with-a-warm-house.png" alt=""><span>></span></a></p>

   <footer class="footer-section" style="background: #A0BFB5; padding: 63px 0px;">
        <div class="container">
            <div class="row">

                <div class="col-md-4 text-left">
                    <p><span><a href="<%=request.getContextPath() %>/about" class="smoth-scroll">About Us</a></span> | <span><a href="mailto:watt.kwon@gmail.com"
                                class="smoth-scroll">Contact Us</a></span></p>
                </div>

                <div class="col-md-4 text-center">
                    <p>© Copyright 2021 Produce404</p>
                </div>

                <div class="col-md-4 uipasta-credit text-right">
                    <p>Designed By <span class="footer-teamName">Produce404</span></p>
                </div>

            </div>
        </div>
    </footer>

<script>
$(goBackError).click(()=>{
	window.history.back();
})
</script>
</body>
</html>