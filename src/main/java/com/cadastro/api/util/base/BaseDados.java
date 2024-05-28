package com.cadastro.api.util.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDados {
    // jdbc:mysql://localhost:3306/database-name  // ?allowPublicKeyRetrieval=true
    private static final String URL = "jdbc:mysql://localhost/cadastro?allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "616853";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
            closeConnection(connection);
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean conectar() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
            closeConnection(connection);
            return false;
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query, Object... params) {
        ResultSet resultSet = null;
        try {
            Connection connection = getConnection();
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }
        return resultSet;
    }

    public static void testConnection() {
        boolean conn = conectar();
        if (conn) {
            System.out.println("Conexão bem-sucedida!");
        } else {
            System.out.println("Não foi possível estabelecer a conexão.");
        }
    }
}
