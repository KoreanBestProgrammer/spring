<%@page import="dto.Member_dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="/sts/css/common.css" rel="stylesheet">
	<link href="/sts/css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goUpdateForm(id){
			mem.t_gubun.value="updateform";
			mem.t_id.value=id;
			mem.method="post";
			mem.action="Member";
			mem.submit();
		}
		function goDelete(id){
			if(confirm("증말로 삭제?")){
				mem.t_gubun.value="delete";
				mem.t_id.value=id;
				mem.method="post";
				mem.action="Member";
				mem.submit();
			}
		}
	</script>
</head>
<body>
	<div class="container">
	
		<div class="leftmargin">
			<img src="/sts/images/jsl_logo.png"><h1>TRACK11 홍길동 회원관리</h1>
		</div>		
		<div class="write_wrap">
		<form name="mem">
			<input type="hidden" name="t_id">
			<input type="hidden" name="t_gubun">
		</form>
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>ID</th>
							<td class="th_left">
								${t_dto.getId()}
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								${t_dto.getName()}
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								${t_dto.getAge()}
							</td>
						</tr>
						<tr>
							<th>가입일</th>
							<td class="th_left">
								${t_dto.getReg_date()}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='Member'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdateForm('${t_dto.getId()}')" value="수정" class="btn_list">
				<input type="button" onClick="goDelete('${t_dto.getId()}')" value="삭제" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>



















    