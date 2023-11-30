package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JPanel HomePanel;
    private JLabel topImageHome;
    private JButton resepButton;
    private JButton nonResepButton;
    private JButton logoutButton;
    public HomePage(){
        setContentPane(HomePanel);
        setTitle("Home");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        resepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResepPage resepPage = new ResepPage();
                SwingUtilities.getWindowAncestor(HomePanel).dispose();
            }
        });
        nonResepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NonResepPage nonResepPage = new NonResepPage();
                SwingUtilities.getWindowAncestor(HomePanel).dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        HomePage.this,
                        "Apakah anda yakin ingin keluar?" ,
                        "Keluar",
                        JOptionPane.INFORMATION_MESSAGE
                );
                System.exit(0);
            }
        });
    }

    private void createUIComponents() {
        ImageIcon icon = new ImageIcon("image_home.png");
        Image scaleImage = icon.getImage().getScaledInstance(150,140,Image.SCALE_DEFAULT);
        topImageHome = new JLabel(new ImageIcon(scaleImage));
    }
}
