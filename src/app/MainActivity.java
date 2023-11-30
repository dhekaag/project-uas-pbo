package app;

import javax.swing.*;

public class MainActivity extends JFrame {
    public MainActivity(){
        try {
            new AuthPage();
        }catch (Exception e){
            JOptionPane.showMessageDialog(
                    MainActivity.this,
                    "Terjadi error : " + e.getMessage()
            );
        }
    }
    public static void main(String[] args) {
        new MainActivity();
    }
}
