/**
 *  check for login
 */

/*
 * 로그인 시 빈문자열 검사
 */
function loginCheck() {
	if(document.frm.userid.value.length == 0) {
		alert("아이디를 입력해주세요.")
		frm.userid.focus()
		return false;
	}
	
	if(document.frm.pwd.value=="") {
		alert("암호는 반드시 입력해야 합니다.")
		frm.pwd.focus()
		return false;	
	}
	return true;	
}

/*
 * 아이디 중복 검사
 */
function idCheck() {
	if(document.frm.userid.value=="") {
		alert('아이디를 입력하여 주십시오.')
		document.frm.userid.focus()
		return
	}
	
	var url="idCheck.do?userid="+document.frm.userid.value;
	window.open(url,"_blank_1",
			"toolbar=no,menubar=no,scrollbars=yes,resizable=no,width=450,height=200")		
}

/*
 * 중복 검사한 아이디 사용 시 호출
 */
function idok() {	
	opener.frm.userid.value="${userid}"; //opener : 부모창
	opener.frm.reid.value="${userid}";
	self.close();		
}

function joinCheck() {
	if(document.frm.name.value.length == 0) {
		alert("이름을 입력해주세요.")
		frm.name.focus()
		return false;
	}
	
	if(document.frm.userid.value.length == 0) {
		alert("아이디를 입력해주세요.")
		frm.userid.focus()
		return false
	}
	
	if(document.frm.userid.value.length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.")
		frm.userid.focus()
		return false
	}
	
	if(document.frm.pwd.value.length == 0) {
		alert("비밀번호를 입력해주세요.")
		frm.pwd.focus()
		return false
	}
	
	if(document.frm.pwd.value != document.frm.pwd_check.value) {
		alert("비밀번호가 일치하지 않습니다.")
		frm.pwd.focus()
		return false
	}
	
	if(document.frm.reid.value.length == 0) {
		alert("아이디 중복 체크를 해주세요.")
		frm.userid.focus()
		return false
	}
	return true
}


