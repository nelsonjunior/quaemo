package br.com.quaemo.api.service;

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

import br.com.quaemo.api.domain.Configuracao;

@Service
public class ConfiguracaoService {

	@Autowired
	private DataSource datasource;
	
	@Autowired 
	private Integer codSistema;	
	

	public List<Configuracao> getConfiguracaoes() {	
		List<Configuracao> lista = new ArrayList<Configuracao>(); 
		StringBuffer query = new StringBuffer("SELECT DISTINCT * FROM TB_CONFIGURACAO" );

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection(this.datasource);
			pstmt = conn.prepareStatement(query.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Configuracao config = new Configuracao(rs);
				lista.add(config);
			}
		} catch (SQLException e) {
			  System.out.println("principal alterado "+e.getMessage());
		} finally {
			closeQuietly(conn, pstmt);
		}
		return lista;
		
	}
	
	public String getValorConfiguracao(String nomeConfiguracao){
		String configuracao = "";
		for(Configuracao conf:getConfiguracaoes()){
			if(conf.getNome().equalsIgnoreCase(nomeConfiguracao)){
				configuracao=conf.getValor();
				break;
			}
		 }		
		return configuracao;
	}
	
	public Integer getCodSistema() {
		return codSistema;
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
		} catch (SQLException e) {
			
		}
	}	

}
