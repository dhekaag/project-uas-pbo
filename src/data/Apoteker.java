package data;

import java.util.Map;

public class Apoteker extends DataObat{
    private final String username = "apoteker";
    private final String password = "apoteker123";
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String cekDataObat(String obat, String keluhan){
        var namaObat = "";
        var namaKeluhan = "";

        for (Map.Entry<String,String> entry : dataObat.entrySet()){
            if (entry.getKey().equals(obat)){
                namaKeluhan = entry.getValue();
                return namaKeluhan;
            } else if (entry.getValue().equals(keluhan)) {
                namaObat = entry.getKey();
                return namaObat;
            }
        }
    return null;
    }
}
