/**
 * 
 */
$(window).on('load', function() {
	
	/*좋아요 버튼 */
    $(function() {
        $( ".heart-icon" ).click(function() {
        $( ".heart-icon" ).toggleClass( "press", 1000 );
    });
  });

	

	/* 회원가입 버튼 클릭 시 회원 가입 모달창 띄우기 */	
	const email = document.getElementsByClassName("_email");	
	
	$(memberEnrollBtn).click((e)=>{
		$(".memberEnroll").fadeToggle();
		$(".loginBox").fadeOut();
	});
	
	$(enrollXBtn).click((e)=>{
		$(".memberEnroll").fadeOut();
	});
	$(enrollXBtn2).click((e)=>{
		$(".memberEnroll").fadeOut();
	});
	/**
	* 중복검사 이후 아이디를 수정하는 경우.
	*/
	$(_memberId).change((e) => {
		$(idValid).val(0);
	});
	
	/**
	* 아이디 중복검사 함수
	*/
	$(btnCheckId).click((e) => {
		//memberId 유효성 검사
		const $memberId = $("#_memberId");
		//아이디는 영문자/숫자  4글자이상만 허용 
		if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
			alert("아이디는 최소 4자리이상이어야 합니다.");
			$memberId.select();
			return;
		}
		
		const title = "popupCheckId";
		const spec = "left=500px, top=300px, width=300px, height=200px";
		const popup = open("", title, spec);
		
		const $frm = $(document.checkIdDuplicateFrm);
		$frm.find("[name=memberId]").val($(_memberId).val());
		$frm.attr("target", title) // form제출을 popup에서 진행
			.submit();
	});
	
	/**
	* 중복검사 이후 아이디를 수정하는 경우.
	*/
	$("._email").change((e) => {
		console.log("이 칸을 건들였다아")
		$(".emailValid").val(0);
	});
	
	/**
	* 이메일 중복검사 함수
	*/
	$(btnCheckEmail).click((e) => {
		//memberEmail 유효성 검사
		const $memberEmail = $("._email");
		// 4글자 이상(\w = [a-zA-Z0-9_], [\w-.]) @가 나오고
        // 1글자 이상(주소). 글자 가 1~3번 반복됨
        if(!regExpTest(/^[\w]{4,}@[\w]+(.[\w]+){1,3}$/, email, "이메일 형식에 어긋납니다."))
			return false;
		
		const title = "popupCheckEmail";
		const spec = "left=500px, top=300px, width=300px, height=200px";
		const popup = open("", title, spec);
		
		const $frm = $(document.checkEmailDuplicateFrm);
		$frm.find("[name=memberEmail]").val($("._email").val());
		$frm.attr("target", title) // form제출을 popup에서 진행
			.submit();
	});
	
	/**
	* memberEnrollFrm 유효성 검사
	* 1. 필수항목 값입력 확인
	* 2. 아이디/비번 4글자이상
	* 3. 비밀번호 일치 확인
	*/
	$("[name=memberEnrollFrm]").submit((e) => {
		//memberId
		const $memberId = $("#_memberId");
		//아이디는 영문자/숫자  4글자이상만 허용 
		if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
			alert("아이디는 최소 4자리이상이어야 합니다.");
			$memberId.select();
			return false;
		}
		
		//idValid 중복검사여부
/*		const $idValid = $("#idValid");
		if($idValid.val() == "0"){
			alert("아이디 중복검사 해주세요.");
			$("#btnCheckId").focus();
			return false;
		}*/

		//password
		const $p1 = $("#_password");
		const $p2 = $("#password2");
		if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
			alert("유효한 패스워드를 입력하세요.");
			$p1.select();
			return false;
		}
		if($p1.val() != $p2.val()){
			alert("패스워드가 일치하지 않습니다.");
			$p1.select();
			return false;
		}
		
		//memberName
		const $memberName = $("#memberName");
		if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
			alert("이름은 한글 2글자 이상이어야 합니다.");
			$memberName.select();
			return false;
		}
		
		//email

		if(!regExpTest(/^[\w]{4,}@[\w]+(.[\w]+){1,3}$/, email, "이메일 형식에 어긋납니다."))
                return false;
		
		//phone
		const $phone = $("#phone");
		//-제거하기
		$phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
		
		if(/^010[0-9]{8}$/.test($phone.val()) == false){
			alert("유효한 전화번호가 아닙니다.");
			$phone.select();
			return false;
		}
		
		const emailVaildVal = $(".emailValid").val();
		
		if(emailVaildVal !== 1){
			alert("중복검사를 다시 해주세요"); 
			return false;	
		}
		
		return true;
		
	});
	
	/* 이메일 검사 */
	function regExpTest(regExp, el, msg){
        if(regExp.test(el[0].value))
            return true;
        //적합한 문자열이 아닌 경우
        alert(msg);
        el[0].value="";
        el[0].focus();
        return false;
    }
    
	/* 로그인 후 멤버 아이디 호버 시 상세 메뉴 띄우기 */
	$membermenu = $(".member-menu");
	$profile = $("#profile");
	$profile.hover(e=>{
		$membermenu.stop().slideDown();
	},e=>{
		$membermenu.slideUp();
		$membermenu.hover(e=>{
			$membermenu.stop().slideDown();
		},e=>{
			$membermenu.stop().slideUp();
		});
		
	});

});

  		function doShow(imgSrc){
        var imgSrcc = $(imgSrc).parent().children("img").attr("src");
        var name = $(imgSrc).html();  

        // console.log($('.aboutdetail.leftbox>p').text());
        // console.log(name)
        // console.log(imgSrcc);
        document.getElementById("bigImg").src = imgSrcc;
        $('.aboutdetail.leftbox>p').text(name);
        }






