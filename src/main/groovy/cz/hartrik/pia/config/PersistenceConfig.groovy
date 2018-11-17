package cz.hartrik.pia.config

import org.hibernate.SessionFactory
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

        def properties = new Properties()
        properties['hibernate.hbm2ddl.auto'] = 'create'  // update
        properties['hibernate.dialect'] = dialect.class.name
        properties['hibernate.validator.apply_to_ddl'] = 'true'
        sessionFactory.setHibernateProperties(properties)

        return sessionFactory
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor()
    }

}
