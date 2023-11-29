package data;

import java.util.HashMap;
import java.util.Map;

public class DataObat {
    public Map<String,String> dataObat = new HashMap<>(Map.of(
            "paracetamol","pusing",
            "obh combi","batuk",
            "panadol","flu dan batuk",
            "captopril","hipertensi",
            "pumpitor","lambung",
            "diatabs","diare",
            "promag","sakit perut",
            "sanprima","antibiotik",
            "aspirin","pereda nyeri dan anti-inflamasi",
            "simvastatin","penurun kolesterol"
        )
    );
    public Map<String,String> dataHargaObat = new HashMap<>(Map.of(
            "promag", "10,000",
            "paracetamol","5,000",
            "obh combi","20,000",
            "panadol","15,000",
            "captopril","1,000",
            "pumpitor","20,000",
            "diatabs","5,000",
            "sanprima","5,000",
            "aspirin","21,000",
            "simvastatin","15,000"
    )
    );


}
