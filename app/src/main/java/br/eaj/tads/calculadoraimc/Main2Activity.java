package br.eaj.tads.calculadoraimc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView label;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        label = (TextView) findViewById(R.id.label);
        edit = (EditText) findViewById(R.id.edit);

        Bundle dados = getIntent().getExtras();

        Boolean isPeso = dados.getBoolean("tipo");
        String editValue = dados.getString("dado");

        if (isPeso){
            label.setText("Peso:");
        }else{
            label.setText("Altura:");
        }

        edit.setText(editValue);
    }

    public void clickOk(View v){
        Intent i = new Intent();
        Bundle param = new Bundle();

        param.putString("resultado", edit.getText().toString());

        i.putExtras(param);

        setResult(RESULT_OK, i);
        finish();
    }

    public void clickCancela (View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}
