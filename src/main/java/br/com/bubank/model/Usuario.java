package br.com.bubank.model;

import java.util.Date;

public class Usuario extends Pessoa {
    // atributos
    private String senha;
    private String username;
    private boolean ativo;
    private boolean admin;
    private final Date dataCadastro;
    
    // construtor
    public Usuario(String senha, String username, Pessoa pessoa) {
        super(pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getEndereco(), pessoa.getCidade(), pessoa.getEstado(), pessoa.getCep());
        this.senha = senha;
        this.username = username;
        this.ativo = true;
        this.admin = false;
        this.dataCadastro = new Date();
    }
    
    // getters e setters
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    public Date getDataCadastro() { return dataCadastro; }
}


    