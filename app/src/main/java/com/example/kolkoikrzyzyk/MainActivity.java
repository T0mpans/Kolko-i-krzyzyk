package com.example.kolkoikrzyzyk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kolkoikrzyzyk.R;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    boolean playerX = true;
    int moveCount = 0;

    int[][] winPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 9; i++) {
            String buttonID = "btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);

            final int index = i;
            buttons[i].setOnClickListener(v -> onButtonClick(index));
        }
    }

    void onButtonClick(int index) {
        if (!buttons[index].getText().toString().equals("")) return;

        buttons[index].setText(playerX ? "O" : "X");
        moveCount++;

        if (checkWin()) {
            Toast.makeText(this,
                    "Wygrywa " + (playerX ? "O" : "X"),
                    Toast.LENGTH_LONG).show();
            resetGame();
            return;
        }

        if (moveCount == 9) {
            Toast.makeText(this, "Remis!", Toast.LENGTH_LONG).show();
            resetGame();
            return;
        }

        playerX = !playerX;
    }

    boolean checkWin() {
        for (int[] pos : winPositions) {
            String a = buttons[pos[0]].getText().toString();
            String b = buttons[pos[1]].getText().toString();
            String c = buttons[pos[2]].getText().toString();

            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                return true;
            }
        }
        return false;
    }

    void resetGame() {
        for (Button button : buttons) {
            button.setText("");
        }
        moveCount = 0;
        playerX = true;
    }
}