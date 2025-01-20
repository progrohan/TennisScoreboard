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
import java.util.Objects;

@WebServlet(name="matches", value = "/matches")
public class FinishedMatchesServlet extends HttpServlet {
    private final int MATCHES_ON_PAGE = 5;
    FinishedMatchesPersistenceService matchesService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        if (page == 0) page = 1;

        String filterByName = req.getParameter("filter_by_player_name");

        List<FinishedMatchDTO> matches;
        int matchesCount;
        int pagesCount;

        if(Objects.equals(filterByName, "")){
            matchesCount = matchesService.count();
            matches = matchesService.findWithPagination((page - 1) * MATCHES_ON_PAGE, MATCHES_ON_PAGE);
        }else{
            matchesCount = matchesService.countWithFilter(filterByName);
            matches = matchesService.findWithFilterAndPagination(filterByName,(page - 1) * MATCHES_ON_PAGE, MATCHES_ON_PAGE);
        }
        pagesCount =(matchesCount + MATCHES_ON_PAGE - 1) / MATCHES_ON_PAGE;


        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", pagesCount);
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/jsp/matches.jsp").forward(req, resp);
    }

}
