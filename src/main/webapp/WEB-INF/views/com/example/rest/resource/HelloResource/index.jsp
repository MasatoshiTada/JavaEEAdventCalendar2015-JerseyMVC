<%@ taglib prefix="mytag" uri="http://example.com/myTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>名前入力画面(相対パス)</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>名前を入力してください</h1>

    <mytag:errors errorClass="error"/>
    <h2>GETでの送信</h2>
    <form method="get" action="./result">
        <%-- value属性は検証エラー時に --%>
        名前：<input type="text" name="name" value="${param['name']}">
        <input type="submit" value="送信">
    </form>

    <hr>

    <h2>POSTでの送信</h2>
    <form method="post" action="./result">
        <%-- value属性は検証エラー時に --%>
        名前：<input type="text" name="name" value="${param['name']}">
        <input type="submit" value="送信">
    </form>

    <a href="./redirect">リダイレクト</a>
</body>
</html>
