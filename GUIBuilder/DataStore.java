import java.util.ArrayList;

public class DataStore {
    public static ArrayList<Barang> daftarBarang = new ArrayList<>();
    public static double totalPemasukan = 0;

    // Method Pencarian menggunkan Linear Search berdasarkan nama barang
    public static int cariBarang(String nama) {
        for (int i = 0; i < daftarBarang.size(); i++) {
            if (daftarBarang.get(i).nama.equalsIgnoreCase(nama)) return i;
        }
        return -1;
    }
}