package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        String className = properties.getProperty("driver_class");
        if (className != null) {
            Class.forName(className);
        }
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    public void createTable(String tableName) {
       try (Statement statement = connection.createStatement()) {
           String sql = String.format(
                   "CREATE TABLE IF NOT EXISTS %s (%s, %s);",
                   tableName, "id SERIAL PRIMARY KEY",
                   "name TEXT");
           statement.execute(sql);
           System.out.println(getTableScheme(tableName));
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE %s;",
                    tableName);
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName, columnName, type);
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName, columnName);
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.initConnection();
        tableEditor.createTable("demo_table");
        tableEditor.dropTable("demo_table");
        tableEditor.createTable("demo_table");
        tableEditor.addColumn("demo_table", "last_name", "text");
        tableEditor.addColumn("demo_table", "age", "int");
        tableEditor.dropColumn("demo_table", "last_name");
        tableEditor.renameColumn("demo_table", "age", "year");
    }
}