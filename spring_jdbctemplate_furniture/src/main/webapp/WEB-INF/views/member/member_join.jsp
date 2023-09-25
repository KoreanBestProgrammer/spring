<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goJoin(){
		
		if(checkValue(mem.t_id,"id 입력!")) return;
		
		if(mem.t_checkId.value == ""){
			alert("id 중복검사를 해주세요!");
			mem.t_id.focus();
			return;
		}
		
		if(mem.t_id.value != mem.t_checkId.value){
			alert("id 중복검사를 다시 해주세요!");
			mem.t_id.focus();
			return;
		}
		
		if(checkValue(mem.t_name,"성명 입력!")) return;
		if(checkValue(mem.t_password,"비밀번호 입력!")) return;
		if(checkValue(mem.t_password_confirm,"비밀번호 확인 입력!")) return;
		
		if(mem.t_password.value != mem.t_password_confirm.value){
			alert("비밀번호 확인을 해주세요!");
			mem.t_password.focus();
			return;
		}
		
		if(checkValue(mem.t_address,"주소 입력!")) return;
		if(checkValue(mem.t_mobile_1,"연락처 입력!")) return;
		if(checkValue(mem.t_mobile_2,"연락처 입력!")) return;
		if(checkValue(mem.t_mobile_3,"연락처 입력!")) return;
		if(checkValue(mem.t_gender,"성별 선택!")) return;
		
		
		mem.t_gubun.value = "memberSave";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
	
	function checkID(){
		if(checkValue(mem.t_id,"id 입력 후 중복검사!")) return;
		$.ajax({
			type : "POST",
			url  : "MemberCheckId",
			data : "t_id="+mem.t_id.value,
			dataType : "text",
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				$("#idResult").text(data);
				
				if(data == "사용불가능"){
					$("#idResult").css("color","red");
					mem.t_checkId.value = "";
				}else {
					$("#idResult").css("color","black");
					mem.t_checkId.value = mem.t_id.value;
				}
				
				
			}
		});				
	}
</script>
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goPage('login')">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a><span class="fnt"><i class="fas fa-apple-alt"></i></span> JOIN</a></li>
				<li><a href="javascript:goPage('myinfo')"><span class="fnt"></span> MY INFORMATION</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER JOIN
			</p>
			<form name="mem">
				<input type="hidden" name="t_gubun">
			<table class="boardForm">
			  <colgroup>
				<col width="200" />
				<col width="500" />
			  </colgroup>
			  <tbody>
				<tr>
				  <th><label for="id">I D</label></th>
				  <td>
					<input name="t_id" type="text" size="10" id="id" title="id입력하세요">
					<input type="button" onclick="checkID()" value="ID중복검사" class="checkB">
					<span id="idResult"></span>
					<input type="hidden" name="t_checkId">
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td><input type="text" size="8" id="nana" name="t_name"></td>
				</tr>
				<tr>
				  <th>비빌번호</th>
				  <td><input type="text" size="13" name="t_password"></td>
				</tr>
				<tr>
				  <th>비밀번호확인</th>
				  <td><input type="text" size="13" name="t_password_confirm"></td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					<select name="t_area">
						<option value="02">서울</option>
						<option value="042">대전</option>
						<option value="050">부산</option>
						<option value="060">대구</option>        
					</select>	  
				  </td>
				</tr>	
				
				<tr>
				  <th>주소</th>
				  <td><input type="text" size="40" name="t_address"></td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>
					<input type="text" size="3" name="t_mobile_1"> -
					<input type="text" size="4" name="t_mobile_2"> -
					<input type="text" size="4" name="t_mobile_3">
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
					  <input type="radio" value="f" name="t_gender" class="middleCheck" /> 여&nbsp;&nbsp;        
					  <input type="radio" value="m" name="t_gender" class="middleCheck" /> 남        
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <input type="checkbox" value="y" name="t_hobby_travel" class="middleCheck" /> 여행&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_reading" class="middleCheck" /> 독서&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_sports" class="middleCheck" /> 운동
				  </td>
				</tr>
			  </tbody>
			</table>
			<div class="buttonGroup_center">
				<a href="javascript:goJoin()" class="butt">JOIN</a>
			</div>	
			</form>
		</div>	

		<%@ include file="../common_footer.jsp" %>
	</div>	
</body>
</html>






    