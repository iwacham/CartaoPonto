package br.opet.projeto.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.opet.projeto.dao.IAbstractDAO;
import br.opet.projeto.models.BaterPonto;
import br.opet.projeto.util.DbConnect;

public class BaterPontoController implements IAbstractDAO<BaterPonto> {

	@Override
	public List<BaterPonto> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaterPonto> procurarPorChave(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Metodo que insere no banco de dados
	 * verifica se as informações de login estão corretas
	 * e se estiverem cai em um switch para verificar qual a ação desejada
	 */
	@Override
	public boolean cadastrarNoBanco(BaterPonto entidade) {
		DbConnect db = new DbConnect();
		Connection conn = null;
		boolean resultado = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();
		try {
			conn = db.getConnection();
			System.out.println("NOME: " + entidade.getNmUsuario());
			System.out.println("NOME: " + entidade.getNmSenha());
			ResultSet rs = db.getResultSet(conn, "SELECT CPF, SENHA FROM TBL_FUNCIONARIO WHERE CPF = '"
					+ entidade.getNmUsuario() + "' AND SENHA = '" + entidade.getNmSenha() + "'");
			if (rs.next()) {
				System.out.println("ACAO: " + entidade.getAcaoFuncionario());
				
				switch (entidade.getAcaoFuncionario()) {
				case "HORA_ENTRADA":
					
					PreparedStatement st = db.getPreparedStatement(conn,
							"INSERT INTO TBL_TIMECARD (CPF, DATA_PONTO, HORA_ENTRADA, STATUS) VALUES (?, ?, ?, ?)");
					st.setString(1, entidade.getNmUsuario());
					st.setString(2, sdf.format(dt));
					st.setTimestamp(3, new java.sql.Timestamp(dt.getTime()));
					st.setInt(4, 0);
					
					if(st.executeUpdate() != 0){
						resultado = true;
					}else{
						resultado = false;
					}
					break;

				case "HORA_SAIDA_INTERVALO":
					st = db.getPreparedStatement(conn,
							"UPDATE TBL_TIMECARD SET HORA_SAIDA_INTERVALO = ? WHERE CPF = ? AND DATA_PONTO = ?");
					st.setTimestamp(1, new java.sql.Timestamp(dt.getTime()));
					st.setString(2, entidade.getNmUsuario());
					st.setString(3, sdf.format(dt));

					if (st.executeUpdate() != 0) {
						resultado = true;
					} else {
						resultado = false;
					}

					
					break;

				case "HORA_RETORNO_INTERVALO":
					st = db.getPreparedStatement(conn,
							"UPDATE TBL_TIMECARD SET HORA_RETORNO_INTERVALO = ? WHERE CPF = ? AND DATA_PONTO = ?");
					st.setTimestamp(1, new java.sql.Timestamp(dt.getTime()));
					st.setString(2, entidade.getNmUsuario());
					st.setString(3, sdf.format(dt));

					if (st.executeUpdate() != 0) {
						resultado = true;
					} else {
						resultado = false;
					}


					break;

				case "HORA_SAIDA":
					st = db.getPreparedStatement(conn,
							"UPDATE TBL_TIMECARD SET HORA_SAIDA = ? WHERE CPF = ? AND DATA_PONTO = ?");
					st.setTimestamp(1, new java.sql.Timestamp(dt.getTime()));
					st.setString(2, entidade.getNmUsuario());
					st.setString(3, sdf.format(dt));

					if (st.executeUpdate() != 0) {
						resultado = true;
					} else {
						resultado = false;
					}


					break;
					
				case "ACESSO_SIMPLES":
					resultado = true;
					break;

				default:
					break;
				}
	
			} else {
				resultado = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	@Override
	public boolean atualizarValorNoBanco(BaterPonto entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletarValorNoBanco(BaterPonto entidade) {
		// TODO Auto-generated method stub
		return false;
	}

}
