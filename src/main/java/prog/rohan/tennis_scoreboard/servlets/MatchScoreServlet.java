package prog.rohan.tennis_scoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prog.rohan.tennis_scoreboard.dto.FinishedMatchDTO;
import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.mapper.MatchMapper;
import prog.rohan.tennis_scoreboard.service.FinishedMatchesPersistenceService;
import prog.rohan.tennis_scoreboard.service.MatchScoreCalculationService;
import prog.rohan.tennis_scoreboard.service.OngoingMatchService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "match-score", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchService ongoingMatchService = OngoingMatchService.getINSTANCE();
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));

        OngoingMatchDTO ongoingMatch = ongoingMatchService.getByUUId(uuid);

        req.setAttribute("ongoingMatch", ongoingMatch);
        req.getRequestDispatcher("/jsp/match-score.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Long winnerId = Long.valueOf(req.getParameter("playerId"));

        OngoingMatchDTO ongoingMatch = ongoingMatchService.getByUUId(uuid);

        ongoingMatch = matchScoreCalculationService.increaseScore(ongoingMatch, winnerId);

        if(matchScoreCalculationService.isMatchOver(ongoingMatch)){
            FinishedMatchDTO finishedMatch = MatchMapper.INSTANCE.toFinished(ongoingMatch);
            finishedMatchesPersistenceService.save(finishedMatch);
            req.setAttribute("match", ongoingMatch);
            ongoingMatchService.deleteMatch(uuid);
            req.getRequestDispatcher("/jsp/final-score.jsp").forward(req, resp);
        }else{
            req.setAttribute("ongoingMatch", ongoingMatch);
            req.getRequestDispatcher("/jsp/match-score.jsp").forward(req, resp);
        }
    }
}
