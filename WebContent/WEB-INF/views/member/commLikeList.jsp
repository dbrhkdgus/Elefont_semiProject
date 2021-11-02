<%@page import="com.kh.elefont.like_cart.model.vo.CommLike"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
 <%
	List<Community> communityList = (List<Community>)request.getAttribute("communityList");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	List<CommLike> commLikeList = (List<CommLike>) request.getAttribute("commLikeList");
	List<Attachment> allAttachmentList = (List<Attachment>)request.getAttribute("allAttachmentList");
	
	
	System.out.println("commlike@jsp"+ commLikeList );
%>
  
     <!-- Portfolio Start -->
    <section id="comm-likelist" class="comm-likelist section-space-padding">
        <div class="container">
         

             <div class="test-shop-box">
    
                        <div class="likelist-tools">
                               
<%
String likeProfilepic ="";
	for(Attachment att : allAttachmentList ){
		if(att.getCommNo() == null && att.getFontNo() == null && att.getMemberNo().equals(loginMember.getMemberNo())){
			likeProfilepic = att.getRenamedFilename();
			System.out.println("likeProfilepic" + likeProfilepic);

%>                                
                                <div class="communitylike-profile-photo-box">
                                     <img src="<%= request.getContextPath()%>/upload/profilephotos/<%=likeProfilepic%>" alt="" />
                                     </div>
                                   <div class="likeList-profile">
                                     
                                   <p><%=loginMember.getMemberId() %>님 좋아요목록</p>
                                   
<%
	}
}
%>
                                
                                
                                        <div class="likeList-profile-button">
                                        <button id="member-font">Font</button><button id="member-comm">Community</button>
                                        </div>
                            
                        </div>
                    <!--     <div class="tools comm-like-search">
                            <select name="comm-sort" id="comm-sort">
                                <option value="title" selected>제목으로 검색</option>
                                <option value="writer">작성자로 검색</option>
                                <option value="content">내용으로 검색</option>
                            </select>
                        </div>
                        <div class="tools comm-finder">
                            <input type="text" name="comm-search" id="comm-search" placeholder="커뮤니티 검색">
                            <button><i class="fas fa-search"></i></button>
                            
                        </div> -->
                    </div>
                    <div class="comm-like-list">

<%
 		
		String attachFilename = "";
		String memberNo = "";
		String commNo = "";
		String commWriter ="";
		String commTitle ="";
		int commLikeCount =0;
		String profileAttachFilename = "";
		String writerNo ="";
		
		
			
		for(CommLike commLike : commLikeList){
			commNo=commLike.getCommNo();
			for(Community comm : communityList){
				if(commNo.equals(comm.getCommNo())){
				for(Attachment att : attachmentList){
					if(att.getCommNo().equals(comm.getCommNo())){
							commWriter=comm.getCommWriter();
							memberNo = att.getMemberNo();
							attachFilename = att.getRenamedFilename();
							commTitle=comm.getCommTitle();
							commLikeCount=comm.getCommLikeCount();
							writerNo = comm.getMemberNo();
							
								for(Attachment attpic : allAttachmentList)	
								if(attpic.getCommNo() == null && attpic.getFontNo() == null && attpic.getMemberNo().equals(writerNo)){
									profileAttachFilename = attpic.getRenamedFilename();
									System.out.println("profileAttachFilename@@@@@@@" + profileAttachFilename);
						}
					}
				}
			}
		}
		
%>
                  
                        <div class="like-comm">
		  				   	
                             <a href="<%= request.getContextPath()%>/community/writerDetail?commWriter=<%= memberNo  %>">
                            <div class="community-profile-photo-box">
								<img class="community-profile-photo" src="<%= request.getContextPath()%>/upload/profilephotos/<%=profileAttachFilename%>"> 
							</div>
                             <div class="like-comm-writer"> <%= commWriter %> </div></a>
                            <div class="comm-img">
                            <a href="<%= request.getContextPath()%>/community/pictureDetail?commNo=<%= commNo %>">
                            <img src="<%=request.getContextPath() %>/upload/community/<%=attachFilename %>" alt="" />
                            </a>
                            </div>
                           
                            <div class="like-comm-buttons"> 
<%
				
				if(loginMember != null && !commLikeList.isEmpty() && commLikeList.contains(commNo)){
%>
						<i class="far fa-heart" data-comm-no="<%=commNo%>"><span><%=commLikeCount %></span></i>
<%
				}else{
%>                                    
						<i class="fas fa-heart" data-comm-no="<%=commNo%>"><span><%=commLikeCount %></span></i>
<%
				}
%>                              
                                <i class="fas fa-search-plus"></i>
                             </div>
                             <div class="like-comm-content">
                                <span><%=commTitle %></span>
                             </div>
                        </div> 
                   
                    
<%
				
		}
	
%>                  
            </div>             
                       
                       


                </div>
            </div>
        </div>
    </section>

    <!-- Portfolio End -->
<script>

/* 좋아요 버튼 클릭시 사용자 좋아요 여부에 따른 버튼 이벤트 */
/* 좋아요 버튼 클릭시 사용자 좋아요 여부에 따른 버튼 이벤트 */
	$(".fa-heart").click((e)=>{
		

		let $target = $(e.target);
		let $commNo = $target.data("commNo");
		console.log($commNo);
		
		$.ajax({
			url: "<%= request.getContextPath()%>/community/commLike",
			dataType: "json",
			type: "GET",
			data: {'commNo' : $commNo},
			success(jsonStr){
				console.log(jsonStr);
				const likeValid = jsonStr["likeValid"];
				const likeCnt = jsonStr["likeCnt"];
				
				if(likeValid == 1){
					$target
						.removeClass("far")
						.addClass("fas");
				}else{
					$target
						.removeClass("fas")
						.addClass("far");
				}
				$target.html(`<span>\${likeCnt}</span>`);
			},
			error: console.log
		});
	});
$("#member-font").click((e)=>{
	console.log("클릭");
	location.href = "<%=request.getContextPath()%>/member/fontLikeList"
});
</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>