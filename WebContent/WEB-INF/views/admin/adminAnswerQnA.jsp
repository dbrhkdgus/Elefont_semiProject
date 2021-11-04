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
                        <li class="">
                            <a class="qnaListLink" href="#" onclick="">
                                <div class="infoArea">
                                    <div class="thumbnailWrap" aria-hidden="true">
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
            <section class="qnaChatSection">
                <div class="qnaChatHeader">
                    <div class="qnaInfoArea">
                        <div class="thumbnailArea">
                            <div class="thumbnailWrap" aria-hidden="true">
                                <img class="qnaProfile" src="https://image-notepet.akamaized.net/resize/620x-/seimage/20190104%2F4ceebf301cf61479f62d88990f863b8d.jpg" alt="https://image-notepet.akamaized.net/resize/620x-/seimage/20190104%2F4ceebf301cf61479f62d88990f863b8d.jpg">
                            </div>
                        </div>
                        <div class="textWrap">
                            <div class="nameArea">
                                <strong class="qnaName">Bazzyung</strong>
                            </div>
                            <div class="textArea">
                                <p class="qnaIsAnswered">답변완료</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="qnaChatArea">
                    <ul class="groupQnaMessageBallon" style="visibility: visible;">
                        <li class="dateCheck">
                            <span>
                                <em>
                                    <strong>10. 24.</strong>
                                    "(일)"
                                </em>
                            </span>
                        </li>
                        <li class="newMessageBallonArea">
                            <div class="messageBalloon">
                                <div class="qnaMessage">
                                    <p class="qnaMessage fromAdmin">
                                        "무엇이 궁금하세요?"
                                        <br>
                                        "문의사항을 남겨주시면 확인 후 답변드리겠습니다."
                                    </p>
                                </div>
                            </div>
                            <div class="qnaMessageTime">
                                <span class="_time">
                                    <em>오후</em>
                                    <span>05:33</span>
                                </span>
                            </div>
                        </li>
                        <li class="newMessageBallonArea">
                            <div class="messageBalloon">
                                <div class="qnaMessage">
                                    <p class="qnaMessage fromMember">
                                        판매자 문의
                                    </p>
                                </div>
                            </div>
                            <div class="qnaMessageTime">
                                <span class="_time">
                                    <em>오후</em>
                                    <span>05:33</span>
                                </span>
                            </div>
                        </li>
                    </ul>
                </div>
            </section>
            <hr>
            <div class="chatWrite">
                <form action="" id="qnaInputFrm">
                    <textarea name="" id="qnaInput" cols="100" rows="3" placeholder="메시지를 입력하세요."></textarea>
                    <input type="button" value="전송">
                </form>
            </div>
        </div>
    </div>
</section>



<%@ include file = "/WEB-INF/views/common/footer.jsp" %>
