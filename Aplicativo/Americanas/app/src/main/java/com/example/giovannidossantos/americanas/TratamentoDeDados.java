package com.example.giovannidossantos.americanas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TratamentoDeDados extends AppCompatActivity {

    ArrayList<String> emails = new ArrayList<>();
    ArrayList<String> senhas = new ArrayList<>();
    boolean dadosCorretos;
    Bundle params, statLog;
    Intent voltaTelaOrigem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        carregaListasSalvas();

        Intent intent = getIntent();
        if (intent != null){
            params = intent.getExtras();
            if (params != null){
                statLog = new Bundle();
                if (params.getString("testeRemetente").equals("Login")){ // Testando se os dados estão vindo da tela de login
                    dadosCorretos = testaLogin(params.getString("emailLogin"), params.getString("senhaLogin"));
                    if (dadosCorretos){
                        Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                        statLog.putBoolean("loginFuncionou", true);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Email e/ou senha incorreto(s)", Toast.LENGTH_SHORT).show();
                        statLog.putBoolean("loginFuncionou", false);
                    }
                    voltaTelaOrigem = new Intent(this, TelaDeLogin.class);
                    voltaTelaOrigem.putExtras(statLog);
                    startActivityForResult(voltaTelaOrigem, 1);
                }
                else{ // Dados estão vindo da tela de registro de uma nova conta
                    if (testaRegistro(params.getString("emailRegistro"))){
                        Toast.makeText(getApplicationContext(), "Conta criada com sucesso, favor realizar o login", Toast.LENGTH_SHORT).show();
                        emails.add(params.getString("emailRegistro"));
                        senhas.add(params.getString("senhaRegistro"));
                        salvarDados();
                        // Como o registro da conta funcionou, volta para a tela de login, para o usuário poder realizá-lo
                        voltaTelaOrigem = new Intent(this, TelaDeLogin.class);
                        startActivity(voltaTelaOrigem);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Email já cadastrado", Toast.LENGTH_SHORT).show();
                        voltaTelaOrigem = new Intent(this, TelaDeRegistro.class);
                        startActivity(voltaTelaOrigem);
                    }
                }
            }
        }

    }

    private boolean testaLogin(String email, String senha){
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

    private boolean testaRegistro(String email){
        for (int i = 0; i < emails.size(); i++){
            if (emails.get(i).equals(email)){
                return false; // Caso encontrar um email igual, já registrado, ao que o usuário está tentando registrar retorna falso
            }
        }
        return true;
    }


    private void salvarDados(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(emails), json2 = gson.toJson(senhas);
        editor.putString("lista de emails", json);
        editor.putString("lista de senhas", json2);
        editor.apply();
    }

    private void carregaListasSalvas(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("lista de emails", null), json2 = sharedPreferences.getString("lista de senhas", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType(), type2 = new TypeToken<ArrayList<String>>() {}.getType();
        emails = gson.fromJson(json, type);
        senhas = gson.fromJson(json2, type2);
    }



}
