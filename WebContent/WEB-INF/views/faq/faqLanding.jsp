<%@page import="com.kh.elefont.question.model.vo.Question"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.faq.model.vo.Faq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<% 
List<Faq> faqList = (List<Faq>)request.getAttribute("faqList");
List<Question> questionList = (List<Question>) request.getAttribute("questionList");

%>


        <section id="portfolio" class="portfolio section-space-padding">     
            <div id ="faqbox">
                <h1 id="faqH1">FAQ</h1>
                <div class="faq_container">
<%
for(Faq f : faqList) {
%>
                    <div class="faq_title"><span id=faq_title_content>Q.<%=f.getFaqTitle() %></span><i class="fas fa-chevron-down"></i></div>
                        <div class="faq_content"> <%=f.getFaqContent() %></div>
<%
}
%>
                </div>
            </div>
            <div class="faq_chat">
                <a id="chatClick" onclick="showUpChat();">
                    <img src="https://i.ibb.co/KLrtGnq/chat-Icon-1.png" alt="" style="width: 130px;" >
                </a>
            </div>
            <div id ="chatMessage">
                <div id="chatMsg">
                	<ul class="question-balloon">
<%
if(!questionList.isEmpty()){
	for(Question q : questionList){
		boolean isMember = loginMember.getMemberNo().equals(q.getqWriter());
%>
					<li class="new-balloon <%= isMember? "member":"admin"%>">
						<div><%= q.getqWriter() %></div>
						<div><%= q.getqContent() %></div>
					</li>
<%
	}
}
%>
					</ul>
                </div>
                <hr>
                <div id="chatPutMsg">
                    <form id="chatInputFrm" action="">
                        <textarea name="qContent" id="textareaMsg" cols="30" rows="3">메세지를 입력하세요</textarea>
                        <input type="button" value="전송" id="chatInputBtn">
                        <input type="hidden" name="qWriter" value="<%=loginMember.getMemberNo() %>" />
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
    
/* 메세지 전송 시 form 제출(ajax) */
$(chatInputBtn).click((e)=>{
	const frmData = new FormData(document.chatInputFrm);
	$.ajax({
		url: "<%=request.getContextPath()%>/question/questionEnroll",
		dataType: "json",
		type: "POST",
		processData: false,
		contentType: false,
		data: frmData,
		success(data){
			console.log(data);
		},
		error: console.log
	});
});

</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>