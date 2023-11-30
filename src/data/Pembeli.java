package data;

import java.text.DecimalFormat;
import java.util.*;

public class Pembeli extends Apoteker{
    private final String username;
    private final String password;
    Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    public Pembeli(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
       return username;
    }
    public String getPassword(){
        return password;
    }

    public void pembelianObat(){

        while (isRunning) {
            System.out.println("====== Menu ======");
            System.out.println("| 1. Resep       |");
            System.out.println("| 2. Non-Resep   |");
            System.out.println("| 3. Exit        |");
            System.out.println("==================");
            System.out.print("\n Pilih menu : ");
            var userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 1 -> resep();
                case 2 -> nonResep();
                case 3 -> {
                    System.out.println("Terima kasih");
                    isRunning = false;
                }
                default -> System.out.println("Nomor tidak tersedia. " +
                        "silahkan masukan nomor yang tersedia"
                );
            }
        }
    }
    private void resep(){
        List<String> resepDokter = new ArrayList<>();

        System.out.println("Masukan nama obat dari resep dokter " +
                "dan ketik 'selesai' jika sudah");
        while (isRunning) {
            System.out.print("\nnama obat : ");
            String namaObat = scanner.nextLine();

            if (namaObat.equalsIgnoreCase("selesai")) {
                System.out.println("\nApakah anda ingin melakukan pembayaran (y/n) : ");
                var input = scanner.nextLine();
                switch (input){
                    case "y" -> {
                        cetakFaktur(resepDokter,null,dataHargaObat);
                        isRunning = false;
                    }
                    case "n" -> {
                        System.out.println("\n Silahkan masukan obat tambahan.");
                    }
                    default -> System.out.println("\n pilihan tidak tersedia silahkan masukkan yang benar!");
                }
            } else if (dataObat.containsKey(namaObat)) {
                cekDataObat(namaObat,"");
                resepDokter.add(namaObat);
            } else {
                System.out.println("Obat tidak tersedia.");
            }

        }
    }
    private void nonResep() {
        List<String> dataKeluhan = new ArrayList<>();
        System.out.println("Masukan nama obat dari resep dokter " +
                "dan ketik 'selesai' jika sudah");
        List<String> obat = new ArrayList<>();
        int counter = 0;

        while (isRunning) {
            System.out.print("\nkeluhan: ");
            String keluhan = scanner.nextLine();

            if (keluhan.equalsIgnoreCase("selesai")) {
                System.out.println("\nApakah anda ingin melakukan pembayaran (y/n) : ");
                var input = scanner.nextLine();
                switch (input){
                    case "y" -> {
                        cetakFaktur(null,dataKeluhan,dataHargaObat);
                        isRunning = false;
                    }
                    case "n" -> {
                        continue;
                    }
                    default -> System.out.println("\n pilihan tidak tersedia silahkan masukkan yang benar!");
                }
            } else if (dataObat.containsValue(keluhan)) {
                cekDataObat(null, keluhan);
                    for (Map.Entry<String, String> entry : dataObat.entrySet()) {
                        if (Objects.equals(entry.getValue(), keluhan)) {
                            obat.add(entry.getKey());
                        }
                    }
            } else {
                System.out.println("Obat tidak tersedia");
            }
        }
    }
    public static void cetakFaktur(List<String> resepDoktor, List<String> nonResep, Map<String, String> dataHargaObat) {
        int totalHarga = 0;

        System.out.println("\n======= Faktur Obat =======");

        if(resepDoktor != null){
            for (String obat : resepDoktor) {
                if (dataHargaObat != null && dataHargaObat.containsKey(obat)) {
                    String hargaStr = dataHargaObat.get(obat);
                    int harga = Integer.parseInt(hargaStr.replace(".", ""));
                    totalHarga += harga;

                    System.out.println("Obat: " + obat);
                    System.out.println("Harga: " + "Rp " + hargaStr);
                    System.out.println();
                }
                else {
                    System.out.println("Obat " + obat + " tidak ditemukan dalam daftar harga.\n");
            }
        }
    }
        if (nonResep != null) {
            for (String obat : nonResep) {
                if (dataHargaObat != null && dataHargaObat.containsKey(obat)) {
                    String hargaStr = dataHargaObat.get(obat);
                    int harga = Integer.parseInt(hargaStr.replace(".", ""));
                    totalHarga += harga;

                    System.out.println("Nama obat: " + obat);
                    System.out.println("Harga: Rp " + hargaStr);
                    System.out.println();
                }
                else {
                    System.out.println("obat tidak tersedia");
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#,###");
        String totalHargaStr = df.format(totalHarga);
        System.out.println("Total Harga: " + "Rp " + totalHargaStr);
        System.out.println("==========================");
    }

//    protected void pembayaran(List<String> resep, String[] nonResep){
//        System.out.println("apakah pesanan sudah selesai (y/n)");
//        var userInput = scanner.nextLine();
//        switch (userInput){
//            case "y" -> cetakFaktur(resep,nonResep,dataHargaObat);
//            case "n" -> pembelianObat();
//            default -> System.out.println("nomor tidak tersedia, " +
//                    "silahkan masukan nomor yang tersedia!");
//        }
//    }
}
