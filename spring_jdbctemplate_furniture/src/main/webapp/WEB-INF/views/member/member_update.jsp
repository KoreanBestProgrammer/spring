<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goUpdate(){
		
		if(checkValue(mem.t_name,"성명 입력!")) return;
		if(checkValue(mem.t_address,"주소 입력!")) return;
		if(checkValue(mem.t_mobile_1,"연락처 입력!")) return;
		if(checkValue(mem.t_mobile_2,"연락처 입력!")) return;
		if(checkValue(mem.t_mobile_3,"연락처 입력!")) return;
		
		if(checkValue(mem.t_password,"비밀번호 입력!")) return;
		
		checkPassword();
		
		if(mem.t_pw_confirm.value == "no"){
			alert("비밀번호가 정확하지 않습니다!");
			mem.t_pw_confirm.focus();
			return;
		}
		
		mem.t_gubun.value = "memberUpdate";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
	
	function checkPassword(){
		$.ajax({
			type : "POST",
			url  : "MemberCheckPassword",
			async:false,
			data : "t_password="+mem.t_password.value+"&t_id="+mem.t_id.value,
			dataType : "text",
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				mem.t_pw_confirm.value = data;
				
				
				
			}
		});			
	}
</script>
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goPage('login')">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a><span class="fnt"></span> JOIN</a></li>
				<li><a><i class="fas fa-apple-alt"></i><span class="fnt"></span> MY INFORMATION</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER INFORMATION
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
				  	${dto.getId()}
				  	<input type="hidden" name="t_id" value="${dto.getId()}">
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td><input type="text" size="8" id="nana" name="t_name"></td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					<select name="t_area">
						<option value="02"  <c:if test="${dto.getArea() eq '02'}">selected</c:if>>서울</option>
						<option value="042" <c:if test="${dto.getArea() eq '042'}">selected</c:if>>대전</option>
						<option value="050" <c:if test="${dto.getArea() eq '050'}">selected</c:if>>부산</option>
						<option value="060" <c:if test="${dto.getArea() eq '060'}">selected</c:if>>대구</option>        
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
					<input type="text" size="3" name="t_mobile_1" value="${dto.getMobile_1()}"> -
					<input type="text" size="4" name="t_mobile_2" value="${dto.getMobile_2()}"> -
					<input type="text" size="4" name="t_mobile_3" value="${dto.getMobile_3()}">
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
					  <input type="radio" value="f" <c:if test="${dto.getGender() eq 'f'}">checked</c:if> name="t_gender" class="middleCheck" /> 여&nbsp;&nbsp;        
					  <input type="radio" value="m" <c:if test="${dto.getGender() eq 'm'}">checked</c:if> name="t_gender" class="middleCheck" /> 남        
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <input type="checkbox" value="y" <c:if test="${dto.getHobby_travel() eq 'y'}">checked</c:if> name="t_hobby_travel" class="middleCheck" /> 여행&nbsp;&nbsp; 
					  <input type="checkbox" value="y" <c:if test="${dto.getHobby_reading() eq 'y'}">checked</c:if> name="t_hobby_reading" class="middleCheck" /> 독서&nbsp;&nbsp; 
					  <input type="checkbox" value="y" <c:if test="${dto.getHobby_sports() eq 'y'}">checked</c:if> name="t_hobby_sports" class="middleCheck" /> 운동
				  </td>
				</tr>
				<tr>
				  <th>비빌번호</th>
				  <td>
				  	<input type="text" size="13" name="t_password">
				  	<input type="hidden" name="t_pw_confirm">
				  </td>
				</tr>
			  </tbody>
			</table>
			<div class="buttonGroup_center">
				<a href="javascript:goUpdate()" class="butt">정보수정</a>
			</div>	
			</form>
		</div>	

		<%@ include file="../common_footer.jsp" %>
	</div>	
</body>
</html>






