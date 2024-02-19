<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Form Page</title>
</head>
<body>
<h2>Form Page</h2>
<form action="#" th:action="@{/submit-form}" method="post">

    <p>로그인테스트</p>
    <button type="submit">가입하기</button>
</form>
</body>
</html>