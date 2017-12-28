import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu_Panel extends JPanel implements ActionListener
{
    private final String WELCOME_STR = "Witaj w grze Kółko i Krzyżyk\n";
    private JButton start_gry_button = new JButton("ROZPOCZNIJ GRĘ");
    private JButton wyjdz_button = new JButton("Wyjdź");
    private JLabel welcome_label = new JLabel(WELCOME_STR);
    private JLabel podpis = new JLabel("\u00a9 Jan Nowak");

    Menu_Panel()
    {
        setSize(200,200);
        setLayout(null);
        welcome_label.setBounds(15,20,167,25);
        start_gry_button.setBounds(25,60,150,50);
        wyjdz_button.setBounds(50,120,100,30);
        podpis.setBounds(90,155,100,15);

        welcome_label.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        add(welcome_label);
        add(start_gry_button);
        add(wyjdz_button);
        add(podpis);

        start_gry_button.addActionListener(this);
        wyjdz_button.addActionListener(this);

        setBackground(Color.yellow);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == start_gry_button)
        {
            JPanel cards = (JPanel) getParent();
            CardLayout cl = (CardLayout)(cards.getLayout());

            JFrame okno = (JFrame) cards.getParent().getParent().getParent().getParent();
            okno.setSize(500,400);
            okno.setLocationRelativeTo(null);

            cl.show(cards, "GamePanel");

        }
        if (source == wyjdz_button) {
            System.exit(0);
        }
    }
}
