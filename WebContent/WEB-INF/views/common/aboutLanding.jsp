<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
	

%>
            <section id="portfolio" class="portfolio section-space-padding">
            <div class="container">
             
    
                <div class="about">
                  <span>About</span>
                     </div>

                     <div class="about content">
                        <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Alias voluptate sed, sit optio ratione vero explicabo distinctio iusto ex facilis eos aliquid quibusdam velit accusantium nihil dicta cumque pariatur magni?</span>
                         </div>  
                         <hr>

                         <div class="about outer">
                            
                            <div class="about inner">
             					<form action ="<%= request.getContextPath() %>/aboutDetail" name="nameFrm">
  			            			<input type="submit" name="name" onclick="submitMember();" value="권혜진" > </input>
                            	
                             </div>   

                             <div class="about inner">
                               	<input type="submit" name="name" onclick="submitMember();" value="김다현" > </input>
                          	</div>   

                              
                              <div class="about inner">
                              	<input type="submit" name="name" onclick="submitMember();" value="김은희" > </input>
                              </div>   

                              <div class="about inner">
                              	<input type="submit" name="name" onclick="submitMember();" value="백지영" > </input>
                              </div>   

                              <div class="about inner">
                               	<input type="submit" name="name" onclick="submitMember();" value="유광현" > </input>
                              </div>   

                              <div class="about inner">
     		                  	<input type="submit" name="name" onclick="submitMember();" value="이윤희" > </input>
                              </div>   
    						
    							
                      
                         </div>

   									 </form>
                <div class="portfolio-inner">
                    <div class="test-shop-box">
    
                      
                        </div>
    
    
                    </div>
                </div>
            </div>
    
        
    
        </section>
    
        <!-- Portfolio End -->
  
<script>
function submitMember () {
		
$(document.nameFrm).submit();

}



</script>  
    



<%@ include file = "/WEB-INF/views/common/footer.jsp" %>