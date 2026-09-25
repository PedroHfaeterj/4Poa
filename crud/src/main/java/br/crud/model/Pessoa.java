package br.crud.model;

public class Pessoa {
    
    private int id;
    private String nome;
    private int idade;
    private String genero;
    private String cpf;

    public Pessoa() {
    }

    public Pessoa(String nome, int idade, String genero, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.cpf = cpf;
    }

    public Pessoa(int id, String nome, int idade, String genero, String cpf) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.cpf = cpf;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Idade: " + idade + " | Gênero: " + genero + " | CPF: " + cpf;
    }
}