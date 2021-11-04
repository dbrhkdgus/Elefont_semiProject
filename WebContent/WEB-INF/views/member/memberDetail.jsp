<%@page import="com.kh.elefont.font.model.vo.FontCategory"%>
<%@page import="com.kh.elefont.order.model.vo.OrderExt"%>
<%@page import="com.kh.elefont.order.model.vo.Order"%>
<%@page import="com.kh.elefont.coupon.model.vo.Coupon"%>
<%@page import="com.kh.elefont.font.model.vo.FontExt"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

     <!-- Portfolio Start -->
<%
String memberRole = loginMember.getMemberRole();
%>
<section id="member-mypage" class="member-mypage section-space-padding">

<%
if("U".equals(memberRole)){
%>
	<%@include file = "/WEB-INF/views/member/detail/userDetail.jsp" %>
<%
}else if("S".equals(memberRole)){
%>
	<%@include file = "/WEB-INF/views/member/detail/sellerDetail.jsp" %>
<%
}else if("A".equals(memberRole)){
%>
	<%@include file = "/WEB-INF/views/member/detail/adminDetail.jsp" %>
<%
}
%>
<script>
$("#btn-member-Info-Edit").click((e)=>{
	location.href = "<%= request.getContextPath()%>/member/memberInfoEdit?memberId=<%=loginMember.getMemberId()%>&memberNo=<%=loginMember.getMemberNo()%>";
});
</script>
</section>
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>