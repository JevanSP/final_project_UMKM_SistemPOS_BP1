import java.util.ArrayList;

public class DataStore {
    public static ArrayList<Barang> daftarBarang = new ArrayList<>();
    public static double totalPemasukan = 0;

    // Method Selection Sort Berdasarkan Nama untuk Admin View (default)
    // public static void selectionSortBarang() {
    //     int n = daftarBarang.size();
    //     for (int i = 0; i < n - 1; i++) {
    //         int minIdx = i;
    //         for (int j = i + 1; j < n; j++) {
    //             if (daftarBarang.get(j).nama.compareToIgnoreCase(daftarBarang.get(minIdx).nama) < 0) {
    //                 minIdx = j;
    //             }
    //         }
    //         Barang temp = daftarBarang.get(minIdx);
    //         daftarBarang.set(minIdx, daftarBarang.get(i));
    //         daftarBarang.set(i, temp);
    //     }
    // }

    // Method Pencarian menggunkan Linear Search berdasarkan nama barang
    public static int cariBarang(String nama) {
        for (int i = 0; i < daftarBarang.size(); i++) {
            if (daftarBarang.get(i).nama.equalsIgnoreCase(nama)) return i;
        }
        return -1;
    }
}