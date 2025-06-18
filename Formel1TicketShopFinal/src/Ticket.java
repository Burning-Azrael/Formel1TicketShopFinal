public class Ticket {
    String name;
    String email;
    String tel;
    String race;

    String opt;
    Integer anzahl;
    String zArt;

    

    public Ticket(String name, String email, String tel, String race, String opt, Integer anzahl, String zArt) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.race = race;
        this.opt = opt;
        this.anzahl = anzahl;
        this.zArt = zArt;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getOpt() {
        return opt;
    }
    public void setOpt(String opt) {
        this.opt = opt;
    }
    public Integer getAnzahl() {
        return anzahl;
    }
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }
    public String getzArt() {
        return zArt;
    }
    public void setzArt(String zArt) {
        this.zArt = zArt;
    }

    




}
