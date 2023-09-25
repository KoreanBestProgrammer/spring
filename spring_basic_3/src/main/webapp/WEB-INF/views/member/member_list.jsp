<%@page import="dto.Member_dto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%
	ArrayList<Member_dto> arr = (ArrayList<Member_dto>)request.getAttribute("t_arr");
	String select = (String)request.getAttribute("t_select");
	String search = (String)request.getAttribute("t_search");
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
	<title>TRACK11 홍길동</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="/sts/css/common.css" rel="stylesheet">
	<link href="/sts/css/layout.css" rel="stylesheet" >	
	<script>
		function goSearch(){
			mem.method="post";
			mem.action="memberList";
			mem.submit();
		}
		
		function goView(id){
			view.t_id.value=id;
			view.method="post";
			view.action="memberView";
			view.submit();
		}
	</script>
</head>
<body>
	<div class="container">

		<div class="leftmargin">
			<img src="/sts/images/jsl_logo.png"><h1>TRACK11 홍길동 회원관리</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span><%=arr.size()%></span>건</p>
			</div>
			<form name="view">
				<input type="hidden" name="t_id">
			</form>
			<form name="mem">
			<div class="search_group">
				<select class="select" name="t_select">
					<option value="id" <%if(select.equals("id")) out.print("selected");%>>ID</option>
					<option value="name" <%if(select.equals("name")) out.print("selected");%>>성명</option>
				</select>
				<input type="text" name="t_search" value="<%=search%>" class="search_word">
				<button onclick="goSearch()" class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
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
					<th>ID</th>
					<th>성명</th>
					<th>나이</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
			<%for(int k = 0 ; k < arr.size() ; k++) { %>
				<tr>
					<td><a href="javascript:goView('<%=arr.get(k).getId() %>')"><%=arr.get(k).getId() %></a></td>
					<td><%=arr.get(k).getName() %></td>
					<td><%=arr.get(k).getAge() %></td>
					<td><%=arr.get(k).getReg_date() %></td>
				</tr>	
			<%} %>
			</tbody>
		</table>
		<div class="paging">
			<a href="memberWrite" class="write">회원등록</a>
		</div>
	</div>
 </body>
</html>







    