<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<style>
/* 회원정보수정 시작 */
.editTitle{
    display: inline-block;
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
#editPhoto{
    display: inline-block;
    margin-right: 40px;
}
#infoEditInput{
    display: inline-block;
}
.info{
    display: inline-block;
}
#editOuterDiv{
    border: 3px solid grey;
    border-radius: 10px;
    display: inline-block;
    padding: 30px 30px;
}
#editButton{
    text-align: center;
}
#editInfo2{
    margin-left: 20px;
    margin-bottom: 8px;
}
#withdrawButton{
    margin-top: 40px;
    width: 60px;
    font-size: 1px;
    float: right;
}
/*회원정보수정 끝*/
</style>

        <!-- 회원정보 수정 section 시작-->
        <section id="portfolio" class="portfolio section-space-padding">
            <div id="editOuterDiv">
                    <div id="titleDiv" >
                        <div class="editTitle" id="title"><h2>회원정보 수정</h2></div>
                        <div class="editTitle"id="withdrawButton"><button>탈퇴하기</button></div>
                    </div>
                    <hr>
                    <div id="infoDiv">
                        <div id="personalInfo">
                            <div id="editPhoto">
                                <div class="defaultphotobox">
                                    <img class="defaultPhoto" src="https://t1.daumcdn.net/cfile/tistory/243FE450575F82662D" alt="프로필기본사진">
                                </div>
                                <p>&nbsp;&nbsp;2021.03.03 가입</p>  
                            </div>
                            <div id="infoEditInput">
                                <div id="info1" class="info">
                                    <table id="editInfo1">
                                        <tr>
                                            <th>아이디<sup>*</sup></th>
                                            <td>
                                                <input type="text" id="editId" readonly placeholder="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>비밀번호<sup>*</sup></th>
                                            <td>
                                                <input type="text" id="editPwd" required>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>비밀번호 확인<sup>*</sup>&nbsp;</th>
                                            <td>
                                                <input type="text" name="" required>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>생년월일</th>
                                            <td>
                                                <input type="date" id="editBirthday">
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="info2" class="info">
                                    <table id="editInfo2">
                                        <tr>
                                            <th>성별</th>
                                            <td>
                                                <input type="radio" id="male" name="editGender" value="male">
                                                <label for="male">남성</label>
                                                <input type="radio" id="female" name="eidtGender" value="male">
                                                <label for="female">여성</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>이메일&nbsp;</th>
                                            <td>
                                                <input type="email" id="editEmail">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>연락처&nbsp;</th>
                                            <td>
                                                <input type="tel" id="editPhone">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>직업</th>
                                            <td>
                                                <select name="job" id="editJob">
                                                    <option value="student">학생</option>
                                                    <option value="student">디자이너</option>
                                                    <option value="student">개발자</option>
                                                    <option value="student">기타</option>
                                                    <option value="none">대답 안 함</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div id="editButton">
                            <br>
                            <button>회원정보수정</button>
                        </div>
                    </div>
            </div>   
            </div>
        </section>


<%@ include file = "/WEB-INF/views/common/footer.jsp" %>