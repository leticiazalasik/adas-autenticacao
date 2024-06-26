package br.com.autenticacao.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.*;
import java.math.*;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private Boolean isativo;

	public Usuario() {

	}

	public Usuario(int id, String nome, String email, String senha, Boolean isativo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.isativo = isativo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getisativo() {
		return isativo;
	}

	public void setisativo(Boolean isativo) {
		this.isativo = isativo;
	}

    

}
