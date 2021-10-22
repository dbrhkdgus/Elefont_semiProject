<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String memberEmail = request.getParameter("memberEmail");
    boolean available = (boolean) request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 중복 검사</title>
<script src="<%= request.getContextPath() %>/js/jquery.min.js"></script>
<style>
.container {
    text-align: center;
    padding-top: 50px;
}
span {
    color: navy;
}
span#duplicate {
    color: red;
}
</style>
</head>
<body>
    <div class="container">
<% if(available) { %>
    <%-- 이메일이 사용가능한 경우 --%>
    [ <span><%= memberEmail %></span> ]는 사용가능합니다.
    <br /><br />
    <button onclick="popupClose();">닫기</button>

<% } else { %>
    <%-- 아이디가 이미 사용중인 경우 --%>
    [ <span id="duplicate"><%= memberEmail %></span> ]는 이미 사용중입니다.
    <br /><br />
    <form name="checkIdDuplicateFrm" action="<%= request.getContextPath() %>/member/checkIdDuplicate" method="POST">
        <input type="text" name="memberEmail" />
        <button type="submit">중복확인</button>
    </form>
<% } %>
    </div>
    <script>
    const popupClose = () => {
        // 부모창 opener 의 #_memberId value 설정
        $("#_email", opener.document).val("<%= memberEmail %>");
        
        // 부모창의 #idValid value 1로 설정
        $("#emailValid", opener.document).val("1");
        
        // popup 닫기
        close();
    };
    </script>
</body>
</html>