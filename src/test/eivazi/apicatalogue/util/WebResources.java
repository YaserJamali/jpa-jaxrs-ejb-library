//package ir.tamin.infra.apicatalogue.util;
//
//import ir.tamin.framework.cdi.CDIBeanFactory;
//import ir.tamin.framework.cdi.util.*;
//import ir.tamin.framework.core.util.Bundle;
//import ir.tamin.framework.ws.rest.repository.DBFunctionRepository;
//import ir.tamin.framework.ws.rest.repository.DBFunctionRepositoryImpl;
//import ir.tamin.framework.ws.rest.repository.EntityRepository;
//import ir.tamin.framework.ws.rest.repository.EntityRepositoryImpl;
//import ir.tamin.framework.ws.rest.repository.ResourceRepository;
//
//import java.util.Locale;
//import java.util.ResourceBundle;
//
//import javax.annotation.Resource;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Produces;
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.sql.DataSource;
//
///**
// *
// * @author s_tayari
// *
// */
//@ApplicationScoped
//public class WebResources {
//
//	@Inject
//	@ConfigFile
//	private Bundle configFile;
//
//	@Produces
//	@WebProperties
//	@ApplicationScoped
//	public Bundle produceConfigFile() {
//		return configFile;
//	}
//
//	// Remove this if you have an EJB module that produces the same EM.
//	@PersistenceContext(unitName = "primary")
//	private EntityManager em;
//
//	@Produces
//	public EntityManager produceEntityManager() {
//		return em;
//	}
//
//	@Resource(lookup = "datasources/ERequestDS")
//	private DataSource dataSource;
//
//	@Produces
//	public DataSource getDataSource() {
//		return dataSource;
//	}
//
//	@Inject
//	private CDIBeanFactory cdiBeanFactory;
//
//	@Produces
//	@ConfigFile
//	@DevelopmentConfig
//	@ApplicationScoped
//	public Bundle produceDevAppProps() {
//		return new Bundle(ResourceBundle.getBundle("dev-web"));
//	}
//
//	@Produces
//	@ConfigFile
//	@SnapshotConfig
//	@ApplicationScoped
//	public Bundle produceSnapshotProps() {
//		return new Bundle(ResourceBundle.getBundle("snapshot-web"));
//	}
//
//	@Produces
//	@ConfigFile
//	@StageConfig
//	@ApplicationScoped
//	public Bundle produceStageProps() {
//		return new Bundle(ResourceBundle.getBundle("stage-web"));
//	}
//
//	@Produces
//	@ConfigFile
//	@TestConfig
//	@ApplicationScoped
//	public Bundle produceTestProps() {
//		return new Bundle(ResourceBundle.getBundle("test-web"));
//	}
//
//	@Produces
//	@ConfigFile
//	@ProductionConfig
//	@ApplicationScoped
//	public Bundle produceProdAppProps() {
//		return new Bundle(ResourceBundle.getBundle("prod-web"));
//	}
//
//	@Produces
//	@MessageBundle
//	@Named("WebMessages")
//	@ApplicationScoped
//	public Bundle produceMessageBundle() {
//		return new Bundle("messages/WebMessages", new Locale(
//				configFile.getProperty("application.locale")));
//	}
//
//	@Produces
//	@ApplicationScoped
//	@Named("EntityRepository")
//	public ResourceRepository produceEntityRepository() {
//		EntityRepository entityRepository = new EntityRepositoryImpl("models", em, cdiBeanFactory);
//		return entityRepository;
//	}
//
//	@Produces
//	@ApplicationScoped
//	@Named("FunctionRepository")
//	public DBFunctionRepository produceFunctionRepository() {
//		DBFunctionRepository dbFunctionRepository = new DBFunctionRepositoryImpl("functions", configFile.getProperty("package.root") + "." + configFile.getProperty("package.functions"), dataSource, cdiBeanFactory);
//		return dbFunctionRepository;
//	}
//}
