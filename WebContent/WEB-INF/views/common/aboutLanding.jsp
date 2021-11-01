<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

            <section id="portfolio" class="portfolio section-space-padding">
            <div class="container">
             
    
                <div class="about">
                  <span>About Us</span>
                     </div>

                     <div class="about content">
                        <span>Team Produce 404</span>
                         </div>  
                         <hr>

             			<form action ="<%= request.getContextPath() %>/aboutDetail" name="nameFrm">
                        	 <div class="about outer">
                            
                            	<div class="about inner">
                                	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMrq_-30ha8PqOeFBq1zwtpnjUPwdJubwKXg&usqp=CAU" alt=""  />
  			            			<input type="submit" name="name" onclick="submitMember();" value="권혜진" > </input>
                            	</div>   

                             	<div class="about inner">
                             		<img src="https://cdn.discordapp.com/attachments/893374121449254916/904649352100851762/DSC07028_2.jpg" alt="" />
                               		<input type="submit" name="name" onclick="submitMember();" value="김다현" > </input>
                          		</div>   

                              
                                <div class="about inner">
                                	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMrq_-30ha8PqOeFBq1zwtpnjUPwdJubwKXg&usqp=CAU" alt="" />
                              		<input type="submit" name="name" onclick="submitMember();" value="김은희" > </input>
                                </div>   

                                <div class="about inner">
                                	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMrq_-30ha8PqOeFBq1zwtpnjUPwdJubwKXg&usqp=CAU" alt="" />
                              		<input type="submit" name="name" onclick="submitMember();" value="백지영" > </input>
                                </div>   

                                <div class="about inner">
                                	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMrq_-30ha8PqOeFBq1zwtpnjUPwdJubwKXg&usqp=CAU" alt="" />
                               		<input type="submit" name="name" onclick="submitMember();" value="유광현" > </input>
                                </div>   

                                <div class="about inner">
                                	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMrq_-30ha8PqOeFBq1zwtpnjUPwdJubwKXg&usqp=CAU" alt="" />
     		                  		<input type="submit" name="name" onclick="submitMember();" value="이윤희" > </input>
                                	
                                </div>   
                        	 </div>

   						</form>
              
                </div>
            
        </section>
    
        <!-- Portfolio End -->
  
<script>
function submitMember () {
		
$(document.nameFrm).submit();

}
</script>  
    
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>