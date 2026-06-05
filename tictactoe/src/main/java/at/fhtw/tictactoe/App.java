package at.fhtw.tictactoe;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private static final String STATE_FILE = Paths.get(
        App.class.getProtectionDomain().getCodeSource().getLocation().getPath()
    ).getParent().getParent().resolve("game_state.json").toString();

    public static void main(String[] args) {
        if (args.length == 0) {
            new TicTacToe().start();
            return;
        }

        try {
            switch (args[0]) {
                case "new":
                    doNew();
                    break;
                case "state":
                    doState();
                    break;
                case "valid-moves":
                    doValidMoves();
                    break;
                case "move":
                    doMove(args);
                    break;
                default:
                    System.out.println("Usage: java -jar tictactoe.jar [new|state|valid-moves|move <row> <col>]");
                    break;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format. Usage: java -jar tictactoe.jar move <row> <col>");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing arguments. Usage: java -jar tictactoe.jar move <row> <col>");
        }
    }

    private static void doNew() throws IOException {
        TicTacToe game = new TicTacToe();
        game.saveState(STATE_FILE);
        game.getBoard().print();
        System.out.println("Current Player: " + game.getCurrentPlayer().getMarker());
        System.out.println("Status: " + game.getStatus());
    }

    private static void doState() throws IOException {
        TicTacToe game = TicTacToe.loadState(STATE_FILE);
        game.getBoard().print();
        System.out.println("Current Player: " + game.getCurrentPlayer().getMarker());
        System.out.println("Status: " + game.getStatus());
    }

    private static void doValidMoves() throws IOException {
        TicTacToe game = TicTacToe.loadState(STATE_FILE);
        List<int[]> moves = game.getValidMoves();
        for (int[] m : moves) {
            System.out.println(m[0] + " " + m[1]);
        }
    }

    private static void doMove(String[] args) throws IOException {
        if (args.length < 3) {
            System.err.println("Usage: java -jar tictactoe.jar move <row> <col>");
            return;
        }
        int row = Integer.parseInt(args[1]);
        int col = Integer.parseInt(args[2]);
        TicTacToe game = TicTacToe.loadState(STATE_FILE);
        String status = game.executeMove(row, col);
        game.saveState(STATE_FILE);
        game.getBoard().print();
        System.out.println("Current Player: " + game.getCurrentPlayer().getMarker());
        System.out.println("Status: " + status);

        if (!status.equals("playing")) {
            if (status.equals("draw")) {
                System.out.println("It's a draw!");
            } else {
                System.out.println("Player " + status.charAt(0) + " wins!");
            }
        }
    }
}
