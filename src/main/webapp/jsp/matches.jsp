<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Played Matches</title>
    <link rel="stylesheet" href="../styles/matches.css">
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h1>Played Matches</h1>
    </div>
    <!-- Форма фильтрации -->
    <form method="GET" action="/matches" class="filter-form">
        <input type="text" name="filter_by_player_name" placeholder="Enter player name" value="${param.filter_by_player_name}" />
        <button type="submit">Search</button>
    </form>

    <!-- Таблица с матчами -->
    <table class="matches-table">
        <thead>
        <tr>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Winner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.firstPlayer.name}</td>
                <td>${match.secondPlayer.name}</td>
                <td>${match.winner.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Пагинация -->
    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="/matches?page=${currentPage - 1}&filter_by_player_name=${param.filter_by_player_name}" class="page-button">Previous</a>
        </c:if>
        <span>Page ${currentPage} of ${totalPages}</span>
        <c:if test="${currentPage < totalPages}">
            <a href="/matches?page=${currentPage + 1}&filter_by_player_name=${param.filter_by_player_name}" class="page-button">Next</a>
        </c:if>
    </div>

    <div class="panel-footer">
        <p>© Tennis Scoreboard, project from zhukovsd/java-backend-learning-course roadmap.</p>
    </div>
</div>
</body>
</html>
