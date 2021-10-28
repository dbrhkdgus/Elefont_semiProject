<%@page import="com.kh.elefont.rep.model.vo.Rep"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<% 
Font font = (Font)request.getAttribute("font"); 
List<Community> communityList = (List<Community>)request.getAttribute("communityList");
List<Attachment> commAttachmentList = (List<Attachment>)request.getAttribute("commAttachmentList");
List<Rep> repList = (List<Rep>)request.getAttribute("repList");
System.out.println("repList@jsp : " + repList );
%>

 <section id="portfolio" class="portfolio section-space-padding">
           <div class="shop-detail">
                <div class= "shop-detail-top">
                    <div class="shop-detail-font-name"><h2><%= font.getFontName() %></h2></div>
                    <div class="shop-detail-buttons">
                    
                        <button id="purchase-button" name="button" type="button" onclick="location.href='<%= request.getContextPath() %>/member/memberCart';" >구매</button>
                        <button id="cart-button" name="button" type="button">장바구니</button>
                        <button id="like-button" name="button" type="button">좋아요</button>
                    </div>
                </div>

                <div class="shop-detail-middle">
                    <div class="sd-font-previewer">
                        <h4  id="font-previewer-title">폰트미리보기</h4 >
                        <hr class="liner">
                        <textarea id="shop-detail-textarea" name="shop-detail-previewer" rows="5" cols="60"></textarea>
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
                                <input type="hidden" name="rep-ref" value="0" />
                                <input type="hidden" name="rep-level" value="1" />
                                <input type="submit" value="등록"/>
                            </form>
<%	
} 
	int repno = 0;
			
for(Rep rep : repList){
		repno = rep.getRepNo();
	if(rep.getRepLevel()==1){
%>

 
 
   
								<div class="reply-outer-box">
                                <div class="reply-box">
                                	<img src="https://cdn1.vectorstock.com/i/1000x1000/10/05/user-icon-vector-22391005.jpg"  id="user-profile">
                                		<span><%=rep.getRepWriter()%> : <%=rep.getRepContent()%></span>
                                </div> 
                                <i class="fab fa-replyd" style="font-size:35px; color: #005A3C; "></i>
                                </div>
<%
	}
	if(rep.getRepLevel()==2 && repno == rep.getRepRef()){
	
%>                                
                                 <div class="re-reply-box">
                                	<img src="https://cdn1.vectorstock.com/i/1000x1000/10/05/user-icon-vector-22391005.jpg"  id="user-profile">
                                		<span><%=rep.getRepWriter()%> : <%=rep.getRepContent()%></span>
                                </div> 
<%
	}
	
	if(loginMember!=null){
%>       
                                <form action="<%=request.getContextPath()%>/rep/ShopRepEnroll"  name="reReplyFrm"class="re-rep-inputbox" method="POST" >                         
                                <input type="text" class ="detail-inputbox" id="re-rep-detail-inputbox"  name="reply-input" placeholder="댓글을 입력하세요" >
                                <input type="hidden" name="font-no" value="<%=font.getFontNo()%>"/>
                                <input type="hidden" name="rep-writer" value="<%=loginMember.getMemberName()%>"/>
                                <input type="hidden" name="rep-level" value="2" />
                                <input type="hidden" name="rep-ref" value="<%=rep.getRepNo() %>" />
                                
                                <input type="submit" value="등록"/>
                            </form>
<%
}
%>                            
                            <script>
                        	
                      		  $(document.reReplyFrm).hide();
                      		  //content 클래스를 가진 div를 표시/숨김(토글)
                      		  $('.fa-replyd').click(function(e)
                      		  {
                      			console.log("click");
                      		    $(e.target).parent().next().slideToggle(500);                     		    
                      		  });
                     

                           /*  $(document.reReplyFrm).hide();
                            $('#btn-re-icon').click((e)=>{
                            	console.log(e.target)
                                $(e.target).show();	
                            }); */
                            </script>


<%
	}

	
%>



                            
                        
                        
                        
                            </div>

                    </div>
                </div>
                
					<div class="sd-review-section">
                        <h4 id="shop-detail-review">폰트후기</h4>
<%
	if(communityList.isEmpty()){
%>
						<hr class="liner">
                        <a href=""> <div class="sd-review">
                            <img src="https://i.ibb.co/qR84ghW/embarrassed.png"  id="sd-review-img" >   
                            <div class="sd-review-box">
                                <h2>아직 등록된 후기가 없어요!</h2>
                                <span id="sd-review-text"><%= font.getFontName() %>를 이용해 멋진 후기를 남겨보세요!</span>  
                            </div>
                        </div></a>

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
                        <a href=""> <div class="sd-review">
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
                        <p id="sd-copyright-content">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ducimus totam tempora autem. Totam nemo, distinctio ipsam culpa vel excepturi fugit enim veritatis vitae tempore! Sed architecto voluptatem recusandae facilis dolorum.</p>
                        
                    </div>        
                
                
                
                
                </div>


            </div>








         </section>


<%@ include file = "/WEB-INF/views/common/footer.jsp" %>
