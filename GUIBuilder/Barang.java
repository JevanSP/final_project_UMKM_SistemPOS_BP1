public class Barang {
    String nama;
    double harga;
    int stok;
    int terjual; // Untuk laporan Owner

    // Konstruktor
    public Barang(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.terjual = 0;
    }
     // Override toString untuk menampilkan di ComboBox pada KasirView dan ListView pada AdminView
    @Override 
    public String toString() { return nama + " - Rp" + harga + " (Stok: " + stok + ")"; }
}