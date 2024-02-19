<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Form Page</title>
</head>
<body>
<h2>Form Page</h2>
<form action="#" th:action="@{/submit-form}" method="post">
    <label for="option1">Option 1:</label>
    <input type="radio" id="option1" name="option" value="option1">
    <label for="option2">Option 2:</label>
    <input type="radio" id="option2" name="option" value="option2">
    <br><br>
    <label for="input1">Input 1:</label>
    <input type="text" id="input1" name="input1" placeholder="Enter input 1">
    <br><br>
    <label for="input2">Input 2:</label>
    <input type="text" id="input2" name="input2" placeholder="Enter input 2">
    <br><br>
    <button type="submit">Submit</button>
</form>
</body>
</html>