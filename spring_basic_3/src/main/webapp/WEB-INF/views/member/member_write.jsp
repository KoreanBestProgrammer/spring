<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="/sts/css/common.css" rel="stylesheet">
	<link href="/sts/css/layout.css" rel="stylesheet" >		
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript">
		function goSave(){
			if(checkValue(mem.t_id,"아이디를 입력하세요")) return;
			if(checkValue(mem.t_name,"이름을 입력하세요")) return;
			if(checkValue(mem.t_age,"나이를 입력하세요")) return;
			if(checkValue(mem.t_reg_date,"가입일을 입력하세요")) return;
			
			mem.method="post";
			mem.action="memberSave";
			mem.submit();
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
								<input name="t_id"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<input name="t_name"  class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								<input name="t_age"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>가입일</th>
							<td class="th_left">
								<input name="t_reg_date"  class="input_200px" type="date">							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="등록" onclick="goSave()" class="btn_ok">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="location.href='memberList'" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>



















    