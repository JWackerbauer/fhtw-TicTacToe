# TicTacToe — AGENTS.md

## Project structure

Single-module Maven project under `tictactoe/`. All Maven commands must run from that directory.

```
tictactoe/
  pom.xml              # Java 17, JUnit Jupiter 5.11.0
  src/main/java/at/fhtw/tictactoe/   # main sources
  src/test/java/at/fhtw/tictactoe/   # tests
```

- **Group/artifact:** `at.fhtw.tictactoe:tictactoe`
- **Language:** Java 17 (`maven.compiler.release=17`)
- **Testing:** JUnit 5 (Jupiter API + Params), Maven Surefire 3.3.0

## Commands

Run from `tictactoe/`:

| Command | Action |
|---|---|
| `mvn clean compile` | compile main sources |
| `mvn clean test` | run tests |
| `mvn clean package` | build JAR |
| `mvn clean` | clean artifacts |

No lint, formatter, typecheck, or codegen steps configured. No CI workflows present.

## Architecture

Four classes matching the UML class diagram at repo root (`class_diagram.png`):

- **`Player`** — holds a `char marker` (`'X'` / `'O'`)
- **`Board`** — 3×3 `char[][]`, methods: `isCellEmpty`, `place`, `isFull`, `clear`, `print`
- **`TicTacToe`** — controller with `start()` (console game loop using `Scanner`), `switchCurrentPlayer()`, `hasWinner()`, plus file-based methods for CLI mode: `saveState`, `loadState`, `getValidMoves`, `executeMove`
- **`App`** — interactive mode (no args) or CLI mode (arg dispatch)

Win detection checks rows, columns, and both diagonals for three matching markers.

## CLI mode (AI-vs-human play)

State lives in `game_state.json` (hand-written JSON, no library dependency).

Run from `tictactoe/` after `mvn clean package`:

| Command | Action |
|---|---|
| `java -jar target/tictactoe-1.0-SNAPSHOT.jar new` | Reset game (X starts) |
| `java -jar target/tictactoe-1.0-SNAPSHOT.jar state` | Show board, current player, status |
| `java -jar target/tictactoe-1.0-SNAPSHOT.jar valid-moves` | List empty cells as `row col` |
| `java -jar target/tictactoe-1.0-SNAPSHOT.jar move <row> <col>` | Place marker for current player |

### Session protocol

1. AI runs `new` → resets state
2. AI runs `state` → shows initial board, prompts human
3. Human runs `move <row> <col>` in their terminal
4. AI runs `state` → sees updated board and current player
5. AI runs `valid-moves` (optional) → sees options
6. AI runs `move <row> <col>` → AI makes its move
7. Loop to step 2 until status != `"playing"`

The AI plays as X, the human plays as O. The AI never makes a human's move. Each player runs `move` for themselves. The state file is the shared source of truth.

## Tests

| Class | Test file | Tests |
|---|---|---|
| `Player` | `PlayerTest` | 2 |
| `Board` | `BoardTest` | 6 |
| `TicTacToe` | `TicTacToeTest` | 17 |
| `App` | `AppTest` | 1 |

Run a single test class: `mvn test -Dtest=PlayerTest`

## Notes

- No Maven wrapper committed (`.mvn/wrapper/maven-wrapper.jar` is gitignored); requires system `mvn`.
- No special environment setup needed. No test fixtures, no integration tests, no flaky suites.
- `App.start()` (interactive mode) uses `Scanner(System.in)` — not covered by unit tests.
- No JSON library; file format is hand-written/parsed in `TicTacToe.saveState`/`loadState`.
