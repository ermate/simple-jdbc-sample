package si.fri.prpo.servleti;

public class User extends Entity {
    private int id;
    private String ime;
    private String priimek;
    private String uporabniskoIme;

    public User(String ime, String priimek, String uporabniskoIme) {
        this.ime = ime;
        this.priimek = priimek;
        this.uporabniskoIme = uporabniskoIme;
    }

    @Override
    public String toString() {
        return "uporabnik{" +
                "ime='" + ime + '\'' +
                ", priimek='" + priimek + '\'' +
                ", uporabniskoIme='" + uporabniskoIme + '\'' +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }
}
