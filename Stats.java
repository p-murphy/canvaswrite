package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Stats extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Object[] bundleArray = (Object[]) getIntent().getExtras().getSerializable("Array");
        String[][] recordArray = new String[26][4];
        int i=0;
        for(Object row : bundleArray)
        {
            recordArray[i][0] = ((String[])row)[0];
            recordArray[i][1] = ((String[])row)[1];
            recordArray[i][2] = ((String[])row)[2];
            recordArray[i][3] = ((String[])row)[3];
            i++;
        }

        /*String[][] bundleArray = (String[][]) getIntent().getExtras().getSerializable("Array");
        String[][] recordArray = new String[26][4];
        for(int i = 0; i < 26; i++)
        {
            recordArray[i][0] = bundleArray[i][0];
            recordArray[i][1] = bundleArray[i][1];
            recordArray[i][2] = bundleArray[i][2];
            recordArray[i][3] = bundleArray[i][3];
        }*/

        setStatText(recordArray);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
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

    public void setStatText(String[][] recordArray){

        TextView Aright = (TextView)findViewById(R.id.Aright);
        TextView Awrong = (TextView)findViewById(R.id.Awrong);
        TextView Apercentage = (TextView)findViewById(R.id.Apercentage);

        TextView Bright = (TextView)findViewById(R.id.Bright);
        TextView Bwrong = (TextView)findViewById(R.id.Bwrong);
        TextView Bpercentage = (TextView)findViewById(R.id.Bpercentage);

        TextView Cright = (TextView)findViewById(R.id.Cright);
        TextView Cwrong = (TextView)findViewById(R.id.Cwrong);
        TextView Cpercentage = (TextView)findViewById(R.id.Cpercentage);

        TextView Dright = (TextView)findViewById(R.id.Dright);
        TextView Dwrong = (TextView)findViewById(R.id.Dwrong);
        TextView Dpercentage = (TextView)findViewById(R.id.Dpercentage);

        TextView Eright = (TextView)findViewById(R.id.Eright);
        TextView Ewrong = (TextView)findViewById(R.id.Ewrong);
        TextView Epercentage = (TextView)findViewById(R.id.Epercentage);

        TextView Fright = (TextView)findViewById(R.id.Fright);
        TextView Fwrong = (TextView)findViewById(R.id.Fwrong);
        TextView Fpercentage = (TextView)findViewById(R.id.Fpercentage);

        TextView Gright = (TextView)findViewById(R.id.Gright);
        TextView Gwrong = (TextView)findViewById(R.id.Gwrong);
        TextView Gpercentage = (TextView)findViewById(R.id.Gpercentage);

        TextView Hright = (TextView)findViewById(R.id.Hright);
        TextView Hwrong = (TextView)findViewById(R.id.Hwrong);
        TextView Hpercentage = (TextView)findViewById(R.id.Hpercentage);

        TextView Iright = (TextView)findViewById(R.id.Iright);
        TextView Iwrong = (TextView)findViewById(R.id.Iwrong);
        TextView Ipercentage = (TextView)findViewById(R.id.Ipercentage);

        TextView Jright = (TextView)findViewById(R.id.Jright);
        TextView Jwrong = (TextView)findViewById(R.id.Jwrong);
        TextView Jpercentage = (TextView)findViewById(R.id.Jpercentage);

        TextView Kright = (TextView)findViewById(R.id.Kright);
        TextView Kwrong = (TextView)findViewById(R.id.Kwrong);
        TextView Kpercentage = (TextView)findViewById(R.id.Kpercentage);

        TextView Lright = (TextView)findViewById(R.id.Lright);
        TextView Lwrong = (TextView)findViewById(R.id.Lwrong);
        TextView Lpercentage = (TextView)findViewById(R.id.Lpercentage);

        TextView Mright = (TextView)findViewById(R.id.Mright);
        TextView Mwrong = (TextView)findViewById(R.id.Mwrong);
        TextView Mpercentage = (TextView)findViewById(R.id.Mpercentage);

        TextView Nright = (TextView)findViewById(R.id.Nright);
        TextView Nwrong = (TextView)findViewById(R.id.Nwrong);
        TextView Npercentage = (TextView)findViewById(R.id.Npercentage);

        TextView Oright = (TextView)findViewById(R.id.Oright);
        TextView Owrong = (TextView)findViewById(R.id.Owrong);
        TextView Opercentage = (TextView)findViewById(R.id.Opercentage);

        TextView Pright = (TextView)findViewById(R.id.Pright);
        TextView Pwrong = (TextView)findViewById(R.id.Pwrong);
        TextView Ppercentage = (TextView)findViewById(R.id.Ppercentage);

        TextView Qright = (TextView)findViewById(R.id.Qright);
        TextView Qwrong = (TextView)findViewById(R.id.Qwrong);
        TextView Qpercentage = (TextView)findViewById(R.id.Qpercentage);

        TextView Rright = (TextView)findViewById(R.id.Rright);
        TextView Rwrong = (TextView)findViewById(R.id.Rwrong);
        TextView Rpercentage = (TextView)findViewById(R.id.Rpercentage);

        TextView Sright = (TextView)findViewById(R.id.Sright);
        TextView Swrong = (TextView)findViewById(R.id.Swrong);
        TextView Spercentage = (TextView)findViewById(R.id.Spercentage);

        TextView Tright = (TextView)findViewById(R.id.Tright);
        TextView Twrong = (TextView)findViewById(R.id.Twrong);
        TextView Tpercentage = (TextView)findViewById(R.id.Tpercentage);

        TextView Uright = (TextView)findViewById(R.id.Uright);
        TextView Uwrong = (TextView)findViewById(R.id.Uwrong);
        TextView Upercentage = (TextView)findViewById(R.id.Upercentage);

        TextView Vright = (TextView)findViewById(R.id.Vright);
        TextView Vwrong = (TextView)findViewById(R.id.Vwrong);
        TextView Vpercentage = (TextView)findViewById(R.id.Vpercentage);

        TextView Wright = (TextView)findViewById(R.id.Wright);
        TextView Wwrong = (TextView)findViewById(R.id.Wwrong);
        TextView Wpercentage = (TextView)findViewById(R.id.Wpercentage);

        TextView Xright = (TextView)findViewById(R.id.Xright);
        TextView Xwrong = (TextView)findViewById(R.id.Xwrong);
        TextView Xpercentage = (TextView)findViewById(R.id.Xpercentage);

        TextView Yright = (TextView)findViewById(R.id.Yright);
        TextView Ywrong = (TextView)findViewById(R.id.Ywrong);
        TextView Ypercentage = (TextView)findViewById(R.id.Ypercentage);

        TextView Zright = (TextView)findViewById(R.id.Zright);
        TextView Zwrong = (TextView)findViewById(R.id.Zwrong);
        TextView Zpercentage = (TextView)findViewById(R.id.Zpercentage);

        Aright.setText(recordArray[0][2]);
        Awrong.setText(recordArray[0][3]);

        Bright.setText(recordArray[1][2]);
        Bwrong.setText(recordArray[1][3]);

        Cright.setText(recordArray[2][2]);
        Cwrong.setText(recordArray[2][3]);

        Dright.setText(recordArray[3][2]);
        Dwrong.setText(recordArray[3][3]);

        Eright.setText(recordArray[4][2]);
        Ewrong.setText(recordArray[4][3]);

        Fright.setText(recordArray[5][2]);
        Fwrong.setText(recordArray[5][3]);

        Gright.setText(recordArray[6][2]);
        Gwrong.setText(recordArray[6][3]);

        Hright.setText(recordArray[7][2]);
        Hwrong.setText(recordArray[7][3]);

        Iright.setText(recordArray[8][2]);
        Iwrong.setText(recordArray[8][3]);

        Jright.setText(recordArray[9][2]);
        Jwrong.setText(recordArray[9][3]);

        Kright.setText(recordArray[10][2]);
        Kwrong.setText(recordArray[10][3]);

        Lright.setText(recordArray[11][2]);
        Lwrong.setText(recordArray[11][3]);

        Mright.setText(recordArray[12][2]);
        Mwrong.setText(recordArray[12][3]);

        Nright.setText(recordArray[13][2]);
        Nwrong.setText(recordArray[13][3]);

        Oright.setText(recordArray[14][2]);
        Owrong.setText(recordArray[14][3]);

        Pright.setText(recordArray[15][2]);
        Pwrong.setText(recordArray[15][3]);

        Qright.setText(recordArray[16][2]);
        Qwrong.setText(recordArray[16][3]);

        Rright.setText(recordArray[17][2]);
        Rwrong.setText(recordArray[17][3]);

        Sright.setText(recordArray[18][2]);
        Swrong.setText(recordArray[18][3]);

        Tright.setText(recordArray[19][2]);
        Twrong.setText(recordArray[19][3]);

        Uright.setText(recordArray[20][2]);
        Uwrong.setText(recordArray[20][3]);

        Vright.setText(recordArray[21][2]);
        Vwrong.setText(recordArray[21][3]);

        Wright.setText(recordArray[22][2]);
        Wwrong.setText(recordArray[22][3]);

        Xright.setText(recordArray[23][2]);
        Xwrong.setText(recordArray[23][3]);

        Yright.setText(recordArray[24][2]);
        Ywrong.setText(recordArray[24][3]);

        Zright.setText(recordArray[25][2]);
        Zwrong.setText(recordArray[25][3]);

        setPercentage(Apercentage, Aright.getText().toString(), Awrong.getText().toString());
        setPercentage(Bpercentage, Bright.getText().toString(), Bwrong.getText().toString());
        setPercentage(Cpercentage, Cright.getText().toString(), Cwrong.getText().toString());
        setPercentage(Dpercentage, Dright.getText().toString(), Dwrong.getText().toString());
        setPercentage(Epercentage, Eright.getText().toString(), Ewrong.getText().toString());
        setPercentage(Fpercentage, Fright.getText().toString(), Fwrong.getText().toString());
        setPercentage(Gpercentage, Gright.getText().toString(), Gwrong.getText().toString());
        setPercentage(Hpercentage, Hright.getText().toString(), Hwrong.getText().toString());
        setPercentage(Ipercentage, Iright.getText().toString(), Iwrong.getText().toString());
        setPercentage(Jpercentage, Jright.getText().toString(), Jwrong.getText().toString());
        setPercentage(Kpercentage, Kright.getText().toString(), Kwrong.getText().toString());
        setPercentage(Lpercentage, Lright.getText().toString(), Lwrong.getText().toString());
        setPercentage(Mpercentage, Mright.getText().toString(), Mwrong.getText().toString());
        setPercentage(Npercentage, Nright.getText().toString(), Nwrong.getText().toString());
        setPercentage(Opercentage, Oright.getText().toString(), Owrong.getText().toString());
        setPercentage(Ppercentage, Pright.getText().toString(), Pwrong.getText().toString());
        setPercentage(Qpercentage, Qright.getText().toString(), Qwrong.getText().toString());
        setPercentage(Rpercentage, Rright.getText().toString(), Rwrong.getText().toString());
        setPercentage(Spercentage, Sright.getText().toString(), Swrong.getText().toString());
        setPercentage(Tpercentage, Tright.getText().toString(), Twrong.getText().toString());
        setPercentage(Upercentage, Uright.getText().toString(), Uwrong.getText().toString());
        setPercentage(Vpercentage, Vright.getText().toString(), Vwrong.getText().toString());
        setPercentage(Wpercentage, Wright.getText().toString(), Wwrong.getText().toString());
        setPercentage(Xpercentage, Xright.getText().toString(), Xwrong.getText().toString());
        setPercentage(Ypercentage, Yright.getText().toString(), Ywrong.getText().toString());
        setPercentage(Zpercentage, Zright.getText().toString(), Zwrong.getText().toString());
    }

    public void setPercentage(TextView per, String right, String wrong){

        int r = Integer.parseInt(right);
        int w = Integer.parseInt(wrong);
        int total = r + w;
        double ratio = (double) 100 / total;
        double p = (double) r * ratio;


        if(r == 0 && w == 0){
            per.setText("No data yet.");
            return;
        }
        else if (w == 0) {
            per.setText("100%");
            return;
        }
        else{
            per.setText(String.format("%.2f", p) + "%");
            return;
        }
    }
}
