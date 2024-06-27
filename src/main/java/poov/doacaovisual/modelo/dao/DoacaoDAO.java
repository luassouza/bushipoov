package poov.doacaovisual.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import poov.doacaovisual.filtro.DoacaoFilter;
import poov.doacaovisual.modelo.Doacao;
import poov.doacaovisual.modelo.Situacao;
import poov.doacaovisual.modelo.TipoSanguineo;

public class DoacaoDAO {

    private final Connection conexao;

    public DoacaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Doacao> buscarDoacoesPorTipoSanguineo(TipoSanguineo tipoSanguineo) {
        List<Doacao> doacoes = new ArrayList<>();
        String sql = "SELECT * FROM doacoes WHERE tipo_sanguineo = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoSanguineo.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Doacao doacao = new Doacao();
                    gravar(doacao);
                    doacoes.add(doacao);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return doacoes;
    }

    public void gravar(Doacao doacao) throws SQLException {
        String query = "INSERT INTO doacao (codigo, data, hora, volume) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setLong(1, doacao.getDoador().getCodigo());
        pstmt.setDate(2, Date.valueOf(doacao.getData()));
        pstmt.setTime(3, Time.valueOf(doacao.getHora()));
        pstmt.setDouble(4, doacao.getVolume());
        pstmt.setString(5, "ATIVO");

        int numInseridos = pstmt.executeUpdate();
        if (numInseridos == 1) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                doacao.setCodigo(rs.getLong(1));
            }
            rs.close();
        }
        pstmt.close();
    }

    public Doacao buscar(Long codigo) throws SQLException {
        Doacao doacao = null;
        String query = "SELECT * FROM doacao WHERE codigo = ? AND situacao = 'ATIVO'";
        PreparedStatement pstmt = conexao.prepareStatement(query);
        pstmt.setLong(1, codigo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            doacao = new Doacao(rs.getLong(1), rs.getDate(2), rs.getTime(3), rs.getDouble(4));
        }
        rs.close();
        pstmt.close();
        return doacao;
    }

    public List<Doacao> buscar(String nome) throws SQLException {
        Doacao doacao;
        List<Doacao> doacoes = new ArrayList<>();
        String sqlBusca = "SELECT * FROM doacao WHERE UPPER(nome) LIKE ? AND situacao = 'ATIVO'";
        PreparedStatement pstmtBusca = conexao.prepareStatement(sqlBusca);
        pstmtBusca.setString(1, "%" + nome.toUpperCase() + "%");
        ResultSet rs = pstmtBusca.executeQuery();
        Situacao situacao;
        while (rs.next()) {
            situacao = rs.getString(5).equals("ATIVO") ? Situacao.ATIVO : Situacao.INATIVO;
            doacao = new Doacao(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), situacao);
            doacoes.add(doacao);
        }
        rs.close();
        pstmtBusca.close();
        return doacoes;
    }

    public List<Doacao> buscarTodas() throws SQLException {
        Doacao doacao;
        List<Doacao> doacoes = new ArrayList<>();
        String sql = "SELECT * FROM doacao WHERE situacao = 'ATIVO'";
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            doacao = new Doacao(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
            doacoes.add(doacao);
        }
        rs.close();
        stmt.close();
        return doacoes;
    }

    public List<Doacao> buscar(DoacaoFilter filtro) throws SQLException {
        Doacao doacao = null;
        List<Doacao> doacoes = new ArrayList<>();
        String query = "SELECT * FROM doacao WHERE situacao = 'ATIVO'";
        if (filtro.getCodigo() != null) {
            query += " AND codigo = ?";
        }
        if (filtro.getData() != null) {
            query += " AND data ILIKE ?";
        }
        if (filtro.getHora() != null) {
            query += "AND hora ILIKE ?";
        }
        if (filtro.getVolume() != null) {
            query += " AND volume = ?";
        }
        System.out.println(query);
        PreparedStatement pstmt = conexao.prepareStatement(query);
        int cont = 1;
        if (filtro.getCodigo() != null) {
            pstmt.setLong(cont, filtro.getCodigo());
            cont++;
        }
        if (filtro.getData() != null) {
            pstmt.setString(cont, "%" + filtro.getData() + "%");
            cont++;
        }
        if (filtro.getHora() != null) {
            pstmt.setString(cont, "%" + filtro.getHora() + "%");
            cont++;
        }
        if (filtro.getVolume() != null) {
            pstmt.setString(cont, "%" + filtro.getVolume() + "%");
        }
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            doacao = new Doacao(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
            doacoes.add(doacao);
        }
        rs.close();
        pstmt.close();
        return doacoes;
    }

    public boolean removerPraValer(Doacao doacao) throws SQLException {
        boolean retorno = false;
        String sqlRemocao = "DELETE FROM doacao WHERE codigo = ?";
        PreparedStatement pstmtRemocao = conexao.prepareStatement(sqlRemocao);
        pstmtRemocao.setLong(1, doacao.getCodigo());

        int removidas = pstmtRemocao.executeUpdate();
        if (removidas == 1) {
            retorno = true;
        }
        pstmtRemocao.close();
        return retorno;
    }

    public boolean remover(Doacao doacao) throws SQLException {
        String sqlAlteracao = "UPDATE doacao SET situacao = 'INATIVO' WHERE codigo = ?";
        PreparedStatement pstmtUpdate = conexao.prepareStatement(sqlAlteracao);
        pstmtUpdate.setLong(1, doacao.getCodigo());
        int alterados = pstmtUpdate.executeUpdate();
        if (alterados == 1) {
            System.out.println("Remoção efetuada com sucesso.");
            doacao.setSituacao(Situacao.INATIVO);
            return true;
        }
        pstmtUpdate.close();
        return false;
    }

    public boolean atualizar(Doacao doacao) throws SQLException {
        String sqlUpdate = "UPDATE doacao SET codigo = ?, data = ?, hora = ?, volume = ? WHERE codigo = ?";
        PreparedStatement pstmtUpdate = conexao.prepareStatement(sqlUpdate);
        pstmtUpdate.setLong(1, doacao.getCodigo());
        pstmtUpdate.setDate(2, Date.valueOf(doacao.getData()));
        pstmtUpdate.setTime(3, Time.valueOf(doacao.getHora()));
        pstmtUpdate.setDouble(3, doacao.getVolume());
        int alterados = pstmtUpdate.executeUpdate();
        pstmtUpdate.close();
        if (alterados == 1) {
            return true;
        } else {
            return false;
        }
    }

}
