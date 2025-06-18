import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BestellungDAO {
    
    public void speichernBestellung(Bestellung bestellung){
        
        String sql = "INSERT INTO bestellung (bid,kid,vid,name,preis,ort,email) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = Database_Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, bestellung.getBid());
                stmt.setInt(2, bestellung.getKid());
                stmt.setInt(3, bestellung.getVid());
                stmt.setString(4, bestellung.getName());
                stmt.setInt(5, bestellung.getPreis());
                stmt.setString(6, bestellung.getOrt());
                stmt.setString(7, bestellung.getEmail());

                stmt.executeUpdate();
                System.out.println("Bestellung erfolgreich gespeichert!");

            }catch (SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Die Bestellung konnte nicht gespeichert werden");
            }
        
        
        
        
        
        
        



    }












}
