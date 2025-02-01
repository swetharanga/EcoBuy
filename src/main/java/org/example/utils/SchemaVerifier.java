package org.example.utils;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SchemaVerifier{
private static final Logger logger = LoggerFactory.getLogger(SchemaVerifier.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void verifySchema() {
        logger.info("Verifying database schema...");

        // Query to get table information
        String tableQuery = """
            SELECT column_name, data_type, column_default, is_nullable
            FROM information_schema.columns
            WHERE table_name = 'eco_buy'
            ORDER BY ordinal_position;
        """;

        // Query to get primary key information
        String pkQuery = """
    SELECT a.attname
    FROM pg_index i
    JOIN pg_attribute a ON a.attrelid = i.indrelid AND a.attnum = ANY(i.indkey)
    WHERE i.indrelid = '"eco_buy"'::regclass AND i.indisprimary;
""";


        try {
            logger.info("Table Schema:");
            List<Map<String, Object>> tableInfo = jdbcTemplate.queryForList(tableQuery);
            tableInfo.forEach(row -> {
                logger.info("Column: {} | Type: {} | Default: {} | Nullable: {}",
                        row.get("column_name"),
                        row.get("data_type"),
                        row.get("column_default"),
                        row.get("is_nullable")
                );
            });

            logger.info("Primary Key:");
            List<Map<String, Object>> pkInfo = jdbcTemplate.queryForList(pkQuery);
            pkInfo.forEach(row -> {
                logger.info("Primary Key Column: {}", row.get("attname"));
            });

        } catch (Exception e) {
            logger.error("Error verifying schema: ", e);
        }
    }

    public void dropAndRecreateTable() {
        try {
            logger.info("Dropping eco_buy table...");
            jdbcTemplate.execute("DROP TABLE IF EXISTS eco_buy CASCADE");
            logger.info("Table dropped successfully. It will be recreated when the application restarts.");
        } catch (Exception e) {
            logger.error("Error dropping table: ", e);
        }
    }
}


