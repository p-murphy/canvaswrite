package com.example.patrick.canvaswrite;

import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.media.SoundPool;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.AudioManager;


public class NodeEdit extends FragmentActivity implements NodeEditFragment.OnFragmentInteractionListener {

    private DatabaseManager db;
    private String letter;
    private int win, lose;
    private SoundPool soundPool;
    public NodeEditFragment nodeEditFragment;

    public int[] mode = {-2};

    public Atom[] atomArray;

    public Node[] nodeArray = new Node[10];

    public Stroke[] strokeArray = new Stroke[10];

    public int nodeCounter = 0;
    public int strokeCounter = 0;

    Button configureNodeButton;
    Button commitNodeButton;
    Button commitStrokeButton;
    Button commitCharacterButton;
    Button paintNegativeOneButton;
    Button paintZeroButton;
    Button paintOneButton;
    Button paintTwoButton;
    Button paintNegativeNineButton;

    TextView strokeCountTextView;
    TextView nodeCountTextView;

    public Canvas bitmapCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        letter = getIntent().getExtras().getString("Letter");
        Bundle letterBundle = new Bundle();
        letterBundle.putSerializable("Letter", letter);
        letterBundle.putSerializable("Mode", mode);
        nodeEditFragment = new NodeEditFragment();
        nodeEditFragment.setArguments(letterBundle);

        setContentView(R.layout.activity_node_edit);

        fragmentTransaction.add(R.id.canvasFrame, nodeEditFragment).commit();

        TextView canvas = (TextView)findViewById(R.id.LetterCanvas);
        canvas.setText(letter);

        db = new DatabaseManager(this);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        win = soundPool.load(this, R.raw.win, 1);
        lose = soundPool.load(this, R.raw.lose, 1);


        /*Log.i("In LEARN", " before first canvasFragment call");
        CanvasFragment canvasFragment = CanvasFragment.newInstance("A");
        Log.i("In LEARN", " after first canvasFragment call");
*/

        configureNodeButton = (Button)findViewById(R.id.configureNodeButton);
        commitNodeButton = (Button)findViewById(R.id.commitNodeButton);
        commitStrokeButton = (Button)findViewById(R.id.commitStrokeButton);
        commitCharacterButton = (Button)findViewById(R.id.commitCharacterButton);
        paintNegativeOneButton = (Button)findViewById(R.id.paintNegativeOneButton);
        paintZeroButton = (Button)findViewById(R.id.paintZeroButton);
        paintOneButton = (Button)findViewById(R.id.paintOneButton);
        paintTwoButton = (Button)findViewById(R.id.paintTwoButton);
        paintNegativeNineButton = (Button)findViewById(R.id.paintNegativeNineButton);

        strokeCountTextView = (TextView)findViewById(R.id.StrokeCount);
        nodeCountTextView = (TextView)findViewById(R.id.NodeCount);

        strokeCountTextView.setText(Integer.toString(strokeCounter + 1));
        nodeCountTextView.setText(Integer.toString(nodeCounter + 1));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_node_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void paintNegativeOne(View view) {

        signifyMode(view);

        mode[0] = -1;
        Log.i("Paint -1"," pressed");
    }

    public void paintZero(View view) {

        signifyMode(view);

        mode[0] = 0;
        Log.i("Paint 0"," pressed");
    }

    public void paintOne(View view) {

        signifyMode(view);

        mode[0] = 1;
        Log.i("Paint 1"," pressed");
    }

    public void paintTwo(View view) {

        signifyMode(view);

        mode[0] = 2;
        Log.i("Paint 2"," pressed");
    }

    public void paintNegativeNine(View view) {

        signifyMode(view);

        mode[0] = -9;
        Log.i("Paint -9"," pressed");
    }

    public void configNode(View view) {

        signifyMode(view);

        mode[0] = 3;
        Log.i("Config Node"," pressed");
    }

    private void signifyMode(View v){

        configureNodeButton.getBackground().clearColorFilter();
        commitNodeButton.getBackground().clearColorFilter();
        commitStrokeButton.getBackground().clearColorFilter();
        commitCharacterButton.getBackground().clearColorFilter();
        paintNegativeOneButton.getBackground().clearColorFilter();
        paintZeroButton.getBackground().clearColorFilter();
        paintOneButton.getBackground().clearColorFilter();
        paintTwoButton.getBackground().clearColorFilter();
        paintNegativeNineButton.getBackground().clearColorFilter();
        v.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));

    }

    public void commitNode(View view) {

        signifyMode(view);

        boolean isStart = false;
        boolean isEnd = false;

        mode[0] = 4;
        Log.i("Commit Node"," saving to database the following nodeMap:");

        atomArray = nodeEditFragment.atomArray;

        Log.i("pringint node", Integer.toString(atomArray[0].value));

        /*
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
        */


        Atom[] newAtoms = new Atom[]{
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom(),
                new Atom()
        };

        for(int i = 0; i < 5; i++)
        {
            String row = "";

            for(int j = 0; j < 5; j++)
            {

                newAtoms[(i*5) + j].location = atomArray[(i*5) + j].location;
                newAtoms[(i*5) + j].value = atomArray[(i*5) + j].value;


                row += "[";
                row += Integer.toString(newAtoms[(i*5) + j].location[0]);
                row += ",";
                row += Integer.toString(newAtoms[(i*5) + j].location[1]);
                row += "]";
            }

            Log.i("  ", row);
        }

        for(int i = 0; i < 5; i++)
        {
            String row = "";

            for(int j = 0; j < 5; j++)
            {
                row += "[";
                row += Integer.toString(newAtoms[(i*5) + j].value);
                row += "]";
            }

            Log.i("  ", row);
        }

        if(nodeCounter == 0)
        {
            isStart = true;
        }

        nodeArray[nodeCounter] = new Node(newAtoms[12].location, newAtoms, isStart, isEnd);
        nodeCounter++;

        nodeCountTextView.setText(Integer.toString(nodeCounter + 1));

        nodeEditFragment.nodeEditCanvas.bitmapCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        nodeEditFragment.nodeEditCanvas.redrawLetter();

        for(int i = 0; i < newAtoms.length; i++)
        {
            if(newAtoms[i].value == -9)
            {
                int x = newAtoms[i].location[0];
                int y = newAtoms[i].location[1];
                nodeEditFragment.nodeEditCanvas.redrawCentersYellow(x, y);
            }
        }

        for(int i = 0; i < nodeCounter; i++)
        {
            int x = nodeArray[i].atomArray[12].location[0];
            int y = nodeArray[i].atomArray[12].location[1];
            nodeEditFragment.nodeEditCanvas.redrawCentersBlue(x, y);
        }

        for(int i = 0; i < strokeCounter; i++)
        {

            for(int j = 0; j < strokeArray[i].nodeArray.length; j++)
            {
                int x = strokeArray[i].nodeArray[j].atomArray[12].location[0];
                int y = strokeArray[i].nodeArray[j].atomArray[12].location[1];
                nodeEditFragment.nodeEditCanvas.redrawCentersGreen(x, y);
            }
        }

    }

    public void commitStroke(View view) {

        signifyMode(view);

        boolean isFirst = false;
        boolean isLast = false;

        Node[] nodes = new Node[nodeCounter];

        for(int i = 0; i < nodeCounter; i++)
        {
            if(i == nodeCounter - 1)
            {
                nodeArray[i].isEnd = true;
            }
            nodes[i] = nodeArray[i];
        }

        mode[0] = 5;
        Log.i("Commit Stroke"," pressed");

        Log.i("Printing all nodes:","......................");

        for(int k = 0; k < nodes.length; k++)
        {

            Log.i("///////////////////", "/////////////////////");
            Log.i("Node #", Integer.toString(k));
            Log.i("-", "-");

            for(int i = 0; i < 5; i++)
            {
                String row = "";

                for(int j = 0; j < 5; j++)
                {
                    row += "[";
                    row += Integer.toString(nodes[k].atomArray[(i*5) + j].location[0]);
                    row += ",";
                    row += Integer.toString(nodes[k].atomArray[(i*5) + j].location[1]);
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
                    row += Integer.toString(nodes[k].atomArray[(i*5) + j].value);
                    row += "]";
                }

                Log.i("  ", row);
            }

            Log.i("///////////////////", "/////////////////////");
        }

        strokeArray[strokeCounter] = new Stroke(nodes, isFirst, isLast);
        strokeCounter++;
        nodeCounter = 0;

        strokeCountTextView.setText(Integer.toString(strokeCounter + 1));
        nodeCountTextView.setText(Integer.toString(nodeCounter + 1));

        nodeEditFragment.nodeEditCanvas.bitmapCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        nodeEditFragment.nodeEditCanvas.redrawLetter();

        for(int i = 0; i < strokeCounter; i++)
        {

            for(int j = 0; j < strokeArray[i].nodeArray.length; j++)
            {
                int x = strokeArray[i].nodeArray[j].atomArray[12].location[0];
                int y = strokeArray[i].nodeArray[j].atomArray[12].location[1];
                nodeEditFragment.nodeEditCanvas.redrawCentersGreen(x, y);

                x = nodeArray[j].atomArray[12].location[0];
                y = nodeArray[j].atomArray[12].location[1];
                nodeEditFragment.nodeEditCanvas.redrawCentersGreen(x, y);
                nodeEditFragment.nodeEditCanvas.redrawCentersGreen(x, y);
                nodeEditFragment.nodeEditCanvas.redrawCentersGreen(x, y);
            }
        }

    }

    public void commitCharacter(View view) {

        signifyMode(view);

        Log.i("-", "-");
        Log.i("-", "-");
        Log.i("-", "-");
        Log.i("-", "-");
        Log.i("-", "-");

        Stroke[] strokes = new Stroke[strokeCounter];

        for(int i = 0; i < strokeCounter; i++)
        {
            if(i == strokeCounter - 1)
            {
                strokeArray[i].isLast = true;
            }
            strokes[i] = strokeArray[i];
        }

        Log.i("Printing all strokes, all nodes:","####################");
        Log.i("Printing all strokes, all nodes:","####################");
        Log.i("Printing all strokes, all nodes:","####################");
        Log.i("Printing all strokes, all nodes:","####################");
        Log.i("Printing all strokes, all nodes:","####################");
        Log.i("", "______________________________________________");
        Log.i("", "______________________________________________");


        Log.i("", "return new Character(A, true, false, 0, 0, new Stroke[]{");

        String output = "return new Character(A, true, false, 0, 0, new Stroke[]{";
        output += "\n";


        for(int s = 0; s < strokeCounter; s++)
        {

            Stroke thisStroke = strokes[s];
            Node[] nodes = thisStroke.nodeArray;

            //Log.i("                          Stroke #", Integer.toString(s + 1));

            Log.i("    ", "new Stroke(false, false, new Node[]{");

            output += "new Stroke(false, false, new Node[]{";
            output += "\n";

            for(int k = 0; k < nodes.length; k++)
            {

                //Log.i("///////////////////", "/////////////////////");
                //Log.i("Node #", Integer.toString(k + 1));
                //Log.i("-", "-");

                Log.i("        ", "new Node(false, false, new Atom[]{");

                output += "new Node(false, false, new Atom[]{";
                output += "\n";

                String newAtom = "            new Atom(new int[]{";

                for(int i = 0; i < 25; i++)
                {
                        newAtom += Integer.toString(nodes[k].atomArray[i].location[0]);
                        newAtom += ",";
                        newAtom += Integer.toString(nodes[k].atomArray[i].location[1]);
                        newAtom += "}, ";
                        newAtom += Integer.toString(nodes[k].atomArray[i].value);

                    if(i == 24)
                    {
                        newAtom += ")";
                    }
                    else
                    {
                        newAtom += "),";
                    }

                    Log.i("  ", newAtom);

                    output += newAtom;
                    output += "\n";


                    newAtom = "new Atom(new int[]{";
                }

                if(k == nodes.length - 1)
                {
                    Log.i("", "        })");

                    output += "        })";
                    output += "\n";

                }
                else
                {
                    Log.i("", "        }),");


                    output += "        }),";
                    output += "\n";
                }

                /*
                Log.i("-", "-");

                for(int i = 0; i < 5; i++)
                {
                    String row = "";

                    for(int j = 0; j < 5; j++)
                    {
                        row += "[";
                        row += Integer.toString(nodes[k].atomArray[(i*5) + j].value);
                        row += "]";
                    }

                    Log.i("  ", row);
                }
                */

                Log.i("///////////////////", "/////////////////////");
            }

            if(s == strokeCounter - 1)
            {
                Log.i("", "    })");

                output += "    })";
                output += "\n";
            }
            else
            {
                Log.i("", "    }),");

                output += "    }),";
                output += "\n";
            }

        }

        Log.i("", "});");

        output += "});";
        output += "\n";

        Log.i("", "______________________________________________");
        Log.i("", "______________________________________________");

        if (output.length() > 4000) {
            Log.i("", "output.length = " + output.length());
            Log.i("", "______________________________________________");
            Log.i("", "______________________________________________");
            int chunkCount = output.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= output.length()) {
                    Log.i("", "chunk " + i + " of " + chunkCount + ":" + output.substring(4000 * i));
                } else {
                    Log.i("", "chunk " + i + " of " + chunkCount + ":" + output.substring(4000 * i, max));
                }
            }
        } else {
            Log.i("", output.toString());
        }

        //Log.i("", output);

        Log.i("", "______________________________________________");
        Log.i("", "______________________________________________");
        mode[0] = 6;
        Log.i("Commit Character "," pressed");

        strokeCounter = 0;

        Character character = new Character(letter, strokes, true, false, 0, 0);
    }


    /*

    public void correct(View view) {


        db.updateRowRight(letter);
        soundPool.play(win, 1, 1, 1, 0, 1f);
        finish();
    }

    public void incorrect(View view) {

        db.updateRowWrong(letter);
        soundPool.play(lose, 1, 1, 1, 0, 1f);
        finish();
    }
*/

    public void onFragmentInteraction(View view){

        //Log.i("OnFragmentInteraction: ", "called");
    }
}
