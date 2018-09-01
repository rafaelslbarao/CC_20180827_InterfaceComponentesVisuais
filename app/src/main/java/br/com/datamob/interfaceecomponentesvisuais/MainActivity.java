package br.com.datamob.interfaceecomponentesvisuais;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.datamob.interfaceecomponentesvisuais.data.Universidade;

public class MainActivity extends AppCompatActivity
{
    public static final int REQUEST_CADASTRO = 1;
    public static final int REQUEST_ATUALIZACAO = 2;
    public static final String EXTRA_UNIVERSIDADE = "br.com.datamob.interfaceecomponentesvisuais.UNIVERSIDADE";
    public static final String EXTRA_LISTA = "br.com.datamob.interfaceecomponentesvisuais.LISTA";
    private ArrayList<Universidade> universidadeArrayList = new ArrayList<>();
    private FloatingActionButton fabAdicionar;
    private ListView lvUniversidades;

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
        inicializaComponentes();
        carregaListaUniversidades();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CADASTRO:
                if (resultCode == RESULT_OK)
                {
                    universidadeArrayList.add((Universidade) data.getSerializableExtra(EXTRA_UNIVERSIDADE));
                    ((ArrayAdapter) lvUniversidades.getAdapter()).notifyDataSetChanged();
                }
                break;
            case REQUEST_ATUALIZACAO:
                switch (resultCode)
                {
                    case CadastroActivity.RESULT_DELETED:
                        universidadeArrayList.remove((Universidade) data.getSerializableExtra(EXTRA_UNIVERSIDADE));
                        ((ArrayAdapter) lvUniversidades.getAdapter()).notifyDataSetChanged();
                        break;
                    case CadastroActivity.RESULT_UPDATED:
                        int index = universidadeArrayList.indexOf((Universidade) data.getSerializableExtra(EXTRA_UNIVERSIDADE));
                        universidadeArrayList.remove(index);
                        universidadeArrayList.add(index, (Universidade) data.getSerializableExtra(EXTRA_UNIVERSIDADE));
                        ((ArrayAdapter) lvUniversidades.getAdapter()).notifyDataSetChanged();
                        break;
                }
                break;
        }
    }

    private void inicializaComponentes()
    {
        fabAdicionar = findViewById(R.id.fabAdicionar);
        lvUniversidades = findViewById(R.id.lvUniversidades);
        inicializaEventos();
    }

    private void inicializaEventos()
    {
        fabAdicionar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                abreTelaParaCadastro();
            }
        });
        //
        lvUniversidades.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Universidade universidadeSelecionada = (Universidade) lvUniversidades.getAdapter().getItem(position);
                abreTelaParaAtualizacao(universidadeSelecionada);
            }
        });
    }

    private void carregaListaUniversidades()
    {
        ArrayAdapter<Universidade> universidadeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, universidadeArrayList);
        lvUniversidades.setAdapter(universidadeArrayAdapter);
    }

    private void abreTelaParaCadastro()
    {
        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra(EXTRA_LISTA, universidadeArrayList);
        startActivityForResult(intent, REQUEST_CADASTRO);
    }

    private void abreTelaParaAtualizacao(Universidade universidade)
    {
        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra(EXTRA_UNIVERSIDADE, universidade);
        startActivityForResult(intent, REQUEST_ATUALIZACAO);
    }

}
