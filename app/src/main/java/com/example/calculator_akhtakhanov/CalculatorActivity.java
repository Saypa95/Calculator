package com.example.calculator_akhtakhanov;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.EditText);
        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(display.getText().toString().equals("")){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
        }
        display.setSelection(cursorPos + 1);
    }

    public void btnClear(View view){
        display.setText("");
    }

    public void btnSkobky(View view){
        int cursorPos = display.getSelectionStart();
        int openSkb = 0;
        int closedSkb = 0;
        int textLen = display.getText().length();

        for (int i=0; i<cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openSkb++;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                closedSkb++;
            }
        }

        if (openSkb == closedSkb || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }
        else if (closedSkb < openSkb && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);

    }

    public void btnStepen(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("^");
        }
    }

    public void btnDelenie(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("/");
        }
    }

    public void btnSeven(View view){
        updateText("7");
    }

    public void btnEight(View view){
        updateText("8");
    }

    public void btnNine(View view){
        updateText("9");
    }

    public void btnUmnozhenie(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("x");
        }
    }

    public void btnFour(View view){
        updateText("4");
    }

    public void btnFive(View view){
        updateText("5");
    }

    public void btnSix(View view){
        updateText("6");
    }

    public void btnMinus(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("-");
        }
    }

    public void btnOne(View view){
        updateText("1");
    }

    public void btnTwo(View view){
        updateText("2");
    }

    public void btnThree(View view){
        updateText("3");
    }

    public void btnPlus(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("+");
        }
    }

    public void btnPlusMinus(View view){
        int cursorPos = display.getSelectionStart();
        String oldStr = display.getText().toString();
        String minus = "-";
        if(display.getText().toString().equals("")){
            display.setText("-");
            display.setSelection(display.getText().length());
        }
        else if (oldStr.charAt(0)=='-'){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(0, 1,"");
            display.setSelection(display.getText().length());
        }
        else{
            display.setText(String.format("%s%s",minus,oldStr));
            display.setSelection(display.getText().length());
        }
    }

    public void btnZero(View view){
        updateText("0");
    }

    public void btnZapyataya(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText(",");
        }
    }

    public void btnRavno(View view){
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("x", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void btnBackspace(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }


}
