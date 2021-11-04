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
                <a id="chatClick">
                    <img src="https://i.ibb.co/KLrtGnq/chat-Icon-1.png" alt="" style="width: 130px;" >
                </a>
            </div>
<%
if(loginMember != null){
%>
            <div id ="chatMessage">
                <div id="chatMsg">
                	<ul class="question-balloon" id="que-balloon">
<%
	if(!questionList.isEmpty()){
		for(Question q : questionList){
%>
						<li class = <%=(!q.getqWriter().equals(loginMember.getMemberNo()))? "left": "right"%>>
							<div class="sender">
								<%=(!q.getqWriter().equals(loginMember.getMemberNo()))? "Elefont": loginMember.getMemberId()%>
							</div>
							<div class="message">
								<%= q.getqContent() %>
							</div>
							<div class="qtime"><%=q.getqDate()%></div>
						</li>
<%
		}
%>
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


<script>
$(chatInputBtn).click((e)=>{
	<%-- const receiver = "<%= loginMember.getMemberNo()%>"; --%>
	/* if(!receiver) return; */

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
					sender: data["qWriter"],
					msg : data["qContent"],
					receiver: data["qQuestioner"],
					time : data["qDate"]
				};
			ws.send(JSON.stringify(msg));
			$(textareaMsg).val("").focus();
		},
		error:console.log			
	});
	
	
});

const appendMsg = (leftRight, name, content, time) =>{
	const $msgdiv = $(`<li>
	<div class="sender"></div>
	<div class="message"></div>
	<div class="qtime"></div></li>`);
	
	$msgdiv.addClass(leftRight);
	$msgdiv.find('.sender').text(name);
	$msgdiv.find('.message').text(content);
	$msgdiv.find('.qtime').text(time);
	console.log($msgdiv);
	
	return $msgdiv;
	
};
</script>     
<%
	}
}
%>
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

    $(chatClick).click((e)=>{
        const $chatMessage = $("#chatMessage");
<%
if(loginMember == null){
%>        
        	alert("로그인 시에만 사용 가능합니다.");
        	return;
<%
}
%>        
        
        if($chatMessage.css("display")=="none"){
            
            $chatMessage.show();
        }
        else {
            
            // $chatMessage.css("display", "none");
            $chatMessage.hide();
        }    
    	
    });
    
    
    

</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>