package com.example.giovannidossantos.americanas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v4.app.SupportActivity.ExtraData.*;

import java.util.ArrayList;

public class TratamentoDeDados extends AppCompatActivity {

    ArrayList<String> emails = new ArrayList<>();
    ArrayList<String> senhas = new ArrayList<>();
    boolean dadosCorretos;
    Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emails.add("oi@bomdia.com");
        senhas.add("a1b2c3d4e9");
        emails.add("giocndo@hotmail.com");
        senhas.add("buenosdias");

        Intent intent = getIntent();
        if (intent != null){
            params = intent.getExtras();
            if (params != null){
                if (params.getString("testeRemetente").equals("Login")){ // Testando se os dados estão vindo da tela de login
                    dadosCorretos = testaDados(params.getString("emailLogin"), params.getString("senhaLogin"));
                    if (dadosCorretos){
                        Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Email e/ou senha incorreto(s)", Toast.LENGTH_SHORT).show();
                    }
                }
                else{ // Dados estão vindo da tela de registro de uma nova conta
                    emails.add(params.getString("emailRegistro"));
                    senhas.add(params.getString("senhaRegistro"));
                }
            }
        }

    }

    private boolean testaDados(String email, String senha){
        boolean testeEmail = false;
        int index = 0;
        //Testando se o email informado na tela de Login é válido(já está registrado)
        for (int i = 0; i < emails.size(); i++){
            if (emails.get(i).equals(email)){
                //Toast.makeText(getApplicationContext(), "email correto", Toast.LENGTH_SHORT).show();
                index = i;
                testeEmail = true;
                break;
            }
        }
        if (!testeEmail){
            return false;
        }
        //Testando se asenha colocada corresponde ao email inserido
        return senhas.get(index).equals(senha);
    }

}
