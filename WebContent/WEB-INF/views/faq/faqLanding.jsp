<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.faq.model.vo.Faq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<% List<Faq> faqList = (List<Faq>)request.getAttribute("faqList"); 
System.out.println("jsp에 잘 왔슴다!"+faqList);
%>


        <section id="portfolio" class="portfolio section-space-padding">     
            <div id ="faqbox">
                <h1 id="faqH1">FAQ</h1>
                <div class="faq_container">
                    <div class="faq_title"><span id=faq_title_content>Q. 폰트 구매는 어떻게 하나요? </span><i class="fas fa-chevron-down"></i></div>
                        <div class="faq_content"> 흑흑흑흑흑흑흑<br>흑흑흑흑흑흑흑<br>흑흑흑흑흑흑흑</div>
                        <div class="faq_title"><span class=faq_title_content>Q. 폰트 사용법에대해 궁금합니다!</span><i class="fas fa-chevron-down"></i></div>
                        <div class="faq_content"> 어떻게 하냐면요 포인트를 넣고요 일단 회원가입은 하셨어요?</div>
                        <div class="faq_title"><span class=faq_title_content>Q. 제가 쓴 글은 어떻게 볼 수 있나요? </span><i class="fas fa-chevron-down"></i></div>
                        <div class="faq_content"> 어떻게 하냐면요 포인트를 넣고요 일단 회원가입은 하셨어요?</div>
                        <div class="faq_title"><span class=faq_title_content>Q.  오늘 저녁메뉴는 무엇인가요? </span><i class="fas fa-chevron-down"></i></div>
                        <div class="faq_content"> 어떻게 하냐면요 포인트를 넣고요 일단 회원가입은 하셨어요?</div>
                        <div class="faq_title"><span class=faq_title_content>Q.  저작권 명시를 해야하나요? </span><i class="fas fa-chevron-down"></i></div>
                        <div class="faq_content"> 어떻게 하냐면요 포인트를 넣고요 일단 회원가입은 하셨어요?</div>    
                </div>
            </div>
            <div class="faq_chat">
                <a id="chatClick" onclick="showUpChat();">
                    <img src="https://i.ibb.co/KLrtGnq/chat-Icon-1.png" alt="" style="width: 130px;" >
                </a>
            </div>
            <div id ="chatMessage">
                <div id="chatMsg">
                </div>
                <hr>
                <div id="chatPutMsg">
                    <form id="chatInputFrm" action="">
                        <textarea name="" id="textareaMsg" cols="30" rows="3">메세지를 입력하세요</textarea>
                        <input type="button" value="전송">
                    </form>
                </div>
            </div>        
        </section>
<script>
	$(document).ready(function() {
		  $(".faq_content").hide();
		  //content 클래스를 가진 div를 표시/숨김(토글)
		  $(".faq_title").click(function()
		  {
		    $(this).next(".faq_content").slideToggle(500);
		  });
		});

    function showUpChat() {
        const $chatMessage = $("#chatMessage");
        
        if($chatMessage.css("display")=="none"){
            console.log(($chatMessage.css("display"))=="none");
            $chatMessage.show();
        }
        else {
            console.log("닫혀랏엽")
            // $chatMessage.css("display", "none");
            $chatMessage.hide();
        }    
    }
</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>