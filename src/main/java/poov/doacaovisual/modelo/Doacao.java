package poov.doacaovisual.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Doacao {
    private Long codigo;
    private Doador doador;
    private LocalDate data;
    private LocalTime hora;
    private Double volume;
    private Situacao situacao = Situacao.ATIVO;

    public Doacao() {
    }

    public Doacao(Long codigo) {
        this.codigo = codigo;
    }

    public Doacao(Long codigo, LocalDate data) {
        this.codigo = codigo;
        this.data = data;
    }

    public Doacao(Long codigo, LocalDate data, LocalTime hora) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
    }

    public Doacao(Double volume, LocalDate data, LocalTime hora) {
        this.volume = volume;
        this.data = data;
        this.hora = hora;
    }

    public Doacao(Long codigo, LocalDate data, LocalTime hora, Double volume) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
        this.volume = volume;
    }

    public Doacao(Long codigo, LocalDate data, LocalTime hora, Double volume, Situacao situacao) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
        this.volume = volume;
        this.situacao = situacao;
    }

    public Doacao(Long codigo, Doador doador, LocalDate data, LocalTime hora, Double volume, Situacao situacao) {
        this.codigo = codigo;
        this.doador = doador;
        this.data = data;
        this.hora = hora;
        this.volume = volume;
        this.situacao = situacao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Doacao [codigo=" + codigo + ", doador=" + doador + ", data=" + data + ", hora=" + hora + ", volume="
                + volume + ", situacao=" + situacao + "]";
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
        Doacao other = (Doacao) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}
