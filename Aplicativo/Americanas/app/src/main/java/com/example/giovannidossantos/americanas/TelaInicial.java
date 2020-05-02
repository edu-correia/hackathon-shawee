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
    public static final int constTrat = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }

    public void Logar(View v){
        // Pegando os valores de input de senha e de email
        emailInput = (EditText) findViewById(R.id.emailView);
        senhaInput = (EditText) findViewById(R.id.senhaView);
        email = emailInput.getText().toString();
        senha = senhaInput.getText().toString();
        //Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();

        // Enviando os dados de email e da senha para a classe TratamentoDeDados
        Bundle params = new Bundle();
        params.putString("emailLogin", email);
        params.putString("senhaLogin", senha);
        params.putString("testeRemetente", "Login");
        Intent intent = new Intent(this, TratamentoDeDados.class);
        intent.putExtras(params);
        startActivityForResult(intent, constTrat);

    }

    public void irParaTelaDeRegistro(View v){
        Intent indoTelaRegistro = new Intent(getApplicationContext(), TelaDeRegistro.class);
        startActivity(indoTelaRegistro);
    }
}
