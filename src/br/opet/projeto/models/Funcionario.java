package br.opet.projeto.models;

import java.util.Date;

/**
 * 
 * @author Tiago Iwamoto
 * @email tiago.iwamoto@opet.edu.br
 *
 *        Esta classe estende Pessoa para ter acesso aos seus atributos e assim
 *        poder detalher mais nosso modelo de trabalho.
 */
public class Funcionario extends Pessoa {

	// Atributos que serao usados por nossa classe funcionario
	private String cargo;
	private String nomeUsuario;
	private String senhaUsuario;

	/**
	 * Construtor vazio para a classe FUNCIONARIO
	 */
	public Funcionario() {
		super();
	}

	/**
	 * Construtor que recebe os valores da classe pai PESSOA e grava o valor no
	 * atributo da classe Funcionario
	 * 
	 * @param cpf
	 * @param dtNasc
	 * @param nome
	 * @param telefone
	 * @param cargo
	 */
	public Funcionario(String cpf, Date dtNasc, String nome, int telefone, String cargo, String nomeUsuario, String senhaUsuario) {
		super(cpf, dtNasc, nome, telefone);
		this.cargo = cargo;
		this.nomeUsuario = nomeUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	/**
	 * Retorna o valor gravado no atributo cargo (se houver um) se não, retorna
	 * null.
	 * 
	 * @return
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Recebe um valor por parametro e grava no atributo cargo.
	 * 
	 * @param cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Retorna o valor gravado no atributo nomeUsuario (se houver um) se não, retorna
	 * null.
	 * 
	 * @return
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * Recebe um valor por parametro e grava no atributo nomeUsuario.
	 * 
	 * @param nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	/**
	 * Retorna o valor gravado no atributo senhaUsuario (se houver um) se não, retorna
	 * null.
	 * 
	 * @return
	 */
	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	/**
	 * Recebe um valor por parametro e grava no atributo senhaUsuario.
	 * 
	 * @param senhaUsuario
	 */
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

}
