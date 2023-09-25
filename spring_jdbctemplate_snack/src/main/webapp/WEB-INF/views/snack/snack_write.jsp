<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="dto.*, dao.*,java.util.*" %>
<%
	ArrayList<SnackDto> arr = (ArrayList<SnackDto>)request.getAttribute("t_arr"); 


%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK!11 김용석</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >		
	<script type="text/javascript">
		function goSave(){			
			if(snack.t_p_code.value == ""){
				alert("제품코드를 입력하시오");
				snack.t_p_code.focus();
				return;
			}
			if(snack.t_p_name.value == ""){
				alert("제품명을 입력하시오");
				snack.t_p_name.focus();
				return;
			}
			if(snack.t_price.value == ""){
				alert("제품가격을 입력하시오");
				snack.t_price.focus();
				return;
			}		
			
			snack.t_gubun.value="snackSave";
			snack.method="post";
			snack.action="Snack";
			snack.submit();
		}	
		
	</script>
	<style>
		.inputRight{
			text-align:right;
		}
	</style>
</head>
<body>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 김용석 SNACK</h1>
		</div>		
		<div class="write_wrap">
			<form name="snack"> 
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
								<input name="t_price"  class="input_100px inputRight" type="text">
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<select name="t_m_code" class="select">	
							<%for(SnackDto dto : arr){ %>                              	                               	
									<option value="<%=dto.getM_code()%>"><%=dto.getM_name() %></option>
							<% } %>	
                                 
																										
								</select>								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="등록" onclick="goSave()" class="btn_ok">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="location.href='Snack'" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>








    