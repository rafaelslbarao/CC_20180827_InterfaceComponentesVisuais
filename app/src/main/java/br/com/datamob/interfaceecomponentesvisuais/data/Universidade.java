package br.com.datamob.interfaceecomponentesvisuais.data;

public class Universidade
{
    private Long codigo;
    private String nome;
    private String cidade;
    private String estado;
    private String data;

    public Universidade(Long codigo, String nome, String cidade, String estado, String data)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.data = data;
    }

    public Universidade()
    {
    }

    public Long getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Long codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
