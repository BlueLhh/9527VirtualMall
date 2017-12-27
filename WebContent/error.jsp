<%@ page language="java" contentType="text/html; charset=utf-8"
   %>
<!-- 接收struts的标签库 -->   
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>错误页面</title>
</head>
<body>
<!--validate()返回的错误信息 -->
<s:fielderror name="error"></s:fielderror>
这是一个错误页面！！！！！！！！！！！
</body>
</html>