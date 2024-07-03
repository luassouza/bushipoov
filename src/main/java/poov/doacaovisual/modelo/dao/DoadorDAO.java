package poov.doacaovisual.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import poov.doacaovisual.filtro.DoadorFilter;
import poov.doacaovisual.modelo.Doador;
import poov.doacaovisual.modelo.Situacao;

public class DoadorDAO {
    private final Connection conexao;

    public DoadorDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravar(Doador doador) throws SQLException {
        String query = "INSERT INTO doador (nome, cpf, contato, tipo, rh) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, doador.getNome());
        pstmt.setString(2, doador.getCpf());
        pstmt.setString(3, doador.getContato());
        pstmt.setString(4, doador.getTipoSanguineo().getDescricao());
        pstmt.setString(5, doador.getRh().getDescricao());

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
        String query = "SELECT * FROM doador WHERE codigo = ? AND situacao = 'Ativo'";
        PreparedStatement pstmt = conexao.prepareStatement(query);
        pstmt.setLong(1, codigo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            doador = new Doador(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
            if (rs.getString(6).equals("A")) {
                doador.setTipoSanguineo(TipoSanguineo.A);
            } else if (rs.getString(6).equals("B")) {
                doador.setTipoSanguineo(TipoSanguineo.B);
            } else if (rs.getString(6).equals("AB")) {
                doador.setTipoSanguineo(TipoSanguineo.AB);
            } else if (rs.getString(6).equals("O")) {
                doador.setTipoSanguineo(TipoSanguineo.O);
            }

            if (rs.getString(7).equals("Positivo")) {
                doador.setRh(RH.POSITIVO);
            } else if (rs.getString(7).equals("Negativo")) {
                doador.setRh(RH.NEGATIVO);
            }

        }
        rs.close();
        pstmt.close();
        return doador;
    }


    public List<Doador> buscar(DoadorFilter filtro) throws SQLException {
        Doador doador = null;
        List<Doador> doadores = new ArrayList<>();
        String query = "SELECT * FROM doador WHERE situacao = 'Ativo' ";
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
        if (filtro.getTipoSanguineo().getDescricao() != "Desconhecido") {
            query += "AND tipo ILIKE ?";
        }
        if (filtro.getRh().getDescricao() != "Desconhecido") {
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
        if (filtro.getTipoSanguineo().getDescricao() != "Desconhecido") {
            pstmt.setString(cont, filtro.getTipoSanguineo().getDescricao());
        }
        if (filtro.getRh().getDescricao() != "Desconhecido") {
            pstmt.setString(cont, filtro.getRh().getDescricao());
        }
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            doador = new Doador(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
            if(rs.getString(6).equals("A")){
                doador.setTipoSanguineo(TipoSanguineo.A);
            }else if(rs.getString(6).equals("B")){
                doador.setTipoSanguineo(TipoSanguineo.B);
            }else if(rs.getString(6).equals("AB")){
                doador.setTipoSanguineo(TipoSanguineo.AB);
            }else if(rs.getString(6).equals("O")){
                doador.setTipoSanguineo(TipoSanguineo.O);
            }else if(rs.getString(6).equals("Desconhecido")){
                doador.setTipoSanguineo(TipoSanguineo.DESCONHECIDO);
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
        String sqlUpdate = "UPDATE doador SET nome = ?, cpf = ?, contato = ?, tipo = ?, rh = ? WHERE codigo = ?";
        PreparedStatement pstmtUpdate = conexao.prepareStatement(sqlUpdate);
        pstmtUpdate.setString(1, doador.getNome());
        pstmtUpdate.setString(2, doador.getCpf());
        pstmtUpdate.setString(3, doador.getContato());
        pstmtUpdate.setString(4, doador.getTipoSanguineo().getDescricao());
        pstmtUpdate.setString(5, doador.getRh().getDescricao());
        pstmtUpdate.setLong(6, doador.getCodigo());
        int alterados = pstmtUpdate.executeUpdate();
        pstmtUpdate.close();
        if (alterados == 1) {
            return true;
        } else {
            return false;
        }
    }
}
