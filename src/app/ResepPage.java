package app;

import data.Apoteker;
import data.DataObat;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResepPage extends JFrame{
    private JPanel ResepPanel;
    private JTextField tfNamaObat;
    private JTextPane obatTersediaTextPane;
    private JButton cekObatButton;
    private JButton pembayaranButton;
    private final DataObat data = new DataObat();
    private final Apoteker apoteker = new Apoteker();
    private final List<String> resepDokter = new ArrayList<>();
    public ResepPage(){
        setContentPane(ResepPanel);
        setTitle("Resep");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        obatTersediaTextPane.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
                // cetak list resep dokter
            }
        });
        cekObatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaObat = tfNamaObat.getText();
                if (data.dataObat.containsKey(namaObat)){
                    String keluhan = apoteker.cekDataObat(namaObat,null);
                    JOptionPane.showMessageDialog(
                        ResepPage.this,
                        "Nama obat : " + namaObat +"\nKeterangan : " + keluhan,
                        "Obat tersedia",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    resepDokter.add(namaObat);
                    updateResepTextPane();
                }else{
                    JOptionPane.showMessageDialog(
                            ResepPage.this,
                            "Obat " + namaObat +" tidak tersedia",
                            "Obat tidak tersedia",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        pembayaranButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanFaktur();
            }
        });
    }
    // Method untuk mengupdate teks pada TextPane
    private void updateResepTextPane() {
        StringBuilder resepText = new StringBuilder();
        for (int i = 0; i < resepDokter.size(); i++) {
            resepText.append(i + 1).append(". ").append(resepDokter.get(i)).append("\n");
        }
        obatTersediaTextPane.setText(resepText.toString());
    }

    private void tampilkanFaktur() {
        int totalHarga = 0;
        StringBuilder fakturText = new StringBuilder();
        fakturText.append("===== FAKTUR PEMBAYARAN =====\n");
        fakturText.append("Daftar Obat:\n");

        for (int i = 0; i < resepDokter.size(); i++) {
            String obat = resepDokter.get(i);
            if (data.dataHargaObat.containsKey(obat)) {
                String hargaStr = data.dataHargaObat.get(obat);
                int harga = Integer.parseInt(hargaStr.replace(",", ""));
                totalHarga += harga;

                fakturText.append(i + 1).append(". ")
                        .append("Obat: ").append(obat).append("\n")
                        .append("Harga: Rp ").append(hargaStr).append("\n\n");
            } else {
                fakturText.append("Obat ").append(obat).append(" tidak ditemukan dalam daftar harga.\n\n");
            }
        }
        DecimalFormat df = new DecimalFormat("#,###");
        String totalHargaStr = df.format(totalHarga);
        fakturText.append("Total Harga: Rp ").append(totalHargaStr).append("\n");
        fakturText.append("=============================");

        int option = JOptionPane.showConfirmDialog(
                ResepPage.this,
                fakturText,
                "Faktur Pembayaran",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(
                    ResepPage.this,
                    "Terima kasih!",
                    "Terima Kasih",
                    JOptionPane.INFORMATION_MESSAGE
            );
            new HomePage();
            SwingUtilities.getWindowAncestor(ResepPanel).dispose();
        }
    }
}
