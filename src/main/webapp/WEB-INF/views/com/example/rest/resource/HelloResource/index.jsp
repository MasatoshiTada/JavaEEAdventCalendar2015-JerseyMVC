<%@ taglib prefix="mytag" uri="http://example.com/myTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>名前入力画面</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
    <h1>名前を入力してください</h1>

    <mytag:errors errorClass="error"/>

    <form method="get" action="./result">
        名前：<input type="text" name="name">
    </form>
</body>
</html>
