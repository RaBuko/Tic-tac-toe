

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Game_Panel extends JPanel implements ActionListener
{
    private JButton wyjdz = new JButton("Wyjdź");
    private JButton reset = new JButton("Reset");
    private JLabel tura_label = new JLabel("Tura gracza:");
    private JLabel nr_ruchu_opis_label = new JLabel("Ilość ruchów:");
    private JLabel nr_ruchu_licznik_label = new JLabel("0");
    private JLabel podpis = new JLabel("\u00a9 Rafał Bukowski");

    private short licznik_ruchow;

    private boolean circle_turn = true;
    private JButton tiles [];
    private char positions [];

    Game_Panel()
    {
        setSize(500,400);
        setLayout(null);
        setBackground(Color.blue);

        licznik_ruchow = 0;

        tiles = new JButton [10];
        positions = new char [] {'-','-','-','-','-','-','-','-','-','-'};
        /*                       [T][LG][GG][PG][LL][SS][PP][LD][DD][PD]
           [T]  [LG][GG][PG]
                [LL][SS][PP]
                [LD][DD][PD]

                L-lewo, G-góra, P-prawo, D-dół
         */

        tura_label.setForeground(Color.orange);
        nr_ruchu_licznik_label.setForeground(Color.orange);
        nr_ruchu_opis_label.setForeground(Color.orange);
        podpis.setForeground(Color.red);

        podpis.setBounds(390,355,100,15);
        reset.setBounds(15,200,120,35);
        wyjdz.setBounds(15,250,120,50);
        tura_label.setBounds(30,80,80,25);
        nr_ruchu_opis_label.setBounds(30,125,80,25);
        nr_ruchu_licznik_label.setBounds(110,125,30,25);

        add(podpis);
        add(reset);
        add(wyjdz);
        add(tura_label);
        add(nr_ruchu_opis_label);
        add(nr_ruchu_licznik_label);
        reset.addActionListener(this);
        wyjdz.addActionListener(this);

        drawStartTiles();
        setVisible(true);
    }

    private void reset()
    {
        for (int i = 0; i < 10;i++)
        {
            positions[i] = '-';
            remove(tiles[i]);
        }

        licznik_ruchow = 0;
        nr_ruchu_licznik_label.setText("0");
        circle_turn = true;

        drawStartTiles();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if (source == reset) reset();
        if (source == wyjdz)
        {
            JPanel cards = (JPanel) getParent();
            CardLayout cl = (CardLayout)(cards.getLayout());

            JFrame okno = (JFrame) cards.getParent().getParent().getParent().getParent();
            okno.setSize(200,200);
            okno.setLocationRelativeTo(null);
            cl.show(cards, "MenuPanel");
        }
        if (source == tiles[1]) changeTile(1);
        if (source == tiles[2]) changeTile(2);
        if (source == tiles[3]) changeTile(3);
        if (source == tiles[4]) changeTile(4);
        if (source == tiles[5]) changeTile(5);
        if (source == tiles[6]) changeTile(6);
        if (source == tiles[7]) changeTile(7);
        if (source == tiles[8]) changeTile(8);
        if (source == tiles[9]) changeTile(9);
    }

    private void drawStartTiles()
    {
        for (int i = 0; i < 10; i++)
            tiles[i] = new JButton(new ImageIcon(loadImage("img/blank_100x100.bmp")));

        tiles[0].setIcon(new ImageIcon(loadImage("img/circle_100x100.bmp")
                .getScaledInstance(25,25,Image.SCALE_SMOOTH)));

        tiles[0].setBounds(105,80,25,25);
        tiles[1].setBounds(150,50,100,100);
        tiles[2].setBounds(250,50,100,100);
        tiles[3].setBounds(350,50,100,100);
        tiles[4].setBounds(150,150,100,100);
        tiles[5].setBounds(250,150,100,100);
        tiles[6].setBounds(350,150,100,100);
        tiles[7].setBounds(150,250,100,100);
        tiles[8].setBounds(250,250,100,100);
        tiles[9].setBounds(350,250,100,100);

        for (int i = 0; i < 10;i++)
        {
            add(tiles[i]);
            tiles[i].addActionListener(this);
        }
    }

    private Image loadImage(String path)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Wystąpił problem z ładowaniem plików");
        }
        return img;
    }

    private void changeTile(int index)
    {

        if (circle_turn)
        {
            tiles[index].setIcon(new ImageIcon(loadImage("img/circle_100x100.bmp")));
            positions[index] = 'O';
            circle_turn = false;
            tiles[0].setIcon(new ImageIcon(loadImage("img/cross_100x100.bmp")
                    .getScaledInstance(25,25,Image.SCALE_SMOOTH)));

        }

        else
        {
            tiles[index].setIcon(new ImageIcon(loadImage("img/cross_100x100.bmp")));
            positions[index] = 'X';
            circle_turn = true;
            tiles[0].setIcon(new ImageIcon(loadImage("img/circle_100x100.bmp")
                    .getScaledInstance(25,25,Image.SCALE_SMOOTH)));
        }

        tiles[index].removeActionListener(this);
        licznik_ruchow++;
        nr_ruchu_licznik_label.setText(Integer.toString(licznik_ruchow));
        checkIfWon();
    }

    private void checkIfWon()
    {
        if (    positions[1] == positions[2] && positions[2] == positions[3] && positions [3] != '-' ||
                positions[4] == positions[5] && positions[5] == positions[6] && positions [6] != '-' ||
                positions[7] == positions[8] && positions[8] == positions[9] && positions [9] != '-' ||

                positions[1] == positions[5] && positions[5] == positions[9] && positions [9] != '-' ||
                positions[3] == positions[5] && positions[5] == positions[7] && positions [7] != '-' ||

                positions[1] == positions[4] && positions[4] == positions[7] && positions [7] != '-' ||
                positions[2] == positions[5] && positions[5] == positions[8] && positions [8] != '-' ||
                positions[3] == positions[6] && positions[6] == positions[9] && positions [9] != '-' )
        {
            if (circle_turn) JOptionPane.showMessageDialog(this,"KONIEC GRY!\nWygrywają KRZYŻYKI ","Krzyżyki",JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(this,"KONIEC GRY!\nWygrywają KÓŁKA ","Kółka",JOptionPane.PLAIN_MESSAGE);
            for (int i = 1; i < 10; i++) tiles[i].setEnabled(false);
            return;
        }
        if (licznik_ruchow == 9)
        {
            JOptionPane.showMessageDialog(this,"KONIEC GRY!\nRemis","Remis",JOptionPane.PLAIN_MESSAGE);
            for (int i = 1; i < 10; i++) tiles[i].setEnabled(false);
        }
    }
}
