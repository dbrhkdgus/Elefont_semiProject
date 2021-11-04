<%@page import="com.kh.elefont.order.model.vo.Order"%>
<%@page import="com.kh.elefont.font.model.vo.FontCopyright"%>
<%@page import="com.kh.elefont.rep.model.vo.Rep"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%@include file = "/css/fontApply.jsp" %>

<% 
Font font = (Font)request.getAttribute("font"); 
FontCopyright fontCopyright = (FontCopyright)request.getAttribute("fontCopyright");
List<Community> communityList = (List<Community>)request.getAttribute("communityList");
List<Attachment> commAttachmentList = (List<Attachment>)request.getAttribute("commAttachmentList");
List<Rep> repList = (List<Rep>)request.getAttribute("repList");
List<Order> orderList = (List<Order>) request.getAttribute("orderList");
List<Attachment> profileAttachList = (List<Attachment>) request.getAttribute("profileAttachList");
int memberLikeValid = (int)request.getAttribute("likeValid");
%>

 <section id="portfolio" class="portfolio section-space-padding" >
    <div class="shop-detail">
       <div class= "shop-detail-top">
           <div class="shop-detail-font-name"><h2><%= font.getFontName() %></h2></div>
           <div class="shop-detail-buttons">
           
	           	<form action="<%= request.getContextPath() %>/font/fontOrder" class="shop-detail-icon" method="GET" name="PurchaseCartFrm">  
	           		<span class="sd-buttton-content">구매</span><i class="fas fa-wallet" id="purchase-button" name="button" font-size="30px" ></i>           
               	<!-- <input id="purchase-button" name="button" type="button" value="구매"> -->
               		<span class="sd-buttton-content">장바구니</span><i class="fas fa-cart-plus" id="cart-button"  data-font-no="<%= font.getFontNo() %>" data-type="cart" name="button"></i>             
<%if(loginMember != null && memberLikeValid == 1){ %>                        	
                    <span class="sd-buttton-content">좋아요 수</span><i class="fas fa-heart" data-font-no="<%=font.getFontNo()%>" id="sd-like-button"><span><%=font.getFontLikeCount() %></span> </i>
<% } else{ %>          
					<span class="sd-buttton-content">좋아요 수</span><i class="far fa-heart" data-font-no="<%=font.getFontNo()%>" id="sd-like-button"><span><%=font.getFontLikeCount() %></span> </i>
<%
} 
%>              
                    <input type="hidden" name="PerCartType" value = "" />
                    <input type="hidden" name="font-no" value="<%=font.getFontNo()%>"/>
                    <input type="hidden" name="font-name" value="<%=font.getFontName()%>"/>
                    <input type="hidden" name="font-price" value="<%=font.getFontPrice()%>"/>
<% if(loginMember != null){ %>                        	
                    <input type="hidden" name="email" value="<%=loginMember.getMemberEmail()%>"/>
                    <input type="hidden" name="member_no" value="<%= loginMember.getMemberNo() %>" />
<%} %>    
                    <input type="hidden" name="cart_no" value="" />
                </form>
                    
			</div>
		</div>

            <div class="shop-detail-middle">
                <div class="sd-font-previewer">
                    <h4  id="font-previewer-title">폰트미리보기</h4 >
                    <hr class="liner">
                    <textarea id="shop-detail-textarea" name="shop-detail-previewer" rows="5" cols="60" style="font-family: '<%= font.getFontFamily() %>';"></textarea>
                </div>    
                
                <div class="shop-detail-reply">
                    <h4  id="shop-detail-rep">댓글</h4>
                    <hr class="liner">
                    
                    <div class="shop-detail-reblybox">
<%
if(loginMember!=null){
%>

	                      <form action="<%=request.getContextPath()%>/rep/ShopRepEnroll" method="POST" >
	                          <input type="text" class="detail-inputbox" name="reply-input" placeholder="댓글을 입력하세요" >
	                          <input type="hidden" name="font-no" value="<%=font.getFontNo()%>"/>
	                          <input type="hidden" name="rep-writer" value="<%=loginMember.getMemberName()%>"/>
	                          <input type="hidden" name="member_no" value="<%= loginMember.getMemberNo() %>" />
	                          <input type="hidden" name="rep-ref" value="0" />
	                          <input type="hidden" name="rep-level" value="1" />
	                          <input type="submit" value="등록"/>
	                      </form>
<%	
}

for(Rep rep : repList){
	int repNo = 0;
	String renamedProfile = "";
	for(Attachment a : profileAttachList){
	String memberNo = a.getMemberNo();
	if(memberNo.equals(rep.getMemberNo())){
		renamedProfile = a.getRenamedFilename();
		break;
		}
	}
	if(rep.getRepLevel()==1){
		repNo = rep.getRepNo();
		
%>

 
 
						<form action="<%= request.getContextPath() %>/rep/DeleteUpdateRep" method="POST" name="DeleteUpdateRepFrm">
							<div class="reply-outer-box">
                   				<div class="reply-box">
                       				<div class="font-rep-profile"><img src="<%=request.getContextPath() %>/upload/profilephotos/<%=renamedProfile%>"  id="user-profile"></div>
                      				<div class="reply-writer-content"><span><%=rep.getRepWriter()%> : </span><span><%=rep.getRepContent()%></span></div>

                          		</div> 
                           		<i class="fab fa-replyd" style="font-size:35px; color: #005A3C; "></i>
<%
		if(loginMember != null &&(loginMember.getMemberNo().equals(rep.getMemberNo()) || "A".equals(loginMember.getMemberRole()))){
		
	
%>
                               	<input type="button" value="수정" class="btn-rep-transFrm" click="transFrm"/>
                               	<input type="button" value="등록" class="btn-rep-update"/>
                               	<input type="button" value="삭제" class="btn-rep-delete"/>
                               	<input type="hidden" name="type" value=""/>
                               	<input type="hidden" name="rep_no" value="<%= rep.getRepNo() %>"/>
                                

                                
                                

<% 		} %>

                            </div><!-- 혜진추가 -->
                       </form>
                    
   								
                             
<%
	}else{
	
%>                               
						<form action="<%= request.getContextPath() %>/rep/DeleteUpdateRep" method="POST" name="DeleteUpdateReRepFrm">
                           <div class="re-reply-box">
                           		<img src="https://i.ibb.co/chkD19T/image.png" alt="" />
                         	 	<div class="font-rep-profile"><img src="<%=request.getContextPath() %>/upload/profilephotos/<%=renamedProfile%>"  id="user-profile"></div>
                          		<div class="re-reply-writer-content"><span><%=rep.getRepWriter()%> : </span><span><%=rep.getRepContent()%></span></div>
<%
	if(loginMember != null && (loginMember.getMemberNo().equals(rep.getMemberNo()) || "A".equals(loginMember.getMemberRole()))){
		
	
%>
                                <input type="button" value="수정" class="btn-re-rep-transFrm"/>
                                <input type="button" value="등록" class="btn-re-rep-update"/>
                                <input type="button" value="삭제" class="btn-re-rep-delete"/>
                                <input type="hidden" name="type" value="" /> 
                                <input type="hidden" name="rep_no" value="<%= rep.getRepNo() %>" />
                             
<% } %>
                            
                           </div> 
                        </form>
<%
		
	}
	
	if(loginMember!=null){
%>       
                       <form action="<%=request.getContextPath()%>/rep/ShopRepEnroll"  name="reReplyFrm"class="re-rep-inputbox" method="POST" >                         
	                       <input type="text" class ="detail-inputbox re-rep-detail-inputbox"  name="reply-input" placeholder="댓글을 입력하세요" >
	                       <input type="hidden" name="font-no" value="<%=font.getFontNo()%>"/>
	                       <input type="hidden" name="rep-writer" value="<%=loginMember.getMemberName()%>"/>
							<input type="hidden" name="member_no" value="<%= loginMember.getMemberNo() %>" />
	                       <input type="hidden" name="rep-level" value="2" />
	                       <input type="hidden" name="rep-ref" value="<%= rep.getRepRef() == 0 ? rep.getRepNo() : rep.getRepRef() %>" />
                                
                           <input type="submit" value="등록"/>
                      </form>
                            
                      <form action="<%= request.getContextPath() %>/rep/DeleteUpdateRep" method="POST" name="DeleteUpdateReRepFrm2">
   							<input type="hidden" name="type" value="" /> 
                            <input type="hidden" name="rep_no" value="<%=rep.getRepNo() %>" />
                                	
						</form>
<%
}
%>                            


<%
	}
if(loginMember != null){
%>

                            <script>
                        	/* 숨김처리관련 스크립트 */
                      		  $(document.reReplyFrm).hide();
                      		  
                      		 $('.fa-replyd').on('click', (e)=>
                             {
                               console.log("click");
                               /* $(e.target).parent().parent().next().slideToggle(500); */                                 
                               $(e.target).parent().parent().next().slideToggle(500);                                 
                             });
                           /*  $(document.reReplyFrm).hide();
                            $('#btn-re-icon').click((e)=>{
                            	console.log(e.target)
                                $(e.target).show();	
                            }); */
                            </script>
<% }else { %>
							<script>
  							$(document.reReplyFrm).hide();
                            
                               $('.fa-replyd').on('click', (e)=>
                             {
                            	alert("댓글등록은 로그인 이후 가능합니다.");
                            	$('.loginBox').show();
                            	$(loginId).select();
                             });
                             </script>
<% } %>
								<script>
								/* 공통스크립트 시작 */
								/* 댓글 스크립트 */
                                	$(".btn-rep-update").hide();
                                	$(".btn-rep-update").click((e)=>{
                                		$("input[name=type]").val("update");
                                		
                                		$(e.target).parent().parent().submit();
                                	});
                                	$('.btn-rep-transFrm').off('click').on('click', (e)=>{
                                		$(e.target).hide();
                                		$(e.target).next().show();
                                		var oldContent = $(e.target).parent().children().children("div").children(":nth-child(2)").text();
                                		$(e.target).parent().children().children("div").children(":nth-child(2)").html('');
                                		$(e.target).parent().children().children("div").children(":nth-child(2)").html(`<input type="text" name="update_rep_content" value = "\${oldContent}" />`);
                                	});
                                	
                                	$(".btn-rep-delete").click((e)=>{
                                		if(confirm("정말 삭제하시겠습니까?")){
                                			
                                		$("input[name=type]").val("delete");
                                		$(e.target).parent().parent().next().next().submit(); 
                                		}
                                	});
                                
								/* 대댓글 스크립트 */
                                	$(".btn-re-rep-update").hide();
                                	
                                	$(".btn-re-rep-update").click((e)=>{
                                		$("input[name=type]").val("update");                      
                                	
                                		$(e.target).parent().parent().submit(); 
                                	});
                                	
                                	$(".btn-re-rep-transFrm").click((e)=>{
                                		$(e.target).hide();
                                		$(e.target).next().show();
                                		var oldContent = $(e.target).parent().children("div").children().next().text();
                                		$(e.target).parent().children("div").children().next().html('');
                                		$(e.target).parent().children("div").children().next().html(`<input type="text" name="update_rep_content" value = "\${oldContent}" />`);
                                		
                                		
                                		
                                	});
                                	
                                	$(".btn-re-rep-delete").click((e)=>{
                                		if(confirm("정말 삭제하시겠습니까?")){
                                			
                                		$("input[name=type]").val("delete");
                                		$(e.target).parent().parent().next().next().submit();
                                		}
                                	});
                                	
                                	/* 구매하기 버튼 클릭 */
                                	$("#purchase-button").click((e)=>{
                                		<% 
                                		if(loginMember == null){
                                		%>
                                		alert("로그인 후 이용이 가능합니다.");
                                		$('.loginBox').show();
                                    	$(loginId).select();
                                		<%
                                		}else{
                                			for(Order order : orderList){ 
	                                			if(font.getFontNo().equals(order.getFontNo())){
	                                	%>
	                                	
	                                			alert("이미 구매한 폰트입니다.");
	                                			return false;
	                                	
	                                	<%	
	                                				break;
	                                			}
                                			}
                                			
                                		%>
                                		
                                		$("input[name=PerCartType]").val("purchase")
                                		$(document.PurchaseCartFrm).submit();
                                		
                                		<%
                                		}
	                                	%>
                                		
                                	});
                                	
                                	/*좋아요 버튼 클릭*/
                                	$("#sd-like-button").click((e)=>{
<%
	if(loginMember == null){			
%>
									alert("로그인 후 이용 가능합니다.");
									$('.loginBox').show();
	                            	$(loginId).select();
									return;
<%
} else if("A".equals(loginMember.getMemberRole())){
	
%>
									alert("일반 회원만 사용 가능한 기능입니다.");
									return;
<%
}
%>
									let $target = $(e.target);
									let $fontNo = $target.data("fontNo");
									
									$.ajax({
										url: "<%=request.getContextPath()%>/font/fontLike",
										dataType:"json",
										type:"GET",
										data: {'fontNo' : $fontNo},
										success(data){
											console.log(data);
											const likeValid = data["likeValid"];
											const likeCnt = data["likeCnt"];
											console.log("likeValid@jsp = " +likeValid);
											console.log("likeCnt@jsp = "+likeCnt);
											
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
										error:console.log
									});
                               	});
                                	
                                	/* 장바구니 추가 버튼 클릭 */
                                	$("#cart-button").click((e)=>{
                                		<% 
                                		if(loginMember == null){
                                		%>
                                		alert("로그인 후 이용이 가능합니다.");
                                		$('.loginBox').show();
                                    	$(loginId).select();
                                		
                                		<%}else{ 
                                			for(Order order : orderList){ 
	                                			if(font.getFontNo().equals(order.getFontNo())){
	                                	%>
	                                	
	                                			alert("이미 구매한 폰트입니다.");
	                                			return false;
	                                	
	                                	<%	
	                                				break;
	                                			}
                                			}
                                			
                                		%>
                                		
                                		
                                		$("input[name=PerCartType]").val("cart");
                                		$fontNo = $(e.target).data("fontNo");
                                		$PerCartType = $(e.target).data("type");                               		
                                		$.ajax({
                                			url: "<%=request.getContextPath()%>/member/memberCart",
                                			dataType: "json",
                                			type:"POST",
                                			data: {'fontNo' : $fontNo, 'PerCartType' : $PerCartType},
                                			success(data){
                                				console.log(data);
                                				const insertCart = data["insertCart"];
                                				if(insertCart == 1){
	                                				alert("장바구니 등록 성공!");                               					
                                				}else{
                                					alert("장바구니에 이미 등록되어 있습니다.")
                                				}
                                				
                                			},
                                			error: console.log
                                		});
                                		<%
                                		}
	                                	%>
                                		
                                	});
                                	
                                	
                                </script>
                            </div>

                    </div>
                </div>
                
					<div class="sd-review-section">
                        <h4 id="shop-detail-review">폰트후기</h4>
<%
	if(communityList.isEmpty()){
%>
						<hr class="liner">
                        <div class="sd-review">
                            <img src="https://i.ibb.co/DzynHD7/image.png" id="sd-review-img" >   
                            <div class="sd-review-box">
                                <h2>아직 등록된 후기가 없어요!</h2>
                                <span id="sd-review-text"><%= font.getFontName() %>를 이용해 멋진 후기를 남겨보세요!</span>  
                            </div>
                        </div>

<%		
	}else{
		
	
	String filename = "";
	for(Community comm : communityList){
		for(Attachment att : commAttachmentList){
			if(att.getCommNo().equals(comm.getCommNo())){
				filename = att.getRenamedFilename();
			}
		}
%>
                        <hr class="liner">
                        <a href="<%=request.getContextPath()%>/community/pictureDetail?commNo=<%=comm.getCommNo()%>"> <div class="sd-review">
                            <img src="<%=request.getContextPath() %>/upload/community/<%= filename %>"  id="sd-review-img" >   
                            <div class="sd-review-box">
                                <h2><%= comm.getCommTitle() %></h2>
                                <span id="sd-review-text"><%= comm.getCommContent() %></span>  
                            </div>
                        </div></a>


<%
	}
}
%>
                    </div>
                    
             
                    <div class="sd-copyright-section">
                        <h4 id="sd-copyright-title">저작권 정보</h4>
                        <hr class="liner">
                        <p id="sd-copyright-content">
                        	본<mark> <%= font.getFontName() %></mark>의 저작권은 <mark><%= fontCopyright.getFontPublisher() %></mark>에 있으며, <mark><%= fontCopyright.getFontDesigner() %></mark>에 의해 만들어졌습니다. 
                        	<br>저작권 및 자세한 문의사항은 <mark><a href="<%=fontCopyright.getFontRootUrl() %>"> <%=fontCopyright.getFontRootUrl() %></a></mark>에 문의하시길 바랍니다.
                        </p>
                      
                    </div>        
                </div>

            </div>


         </section>


<%@ include file = "/WEB-INF/views/common/footer.jsp" %>