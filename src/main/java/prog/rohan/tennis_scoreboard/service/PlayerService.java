package prog.rohan.tennis_scoreboard.service;

import prog.rohan.tennis_scoreboard.dto.PlayerDTO;

public class PlayerService {
    //PlayerDAO playerDAO = PlayerDAO.getInstance();

    public PlayerDTO saveIfNotExists(String name){
        PlayerDTO playerResponseDTO = new PlayerDTO(0L,name);
        // Player player = PlayerDAO.saveIfNotExists(name)
        //playerResponseDTO = map Player -> PlayerResponseDTO

        return playerResponseDTO;
    }

}
