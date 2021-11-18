package Objetos;

public class Toros {
    
    private String idCrotal;
    private String idRaza;
    private String estado;
    private String entradaExplotacion;
    private String fechaNacimiento;

    public Toros(String idCrotal, String idRaza, String estado, String entradaExplotacion, String fechaNacimiento) {
        this.idCrotal = idCrotal;
        this.idRaza = idRaza;
        this.estado = estado;
        this.entradaExplotacion = entradaExplotacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdCrotal() {
        return idCrotal;
    }

    public void setIdCrotal(String idCrotal) {
        this.idCrotal = idCrotal;
    }

    public String getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(String idRaza) {
        this.idRaza = idRaza;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEntradaExplotacion() {
        return entradaExplotacion;
    }

    public void setEntradaExplotacion(String entradaExplotacion) {
        this.entradaExplotacion = entradaExplotacion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    
    
}
