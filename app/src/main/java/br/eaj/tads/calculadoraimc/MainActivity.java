package br.eaj.tads.calculadoraimc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int PESO = 887;
    public static final int ALTURA = 889;

    TextView peso;
    TextView altura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = (TextView) findViewById(R.id.peso);
        altura = (TextView) findViewById(R.id.altura);
    }

    public void cliquePeso(View v){

        Intent i = new Intent(this, Main2Activity.class);

        Bundle params = new Bundle();

        params.putString("dado", peso.getText().toString());
        params.putBoolean("tipo", true);

        i.putExtras(params);

        startActivityForResult(i, PESO);

    }

    public void cliqueAltura(View v){

        Intent i = new Intent(this, Main2Activity.class);

        Bundle params = new Bundle();

        params.putString("dado", altura.getText().toString());
        params.putBoolean("tipo", false);

        i.putExtras(params);

        startActivityForResult(i, ALTURA);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PESO){
            if (resultCode == RESULT_OK){
                peso.setText(data.getStringExtra("resultado"));

            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == ALTURA){
            if (resultCode == RESULT_OK){
                altura.setText(data.getStringExtra("resultado"));

            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Cancelado2 testetestets", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void calcula(View v){

        Double num_peso = Double.parseDouble(peso.getText().toString());
        Double num_altura = Double.parseDouble(altura.getText().toString());

        Double resultado = num_peso / (num_altura * num_altura);

        TextView textResult = (TextView) findViewById(R.id.resulttext);
        textResult.setText(""+resultado);
    }
}
