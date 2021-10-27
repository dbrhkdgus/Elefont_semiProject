<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

	<!-- Portfolio Start -->
    <section id="portfolio" class="portfolio section-space-padding">
        <div class="container">
         

            <div class="portfolio-inner">
                <div class="test-shop-box">

                    <div class="search-tools" id="search-tools">
                        <div class="tools font-name-finder">
                            <input type="text" name="font-search" id="font-search" placeholder="폰트명으로 검색">
                            <button><i class="fas fa-search"></i></button>
                            
                        </div>
                        <div class="tools font-color-previewer">
                            <span id="color-title">색상 선택하기</span>
                            <form action="HTMLColorPicker.html">
                                <input type="color" name="favcolor" id="color" >
                            </form>
                        </div>
                        <div class="tools font-size-range">
                            <span id="range-result"></span>
                            <input type="range" min="10" max="50" step="2" value="20" id="range" />
                        </div>
                        <div class="tools font-style-category">
                            <button id="style-button">폰트 형태</button>
                            <div class="font-style-chkbox">
                                <input type="checkbox" name="font-style" id="font-style1"><label for="font-style1">Serif</label><br>
                                <input type="checkbox" name="font-style" id="font-style2"><label for="font-style2">Sans Serif</label><br>
                                <input type="checkbox" name="font-style" id="font-style3"><label for="font-style3">Handwriting</label><br>
                                <input type="checkbox" name="font-style" id="font-style4"><label for="font-style4">Monospace</label>
                                
                            </div>
                        </div>
                        <div class="tools font-sort-selector">
                            <select name="font-sort" id="font-sort">
                                <option value="popular" selected>인기순</option>
                                <option value="view">조회순</option>
                                <option value="order">판매순</option>
                                <option value="recommand">MD추천순</option>
                            </select>
                        </div>
                    </div>

              
                    <div class="test-shop">
                        <div class="test-item">
                            <div class="test-item-title"> 전체 폰트 적용 </div>

                            <textarea name="" id="allFontStyle" cols="30" rows="10"></textarea>
                           
                        </div>
 <%
 	List<Font> fontList = (List<Font>)request.getAttribute("fontList");
    List<Attachment> fontAttchmentList = (List<Attachment>)request.getAttribute("fontAttchmentList");
   	System.out.println("fontList@jsp : " + fontList);
   	System.out.println("fontAttchmentList@jsp : " + fontAttchmentList);
    for(Font font : fontList){
    	for(Attachment att : fontAttchmentList){
    		
    	}
%>
                        <div class="test-item">
                            <a href="<%= request.getContextPath()%>/shopDetail?fontNo=<%= font.getFontNo()%>"><div class="test-item-title"> <%= font.getFontName()%> </div></a>
                            <textarea name="" id="" cols="30" rows="10" class="font-style"></textarea>
                            <div class="test-item-buttons"> 
                                <i class="fas fa-heart"></i>  
                                <i class="fas fa-search-plus"></i>
                             </div>
                        </div> 

<%
    }
 %>                    
                      
                       
                    </div>


                </div>
            </div>

    </section>
<script>
$("#range-result").html($(range).val()+"px");
$(".font-style").css("font-size", $(range).val()+"px");
$(".font-style").css("color", $(color).val());
	/* 샵 랜딩 스타일 체크박스 드롭다운 메뉴 */
	$("#style-button").click((e)=>{
		console.log("스타일 버튼 클릭");
	    $(".font-style-chkbox").toggle();
	});
	/* 폰트 사이즈 조절 바 px크기 입력, textarea에 반영*/
	
	$(range).change((e)=>{
		let $fontSize = $(e.target).val();
		$("#range-result").html($(range).val()+"px");
		$(".font-style").css("font-size", $fontSize+"px");
    });
	
	/* 돋보기 아이콘 클릭시 넘어감*/
	$(".fa-search-plus").click((e)=>{
		location.href = "<%=request.getContextPath()%>/shopDetail";
	});
	
	/* 색상 변경 시 아래 textarea에 있는 글씨들에 반영 */
	$(color).change((e)=>{
		let $fontColor = $(e.target).val();
		console.log($fontColor);
		$(".font-style").css("color",$fontColor);
	});
	
	/* 전체 폰트 적용에 내용 입력 시 모든 textarea에 적용 */
	$(allFontStyle).on("propertychange change keyup paste input", (e)=>{
		$(".font-style").val($(e.target).val());
	});
	
</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>