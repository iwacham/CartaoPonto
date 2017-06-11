/**
 * Autor: Tiago Henrique Iwamoto
 * Email: tiago.iwamoto@opet.edu.br
 * Bean responsável pela comunicação entre a VIEW e a CONTROLLER
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

	// Implementar sistema de login
	private static final String ADM_USER = "adm";
	private static final String ADM_SENHA = "1234";

	private String admUser;
	private String admSenha;

	private String buscarFuncionario;
	private Funcionario func = new Funcionario();

	public String verificarAdmin() {

		String resultadoLogin = null;
		if (this.admUser.equals(ADM_USER) && this.admSenha.equals(ADM_SENHA)) {
			resultadoLogin = "listarHorasRegistradas";
		} else {
			resultadoLogin = "index";
		}

		return resultadoLogin;
	}

	public String chamarPaginaBusca() {
		return "resultadoBusca";
	}

	public List<Funcionario> buscaFuncionario() {
		FuncionarioController fnc = new FuncionarioController();
		System.out.println("Busca: " + buscarFuncionario);
		return fnc.procurarPorChave(buscarFuncionario);

	}

	/**
	 * Seta o objeto funcionario com o objeto recebido por parametro e chama a
	 * pagina que fara a exclusão do funcionário
	 * 
	 * @param f
	 * @return
	 */
	public String chamarPaginaExclusao(Funcionario f) {
		this.func = f;

		return "excluirFuncionario";
	}

	/**
	 * Metodo que faz a comunicação entre a VIEW e o controller
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
	 * pagina que fara a alteração do funcionário
	 * 
	 * @param f
	 * @return
	 */
	public String chamarPaginaAlteracao(Funcionario f) {
		this.func = f;
		return "editarFuncionario";
	}

	/**
	 * Metodo que faz a comunicação entre a VIEW e o controller
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

	public String getBuscarFuncionario() {
		return buscarFuncionario;
	}

	public void setBuscarFuncionario(String buscarFuncionario) {
		this.buscarFuncionario = buscarFuncionario;
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
	 * Metodo responsável por limpar os valores gravados no objeto
	 * 
	 * @return
	 */
	public String limparObjetos() {
		this.func = new Funcionario();
		return "listarFuncionarios";
	}

	public String getAdmUser() {
		return admUser;
	}

	public void setAdmUser(String admUser) {
		this.admUser = admUser;
	}

	public String getAdmSenha() {
		return admSenha;
	}

	public void setAdmSenha(String admSenha) {
		this.admSenha = admSenha;
	}

}
