<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
		<title>错误页面</title>
		<link rel="icon" href="img/mst.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="img/mst.ico" type="image/x-icon" />
 
<script type="text/javascript">
  
 
setTimeout("javascript:location.href='${pageContext.request.contextPath}/login.jsp'", 3000);
</script>
 
</head>
<body>
<div align="center">
    <br/>
    <br/>
    <br/>
    <h2>${error}</h2>
   <!--  http://121.40.29.64:8081/SSM/login.jsp -->
    <h4><a href="${pageContext.request.contextPath}/login.jsp" >立即跳转</a></h4>
   
</div>
  
</body>
</html>
