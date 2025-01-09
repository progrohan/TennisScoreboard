<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/main-page.css">
    <title>Main page</title>
</head>
<body>
<div class="panel">
    <section class="panel-header">
        <h1>Welcome to Tennis Scoreboard!</h1>
        <p>Manage your tennis matches, record results, and track rankings</p>
    </section>
    <a class="new-match-button-link" href="${pageContext.request.contextPath}/new-match">
        <button class="new-match-button">Start new match</button>
    </a>
    <a class="finished-matches-button-link" href="${pageContext.request.contextPath}/matches?page=1">
        <button class="finished-matches-button">See finished matches</button>
    </a>
    <section class="panel-footer">
        <p>© Tennis Scoreboard, project from zhukovsd/java-backend-learning-course roadmap.</p>
    </section>
</div>
</body>
</html>
