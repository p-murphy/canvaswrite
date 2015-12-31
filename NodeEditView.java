package com.example.patrick.canvaswrite;

/**
 * Created by Patrick on 4/6/2015.
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

public class NodeEditView extends View {
    // used to determine whether user moved a finger enough to draw again
    private static final float TOUCH_TOLERANCE = 10;

    private Bitmap bitmap; // drawing area for display or saving
    public Canvas bitmapCanvas; // used to draw on bitmap
    private final Paint paintScreen; // used to draw bitmap onto screen
    private final Paint paintLine; // used to draw lines onto bitmap

    private Paint myPaint = new Paint();

    private final int NEG_ONE = -1;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int TWO = 2;

    private final int NEG_NIN = -9;


    // Maps of current Paths being drawn and Points in those Paths
    private final Map<Integer, Path> pathMap = new HashMap<Integer, Path>();
    private final Map<Integer, Point> previousPointMap =
            new HashMap<Integer, Point>();

    // used to hide/show system bars
    private GestureDetector singleTapDetector;

    private int canvasWidth;
    private int canvasHeight;

    private int[] coordinates = new int[2];
    int[] mode = {-3};

    private int nodePointer = 0;
    private int strokePointer = 0;

    private int pixels;

    private String imageName;

    private CanvasMap canvasMap;

    public String characterKey;
    public Character character = new Character();

    private String resPath = "drawable/";
    private String letterFile;
    private String canvasGridResPath = "drawable/zone_grid";

    public Atom[] atomArray;

    public NodeEditView(Context context, String cK, int[] m) {
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
        mode = m;

        selector(characterKey);

        letterFile = imageName;
        resPath += letterFile;

        atomArray = new Atom[25];

        if (characterKey.equals("A")) {
            character = character.BuildGenericCharacter(characterKey, true, false, 0, 0);
        } else if (characterKey.equals("B")) {
            character = character.BuildGenericCharacter(characterKey, true, false, 0, 0);
        } else {
            character = character.BuildGenericCharacter(characterKey, true, false, 0, 0);
        }

    }

    public NodeEditView(Context context, AttributeSet attrs) {
        super(context, attrs); // pass context to View's constructor
        paintScreen = new Paint(); // used to display bitmap onto screen

        paintLine = new Paint();
        paintLine.setAntiAlias(true); // smooth edges of drawn line
        paintLine.setColor(Color.BLACK); // default color is black
        paintLine.setStyle(Paint.Style.STROKE); // solid line
        paintLine.setStrokeWidth(5); // set the default line width
        paintLine.setStrokeCap(Paint.Cap.ROUND); // rounded line ends

        canvasMap = new CanvasMap();

        Log.i("CanvasView onCreate, being called with AttributeSet constructor", "null!");


    }

    // Method onSizeChanged creates Bitmap and Canvas after app displays
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {

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

        d.setBounds(0, 0, canvasHeight, canvasWidth);
        d.setAlpha(128);
        d.draw(bitmapCanvas);
        this.setBackgroundDrawable(e);

        Log.i("CANVAS HxW:", canvasHeight + " by " + canvasWidth);
    }

    // clear the painting
    public void clear() {
        pathMap.clear(); // remove all paths
        previousPointMap.clear(); // remove all previous points
        bitmap.eraseColor(Color.WHITE); // clear the bitmap
        invalidate(); // refresh the screen
    }

    // set the painted line's color
    public void setDrawingColor(int color) {
        paintLine.setColor(color);
    }

    // return the painted line's color
    public int getDrawingColor() {
        return paintLine.getColor();
    }

    // set the painted line's width
    public void setLineWidth(int width) {
        paintLine.setStrokeWidth(width);
    }

    // return the painted line's width
    public int getLineWidth() {
        return (int) paintLine.getStrokeWidth();
    }

    // called each time this View is drawn
    @Override
    protected void onDraw(Canvas canvas) {
        // draw the background screen
        canvas.drawBitmap(bitmap, 0, 0, paintScreen);

        // for each path currently being drawn
        for (Integer key : pathMap.keySet())
            canvas.drawPath(pathMap.get(key), paintLine); // draw line
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // if a single tap event occurred on KitKat or higher device
      /*if (singleTapDetector.onTouchEvent(event))
         return true;*/

        // get the event type and the ID of the pointer that caused the event
        int action = event.getActionMasked(); // event type
        int actionIndex = event.getActionIndex(); // pointer (i.e., finger)

        // determine whether touch started, ended or is moving
        if (action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_POINTER_DOWN) {
            touchStarted(event.getX(actionIndex), event.getY(actionIndex),
                    event.getPointerId(actionIndex));
        } else if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_POINTER_UP) {
            touchEnded(event.getPointerId(actionIndex));
        } else {
            touchMoved(event);
        }

        invalidate(); // redraw
        return true;
    }

    private void touchStarted(float x, float y, int lineID) {
        Path path; // used to store the path for the given touch id
        Point point; // used to store the last point in path

        Log.i("NODE EDIT: TOUCH STARTED"," !!!!!!!!!!!!!!!!");
        Log.i("NODE EDIT: MODE IS", Integer.toString(mode[0]));

        // if there is already a path for lineID
        if (pathMap.containsKey(lineID)) {
            path = pathMap.get(lineID); // get the Path
            path.reset(); // reset the Path because a new touch has started
            point = previousPointMap.get(lineID); // get Path's last point
        } else {
            path = new Path();
            pathMap.put(lineID, path); // add the Path to Map
            point = new Point(); // create a new Point
            previousPointMap.put(lineID, point); // add the Point to the Map
        }

        // move to the coordinates of the touch
        path.moveTo(x, y);
        point.x = (int) x;
        point.y = (int) y;

        Log.i("On Canvas, XY points: ", point.x + ", " + point.y);

        coordinates = canvasMap.mapXY(point.x, point.y, canvasHeight, 16);

        Log.i("On Canvas, XY COORDS: ", coordinates[0] + ", " + coordinates[1]);

        int m = mode[0];

        if(m == 3)
        {
            placeNode(m, coordinates);
        }
        else if(m < 3 && m > -2)
        {
            paintZone(m, coordinates, atomArray);
        }
        else if(m == -9)
        {
            paintZone(m, coordinates, atomArray);
        }
        else
        {
            Log.i("Taking no action because node is: ", Integer.toString(m));
        }

    }

    private void placeNode(int mode, int[] coord){

        Log.i("Now establishing node with center point: ", Integer.toString(coord[0]) + ","
                + Integer.toString(coord[1]));

        //Declare 25 atoms
        //Paint zones

        Atom atom1 = new Atom(new int[]{coord[0] - 2,coord[1] - 2});
        Atom atom2 = new Atom(new int[]{coord[0] - 1,coord[1] - 2});
        Atom atom3 = new Atom(new int[]{coord[0],coord[1] - 2});
        Atom atom4 = new Atom(new int[]{coord[0] + 1,coord[1] - 2});
        Atom atom5 = new Atom(new int[]{coord[0] + 2,coord[1] - 2});

        Atom atom6 = new Atom(new int[]{coord[0] - 2,coord[1] - 1});
        Atom atom7 = new Atom(new int[]{coord[0] - 1,coord[1] - 1});
        Atom atom8 = new Atom(new int[]{coord[0],coord[1] - 1});
        Atom atom9 = new Atom(new int[]{coord[0] + 1,coord[1] - 1});
        Atom atom10 = new Atom(new int[]{coord[0] + 2,coord[1] - 1});

        Atom atom11 = new Atom(new int[]{coord[0] - 2,coord[1]});
        Atom atom12 = new Atom(new int[]{coord[0] - 1,coord[1]});
        Atom atom13 = new Atom(new int[]{coord[0],coord[1]});
        Atom atom14 = new Atom(new int[]{coord[0] + 1,coord[1]});
        Atom atom15 = new Atom(new int[]{coord[0] + 2,coord[1]});

        Atom atom16 = new Atom(new int[]{coord[0] - 2,coord[1] + 1});
        Atom atom17 = new Atom(new int[]{coord[0] - 1,coord[1] + 1});
        Atom atom18 = new Atom(new int[]{coord[0],coord[1] + 1});
        Atom atom19 = new Atom(new int[]{coord[0] + 1,coord[1] + 1});
        Atom atom20 = new Atom(new int[]{coord[0] + 2,coord[1] + 1});

        Atom atom21 = new Atom(new int[]{coord[0] - 2,coord[1] + 2});
        Atom atom22 = new Atom(new int[]{coord[0] - 1,coord[1] + 2});
        Atom atom23 = new Atom(new int[]{coord[0],coord[1] + 2});
        Atom atom24 = new Atom(new int[]{coord[0] + 1,coord[1] + 2});
        Atom atom25 = new Atom(new int[]{coord[0] + 2,coord[1] + 2});

        atomArray[0] = atom1;
        atomArray[1] = atom2;
        atomArray[2] = atom3;
        atomArray[3] = atom4;
        atomArray[4] = atom5;
        atomArray[5] = atom6;
        atomArray[6] = atom7;
        atomArray[7] = atom8;
        atomArray[8] = atom9;
        atomArray[9] = atom10;
        atomArray[10] = atom11;
        atomArray[11] = atom12;
        atomArray[12] = atom13;
        atomArray[13] = atom14;
        atomArray[14] = atom15;
        atomArray[15] = atom16;
        atomArray[16] = atom17;
        atomArray[17] = atom18;
        atomArray[18] = atom19;
        atomArray[19] = atom20;
        atomArray[20] = atom21;
        atomArray[21] = atom22;
        atomArray[22] = atom23;
        atomArray[23] = atom24;
        atomArray[24] = atom25;

        paintConfig(NEG_ONE, atom1.location, atomArray);
        paintConfig(NEG_ONE, atom2.location, atomArray);
        paintConfig(NEG_ONE, atom3.location, atomArray);
        paintConfig(NEG_ONE, atom4.location, atomArray);
        paintConfig(NEG_ONE, atom5.location, atomArray);

        paintConfig(NEG_ONE, atom6.location, atomArray);
        paintConfig(ZERO, atom7.location, atomArray);
        paintConfig(ZERO, atom8.location, atomArray);
        paintConfig(ZERO, atom9.location, atomArray);
        paintConfig(NEG_ONE, atom10.location, atomArray);

        paintConfig(NEG_ONE, atom11.location, atomArray);
        paintConfig(ZERO, atom12.location, atomArray);
        paintConfig(TWO, atom13.location, atomArray);
        paintConfig(ZERO, atom14.location, atomArray);
        paintConfig(NEG_ONE, atom15.location, atomArray);

        paintConfig(NEG_ONE, atom16.location, atomArray);
        paintConfig(ZERO, atom17.location, atomArray);
        paintConfig(ZERO, atom18.location, atomArray);
        paintConfig(ZERO, atom19.location, atomArray);
        paintConfig(NEG_ONE, atom20.location, atomArray);

        paintConfig(NEG_ONE, atom21.location, atomArray);
        paintConfig(NEG_ONE, atom22.location, atomArray);
        paintConfig(NEG_ONE, atom23.location, atomArray);
        paintConfig(NEG_ONE, atom24.location, atomArray);
        paintConfig(NEG_ONE, atom25.location, atomArray);

        for(int i = 0; i < 5; i++)
        {
            String row = "";

            for(int j = 0; j < 5; j++)
            {
                row += "[";
                row += Integer.toString(atomArray[(i*5) + j].location[0]);
                row += ",";
                row += Integer.toString(atomArray[(i*5) + j].location[1]);
                row += "]";
            }

            Log.i("  ", row);
        }

        Log.i("-", "-");

        for(int i = 0; i < 5; i++)
        {
            String row = "";

            for(int j = 0; j < 5; j++)
            {
                row += "[";
                row += Integer.toString(atomArray[(i*5) + j].value);
                row += "]";
            }

            Log.i("  ", row);
        }

    }

    private void paintConfig(int mode, int[] c, Atom[] a){

        int offsetX = (c[0] * 75) - 75;
        int offsetY = (c[1] * 75) - 75;

        Atom atom = new Atom();

        for(int i = 0; i < 25; i++)
        {

            if(a[i].location[0] == c[0] && a[i].location[1] == c[1])
            {
                //Log.i("Painting the atom at location: ", Integer.toString(a[i].location[0])
                //+ "," + Integer.toString(a[i].location[1]));

                switch(mode)
                {
                    case -1:  paintNegOne(a[i]); break;
                    case 0:  paintZero(a[i]); break;
                    case 1: paintOne(a[i]); break;
                    case 2: paintTwo(a[i]); break;
                    case -9: paintNegativeNine(a[i]); break;
                    default: break;
                }

                myPaint.setStrokeWidth(10);
                bitmapCanvas.drawRect(offsetX, offsetY, offsetX+ 75, offsetY + 75, myPaint);

                return;
            }

        }

    }

    private void paintZone(int mode, int[] c, Atom[] a){

        int offsetX = (c[0] * 75) - 75;
        int offsetY = (c[1] * 75) - 75;

        Atom atom = new Atom();

        for(int i = 0; i < 25; i++)
        {

            if(a[i].location[0] == c[0] && a[i].location[1] == c[1])
            {
                //Log.i("Painting the atom at location: ", Integer.toString(a[i].location[0])
                //        + "," + Integer.toString(a[i].location[1]));
                //Log.i("which is the local node location: ", c[0] + "." + c[1]);

                switch(mode)
                {
                    case -1:  paintNegOne(a[i]); break;
                    case 0:  paintZero(a[i]); break;
                    case 1: paintOne(a[i]); break;
                    case 2: paintTwo(a[i]); break;
                    case -9: paintNegativeNine(a[i]); break;
                    default: break;
                }

                myPaint.setStrokeWidth(10);
                bitmapCanvas.drawRect(offsetX, offsetY, offsetX+ 75, offsetY + 75, myPaint);

                return;
            }

        }

    }

    private void paintNegOne(Atom a){

        //Log.i("Now painting -1 node with center point: ", Integer.toString(coord[0]) + "," + Integer.toString(coord[1]));

        myPaint.setARGB(20,255,0,0);
        a.value = -1;
    }

    private void paintZero(Atom a){

        //Log.i("Now painting 0 node with center point: ", Integer.toString(coord[0]) + "," + Integer.toString(coord[1]));

        myPaint.setARGB(10,255,255,255);
        a.value = 0;
    }

    private void paintOne(Atom a){

        //Log.i("Now painting 1 node with center point: ", Integer.toString(coord[0]) + "," + Integer.toString(coord[1]));

        myPaint.setARGB(20,0,0,255);
        a.value = 1;
    }

    private void paintTwo(Atom a){

        //Log.i("Now painting 2 node with center point: ", Integer.toString(coord[0]) + "," + Integer.toString(coord[1]));

        myPaint.setARGB(100,0,128,255);
        a.value = 2;
    }

    private void paintNegativeNine(Atom a){

        //Log.i("Now painting 2 node with center point: ", Integer.toString(coord[0]) + "," + Integer.toString(coord[1]));

        myPaint.setARGB(100,0,230,255);
        a.value = -9;
    }

    private void touchMoved(MotionEvent event) {
    }

    private void touchEnded(int lineID) {
        Path path = pathMap.get(lineID); // get the corresponding Path
        bitmapCanvas.drawPath(path, paintLine); // draw to bitmapCanvas
        path.reset(); // reset the Path
    }

    public void redrawLetter(){

        bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(),
                Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);

        int letterTrace = getResources().getIdentifier(resPath, null, getContext().getPackageName());
        int canvasGuide = getResources().getIdentifier(canvasGridResPath, null, getContext().getPackageName());

        Drawable d = getResources().getDrawable(letterTrace);
        Drawable e = getResources().getDrawable(canvasGuide);

        canvasWidth = this.getWidth();
        canvasHeight = this.getHeight();

        //Log.i("mCanvas width in CancvasView ", Integer.toString(canvasWidth));
        //Log.i("mCanvas height in CanvasView", Integer.toString(canvasHeight));

        d.setBounds(0, 0, canvasHeight, canvasWidth);
        d.setAlpha(128);
        d.draw(bitmapCanvas);
        this.setBackgroundDrawable(e);

        //Log.i("CANVAS HxW:", canvasHeight + " by " + canvasWidth);
    }

    public void redrawCentersBlue(int x, int y){

        int offsetX = (x * 75) - 75;
        int offsetY = (y * 75) - 75;

        myPaint.setARGB(100,0,128,255);

        myPaint.setStrokeWidth(10);
        bitmapCanvas.drawRect(offsetX, offsetY, offsetX+ 75, offsetY + 75, myPaint);

    }

    public void redrawCentersGreen(int x, int y){

        int offsetX = (x * 75) - 75;
        int offsetY = (y * 75) - 75;

        myPaint.setARGB(100,0,255,128);

        myPaint.setStrokeWidth(10);
        bitmapCanvas.drawRect(offsetX, offsetY, offsetX+ 75, offsetY + 75, myPaint);

    }

    public void redrawCentersYellow(int x, int y){

        int offsetX = (x * 75) - 75;
        int offsetY = (y * 75) - 75;

        myPaint.setARGB(100,255,255,0);

        myPaint.setStrokeWidth(10);
        bitmapCanvas.drawRect(offsetX, offsetY, offsetX+ 75, offsetY + 75, myPaint);

    }

    private void selector(String ck) {
        switch (ck) {
            case "A":imageName = "u_a";break;
            case "B":imageName = "u_b";break;
            case "C":imageName = "u_c";break;
            case "D":imageName = "u_d";break;
            case "E":imageName = "u_e";break;
            case "F":imageName = "u_f";break;
            case "G":imageName = "u_g";break;
            case "H":imageName = "u_h";break;
            case "I":imageName = "u_i";break;
            case "J":imageName = "u_j";break;
            case "K":imageName = "u_k";break;
            case "L":imageName = "u_l";break;
            case "M":imageName = "u_m";break;
            case "N":imageName = "u_n";break;
            case "O":imageName = "u_o";break;
            case "P":imageName = "u_p";break;
            case "Q":imageName = "u_q";break;
            case "R":imageName = "u_r";break;
            case "S":imageName = "u_s";break;
            case "T":imageName = "u_t";break;
            case "U":imageName = "u_u";break;
            case "V":imageName = "u_v";break;
            case "W":imageName = "u_w";break;
            case "X":imageName = "u_x";break;
            case "Y":imageName = "u_y";break;
            case "Z":imageName = "u_z";break;
            default:imageName = "upper_a";break;
        }
    }
}
