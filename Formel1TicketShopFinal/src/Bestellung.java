import java.math.BigDecimal;

public class Bestellung {




    private String name;
    private BigDecimal preis;
    private String ort;
    private String email;

    public Bestellung(String name, BigDecimal preis, String ort, String email) {
       
        this.name = name;
        this.preis = preis;
        this.ort = ort;
        this.email = email;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
