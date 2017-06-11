package br.opet.projeto.models;

import java.util.Date;

public class BaterPonto {

	private String nmUsuario;
	private String nmSenha;

	private String acaoFuncionario;
	private String diaTrabalho;
	private Date horaEntrada;
	private Date horaSaidaIntervalo;
	private Date horaRetornoIntervalo;
	private Date horaSaida;

	private String dataFazerAlteracao;
	private String horaFazerAlteracao;
	private String observacao;
	private int status;

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getNmSenha() {
		return nmSenha;
	}

	public void setNmSenha(String nmSenha) {
		this.nmSenha = nmSenha;
	}

	public String getAcaoFuncionario() {
		return acaoFuncionario;
	}

	public void setAcaoFuncionario(String acaoFuncionario) {
		this.acaoFuncionario = acaoFuncionario;
	}

	public String getDiaTrabalho() {
		return diaTrabalho;
	}

	public void setDiaTrabalho(String diaTrabalho) {
		this.diaTrabalho = diaTrabalho;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaidaIntervalo() {
		return horaSaidaIntervalo;
	}

	public void setHoraSaidaIntervalo(Date horaSaidaIntervalo) {
		this.horaSaidaIntervalo = horaSaidaIntervalo;
	}

	public Date getHoraRetornoIntervalo() {
		return horaRetornoIntervalo;
	}

	public void setHoraRetornoIntervalo(Date horaRetornoIntervalo) {
		this.horaRetornoIntervalo = horaRetornoIntervalo;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getDataFazerAlteracao() {
		return dataFazerAlteracao;
	}

	public void setDataFazerAlteracao(String dataFazerAlteracao) {
		this.dataFazerAlteracao = dataFazerAlteracao;
	}

	public String getHoraFazerAlteracao() {
		return horaFazerAlteracao;
	}

	public void setHoraFazerAlteracao(String horaFazerAlteracao) {
		this.horaFazerAlteracao = horaFazerAlteracao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
