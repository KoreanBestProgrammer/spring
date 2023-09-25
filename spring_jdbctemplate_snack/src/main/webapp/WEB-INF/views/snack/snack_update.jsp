<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*,java.util.*" %>
<%
	SnackDto dto = (SnackDto)request.getAttribute("t_dto");
	ArrayList<SnackDto> arr = (ArrayList<SnackDto>)request.getAttribute("t_arr");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK!11 김용석</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goUpdate(){
			snack.t_gubun.value="snackUpdate";
			snack.method="post";
			snack.action="Snack";
			snack.submit();
		}
	</script>
	<style>
		.input_100px{
			text-align:right;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK!11 김용석 SNACK</h1>
		</div>		
		<div class="write_wrap">
		<form name="snack">
			<input type="hidden" name="t_p_code" value="<%=dto.getP_code() %>">
			<input type="hidden" name="t_gubun">
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
								<input name="t_p_name" value="<%=dto.getP_name() %>" class="input_300px" type="text">
							</td>
						</tr>
						<tr>
						 <% 
						 	String strPrice = "";
						 	if(dto.getPrice() != null){
							 	strPrice = dto.getPrice();
						 	}
								strPrice = strPrice.replaceAll(",", "");
								strPrice = strPrice.replaceAll(" ", "");							 						 
						 %>
							<th>가격</th>
							<td class="th_left">
								<input name="t_price" value="<%=strPrice%>" class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<select name="t_m_code" class="select">
								<% for(SnackDto dt : arr) { %>
									<option value="<%=dt.getM_code()%>" <%if(dt.getM_code().equals(dto.getM_code())) out.print("selected");%>><%=dt.getM_name() %></option>
								<% } %>
								</select>								
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='Snack'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdate()" value="수정저장" class="btn_ok">&nbsp;&nbsp;
			</div>
		</div>
	</div>
</body>
</html>



















    