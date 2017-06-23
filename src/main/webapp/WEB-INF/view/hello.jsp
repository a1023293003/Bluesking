<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${message}</h2>
	<form action="/bluesking/hello1" method="post">
		<input type="text" name="name" value="哈哈" />
		<input type="text" name="test" value="lalal" />
		<input type="file" name="file" value="" />
		<input type="submit" value="提交" />
	</form>
</body>
</html>