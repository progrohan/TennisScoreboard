package prog.rohan.tennis_scoreboard.utils;

import prog.rohan.tennis_scoreboard.exceptions.InvalidDataException;

import java.util.Objects;

public class DataValidator {
    public void checkNamesSameness(String firstUsername, String secondUsername){
        if(Objects.equals(firstUsername, secondUsername))
            throw new InvalidDataException("Names should not be the same!");
    }
}
