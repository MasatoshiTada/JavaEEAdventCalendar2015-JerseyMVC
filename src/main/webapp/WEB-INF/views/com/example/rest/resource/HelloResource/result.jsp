<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>メッセージ表示画面</title>
</head>
<body>
    <c:out value="${messageDto.message}"/>
</body>
</html>
