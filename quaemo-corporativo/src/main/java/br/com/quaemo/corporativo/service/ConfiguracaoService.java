package br.com.quaemo.corporativo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quaemo.domain.Configuracao;

@Service
public class ConfiguracaoService {
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private Integer codSistema; 

	public List<Configuracao> getConfiguracaoes() {
		List<Configuracao> lista = new ArrayList<Configuracao>();
		StringBuffer query = new StringBuffer("SELECT DISTINCT * FROM TB_CONFIGURACAO");
		if (this.codSistema != null) {
			query.append(" WHERE COD_SISTEMA=? OR COD_SISTEMA is null");
		} else {
			query.append(" WHERE COD_SISTEMA is null");
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection(this.datasource);

			pstmt = conn.prepareStatement(query.toString());
			if (this.codSistema != null) {
				pstmt.setInt(1, this.codSistema);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Configuracao config = new Configuracao(rs);
				lista.add(config);
			}
		} catch (SQLException e) {
			System.out.println("principal alterado " + e.getMessage());
		} finally {
			closeQuietly(conn, pstmt);
		}
		return lista;
	}

	public String getValorConfiguracao(String nomeConfiguracao) {
		String configuracao = "";
		for (Configuracao conf : getConfiguracaoes()) {
			if (conf.getNome().equalsIgnoreCase(nomeConfiguracao)) {
				configuracao = conf.getValor();
				break;
			}
		}
		return configuracao;
	}

	public String[] getRecursos() {
		String[] recurso = null;
		StringBuffer query = new StringBuffer("select URL_RECURSO from TB_RECURSO WHERE COD_SISTEMA  =?");
		StringBuffer queryCount = new StringBuffer("select count(*) from TB_RECURSO WHERE COD_SISTEMA  =?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCount = null;
		try {
			conn = getConnection(this.datasource);

			pstmt = conn.prepareStatement(query.toString());
			if (this.codSistema != null) {
				pstmt.setInt(1, this.codSistema);
			}
			ResultSet rs = pstmt.executeQuery();

			pstmtCount = conn.prepareStatement(queryCount.toString());
			if (this.codSistema != null) {
				pstmtCount.setInt(1, this.codSistema);
			}
			ResultSet count = pstmtCount.executeQuery();
			count.next();
			int qtdRegistro = count.getInt(1);
			count.close();
			pstmtCount.close();
			int posicao = 0;
			recurso = new String[qtdRegistro];
			while (rs.next()) {
				recurso[posicao] = (rs.getString("URL_RECURSO").trim() + "/**");
				posicao++;
			}
		} catch (SQLException e) {
			System.out.println("principal alterado " + e.getMessage());
		} finally {
			closeQuietly(conn, pstmt);
		}
		return recurso;
	}

	private Connection getConnection(DataSource datasource) throws SQLException {
		return datasource.getConnection();
	}

	private void closeQuietly(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException localSQLException) {
		}
	}
}
