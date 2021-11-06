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
                      <div class="underlined-text">
					    <p class="underlined first">Produce 404</p>
					    <br />
					    <p class="underlined second">멤버소개</p>
					    
					   </div>
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
                                        <div class="left content about-detail-content">
                                        <p>
                                        안녕하세요. 프로듀스404의 권혜진입니다. <br />
                                        팀 이름을 지을 때는 그냥 재밌고 입에 붙어서 좋아했는데 <br />
                                        팀명대로 에러를 생산하는 사람이 되어버렸습니다. <br />
                                        다음 팀명은 always200이 좋겠어요.
                                        </p>
                                        <p>
                                        이것저것 부딪힌 문제는 많았지만 좋은 팀원들을 만나<br /> 즐겁게 프로젝트를 진행했습니다. <br />
										다들 감사하고 사랑합니다~! 우리 팀 최고!
                                        </p>
                                    </div>
                                 </div>   


                             
  
                                 <div class="aboutdetail leftbox"  > 
                                 <input type="hidden" value="<%=kdh%>"  class="input-val"/> 
                                    <div class="left image">   
                                   <img src="https://cdn.discordapp.com/attachments/893374121449254916/904649352100851762/DSC07028_2.jpg" alt=""  id="bigImg" >
                                   <p>김다현</p>
                                   </div>
                                       <div class="left content about-detail-content" style="margin-top :40px;">
                                       <p>안녕하세요.느리지만 매일 조금씩 성장해나가는 김다현입니다.<br />
										엘리폰트 쇼핑몰 프로젝트를 통해
										좌충우돌하며 여기저기 들이박았지만 <br />3레벨 정도 레벨업 했습니다. ㅎㅎ <br />
										또 팀원분들의 ※개그※와 따숩한 도움의 손길이 없었다면 들이박다가 고장 났을 거에요.

										최고의 팀원들과 함께한 좌충우돌 레벨업의 추억, <br />여기 엘리폰트 프로젝트에 남깁니다.
										</p>
										<p>Hello,
I’m Darci Kim and I am an ever-growing individual, levelling up everyday :) <br />

Despite the challenges I faced while developing Elefont, I’d like to think that I’ve levelled up from this experience! 
My teammates’ support and healthy supply of running jokes are what kept me going during this project. 
And as I look back on our shared moments of struggles and successes, I present to you Elefont!</p>
                                   </div>
                                </div>   



                                <div class="aboutdetail leftbox"> 
                                    <div class="left image">   
                                    <input type="hidden" value="<%=keh%>" class="input-val" /> 
                                   <img src="https://i.ibb.co/s1JmptQ/image.png" alt=""  id="bigImg" >
                                   <p>김은희</p>
                                   </div>
                                       <div class="left content about-detail-content">
                                       <p>
                                       안녕하세요!엘리폰트 프로젝트팀원 김은희입니다. <br />
                                       두 번째 팀 프로젝트로 폰트쇼핑몰을 만들어보았는데요. <br />
                                       </p>
                                       
                                       <p>
                                       	팀장님 팀원분들 덕분에 정말 멋진 프로젝트를<br /> 만들 수 있어 영광이었습니다 <br />
                                       	혼자서는 못해냈을 멋진 경험을 하게 해줘서 고마워요!!:) <br /><br />
                                       	알러뷰 404 포레버!!
                                       </p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"  > 
                                <input type="hidden" value="<%=bjy%>" class="input-val" /> 
                                    <div class="left image">   
                                   <img src="https://media.discordapp.net/attachments/896004674937823263/904724717175439430/74d5566ca4912f5e.jpg" alt=""  id="bigImg" >
                                   <p>백지영</p>
                                   </div>
                                       <div class="left content about-detail-content">
                                       <p>
                                       안녕하세요? 팀프로듀스 404에서 0을 맡고 있습니다. <br />
                                       훌륭한 팀장,팀원 분들 만나서	프로젝트가 순조롭게 흘러가는 걸  보고 <br />
										팀웍이란 이런거구나 하고 느꼈습니다.
                                       </p>
                                       
                                       <p>
                                       다들 함께 울고 웃으며 코딩할 수 있어서 <br />
										너무 즐거웠고 영광이었어요 ! <br />
										앞으로 남은 기간들도 모두 힘내서 화이팅해봐용 <br />
                                       </p>
                                       
                                       <p>
                                       마지막으로 보고계실 kh국민프로듀서 팀장님들 픽미픽미업
                                       </p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox" > 
                                	<input type="hidden" value="<%=ykh%>" class="input-val" /> 
                                    <div class="left image">   
                                   <img src="https://i.ibb.co/L1pCTTN/tmp-1613290479805.jpg" alt=""  id="bigImg" >
                                   <p>유광현</p>
                                   </div>
                                       <div class="left content about-detail-content">
                                       <p>비록 이 프로젝트가 '세미'였지만,<br /> 마치 파이널처럼 열심히 달려왔습니다. <br />
                                       함께 저와 호흡을 맞춰주신 프로듀스 404 팀원들께<br /> 그동안 정말 고생했고, 고맙고, 감사하다는 말과 <br />
                                       최고의 팀원이었다고 꼭 말하고 싶습니다! (너네가 짱이야!) <br />                                    	
                                       </p>
                                       <p>
                                       코딩은 너무나 재미있다는 걸, 저의 선택이 틀리지 않았다는 걸 다시 한번 느끼게 되는 소중한 시간이었습니다. <br />
                                       쉽게 코드를 써 내려갈 때의 짜릿함과 풀리지 않는 로직에<br /> 머리를 싸메다 유레카를 외치던 순간의 희열을<br /> 저는 잊지 못할 것 같습니다. <br />
                           
                                       </p>
                                       <p>
                                       이제는 다가올 파이널을 기대하며,<br /> 일신우일신하는 유광현이 되겠습니다. <br />
                                       미래의 연봉을 위해!😎                                       </p>
                                   </div>
                                         
                                </div>   
                                <div class="aboutdetail leftbox"  > 
                                <input type="hidden" value="<%=lyh%>" class="input-val" /> 
                                    <div class="left image">   
                                   <img src="https://media.discordapp.net/attachments/896004674937823263/904724557737386054/unknown.png?width=641&height=670" alt=""  id="bigImg" >
                                   <p>이윤희</p>
                                   </div>
                                       <div class="left content about-detail-content">
                                       <p>안녕? 나는 팀 프로듀스 404에서 짱짱맨을 맡고있는 윤희라구해*^^*
											이번 프로젝트는 정말 힘들지만 힘든만큼 뿌듯하고 높은 산을 등산한 느낌이야.
											등산을 하니까 생각나는데 니체는 '등산의기쁨은 정상에 올랐을 때 가장크다. 하지만 나의 최상의 기쁨은 험악한 산을 기어올라가는 순간에 있다'
											라는 말을 했는데 그말은 '인생도 마찬가지다! 인생에 있어서 모든 고난이자취를 
											감췄을때를 생각해보라! 그이상 삭막한것이 없으리라!' 라는 뜻이래
											코딩도 마찬가지인거같아... 에러가 많을수록 배우는게 많은거지<br />
											중요한건 멋지고 착한 산악인들을 만나 이번프로젝트라는 산을 완주하게 되어서 너무 
											기쁘고 팀원들에게 감사인사를 드립니다.!  <br />	-2021년11월05일 감사를 담아 윤희가
									</p>
                                   </div>
                                         
                                </div>   

               
    
    
                              <div class="aboutdetail rightouter">
                           
                                <div class="aboutdetail rightinner" name="권혜진"  >
                                    <img src="https://media.discordapp.net/attachments/895611304352555008/904720737141678080/KakaoTalk_20211101_221426889.jpg?width=670&height=670" alt="">
                                     <button type="button"   class = "rightBtn"  > 권혜진</button>
                                </div> 
                                    
    
    
                                  <div class="aboutdetail rightinner">
                                    <img src="https://cdn.discordapp.com/attachments/893374121449254916/904649352100851762/DSC07028_2.jpg" alt="" >
                                     <button type="button"  class = "rightBtn" > 김다현</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="https://i.ibb.co/s1JmptQ/image.png" alt="">
                                    <button type="button" class = "rightBtn"  > 김은희</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="https://media.discordapp.net/attachments/896004674937823263/904724717175439430/74d5566ca4912f5e.jpg" alt="">
                                    <button type="button" class = "rightBtn"   > 백지영</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="https://i.ibb.co/L1pCTTN/tmp-1613290479805.jpg" alt="">
                                    <button type="button" class = "rightBtn"  > 유광현</button>
                                  </div>   
    
                                  <div class="aboutdetail rightinner">
                                    <img src="https://media.discordapp.net/attachments/896004674937823263/904724557737386054/unknown.png?width=641&height=670" alt="">
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
					const $leftinner = $(".leftbox");
					 $leftinner.css("display" ,"none");
	                 $leftinner.eq(i).css("display","flex");
				}
            	
			i++	
			}
  
            	
            	
                 $(".rightBtn").click((e)=>{
                   const $index = $(e.target).parent().index();
                    const $leftinner = $(".leftbox");
                    $leftinner.css("display" ,"none");
                    $leftinner.eq($index).css("display","flex");

 
                });
                 
    	    
                
            
                </script>
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>