/**
 * Autor: Tiago Henrique Iwamoto
 * Email: tiago.iwamoto@opet.edu.br
 * Bean respons�vel pela comunica��o entre a VIEW e a CONTROLLER
 * 
 */

package br.opet.projeto.viewbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.opet.projeto.controllers.FuncionarioController;
import br.opet.projeto.models.Funcionario;

/**
 * Crio o mapeamento usando o @ManagedBean
 * 
 * @author Tiago Iwamoto
 *
 */
@ManagedBean(name = "fBean")
@SessionScoped
public class FuncionarioBean {

	private Funcionario func = new Funcionario();

	/**
	 * Seta o objeto funcionario com o objeto recebido por parametro e chama a
	 * pagina que fara a exclus�o do funcion�rio
	 * 
	 * @param f
	 * @return
	 */
	public String chamarPaginaExclusao(Funcionario f) {
		this.func = f;

		return "excluirFuncionario";
	}

	/**
	 * Metodo que faz a comunica��o entre a VIEW e o controller
	 */
	public void excluirFuncionario() {
		// Inicio do metodo

		FuncionarioController fnc = new FuncionarioController();

		if (fnc.deletarValorNoBanco(func)) {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Cadastro removido com sucesso!&status=OK");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Erro ao remover cadastro!&status=BAD");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Fim do metodo
	}

	/**
	 * Seta o objeto funcionario com o objeto recebido por parametro e chama a
	 * pagina que fara a altera��o do funcion�rio
	 * 
	 * @param f
	 * @return
	 */
	public String chamarPaginaAlteracao(Funcionario f) {
		this.func = f;
		return "editarFuncionario";
	}

	/**
	 * Metodo que faz a comunica��o entre a VIEW e o controller
	 */
	public void alterarCadastroFuncionario() {
		// Inicio do metodo

		FuncionarioController fnc = new FuncionarioController();

		if (fnc.atualizarValorNoBanco(func)) {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Cadastro alterado com sucesso!&status=OK");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Erro ao alterar cadastro!&status=BAD");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Fim do metodo
	}

	/**
	 * Metodo que ira buscar na controller todos os funcionarios cadastrados
	 * grava em uma lista e retorna para a view
	 * 
	 * @return
	 */
	public List<Funcionario> listarFuncionarios() {
		FuncionarioController fnc = new FuncionarioController();

		return fnc.listarTodos();
	}

	/**
	 * Metodo que insere no banco de dados e redireciona para pagina de sucesso
	 * ou erro!
	 */
	public void cadastrarFuncionario() {
		// Inicio do metodo

		FuncionarioController fnc = new FuncionarioController();

		if (fnc.cadastrarNoBanco(func)) {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Cadastro efetuado com sucesso!&status=OK");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Erro ao cadastrar!&status=BAD");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Fim do metodo
	}

	/**
	 * metodo que retorna o objeto funcionario
	 * 
	 * @return
	 */
	public Funcionario getFunc() {
		return func;
	}

	/**
	 * Recebo um objeto de funcionario instancio um novo funcionario e gravo o
	 * valor do objeto recebido no funcionario.
	 * 
	 * @param f
	 */
	public void setFunc(Funcionario f) {
		this.func = f;
	}

	/**
	 * Metodo respons�vel por limpar os valores gravados no objeto
	 * @return
	 */
	public String limparObjetos(){
		this.func = new Funcionario();
		return "listarFuncionarios";
	}
}