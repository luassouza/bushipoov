package poov.doacaovisual.filtro;

import poov.doacaovisual.modelo.RH;
import poov.doacaovisual.modelo.Situacao;
import poov.doacaovisual.modelo.TipoSanguineo;

public class DoadorFilter {
    private String nome;
    private Long codigo;
    private String cpf;
    private String contato;
    private Boolean tipoERhCorretos;
    private Situacao situacao = Situacao.ATIVO;
    private TipoSanguineo tiposanguineo = TipoSanguineo.DESCONHECIDO;
    private RH rh = RH.DESCONHECIDO;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Boolean getTipoERhCorretos() {
        return tipoERhCorretos;
    }

    public void setTipoERhCorretos(Boolean tipoERhCorretos) {
        this.tipoERhCorretos = tipoERhCorretos;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public TipoSanguineo getTiposanguineo() {
        return tiposanguineo;
    }

    public void setTiposanguineo(TipoSanguineo tiposanguineo) {
        this.tiposanguineo = tiposanguineo;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
    }

}
