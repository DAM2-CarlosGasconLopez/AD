package Objetos;

public class Terneros {

    private String idCrotal;
    private String idRaza;
    private String sexo;
    private String peso;
    private String estado;
    private String fechaNacimiento;
    private String idCrotalPadre;
    private String idCrotalMadre;

    public Terneros(String idCrotal, String idRaza, String sexo, String peso, String estado, String fechaNacimiento,String idCrotalPadre, String idCrotalMadre) {
        this.idCrotal = idCrotal;
        this.idRaza = idRaza;
        this.sexo = sexo;
        this.peso = peso;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.idCrotalPadre = idCrotalPadre;
        this.idCrotalMadre = idCrotalMadre;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdCrotalPadre() {
        return idCrotalPadre;
    }

    public void setIdCrotalPadre(String idCrotalPadre) {
        this.idCrotalPadre = idCrotalPadre;
    }

    public String getIdCrotalMadre() {
        return idCrotalMadre;
    }

    public void setIdCrotalMadre(String idCrotalMadre) {
        this.idCrotalMadre = idCrotalMadre;
    }

    

    
}
