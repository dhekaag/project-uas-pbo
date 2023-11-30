package data;

public class Main {
    public static void main(String[] args) {
        try {
            Pembeli pembeli = new Pembeli("","");
            pembeli.pembelianObat();
        }catch (Exception e){
            System.out.println("Terjadi error : " + e.getMessage());
        }

    }
}
