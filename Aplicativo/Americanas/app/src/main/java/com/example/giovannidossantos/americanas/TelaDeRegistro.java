package com.example.giovannidossantos.americanas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelaDeRegistro extends AppCompatActivity {

    String email, senha, confirmSenha;
    EditText emailInput, senhaInput, confirmInput;

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
        Toast.makeText(getApplicationContext(), senha + " | " + confirmSenha, Toast.LENGTH_SHORT).show();
        if (!senha.equals(confirmSenha)){
            Toast.makeText(getApplicationContext(), "A confirmação tem que ser igual a senha", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Conta criada com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

}
