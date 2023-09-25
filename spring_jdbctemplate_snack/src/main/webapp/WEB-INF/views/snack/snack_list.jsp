<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*,java.util.*" %>
<%
	ArrayList<SnackDto> arr = (ArrayList<SnackDto>)request.getAttribute("t_arr");
	String select = (String)request.getAttribute("t_select");
	String search = (String)request.getAttribute("t_search");
	String m_code = (String)request.getAttribute("t_m_code");
	ArrayList<SnackDto> arr2 = (ArrayList<SnackDto>)request.getAttribute("t_arr2");
%>    
<!DOCTYPE html>
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : 풀스텍 홍길동
	******************************************** 
 -->	
	<title>TRACK!11 김용석</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
</head>
<script>
function goSearch(){
	snack.method="post";
//	snack.action="SnackList";
	snack.action="Snack";
	snack.submit();
}
function goView(no){
	view.t_gubun.value="snackView";
	view.t_p_code.value=no;
	view.method="post";
	//view.action="SnackView";
	view.action="Snack";
	view.submit();
}
function goWriteForm(){
	view.t_gubun.value="writeForm";
	view.method="post";
	view.action="Snack";
	view.submit();
}
</script>
<body>
	<div class="container">
	<form name="view">
		<input type="hidden" name="t_p_code" >
		<input type="hidden" name="t_gubun">
	</form>
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK!11 김용석 SNACK</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span><%=arr.size() %></span>건</p>
			</div>
			<form name="snack">
			<div class="search_group">
				<select name="t_select" class="select">
					<option value="h.p_name" <% if(select.equals("h.p_name")) out.print("selected"); %>>제품명</option>
					<option value="h.p_code" <% if(select.equals("h.p_code")) out.print("selected"); %>>제품번호</option>
				</select>
				<input type="text" name="t_search" value="<%=search %>" class="search_word">
				<button onclick="goSearch()" class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
				<div>
					<input type="radio" name="t_m_code" value="0" <%if(m_code.equals("0")) out.print("checked"); %>>전체&nbsp;&nbsp;&nbsp;&nbsp;
					<%for(SnackDto dto : arr2){ %>
						<input type="radio" value="<%=dto.getM_code() %>" name="t_m_code" <%if(m_code.equals(dto.getM_code())) out.print("checked"); %>><%=dto.getM_name() %>&nbsp;&nbsp;&nbsp;&nbsp;
					<%} %>
				</div>
			</div>
			</form>
		</div>
	</div>
	<div class="board_list">
		<table class="board_table">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<thead>
				<tr>
					<th>제품번호</th>
					<th>제품명</th>
					<th>제조사명</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
			<% for(SnackDto dto : arr){ %>
				<tr>
					<td><a href="javascript:goView('<%=dto.getP_code() %>')"><%=dto.getP_code() %></a></td>
					<td><a href="javascript:goView('<%=dto.getP_code() %>')"><%=dto.getP_name() %></a></td>
					<td><%=dto.getM_name() %></td>
					<td><%=dto.getPrice() %></td>
				</tr>	
			<% } %>	
			
			</tbody>
		</table>
		<div class="paging">
			<!--  <a href="SnackWrite" class="write">제품등록</a> -->
			<a href="javascript:goWriteForm()" class="write">제품등록</a>
		</div>
	</div>
 </body>
</html>







    