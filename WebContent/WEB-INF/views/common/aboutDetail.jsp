<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<script src=""></script>
<%
	String namee = request.getParameter("name");
	
%>
   <!-- Portfolio Start -->
            <section id="portfolio" class="portfolio section-space-padding">
                <div class="container">
                 
        
                    <div class="about">
                      <span>AboutDetail</span>
                         </div>
    
                         <div class="about content">
                             
                             </div>  
                             <hr>
    
                             <div class="aboutdetail outer">
                                
    
                                <div class="aboutdetail leftbox active "> 
                                     <div class="left image ">   
                                    <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                    <p>권혜진</p>
                                    </div>
                                        <div class="left content ">
                                        <p>권혜진님의 소개글입니다</p>
                                    </div>
                                          
                                 </div>   
    
                                 <div class="aboutdetail leftbox"> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                   <p>김다현</p>
                                   </div>
                                       <div class="left content ">
                                       <p>김다현님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                   <p>김은희</p>
                                   </div>
                                       <div class="left content ">
                                       <p>김은희님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                   <p>백지영</p>
                                   </div>
                                       <div class="left content ">
                                       <p>백지영님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                   <p>유광현</p>
                                   </div>
                                       <div class="left content ">
                                       <p>유광현님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/yyhZB9x/924514.png" alt=""  id="bigImg" >
                                   <p>이윤희</p>
                                   </div>
                                       <div class="left content ">
                                       <p>이윤희님의 소개글입니다</p>
                                   </div>
                                         
                                </div>   

               
    
    
                              <div class="aboutdetail rightouter">
                           
                                <div class="aboutdetail rightinner" name="권혜진" >
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
    
        
                    <div class="portfolio-inner">
                        <div class="test-shop-box">
        
                          
                           
        
        
                        </div>
                    </div>
                </div>
        
             
        	
            <script>
              
                
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