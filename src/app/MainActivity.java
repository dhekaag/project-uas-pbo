package app;

public class MainActivity {
    public static void main(String[] args) {
        try {
            new AuthPage();
        }catch (Exception e){
            System.out.println("Terjadi error : " + e.getMessage());
        }
    }
}
