package prog.rohan.tennis_scoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.dto.PlayerDTO;
import prog.rohan.tennis_scoreboard.service.OngoingMatchService;
import prog.rohan.tennis_scoreboard.service.PlayerService;
import prog.rohan.tennis_scoreboard.utils.DataValidator;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "new-match", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    PlayerService playerService = new PlayerService();
    OngoingMatchService ongoingMatchService = OngoingMatchService.getINSTANCE();
    DataValidator dataValidator = new DataValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/new-match.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayerName = req.getParameter("firstUsername");
        String secondPlayerName = req.getParameter("secondUsername");
        dataValidator.checkNamesSameness(firstPlayerName, secondPlayerName);

        PlayerDTO firstPlayer = playerService.save(firstPlayerName);
        PlayerDTO secondPlayer = playerService.save(secondPlayerName);

        UUID uuid = ongoingMatchService.startNewMatch(firstPlayer, secondPlayer);

        resp.sendRedirect(req.getContextPath()
                          + "/match-score?uuid="
                          + uuid);
    }
}
