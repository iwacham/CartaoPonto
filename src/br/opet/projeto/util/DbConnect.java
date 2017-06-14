package br.opet.projeto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Tiago Iwamoto
 * @email  tiago.iwamoto@opet.edu.br
 *
 * Classe para acesso ao banco de dados
 */
public class DbConnect {

	/**
	 * Metodo que informa endereço e informações para acesso ao banco de dados.
	 * @return o mesmo retorna uma conexão
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 */
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.OracleDriver").newInstance();
		String dbPath = "dbc:oracle:thin:@localhost:1521:XE";
		Connection conectar = DriverManager.getConnection(dbPath, "system", "system");
		return conectar;
	}

	/**
	 * Metodo ira receber uma conexao, e um comando SQL e ira executar este comando
	 * @param conn
	 * @param sql
	 * @return o mesmo retorna o resultado desta execucao.
	 * @throws SQLException
	 */
	public ResultSet getResultSet(Connection conn, String sql) throws SQLException{
		Statement st = conn.createStatement();
		return st.executeQuery(sql);
	}

	/**
	 * Metodo recebe uma conexao e um comando SQL e ira executar este comando fazendo o "escape"
	 * de caracteres especiais, evitando assim injeções SQL.
	 * @param conn
	 * @param sql
	 * @return o mesmo retorna o resultado desta execucao.
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException{
		return conn.prepareStatement(sql);
	}
}
