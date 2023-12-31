import java.util.Date;

public class Reservas {
    private String nombreSala;
    private int anio;
    private int mes;
    private int dia;
    private String hora;
    public Reservas(String nombreSala, int anio, int mes, int dia, String hora){
        this.nombreSala = nombreSala;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
    }
    public String getNombreSala() {
        return nombreSala;
    }
    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
}
