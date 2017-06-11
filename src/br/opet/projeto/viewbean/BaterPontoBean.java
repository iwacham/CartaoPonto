package br.opet.projeto.viewbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.opet.projeto.controllers.BaterPontoController;
import br.opet.projeto.controllers.FuncionarioController;
import br.opet.projeto.models.BaterPonto;

@ManagedBean(name = "bpBean")
@SessionScoped
public class BaterPontoBean {

	private BaterPonto bp = new BaterPonto();

	public String chamarPaginaContestar(BaterPonto f){
		this.bp = f;
		System.out.println("DATA DIA: " + bp.getDiaTrabalho());
		return "contestarHorario";
	}
	
	public void gravarHoraContestada(){
		BaterPontoController bpc = new BaterPontoController();
		
		System.out.println("DATA BEAN: " + bp.getDiaTrabalho());

		if (bpc.gravarHorasContestadas(bp)) {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Registro efetuado com sucesso!&status=OK");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Erro ao registrar!&status=BAD");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Fim do metodo
		
	}
	
	public List<BaterPonto> listarHorasContestadas() {
		BaterPontoController bpc = new BaterPontoController();

		return bpc.listarHorasContestadas();
	}

	/**
	 * Metodo que busca o cpf no banco e retorna uma lista com o ponto completo
	 * daquele funcionario
	 * 
	 * @return
	 */
	public List<BaterPonto> listarPontoFuncionario() {
		BaterPontoController bpc = new BaterPontoController();

		return bpc.procurarPorChave(bp.getNmUsuario());
	}

	/**
	 * Metodo responsavel por toda a operacao do funcionario executar login e
	 * bater o ponto
	 */
	public void baterPonto() {
		// Inicio do metodo
		System.out.println("User: " + bp.getNmUsuario());
		BaterPontoController bpc = new BaterPontoController();
		if (bpc.cadastrarNoBanco(bp)) {

			if (bp.getAcaoFuncionario().equals("ACESSO_SIMPLES")) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/CartaoPontoOpet/pages/usr/base/listarCartaoPonto.xhtml");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Registro efetuado com sucesso!&status=OK");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/CartaoPontoOpet/pages/msg.xhtml?msg=Erro ao registrar!&status=BAD");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Fim do metodo
	}

	public BaterPonto getBp() {
		return bp;
	}

	public void setBp(BaterPonto bp) {
		this.bp = bp;
	}

}
