package br.com.quaemo.api.config;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
  
public class ApiEntityManager  {
 
	public static final String MYSQL  = "org.hibernate.dialect.MySQL5Dialect";
	public static final String DB2    = "org.hibernate.dialect.DB2Dialect";
	public static final String ORACLE = "org.hibernate.dialect.OracleDialect";
	
	private static final String SHOW_SQL = "hibernate.show_sql";
	private static final String DIALECT = "hibernate.dialect";
	private static final String FORMAT = "hibernate.format_sql";
	
	private String dataSource;
	private String name;
	private String[] packagesToScan;
	private Properties properties;
	
	public ApiEntityManager(String name) {
		this.name = name;
		properties = new Properties();
		properties.setProperty(SHOW_SQL, "false");
	}
	
	public ApiEntityManager dialect(String dialect) {
		properties.setProperty(DIALECT, dialect);
		return this;
	}
	
	public ApiEntityManager dataSource(String dataSource) {
		this.dataSource = dataSource; 
		return this;
	}
	
	public ApiEntityManager packagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan; 
		return this;
	}
	
	public ApiEntityManager showSql() {
		properties.setProperty(SHOW_SQL, "true"); 
		properties.setProperty(FORMAT, "true");		
		return this;
	} 
	
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName(name);
		factory.setPackagesToScan(packagesToScan);
		factory.setJpaProperties(properties);
		factory.setDataSource(getDataSource());
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory;
	} 

	public DataSource getDataSource()  { 
		try {
			return (DataSource) new InitialContext().lookup(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} 
 
}
