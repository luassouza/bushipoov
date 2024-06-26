package poov.doacaovisual.modelo;

public class Doador {
    private String nome;
    private Long codigo;
    private String cpf;
    private String contato;
    private Boolean tipoERhCorretos;
    private Situacao situacao = Situacao.ATIVO;
    private TipoSanguineo tiposanguineo = TipoSanguineo.DESCONHECIDO;
    private RH rh = RH.DESCONHECIDO;

    public Doador() {
        nome = "sem nome";
        cpf = "sem cpf";
        contato = "sem contato";
    }

    public Doador(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Doador(String nome, Long codigo, String cpf) {
        this.nome = nome;
        this.codigo = codigo;
        this.cpf = cpf;
    }

    public Doador(String nome, Long codigo, String cpf, String contato) {
        this.nome = nome;
        this.codigo = codigo;
        this.cpf = cpf;
        this.contato = contato;
    }

    public Doador(String nome, Long codigo, String cpf, String contato, Boolean tipoERhCorretos) {
        this.nome = nome;
        this.codigo = codigo;
        this.cpf = cpf;
        this.contato = contato;
        this.tipoERhCorretos = tipoERhCorretos;
    }

    public Doador(String nome, Long codigo, String cpf, String contato, Boolean tipoERhCorretos, Situacao situacao) {
        this.nome = nome;
        this.codigo = codigo;
        this.cpf = cpf;
        this.contato = contato;
        this.tipoERhCorretos = tipoERhCorretos;
        this.situacao = situacao;
    }

    public Doador(String nome, Long codigo, String cpf, String contato, Boolean tipoERhCorretos, Situacao situacao,
            TipoSanguineo tiposanguineo) {
        this.nome = nome;
        this.codigo = codigo;
        this.cpf = cpf;
        this.contato = contato;
        this.tipoERhCorretos = tipoERhCorretos;
        this.situacao = situacao;
        this.tiposanguineo = tiposanguineo;
    }

    public Doador(String nome, Long codigo, String cpf, String contato, Boolean tipoERhCorretos, Situacao situacao,
            TipoSanguineo tiposanguineo, RH rh) {
        this.nome = nome;
        this.codigo = codigo;
        this.cpf = cpf;
        this.contato = contato;
        this.tipoERhCorretos = tipoERhCorretos;
        this.situacao = situacao;
        this.tiposanguineo = tiposanguineo;
        this.rh = rh;
    }

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

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", codigo=" + codigo +
                ", cpf='" + cpf + '\'' +
                ", contato='" + contato + '\'' +
                ", tipoERhCorretos=" + tipoERhCorretos +
                ", situacao=" + situacao.getDescricao() +
                ", tiposanguineo=" + tiposanguineo.getDescricao() +
                ", rh=" + rh.getDescricao();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doador other = (Doador) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}
