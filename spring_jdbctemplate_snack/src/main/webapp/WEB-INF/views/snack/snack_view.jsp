<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%
	SnackDto dto = (SnackDto)request.getAttribute("t_dto");

%> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK!11 김용석</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goDelete(){
			snack.t_gubun.value="snackDelete";
			snack.method="post";
			snack.action="Snack";
			snack.submit();
		}
		function goUpdateForm(){
			snack.t_gubun.value="goUpdateForm";
			snack.method="post";
			snack.action="Snack";
			snack.submit();
		}
		
	</script>
</head>
<body>
	<div class="container">
	<form name="snack">
			<input type="hidden" name="t_p_code" value="<%=dto.getP_code() %>">
			<input type="hidden" name="t_gubun">
	</form>
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK!11 김용석 SNACK</h1>
		</div>		
		<div class="write_wrap">
		
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>제품번호</th>
							<td class="th_left">
								<%=dto.getP_code() %>
							</td>
						</tr>
						<tr>
							<th>제품명</th>
							<td class="th_left">
								<%=dto.getP_name() %>
							</td>
						</tr>
						<tr>
							<th>가격</th>
							<td class="th_left">
								<%=dto.getPrice() %>
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<%=dto.getM_name() %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='Snack'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdateForm()" value="수정" class="btn_list">
				<input type="button" onClick="goDelete()" value="삭제" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>



















    