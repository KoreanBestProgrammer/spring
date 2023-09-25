<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goUpdateForm(){
		mem.t_gubun.value="updateForm";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
	function goPasswordChange(){
		mem.t_gubun.value = "passwordUpdateForm";
		mem.method="post";
		mem.action="Member";
		mem.submit();
		
	}
	function goExit(){
		if(confirm("정말 탈퇴하시겠습니까?")){
			mem.t_gubun.value = "memberExit";
			mem.method="post";
			mem.action="Member";
			mem.submit();
		}
	}
</script>
<form name="mem">
	<input type="hidden" name="t_gubun">	
</form>
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goPage('login')">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a href="javascript:goPage('join')"><span class="fnt"></span> JOIN</a></li>
				<li><a><span class="fnt"><i class="fas fa-apple-alt"></i></span> MY INFORMATION</a></li>
				
			</ul>
		</div>
		<div id="b_right">
			<p class="n_title">
				My Information
			</p>
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
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td>${dto.getName()}</td>
				</tr>
				<tr>
				  <th>비빌번호</th>
				<td>
					<c:forEach begin="1" end="${dto.getPassword_len()}">
						*
					</c:forEach>
				</td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
				  <c:choose>
				  	<c:when test="${dto.getArea() eq '042'}">
				  		대전
				  	</c:when>
				  	<c:when test="${dto.getArea() eq '02'}">
				  		서울
				  	</c:when>
				  	<c:when test="${dto.getArea() eq '050'}">
				  		부산
				  	</c:when>
				  	<c:when test="${dto.getArea() eq '060'}">
				  		대구
				  	</c:when>
				  </c:choose>
				  
				<!--
				    <c:if test="${dto.getArea() eq '042'}">
						대전
					</c:if>
					<c:if test="${dto.getArea() eq '02'}">
						서울
					</c:if>
					<c:if test="${dto.getArea() eq '050'}">
						부산
					</c:if>
					<c:if test="${dto.getArea() eq '060'}">
						대구
					</c:if>
				 -->	
				  </td>
				</tr>	
				
				<tr>
				  <th>주소</th>
				  <td>${dto.getAddress()}</td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>
					${dto.getMobile_1()}-${dto.getMobile_2()}-${dto.getMobile_3()}
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				<td>
				  <c:choose>
				  	<c:when test="${dto.getGender() eq 'm'}">
				  		남자
				  	</c:when>
				  	<c:otherwise>
						여자				  	
				  	</c:otherwise>
				 </c:choose>
				</td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <c:if test="${dto.getHobby_travel() eq 'y'}">
					  	여행
					  </c:if>&nbsp;
					  <c:if test="${dto.getHobby_reading() eq 'y'}">
					  	독서
					  </c:if>&nbsp;
					  <c:if test="${dto.getHobby_sports() eq 'y'}">
					  	운동
					  </c:if>
				  </td>
				</tr>
				<tr>
				  <th>회원가입일</th>
				  <td>
					${dto.getReg_date()}
				  </td>
				</tr>
				<tr>
				  <th>정보수정일</th>
				  <td>
					${dto.getUpdate_date()}
				  </td>
				</tr>
				<tr>
				  <th>최근접속일</th>
				  <td>
					${dto.getLast_login_date()}
				  </td>
				</tr>
			  </tbody>
			</table>
			<div class="buttonGroup_center">
				<a href="javascript:goUpdateForm()" class="butt">정보수정</a>
				<a href="javascript:goPasswordChange()" class="butt">비밀번호변경</a>
				<a href="javascript:goExit()" class="butt">회원탈퇴</a>
			</div>	
		</div>	
		<%@ include file="../common_footer.jsp" %>
	</div>	
</body>
</html>






    