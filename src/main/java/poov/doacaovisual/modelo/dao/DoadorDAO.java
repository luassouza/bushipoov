package poov.doacaovisual.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import poov.doacaovisual.filtro.DoadorFilter;
import poov.doacaovisual.modelo.Situacao;
import poov.doacaovisual.modelo.TipoSanguineo;
import poov.doacaovisual.modelo.Doador;
import poov.doacaovisual.modelo.RH;

public class DoadorDAO {

    private final Connection conexao;

    public DoadorDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravar(Doador doador) throws SQLException {
        String query = "INSERT INTO doador (nome, codigo, cpf, contato, tiposanguineo, rh, situacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, doador.getNome());
        pstmt.setLong(2, doador.getCodigo());
        pstmt.setString(2, doador.getCpf());
        pstmt.setString(3, doador.getContato());
        pstmt.setString(4, "DESCONHECIDO");
        pstmt.setString(5, "DESCONHECIDO");
        pstmt.setString(6, "ATIVO");

        int numInseridos = pstmt.executeUpdate();
        if (numInseridos == 1) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                doador.setCodigo(rs.getLong(1));
            }
            rs.close();
        }
        pstmt.close();
    }

    public Doador buscar(long codigo) throws SQLException {
        Doador doador = null;
        String query = "SELECT * FROM doador WHERE codigo = ? AND situacao = 'ATIVO'";
        PreparedStatement pstmt = conexao.prepareStatement(query);
        pstmt.setLong(1, codigo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            doador = new Doador(rs.getString(1), rs.getLong(2), rs.getString(3), rs.getString(4));
        }
        rs.close();
        pstmt.close();
        return doador;
    }

    public List<Doador> buscar(String nome) throws SQLException {
        Doador doador;
        List<Doador> doadores = new ArrayList<>();
        String sqlBusca = "SELECT * FROM doador WHERE UPPER(nome) LIKE ? AND situacao = 'ATIVO'";
        PreparedStatement pstmtBusca = conexao.prepareStatement(sqlBusca);
        pstmtBusca.setString(1, "%" + nome.toUpperCase() + "%");
        ResultSet rs = pstmtBusca.executeQuery();
        Situacao situacao;
        while (rs.next()) {
            situacao = rs.getString(6).equals("ATIVO") ? Situacao.ATIVO : Situacao.INATIVO;
            doador = new Doador(rs.getString(1), rs.getLong(2), rs.getString(3), rs.getString(4));
            doadores.add(doador);
        }
        rs.close();
        pstmtBusca.close();
        return doadores;
    }

    public List<Doador> buscarTodas() throws SQLException {
        Doador doador;
        List<Doador> doadores = new ArrayList<>();
        String sql = "SELECT * FROM doador WHERE situacao = 'ATIVO'";
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            doador = new Doador(rs.getString(1), rs.getLong(2), rs.getString(3), rs.getString(4));
            doadores.add(doador);
        }
        rs.close();
        stmt.close();
        return doadores;
    }

    public List<Doador> buscar(DoadorFilter filtro) throws SQLException {
        Doador doador = null;
        List<Doador> doadores = new ArrayList<>();
        String query = "SELECT * FROM doador WHERE situacao = 'Ativo'";
        if (filtro.getCodigo() != null) {
            query += "AND codigo = ?";
        }
        if (filtro.getNome() != null) {
            query += "AND nome ILIKE ?";
        }
        if (filtro.getCpf() != null) {
            query += "AND cpf ILIKE ?";
        }
        if (filtro.getContato() != null) {
            query += "AND contato ILIKE ?";
        }
        if (filtro.getTiposanguineo() != null) {
            query += "AND tipo ILIKE ?";
        }
        if (filtro.getRh() != null) {
            query += "AND rh ILIKE ?";
        }
        System.out.println(query);
        PreparedStatement pstmt = conexao.prepareStatement(query);
        int cont = 1;
        if (filtro.getCodigo() != null) {
            pstmt.setLong(cont, filtro.getCodigo());
            cont++;
        }
        if (filtro.getNome() != null) {
            pstmt.setString(cont, "%" + filtro.getNome() + "%");
            cont++;
        }
        if (filtro.getCpf() != null) {
            pstmt.setString(cont, "%" + filtro.getCpf() + "%");
        }
        if (filtro.getContato() != null) {
            pstmt.setString(cont, "%" + filtro.getContato() + "%");
        }
        if (filtro.getTiposanguineo() != null) {
            pstmt.setString(cont, "%" + filtro.getTiposanguineo() + "%");
        }
        if (filtro.getRh() != null) {
            pstmt.setString(cont, "%" + filtro.getRh() + "%");
        }
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            doador = new Doador(rs.getString(1), rs.getLong(2), rs.getString(3), rs.getString(4));
            if(rs.getString(5).equals("A")){
                doador.setTiposanguineo(TipoSanguineo.A);
            }else if(rs.getString(5).equals("B")){
                doador.setTiposanguineo(TipoSanguineo.B);
            }else if(rs.getString(5).equals("AB")){
                doador.setTiposanguineo(TipoSanguineo.AB);
            }else if(rs.getString(5).equals("O")){
                doador.setTiposanguineo(TipoSanguineo.O);
            }

            if(rs.getString(7).equals("Positivo")){
                doador.setRh(RH.POSITIVO);
            }else if (rs.getString(7).equals("Negativo")){
                doador.setRh(RH.NEGATIVO);
            }
            doadores.add(doador);
        }
        rs.close();
        pstmt.close();
        return doadores;

        System.out.println(query);
        PreparedStatement pstmt = conexao.prepareStatement(query);
        int cont = 1;
        if (filtro.getNome() != null) {
            pstmt.setString(cont, "%" + filtro.getNome() + "%");
            cont++;
        }
        if (filtro.getCodigo() != null) {
            pstmt.setLong(cont, filtro.getCodigo());
            cont++;
        }
        if (filtro.getCpf() != null) {
            pstmt.setString(cont, "%" + filtro.getCpf() + "%");
            cont++;
        }
        if (filtro.getContato() != null) {
            pstmt.setString(cont, "%" + filtro.getContato() + "%");
        }

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            doador = new Doador(rs.getString(1), rs.getLong(2), rs.getString(3), rs.getString(4));
            doadores.add(doador);
        }
        rs.close();
        pstmt.close();
        return doadores;
    }

    public boolean removerPraValer(Doador doador) throws SQLException {
        boolean retorno = false;
        String sqlRemocao = "DELETE FROM doador WHERE codigo = ?";
        PreparedStatement pstmtRemocao = conexao.prepareStatement(sqlRemocao);
        pstmtRemocao.setLong(1, doador.getCodigo());

        int removidas = pstmtRemocao.executeUpdate();
        if (removidas == 1) {
            retorno = true;
        }
        pstmtRemocao.close();
        return retorno;
    }

    public boolean remover(Doador doador) throws SQLException {
        String sqlAlteracao = "UPDATE doador SET situacao = 'INATIVO' WHERE codigo = ?";
        PreparedStatement pstmtUpdate = conexao.prepareStatement(sqlAlteracao);
        pstmtUpdate.setLong(1, doador.getCodigo());
        int alterados = pstmtUpdate.executeUpdate();
        if (alterados == 1) {
            System.out.println("Remoção efetuada com sucesso.");
            doador.setSituacao(Situacao.INATIVO);
            return true;
        }
        pstmtUpdate.close();
        return false;
    }

    public boolean atualizar(Doador doador) throws SQLException {
        String sqlUpdate = "UPDATE doador SET nome = ?, codigo = ?, cpf = ?, contato = ? WHERE codigo = ?";
        PreparedStatement pstmtUpdate = conexao.prepareStatement(sqlUpdate);
        pstmtUpdate.setString(1, doador.getNome());
        pstmtUpdate.setLong(2, doador.getCodigo());
        pstmtUpdate.setString(3, doador.getCpf());
        pstmtUpdate.setString(4, doador.getContato());
        int alterados = pstmtUpdate.executeUpdate();
        pstmtUpdate.close();
        if (alterados == 1) {
            return true;
        } else {
            return false;
        }
    }
}
