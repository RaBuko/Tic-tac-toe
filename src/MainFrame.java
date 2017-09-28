
import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame
{
    Menu_Panel menu_panel;
    Game_Panel game_panel;
    Container contentPane;

    MainFrame()
    {
        super("Gra kółko i krzyżyk");
        setSize(200,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = getContentPane();

        JPanel cards = new JPanel(new CardLayout());
        cards.add(menu_panel = new Menu_Panel(),"MenuPanel");
        cards.add(game_panel = new Game_Panel(),"GamePanel");
        contentPane.add(cards);

        setVisible(true);
    }

    public static void main(String ... args)
    {
        MainFrame tictactoe = new MainFrame();
    }
}
