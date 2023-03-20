package com.example.hesapmakinesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    String islem = "";
    TextView sonucTextView;
    EditText editTextIslem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sonucTextView = findViewById(R.id.text_sonuc);
        editTextIslem = findViewById(R.id.editTextIslem);
        editTextIslem.setShowSoftInputOnFocus(false);
        editTextIslem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    public void anyButton (View view){
        switch (view.getId()){
            case R.id.btntemizle: editTextIslem.setText(""); break;
            case R.id.btnparantez: parantezEkle(); break;
            case R.id.btnbol:      updateEdittext("/"); break;
            case R.id.btntopla:      updateEdittext("+"); break;
            case R.id.btncikart:      updateEdittext("-"); break;
            case R.id.btncarp:      updateEdittext("*"); break;
            case R.id.btnnokta:      updateEdittext("."); break;
            case R.id.btnus:     updateEdittext("^"); break;
            case R.id.btn0:      updateEdittext("0"); break;
            case R.id.btn1:      updateEdittext("1"); break;
            case R.id.btn2:      updateEdittext("2"); break;
            case R.id.btn3:      updateEdittext("3"); break;
            case R.id.btn4:      updateEdittext("4"); break;
            case R.id.btn5:      updateEdittext("5"); break;
            case R.id.btn6:      updateEdittext("6"); break;
            case R.id.btn7:      updateEdittext("7"); break;
            case R.id.btn8:      updateEdittext("8"); break;
            case R.id.btn9:      updateEdittext("9"); break;
            case R.id.karaktersil: karakterSil(); break;
            case R.id.btnsonuchesapla: sonucHesapla(); break;
            
            
        }


    }

    private void sonucHesapla() {
        boolean isCallSuccessful = License.iConfirmNonCommercialUse("John Doe");
        boolean isConfirmed = License.checkIfUseTypeConfirmed();
        String message = License.getUseTypeConfirmationMessage();
        mXparser.consolePrintln("isCallSuccessful = " + isCallSuccessful);
        mXparser.consolePrintln("isConfirmed = " + isConfirmed);

        String islemler = editTextIslem.getText().toString();
        Expression ifade = new Expression(islemler);
        String sonuc = String.valueOf(ifade.calculate()).toString();
        sonucTextView.setText(sonuc);

    }

    private void karakterSil() {
        int cursor = editTextIslem.getSelectionStart();

        if (cursor > 0){
            String editTextEski = editTextIslem.getText().toString();
            String editTextSoldaKalan = editTextEski.substring(0,cursor - 1);
            String editTextSagdaKalan = editTextEski.substring(cursor);
            String editTextYeni = editTextSoldaKalan + "" + editTextSagdaKalan;
            editTextIslem.setText(editTextYeni);
            editTextIslem.setSelection(cursor - 1);

        }
    }

    private void parantezEkle() {
        int cursor = editTextIslem.getSelectionStart();
        String editText = editTextIslem.getText().toString();
        int parantezSayisi = 0;

        for(int i = 0; i < editText.length(); i++ ){
            if (editText.substring(i,i+1).equalsIgnoreCase("(")) parantezSayisi++;
            if (editText.substring(i,i+1).equalsIgnoreCase(")")) parantezSayisi--;
        }
        String  sonKarakter = null;
        if(editText.length() != 0){
            sonKarakter = editText.substring(editText.length() - 1);
            
        }
        if (parantezSayisi == 0 || sonKarakter.equals("(")) updateEdittext("(");
        else if (parantezSayisi > 0 && !sonKarakter.equals(")")) updateEdittext(")");


    }

    private void updateEdittext(String karakterEkle) {
        int cursor = editTextIslem.getSelectionStart();
        String editTextEski = editTextIslem.getText().toString();
        String editTextSoldaKalan = editTextEski.substring(0,cursor);
        String editTextSagdaKalan = editTextEski.substring(cursor);
        String editTextYeni = editTextSoldaKalan + karakterEkle + editTextSagdaKalan;
        editTextIslem.setText(editTextYeni);
        editTextIslem.setSelection(cursor + 1);



    }


}