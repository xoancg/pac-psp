package server;

public class Tarea {
    private String descripcion;
    private String estado;

    // Constructor generado automáticamente por IntelliJ IDEA
    public Tarea(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y Setters generados automáticamente por IntelliJ IDEA
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // toString Tarea
    @Override
    public String toString() {
        return "Descripción: '" + descripcion + '\'' + "; estado: '" + estado + '\'';
    }
}
