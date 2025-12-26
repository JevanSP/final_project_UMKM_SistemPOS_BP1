// `Barang` class untuk merepresentasikan item barang dalam sistem inventaris

public class Barang {
    // Untuk menyimpan informasi barang untuk Admin dan Kasir
    String nama;    
    double harga;
    int stok;
    // Untuk laporan Owner
    int terjual; 

    // Konstruktor untuk inisialisasi barang baru
    public Barang(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.terjual = 0;
    }
     // Override toString untuk menampilkan di ComboBox pada KasirView dan ListView pada AdminView
    @Override 
    public String toString() 
    { return nama + " - Rp" + harga + " (Stok: " + stok + ")"; }
}