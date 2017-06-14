package br.opet.projeto.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.opet.projeto.dao.IAbstractDAO;
import br.opet.projeto.models.Funcionario;
import br.opet.projeto.util.DbConnect;

/**
 * 
 * @author Tiago Iwamoto
 * @email tiago.iwamoto@opet.edu.br
 * 
 *        Classe responsavel por fazer a comunicacao entre a VIEW e o BANCO DE
 *        DADOS.
 */
public class FuncionarioController implements IAbstractDAO<Funcionario> {

	@Override
	public List<Funcionario> listarTodos() {
		// Instancio a classe DbConnect para poder usar seus metodos
		DbConnect dbconn = new DbConnect();

		// Inicio uma Connection como nula. para que todo o escopo do metodo
		// possa usa la.
		Connection conn = null;

		// Crio uma variavel booleana e inicio a mesma como false
		// Esta variavel sera modificada durate a execução do metodo e sera
		// retornada
		List<Funcionario> lista = null;

		// tento iniciar uma conexao e inserir os valores na lista
		try {
			lista = new ArrayList<Funcionario>();
			conn = dbconn.getConnection();
			ResultSet rs = dbconn.getResultSet(conn, "SELECT * FROM TBL_FUNCIONARIO");
			while (rs.next()) {

				Funcionario func = new Funcionario(rs.getString("CPF"),
						new java.util.Date(rs.getDate("DT_NASC").getTime()), rs.getString("NOME"),
						rs.getInt("TELEFONE"), rs.getString("CARGO"), rs.getString("SENHA"));
				// getDate("DT_NASC").getDate()
				lista.add(func);
			}

			/*
			 * Se algum erro ocorrer durante a execucao do bloco acima ira cair
			 * no bloco catch que ira imprimir o resultado na console.
			 */
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
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

	@Override
	public List<Funcionario> procurarPorChave(String chave) {
		// Instancio a classe DbConnect para poder usar seus metodos
		DbConnect dbconn = new DbConnect();

		// Inicio uma Connection como nula. para que todo o escopo do metodo
		// possa usa la.
		Connection conn = null;

		// Crio uma variavel booleana e inicio a mesma como false
		// Esta variavel sera modificada durate a execução do metodo e sera
		// retornada
		List<Funcionario> lista = null;
		System.out.println("Busca C: " + chave);
		// tento iniciar uma conexao e inserir os valores na lista
		try {
			lista = new ArrayList<Funcionario>();
			conn = dbconn.getConnection();
			ResultSet rs = dbconn.getResultSet(conn, "SELECT * FROM TBL_FUNCIONARIO WHERE CPF = '" + chave + "' OR NOME LIKE '%" + chave + "%'");
			while (rs.next()) {

				Funcionario func = new Funcionario(rs.getString("CPF"),
						new java.util.Date(rs.getDate("DT_NASC").getTime()), rs.getString("NOME"),
						rs.getInt("TELEFONE"), rs.getString("CARGO"), rs.getString("SENHA"));
				// getDate("DT_NASC").getDate()
				lista.add(func);
			}

			/*
			 * Se algum erro ocorrer durante a execucao do bloco acima ira cair
			 * no bloco catch que ira imprimir o resultado na console.
			 */
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
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
	 * Metodo ira cadastrar um Funcionario no Banco de dados.
	 */
	@Override
	public boolean cadastrarNoBanco(Funcionario entidade) {

		// Instancio a classe DbConnect para poder usar seus metodos
		DbConnect dbconn = new DbConnect();

		// Inicio uma Connection como nula. para que todo o escopo do metodo
		// possa usa la.
		Connection conn = null;

		// Crio uma variavel booleana e inicio a mesma como false
		// Esta variavel sera modificada durate a execução do metodo e sera
		// retornada
		boolean resultadoAcao = false;

		// tento iniciar uma conexao e inserir os valores no banco
		try {
			conn = dbconn.getConnection();
			PreparedStatement pst = dbconn.getPreparedStatement(conn,
					"INSERT INTO TBL_FUNCIONARIO(CPF, DT_NASC, NOME, TELEFONE, CARGO, SENHA) VALUES (?, ?, ?, ?, ?, ?)");
			pst.setString(1, entidade.getCpf().replaceAll("[.-]", ""));
			pst.setDate(2, new java.sql.Date(entidade.getDtNasc().getTime()));
			pst.setString(3, entidade.getNome());
			pst.setInt(4, entidade.getTelefone());
			pst.setString(5, entidade.getCargo());
			pst.setString(6, entidade.getSenhaUsuario());

			/*
			 * Se o resultado da query for diferente de 0 entao atribuo true a
			 * variavel resultadoacao e encerro o prepared statement. Se nao a
			 * mesma sera false.
			 */

			if (pst.executeUpdate() != 0) {
				resultadoAcao = true;
			} else {
				resultadoAcao = false;
			}

			/*
			 * Se algum erro ocorrer durante a execucao do bloco acima ira cair
			 * no bloco catch que ira imprimir o resultado na console e setar a
			 * variavel resultadoacao como false.
			 */
		} catch (Exception e) {
			e.printStackTrace();
			resultadoAcao = false;
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

		return resultadoAcao;
	}

	/**
	 * Metodo recebe um objeto e atualiza o banco com as informações recebidas
	 * por este objeto!
	 */
	@Override
	public boolean atualizarValorNoBanco(Funcionario entidade) {
		// Instancio a classe DbConnect para poder usar seus metodos
		DbConnect dbconn = new DbConnect();

		// Inicio uma Connection como nula. para que todo o escopo do metodo
		// possa usa la.
		Connection conn = null;

		// Crio uma variavel booleana e inicio a mesma como false
		// Esta variavel sera modificada durate a execução do metodo e sera
		// retornada
		boolean resultadoAcao = false;

		// tento iniciar uma conexao e atualizar os valores no banco
		try {
			conn = dbconn.getConnection();
			PreparedStatement pst = dbconn.getPreparedStatement(conn,
					"UPDATE TBL_FUNCIONARIO SET DT_NASC = ?, NOME = ?, TELEFONE = ?, CARGO = ? WHERE CPF = ?");
			pst.setTimestamp(1, new java.sql.Timestamp(entidade.getDtNasc().getTime()));
			pst.setString(2, entidade.getNome());
			pst.setInt(3, entidade.getTelefone());
			pst.setString(4, entidade.getCargo());
			pst.setString(5, entidade.getCpf().replaceAll("[.-]", ""));
			
			System.out.println("DATA: " + entidade.getDtNasc());
			System.out.println("NOME: " + entidade.getNome());
			System.out.println("TEL: " + entidade.getTelefone());
			System.out.println("CARGO: " + entidade.getCargo());
			System.out.println("CPF: " + entidade.getCpf());

			/*
			 * Se o resultado da query for diferente de 0 entao atribuo true a
			 * variavel resultadoacao e encerro o prepared statement. Se nao a
			 * mesma sera false.
			 */

			if (pst.executeUpdate() != 0) {
				resultadoAcao = true;
			} else {
				resultadoAcao = false;
			}

			/*
			 * Se algum erro ocorrer durante a execucao do bloco acima ira cair
			 * no bloco catch que ira imprimir o resultado na console e setar a
			 * variavel resultadoacao como false.
			 */
		} catch (Exception e) {
			e.printStackTrace();
			resultadoAcao = false;
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

		return resultadoAcao;
	}

	@Override
	public boolean deletarValorNoBanco(Funcionario entidade) {
		// Instancio a classe DbConnect para poder usar seus metodos
		DbConnect dbconn = new DbConnect();

		// Inicio uma Connection como nula. para que todo o escopo do metodo
		// possa usa la.
		Connection conn = null;

		// Crio uma variavel booleana e inicio a mesma como false
		// Esta variavel sera modificada durate a execução do metodo e sera
		// retornada
		boolean resultadoAcao = false;

		// tento iniciar uma conexao e atualizar os valores no banco
		try {
			conn = dbconn.getConnection();
			PreparedStatement pst = dbconn.getPreparedStatement(conn, "DELETE FROM TBL_FUNCIONARIO WHERE CPF = ?");
			pst.setString(1, entidade.getCpf());

			/*
			 * Se o resultado da query for diferente de 0 entao atribuo true a
			 * variavel resultadoacao e encerro o prepared statement. Se nao a
			 * mesma sera false.
			 */

			if (pst.executeUpdate() != 0) {
				resultadoAcao = true;
			} else {
				resultadoAcao = false;
			}

			/*
			 * Se algum erro ocorrer durante a execucao do bloco acima ira cair
			 * no bloco catch que ira imprimir o resultado na console e setar a
			 * variavel resultadoacao como false.
			 */
		} catch (Exception e) {
			e.printStackTrace();
			resultadoAcao = false;
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

		return resultadoAcao;
	}

}
