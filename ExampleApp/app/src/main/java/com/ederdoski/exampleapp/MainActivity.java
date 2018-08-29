package com.ederdoski.exampleapp;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ederdoski.simplecartesianplotter.CartesianPlotter;
import com.ederdoski.simplecartesianplotter.models.PointsFence;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    ArrayList<PointsFence> points = new ArrayList<>();

    boolean drawFence = false;
    float lastCoordX;
    float lastCoordY;

    SurfaceView surfaceView;
    LinearLayout lyCanvas;
    Button btnAddPoint;
    Button btnSavePoint;
    Button btnFence;
    TextView txtCoordX;
    TextView txtCoordY;
    TextView txtScale;
    SeekBar barScale;

    private void buttonListener(){

        btnAddPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartesianPlotter.setCanvas(CartesianPlotter.getSurfaceHolder().lockCanvas());

                if (CartesianPlotter.getCanvas() != null) {

                    CartesianPlotter.refresh(lyCanvas, points, drawFence);

                    Random r = new Random();
                    float cx = r.nextInt(60 + 1 + 10) -40;
                    float cy = r.nextInt(60 + 1 + 10) -30;

                    lastCoordX = cx;
                    lastCoordY = cy;

                    float valuex = cx * CartesianPlotter.getScale();
                    float valuey = cy * CartesianPlotter.getScale();

                    CartesianPlotter.drawPoint(lyCanvas, valuex, valuey, 5);

                    txtCoordX.setText("CoordX: "+ cx);
                    txtCoordY.setText("CoordY: "+ cy);
                    txtScale.setText("Scale:  "+ CartesianPlotter.getScale());

                    CartesianPlotter.getSurfaceHolder().unlockCanvasAndPost(CartesianPlotter.getCanvas());
                }

            }
        });

        btnSavePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points.add(new PointsFence(lastCoordX, lastCoordY));
            }
        });

        btnFence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(points.size() < 3) {
                    Toast.makeText(MainActivity.this, "At least 3 points are needed to graph", Toast.LENGTH_SHORT).show();
                }else{
                    if(!drawFence) {
                        drawFence = true;
                        Toast.makeText(MainActivity.this, "Draw Fence activated", Toast.LENGTH_SHORT).show();
                    }else{
                        drawFence = false;
                        Toast.makeText(MainActivity.this, "Draw Fence desactivated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        barScale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                if(progress == 0){
                    seekBar.setProgress(1);
                }else{
                    CartesianPlotter.setScale(progress*3);
                }
            }
        });
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        CartesianPlotter.setStyle(Paint.Style.STROKE);
        CartesianPlotter.getPaint().setStrokeWidth(10);
        CartesianPlotter.setSurfaceHolder(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        CartesianPlotter.setSurfaceHolder(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView  = findViewById(R.id.surfaceView);
        lyCanvas     = findViewById(R.id.lyCanvas);
        btnAddPoint  = findViewById(R.id.btnAddPoint);
        btnSavePoint = findViewById(R.id.btnSavePoint);
        btnFence     = findViewById(R.id.btnFence);
        txtCoordX    = findViewById(R.id.txtCordX);
        txtCoordY    = findViewById(R.id.txtCordY);
        txtScale     = findViewById(R.id.txtScale);
        barScale     = findViewById(R.id.barScale);

        new CartesianPlotter(this, surfaceView.getHolder());
        CartesianPlotter.getSurfaceHolder().addCallback(this);

        buttonListener();


    }

}