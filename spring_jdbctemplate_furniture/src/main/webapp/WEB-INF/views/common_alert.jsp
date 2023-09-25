<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//	String msg = (String)request.getAttribute("t_msg");
//	String url = (String)request.getAttribute("t_url");
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	alert("${t_msg}");
	location.href = "${t_url}";
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>