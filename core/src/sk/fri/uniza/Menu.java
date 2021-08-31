package sk.fri.uniza;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton EXITButton;
    private JButton STARTButton;
    private JPanel menuPanel;

    public Menu() {
        super("Space Invaders");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menuPanel);
        this.pack();

        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello World");
            }
        });

        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

//    public void appear() {
//        this.window.setVisible(true);
//    }

    public static void main(String[] args) {

        JFrame frame = new Menu();
        frame.setVisible(true);

    }

}
