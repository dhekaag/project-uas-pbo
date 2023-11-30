package app;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthPage extends JFrame{
    private JLabel imageLogo;
    private JPanel AuthPanel;
    private JTextField usernameTextField;
    private JPanel spacer1;
    private JPasswordField passwordTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabe;
    private JPanel spacer4;
    private JButton loginButton;
    private JButton signupButton;
    private JLabel labelKosong;
    private static final String userFile = "user.json";
    private JSONObject userData;

    public AuthPage(){
        setContentPane(AuthPanel);
        setTitle("Apotek sejahtera app");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                boolean success = login(username, password);
                if (success) {
                    HomePage homePage = new HomePage();
                    SwingUtilities.getWindowAncestor(AuthPanel).dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            AuthPage.this,
                            "Username atau password salah",
                            "Kesalan",
                            JOptionPane.ERROR_MESSAGE
                            );
                }
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                boolean success = signup(username, password);
                if (success) {
                    JOptionPane.showMessageDialog(
                            AuthPage.this,
                            "Yeay, berhasil membuat akun \ndengan username " + username,
                            "Berhasil",
                            JOptionPane.INFORMATION_MESSAGE
                            );
                } else {
                    JOptionPane.showMessageDialog(
                            AuthPage.this,
                            "Username tersebut telah digunakan,\nsilahkan mendaftar dengan username lain",
                            "Kesalahan",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    private void createUIComponents() {
        imageLogo = new JLabel(new ImageIcon("logo_app.png"));
    }
    private boolean login(String username, String password) {
        loadUserData();
        if (userData.has(username)) {
            JSONObject user = userData.getJSONObject(username);
            String storedPassword = user.getString("password");
            return storedPassword.equals(password);
        }
        return false;
    }

    private boolean signup(String username, String password) {
        loadUserData();
        if (!userData.has(username)) {
            userData.put(username, new JSONObject().put("password", password));
            saveUserData();
            return true;
        }
        return false; // Username already exists
    }

    private void loadUserData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(userFile)));
            userData = new JSONObject(content);
        } catch (IOException e) {
            userData = new JSONObject();
        }
    }

    private void saveUserData() {
        try (FileWriter file = new FileWriter(userFile)) {
            file.write(userData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
