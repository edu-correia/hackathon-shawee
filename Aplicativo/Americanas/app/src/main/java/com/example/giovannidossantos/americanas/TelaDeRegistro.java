package com.example.giovannidossantos.americanas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelaDeRegistro extends AppCompatActivity {

    public String email, senha, confirmSenha;
    EditText emailInput, senhaInput, confirmInput;
    public static final int constTratam = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_registro);
    }

    public void registrar(View v){
        emailInput = (EditText) findViewById(R.id.emailRegistro);
        senhaInput = (EditText) findViewById(R.id.senhaRegistro);
        confirmInput = (EditText) findViewById(R.id.senhaConfirm);
        email = emailInput.getText().toString();
        senha = senhaInput.getText().toString();
        confirmSenha = confirmInput.getText().toString();
        if (!senha.equals(confirmSenha)){
            Toast.makeText(getApplicationContext(), "A confirmação tem que ser igual a senha", Toast.LENGTH_SHORT).show();
        }
        else if (senha.length() < 8){
            Toast.makeText(getApplicationContext(), "A senha precisa ter no mínimo 8 caracteres", Toast.LENGTH_SHORT).show();
        }
        else{
            // Enviando os dados de email e senha para a classe Tratamento de Dados, onde vai ser registrada uma nova conta
            Bundle parametros = new Bundle();
            parametros.putString("emailRegistro", email);
            parametros.putString("senhaRegistro", senha);
            parametros.putString("testeRemetente", "Registro");
            Intent intent1 = new Intent(this, TratamentoDeDados.class);
            intent1.putExtras(parametros);
            startActivityForResult(intent1, constTratam);

        }

    }

}
