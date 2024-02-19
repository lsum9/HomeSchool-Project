<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Form Page</title>
</head>
<body>
<h2>Form Page</h2>
<form action="#" th:action="@{/submit-form}" method="post">

    <input type="radio" id="userType1" name="userType">
    <label for="userType1">선생님</label>

    <input type="radio" id="userType2" name="userType">
    <label for="userType2">학생</label>
    <br><br>
    <label for="userName">이름</label>
    <input type="text" id="userName" name="userName" th:value="${loginUserDto.userName}" readonly>
    <br><br>
    <label for="profileText">자기소개</label>
    <textarea id="profileText" name="profileText" placeholder="자기소개를 입력하세요" rows="4" cols="50"></textarea>
    <br><br>
    <button type="submit">가입하기</button>
</form>
</body>
</html>