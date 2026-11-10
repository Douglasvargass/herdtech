package dao;

import models.Cliente;
import models.Gado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GadoDAO {

    // Consulta base: traz o gado junto com os dados do dono
    private static final String SELECT_BASE =
            "SELECT g.id, g.nome, g.raca, g.genero, g.data_nasc, " +
            "c.id AS c_id, c.nome AS c_nome, c.cpf, c.data_nasc AS c_data_nasc, c.nome_fazenda, c.endereco " +
            "FROM gado g JOIN cliente c ON c.id = g.id_cliente ";

    // Busca todo o gado
    public ArrayList<Gado> listar() {
        ArrayList<Gado> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE + "ORDER BY g.id")) {

            while (rs.next()) {
                lista.add(montarGado(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
        return lista;
    }

    // Busca um gado pelo id (retorna null se não existir)
    public Gado buscarPorId(Integer id) {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(SELECT_BASE + "WHERE g.id = ?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return montarGado(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
        return null;
    }

    // Insere um gado e devolve o id gerado pelo banco
    public Boolean cadastrar(Gado gado) {
        String sql = "INSERT INTO gado (id_cliente, nome, raca, genero, data_nasc) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, gado.getDono().getId());
            ps.setString(2, gado.getNome());
            ps.setString(3, gado.getRaca());
            ps.setString(4, gado.getGenero());
            ps.setString(5, gado.getDataNasc());

            if (ps.executeUpdate() == 0) return false;

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) gado.setId(chaves.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
    }

    // Atualiza os dados de um gado
    public Boolean alterar(Gado gado) {
        String sql = "UPDATE gado SET nome = ?, raca = ?, genero = ?, data_nasc = ? WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, gado.getNome());
            ps.setString(2, gado.getRaca());
            ps.setString(3, gado.getGenero());
            ps.setString(4, gado.getDataNasc());
            ps.setInt(5, gado.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
    }

    // Remove um gado pelo id
    public Boolean excluir(Integer id) {
        String sql = "DELETE FROM gado WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco: " + e.getMessage());
        }
    }

    // Converte uma linha do banco em objeto Gado (com o Cliente dono)
    private Gado montarGado(ResultSet rs) throws SQLException {
        Cliente dono = new Cliente(
                rs.getInt("c_id"),
                rs.getString("c_nome"),
                rs.getLong("cpf"),
                rs.getString("c_data_nasc"),
                rs.getString("nome_fazenda"),
                rs.getString("endereco")
        );

        return new Gado(
                rs.getInt("id"),
                dono,
                rs.getString("nome"),
                rs.getString("raca"),
                rs.getString("genero"),
                rs.getString("data_nasc")
        );
    }
}
