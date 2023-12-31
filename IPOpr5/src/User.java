import java.util.Vector;

public class User {
    private String username;
    private String email;
    private Vector<Reservas> reservas;

    public User(String username, String email,Vector<Reservas> reservas) {
        this.username = username;
        this.email = email;
        this.reservas = new Vector<Reservas>();
    }
    public User(User user){
        this.username = user.getUsername();
        this.reservas = user.getReservas();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Vector<Reservas> getReservas() {
        return reservas;
    }
    public void setReservas(Vector<Reservas> reservas) {
        this.reservas = reservas;
    }
    public void addReservas(Reservas r){
        this.reservas.add(r);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
