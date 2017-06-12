package br.opet.projeto.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.javafx.cursor.StandardCursorFrame;

import br.opet.projeto.dao.IAbstractDAO;
import br.opet.projeto.models.BaterPonto;
import br.opet.projeto.util.DbConnect;

public class BaterPontoController implements IAbstractDAO<BaterPonto> {

	@Override
	public List<BaterPonto> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo responsavel por buscar no banco e me retorna uma lista.
	 */
	@Override
	public List<BaterPonto> procurarPorChave(String chave) {
		// Instancio a classe DbConnect para poder usar seus metodos
		DbConnect dbconn = new DbConnect();
		System.out.println("CPF: " + chave);
		// Inicio uma Connection como nula. para que todo o escopo do metodo
		// possa usa la.
		Connection conn = null;

		// Crio uma variavel booleana e inicio a mesma como false
		// Esta variavel sera modificada durate a execução do metodo e sera
		// retornada
		List<BaterPonto> lista = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		// tento iniciar uma conexao e inserir os valores na lista
		try {
			conn = dbconn.getConnection();
			ResultSet rs = dbconn.getResultSet(conn, "SELECT * FROM TBL_TIMECARD WHERE CPF = '" + chave + "'");
			while (rs.next()) {
				BaterPonto bp = new BaterPonto();

				bp.setNmUsuario(rs.getString("CPF"));

				System.out.println(sdf.format(rs.getTimestamp("HORA_ENTRADA").getTime()));

				bp.setHoraEntrada(rs.getTimestamp("HORA_ENTRADA"));

				bp.setHoraSaidaIntervalo(rs.getTimestamp("HORA_SAIDA_INTERVALO"));

				bp.setHoraRetornoIntervalo(rs.getTimestamp("HORA_RETORNO_INTERVALO"));

				bp.setHoraSaida(rs.getTimestamp("HORA_SAIDA"));

				bp.setDiaTrabalho(rs.getString("DATA_PONTO"));

				lista.add(bp);

			}

			/*
			 * Se algum erro ocorrer durante a execucao do bloco acima ira cair
			 * no bloco catch que ira imprimir o resultado na console.
			 */
		} catch (Exception e) {
			e.printStackTrace();

		}

		/*
		 * Por fim verificamos se a conexao esta diferente de nula e se sim
		 * encerramo a mesma.
		 */
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return lista;
	}

	/**
	 * Metodo que insere no banco de dados verifica se as informações de login
	 * estão corretas e se estiverem cai em um switch para verificar qual a ação
	 * desejada
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

					if (st.executeUpdate() != 0) {
						resultado = true;
					} else {
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

	
	/**
	 * Faz a listagem das horas que serao contestadas pelos funcionarios
	 * @return
	 */
	public List<BaterPonto> listarHorasContestadas() {
		// Instancio a classe DbConnect para poder usar seus metodos
		DbConnect dbconn = new DbConnect();
		// Inicio uma Connection como nula. para que todo o escopo do metodo
		// possa usa la.
		Connection conn = null;

		// Crio uma variavel booleana e inicio a mesma como false
		// Esta variavel sera modificada durate a execução do metodo e sera
		// retornada
		List<BaterPonto> lista = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		// tento iniciar uma conexao e inserir os valores na lista
		try {
			conn = dbconn.getConnection();
			ResultSet rs = dbconn.getResultSet(conn, "SELECT * FROM TBL_TIMECARD WHERE OBSERVACAO IS NOT NULL");
			while (rs.next()) {
				BaterPonto bp = new BaterPonto();
				
				bp.setNmUsuario(rs.getString("CPF"));

				System.out.println(sdf.format(rs.getTimestamp("HORA_ENTRADA").getTime()));

				bp.setHoraEntrada(rs.getTimestamp("HORA_ENTRADA"));

				bp.setHoraSaidaIntervalo(rs.getTimestamp("HORA_SAIDA_INTERVALO"));

				bp.setHoraRetornoIntervalo(rs.getTimestamp("HORA_RETORNO_INTERVALO"));

				bp.setHoraSaida(rs.getTimestamp("HORA_SAIDA"));

				bp.setDiaTrabalho(rs.getString("DATA_PONTO"));

				bp.setHoraFazerAlteracao(rs.getString("HORA_FAZER_ALTERACAO"));
				
				bp.setDataFazerAlteracao(rs.getString("DATA_PONTO"));
				
				bp.setObservacao(rs.getString("OBSERVACAO"));
				
				bp.setStatus(rs.getInt("STATUS"));


				lista.add(bp);

			}

			/*
			 * Se algum erro ocorrer durante a execucao do bloco acima ira cair
			 * no bloco catch que ira imprimir o resultado na console.
			 */
		} catch (Exception e) {
			e.printStackTrace();

		}

		/*
		 * Por fim verificamos se a conexao esta diferente de nula e se sim
		 * encerramo a mesma.
		 */
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return lista;
	}
	
	/**
	 * Metodo responsavel por gravar no banco as horas que os funcionarios
	 * irao contestar.
	 * @param bp
	 * @return
	 */
	public boolean gravarHorasContestadas(BaterPonto bp){
		DbConnect db = new DbConnect();
		Connection conn = null;
		boolean resultado = false;
		
		
		System.out.println("CPF: " + bp.getNmUsuario());
		System.out.println("OBS: " + bp.getObservacao());
		System.out.println("TIPO: " + bp.getHoraFazerAlteracao());
		System.out.println("DATA PONTO: " + bp.getDiaTrabalho());
		
		
		
		try{
			conn = db.getConnection();
			PreparedStatement st = db.getPreparedStatement(conn,
					"UPDATE TBL_TIMECARD SET HORA_FAZER_ALTERACAO = ?, OBSERVACAO = ?, STATUS = ? WHERE CPF = ? AND DATA_PONTO = ?");
			st.setString(1, bp.getHoraFazerAlteracao());
			st.setString(2, bp.getObservacao());
			st.setInt(3, 1);
			st.setString(4, bp.getNmUsuario());
			st.setString(5, bp.getDiaTrabalho());
			
			if (st.executeUpdate() != 0) {
				resultado = true;
			} else {
				resultado = false;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}

}
