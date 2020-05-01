package com.example.giovannidossantos.americanas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelaInicial extends AppCompatActivity {
    public String email, senha;
    EditText emailInput, senhaInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
    }

    public void Logar(View v){
        // Pegando os valores de input de senha e de email
        emailInput = (EditText) findViewById(R.id.emailView);
        senhaInput = (EditText) findViewById(R.id.senhaView);
        email = emailInput.getText().toString();
        senha = senhaInput.getText().toString();
    }

    public void irParaTelaDeRegistro(View v){
        Intent indoTelaRegistro = new Intent(getApplicationContext(), TelaDeRegistro.class);
        startActivity(indoTelaRegistro);
    }


}
