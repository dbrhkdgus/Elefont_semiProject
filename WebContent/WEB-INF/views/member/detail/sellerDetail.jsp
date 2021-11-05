<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Font> approvalList = (List<Font>)request.getAttribute("approvalList");
List<Font> checkedList = (List<Font>)request.getAttribute("checkedList");
List<Font> auditList = (List<Font>)request.getAttribute("auditList");
Attachment profile = (Attachment) request.getAttribute("profile");
%>
        <div class="member-container">
         <div class="member-profile">
            <div id="my-profile-img">
		      	<img id="my-profile-pic" src="<%= request.getContextPath()%>/upload/profilephotos/<%=profile.getRenamedFilename()%>" alt="" />
		     </div>
            <h3><%=loginMember.getMemberId() %></h3>
            <button id="btn-member-Info-Edit">설정</button>
            <hr>
            <div class="member-detail">
              <div>
                    <span>폰트 등록</span><br />
                    <div class="font-audit"><%=checkedList.size() %></div>
                </div>
                <div>
                    <span>심사중</span><br />
                    <div class="font-audit"><%=auditList.size() %></div>
                </div>
                <a href="<%=request.getContextPath()%>/seller/fontAudit">
                    <span>심사 완료</span><br />
                    <div class="font-audit"><%=approvalList.size() %></div>
                </a>
                 </div>
         </div>
         <div class="member-log">
            <div class="member-title">
              <span><%=loginMember.getMemberId() %></span>님, 안녕하세요!
                <button id="member-font" onclick="location.href='<%=request.getContextPath()%>/seller/fontEnroll'">폰트 등록</button>
                 </div>
            	<div class="data-graphs">
				<div>
					<h4>내 폰트 판매 현황</h4>
					<div class="data-bars">
<% for(Font fontC : checkedList){
%>
						<div class="data-name">
							<%=fontC.getFontName() %>
						</div>	
						<div class="data-bar">
							<div data-width="<%= (int)fontC.getFontPurchasedCount()>0?(int)fontC.getFontPurchasedCount()%25*4+10:10 %>"><span><%= fontC.getFontPurchasedCount() %></span></div>
						</div>
<%	
}
%>
			
					</div>
				</div>
				<div>
					<h4>내 폰트 좋아요 현황</h4>
					<div class="data-bars">
<% for(Font fontC : checkedList){ %>					
						<div class="data-name">
							<%=fontC.getFontName() %>
						</div>	
						<div class="data-bar">
							<div data-width="<%= (int)fontC.getFontLikeCount()>0?(int)fontC.getFontLikeCount()%25*4+10:10 %>"><span><%= fontC.getFontLikeCount() %></span></div>
						</div>
<% } %>						
					</div>
					
				</div>
			</div>
		</div>


     </div>
     
<script>
$(window).on('load', ()=>{
	
		$(".data-bar>div").each(function(index, item){
			let $target = $(item);
			let $data = $target.data('width');
			
			$target.css('width', $data + '%');
		});

	
});


</script>       