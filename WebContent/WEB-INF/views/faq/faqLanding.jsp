<%@page import="com.kh.elefont.question.model.vo.Question"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.faq.model.vo.Faq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<% 
List<Faq> faqList = (List<Faq>)request.getAttribute("faqList");

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
                	<ul class="question-balloon" id="que-balloon">
					</ul>
                </div>
                <hr>
                <div id="chatPutMsg">
                    <form id = "chatInputFrm" name="chatInputFrm" action="<%= request.getContextPath()%>/chat/chatInput">
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
            
            $chatMessage.show();
        }
        else {
            
            // $chatMessage.css("display", "none");
            $chatMessage.hide();
        }    
    }
    
    $(chatInputBtn).click((e)=>{
    	const receiver = "<%= loginMember.getMemberNo()%>";
		if(!receiver) return;

		const $frmData = $(document.chatInputFrm);
		$.ajax({
			url : "<%= request.getContextPath()%>/chat/chatInput",
			data : $frmData.serialize(),
			method : "post",
			dataType : "json",
			success(data) {
				console.log(data);

				const msg = {
						type: "que",
						sender: "<%= loginMember.getMemberNo()%>",
						msg : $(textareaMsg).val(),
						receiver: receiver,
						time : Date.now()
					};
					
				ws.send(JSON.stringify(msg));
				$(textareaMsg).val("").focus();
				
			},
			//유효하지 않은 쿠폰일 시 alert 띄우고 input 값 지우기
			error:console.log			
		});
		
   	
    });
    

</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>