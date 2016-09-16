package com.memes.drversace.dexcalc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    int plateau = 0;
    int savedml = 10;
    private GoogleApiClient client;
    private Toast memetoast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView pp = (TextView) findViewById(R.id.textView5);
        final TextView dxmper = (TextView) findViewById(R.id.textView6);
        final TextView mlpill = (TextView) findViewById(R.id.textView7);
        //final Button calc = (Button) findViewById(R.id.button);
        RadioGroup plateauselect = (RadioGroup) findViewById(R.id.radioGroup);
        RadioGroup wieghttype = (RadioGroup) findViewById(R.id.radioGroup2);
        EditText weight = (EditText) findViewById(R.id.editText);
        final EditText mgmeme = (EditText) findViewById(R.id.editText2);
        final EditText mlmeme = (EditText) findViewById(R.id.editText3);
        final Switch pillsyrup = (Switch) findViewById(R.id.switch1);
        Button triptimer = (Button) findViewById(R.id.button);
        final Button tripnotes = (Button) findViewById(R.id.button2);
        memetoast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);


        if (!pillsyrup.isChecked()) {
            pillsyrup.setText("Syrup   ");
            dxmper.setText("mg DXM per");
            mlpill.setText("mL");
            mlmeme.setEnabled(true);

        } else {
            pillsyrup.setText("Gel-caps   ");
            dxmper.setText("mg DXM per");
            mlpill.setText("gel-cap");
            mlmeme.setText("1");
            mlmeme.setEnabled(false);
        }

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Using DXM recreationally may result in health problems. The developers assume no responsibility for your actions, this app is for educational purposes only. Use at your own risk.");
        dlgAlert.setTitle("WARNING");
        dlgAlert.setPositiveButton("I understand", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismisses
            }
        });
        dlgAlert.setCancelable(false);
        dlgAlert.create().show();

        pillsyrup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!pillsyrup.isChecked()) {
                    pillsyrup.setText("Syrup   ");
                    dxmper.setText("mg DXM per");
                    mlpill.setText("mL");
                    mlmeme.setText(String.valueOf(savedml));
                    mlmeme.setEnabled(true);


                } else {
                    pillsyrup.setText("Gel-caps   ");
                    try {
                        savedml = Integer.parseInt(mlmeme.getText().toString());
                    } catch (Exception e) {

                    }
                    dxmper.setText("mg DXM per");
                    mlpill.setText("gel-cap");
                    mlmeme.setText("1");
                    mlmeme.setEnabled(false);
                }
                calculate();
            }
        });


        seekBar.setProgress(0);
        seekBar.incrementProgressBy(1);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pp.setText((String.valueOf(progress) + "%"));
                plateau = progress;

                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        plateauselect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculate();
            }
        });

        wieghttype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculate();
            }
        });

      /*  calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
*/
        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mgmeme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mlmeme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        triptimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                // Intent timerintent = new Intent("android.intent.action.TIMER");
                // startActivity(timerintent);
                memetoast.setText("Coming Soon");
                memetoast.show();


            }
        });

        tripnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                memetoast.setText("Coming Soon");
                memetoast.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.dxmfaq:
                Intent browserIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dxm.darkridge.com/faq/contents.html"));
                startActivity(browserIntent1);

            case R.id.fourm:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.reddit.com/r/dxm/"));
                startActivity(browserIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    public void calculate() {

        RadioButton one = (RadioButton) findViewById(R.id.radioButton);
        RadioButton two = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton three = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton four = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton kgt = (RadioButton) findViewById(R.id.radioButton6);
        TextView min = (TextView) findViewById(R.id.min);
        TextView max = (TextView) findViewById(R.id.max);
        TextView usenet = (TextView) findViewById(R.id.usenet);
        TextView gels = (TextView) findViewById(R.id.gels);
        TextView select = (TextView) findViewById(R.id.select);
        TextView dxmper = (TextView) findViewById(R.id.textView6);
        TextView mlpill = (TextView) findViewById(R.id.textView7);
        EditText weight = (EditText) findViewById(R.id.editText);
        EditText mgmeme = (EditText) findViewById(R.id.editText2);
        EditText mlmeme = (EditText) findViewById(R.id.editText3);
        Switch pillsyrup = (Switch) findViewById(R.id.switch1);
        memetoast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);



        float lbs = 0;
        float kg;
        try {
            lbs = Integer.parseInt(weight.getText().toString());
        } catch (Exception e) {
            if (weight.getText().toString().isEmpty()) {
            } else {
               // Toast.makeText(getApplicationContext(), "Invalid Weight", Toast.LENGTH_SHORT).show();
                memetoast.setText("Invalid Weight");
                memetoast.show();
            }
        }
        if (kgt.isChecked()) {
            kg = lbs;
        } else {
            kg = (float) (lbs * 0.453592);
        }
        float plateaumg = (float) (plateau * 0.01);
        if (one.isChecked()) {

            double minmg1 = (kg * 1.5);
            double maxmg1 = (kg * 2.5);
            double usenetmg1 = (kg * 2.7);
            double selectmg1 = (((maxmg1 - minmg1) * plateaumg) + minmg1);
            double gelcaps1 = 0;
            try {
                gelcaps1 = selectmg1 / (Double.parseDouble(mgmeme.getText().toString()) / Integer.parseInt(mlmeme.getText().toString()));
            } catch (Exception e) {
            }
            min.setText("Minimum: " + new BigDecimal(String.valueOf(minmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            max.setText("Maximum: " + new BigDecimal(String.valueOf(maxmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            usenet.setText("Usenet: " + new BigDecimal(String.valueOf(usenetmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            select.setText("User Selection: " + new BigDecimal(String.valueOf(selectmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");

            if (!pillsyrup.isChecked()) {

                gels.setText("Syrup Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mL");
            } else {
                gels.setText("Gel-caps Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN));
            }


        }
        if (two.isChecked()) {

            double minmg1 = (kg * 2.5);
            double maxmg1 = (kg * 7.5);
            double usenetmg1 = (kg * 6.4);
            double selectmg1 = (((maxmg1 - minmg1) * plateaumg) + minmg1);
            double gelcaps1 = 0;
            try {
                gelcaps1 = selectmg1 / (Double.parseDouble(mgmeme.getText().toString()) / Integer.parseInt(mlmeme.getText().toString()));
            } catch (Exception e) {
            }
            min.setText("Minimum: " + new BigDecimal(String.valueOf(minmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            max.setText("Maximum: " + new BigDecimal(String.valueOf(maxmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            usenet.setText("Usenet: " + new BigDecimal(String.valueOf(usenetmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            select.setText("User Selection: " + new BigDecimal(String.valueOf(selectmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            if (!pillsyrup.isChecked()) {
                gels.setText("Syrup Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mL");
            } else {
                gels.setText("Gel-caps Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN));
            }
        }
        if (three.isChecked()) {

            double minmg1 = (kg * 7.5);
            double maxmg1 = (kg * 15);
            double usenetmg1 = (kg * 9.4);
            double selectmg1 = (((maxmg1 - minmg1) * plateaumg) + minmg1);
            double gelcaps1 = 0;
            try {
                gelcaps1 = selectmg1 / (Double.parseDouble(mgmeme.getText().toString()) / Integer.parseInt(mlmeme.getText().toString()));
            } catch (Exception e) {
            }
            min.setText("Minimum: " + new BigDecimal(String.valueOf(minmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            max.setText("Maximum: " + new BigDecimal(String.valueOf(maxmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            usenet.setText("Usenet: " + new BigDecimal(String.valueOf(usenetmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            select.setText("User Selection: " + new BigDecimal(String.valueOf(selectmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            if (!pillsyrup.isChecked()) {
                gels.setText("Syrup Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mL");
            } else {
                gels.setText("Gel-caps Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN));
            }

        }
        if (four.isChecked()) {

            double minmg1 = (kg * 15);
            double maxmg1 = (kg * 20);
            double usenetmg1 = (kg * 18);
            double selectmg1 = (((maxmg1 - minmg1) * plateaumg) + minmg1);
            double gelcaps1 = 0;
            try {
                gelcaps1 = selectmg1 / (Double.parseDouble(mgmeme.getText().toString()) / Integer.parseInt(mlmeme.getText().toString()));
            } catch (Exception e) {
            }
            min.setText("Minimum: " + new BigDecimal(String.valueOf(minmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            max.setText("Maximum: " + new BigDecimal(String.valueOf(maxmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            usenet.setText("Usenet: " + new BigDecimal(String.valueOf(usenetmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            select.setText("User Selection: " + new BigDecimal(String.valueOf(selectmg1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mg");
            if (!pillsyrup.isChecked()) {
                gels.setText("Syrup Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN) + " mL");
            } else {
                gels.setText("Gel-caps Required: " + new BigDecimal(String.valueOf(gelcaps1)).setScale(1, BigDecimal.ROUND_HALF_DOWN));
            }
        }
    }
}
