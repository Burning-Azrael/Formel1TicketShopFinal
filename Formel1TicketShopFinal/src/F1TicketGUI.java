import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class F1TicketGUI extends JFrame {

    JLabel lbl_title, lbl_name, lbl_email, lbl_phone, lbl_race, lbl_seat, lbl_quantity, lbl_payment;
    JTextField txt_name, txt_email, txt_phone;
    JComboBox<String> cbo_race;
    JComboBox<Integer> cbo_quantity;
    JRadioButton opt_paypal, opt_cash, opt_stehplatz,opt_tribuene,opt_vip;
    ButtonGroup paymentGroup;
    //JCheckBox chk_stehplatz, chk_tribuene, chk_vip;
    JButton btn_reset, btn_save, btn_exit, btn_speichern;

    Map<String, Map<String, BigDecimal>> preise;

    public F1TicketGUI() {
        this.setTitle("Formel 1 Ticketformular");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        initPrices();
        initComponents();

        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initPrices() {
        preise = new HashMap<>();

        preise.put("Monaco", Map.of(
                "Stehplatz", new BigDecimal("180"),
                "Tribüne", new BigDecimal("300"),
                "VIP", new BigDecimal("800")));

        preise.put("Silverstone", Map.of(
                "Stehplatz", new BigDecimal("150"),
                "Tribüne", new BigDecimal("250"),
                "VIP", new BigDecimal("700")));

        preise.put("Monza", Map.of(
                "Stehplatz", new BigDecimal("130"),
                "Tribüne", new BigDecimal("220"),
                "VIP", new BigDecimal("600")));

        preise.put("Österreich", Map.of(
                "Stehplatz", new BigDecimal("120"),
                "Tribüne", new BigDecimal("200"),
                "VIP", new BigDecimal("550")));

        preise.put("Imola", Map.of(
                "Stehplatz", new BigDecimal("140"),
                "Tribüne", new BigDecimal("230"),
                "VIP", new BigDecimal("620")));
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Font labelFont = new Font("Sans Serif", Font.PLAIN, 12);

        lbl_title = new JLabel("Formel 1 Ticket-Bestellformular");
        lbl_title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(20, 10, 10, 10);
        this.add(lbl_title, c);

        // Persönliche Daten
        lbl_name = new JLabel("Name:");
        lbl_name.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(lbl_name, c);

        txt_name = new JTextField(15);
        c.gridx = 1;
        this.add(txt_name, c);

        lbl_email = new JLabel("E-Mail:");
        lbl_email.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 2;
        this.add(lbl_email, c);

        txt_email = new JTextField(15);
        c.gridx = 1;
        this.add(txt_email, c);

        lbl_phone = new JLabel("Telefon:");
        lbl_phone.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 3;
        this.add(lbl_phone, c);

        txt_phone = new JTextField(15);
        c.gridx = 1;
        this.add(txt_phone, c);

        // Rennen
        lbl_race = new JLabel("Rennstrecke:");
        lbl_race.setFont(labelFont);
        c.gridx = 0; 
        c.gridy = 4;
        this.add(lbl_race, c);

        cbo_race = new JComboBox<>(new String[]{"Monaco", "Silverstone", "Monza", "Österreich", "Imola"});
        c.gridx = 1;
        this.add(cbo_race, c);

        // Sitzplätze
        lbl_seat = new JLabel("Sitzplatzoption:");
        lbl_seat.setFont(labelFont);
        c.gridx = 0; 
        c.gridy = 5;
        this.add(lbl_seat, c);

        opt_stehplatz = new JRadioButton("Stehplatz");
        opt_stehplatz.setSelected(true);
        opt_tribuene = new JRadioButton("Tribüne");
        opt_vip = new JRadioButton("VIP");
 
        
        

        ButtonGroup sitzgruppe = new ButtonGroup();
        sitzgruppe.add(opt_stehplatz);
        sitzgruppe.add(opt_tribuene);
        sitzgruppe.add(opt_vip);

        
 
        JPanel sitzPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sitzPanel.add(opt_stehplatz);
        sitzPanel.add(opt_tribuene);
        sitzPanel.add(opt_vip);
 
        c.gridx = 1;
        this.add(sitzPanel, c);

        // Menge
        lbl_quantity = new JLabel("Anzahl Tickets:");
        lbl_quantity.setFont(labelFont);
        c.gridx = 0; 
        c.gridy = 6;
        this.add(lbl_quantity, c);

        cbo_quantity = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        c.gridx = 1;
        this.add(cbo_quantity, c);

        // Zahlung
        lbl_payment = new JLabel("Zahlungsart:");
        lbl_payment.setFont(labelFont);
        c.gridx = 0; 
        c.gridy = 7;
        this.add(lbl_payment, c);

        opt_paypal = new JRadioButton("PayPal");
        opt_cash = new JRadioButton("Bar");
        opt_cash.setSelected(true);

        paymentGroup = new ButtonGroup();
        paymentGroup.add(opt_paypal);
        paymentGroup.add(opt_cash);
        JPanel zahlungPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        zahlungPanel.add(opt_paypal);
        zahlungPanel.add(opt_cash);
        c.gridx = 1;
        this.add(zahlungPanel, c);

        // Buttons
        ActionListener myActionListener = new MyActionListener();

        btn_reset = new JButton("Zurücksetzen");
        c.gridx = 0;
        c.gridy = 8;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(btn_reset, c);

        btn_save = new JButton("Speichern in Datei");
        c.gridx = 1;
        c.gridy = 8;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(btn_save, c);

        btn_exit = new JButton("Beenden");
        c.gridx = 0;
        c.gridy = 9;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(btn_exit, c);

        btn_speichern = new JButton("Speichern in DB");
        c.gridx = 1;
        c.gridy = 9;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(btn_speichern,c);
        
        // Events
        btn_reset.addActionListener(myActionListener);
        btn_exit.addActionListener(e -> dispose());
        btn_save.addActionListener(this::saveToFile);
        btn_speichern.addActionListener(myActionListener);
        
        
    }

    

    private void saveToFile(ActionEvent e) {
        String name = txt_name.getText();
        String email = txt_email.getText();
        String telefon = txt_phone.getText();
        String strecke = (String) cbo_race.getSelectedItem();
        int anzahl = (Integer) cbo_quantity.getSelectedItem();
        String sitzplatz = opt_vip.isSelected() ? "VIP" :
                           opt_tribuene.isSelected() ? "Tribüne" :
                           opt_stehplatz.isSelected() ? "Stehplatz" : "Keine Auswahl";

        if (sitzplatz.equals("Keine Auswahl")) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie eine Sitzplatzoption.");
            return;
        }

        BigDecimal preisProTicket = preise.get(strecke).get(sitzplatz);
        BigDecimal gesamtpreis = preisProTicket.multiply(BigDecimal.valueOf(anzahl));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("F1_Bestellungen.txt", true))) {
            writer.write(name + ";" + email + ";" + telefon + ";" + strecke + ";" + sitzplatz + ";" + anzahl + ";" + gesamtpreis + "€");
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Bestellung gespeichert.\nGesamtpreis: " + gesamtpreis + "€");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Speichern!");
        }
    }
    private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == btn_speichern) {
                String name = txt_name.getText();
                String email = txt_email.getText();
                String tel = txt_phone.getText();
                String race = cbo_race.getSelectedItem().toString();
                String opt = "";
                String zArt = "";

                if (opt_stehplatz.isSelected()) {
                    opt = opt_stehplatz.getText();
                }else if (opt_tribuene.isSelected()){
                    opt = opt_tribuene.getText();
                }else if (opt_vip.isSelected()){
                    opt = opt_vip.getText();
                }

                Integer anzahl = Integer.parseInt(cbo_quantity.getSelectedItem().toString());

                if (opt_paypal.isSelected()) {
                    zArt = opt_paypal.getText();
                }else if(opt_cash.isSelected()){
                    zArt = opt_cash.getText();
                }

                Ticket ticket = new Ticket(name, email, tel, race, opt, anzahl, zArt);

                BestellungDAO bestellung = new BestellungDAO();

                //es fehlen die IDs von kunden und veranstaltung
                //bestellung.speichernBestellung(ticket);
            
            }else if (e.getSource() == btn_reset) {
                txt_name.setText("");
                txt_email.setText("");
                txt_phone.setText("");

                cbo_race.setSelectedIndex(0);

                opt_stehplatz.setSelected(true);
                opt_tribuene.setSelected(false);
                opt_vip.setSelected(false);

                cbo_quantity.setSelectedIndex(0);

                opt_cash.setSelected(true);
                opt_paypal.setSelected(false);
            }
        }
 
    }
}
