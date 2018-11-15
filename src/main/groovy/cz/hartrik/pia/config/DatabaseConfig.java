package cz.hartrik.pia.config;

import com.heroku.sdk.jdbc.DatabaseUrl;
import java.net.URISyntaxException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Database configuration.
 *
 * @version 2017-04-14
 * @author Patrik Harag
 */
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        String url = System.getenv("DATABASE_URL");
        if (url != null) {
            DatabaseUrl db = DatabaseUrl.extract();
            String jdbc = db.jdbcUrl();
            String user = db.username();
            String pass = db.password();

            String fullUrl = String.format("%s?user=%s&password=%s", jdbc, user, pass);
            return new DriverManagerDataSource(fullUrl);

        } else {
            // db is not available
            return null;
        }
    }

}
