<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="stylesheet" href="../styles/match-score.css">
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h1>Current Match Score</h1>
    </div>
    <table class="players-table">
        <thead>
        <tr>
            <th>Player</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr class="player-row">
            <td class="player-name">${ongoingMatch.firstPlayer.name}</td>
            <td>${ongoingMatch.firstPlayerScore.sets}</td>
            <td>${ongoingMatch.firstPlayerScore.games}</td>
            <td>${ongoingMatch.firstPlayerScore.points}</td>
            <td>
                <form action="/match-score" method="POST">
                    <input type="hidden" name="uuid" value="${ongoingMatch.uuid}">
                    <input type="hidden" name="playerId" value="${ongoingMatch.firstPlayer.id}">
                    <button type="submit" class="score-button">+ Point</button>
                </form>
            </td>
        </tr>
        <tr class="player-row">
            <td class="player-name">${ongoingMatch.secondPlayer.name}</td>
            <td>${ongoingMatch.secondPlayerScore.sets}</td>
            <td>${ongoingMatch.secondPlayerScore.games}</td>
            <td>${ongoingMatch.secondPlayerScore.points}</td>
            <td>
                <form action="/match-score" method="POST">
                    <input type="hidden" name="uuid" value="${ongoingMatch.uuid}">
                    <input type="hidden" name="playerId" value="${ongoingMatch.secondPlayer.id}">
                    <button type="submit" class="score-button">+ Point</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="panel-footer">
        <p>© Tennis Scoreboard, project from zhukovsd/java-backend-learning-course roadmap</p>
    </div>
</div>
</body>
</html>
