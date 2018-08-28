package com.ederdoski.simplecartesianplotter.models;

public class PointsFence {

    private float coordX;
    private float coordY;

    public PointsFence(float coordX, float coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public float getCoordX() {
        return coordX;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

}
