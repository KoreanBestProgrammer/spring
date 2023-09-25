<%@page import="dto.SnackDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<SnackDto> arr = (ArrayList<SnackDto>)request.getAttribute("t_arr");
	String select = (String)request.getAttribute("t_select");
	String search = (String)request.getAttribute("t_search");
%>	
<!DOCTYPE html>
<html> 
<head>
<script>
	function goWriteForm(){
		snack.t_gubun.value="write";
		snack.method="post";
		snack.action="Snack";
		snack.submit();
	}
	function goSearch(){
		list.method="post";
		list.action="Snack";
		list.submit();
	}
	function goView(code){
		snack.t_gubun.value="view";
		snack.t_p_code.value=code;
		snack.method="post";
		snack.action="Snack";
		snack.submit();
	}
</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : 풀스텍 홍길동
	******************************************** 
 -->	
	<title>TRACK11 홍길동</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	
</head>
<body>
	<div class="container">
	
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 김용석 SNACK</h1>
		</div>		
		<form name="snack">
			<input type="hidden" name="t_gubun">
			<input type="hidden" name="t_p_code">
		</form>
		<form name="list">
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span><%=arr.size()%></span>건</p>
			</div>
			<div class="search_group">
				<select class="select" name="t_select">
					<option value="p_name" <%if(select.equals("p_name")) out.print("selected");%>>제품명</option>
					<option value="m_name" <%if(select.equals("m_name")) out.print("selected");%>>제조사명</option>
				</select>
				<input type="text" name="t_search" value="<%=search %>" class="search_word">
				<button class="btn_search" onclick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</div>
		</div>
		</form>
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
			<%for(int k = 0 ; k < arr.size() ; k++) {%>
				<tr>
					<td><a href="javascript:goView('<%=arr.get(k).getP_code()%>')"><%=arr.get(k).getP_code()%></a></td>
					<td><%=arr.get(k).getP_name()%></td>
					<td><%=arr.get(k).getM_name()%></td>
					<td><%=arr.get(k).getStrprice()%></td>
				</tr>	
			<% }%>
			</tbody>
		</table>
		<div class="paging">
			<a href="javascript:goWriteForm()" class="write">제품등록</a>
		</div>
	</div>
 </body>
</html>







    