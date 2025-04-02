package br.unit;

public class Pessoa {
    private String nome;
    private String sexo;
    private String idade;

    public Pessoa(String nome, String sexo, String idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome + " (" + sexo + ", " + idade + " anos)";
    }
}