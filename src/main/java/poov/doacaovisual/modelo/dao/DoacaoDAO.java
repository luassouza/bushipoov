package poov.doacaovisual.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import poov.doacaovisual.filtro.DoacaoFilter;
import poov.doacaovisual.modelo.Doacao;
import poov.doacaovisual.modelo.Doador;
import poov.doacaovisual.modelo.Situacao;

public class DoacaoDAO {

    private final Connection conexao;

    public DoacaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravar(Doacao doacao) throws SQLException {
        String query = "INSERT INTO doacao (data, hora, volume, cod_doador) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setDate(1, Date.valueOf(doacao.getData()));
        pstmt.setTime(2, Time.valueOf(doacao.getHora()));
        pstmt.setDouble(3, doacao.getVolume());
        pstmt.setLong(4, doacao.getDoador().getCodigo());

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

    public List<Doacao> buscar(DoacaoFilter filtro) throws SQLException {
        Doacao doacao = null;
        List<Doacao> doacoes = new ArrayList<>();
        String query = "SELECT * FROM doacao INNER JOIN doador on cod_doador = doador.codigo WHERE doacao.situacao = 'Ativo'";
        if (filtro.getCodigo() != null) {
            query += " AND codigo = ?";
        }
        if (filtro.getVolume() != null) {
            query += " AND volume ILIKE ?";
        }
        if (filtro.getData() != null) {
            query += " AND data ILIKE ?";
        }
        if (filtro.getHora() != null) {
            query += " AND hora ILIKE ?";
        }
        if(filtro.getDoador() != null){
            if(filtro.getDoador().getCodigo() != null){
                query += " AND doador.codigo = ?";
            }
            if(filtro.getDoador().getNome() != null){
                query += " AND doador.nome = ?";
            }
            if(filtro.getDoador().getContato() != null){
                query += " AND doador.contato = ?";
            }
            if(filtro.getDoador().getCpf() != null){
                query += " AND doador.cpf = ?";
            }
        }
        System.out.println(query);
        PreparedStatement pstmt = conexao.prepareStatement(query);
        int cont = 1;
        if (filtro.getCodigo() != null) {
            pstmt.setLong(cont, filtro.getCodigo());
            cont++;
        }
        if (filtro.getVolume() != null) {
            pstmt.setString(cont, "%" + filtro.getVolume() + "%");
            cont++;
        }
        if (filtro.getData() != null) {
            pstmt.setString(cont, "%" + filtro.getData() + "%");
        }
        if(filtro.getDoador() != null){
            if(filtro.getDoador().getCodigo() != null){
                pstmt.setLong(cont, filtro.getDoador().getCodigo());
                cont++;
            }
            if(filtro.getDoador().getNome() != null){
                pstmt.setString(cont, filtro.getDoador().getNome());
                cont++;
            }
            if(filtro.getDoador().getContato() != null){
                pstmt.setString(cont, filtro.getDoador().getContato());
                cont++;
            }
            if(filtro.getDoador().getCpf() != null){
                pstmt.setString(cont, filtro.getDoador().getCpf());
                cont++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            doacao = new Doacao(rs.getLong(1), rs.getDouble(2), rs.getDate(3).toLocalDate(), rs.getTime(4).toLocalTime());
            Doador doador = new Doador(rs.getLong(7), rs.getString(8), rs.getString(9), rs.getString(10));
            doacao.setDoador(doador);
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



}
