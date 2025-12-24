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
        
        // Serching dan updating harga berdasarkan nama barang
        Button btnCari = new Button("Cari & Ubah Harga");
        btnCari.setOnAction(e -> {
            int index = DataStore.cariBarang(tfNama.getText()); // Searching dari tfNama
            if (index != -1) {
                DataStore.daftarBarang.get(index).harga = Double.parseDouble(tfHarga.getText());
                new Alert(Alert.AlertType.INFORMATION, "Harga diperbarui!").show();
                show(stage); // Refresh
            }
        });
        // Serching dan updating stok berdasarkan nama barang
        Button btnCari2 = new Button("Cari & Ubah Stok");
        btnCari2.setOnAction(e -> {
            int index = DataStore.cariBarang(tfNama.getText()); // Searching dari tfNama
            if (index != -1) {
                DataStore.daftarBarang.get(index).stok = Integer.parseInt(tfStok.getText());
                new Alert(Alert.AlertType.INFORMATION, "Stok diperbarui!").show();
                show(stage); // Refresh
            }
        });
        
        Button btnTambah = new Button("Tambah Baru");
        btnTambah.setOnAction(e -> {    // Menambah barang baru ke DataStore
            DataStore.daftarBarang.add(new Barang(tfNama.getText(), Double.parseDouble(tfHarga.getText()), Integer.parseInt(tfStok.getText())));
            show(stage);
        });

        Button btnSortStokKcl = new Button("Sort Stok (Kecil-Besar)");
        btnSortStokKcl.setOnAction(e -> {
            // Sorting Stok Kecil ke Besar mengggunakan Bubble Sort
            int n = DataStore.daftarBarang.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (DataStore.daftarBarang.get(j).stok > DataStore.daftarBarang.get(j + 1).stok) {
                        Barang temp = DataStore.daftarBarang.get(j);
                        DataStore.daftarBarang.set(j, DataStore.daftarBarang.get(j + 1));
                        DataStore.daftarBarang.set(j + 1, temp);
                    }
                }
            }
            show(stage); // Refresh
        });

        Button btnBack = new Button("Logout");
        btnBack.setOnAction(e -> new LoginView().show(stage));

        root.getChildren().addAll(new Label("ADMIN PANEL"), listView, tfNama, tfHarga, tfStok, btnCari, btnCari2,btnSortStokKcl,btnTambah, btnBack);
        stage.setScene(new Scene(root, 400, 500));
    }
}