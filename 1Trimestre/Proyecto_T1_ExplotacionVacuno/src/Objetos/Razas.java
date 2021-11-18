package Objetos;

public class Razas {
    
    private String idRaza;
    private String tipoRaza;
    private String colorPiel;

    public Razas(String idRaza, String tipoRaza, String colorPiel) {
        this.idRaza = idRaza;
        this.tipoRaza = tipoRaza;
        this.colorPiel = colorPiel;
    }

    public String getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(String idRaza) {
        this.idRaza = idRaza;
    }

    public String getTipoRaza() {
        return tipoRaza;
    }

    public void setTipoRaza(String tipoRaza) {
        this.tipoRaza = tipoRaza;
    }

    public String getColorPiel() {
        return colorPiel;
    }

    public void setColorPiel(String colorPiel) {
        this.colorPiel = colorPiel;
    }

    

    

}
