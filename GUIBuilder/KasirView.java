import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class KasirView {
    // List sementara untuk menampung item yang akan dibeli dalam satu transaksi
    ArrayList<Barang> keranjang = new ArrayList<>();

    public void show(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f4c064;");

        ComboBox<Barang> cbBarang = new ComboBox<>();   // ComboBox untuk memilih barang dari Admin
        cbBarang.getItems().addAll(DataStore.daftarBarang);

        TextField tfQty = new TextField();          // Input jumlah beli
        tfQty.setPromptText("Jumlah Beli");         
        TextArea areaKeranjang = new TextArea();    // Menampilkan isi keranjang
        areaKeranjang.setEditable(false);
        CheckBox cbMember = new CheckBox("Member (Diskon 10%)");    // CheckBox member untuk diskon

        Button btnTambah = new Button("Tambah ke Keranjang");
        btnTambah.setOnAction(e -> {
            Barang b = cbBarang.getValue();

            if (b == null || tfQty.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Pilih barang dan isi jumlah!").show();  // Validasi jika inputan pada qty kosong berupa alert warning
                return;
            }

            int qtyDiminta = Integer.parseInt(tfQty.getText()); // Jumlah yang ingin dibeli

            if (qtyDiminta > b.stok) {
                new Alert(Alert.AlertType.ERROR, "Stok tidak mencukupi! Sisa stok: " + b.stok).show(); // Validasi stok jika stok tidak cukup
            }
                else if (qtyDiminta <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Jumlah beli harus lebih dari 0!").show(); // Validasi jumlah beli harus lebih dari 0
                } 
            else {
                // Update stok dan terjual pada DataStore
                b.stok -= qtyDiminta;
                b.terjual += qtyDiminta;

                // Simpan ke keranjang (kita gunakan field 'stok' pada objek baru sebagai qty yang dibeli)
                keranjang.add(new Barang(b.nama, b.harga, qtyDiminta));

                areaKeranjang.appendText(b.nama + " x" + qtyDiminta + " (Rp" + (b.harga * qtyDiminta) + ")\n"); // Menampilkan di area keranjang
                tfQty.clear();
            }
            show(stage); // Refresh untuk update stok di ComboBox pilihan barang
        });

        Button btnBayar = new Button("Proses Bayar");
        btnBayar.setOnAction(e -> {
            if (keranjang.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Keranjang masih kosong!").show();   // Validasi keranjang kosong 
                return;
            }
            // Hitung total bayar
            double total = 0;
            for (Barang item : keranjang) {
                total += item.harga * item.stok; // item.stok di sini adalah qty yang dibeli
            }
            // Menghitung diskon member 10% jika checkbox member dicentang
            if (cbMember.isSelected())
                total *= 0.9;

            DataStore.totalPemasukan += total;

            new Alert(Alert.AlertType.INFORMATION, "Transaksi Berhasil!\nTotal Bayar: Rp" + total).show();

            keranjang.clear(); // Bersihkan keranjang untuk transaksi baru
            areaKeranjang.clear();  // Bersihkan area keranjang
            cbBarang.getItems().setAll(DataStore.daftarBarang); // Refresh ComboBox barang
        });

        Button btnBack = new Button("Logout");
        btnBack.setOnAction(e -> new LoginView().show(stage));

        root.getChildren().addAll(new Label("KASIR - TRANSAKSI"), cbBarang, tfQty, btnTambah,
        new Label("Keranjang:"), areaKeranjang, cbMember, btnBayar, btnBack);
        stage.setScene(new Scene(root, 400, 600));
    }
}