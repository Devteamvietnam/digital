package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V0_0_1__Hello extends BaseJavaMigration {
  public void migrate(Context context) {
    JdbcTemplate jdbcTemplate = 
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
    
    System.out.println("Helllo Java Flyway Migration V1.0.0");
    jdbcTemplate.query("SELECT * FROM account_account", (rs) -> {
        System.out.println(rs.getString("login_id"));
    });
  }
}
