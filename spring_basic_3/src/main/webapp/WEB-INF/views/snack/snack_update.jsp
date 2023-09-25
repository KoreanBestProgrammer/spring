<%@page import="java.util.ArrayList"%>
<%@page import="dto.SnackDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SnackDto dto = (SnackDto)request.getAttribute("t_dto");
	String m_code = (String)request.getAttribute("t_m_code");
	ArrayList<SnackDto> arr = (ArrayList<SnackDto>)request.getAttribute("t_arr"); 
%>    
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 김용석</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >
	<script type="text/javascript" src="js/common.js"></script>
		
	<script type="text/javascript">
		function goUpdate(code){
		
			if(checkValue(snack.t_p_name,"제품이름을 입력하세요")) return;
			if(checkValue(snack.t_price,"제품가격을 입력하세요")) return;
			
			snack.t_gubun.value="update";
			snack.t_p_code.value=code;
			snack.method="post";
			snack.action="Snack";
			snack.submit();
		}
	</script>
	
</head>
<body>
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 김용석 SNACK</h1>
		</div>		
			<div class="write_wrap">
			<form name="snack">
			<div class="board_list">
			<input type="hidden" name="t_gubun">
			<input type="hidden" name="t_p_code">
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
								<th>가격</th>
								<td class="th_left">
									<input name="t_price" value="<%=dto.getStrprice() %>" class="input_100px" style="text-align:right" type="text">
								</td>
							</tr>
							<tr>
								<th>제조사</th>
								<td class="th_left">
									<select name="t_m_code"  class="select">
									<%for(int k = 0 ; k < arr.size() ; k++){ %>
										<option value="<%=arr.get(k).getM_code()%>" <%if(arr.get(k).getM_code().equals(dto.getM_code())) out.print("selected"); %>><%=arr.get(k).getM_name() %></option>
									<%} %>
									</select>								
									
								</td>
							</tr>
						</tbody>
					</table>
					</div>				
				</form>
				<div class="btn_wrap">
					<input type="button" onClick="location.href='Snack'" value="목록" class="btn_list">
					<input type="button" onClick="goUpdate('<%=dto.getP_code()%>')" value="수정저장" class="btn_ok">&nbsp;&nbsp;
				</div>
			</div>
		</div>
	</div>
</body>
</html>



















    