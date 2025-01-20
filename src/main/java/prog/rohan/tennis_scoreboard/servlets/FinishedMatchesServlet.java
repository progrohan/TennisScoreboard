package prog.rohan.tennis_scoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prog.rohan.tennis_scoreboard.dto.FinishedMatchDTO;
import prog.rohan.tennis_scoreboard.service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.util.List;

@WebServlet(name="matches", value = "/matches")
public class FinishedMatchesServlet extends HttpServlet {
    private final int MATCHES_ON_PAGE = 5;
    FinishedMatchesPersistenceService matchesService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        String filterByName = req.getParameter("filter_by_player_name");

        int matchesCount = matchesService.count();
        int pagesCount = matchesCount / MATCHES_ON_PAGE;
        List<FinishedMatchDTO> matches = matchesService.findWithPagination(page * MATCHES_ON_PAGE + 1, MATCHES_ON_PAGE);
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/jsp/matches.jsp").forward(req, resp);
    }

}