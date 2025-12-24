import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OwnerView {
    public void show(Stage stage) {
        VBox root = new VBox(10); root.setStyle("-fx-padding: 20;");
        
        Label lblTotal = new Label("Total Pemasukan: Rp" + DataStore.totalPemasukan);
        
        // Sorting Top 5 Terlaris
        DataStore.daftarBarang.sort((a, b) -> b.terjual - a.terjual); 
        
        ListView<String> lvLaris = new ListView<>();
        for (int i = 0; i < Math.min(5, DataStore.daftarBarang.size()); i++) {
            Barang b = DataStore.daftarBarang.get(i);
            lvLaris.getItems().add(b.nama + " - Terjual: " + b.terjual);
        }

        Button btnBack = new Button("Logout");
        btnBack.setOnAction(e -> new LoginView().show(stage));

        root.getChildren().addAll(new Label("OWNER REPORT"), lblTotal, new Label("Top 5 Produk:"), lvLaris, btnBack);
        stage.setScene(new Scene(root, 400, 400)); 
    }
}