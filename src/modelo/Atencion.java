package modelo;

public class Atencion {

    private int folio;
    private Paciente paciente;
    private String fecha;
    private int hora;
    private String descripcion;
    private int costo;
    private Dentista dentista;

    public Atencion() {
        this.folio = 0;
        this.paciente = new Paciente();
        this.fecha = "sin info";
        this.hora = 0;
        this.descripcion = "sin info";
        this.costo = 0;
        this.dentista = new Dentista();
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

}
