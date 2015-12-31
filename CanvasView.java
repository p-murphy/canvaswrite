package com.example.patrick.canvaswrite;

/**
 * Created by Patrick on 3/18/2015.
 */

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class  CanvasView extends View
{
    // used to determine whether user moved a finger enough to draw again
    private static final float TOUCH_TOLERANCE = 10;

    private Bitmap bitmap; // drawing area for display or saving
    private Canvas bitmapCanvas; // used to draw on bitmap
    private final Paint paintScreen; // used to draw bitmap onto screen
    private final Paint paintLine; // used to draw lines onto bitmap

    // Maps of current Paths being drawn and Points in those Paths
    private final Map<Integer, Path> pathMap = new HashMap<Integer, Path>();
    private final Map<Integer, Point> previousPointMap =
            new HashMap<Integer, Point>();

    // used to hide/show system bars
    private GestureDetector singleTapDetector;

    private int canvasWidth;
    private int canvasHeight;

    private int[] coordinates = new int[2];

    private int nodePointer = 0;
    private int strokePointer = 0;

    private int flag = 0;
    private final int OUT_OF_BOUNDS = -100;
    private final int EARLY_RELEASE = -101;


    private int pixels;

    private String imageName;

    private CanvasMap canvasMap;

    public String characterKey;
    public Character character = new Character();

    private String resPath = "drawable/";
    private String letterFile;
    private String canvasGridResPath = "drawable/zone_grid";

    public boolean finish = false;

    int highestValue = 0;

    public int[] nodeScore = new int[]{0,0};
    public int strokeScore = 0;

    public CanvasView(Context context, String cK)
    {
        super(context); // pass context to View's constructor
        paintScreen = new Paint(); // used to display bitmap onto screen

        final float scale = getContext().getResources().getDisplayMetrics().density;
        pixels = (int) (1 * scale + 0.5f); //SHOULD BE 32

        Log.i("!!!!!!!!!!!!!!!!!!!!!!Pixels is ", Integer.toString(pixels));
        Log.i("!!!!!!!!!!!!!!!!!!!!!!Scale is ", Float.toString(scale));

        // set the initial display settings for the painted line
        paintLine = new Paint();
        paintLine.setAntiAlias(true); // smooth edges of drawn line
        paintLine.setColor(Color.BLACK); // default color is black
        paintLine.setStyle(Paint.Style.STROKE); // solid line
        paintLine.setStrokeWidth(pixels); // set the default line width
        paintLine.setStrokeCap(Paint.Cap.ROUND); // rounded line ends

        canvasMap = new CanvasMap();

        characterKey = cK;

        selector(characterKey);

        letterFile = imageName;
        resPath += letterFile;

        if(characterKey.equals("A")) {
            character = character.doABarrelRollA();
            //character = character.BuildGenericCharacter(characterKey, true, false, 0, 0);
            Log.i("WwWwWwWwWwWwWwWwWwWw We are building a ", characterKey);
        }
        else if(characterKey.equals("B")) {
            //character = character.BuildGenericCharacter(characterKey, true, false, 0, 0);
            //Log.i("WwWwWwWwWwWwWwWwWwWw We are building a ", characterKey);
        }
        else if(characterKey.equals("C")) {
            //character = character.BuildGenericCharacter(characterKey, true, false, 0, 0);
            //Log.i("WwWwWwWwWwWwWwWwWwWw We are building a ", characterKey);
        }
        else
        {
            character = character.BuildGenericCharacter(characterKey, true, false, 0, 0);
            //Log.i("WwWwWwWwWwWwWwWwWwWw We are building a ", characterKey);
        }

        //Log.i("mCanvas width in CanvasView constructor ", Integer.toString(this.getWidth()));
        //Log.i("mCanvas height in CanvasView constructor", Integer.toString(this.getHeight()));

        // GestureDetector for single taps
        //singleTapDetector =
        //new GestureDetector(getContext(), singleTapListener);
    }

    public CanvasView(Context context, AttributeSet attrs)
    {
        super(context, attrs); // pass context to View's constructor
        paintScreen = new Paint(); // used to display bitmap onto screen


        // set the initial display settings for the painted line
        paintLine = new Paint();
        paintLine.setAntiAlias(true); // smooth edges of drawn line
        paintLine.setColor(Color.BLACK); // default color is black
        paintLine.setStyle(Paint.Style.STROKE); // solid line
        paintLine.setStrokeWidth(5); // set the default line width
        paintLine.setStrokeCap(Paint.Cap.ROUND); // rounded line ends

        //setBackgroundResource(R.drawable.upper_a);

        canvasMap = new CanvasMap();

        Log.i("CanvasView onCreate, being called with AttributeSet constructor", "null!");

        //int characterStokeCount = character.strokeArray.length;

        //Log.i("This character has this many strokes: ", Integer.toString(characterStokeCount));

        // GestureDetector for single taps
      //singleTapDetector =
         //new GestureDetector(getContext(), singleTapListener);

    }

    // Method onSizeChanged creates Bitmap and Canvas after app displays
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH)
    {

        Log.i("EXPERIMENT ", Integer.toString(getWidth()));
        Log.i("EXPERIMENT with THIS", Integer.toString(this.getWidth()));

        bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(),
                Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);

        //bitmap.eraseColor(Color.RED); // erase the Bitmap with white
        /*Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.zone_grid);
        bitmapCanvas = new Canvas(bitmap.copy(Bitmap.Config.ARGB_8888, true));*/

        int letterTrace = getResources().getIdentifier(resPath, null, getContext().getPackageName());
        int canvasGuide = getResources().getIdentifier(canvasGridResPath, null, getContext().getPackageName());

        //Drawable d = getResources().getDrawable(R.drawable.upper_a);
        Drawable d = getResources().getDrawable(letterTrace);
        Drawable e = getResources().getDrawable(canvasGuide);

        canvasWidth = this.getWidth();
        canvasHeight = this.getHeight();

        Log.i("mCanvas width in CancvasView ", Integer.toString(canvasWidth));
        Log.i("mCanvas height in CanvasView", Integer.toString(canvasHeight));

        d.setBounds(0,0,canvasHeight,canvasWidth);
        d.setAlpha(128);
        d.draw(bitmapCanvas);
        this.setBackgroundDrawable(e);

        Log.i("CANVAS HxW:", canvasHeight + " by " + canvasWidth);
    }

    // clear the painting
    public void clear()
    {
        pathMap.clear(); // remove all paths
        previousPointMap.clear(); // remove all previous points
        bitmap.eraseColor(Color.WHITE); // clear the bitmap
        invalidate(); // refresh the screen
    }

    // set the painted line's color
    public void setDrawingColor(int color)
    {
        paintLine.setColor(color);
    }

    // return the painted line's color
    public int getDrawingColor()
    {
        return paintLine.getColor();
    }

    // set the painted line's width
    public void setLineWidth(int width)
    {
        paintLine.setStrokeWidth(width);
    }

    // return the painted line's width
    public int getLineWidth()
    {
        return (int) paintLine.getStrokeWidth();
    }

    // called each time this View is drawn
    @Override
    protected void onDraw(Canvas canvas)
    {
        // draw the background screen
        canvas.drawBitmap(bitmap, 0, 0, paintScreen);

        // for each path currently being drawn
        for (Integer key : pathMap.keySet())
            canvas.drawPath(pathMap.get(key), paintLine); // draw line
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // if a single tap event occurred on KitKat or higher device
      /*if (singleTapDetector.onTouchEvent(event))
         return true;*/

        // get the event type and the ID of the pointer that caused the event
        int action = event.getActionMasked(); // event type
        int actionIndex = event.getActionIndex(); // pointer (i.e., finger)

        // determine whether touch started, ended or is moving
        if (action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_POINTER_DOWN)
        {
            touchStarted(event.getX(actionIndex), event.getY(actionIndex),
                    event.getPointerId(actionIndex));
        }
        else if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_POINTER_UP)
        {
            touchEnded(event.getPointerId(actionIndex));
        }
        else
        {
            touchMoved(event);
        }

        invalidate(); // redraw
        return true;
    }

    private void touchStarted(float x, float y, int lineID)
    {
        Path path; // used to store the path for the given touch id
        Point point; // used to store the last point in path

        Log.i("TOUCH STARTED"," !!!!!!!!!!!!!!!!");

        // if there is already a path for lineID
        if (pathMap.containsKey(lineID))
        {
            path = pathMap.get(lineID); // get the Path
            path.reset(); // reset the Path because a new touch has started
            point = previousPointMap.get(lineID); // get Path's last point
        }
        else
        {
            path = new Path();
            pathMap.put(lineID, path); // add the Path to Map
            point = new Point(); // create a new Point
            previousPointMap.put(lineID, point); // add the Point to the Map
        }

        // move to the coordinates of the touch
        path.moveTo(x, y);
        point.x = (int) x;
        point.y = (int) y;

        coordinates = canvasMap.mapXY(point.x, point.y, canvasHeight, 16);

        highestValue = 0;

        for(int i = 0; i < character.strokeArray[0].nodeArray[0].atomArray.length; i++)
        {
            if(coordinates[0] == character.strokeArray[0].nodeArray[0].atomArray[i].location[0]
            && coordinates[1] == character.strokeArray[0].nodeArray[0].atomArray[i].location[1])
            {
                return;
            }

        }

        Log.i(""," OUT OF BOUNDS FAILURE");

    }

    private void touchMoved(MotionEvent event)
    {
        // for each of the pointers in the given MotionEvent
        for (int i = 0; i < event.getPointerCount(); i++)
        {
            // get the pointer ID and pointer index
            int pointerID = event.getPointerId(i);
            int pointerIndex = event.findPointerIndex(pointerID);

            // if there is a path associated with the pointer
            if (pathMap.containsKey(pointerID))
            {
                // get the new coordinates for the pointer
                float newX = event.getX(pointerIndex);
                float newY = event.getY(pointerIndex);

                // get the Path and previous Point associated with
                // this pointer
                Path path = pathMap.get(pointerID);
                Point point = previousPointMap.get(pointerID);

                // calculate how far the user moved from the last update
                float deltaX = Math.abs(newX - point.x);
                float deltaY = Math.abs(newY - point.y);

                // if the distance is significant enough to matter
                if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE)
                {
                    // move the path to the new location
                    path.quadTo(point.x, point.y, (newX + point.x) / 2,
                            (newY + point.y) / 2);

                    // store the new coordinates
                    point.x = (int) newX;
                    point.y = (int) newY;


                    Log.i("On Canvas, XY points: ", point.x + ", " + point.y);

                    coordinates = canvasMap.mapXY(point.x, point.y, canvasHeight, 16);

                    Log.i("On Canvas, XY COORDS: ", coordinates[0] + ", " + coordinates[1]);
                    Log.i("TOUCH", " MOVED");
                    //character.evaluateTouch(character, coordinates, strokePointer, nodePointer);
                    nodeScore = character.newEvaluateTouch(coordinates, character.strokeArray[strokePointer].nodeArray[nodePointer].atomArray, highestValue);

                    if(nodeScore[0] == -1)
                    {
                        Log.i("In Canvas View"," FAIL");
                        flag = OUT_OF_BOUNDS;
                    }

                    if(nodeScore[0] == -5)
                    {
                        highestValue = nodeScore[1];
                        Log.i("In Canvas View"," nothing");
                    }

                    if(nodeScore[0] == -9)
                    {
                        Log.i("In Canvas View"," we have reached the next node");

                        highestValue = nodeScore[1];
                        Log.i("highestNodeScore is ", Integer.toString(highestValue));
                        strokeScore += highestValue;

                        highestValue = 0;

                        nodePointer++;
                        if(nodePointer == character.strokeArray[strokePointer].nodeArray.length)
                        {
                            nodePointer--;
                        }
                    }

                    Log.i("StrokeScore is ", Integer.toString(strokeScore));
                }
            }
        }
    }

    private void touchEnded(int lineID)
    {
        strokeScore = 0;
        nodePointer = 0;
        strokePointer++;

        if(strokePointer == character.strokeArray.length)
        {
            Log.i("", "WE ARE AT THE END");
            finish = true;
        }

        Path path = pathMap.get(lineID); // get the corresponding Path
        bitmapCanvas.drawPath(path, paintLine); // draw to bitmapCanvas
        path.reset(); // reset the Path
    }



    public int returnScore()
    {
        return strokeScore;
    }
    public int returnFlag() { return flag; }

    private void selector(String ck) {
        switch (ck) {
            case "A":
                imageName = "u_a";
                break;
            case "B":
                imageName = "u_b";
                break;
            case "C":
                imageName = "u_c";
                break;
            case "D":
                imageName = "u_d";
                break;
            case "E":
                imageName = "u_e";
                break;
            case "F":
                imageName = "u_f";
                break;
            case "G":
                imageName = "u_g";
                break;
            case "H":
                imageName = "u_h";
                break;
            case "I":
                imageName = "u_i";
                break;
            case "J":
                imageName = "u_j";
                break;
            case "K":
                imageName = "u_k";
                break;
            case "L":
                imageName = "u_l";
                break;
            case "M":
                imageName = "u_m";
                break;
            case "N":
                imageName = "u_n";
                break;
            case "O":
                imageName = "u_o";
                break;
            case "P":
                imageName = "u_p";
                break;
            case "Q":
                imageName = "u_q";
                break;
            case "R":
                imageName = "u_r";
                break;
            case "S":
                imageName = "u_s";
                break;
            case "T":
                imageName = "u_t";
                break;
            case "U":
                imageName = "u_u";
                break;
            case "V":
                imageName = "u_v";
                break;
            case "W":
                imageName = "u_w";
                break;
            case "X":
                imageName = "u_x";
                break;
            case "Y":
                imageName = "u_y";
                break;
            case "Z":
                imageName = "u_z";
                break;
            default:
                imageName = "upper_a";
                break;
        }
    }

    /*
    // save the current image to the Gallery
    public void saveImage()
    {
        // use "Doodlz" followed by current time as the image name
        String name = "Doodlz" + System.currentTimeMillis() + ".jpg";

        // insert the image in the device's gallery
        String location = MediaStore.Images.Media.insertImage(
                getContext().getContentResolver(), bitmap, name,
                "Doodlz Drawing");

        if (location != null) // image was saved
        {
            // display a message indicating that the image was saved
            Toast message = Toast.makeText(getContext(),
                    R.string.message_saved, Toast.LENGTH_SHORT);
            message.setGravity(Gravity.CENTER, message.getXOffset() / 2,
                    message.getYOffset() / 2);
            message.show();
        }
        else
        {
            // display a message indicating that the image was saved
            Toast message = Toast.makeText(getContext(),
                    R.string.message_error_saving, Toast.LENGTH_SHORT);
            message.setGravity(Gravity.CENTER, message.getXOffset() / 2,
                    message.getYOffset() / 2);
            message.show();
        }
    } // end method saveImage */

    // print the current image
    /*public void printImage()
    {
        if (PrintHelper.systemSupportsPrint())
        {
            // use Android Support Library's PrintHelper to print image
            PrintHelper printHelper = new PrintHelper(getContext());

            // fit image in page bounds and print the image
            printHelper.setScaleMode(PrintHelper.SCALE_MODE_FIT);
            printHelper.printBitmap("Doodlz Image", bitmap);
        }
        else
        {
            // display message indicating that system does not allow printing
            Toast message = Toast.makeText(getContext(),
                    R.string.message_error_printing, Toast.LENGTH_SHORT);
            message.setGravity(Gravity.CENTER, message.getXOffset() / 2,
                    message.getYOffset() / 2);
            message.show();
        }
    }

        // hide system bars and action bar
   /*public void hideSystemBars()
   {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
         setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE);
   }

   // show system bars and action bar
   public void showSystemBars()
   {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
         setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
   }


   // create SimpleOnGestureListener for single tap events
   private SimpleOnGestureListener singleTapListener =
      new SimpleOnGestureListener()
      {
         @Override
         public boolean onSingleTapUp(MotionEvent e)
         {
            if ((getSystemUiVisibility() &
               View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0)
               hideSystemBars();
            else
               showSystemBars();
            return true;
         }
      };

    */
}