package dao;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // Dados de acesso ao banco (ajuste conforme o seu PostgreSQL)
    private static final String URL = "jdbc:postgresql://localhost:5432/heedtech";
    private static final String USUARIO = "postgres";

    // A senha é digitada numa janela e fica guardada só enquanto o programa está aberto
    private static String senha = null;

    // Abre uma nova conexão com o banco
    public static Connection conectar() throws SQLException {
        // Se ainda não temos a senha, pede ao usuário
        if (senha == null) {
            senha = pedirSenha();
        }

        try {
            return DriverManager.getConnection(URL, USUARIO, senha);
        } catch (SQLException e) {
            // Código 28P01 = senha incorreta: esquece a senha para pedir de novo na próxima vez
            if ("28P01".equals(e.getSQLState())) {
                senha = null;
                throw new SQLException("Senha do banco incorreta! Tente novamente.");
            }
            throw e;
        }
    }

    // Mostra uma janela para digitar a senha (campo escondido)
    private static String pedirSenha() throws SQLException {
        JPasswordField campo = new JPasswordField();
        int opcao = JOptionPane.showConfirmDialog(
                null,
                campo,
                "Senha do PostgreSQL (usuário: " + USUARIO + ")",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcao != JOptionPane.OK_OPTION) {
            throw new SQLException("Conexão cancelada pelo usuário.");
        }
        return new String(campo.getPassword());
    }
}
