<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
 <%
	List<Community> communityList = (List<Community>)request.getAttribute("communityList");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	List<String> commLikeList = (List<String>) request.getAttribute("commLikeList");
	System.out.println("commlike@jsp"+ commLikeList );
	System.out.println("commlike@jsp"+ communityList );
%>
  
     <!-- Portfolio Start -->
    <section id="comm-likelist" class="comm-likelist section-space-padding">
        <div class="container">
         

            <div class="commlist-inner">
                <div class="test-shop-box">

                    <div class="commlist-tools">
                        <div class="tools commlist-change">
                            <table>
                                <tr>
                                    <th rowspan="2"><i class="fas fa-user"></i></th>
                                    <th>유저 아이디</th>
                                    <th colspan="2">좋아요목록</th>
                                </tr>
                                <tr>
                                    <td>좋아요 카운트 수</td>
                                    <td><button id="member-font">Font</button></td>
                                    <td><button id="member-comm">Community</button></td>
                                </tr>
                            </table>
                            
                        </div>
                        <div class="tools comm-like-search">
                            <select name="comm-sort" id="comm-sort">
                                <option value="title" selected>제목으로 검색</option>
                                <option value="writer">작성자로 검색</option>
                                <option value="content">내용으로 검색</option>
                            </select>
                        </div>
                        <div class="tools comm-finder">
                            <input type="text" name="comm-search" id="comm-search" placeholder="커뮤니티 검색">
                            <button><i class="fas fa-search"></i></button>
                            
                        </div>
                    </div>
                    

<%
		
		String attachFilename = "";
		String memberNo = "";
		String commNo = "";
		
		
		
		for(Community comm : commLikeList){
			for(Attachment att : attachmentList){
				
				if(att.getCommNo().equals(comm.getCommNo())){
					attachFilename = att.getRenamedFilename();
					memberNo = att.getMemberNo();
					commNo = att.getCommNo();
				}
			}
%>
                    <div class="comm-like-list">
                        <div class="like-comm">
                            <a href=""><i class="fas fa-user"></i><div class="like-comm-writer"> user id </div></a>
                            <div class="comm-img"></div>
                            <div class="like-comm-buttons"> 
                           
                                <i class="fas fa-heart"></i>
                                <i class="fas fa-search-plus"></i>
                             </div>
                             <div class="like-comm-content">
                                <span>제목 테스트</span>
                             </div>
                        </div> 
                    </div>
                    
<%
		}
	
%>                  
                        
                       
                       


                </div>
            </div>
        </div>
    </section>

    <!-- Portfolio End -->
<script>
$("#member-font").click((e)=>{
	console.log("클릭");
	location.href = "<%=request.getContextPath()%>/member/fontLikeList"
});
</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>