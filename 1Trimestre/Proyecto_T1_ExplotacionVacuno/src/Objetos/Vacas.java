package Objetos;


public class Vacas {

    private String idCrotal;
    private String raza;
    private String estado;
    private String partos;
    private String entradaExplotacion;
    private String fechaNacimiento;
    
    public Vacas(String idCrotal, String raza, String estado, String partos, String entradaExplotacion,String fechaNacimiento) {
        this.idCrotal = idCrotal;
        this.raza = raza;
        this.estado = estado;
        this.partos = partos;
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
        return raza;
    }
    public void setIdRaza(String raza) {
        this.raza = raza;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getPartos() {
        return partos;
    }
    public void setPartos(String partos) {
        this.partos = partos;
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
