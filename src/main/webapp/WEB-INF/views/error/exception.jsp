<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>例外画面</title>
</head>
<body>
    <h1>例外が発生しました</h1>
    メッセージ：<c:out value="${model}"/>
</body>
</html>
