package prog.rohan.tennis_scoreboard.service;

import prog.rohan.tennis_scoreboard.dto.PlayerDTO;
import prog.rohan.tennis_scoreboard.entity.Player;
import prog.rohan.tennis_scoreboard.exceptions.DataNotFoundException;
import prog.rohan.tennis_scoreboard.repository.PlayerRepositrory;


import java.util.Optional;

public class PlayerService {
    PlayerRepositrory playerRepositrory = PlayerRepositrory.getINSTANCE();

    public PlayerDTO save(String name){
        Optional<Player> optionalPlayer = playerRepositrory.findByName(name);

        Player player = optionalPlayer
                .orElseGet(() -> playerRepositrory
                        .save(new Player(null, name)));

        return new PlayerDTO(player.getId(), player.getName());
    }

    public PlayerDTO findById(Long id){
        Optional<Player> optionalPlayer = playerRepositrory.findById(id);
        Player player = optionalPlayer
                .orElseThrow(() -> new DataNotFoundException("Player not found by id!"));
        return new PlayerDTO(player.getId(), player.getName());
    }
}

