package cz.hartrik.pia.config

import org.hibernate.SessionFactory
import org.hibernate.c3p0.internal.C3P0ConnectionProvider
import org.hibernate.dialect.Dialect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.sql.DataSource

/**
 * Persistence configuration.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Configuration
@EnableTransactionManagement
class PersistenceConfig {

    @Bean
    @Autowired
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager()
        txManager.setSessionFactory(sessionFactory)
        return txManager
    }

    @Bean
    @Autowired
    LocalSessionFactoryBean sessionFactory(DataSource dataSource, Dialect dialect) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean()
        sessionFactory.setDataSource(dataSource)
        sessionFactory.setPackagesToScan('cz.hartrik.pia.dto')
        sessionFactory.setHibernateProperties(initProperties(dialect))
        return sessionFactory
    }

    private static Properties initProperties(Dialect dialect) {
        def properties = new Properties()

        // basic properties
        properties.'hibernate.hbm2ddl.auto' = 'create'  // update
        properties.'hibernate.dialect' = dialect.class.name
        properties.'hibernate.validator.apply_to_ddl' = 'true'

        // connection pool
        properties.'connection.provider_class' = C3P0ConnectionProvider.name
        properties.'hibernate.c3p0.min_size' = '5'
        properties.'hibernate.c3p0.max_size' = '20'
        properties.'hibernate.c3p0.timeout' = '300'
        properties.'hibernate.c3p0.max_statements' = '50'
        properties.'hibernate.c3p0.idle_test_period' = '300'

        return properties
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor()
    }

}
