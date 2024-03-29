<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Form Page</title>
</head>
<body>
<h2>Form Page</h2>
<form action="sign-up" th:action="@{/sign-up}" method="post">
    <input type="radio" id="userType1" name="userType" th:value="t">
    <label for="userType1">선생님</label>

    <input type="radio" id="userType2" name="userType" th:value="s">
    <label for="userType2">학생</label>
    <br><br>
    <label for="userId">아이디</label>
    <input type="text" id="userId" name="userId" th:value="${signDto.userId}" readonly>
    <label for="profileName">이름</label>
    <input type="text" id="profileName" name="profileName" th:value="${signDto.profileName}" readonly>
    <br><br>
    <label for="profileText">자기소개</label>
    <textarea id="profileText" name="profileText" placeholder="자기소개를 입력하세요" rows="4" cols="50"></textarea>
    <br><br>
    <button type="submit">가입하기</button>
</form>
</body>
</html>