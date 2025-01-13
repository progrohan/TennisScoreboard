
CREATE TABLE Players (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         Name VARCHAR(255) NOT NULL
);

CREATE TABLE Matches (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         Player1 INT NOT NULL,
                         Player2 INT NOT NULL,
                         Winner INT NOT NULL,
                         FOREIGN KEY (Player1) REFERENCES Players(ID),
                         FOREIGN KEY (Player2) REFERENCES Players(ID),
                         FOREIGN KEY (Winner) REFERENCES Players(ID)
);


INSERT INTO Players (Name) VALUES
                               ('Alice'),
                               ('Bob'),
                               ('Charlie'),
                               ('Diana');


INSERT INTO Matches (Player1, Player2, Winner) VALUES
                                                   (1, 2, 1), -- Матч между Alice и Bob, победила Alice
                                                   (1, 3, 3), -- Матч между Alice и Charlie, победил Charlie
                                                   (2, 3, 2), -- Матч между Bob и Charlie, победил Bob
                                                   (2, 4, 4), -- Матч между Bob и Diana, победила Diana
                                                   (3, 4, 3); -- Матч между Charlie и Diana, победил Charlie


