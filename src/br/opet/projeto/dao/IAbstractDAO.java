package br.opet.projeto.dao;

import java.util.List;

/**
 * 
 * @author Tiago Iwamoto
 * @email  tiago.iwamoto@opet.edu.br
 * 
 * Classe abstrata com as operacoes basicas do sistema
 * @param <T>
 */
public interface IAbstractDAO<T> {
	
	/**
	 * 
	 * @return uma lista com os valores gravados nela
	 */
	List<T> listarTodos();
	
	/**
	 * 
	 * @param chave
	 * @return uma lista com valores gravados nela.
	 */
	List<T> procurarPorChave(String chave);
	
	/**
	 * 
	 * @param entidade
	 * @return retorna um valor booleano (Verdadeiro ou falso)
	 */
	boolean cadastrarNoBanco(T entidade);
	
	/**
	 * 
	 * @param entidade
	 * @return retorna um valor booleano (Verdadeiro ou falso)
	 */
	boolean atualizarValorNoBanco(T entidade);
	
	/**
	 * 
	 * @param entidade
	 * @return retorna um valor booleano (Verdadeiro ou falso)
	 */
	boolean deletarValorNoBanco(T entidade);
}
