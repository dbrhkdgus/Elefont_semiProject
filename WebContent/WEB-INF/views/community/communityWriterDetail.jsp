<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
    
    <!-- community writer detail 시작 -->
<%
	Member writerMember = (Member)request.getAttribute("writerMember");
System.out.println("writerMember@jsp : " +  writerMember);

%>
<section id="portfolio" class="portfolio section-space-padding">
    <div id="writer-detail"class="container"> 
        <div class="comm-writer-detail">

            <div class="comm-writer-info">
                
                <img src="./images/free-icon-male-user-74464.png" alt="" class="writer-profile-img">
                <h2><%= writerMember.getMemberName() %></h2>
                <h2>게시글 수 : <span><%=  %></span></h2>
            </div>
            
            <div class="comm-writer-history">
                <div class="comm-writer-comm-history">
                    <p>커뮤니티 게시글 이력</p>
                    <div class="comm-writer-comm-history-content">
                        
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                       
                    </div>
                    <div class="comm-writer-comm-history-content">
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                        <img src="./images/free-icon-photo-frame-3342153.png" alt="">
                       
                    </div>
                </div>
                <div class="comm-writer-font-history">
                    <p>폰트 좋아요 이력</p>
                    <div class="comm-writer-font-history-content">
                        <div class="like-item-comm">
                            <a href=""><div class="like-item-title"> 광현체 </div></a>
                            <textarea name="" id="" cols="30" rows="10"></textarea> 
                        </div>
                        <div class="like-item-comm">
                            <a href=""><div class="like-item-title"> 광현체 </div></a>
                            <textarea name="" id="" cols="30" rows="10"></textarea> 
                        </div> 
                        <div class="like-item-comm">
                            <a href=""><div class="like-item-title"> 광현체 </div></a>
                            <textarea name="" id="" cols="30" rows="10"></textarea> 
                        </div> 
                        <div class="like-item-comm">
                            <a href=""><div class="like-item-title"> 광현체 </div></a>
                            <textarea name="" id="" cols="30" rows="10"></textarea> 
                        </div> 
                    </div>
                </div>
            </div>
            
        </div>
            
    </div>
</section>
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>

<!-- community user detail 끝 -->