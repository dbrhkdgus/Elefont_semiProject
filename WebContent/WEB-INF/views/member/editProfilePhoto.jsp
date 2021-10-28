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
        <div id="PPOuterBox">
            <div id="EditProfilePhotoTitle">
                <h1>프로필사진 변경하기</h1>
            </div>
            <div id="ppphotobox">
                <div class="defaultphotobox">
                    <img class="defaultPhoto"
                        src="https://t1.daumcdn.net/cfile/tistory/243FE450575F82662D"
                        alt="프로필기본사진">
                </div>
                <br>
                <input type="button" value="기본사진으로 변경">
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
            <div id="confirmBtn">
                <button>등록</button>
            </div>
        </div>
    
    </body>
</html>