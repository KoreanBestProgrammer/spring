<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp"%>	
<script type="text/javascript">
	function goPw(){
		mem.t_new_pw.focus();
	}
	function passwordUpdate(){
		if(checkValue(mem.t_now_pw,"현재비밀번호 입력!")) return;
		if(checkValue(mem.t_new_pw,"새로운비밀번호 입력!")) return;
		if(checkValue(mem.t_new_pw_confirm,"비밀번호 확인입력!")) return;
		
		if(mem.t_new_pw.value != mem.t_new_pw_confirm.value){
			alert("새로운 비밀번호가 일치하지 않습니다.");
			mem.t_new_pw_confirm.focus();
			return;
		}
		
		mem.t_gubun.value ="passwordUpdate";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
</script>	
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="Member">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a href="javascript:goPage('join')"> CONTACK</a></li>
				<li><a href="javascript:goPage('myinfo')"><span class="fnt"><i class="fas fa-apple-alt"></i></span>MYINFORMATION</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER PASSWORD UPDATE
			</p>
		
			<div class="login">
				<div class="member_boxL">
					<h2>PASSWORD</h2>
					<div class="login_form password_form">
						<form name="mem">
							<input type="hidden" name="t_gubun" >
							<div class="fl_clear"><label for="mbrId"  style="width:120px;">현재비밀번호</label><input name="t_now_pw" autofocus id="mbrId" type="text" onkeypress="if(event.keyCode==13){goPw()}"></div>
							<div class="fl_clear"><label for="scrtNo" style="width:120px;">새로운비밀번호</label><input name="t_new_pw" id="scrtNo" type="password" onkeypress="if(event.keyCode==13){mem.t_new_pw_confirm.focus();}"></div>
							<div class="fl_clear"><label for="scrtNo" style="width:120px;">비밀번호확인</label><input name="t_new_pw_confirm" id="scrtNo" type="password"></div>
							<a class="btn_login btn_Blue" href="javascript:passwordUpdate()" style="height:127px;">변 경</a>
						</form>
					</div>
				   
				</div>		
			</div>

		</div>	

		<div id="b_bottom">
			<%@ include file="../common_footer.jsp" %>	
		</div>	
	</div>	
</body>
</html>






    