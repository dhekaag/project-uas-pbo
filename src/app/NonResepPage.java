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

public class NonResepPage extends JFrame{
    private JPanel NonResepPanel;
    private JTextField tfKeluhan;
    private JButton cekObatButton;
    private JTextPane obatTersediaTextPane;
    private JButton pembayaranButton;
    private JLabel labelKeluhan;
    private final DataObat data = new DataObat();
    private final Apoteker apoteker = new Apoteker();
    private final List<String> dataObat = new ArrayList<>();

    public NonResepPage(){
        setContentPane(NonResepPanel);
        setTitle("Non-resep");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        cekObatButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final String keluhan = tfKeluhan.getText();
                if (data.dataObat.containsValue(keluhan)){
                    String namaObat = apoteker.cekDataObat(null,keluhan);
                    JOptionPane.showMessageDialog(
                            NonResepPage.this,
                            "Nama obat : " + namaObat +"\nKeterangan : " + keluhan,
                            "Obat tersedia",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    dataObat.add(namaObat);
                    updateResepTextPane();
                }
            }
        });
        pembayaranButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanFaktur();
            }
        });
        obatTersediaTextPane.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
            }
        });
    }
    private void updateResepTextPane() {
        StringBuilder resepText = new StringBuilder();
        for (int i = 0; i < dataObat.size(); i++) {
            resepText.append(i + 1).append(". ").append(dataObat.get(i)).append("\n");
        }
        obatTersediaTextPane.setText(resepText.toString());
    }
    private void tampilkanFaktur() {
        int totalHarga = 0;
        StringBuilder fakturText = new StringBuilder();
        fakturText.append("===== FAKTUR PEMBAYARAN =====\n");
        fakturText.append("Daftar Obat:\n");

        for (int i = 0; i < dataObat.size(); i++) {
            String obat = dataObat.get(i);
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
                NonResepPage.this,
                fakturText,
                "Faktur Pembayaran",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(
                    NonResepPage.this,
                    "Terima kasih!",
                    "Terima Kasih",
                    JOptionPane.INFORMATION_MESSAGE
            );
            new HomePage();
            SwingUtilities.getWindowAncestor(NonResepPanel).dispose();
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
