<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	
	<link href="/sts/css/common.css" rel="stylesheet">
	<link href="/sts/css/layout.css" rel="stylesheet">	
	<script type="text/javascript" src="js/common.js"></script>	
	
	<script type="text/javascript">
		function goSave(){
			if(checkValue(snack.t_p_code,"제품코드를 입력하세요")) return;
			if(checkValue(snack.t_p_name,"제품이름을 입력하세요")) return;
			if(checkValue(snack.t_price,"제품가격을 입력하세요")) return;
			
			snack.t_gubun.value="save";
			snack.method="post";
			snack.action="Snack";
			snack.submit();
		}
	</script>
	
</head>
<body>
	<div class="container">

		<div class="leftmargin">
			<img src="/sts/images/jsl_logo.png"><h1>TRACK11 김용석 SNACK</h1>
		</div>		
		
		<div class="write_wrap">
			
			<div class="board_list">
			<form name="snack">
			<input type="hidden" name="t_gubun">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>제품번호</th>
							<td class="th_left">
								<input name="t_p_code"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>제품명</th>
							<td class="th_left">
								<input name="t_p_name"  class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>가격</th>
							<td class="th_left">
								<input name="t_price"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<select name="t_m_code" class="select">
								<c:forEach items="${t_arr}" var="dto">
									<option value="${dto.getM_code()}">${dto.getM_name()}</option>
									
								</c:forEach>	
								</select>								
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="등록" class="btn_ok" onclick="goSave()">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="location.href='Snack'" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>



















    