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

Three classes matching the UML class diagram at repo root (`class_diagram.png`):

- **`Player`** — holds a `char marker` (`'X'` / `'O'`)
- **`Board`** — 3×3 `char[][]`, methods: `isCellEmpty`, `place`, `isFull`, `clear`, `print`
- **`TicTacToe`** — controller with `start()` (console game loop using `Scanner`), `switchCurrentPlayer()`, `hasWinner()`

`App.main()` creates a `TicTacToe` instance and calls `start()`. The game reads row/col from stdin.

Win detection checks rows, columns, and both diagonals for three matching markers.

## Tests

| Class | Test file | Tests |
|---|---|---|
| `Player` | `PlayerTest` | 2 |
| `Board` | `BoardTest` | 6 |
| `TicTacToe` | `TicTacToeTest` | 9 |
| `App` | `AppTest` | 1 |

Run a single test class: `mvn test -Dtest=PlayerTest`

## Notes

- No Maven wrapper committed (`.mvn/wrapper/maven-wrapper.jar` is gitignored); requires system `mvn`.
- No special environment setup needed. No test fixtures, no integration tests, no flaky suites.
- `App.start()` uses `Scanner(System.in)` — interactive; not covered by unit tests.
