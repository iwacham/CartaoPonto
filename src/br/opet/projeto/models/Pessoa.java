package br.opet.projeto.models;

import java.util.Date;

/**
 * 
 * @author Tiago Iwamoto
 * @email tiago.iwamoto@opet.edu.br
 * 
 *        Classe Model de pessoa, esta e abstrata por se tratar de um modelo.
 */
public abstract class Pessoa {

	// Abaixo serao declarados os atributos que serao usados em nosso projeto
	private String cpf;
	private Date dtNasc;
	private String nome;
	private int telefone;

	/**
	 * Construtor vazio da classe Pessoa
	 */
	public Pessoa() {

	}

	/**
	 * Construtor que recebe todos os atributos da classe PESSOA pelos
	 * parametros abaixo.
	 * 
	 * @param cpf
	 * @param dtNasc
	 * @param nome
	 * @param telefone
	 */
	public Pessoa(String cpf, Date dtNasc, String nome, int telefone) {
		super();
		this.cpf = cpf;
		this.dtNasc = dtNasc;
		this.nome = nome;
		this.telefone = telefone;
	}

	/**
	 * Retorna o valor gravado no atributo cpf (se houver um) se não, retorna
	 * null.
	 * 
	 * @return
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Recebe um valor por parametro e grava no atributo cpf.
	 * 
	 * @param cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Retorna o valor gravado no atributo dtNasc (se houver um) se não, retorna
	 * null.
	 * 
	 * @return
	 */
	public Date getDtNasc() {
		return dtNasc;
	}

	/**
	 * Recebe um valor por parametro e grava no atributo dtNasc.
	 * 
	 * @param dtNasc
	 */
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	/**
	 * Retorna o valor gravado no atributo nome (se houver um) se não, retorna
	 * null.
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recebe um valor por parametro e grava no atributo nome.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o valor gravado no atributo telefone (se houver um) se não,
	 * retorna null.
	 * 
	 * @return
	 */
	public int getTelefone() {
		return telefone;
	}

	/**
	 * Recebe um valor por parametro e grava no atributo telefone.
	 * 
	 * @param telefone
	 */
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	/**
	 * Este metodo apenas ira mostrar na console os valores gravados nos
	 * atributos Apenas se for chamado.
	 */
	public void mostraValoresGravados() {
		System.out.println("CPF: " + this.cpf);
		System.out.println("Data Nascimento: " + this.dtNasc);
		System.out.println("Nome: " + this.nome);
		System.out.println("Telefone: " + this.telefone);
	}

}
