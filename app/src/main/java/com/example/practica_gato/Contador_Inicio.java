package com.example.practica_gato;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contador_Inicio extends AppCompatActivity implements View.OnClickListener {
    private TextView PlayerOneScore, PlayerTwoScore, PlayerStatus;
    private Button[] buttons = new Button[9];
    private Button Reset, PlayAgain;

    private int PlayerOneScoreCount, PlayerTwoScoreCount;
    private boolean PlayerOneActive = true;
    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] WinningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_inicio);

        PlayerOneScore = findViewById(R.id.ScoreOne);
        PlayerTwoScore = findViewById(R.id.ScoreTwo);
        PlayerStatus = findViewById(R.id.TextStatus);
        Reset = findViewById(R.id.ResetGame);
        PlayAgain = findViewById(R.id.PlayAgain);

        buttons[0] = findViewById(R.id.btn1);
        buttons[1] = findViewById(R.id.btn2);
        buttons[2] = findViewById(R.id.btn3);
        buttons[3] = findViewById(R.id.btn4);
        buttons[4] = findViewById(R.id.btn5);
        buttons[5] = findViewById(R.id.btn6);
        buttons[6] = findViewById(R.id.btn7);
        buttons[7] = findViewById(R.id.btn8);
        buttons[8] = findViewById(R.id.btn9);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(this);
        }

        PlayerOneScoreCount = 0;
        PlayerTwoScoreCount = 0;
        rounds = 0;

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayAgain();
                PlayerOneScoreCount = 0;
                PlayerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });

        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayAgain();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        } else if (checkWinner()) {
            return;
        }

        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int GameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1)) - 1;

        if (PlayerOneActive) {
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#FFFFFF"));
            gameState[GameStatePointer] = 0;
        } else {
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#000000"));
            gameState[GameStatePointer] = 1;
        }
        rounds++;

        if (checkWinner()) {
            if (PlayerOneActive) {
                PlayerOneScoreCount++;
                updatePlayerScore();
                PlayerStatus.setText("El jugador 1 ganó la ronda!");
            } else {
                PlayerTwoScoreCount++;
                updatePlayerScore();
                PlayerStatus.setText("El jugador 2 ganó la ronda!");
            }
        } else if (rounds == 9) {
            PlayerStatus.setText("Empate!");
        } else {
            PlayerOneActive = !PlayerOneActive;
        }
    }

    private boolean checkWinner() {
        for (int[] positions : WinningPositions) {
            if (gameState[positions[0]] != 2 &&
                    gameState[positions[0]] == gameState[positions[1]] &&
                    gameState[positions[1]] == gameState[positions[2]]) {
                return true;
            }
        }
        return false;
    }

    private void PlayAgain() {
        rounds = 0;
        PlayerOneActive = true;
        for (int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
        PlayerStatus.setText("Status");
    }

    private void updatePlayerScore() {
        PlayerOneScore.setText(Integer.toString(PlayerOneScoreCount));
        PlayerTwoScore.setText(Integer.toString(PlayerTwoScoreCount));
    }
}
