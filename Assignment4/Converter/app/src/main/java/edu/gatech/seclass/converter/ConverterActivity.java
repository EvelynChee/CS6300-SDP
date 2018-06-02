package edu.gatech.seclass.converter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ConverterActivity extends AppCompatActivity {
    private RadioButton rbFrommile;
    private RadioButton rbFromft;
    private RadioButton rbFrominch;
    private RadioButton rbFromkm;
    private RadioButton rbFromm;
    private RadioButton rbFromcm;
    private RadioButton rbTomile;
    private RadioButton rbToft;
    private RadioButton rbToinch;
    private RadioButton rbTokm;
    private RadioButton rbTom;
    private RadioButton rbTocm;
    private EditText distValue;
    private EditText distResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        rbFrommile = (RadioButton) findViewById(R.id.rbFrommile);
        rbFromft = (RadioButton) findViewById(R.id.rbFromft);
        rbFrominch = (RadioButton) findViewById(R.id.rbFrominch);
        rbFromkm = (RadioButton) findViewById(R.id.rbFromkm);
        rbFromm = (RadioButton) findViewById(R.id.rbFromm);
        rbFromcm = (RadioButton) findViewById(R.id.rbFromcm);
        rbTomile = (RadioButton) findViewById(R.id.rbTomile);
        rbToft = (RadioButton) findViewById(R.id.rbToft);
        rbToinch = (RadioButton) findViewById(R.id.rbToinch);
        rbTokm = (RadioButton) findViewById(R.id.rbTokm);
        rbTom = (RadioButton) findViewById(R.id.rbTom);
        rbTocm = (RadioButton) findViewById(R.id.rbTocm);
        distValue = (EditText) findViewById(R.id.distValue);
        distResult = (EditText) findViewById(R.id.distResult);
    }

    public void handleClick(View view) {
        String unitFrom = "mi";
        String unitTo = "mi";
        switch (view.getId()) {
            case R.id.buttonConvert:
                String value = distValue.getText().toString();
                if (value.length() > 0) {
                    if (rbFromft.isChecked()) {
                        unitFrom = "ft";
                    }
                    if (rbFrominch.isChecked()) {
                        unitFrom = "inch";
                    }
                    if (rbFromkm.isChecked()) {
                        unitFrom = "km";
                    }
                    if (rbFromm.isChecked()) {
                        unitFrom = "m";
                    }
                    if (rbFromcm.isChecked()) {
                        unitFrom = "cm";
                    }

                    if (rbToft.isChecked()) {
                        unitTo = "ft";
                    }
                    if (rbToinch.isChecked()) {
                        unitTo = "inch";
                    }
                    if (rbTokm.isChecked()) {
                        unitTo = "km";
                    }
                    if (rbTom.isChecked()) {
                        unitTo = "m";
                    }
                    if (rbTocm.isChecked()) {
                        unitTo = "cm";
                    }
                    if (unitFrom.contentEquals(unitTo)) {
                        distResult.setText(value);
                    }
                    else {
                        if (unitFrom.contentEquals("mi")) {
                            if (unitTo.contentEquals("ft")) {
                                distResult.setText(miToft(value));
                            }
                            if (unitTo.contentEquals("inch")){
                                distResult.setText(miToinch(value));
                            }
                            if (unitTo.contentEquals("km")) {
                                distResult.setText(miTokm(value));
                            }
                            if (unitTo.contentEquals("m")) {
                                distResult.setText(miTom(value));
                            }
                            if (unitTo.contentEquals("cm")) {
                                distResult.setText(miTocm(value));
                            }
                        }
                        if (unitFrom.contentEquals("ft")) {
                            if (unitTo.contentEquals("mi")) {
                                distResult.setText(ftTomi(value));
                            }
                            if (unitTo.contentEquals("inch")){
                                distResult.setText(ftToinch(value));
                            }
                            if (unitTo.contentEquals("km")) {
                                distResult.setText(ftTokm(value));
                            }
                            if (unitTo.contentEquals("m")) {
                                distResult.setText(ftTom(value));
                            }
                            if (unitTo.contentEquals("cm")) {
                                distResult.setText(ftTocm(value));
                            }

                        }
                        if (unitFrom.contentEquals("inch")) {
                            if (unitTo.contentEquals("mi")) {
                                distResult.setText(inchTomi(value));
                            }
                            if (unitTo.contentEquals("ft")){
                                distResult.setText(inchToft(value));
                            }
                            if (unitTo.contentEquals("km")) {
                                distResult.setText(inchTokm(value));
                            }
                            if (unitTo.contentEquals("m")) {
                                distResult.setText(inchTom(value));
                            }
                            if (unitTo.contentEquals("cm")) {
                                distResult.setText(inchTocm(value));
                            }

                        }
                        if (unitFrom.contentEquals("km")) {
                            if (unitTo.contentEquals("mi")) {
                                distResult.setText(kmTomi(value));
                            }
                            if (unitTo.contentEquals("ft")){
                                distResult.setText(kmToft(value));
                            }
                            if (unitTo.contentEquals("inch")) {
                                distResult.setText(kmToinch(value));
                            }
                            if (unitTo.contentEquals("m")) {
                                distResult.setText(kmTom(value));
                            }
                            if (unitTo.contentEquals("cm")) {
                                distResult.setText(kmTocm(value));
                            }

                        }
                        if (unitFrom.contentEquals("m")) {
                            if (unitTo.contentEquals("mi")) {
                                distResult.setText(mTomi(value));
                            }
                            if (unitTo.contentEquals("ft")){
                                distResult.setText(mToft(value));
                            }
                            if (unitTo.contentEquals("inch")) {
                                distResult.setText(mToinch(value));
                            }
                            if (unitTo.contentEquals("km")) {
                                distResult.setText(mTokm(value));
                            }
                            if (unitTo.contentEquals("cm")) {
                                distResult.setText(mTocm(value));
                            }
                        }
                        if (unitFrom.contentEquals("cm")) {
                            if (unitTo.contentEquals("mi")) {
                                distResult.setText(cmTomi(value));
                            }
                            if (unitTo.contentEquals("ft")){
                                distResult.setText(cmToft(value));
                            }
                            if (unitTo.contentEquals("inch")) {
                                distResult.setText(cmToinch(value));
                            }
                            if (unitTo.contentEquals("km")) {
                                distResult.setText(cmTokm(value));
                            }
                            if (unitTo.contentEquals("m")) {
                                distResult.setText(cmTom(value));
                            }
                        }
                    }

                }
                else  {
                    Context context = getApplicationContext();
                    CharSequence text = "Empty value!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;
            case R.id.buttonReset:
                distValue.setText("");
                distResult.setText("");
                rbFrommile.setChecked(true);
                rbTomile.setChecked(true);
                break;
        }
    }

    public String miToft(String strmi) {
        double mi = Double.parseDouble(strmi);
        double ft = mi * 5280;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(ft));
    }

    public String miToinch(String strmi) {
        double mi = Double.parseDouble(strmi);
        double inch = mi * 63360;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(inch));
    }

    public String miTokm(String strmi) {
        double mi = Double.parseDouble(strmi);
        double km = mi * 1.6093;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(km));
    }

    public String miTom(String strmi) {
        double mi = Double.parseDouble(strmi);
        double m = mi * 1609.344;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(m));
    }

    public String miTocm(String strmi) {
        double mi = Double.parseDouble(strmi);
        double cm = mi * 160934.3989;
        DecimalFormat forcmat = new DecimalFormat("#.##");
        return String.valueOf(forcmat.format(cm));
    }

    public String ftTomi(String strft) {
        double ft = Double.parseDouble(strft);
        double mi = ft * 0.0002;
        DecimalFormat format = new DecimalFormat("#.####");
        return String.valueOf(format.format(mi));
    }

    public String ftToinch(String strft) {
        double ft = Double.parseDouble(strft);
        double inch = ft * 12;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(inch));
    }

    public String ftTokm(String strft) {
        double ft = Double.parseDouble(strft);
        double km = ft * 0.0003;
        DecimalFormat format = new DecimalFormat("#.####");
        return String.valueOf(format.format(km));
    }

    public String ftTom(String strft) {
        double ft = Double.parseDouble(strft);
        double m = ft * 0.3048;
        DecimalFormat format = new DecimalFormat("#.####");
        return String.valueOf(format.format(m));
    }

    public String ftTocm(String strft) {
        double ft = Double.parseDouble(strft);
        double cm = ft * 30.48;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(cm));
    }

    public String inchTomi(String strinch) {
        double inch = Double.parseDouble(strinch);
        double mi = inch * 1.578E-5;
        DecimalFormat format = new DecimalFormat("0.0E0");
        return String.valueOf(format.format(mi));
    }

    public String inchToft(String strinch) {
        double inch = Double.parseDouble(strinch);
        double ft = inch * 0.0833;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(ft));
    }

    public String inchTokm(String strinch) {
        double inch = Double.parseDouble(strinch);
        double km = inch * 2.54E-5;
        DecimalFormat format = new DecimalFormat("0.0E0");
        return String.valueOf(format.format(km));
    }

    public String inchTom(String strinch) {
        double inch = Double.parseDouble(strinch);
        double m = inch * 0.0254;
        DecimalFormat format = new DecimalFormat("#.####");
        return String.valueOf(format.format(m));
    }

    public String inchTocm(String strinch) {
        double inch = Double.parseDouble(strinch);
        double cm = inch * 2.54;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(cm));
    }

    public String kmTomi(String strkm) {
        double km = Double.parseDouble(strkm);
        double mi = km * 0.6214;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(mi));
    }

    public String kmToft(String strkm) {
        double km = Double.parseDouble(strkm);
        double ft = km * 3280.8399;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(ft));
    }

    public String kmToinch(String strkm) {
        double km = Double.parseDouble(strkm);
        double inch = km * 39370.079;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(inch));
    }

    public String kmTom(String strkm) {
        double km = Double.parseDouble(strkm);
        double m = km * 1000;
        DecimalFormat format = new DecimalFormat("#");
        return String.valueOf(format.format(m));
    }

    public String kmTocm(String strkm) {
        double km = Double.parseDouble(strkm);
        double cm = km * 100000;
        DecimalFormat format = new DecimalFormat("#");
        return String.valueOf(format.format(cm));
    }

    public String mTomi(String strm) {
        double m = Double.parseDouble(strm);
        double mi = m * 0.0006;
        DecimalFormat format = new DecimalFormat("#.####");
        return String.valueOf(format.format(mi));
    }

    public String mToft(String strm) {
        double m = Double.parseDouble(strm);
        double ft = m * 3.2808;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(ft));
    }

    public String mToinch(String strm) {
        double m = Double.parseDouble(strm);
        double inch = m * 39.3701;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(inch));
    }

    public String mTokm(String strm) {
        double m = Double.parseDouble(strm);
        double km = m * 0.001;
        DecimalFormat format = new DecimalFormat("#.###");
        return String.valueOf(format.format(km));
    }

    public String mTocm(String strm) {
        double m = Double.parseDouble(strm);
        double cm = m * 100;
        DecimalFormat format = new DecimalFormat("#");
        return String.valueOf(format.format(cm));
    }

    public String cmTomi(String strcm) {
        double cm = Double.parseDouble(strcm);
        double mi = cm * 6.214E-6;
        DecimalFormat format = new DecimalFormat("0.0E0");
        return String.valueOf(format.format(mi));
    }

    public String cmToft(String strcm) {
        double cm = Double.parseDouble(strcm);
        double ft = cm * 0.0328;
        DecimalFormat format = new DecimalFormat("#.####");
        return String.valueOf(format.format(ft));
    }

    public String cmToinch(String strcm) {
        double cm = Double.parseDouble(strcm);
        double inch = cm * 0.3937;
        DecimalFormat format = new DecimalFormat("#.####");
        return String.valueOf(format.format(inch));
    }

    public String cmTom(String strcm) {
        double cm = Double.parseDouble(strcm);
        double m = cm * 0.01;
        DecimalFormat format = new DecimalFormat("#.##");
        return String.valueOf(format.format(m));
    }

    public String cmTokm(String strcm) {
        double cm = Double.parseDouble(strcm);
        double km = cm * 1E-5;
        DecimalFormat format = new DecimalFormat("0.0E0");
        return String.valueOf(format.format(km));
    }

}
