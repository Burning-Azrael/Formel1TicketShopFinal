import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.MissingFormatArgumentException;


public class AlleBestellungenGUI extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> bestellungsListe;
    private JButton btnZurueck;

    public AlleBestellungenGUI() {
        setTitle("Alle Bestellungen");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(675, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Überschrift
        JLabel lblUeberschrift = new JLabel("Alle Bestellungen");
        lblUeberschrift.setFont(new Font("Sans Serif", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(20, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        add(lblUeberschrift, c);

        // Listbox mit ScrollPane
        listModel = new DefaultListModel<>();

        try {
        listModel.addElement("" + String.format("%-6s %-16s %-15s %-20s %-15s", "BNr.: ", "Name: ", "Preis: ", "Veranstaltungsort: ", "E-Mail:"));
        listModel.addElement(String.format("%-6s %-16s %-15s %-20s %-15s", "------", "----------------", "---------------", "--------------------", "---------------"));
        } catch (MissingFormatArgumentException e) {
            e.printStackTrace();
            listModel.addElement("FEHLER IN FORMATZEILE!");
        }

        bestellungsListe = new JList<>(listModel);
        bestellungsListe.setFont(new Font("Monospaced", Font.PLAIN, 12));

        bestellungsListe.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                                                          Object value,
                                                          int index,
                                                          boolean isSelected,
                                                          boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

                label.setFont(new Font("Monospaced", index == 0 ? Font.BOLD : Font.PLAIN, 13));
                return label;
            }
        });

        bestellungsListe.setVisibleRowCount(4);  // max. 4 sichtbare Zeilen
        JScrollPane scrollPane = new JScrollPane(bestellungsListe);

        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(0, 10, 10, 10);
        add(scrollPane, c);

        // Zurück-Button
        btnZurueck = new JButton("Zurück zum Hauptmenü");
        btnZurueck.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(10, 10, 20, 10);
        add(btnZurueck, c);

        btnZurueck.addActionListener(e -> dispose());
        btnZurueck.addActionListener(e -> new StartGUI());

        ladeBestellungen();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void ladeBestellungen() {
        BestellungDAO dao = new BestellungDAO();
        List<Bestellung> bestellungen = dao.ladeAlleBestellungen();
        List<Integer> keys = dao.ladeKeys();
    
        if (bestellungen.isEmpty()) {
            listModel.addElement("Keine Bestellungen gefunden.");
            return;
        }

        Integer counter = 0;
    
        for (Bestellung b : bestellungen) {
            listModel.addElement(String.format(
                "%-6s %-16s %-15s %-20s %-15s",
                keys.get(counter),
                b.getName(),
                b.getPreis() + " €",
                b.getOrt(),
                b.getEmail()
            ));
            counter++;
        }
    }
    
}
