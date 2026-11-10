package dao;

import models.Aplicacao;
import models.Cliente;
import models.Gado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AplicacaoDAO {

    // Consulta base: traz a aplicação junto com o gado e o dono dele
    private static final String SELECT_BASE =
            "SELECT a.id, a.vacina, a.data_aplicacao, a.proxima_vacina, a.quem_aplicou, " +
            "g.id AS g_id, g.nome AS g_nome, g.raca, g.genero, g.data_nasc AS g_data_nasc, " +
            "c.id AS c_id, c.nome AS c_nome, c.cpf, c.data_nasc AS c_data_nasc, c.nome_fazenda, c.endereco " +
            "FROM aplicacao a " +
            "JOIN gado g ON g.id = a.id_gado " +
            "JOIN cliente c ON c.id = g.id_cliente ";

    // Busca todas as aplicações
    public ArrayList<Aplicacao> listar() {
        ArrayList<Aplicacao> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE + "ORDER BY a.id")) {

            while (rs.next()) {
                lista.add(montarAplicacao(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
        return lista;
    }

    // Busca uma aplicação pelo id (retorna null se não existir)
    public Aplicacao buscarPorId(Integer id) {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(SELECT_BASE + "WHERE a.id = ?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return montarAplicacao(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
        return null;
    }

    // Insere uma aplicação e devolve o id gerado pelo banco
    public Boolean cadastrar(Aplicacao aplicacao) {
        String sql = "INSERT INTO aplicacao (id_gado, vacina, data_aplicacao, proxima_vacina, quem_aplicou) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, aplicacao.getGado().getId());
            ps.setString(2, aplicacao.getVacina());
            ps.setString(3, aplicacao.getDataAplicacao());
            ps.setString(4, aplicacao.getProximaVacina());
            ps.setString(5, aplicacao.getQuemAplicou());

            if (ps.executeUpdate() == 0) return false;

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) aplicacao.setId(chaves.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
    }

    // Atualiza os dados de uma aplicação (o gado não muda)
    public Boolean alterar(Aplicacao aplicacao) {
        String sql = "UPDATE aplicacao SET vacina = ?, data_aplicacao = ?, proxima_vacina = ?, quem_aplicou = ? WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, aplicacao.getVacina());
            ps.setString(2, aplicacao.getDataAplicacao());
            ps.setString(3, aplicacao.getProximaVacina());
            ps.setString(4, aplicacao.getQuemAplicou());
            ps.setInt(5, aplicacao.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
    }

    // Remove uma aplicação pelo id
    public Boolean excluir(Integer id) {
        String sql = "DELETE FROM aplicacao WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
    }

    // Converte uma linha do banco em objeto Aplicacao (com gado e dono)
    private Aplicacao montarAplicacao(ResultSet rs) throws SQLException {
        Cliente dono = new Cliente(
                rs.getInt("c_id"),
                rs.getString("c_nome"),
                rs.getLong("cpf"),
                rs.getString("c_data_nasc"),
                rs.getString("nome_fazenda"),
                rs.getString("endereco")
        );

        Gado gado = new Gado(
                rs.getInt("g_id"),
                dono,
                rs.getString("g_nome"),
                rs.getString("raca"),
                rs.getString("genero"),
                rs.getString("g_data_nasc")
        );

        return new Aplicacao(
                rs.getInt("id"),
                dono,
                gado,
                rs.getString("vacina"),
                rs.getString("data_aplicacao"),
                rs.getString("proxima_vacina"),
                rs.getString("quem_aplicou")
        );
    }
}
