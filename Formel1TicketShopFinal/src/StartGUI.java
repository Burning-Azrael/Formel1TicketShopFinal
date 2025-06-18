import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartGUI extends JFrame{

    JButton btn_neu, btn_anzeigen, btn_loeschen;

    public StartGUI(){
        this.setTitle("Hauptmenü");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initcomponents();
        this.setSize(500,100);
        setLocationRelativeTo(null);

        this.setVisible(true);

    }

    private void initcomponents(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        MyActionListener mylistener = new MyActionListener();

        this.btn_neu = new JButton("Neue Bestellung");
        c.gridx = 0;
        c.gridy = 0;
        this.add(btn_neu,c);
        btn_neu.addActionListener(mylistener);

        this.btn_anzeigen = new JButton("Bestellformulare anzeigen");
        c.gridx = 1;
        c.gridy = 0;
        this.add(btn_anzeigen,c);
        btn_anzeigen.addActionListener(mylistener);

        this.btn_loeschen = new JButton("Alte Bestellung löschen");
        c.gridx = 2;
        c.gridy = 0;
        this.add(btn_loeschen,c);
        btn_loeschen.addActionListener(mylistener);
    }

        private class MyActionListener implements ActionListener{
        
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn_neu) {
                    dispose();
                    new F1TicketGUI();

                }else if (e.getSource() == btn_anzeigen) {
                    
                }else if (e.getSource() == btn_loeschen) {
                    
                }
        
            }

           
            
        }




    }
    

