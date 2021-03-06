<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.question.model.vo.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
List<Question> questionList = (List<Question>)request.getAttribute("questionList");
List<Question> questionListGroupBy = (List<Question>)request.getAttribute("questionListGroupBy");
List<Member> memberList = (List<Member>)request.getAttribute("memberList");
List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
List<String> questionerList = (List<String>)request.getAttribute("questionerList");

%>
<section class="section-space-padding">
	<div id="qnaContainer">
        <div id="snb">
            <section class="qnaListArray">
                <ul class="qnaListHeader">
                    <li class="qnaListHeaderLi">
                        <button class="btnArrayByDate" type="button" value="날짜순으로 정렬, 아이콘 넣을 것">날짜순</button>
                    </li>
                    <li class="qnaListHeaderLi">
                        <button class="btnArrayByDate" type="button" value="답변안한것들 위로 정렬 혹은 답변 안한 질문만 나오게">안읽음</button>
                    </li>
                </ul>
            </section>
            <section class="qnaListArea">
                <div class="qnaListWrap">
                    <ul class="qnaList">
<%
for(Question que : questionListGroupBy){
	for(Member member : memberList){
		if(que.getqQuestioner().equals(member.getMemberNo())){
			for(Attachment att : attachmentList){
				if(att.getMemberNo().equals(que.getqQuestioner())){
				
%>
                        <li class="msg-div" id="btn-<%=que.getqQuestioner()%>">
                            <a class="qnaListLink" >
                                <div class="infoArea" data-name="<%=que.getqQuestioner()%>">
                                    <div class="thumbnailWrap" aria-hidden="true" >
                                        <img class="qnaProfile" src="<%=request.getContextPath()%>/upload/profilephotos/<%=att.getRenamedFilename()%>">
                                    </div>
                                    <div class="textWrap">
                                        <div class="nameArea">
                                            <strong class="qnaName"><%=member.getMemberName() %></strong>
                                            <span class="dateArea"><%= que.getqDate() %></span>
                                        </div>
                                        <div class="textArea">
                                            <p class="qnaMessage"><%= que.getqContent() %></p>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
<% 		
					
				}
			}
			break;
		}
	}
}
%>                    
                      
                        
                    </ul>
                </div>
            </section>
        </div>
        
       <div class="qnaContent">
<%
for(Question que : questionListGroupBy){
	for(Member member : memberList){
		if(que.getqQuestioner().equals(member.getMemberNo())){
			for(Attachment att : attachmentList){
				if(att.getMemberNo().equals(que.getqQuestioner())){
%>
            <section class="qnaChatSection"id="<%= que.getqQuestioner()%>">
                <div class="qnaChatHeader">
                    <div class="qnaInfoArea">
                        <div class="thumbnailArea">
                            <div class="thumbnailWrap" aria-hidden="true">
                                <img class="qnaProfile" src="<%=request.getContextPath() %>/upload/profilephotos/<%=att.getRenamedFilename()%>">
                            </div>
                        </div>
                        <div class="textWrap">
                            <div class="nameArea">
                                <strong class="qnaName"><%= member.getMemberName() %></strong>
                            </div>
                            <div class="textArea">
<%
if("N".equals(que.getqIsAnswered())){
%>
                                <p class="qnaIsAnswered" style="color:red">답변미완료</p>

<%	
}else{
%>
 								<p class="qnaIsAnswered">답변완료</p>
<%	
}
%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="chatMsg qna-chat-msg" id="">
                    <ul class="question-balloon" style="visibility: visible;">
                        <li class="dateCheck">
                            <span>
                                <em>
                                    <strong>10. 24.</strong>
                                    
                                </em>
                            </span>
                        <div class="qnaMessage" id="message-from-<%= que.getqQuestioner()%>"></div>
                        </li>
<%
for(Question ques : questionList){
	if(ques.getqQuestioner().equals(que.getqQuestioner())){
%>
						<li class = <%=(!ques.getqWriter().equals(loginMember.getMemberNo()))? "left": "right"%>>
							<div class="sender">
								<%=(!ques.getqWriter().equals(loginMember.getMemberNo()))? member.getMemberName(): loginMember.getMemberId()%>
							</div>
							<div class="message">
								<%= ques.getqContent() %>
							</div>
							<div class="qtime"><%=ques.getqDate()%></div>
						</li>

<%
	}
}
%>                                
                 
             </ul>
         </div>
          <hr>
         <div class="chatPutMsg">
             <form class = "chatInputFrm" name="chatInputFrm" action="<%= request.getContextPath()%>/chat/chatInput">
                 <textarea name="qContent" class="textareaMsg" cols="30" rows="3">메세지를 입력하세요</textarea>
                 <input type="button" value="전송" class="chatInputBtn">
                 <input type="hidden" name="qWriter" value="<%=loginMember.getMemberNo() %>" />
             </form>
         </div>

            </section>
<%
				}
			}
		}
	}
}

%>        

        </div>
    </div>
</section>

<script>
$(".qnaChatSection").hide();
$(".infoArea").click((e)=>{
	const target = $(e.target).data("name");
	const index = $(e.target).parent().parent().index();
	$(".qnaChatSection").hide();
	$("#"+target).show();
	$('.qna-chat-msg').eq(index).scrollTop($('.qna-chat-msg').eq(index).prop('scrollHeight'));
})

</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>
