# Android Simple Cartesian Plotter

## Introduction

This library is a simple interface to facilitate the way to graph points in a Cartesian plane, additionally you can create simple polygons, it was done with the native app of Canvas. Develop this small library because after searching several libraries in different sites I did not find any that would help me with my problem, I hope it will save you programming time :).

## Install

You can download library files from JCenter or GitHub.

**LatestVersion is 1.0.1**

Add the following in your app's build.gradle file:

```
dependencies {
    implementation "com.ederdoski.simple-cartesian-plotter:simplecartesianplotter:1.0.1"
}
```

## Usage

* **In XML**

1) Create a surfaceView object, this will be your canvas.

```
	<SurfaceView
        android:id="@+id/surfaceView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

* **In Java (Required)**

1) Implement in your main class SurfaceHolder.Callback

```
	implements SurfaceHolder.Callback
```

2) Generate the methods surfaceCreated, surfaceChanged, surfaceDestroyed.

```
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
    public void surfaceDestroyed(SurfaceHolder holder) {}
```

3) Instantiate the class CartesianPlotter

```
	new CartesianPlotter(this, surfaceView.getHolder());
```

4) Instantiate the following callback

```
	CartesianPlotter.getSurfaceHolder().addCallback(this);
```

* **Draw in Plane**

1) Start editing the pixels in the surface. 

```
	CartesianPlotter.getSurfaceHolder().addCallback(this);
```

2) Once you verify that your canvas is not null, invoke the refresh method to initialize the canvas.
```
	CartesianPlotter.refresh(lyCanvas, aPoints, drawPoligon);
```

3) Then indicate to the drawPoint () method, the coordinates of the point to be drawn, remember to multiply your coordinate by the desired scale (zoom).
```
	float valueX = cx * CartesianPlotter.getScale();
    float valueY = cy * CartesianPlotter.getScale();

	CartesianPlotter.drawPoint(lyCanvas, valueX, valueY, radius);
```

4) Finally, release your canvas and publish your update
```
	CartesianPlotter.getSurfaceHolder().unlockCanvasAndPost(CartesianPlotter.getCanvas());
```

You should have a piece of code like this:

```
	if (CartesianPlotter.getCanvas() != null) {

        CartesianPlotter.refresh(lyCanvas, aPoints, drawPoligon);

        float valueX = cx * CartesianPlotter.getScale();
        float valueY = cy * CartesianPlotter.getScale();

        CartesianPlotter.drawPoint(lyCanvas, valueX, valueY, radius);

        CartesianPlotter.getSurfaceHolder().unlockCanvasAndPost(CartesianPlotter.getCanvas());
    }
```

## Methods

This method is responsible for drawing the plane, and previously saved points in an ArrayList <PointsFence>, you can also activate or deactivate if you want to draw a polygon with the saved points.

* This method receives three parameters:

1) A LinearLayout container of the SurfaceView.
2) An ArrayList <PointsFence> in which coordinates can be saved.
3) A boolean variable that indicates whether or not you want to draw the polygon.

```
CartesianPlotter.refresh();
```

This method is responsible for placing a point in the plane.

* This method receives four parameters:

1) A LinearLayout container of the SurfaceView.
2) A value to be plotted indicating the X coordinate.
3) A value to be plotted indicating the Y coordinate.
4) A value that indicates the radius of the point to be plotted.

```
CartesianPlotter.drawPoint();
```

* Set the size of the scale on the canvas (zoom)
```
CartesianPlotter.setScale()
```

* get the size of the scale on the canvas (zoom)
```
CartesianPlotter.getScale()
```

* Set background color of canvas
```
CartesianPlotter.setBackgroundColor()
```

* Set color of lines in plane
```
CartesianPlotter.setColorPlane()
```

* Set the color of the current position
```
CartesianPlotter.setPointColor()
```

* Set color of position saved
```
CartesianPlotter.setPointSaveColor()
```

* Set the color of the polygon lines
```
CartesianPlotter.setFenceColor()
```

**Important**

Currently the methods that involve setColor, receive an int parameter of type color in encoded format, if you want to modify the color of some element I advise you to use the native interface of Colors of Android, example:

```
CartesianPlotter.setPointColor (Color.BLACK);
```

for more details read: [Color | Android Developers](https://developer.android.com/reference/android/graphics/Color)


## License

This code is open-sourced software licensed under the [MIT license.](https://opensource.org/licenses/MIT)