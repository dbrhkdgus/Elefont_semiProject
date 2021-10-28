<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
#PPTable{
    text-align: center;
}
#dropTheImage{
    display: flex;
    justify-content: center;
}


</style>
<body>
    <div id="PPOuterBox">
        <div id="EditProfilePhotoTitle">
            <h1>프로필사진 변경하기</h1>
        </div>
        <div id="dropTheImage">
            <foam id="imageUploadFrm">
                <table id="PPTable">
                    <tr>
                        <td id="tdpp1" class="pptd">이미지 <br/> 첨부파일</td>
                        <td id="tdpp2" class="pptd"><input type="file" name="" id="" /></td>
                    </tr>
                </table>
            </foam>
        </div>
    </div>
</body>
</html>