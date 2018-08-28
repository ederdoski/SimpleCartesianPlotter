package com.ederdoski.simplecartesianplotter;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.widget.LinearLayout;

import com.ederdoski.simplecartesianplotter.models.PointsFence;

import java.util.ArrayList;

public class CartesianPlotter {

    private static Activity  act;
    private static Paint  paint;
    private static Canvas canvas;
    private static SurfaceHolder surfaceHolder;

    private static int scale = 1;
    private static int planeColor      = android.R.color.white;
    private static int backgroundColor = android.R.color.black;
    private static int pointColor      = android.R.color.holo_green_light;
    private static int pointSaveColor  = android.R.color.holo_blue_bright;
    private static int fenceColor      = android.R.color.holo_red_light;

    public CartesianPlotter(Activity _act, SurfaceHolder _surfaceHolder){
        act           = _act;
        paint         = new Paint();
        surfaceHolder = _surfaceHolder;
    }

    public static void setStyle(Paint.Style style){
        paint.setStyle(style);
    }

    public static void setPlane(Canvas canvas, int width, int height){
        setColor(planeColor);
        canvas.drawLine(0, height / 2, width, height / 2, paint);
        canvas.drawLine(width / 2, 0, width / 2, height, paint);
    }

    public static void setBackground(Canvas canvas, int color){
        canvas.drawColor(ContextCompat.getColor(act, color));
    }

    public static void refresh(LinearLayout layoutGeofence, ArrayList<PointsFence> aPointsFence, boolean drawFence){
        CartesianPlotter.setBackground(canvas, backgroundColor);
        CartesianPlotter.setPlane(canvas, layoutGeofence.getWidth(), layoutGeofence.getHeight());

        setColor(pointSaveColor);

        for (int i = 0; i < aPointsFence.size(); i++) {

            float a = aPointsFence.get(i).getCoordX() * scale;
            float b = aPointsFence.get(i).getCoordY() * scale;

            if (b < 0) {
                b = Math.abs(b) + layoutGeofence.getHeight() / 2;
            } else {
                b = -b + layoutGeofence.getHeight() / 2;
            }

            a = a + layoutGeofence.getWidth() / 2;

            canvas.drawCircle(a, b, 5, CartesianPlotter.getPaint());
        }

        if(drawFence) {
            drawGeofence(layoutGeofence, aPointsFence);
        }
    }

    public static void drawGeofence(LinearLayout layoutGeofence, ArrayList<PointsFence> aPointsFence){
        if(aPointsFence.size() > 2) {

            CartesianPlotter.setColor(fenceColor);

            for (int i = 0; i < aPointsFence.size(); i++) {

                if(i+1 != aPointsFence.size()) {

                    float valueX1 = aPointsFence.get(i).getCoordX()  * CartesianPlotter.getScale();
                    float valueX2 = aPointsFence.get(i+1).getCoordX()* CartesianPlotter.getScale();
                    float valueY1 = aPointsFence.get(i).getCoordY()  * CartesianPlotter.getScale();
                    float valueY2 = aPointsFence.get(i+1).getCoordY()* CartesianPlotter.getScale();

                    if(valueY1 < 0){
                        valueY1 = Math.abs(valueY1) + layoutGeofence.getHeight()/2;
                    }else{
                        valueY1 = -valueY1 + layoutGeofence.getHeight()/2;
                    }

                    if(valueY2 < 0){
                        valueY2 = Math.abs(valueY2) + layoutGeofence.getHeight()/2;
                    }else{
                        valueY2 = -valueY2 + layoutGeofence.getHeight()/2;
                    }

                    valueX1 = valueX1 + layoutGeofence.getWidth()/2;
                    valueX2 = valueX2 + layoutGeofence.getWidth()/2;

                    CartesianPlotter.getCanvas().drawLine(valueX1, valueY1, valueX2, valueY2, CartesianPlotter.getPaint());
                }else{

                    float valueX1 = aPointsFence.get(i).getCoordX()* CartesianPlotter.getScale();
                    float valueX2 = aPointsFence.get(0).getCoordX()* CartesianPlotter.getScale();
                    float valueY1 = aPointsFence.get(i).getCoordY()* CartesianPlotter.getScale();
                    float valueY2 = aPointsFence.get(0).getCoordY()* CartesianPlotter.getScale();

                    if(valueY1 < 0){
                        valueY1 = Math.abs(valueY1) + layoutGeofence.getHeight()/2;
                    }else{
                        valueY1 = -valueY1 + layoutGeofence.getHeight()/2;
                    }

                    if(valueY2 < 0){
                        valueY2 = Math.abs(valueY2) + layoutGeofence.getHeight()/2;
                    }else{
                        valueY2 = -valueY2 + layoutGeofence.getHeight()/2;
                    }

                    valueX1 = valueX1 + layoutGeofence.getWidth()/2;
                    valueX2 = valueX2 + layoutGeofence.getWidth()/2;

                    CartesianPlotter.getCanvas().drawLine(valueX1, valueY1, valueX2, valueY2, CartesianPlotter.getPaint());
                }
            }
        }
    }

    public static void setColorPlane(int color){
        planeColor = color;
    }

    public static void setBackgroundColor(int color){
        backgroundColor = color;
    }

    public static void setPointColor(int color){
        pointColor = color;
    }

    public static void setPointSaveColor(int color){
        pointSaveColor = color;
    }

    public static void setFenceColor(int color){
        fenceColor = color;
    }

    private static void setColor(int color){
        paint.setColor(ContextCompat.getColor(act, color));
    }

    public static void drawPoint(LinearLayout lyCanvas, float valuex, float valuey, int radius){
        setColor(pointColor);

        if(valuey < 0){
            valuey = Math.abs(valuey) + lyCanvas.getHeight()/2;
        }else{
            valuey = -valuey + lyCanvas.getHeight()/2;
        }

        valuex = valuex + lyCanvas.getWidth()/2;

        canvas.drawCircle(valuex, valuey, radius, paint);
    }

    public static Paint getPaint(){
        return paint;
    }

    public static void setCanvas(Canvas _canvas){
        canvas = _canvas;
    }

    public static Canvas getCanvas(){
        return canvas;
    }

    public static SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public static void setSurfaceHolder(SurfaceHolder _surfaceHolder) {
        surfaceHolder = _surfaceHolder;
    }

    public static int getScale() {
        return scale;
    }

    public static void setScale(int _scale) {
        scale = _scale;
    }
}


