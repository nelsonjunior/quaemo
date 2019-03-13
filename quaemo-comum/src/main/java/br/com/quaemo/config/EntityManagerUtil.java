package br.com.quaemo.config;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
 
  
public class EntityManagerUtil  {
 
	public static final String MYSQL  = "org.hibernate.dialect.MySQL5Dialect";
	public static final String DB2    = "org.hibernate.dialect.DB2Dialect";
	public static final String ORACLE = "org.hibernate.dialect.OracleDialect";
	
	private String dataSource;
	private String name;
	private String[] packagesToScan;
	private Properties properties;
	
	public EntityManagerUtil(String name) {
		this.name = name;
		properties = new Properties();
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("hibernate.format_sql", "true");		
	}
	
	public EntityManagerUtil dialect(String dialect) {
		properties.setProperty("hibernate.dialect", dialect);
		return this;
	}
	
	public EntityManagerUtil dataSource(String dataSource) {
		this.dataSource = dataSource; 
		return this;
	}
	
	public EntityManagerUtil packagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan; 
		return this;
	}
	
	public EntityManagerUtil showSql() {
		properties.setProperty("hibernate.show_sql", "true");
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
