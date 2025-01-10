<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../styles/new-match.css">
        <title>New match</title>
    </head>
    <body>
    <div class="panel">
        <form action="${pageContext.request.contextPath}/new-match" method="POST">
            <section class="panel-header">
                <h1>New match</h1>
            </section>
            <section class="first-player">
                <input type="text" id="first-username" name="firstUsername" placeholder="First player's name" required>
            </section>
            <section class="second-player">
                <input type="text" id="second-username" name="secondUsername" placeholder="Second player's name" required>
            </section>
            <button type="submit" class="start-button">Start</button>
        </form>
        <section class="panel-footer">
            <p>© Tennis Scoreboard, project from zhukovsd/java-backend-learning-course roadmap.</p>
        </section>
    </div>
    </body>
</html>
