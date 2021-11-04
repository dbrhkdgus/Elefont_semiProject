<%@page import="java.io.File"%>
<%@page import="com.kh.elefont.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://kit.fontawesome.com/76afde4c0b.js" crossorigin="anonymous"></script>
    <!-- Meta Tag -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- SEO -->
    <meta name="description" content="150 words">
    <meta name="author" content="uipasta">
    <meta name="url" content="http://www.yourdomainname.com">
    <meta name="copyright" content="company name">
    <meta name="robots" content="index,follow">


    <title>Elefont</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon/favicon.ico">
    <link rel="apple-touch-icon" sizes="144x144" type="image/x-icon" href="images/favicon/apple-touch-icon.png">

    <!-- All CSS Plugins -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/plugin.css">

    <!-- Main CSS Stylesheet -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

    <!-- Google Web Fonts  -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:400,300,500,600,700">

	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/elefontScript.js"></script>

    <!-- HTML5 shiv and Respond.js support IE8 or Older for HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	   <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	   <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<script>
/**
 * websocket client 설정
 */
 const ws = new WebSocket(`ws://\${location.host}<%=request.getContextPath()%>/elefontWebsocket`); //이 주소로 웹소켓 연결을 다시 요청
 ws.onopen = (e) => {
 } ;
 ws.onmessage = (e) => {
	 const msg = JSON.parse(e.data);
	 if("dm" === msg.type){
		 alert(`\${msg.sender}님의 메세지
------------------------------------
\${msg.msg}`);
	 }else{
		 msgToHtml(msg); 
	 }
 } ;
 ws.onerror = (e) => {
 } ;
 
 ws.onclose = (e) => {
 } ;



</script>

</head>

<body>
<script>
<%
	String msg = (String)session.getAttribute("msg");
	
	if(msg != null) { 
%> 
	
    alert("<%= msg %>");
    
    
<% 
	session.removeAttribute("msg"); } 

	Member loginMember = (Member)session.getAttribute("loginMember");

%>


</script>
    <!-- Preloader Start -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- Preloader End -->



    <!-- Home & Menu Section Start -->
    <header id="home" class="home-section">

        <div class="header-top-area">
            <div class="container">
                <div class="row">

                    <div class="col-sm-3">
                        <div class="logo">
                            <a href="<%= request.getContextPath() + "/" %>">Elefont</a>
                        </div>
                    </div>

                    <div class="col-sm-9">
                        <div class="navigation-menu">
                            <div class="navbar">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                                        data-target=".navbar-collapse">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                </div>
                                <div class="navbar-collapse collapse">
                                    <ul class="nav navbar-nav navbar-right">
                                        <li class="active"><a class="smoth-scroll" href="<%= request.getContextPath() %>">Home <div class="ripple-wrapper"></div></a>
                                        </li>
                                        <li><a class="smoth-scroll" href="<%= request.getContextPath() %>/shop">Shop</a>
                                        </li>
                                        <li><a class="smoth-scroll" href="<%= request.getContextPath() %>/community">Community</a>
                                        </li>
                                        <li><a class="smoth-scroll" href="<%= request.getContextPath() %>/faq">FAQ</a>
                                        </li>
                                        <li><a class="smoth-scroll" href="<%= request.getContextPath() %>/about">About</a>
                                        </li>

<%

if(loginMember != null){
	
	
	Member member = (Member) session.getAttribute("member");
	File profilePhotoAttach = (File) session.getAttribute("profilePhotoAttach");
	String photoPath = profilePhotoAttach.getName();
%>
                                        <li ><a class="smoth-scroll" id="profile" href="#user"><img id="profile-img" src="<%= request.getContextPath() %>/upload/profilephotos/<%=photoPath%>" ><span id="profile-loginMember-name"><%= loginMember.getMemberName() %></span></a>
                                        </li> 
<% 
}else{
%>
                                        <li><a class="smoth-scroll" id="loginBtn" href="#">Login</a>
                                        </li>
                                        
                                        <script>
                                    	/* 로그인 버튼 클릭 시 로그인 창 띄우기 */
                                    	$(loginBtn).click((e)=> {
                                    		if($('.loginBox').css('display') == 'none') {
                                    			$('.loginBox').show();
                                    		} else {
                                    			$('.loginBox').hide();
                                    		}
                                    	});
                                        </script>
<% } %>
                                    </ul>
                                </div>
                               	<div class = "loginBox">
                               		<form action="<%=request.getContextPath()%>/login/loginMember" name="" method="POST">
									    <input type="text" class="loginDesign" id="loginId" name="loginId" placeholder="아이디" >
									    <br>
									    <input type="password" class="loginDesign" id="loginPw" name="password" placeholder="비밀번호" >
									    <br>
									    <input type="submit" class="loginDesign" id="memberLoginBtn" value="로그인">
									    <input type="button" class="loginDesign" id="memberEnrollBtn" value="회원가입"/>
                               		</form>
								    
							    </div>
                    			
                    			
                    			
<%
	if(loginMember != null){
   		String memberRole = loginMember.getMemberRole();		
	
	if("U".equals(memberRole)){
%>
                    			
                                <ul class="member-menu">
                                	<li><a href="<%=request.getContextPath() %>/member/memberDetail"><img src="<%=request.getContextPath() %>/images/dropdown/home.png" alt="" />마이페이지</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/fontLikeList"><img src="<%=request.getContextPath() %>/images/dropdown/like.png" alt="" />좋아요 리스트</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberCart"><img src="<%=request.getContextPath() %>/images/dropdown/shopping-cart.png" alt="" />장바구니</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberLogout"><img src="<%=request.getContextPath() %>/images/dropdown/logout.png" alt="" />로그아웃</a></li>
                                </ul>
<%		
	}else if("S".equals(memberRole)){
%>
								<ul class="member-menu">
                                	<li><a href="<%=request.getContextPath() %>/member/memberDetail"><img src="<%=request.getContextPath() %>/images/dropdown/home.png" alt="" />마이페이지</a></li>
                                    <li><a href="<%=request.getContextPath() %>/seller/fontEnroll"><img src="<%=request.getContextPath() %>/images/dropdown/like.png" alt="" />폰트 등록</a></li>
                                    <li><a href="<%=request.getContextPath() %>/seller/fontAudit"><img src="<%=request.getContextPath() %>/images/dropdown/audit.png" alt="" />심사 현황</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberLogout"><img src="<%=request.getContextPath() %>/images/dropdown/logout.png" alt="" />로그아웃</a></li>
                                </ul>



<%
	}else if("A".equals(memberRole)){
%>
								<ul class="member-menu">
                                	<li><a href="<%=request.getContextPath() %>/member/memberDetail?index=0"><img src="<%=request.getContextPath() %>/images/dropdown/member.png" alt="" />회원 관리</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberDetail?index=1"><img src="<%=request.getContextPath() %>/images/dropdown/board.png" alt="" />게시판 관리</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberDetail?index=2"><img src="<%=request.getContextPath() %>/images/dropdown/shopping-cart.png" alt="" />주문 관리</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberDetail?index=3"><img src="<%=request.getContextPath() %>/images/dropdown/coupon.png" alt="" />쿠폰 관리</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberDetail?index=4"><img src="<%=request.getContextPath() %>/images/dropdown/fonts.png" alt="" />폰트 관리</a></li>
                                    <li><a href="<%=request.getContextPath() %>/member/memberLogout"><img src="<%=request.getContextPath() %>/images/dropdown/logout.png" alt="" />로그아웃</a></li>
                                </ul>





<%
	}
}             			
%>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>