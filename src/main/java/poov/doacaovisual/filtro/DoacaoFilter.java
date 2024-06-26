package poov.doacaovisual.filtro;

import java.time.LocalDate;
import java.time.LocalDateTime;

import poov.doacaovisual.modelo.Doador;

public class DoacaoFilter {
    private Long codigo;
    private Doador doador;
    private LocalDate data;
    private LocalDateTime hora;
    private Double volume;

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

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

}