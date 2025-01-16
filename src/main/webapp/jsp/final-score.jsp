<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../styles/final-score.css">
    <title>Match Final Score</title>
</head>
<body>
<div class="panel">
    <section class="panel-header">
        <h1>Match Final Score</h1>
    </section>
    <table class="players-table">
        <thead>
        <tr>
            <th>Player</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <tr class="${match.firstPlayerScore.points > match.secondPlayerScore.points ? 'winner-row' : ''}">
            <td class="player-name">${match.firstPlayer.name}</td>
            <td>${match.firstPlayerScore.sets}</td>
            <td>${match.firstPlayerScore.games}</td>
            <td>${match.firstPlayerScore.points}</td>
        </tr>
        <tr class="${match.firstPlayerScore.points < match.secondPlayerScore.points ? 'winner-row' : ''}">
            <td class="player-name">${match.secondPlayer.name}</td>
            <td>${match.secondPlayerScore.sets}</td>
            <td>${match.secondPlayerScore.games}</td>
            <td>${match.secondPlayerScore.points}</td>
        </tr>
        </tbody>
    </table>
    <a href="/" class="back-button">Return to Main Page</a>
    <section class="panel-footer">
        <p>© Tennis Scoreboard, project from zhukovsd/java-backend-learning-course roadmap.</p>
    </section>
</div>
</body>
</html>
