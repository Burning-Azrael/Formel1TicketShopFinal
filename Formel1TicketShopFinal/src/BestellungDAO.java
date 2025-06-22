import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BestellungDAO {

    public void speichernBestellung(Bestellung bestellung) {

        String sql = "INSERT INTO bestellung (name,preis,ort,email) VALUES (?,?,?,?)";

        try (Connection conn = Database_Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            
            stmt.setString(1, bestellung.getName());
            stmt.setBigDecimal(2, bestellung.getPreis());
            stmt.setString(3, bestellung.getOrt());
            stmt.setString(4, bestellung.getEmail());

            stmt.executeUpdate();
            System.out.println("Bestellung erfolgreich gespeichert!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Die Bestellung konnte nicht gespeichert werden");
        }

    }

    public List<Bestellung> ladeAlleBestellungen(){

        List<Bestellung> liste = new ArrayList<>();
        String sql = "SELECT * FROM bestellung";

        try(Connection conn = Database_Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

                //Verarbeiten des ResultSet (Antwort auf die SELECT-Abfrage)
                while (rs.next()) {//jede Datenzeile aus dem ResultSet wird durchlaufen
                    //Daten aus einer Daten zeile in ein Bestellung Objekt speichern
                    Bestellung b = new Bestellung(  rs.getString("name"),
                                                    rs.getBigDecimal("preis"),
                                                    rs.getString("ort"), 
                                                    rs.getString("email"));
                    
                    //Bestellung-Objekt in Liste speichern
                    liste.add(b);
                    }
                }catch(SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Fehler beim Laden der Bestellungen!");
            }
        return liste;
    }

    public void speichernKunde(Kunde kunde){
        String sql = "INSERT INTO kunde (name,email) VALUES (?,?)";

        try (Connection conn = Database_Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, kunde.getName());
            stmt.setString(2, kunde.getEmail());
            stmt.executeUpdate();
            System.out.println("Bestellung erfolgreich gespeichert!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Die Bestellung konnte nicht gespeichert werden");
        }
    }
    public void speichernVeranstaltung(Veranstaltung v){
        String sql = "INSERT INTO veranstaltung (ort,ticketanzahl,ticketoption) VALUES (?,?,?)";

        try (Connection conn = Database_Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getOrt());
            stmt.setInt(2, v.getTicketanzahl());
            stmt.setString(3, v.getTicketoption());
            stmt.executeUpdate();
            System.out.println("Bestellung erfolgreich gespeichert!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Die Bestellung konnte nicht gespeichert werden");
        }
    }

}
