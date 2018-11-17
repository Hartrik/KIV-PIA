package cz.hartrik.pia.config

import com.heroku.sdk.jdbc.DatabaseUrl
import org.hibernate.dialect.DerbyTenFiveDialect
import org.hibernate.dialect.Dialect
import org.hibernate.dialect.PostgreSQL95Dialect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType

import javax.sql.DataSource

/**
 * Database configuration.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Configuration
class DatabaseConfig {

    private static class Database {
        DataSource dataSource
        Dialect dialect
    }

    @Bean
    Database database() throws URISyntaxException {
        String url = System.getenv("DATABASE_URL")
        if (url != null) {
            DatabaseUrl db = DatabaseUrl.extract()
            String jdbc = db.jdbcUrl()
            String user = db.username()
            String pass = db.password()
            return new Database(
                    dataSource: new DriverManagerDataSource("$jdbc?user=$user&password=$pass"),
                    dialect: new PostgreSQL95Dialect()
            )

        } else {
            // external db is not set
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder()
            return new Database(
                    dataSource: builder.setType(EmbeddedDatabaseType.DERBY).build(),
                    dialect: new DerbyTenFiveDialect()
            )

        }
    }

    @Bean
    @Autowired
    DataSource dataSource(Database database) {
        database.dataSource
    }

    @Bean
    @Autowired
    Dialect dialect(Database database) {
        database.dialect
    }

}
