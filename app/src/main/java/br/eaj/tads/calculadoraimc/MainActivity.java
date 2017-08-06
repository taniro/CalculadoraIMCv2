package br.eaj.tads.calculadoraimc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int PESO = 887;
    public static final int ALTURA = 889;

    public static final String TV_PESO = "TV_PESO";
    public static final String TV_ALTURA = "TV_ALTURA";
    public static final String TV_RESULTADO= "TV_RESULTADO";
    private static final String PREFS_NAME = "IMC_PREFS";

    TextView peso;
    TextView altura;
    TextView textResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = (TextView) findViewById(R.id.peso);
        altura = (TextView) findViewById(R.id.altura);
        textResult = (TextView) findViewById(R.id.resulttext);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE );
        String peso_text = settings.getString(TV_PESO, "0.0");
        String altura_text = settings.getString(TV_ALTURA, "0.0");
        String result_text = settings.getString(TV_RESULTADO, "");

        peso.setText(peso_text);
        altura.setText(altura_text);
        textResult.setText(result_text);

        Log.i("IMC", "onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("IMC", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("IMC", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("IMC", "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TV_PESO, peso.getText().toString());
        editor.putString(TV_ALTURA, altura.getText().toString());
        editor.putString(TV_RESULTADO, textResult.getText().toString());

        // Commit the edits!
        editor.commit();

        Log.i("IMC", "onStop");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TV_PESO, peso.getText().toString());
        outState.putString(TV_ALTURA, altura.getText().toString());
        outState.putString(TV_RESULTADO, textResult.getText().toString());

        Log.i("IMC", "onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        peso.setText(savedInstanceState.getString(TV_PESO));
        altura.setText(savedInstanceState.getString(TV_ALTURA));
        textResult.setText(savedInstanceState.getString(TV_RESULTADO));

        Log.i("IMC", "onRestoreInstanceState");
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
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void calcula(View v){

        Double num_peso = Double.parseDouble(peso.getText().toString());
        Double num_altura = Double.parseDouble(altura.getText().toString());

        Double resultado = num_peso / (num_altura * num_altura);

        textResult.setText(""+resultado);
    }
}
