# TicTacToe

This is a TicTacToe game for the FHTW Software Lifecylcle Tooling project

## User Stories

- As a player, I want to be able to make a move by choosing an empty square, so that I can place my symbol on the board.
- As a player, I want to be able to see the current state of the game, so that I can keep track of the moves made by both myself and my opponent.
- As a player, I want to be notified when the game has ended in a win, loss or draw, so that I can see the result of the game.
- As a player, I want to be able to start a new game after the current game has ended, so that I can play again.

## Class Diagram

![TicTacToe Game Class Diagram](class_diagram.png "TicTacToe Game Class Diagram")

## Build & Run

All commands run from the `tictactoe/` directory. Requires Java 17 and Maven.

| Command | Action |
|---|---|
| `mvn clean compile` | Compile the project |
| `mvn clean test` | Run all tests |
| `mvn clean package` | Build JAR (`target/tictactoe-1.0-SNAPSHOT.jar`) |
| `java -jar target/tictactoe-1.0-SNAPSHOT.jar` | Run the game |

## Example Output

```
Current Player: X
▁▁▁▁▁▁
| | | |
| | | |
| | | |
▔▔▔▔
row (0-2): 1 (human input)
column (0-2): 1 (human input)
Current Player: O
▁▁▁▁▁▁
| | | |
| |X| |
| | | |
▔▔▔▔
rom (0-2): 1 (human input)
column (0-2): 0 (human input)
Current Player: X
▁▁▁▁▁▁
| | | |
|O|X| |
| | | |
▔▔▔▔
```