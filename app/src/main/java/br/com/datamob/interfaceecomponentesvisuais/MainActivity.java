package br.com.datamob.interfaceecomponentesvisuais;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import br.com.datamob.interfaceecomponentesvisuais.data.Universidade;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<Universidade> universidadeArrayList = new ArrayList<>();

    /*
    Criar aplicativo para cadastros de universidades
    A universidade deve conter código, nome, cidade, estado, data inauguração
    Deverá possuir duas telas, uma que apresente todos as universidades cadastradas ordenados por nome
    Outra que permita inserir, atualizar ou remover a universidade
     */


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fabAdicionar).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });
    }

}
