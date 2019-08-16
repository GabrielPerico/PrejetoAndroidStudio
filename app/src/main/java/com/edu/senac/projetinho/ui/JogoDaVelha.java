package com.edu.senac.projetinho.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.JogoDaVelhaHelper;

public class JogoDaVelha extends AppCompatActivity {

    String msg;
    boolean end = false, x = false, XA = true, XB = true, XC = true, YA = true, YB = true, YC = true, ZA = true, ZB = true, ZC = true;
    int i = 0,nxwin,nowin;
    int[] tabuleiro = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_da_velha);

    }

    public void changeImg(View v) {
        ImageView change = findViewById(v.getId());
        if (!end) {
            switch (v.getId()) {
                case (R.id.xa):
                    if (XA) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        XA = false;
                        tabuleiro[0] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.xb):
                    if (XB) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        XB = false;
                        tabuleiro[1] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.xc):
                    if (XC) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        XC = false;
                        tabuleiro[2] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.ya):
                    if (YA) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        YA = false;
                        tabuleiro[3] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.yb):
                    if (YB) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        YB = false;
                        tabuleiro[4] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.yc):
                    if (YC) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        YC = false;
                        tabuleiro[5] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.za):
                    if (ZA) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        ZA = false;
                        tabuleiro[6] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.zb):
                    if (ZB) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        ZB = false;
                        tabuleiro[7] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
                case (R.id.zc):
                    if (ZC) {
                        change.setImageResource(x ? R.drawable.x_icon : R.drawable.o_icon);
                        ZC = false;
                        tabuleiro[8] = x ? 1 : 2;
                        x = !x;
                        i = i + 1;
                    }
                    break;
            }
        }

        JogoDaVelhaHelper jogoDaVelhaHelper = new JogoDaVelhaHelper();
        msg = jogoDaVelhaHelper.MesageHelper(i, tabuleiro);

        if (!msg.equals("")) {
            if(msg.equals("Vencedor foi X!")){
                TextView xwin = findViewById(R.id.xwin);
                nxwin=Integer.parseInt(xwin.getText().toString())+1;
                xwin.setText(Integer.toString(nxwin));
            }else {
                TextView owin = findViewById(R.id.owin);
                nowin=Integer.parseInt(owin.getText().toString())+1;
                owin.setText(Integer.toString(nowin));
            }
            
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_jogodavelha);

            // set the custom dialog components - text, image and button
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText(msg + " Clique em OK para jogar novamente!");

            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restartGame();
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    public void restartGame() {
        ImageView xa = findViewById(R.id.xa);
        ImageView xb = findViewById(R.id.xb);
        ImageView xc = findViewById(R.id.xc);
        ImageView ya = findViewById(R.id.ya);
        ImageView yb = findViewById(R.id.yb);
        ImageView yc = findViewById(R.id.yc);
        ImageView za = findViewById(R.id.za);
        ImageView zb = findViewById(R.id.zb);
        ImageView zc = findViewById(R.id.zc);
        for (int count = 0; count <= 8; count++) {
            tabuleiro[count] = 0;
        }
        i = 0;
        end = false;
        x = false;
        XA = true;
        XB = true;
        XC = true;
        YA = true;
        YB = true;
        YC = true;
        ZA = true;
        ZB = true;
        ZC = true;
        msg = "";

        xa.setImageDrawable(null);
        xb.setImageDrawable(null);
        xc.setImageDrawable(null);
        ya.setImageDrawable(null);
        yb.setImageDrawable(null);
        yc.setImageDrawable(null);
        za.setImageDrawable(null);
        zb.setImageDrawable(null);
        zc.setImageDrawable(null);
    }
}
