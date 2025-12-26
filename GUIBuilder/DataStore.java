import java.util.ArrayList;

// Fungsi DataStore untuk menyimpan data barang dan total pemasukan
public class DataStore {
    // List menyimpan data barang dari Admin berdasarkan class Barang kita deklarasikan
    public static ArrayList<Barang> daftarBarang = new ArrayList<>();
    // Variabel menyimpan total pemasukan
    public static double totalPemasukan = 0;
    // Method Pencarian menggunkan Linear Search berdasarkan nama barang
    public static int cariBarang(String nama) {
        for (int i = 0; i < daftarBarang.size(); i++) {
            if (daftarBarang.get(i).nama.equalsIgnoreCase(nama)) 
                return i;
        }
        return -1;
    }
}