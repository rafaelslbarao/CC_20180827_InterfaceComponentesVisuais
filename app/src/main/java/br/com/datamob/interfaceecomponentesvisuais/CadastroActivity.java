package br.com.datamob.interfaceecomponentesvisuais;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.datamob.interfaceecomponentesvisuais.data.Universidade;
import br.com.datamob.interfaceecomponentesvisuais.dialogs.PopupInformacao;

public class CadastroActivity extends AppCompatActivity
{
    public static final int RESULT_DELETED = 100;
    public static final int RESULT_UPDATED = 200;

    private TextInputEditText etCodigo;
    private TextInputEditText etNome;
    private TextInputEditText etCidade;
    private TextInputEditText etData;
    private Button btAdicionar;
    private Button btAtualizar;
    private Button btRemover;
    private Spinner spEstado;

    private ArrayList<Universidade> universidadeArrayList;
    private Universidade universidade;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        carregaParametros();
        inicializaComponentes();
        inicializaEventos();
        carregaValoresConfiguraTela();
    }

    private void carregaParametros()
    {
        universidadeArrayList = (ArrayList<Universidade>) getIntent().getSerializableExtra(MainActivity.EXTRA_LISTA);
        universidade = (Universidade) getIntent().getSerializableExtra(MainActivity.EXTRA_UNIVERSIDADE);
    }

    private void inicializaComponentes()
    {
        etCodigo = findViewById(R.id.etCodigo);
        etNome = findViewById(R.id.etNome);
        etCidade = findViewById(R.id.etCidade);
        etData = findViewById(R.id.etData);
        btAdicionar = findViewById(R.id.btAdicionar);
        btAtualizar = findViewById(R.id.btAtualizar);
        btRemover = findViewById(R.id.btRemover);
        spEstado = findViewById(R.id.spEstado);
        //
        carregaEstados();
    }

    private void inicializaEventos()
    {
        etCodigo.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                etCodigo.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        etNome.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                etNome.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        etCidade.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                etCidade.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        etData.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                etData.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        btAdicionar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                adicionaUniversidade();
            }
        });
        btRemover.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                removeUniversidade();
            }
        });
        btAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                atualizaUniversidade();
            }
        });
    }

    private void carregaEstados()
    {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.estado));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spEstado.setAdapter(arrayAdapter);

    }

    private void atualizaUniversidade()
    {
        if (!validaTela())
            return;

        //
        universidade.setCidade(etCidade.getText().toString());
        universidade.setEstado(spEstado.getSelectedItem().toString());
        universidade.setNome(etNome.getText().toString());
        universidade.setData(etData.getText().toString());
        //
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_UNIVERSIDADE, universidade);
        setResult(RESULT_UPDATED, intent);
        finish();
    }

    private void removeUniversidade()
    {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_UNIVERSIDADE, universidade);
        setResult(RESULT_DELETED, intent);
        finish();
    }

    private void adicionaUniversidade()
    {
        if (!validaTela())
            return;
        //
        Universidade universidade = new Universidade();
        universidade.setCodigo(Long.valueOf(etCodigo.getText().toString()));
        universidade.setCidade(etCidade.getText().toString());
        universidade.setEstado(spEstado.getSelectedItem().toString());
        universidade.setNome(etNome.getText().toString());
        universidade.setData(etData.getText().toString());
        //
        Intent intentRetorno = new Intent();
        intentRetorno.putExtra(MainActivity.EXTRA_UNIVERSIDADE, universidade);
        setResult(RESULT_OK, intentRetorno);
        finish();
    }

    private boolean validaTela()
    {
        if (etCodigo.getText().toString().trim().equals(""))
        {
            etCodigo.setError(getString(R.string.InformeCodigo));
            return false;
        }

        if (universidadeArrayList != null)
        {
            Long codigo = Long.valueOf(etCodigo.getText().toString());
            for (Universidade universidade : universidadeArrayList)
            {
                if (universidade.getCodigo().equals(codigo))
                {
                    etCodigo.setError(getString(R.string.CodigoExistente));
                    return false;
                }
            }
        }

        if (etNome.getText().toString().trim().equals(""))
        {
            etNome.setError(getString(R.string.InformeNome));
            return false;
        }

        if (etCidade.getText().toString().trim().equals(""))
        {
            etCidade.setError(getString(R.string.InformeCidade));
            return false;
        }

        if (spEstado.getSelectedItemPosition() <= 0)
        {
            PopupInformacao.mostraMensagem(this, R.string.InformeEstado);
            return false;
        }

        if (etData.getText().toString().trim().equals(""))
        {
            etData.setError(getString(R.string.InformeData));
            return false;
        }


        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.parse(etData.getText().toString());
        } catch (Exception ex)
        {
            etData.setError(getString(R.string.DataInvalida));
            return false;
        }

        return true;
    }

    private void carregaValoresConfiguraTela()
    {
        if (universidade != null)
        {
            etCodigo.setText(universidade.getCodigo().toString());
            etCodigo.setEnabled(false);
            //
            etNome.setText(universidade.getNome());
            //
            etCidade.setText(universidade.getCidade());
            //
            etData.setText(universidade.getData());
            //
            for (int i = 0; i < spEstado.getCount(); i++)
            {
                if (spEstado.getItemAtPosition(i).toString().equals(universidade.getEstado()))
                {
                    spEstado.setSelection(i);
                    break;
                }
            }
            //
            btAtualizar.setVisibility(View.VISIBLE);
            btRemover.setVisibility(View.VISIBLE);
        }
        else
            btAdicionar.setVisibility(View.VISIBLE);
    }
}
