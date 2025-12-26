import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {
    public void show(Stage stage) {
        VBox root = new VBox(10); root.setStyle("-fx-padding: 20;-fx-background-color: #f4c064;");
        
        ListView<Barang> listView = new ListView<>();
        // DataStore.selectionSortBarang(); // Urutkan dulu sebelum tampil
        listView.getItems().addAll(DataStore.daftarBarang);

        TextField tfNama = new TextField(); tfNama.setPromptText("Nama Barang");
        TextField tfHarga = new TextField(); tfHarga.setPromptText("Harga");
        TextField tfStok = new TextField(); tfStok.setPromptText("Stok");
        
        // Menambah barang baru ke DataStore
        Button btnTambah = new Button("Tambah Baru"); btnTambah.setStyle("-fx-background-color: #90ee99;");
        btnTambah.setOnAction(e -> {    // Menambah barang baru ke DataStore
            DataStore.daftarBarang.add(new Barang(tfNama.getText(), Double.parseDouble(tfHarga.getText()), Integer.parseInt(tfStok.getText())));
            show(stage); // Refresh
        });

        // Menghapus barang dari DataStore
        Button btnHapus = new Button("Hapus Barang"); btnHapus.setStyle("-fx-background-color: #ff69b4;");
        btnHapus.setOnAction(e -> {    // Menghapus barang dari DataStore berdasarkan nama barang 
            int index = DataStore.cariBarang(tfNama.getText());
            if (index != -1) {
                DataStore.daftarBarang.remove(index);  
                new Alert(Alert.AlertType.INFORMATION, "Barang dihapus!").show();
                show(stage); // Refresh
            } else {
                new Alert(Alert.AlertType.ERROR, "Barang tidak ditemukan!").show();
            }
        });

        // Serching dan updating harga berdasarkan nama barang
        Button btnCari = new Button("Cari & Ubah Harga");
        btnCari.setOnAction(e -> {
            int index = DataStore.cariBarang(tfNama.getText()); // Searching dari tfNama dengan method cariBarang pada DataStore
            if (index != -1) {  // jika index atau hasil searching ditemukan
                DataStore.daftarBarang.get(index).harga = Double.parseDouble(tfHarga.getText()); // index dari hasil searching, lalu update harga
                new Alert(Alert.AlertType.INFORMATION, "Harga diperbarui!").show();
                show(stage); // Refresh
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Barang tidak ditemukan!").show();
            }
        });
        // Serching dan updating stok berdasarkan nama barang
        Button btnCari2 = new Button("Cari & Ubah Stok");
        btnCari2.setOnAction(e -> {
            int index = DataStore.cariBarang(tfNama.getText()); // Searching dari tfNama dengan method cariBarang pada DataStore
            if (index != -1) {  // jika index atau hasil searching ditemukan
                DataStore.daftarBarang.get(index).stok = Integer.parseInt(tfStok.getText());    // index dari hasil searching, lalu update stok
                new Alert(Alert.AlertType.INFORMATION, "Stok diperbarui!").show();
                show(stage); // Refresh
            } else {
                new Alert(Alert.AlertType.ERROR, "Barang tidak ditemukan!").show();
            }
        });
        
        
        // Sorting Stok Kecil ke Besar mengggunakan Bubble Sort
        Button btnSortStokKcl = new Button("Sort Stok (Kecil-Besar)");
        btnSortStokKcl.setOnAction(e -> {
            int n = DataStore.daftarBarang.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (DataStore.daftarBarang.get(j).stok > DataStore.daftarBarang.get(j + 1).stok) {  // Bandingkan stok
                        Barang temp = DataStore.daftarBarang.get(j);                        // Simpan sementara
                        DataStore.daftarBarang.set(j, DataStore.daftarBarang.get(j + 1));   // Tukar
                        DataStore.daftarBarang.set(j + 1, temp);                            // Kembalikan dari penyimpanan sementara
                    }
                }
            }
            show(stage); // Refresh
        });

        Button btnBack = new Button("Logout");
        btnBack.setOnAction(e -> new LoginView().show(stage));

        root.getChildren().addAll(new Label("ADMIN PANEL"), listView, tfNama, tfHarga, tfStok, btnTambah, btnHapus,btnCari, btnCari2, btnSortStokKcl ,btnBack);
        stage.setScene(new Scene(root, 400, 500));
    }
}