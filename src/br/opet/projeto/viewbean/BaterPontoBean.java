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
	
	/**
	 * Metodo que busca o cpf no banco e retorna uma lista
	 * com o ponto completo daquele funcionario
	 * @return
	 */
	public List<BaterPonto> listarPontoFuncionario(){
		BaterPontoController bpc = new BaterPontoController();
		for (BaterPonto bp : bpc.procurarPorChave(bp.getNmUsuario())) {
			System.out.println("DATA: " + bp.getHoraEntrada());
		}
		
		return bpc.procurarPorChave(bp.getNmUsuario());
	}

	/**
	 * Metodo responsavel por toda a operacao do funcionario
	 * executar login e bater o ponto
	 */
	public void baterPonto() {
		// Inicio do metodo
		System.out.println("User: " + bp.getNmUsuario());
		BaterPontoController bpc = new BaterPontoController();
		if (bpc.cadastrarNoBanco(bp)) {
			
			if(bp.getAcaoFuncionario().equals("ACESSO_SIMPLES")){
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
