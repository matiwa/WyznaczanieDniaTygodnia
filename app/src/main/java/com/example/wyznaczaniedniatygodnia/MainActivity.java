package com.example.wyznaczaniedniatygodnia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText ed,em,er;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        ed=findViewById(R.id.edzien);
        em=findViewById(R.id.emiesiac);
        er=findViewById(R.id.erok);
    }

    //tablica z nazwami dni tygodnia
    public static String tydzien[] =
            {"poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela"};

    //tablica z liczbą dni od początku roku (nieprzestepnego) dla kolejnych miesiecy
    public static int liczbaDni[] =
            {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    //true jezeli podany rok jest przestepny,
//false w przeciwnym wypadku
    public static boolean przestepny(int rok) {
        return ((rok % 4 == 0  &&  rok % 100 != 0) || rok % 400 == 0);
    }

    //dla podanej daty wyznacza dzien tygodnia
//0 - poniedziałek, 1 - wtorek, ... 6 - niedziela
    public static int dzienTygodnia(int dzien, int miesiac, int rok) {
        int dzienRoku;
        int yy, c, g;
        int wynik;

        dzienRoku = dzien + liczbaDni[miesiac-1];
        if ((miesiac > 2) && (przestepny(rok) == true))
            dzienRoku++;

        yy = (rok - 1) % 100;
        c = (rok - 1) - yy;
        g = yy + (yy / 4);
        wynik = (((((c / 100) % 4) * 5) + g) % 7);
        wynik += dzienRoku - 1;
        wynik %= 7;

        return wynik;
    }

    public void wyznacz(View view){
        int d,m,r;
        String bufor="";
        try{
            d=Integer.parseInt(ed.getText().toString());
            m=Integer.parseInt(em.getText().toString());
            r=Integer.parseInt(er.getText().toString());
            bufor="Dnia "+d+"."+m+"."+r+" jest "+tydzien[dzienTygodnia(d,m,r)];
            Toast.makeText(this,bufor,Toast.LENGTH_LONG).show();
        }catch (Exception blad){
            Toast.makeText(this,blad.getMessage(),Toast.LENGTH_LONG).show();
            tv.setText("");
        }finally {
            tv.setText(bufor);
            ed.setText("");
            em.setText("");
            er.setText("");
        }
    }
}