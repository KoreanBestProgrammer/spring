<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goPw(){
		mem.t_pw.focus();
	}
	function memberLogin(){
		mem.t_gubun.value="memberLogin";
		
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
</script>

		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a><span class="fnt"><i class="fas fa-apple-alt"></i></span>LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a href="javascript:goPage('join')"> JOIN</a></li>
				<li><a href="javascript:goPage('myinfo')"><span class="fnt"></span> MY INFORMATION</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER LOGIN
			</p>
		<form name="mem">
			<div class="login">
				<div class="member_boxL">
					<h2>LOGIN</h2>
					<div class="login_form">
							<input type="hidden" name="t_gubun">
							<div class="fl_clear"><label for="mbrId">아이디</label><input name="t_id" autofocus id="mbrId" type="text" onkeypress="if( event.keyCode==13 ){goPw()}"></div>
							<div class="fl_clear"><label for="scrtNo">비밀번호</label><input name="t_pw" id="scrtNo" type="password" onkeypress="if( event.keyCode==13 ){memberLogin()}"></div>
							<a class="btn_login btn_Blue" href="javascript:memberLogin()">로그인</a>
					</div>
				</div>		
			</div>
		</form>
		</div>	

		<%@ include file="../common_footer.jsp" %>
	</div>	
</body>
</html>






    