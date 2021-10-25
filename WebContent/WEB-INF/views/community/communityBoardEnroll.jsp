<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/common/LandingHeader.jsp"%>
    <!-- Community Board Enroll start -->
<section id="portfolio" class="portfolio section-space-padding">
    <div class="container">

        <script>
            /**
            * boardEnrollFrm 유효성 검사
            */
            function boardValidate(e){
                const $title = $("[name=title]");
                const $content = $("[name=content]");
                //제목을 작성하지 않은 경우 폼제출할 수 없음.
                if(!/^.+$/.test($title.val())){
                    alert("제목을 입력하세요.");
                    return false;
                }
                                   
                //내용을 작성하지 않은 경우 폼제출할 수 없음.
                // .(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
                if(!/^(.|\n)+$/.test($content.val())){
                    alert("내용을 입력하세요.");
                    return false;
                }
                return true;
            }
            
            $(() => {
                $(document.boardEnrollFrm).submit(boardValidate);
            });
            </script>
            
            <section id="board-container">
            <h2>커뮤니티 게시글 작성</h2>
            <form
                name="boardEnrollFrm"
                action="<%=request.getContextPath() %>/community/boardEnroll" 
                method="post"
                enctype="multipart/form-data" 
                >
                 
                <table id="tbl-board-view">
                <tr>
                    <th>제 목</th>
                    <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                        <input type="text" name="writer" value="<%= loginMember.getMemberName()%>" readonly/>
                    </td>
                </tr>
                <tr>
                	<th>사용된 폰트</th>
                	<td>
                		<input type="text" name="font" />
                	</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>			
                        <input type="file" name="upFile">
                    
                    </td>
                </tr>
                <tr>
                    <th>내 용</th>
                    <td><textarea rows="5" cols="40" name="content"></textarea></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit" value="등록하기">
                    </th>
                </tr>
            </table>
            </form>
            </section>



    </div>
</section>

<!-- Community Board Enroll end -->
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>