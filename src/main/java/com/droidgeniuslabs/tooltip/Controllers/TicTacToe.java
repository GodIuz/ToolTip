package com.droidgeniuslabs.tooltip.Controllers;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe {
    @FXML private GridPane board;
    @FXML private Label playerXScore, playerOScore, drawScore;
    @FXML private ComboBox<String> difficultyComboBox;
    @FXML private ComboBox<String> modeBox;
    @FXML private Button restartButton;
    private final Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private int xScore = 0, oScore = 0, draws = 0;
    private final List<String> mode = List.of("Player vs AI", "Player vs Player");
    private final List<String> difficulty = List.of("Easy", "Medium", "Hard", "Unbeatable");

    public void initialize() {
        difficultyComboBox.getItems().setAll(difficulty);
        difficultyComboBox.getSelectionModel().selectFirst();
        modeBox.getItems().setAll(mode);
        modeBox.getSelectionModel().selectFirst();
        restartButton.setOnAction(e -> resetBoard());
        createBoard();
    }

    private void createBoard() {
        board.getChildren().clear();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button();
                btn.setPrefSize(100, 100);
                btn.setStyle("-fx-font-size: 24; -fx-background-color: #f4f4f4; -fx-border-color: #ccc;");
                final int r = row, c = col;
                btn.setOnAction(e -> handleMove(btn, r, c));
                board.add(btn, col, row);
                buttons[row][col] = btn;
            }
        }
    }

    private void handleMove(Button btn, int row, int col) {
        if (!btn.getText().isEmpty()) return;

        String currentSymbol = playerXTurn ? "X" : "O";
        btn.setText(currentSymbol);
        animateButton(btn);

        if (checkWin(currentSymbol)) {
            if (currentSymbol.equals("X")) xScore++;
            else oScore++;
            updateScores();
            resetBoard();
            return;
        } else if (isBoardFull()) {
            draws++;
            updateScores();
            resetBoard();
            return;
        }

        playerXTurn = !playerXTurn;

        if (!playerXTurn && modeBox.getValue().equals("Player vs AI")) {
            handleAIMove();
        }
    }

    private void animateButton(Button btn) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), btn);
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(1);
        st.setToY(1);
        st.play();
    }

    private void handleAIMove() {
        String difficulty = difficultyComboBox.getValue();
        int[] move = switch (difficulty) {
            case "Easy" -> randomMove();
            case "Medium" -> mediumMove();
            case "Hard" -> hardMove();
            default -> unbeatableMove();
        };

        if (move != null) {
            Button btn = buttons[move[0]][move[1]];
            handleMove(btn, move[0], move[1]);
        }
    }

    private int[] randomMove() {
        List<int[]> available = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().isEmpty()) available.add(new int[]{i, j});
        return available.isEmpty() ? null : available.get(new Random().nextInt(available.size()));
    }

    private int[] mediumMove() {
        int[] winMove = findWinningMove("O");
        if (winMove != null) return winMove;
        int[] blockMove = findWinningMove("X");
        return blockMove != null ? blockMove : randomMove();
    }

    private int[] hardMove() {
        int[] move = findWinningMove("O");
        if (move != null) return move;
        move = findWinningMove("X");
        if (move != null) return move;
        return centerOrCornerMove();
    }

    private int[] unbeatableMove() {
        return minimaxMove();
    }

    private int[] findWinningMove(String symbol) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    buttons[i][j].setText(symbol);
                    boolean win = checkWin(symbol);
                    buttons[i][j].setText("");
                    if (win) return new int[]{i, j};
                }
            }
        return null;
    }

    private int[] centerOrCornerMove() {
        if (buttons[1][1].getText().isEmpty()) return new int[]{1, 1};
        int[][] corners = {{0,0}, {0,2}, {2,0}, {2,2}};
        for (int[] corner : corners)
            if (buttons[corner[0]][corner[1]].getText().isEmpty()) return corner;
        return randomMove();
    }

    private int[] minimaxMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    buttons[i][j].setText("O");
                    int score = minimax(false);
                    buttons[i][j].setText("");
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        if (checkWin("O")) return 1;
        if (checkWin("X")) return -1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    buttons[i][j].setText(isMaximizing ? "O" : "X");
                    int score = minimax(!isMaximizing);
                    buttons[i][j].setText("");
                    bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

    private boolean isBoardFull() {
        for (Button[] row : buttons)
            for (Button btn : row)
                if (btn.getText().isEmpty()) return false;
        return true;
    }

    private boolean checkWin(String symbol) {
        for (int i = 0; i < 3; i++)
            if (buttons[i][0].getText().equals(symbol) && buttons[i][1].getText().equals(symbol) && buttons[i][2].getText().equals(symbol)) return true;
        for (int i = 0; i < 3; i++)
            if (buttons[0][i].getText().equals(symbol) && buttons[1][i].getText().equals(symbol) && buttons[2][i].getText().equals(symbol)) return true;
        if (buttons[0][0].getText().equals(symbol) && buttons[1][1].getText().equals(symbol) && buttons[2][2].getText().equals(symbol)) return true;
	return buttons[0][2].getText().equals(symbol) && buttons[1][1].getText()
			.equals(symbol) && buttons[2][0].getText().equals(symbol);
    }

    private void updateScores() {
        playerXScore.setText(String.valueOf(xScore));
        playerOScore.setText(String.valueOf(oScore));
        drawScore.setText(String.valueOf(draws));
    }

    private void resetBoard() {
        playerXTurn = true;
        createBoard();
    }
}
