<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<script src=""></script>
<%
	String name = request.getParameter("name");
	String khj ="권혜진";
	String kdh ="김다현";
	String keh ="김은희";
	String bjy ="백지영";
	String ykh = "유광현";
	String lyh = "이윤희";
%>
   <!-- Portfolio Start -->
            <section id="portfolio" class="portfolio section-space-padding">
                <div class="container">
                 
        
                    <div class="about">
                      <span>AboutDetail</span>
                         </div>
                         <div class="about content">
                             </div>  
                             <div class="aboutdetail outer">




                                <div class="aboutdetail leftbox active ">
                                	<input type="hidden" value="<%=khj%>" class="input-val" /> 
                                     <div class="left image ">   
                                    <img src="https://media.discordapp.net/attachments/895611304352555008/904720737141678080/KakaoTalk_20211101_221426889.jpg?width=670&height=670" alt=""  id="bigImg" >
                                    <p >권혜진</p>
                                    </div>
                                        <div class="left content ">
                                        <p>권혜진님의 소개글입니다</p>
                                    </div>
                                 </div>   


                             
  
                                 <div class="aboutdetail leftbox"  > 
                                 <input type="hidden" value="<%=kdh%>"  class="input-val"/> 
                                    <div class="left image">   
                                   <img src="https://cdn.discordapp.com/attachments/893374121449254916/904649352100851762/DSC07028_2.jpg" alt=""  id="bigImg" >
                                   <p>김다현</p>
                                   </div>
                                       <div class="left content ">
                                       <p>김다현님의 소개글입니다</p>
                                   </div>
                                </div>   



                                <div class="aboutdetail leftbox"> 
                                    <div class="left image">   
                                    <input type="hidden" value="<%=keh%>" class="input-val" /> 
                                   <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                   <p>김은희</p>
                                   </div>
                                       <div class="left content ">
                                       <p>김은희님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"  > 
                                <input type="hidden" value="<%=bjy%>" class="input-val" /> 
                                    <div class="left image">   
                                   <img src="https://media.discordapp.net/attachments/896004674937823263/904722580630556712/profile.jpg" alt=""  id="bigImg" >
                                   <p>백지영</p>
                                   </div>
                                       <div class="left content ">
                                       <p>백지영님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox" > 
                                	<input type="hidden" value="<%=ykh%>" class="input-val" /> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/L1pCTTN/tmp-1613290479805.jpg" alt=""  id="bigImg" >
                                   <p>유광현</p>
                                   </div>
                                       <div class="left content ">
                                       <p>유광현님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"  > 
                                <input type="hidden" value="<%=lyh%>" class="input-val" /> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                   <p>이윤희</p>
                                   </div>
                                       <div class="left content ">
                                       <p>이윤희님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   

               
    
    
                              <div class="aboutdetail rightouter">
                           
                                <div class="aboutdetail rightinner" name="권혜진"  >
                                    <img src="https://i.ibb.co/ZT0fdYz/Visual-Studio-Code-1-35-icon-svg.png" alt="">
                                     <button type="button"   class = "rightBtn"  > 권혜진</button>
                                </div> 
                                    
    
    
                                  <div class="aboutdetail rightinner">
                                    <img src="./images/portfolio/1.jpg" alt="" >
                                     <button type="button"  class = "rightBtn" > 김다현</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="./images/portfolio/1.jpg" alt="">
                                    <button type="button" class = "rightBtn"  > 김은희</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="./images/portfolio/1.jpg" alt="">
                                    <button type="button" class = "rightBtn"   > 백지영</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="./images/portfolio/1.jpg" alt="">
                                    <button type="button" class = "rightBtn"  > 유광현</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="./images/portfolio/1.jpg" alt="">
                                    <button type="button" class = "rightBtn"  > 이윤희</button>
                                  </div>   
    
    
                                </div>
                                 
                             </div>
    
        
                </div>
</section>        
            <script>
            var i =0;
            const $name = "<%=name%>";
			while(i<=5){
				if($(".input-val").eq(i).val()== $name){
					console.log("일치 , " + i)
					const $leftinner = $(".leftbox");
					 $leftinner.css("display" ,"none");
	                 $leftinner.eq(i).css("display","flex");
				}
            	
			i++	
			}
  
            	
            	
                 $(".rightBtn").click((e)=>{
                   const $index = $(e.target).parent().index();
                    // console.log($rightinner);
                    const $leftinner = $(".leftbox");
                    console.log($leftinner);
                    $leftinner.css("display" ,"none");
                    $leftinner.eq($index).css("display","flex");

 
                });
                 
    	    
                
            
                </script>
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>