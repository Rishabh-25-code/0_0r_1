package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, ZERO, NO
    }
    Player currentPlayer = Player.ONE;
    Player[] playerChoices = new Player[9];


    int[][] winnerArray = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTheGame();
            }
        });

    }

    public void imageViewTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;

        int tagOfTappedImage = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tagOfTappedImage] == Player.NO && gameOver == false) {

        playerChoices[tagOfTappedImage] = currentPlayer;

        if(currentPlayer == Player.ONE) {
            tappedImageView.setTranslationX(-2000f);
            tappedImageView.setImageResource(R.drawable.one);
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(360).setDuration(500);
            currentPlayer = Player.ZERO;
        }
        else if(currentPlayer == Player.ZERO) {
            tappedImageView.setTranslationX(2000f);
            tappedImageView.setImageResource(R.drawable.zero);
            tappedImageView.animate().translationXBy(-2000).alpha(1).rotation(-360).setDuration(500);
            currentPlayer = Player.ONE;
        }

//        Toast.makeText(this,tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();
        for (int[] winnerColumns : winnerArray) {
            if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != Player.NO) {
//                btnReset.setVisibility(View.VISIBLE);
                gameOver = true;
                String winnerOfGame = "";

                if (currentPlayer == Player.ONE) {

                    winnerOfGame = "Zero ";

                } else if (currentPlayer == Player.ZERO) {

                    winnerOfGame = "One ";
                }

                Toast.makeText(this, winnerOfGame + "is Winner",Toast.LENGTH_LONG).show();

            }
//            else{
//                Toast.makeText(this, "no one win", Toast.LENGTH_SHORT).show();
//            }

          }

        }

    }

    private void resetTheGame() {

        for (int index = 0; index < gridLayout.getChildCount(); index++) {

            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }

        currentPlayer = Player.ONE;
        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;

        gameOver = false;

//        btnReset.setVisibility(View.INVISIBLE);

    }

}