<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 사진 수정</title>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
</head>
<style>
    #EditProfilePhotoTitle{
        text-align: center;
    }
    #PPTable{
        border: 1px solid black;
    }
    .pptd{
        border: 1px solid black;
        height: 80px;
        text-align: center;
    }
    #tdpp1{
        width: 120px;
    }
    #tdpp2{
        width: 300px;
    }
    #PPTable{
        text-align: center;
    }
    #dropTheImage{
        display: flex;
        justify-content: center;
    }
    .defaultphotobox {
    width: 150px;
    height: 150px; 
    border-radius: 70%;
    overflow: hidden;
    }
    .defaultPhoto {
    width: 100%;
    height: 100%;
    object-fit: cover;
    }
    #ppphotobox{
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
    align-items: center;
    flex-direction: column;
    }
    #confirmBtn{
        text-align: center;
        margin-top: 30px;

    }
    </style>
    <body>
    
    <% String photoPath = (String) request.getParameter("profilePhotoPath");  %>

    
        <div id="PPOuterBox">
            <div id="EditProfilePhotoTitle">
                <h1>프로필사진 변경하기</h1>
            </div>
            <div id="ppphotobox">
                <div class="defaultphotobox" id="defaultphotobox">
                    <img class="defaultPhoto" id="previewPhoto"
                        src="<%= request.getContextPath() %>/upload/profilephotos/<%=photoPath%>"
                        alt="프로필기본사진">
                </div>
                <br>
                <input type="button" value="기본사진으로 변경" onclick="setAsDefaultPhoto();">
                <form action="<%=request.getContextPath()%>/member/changeAsDefaultPhoto" name="setAsDefaultPhotoFrm" method="post">
                	<input type="hidden" name="memberNo" value="<%=request.getParameter("memberNo")%>" />
                </form>
            </div>
            <div id="dropTheImage">
           	    <form name="imageUploadFrm"  id="imageUploadFrm" method="post" enctype="multipart/form-data"
   				action="<%=request.getContextPath()%>/member/profileFileUpload">
	                  <table id="PPTable">
	                      <tr>
	                          <td id="tdpp1" class="pptd">이미지 <br/> 첨부파일</td>
	                          <td id="tdpp2" class="pptd"><input type="file" name="profileimage" onchange='openFile(event)'/>
	                          <hr /><input id="changeImage" type="button" value="프로필사진 변경하기" onclick="ddd();" /></td>
	                          <input type="hidden" id="didWePutFile" value="0"/>
	                          <input type="hidden" name="memberNo" value="<%=request.getParameter("memberNo")%>" />
	                      </tr>
	                  </table>
            	</form>
            </div>
            <div id="confirmBtn">
                <button id="closeBtn">창닫기</button>
            </div>
        </div>
 <script>
$(closeBtn).click(()=>{
	if(confirm("프로필 사진 변경을 취소하시겠습니까?")){
		self.close();		
	};
}) 
 
 var openFile = function(event) { 
	
	$(didWePutFile).val(1);
	console.log($(didWePutFile).val());
	var input = event.target; var reader = new FileReader(); 
	 
 	reader.onload = function(){ 
 		var dataURL = reader.result; 
 		var output = document.getElementById('previewPhoto'); 
 		output.src = dataURL; 
 		}; 
 		
 	reader.readAsDataURL(input.files[0]); 
 	};

	 function ddd(){
		 const $canWeSubmit = $(didWePutFile).val();
		 if($canWeSubmit == 1) {
			 if(confirm("프로필 사진을 변경하시겠습니까?")){
			 $(document.imageUploadFrm).submit();
			 }
		 }else{
			 alert("파일을 입력하세요");
		 }
	 }
	 
	 function setAsDefaultPhoto() {
		 if(confirm("프로필 사진을 기본 이미지로 변경하시겠습니까?")){
			 $(document.setAsDefaultPhotoFrm).submit();
			 
		 }
	 }
 
 </script> 
    </body>
</html>