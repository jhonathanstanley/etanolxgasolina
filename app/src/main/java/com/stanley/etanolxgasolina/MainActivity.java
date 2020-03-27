package com.stanley.etanolxgasolina;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etEtanol, etGasolina;
    TextView tvEtanol, tvGasolina;
    Button btCalcular;
    ImageView ivResultado;
    String imgEtanol = "@drawable/etanol";
    String imgGasolina = "@drawable/gasolina";
    String imgStandard = "@drawable/standard";
    double dEtanol, dGasolina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEtanol = findViewById(R.id.etEtanol);
        etGasolina = findViewById(R.id.etGasolina);
        btCalcular = findViewById(R.id.btCalcular);
        ivResultado = findViewById(R.id.ivResultado);

        int imageResource = getResources().getIdentifier(imgStandard, null, getPackageName());
        Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        ivResultado.setImageDrawable(res);

    }

    public void calcularConsumo(View view) {
        ///Coletando valor do etanol e convertendo para double
        String sEtanol = etEtanol.getText().toString();
        String sGasolina = etGasolina.getText().toString();

        if (sEtanol == null || sEtanol.trim().equals("") || sGasolina == null || sGasolina.trim().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("O valor de etanol ou gasolina está vazio!");
            builder.setTitle("Erro!");
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{
            dEtanol = Float.parseFloat(sEtanol);
            dGasolina = Float.parseFloat(sGasolina);

            ///Calculando 70% do valor da gasolina para comparar com o valor do Etanol
            double calculoGasolina = dGasolina * 0.7;

            if (dEtanol > calculoGasolina){
                int imageResource = getResources().getIdentifier(imgGasolina, null, getPackageName());
                Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
                ivResultado.setImageDrawable(res);
            }
            else{
                int imageResource = getResources().getIdentifier(imgEtanol, null, getPackageName());
                Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
                ivResultado.setImageDrawable(res);
            }
        }
    }
}
