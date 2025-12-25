import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    public void show(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 30; -fx-alignment: center; -fx-background-color: #f4c064;");

        Label title = new Label("POS UMKM - PILIH MODE");
        ComboBox<String> modeUser = new ComboBox<>();   // ComboBox untuk memilih mode user
        modeUser.getItems().addAll("Kasir", "Admin", "Owner");
        modeUser.setValue("Kasir");

        PasswordField pinField = new PasswordField();
        pinField.setPromptText("Masukkan PIN");

        Button btnLogin = new Button("Masuk");
        btnLogin.setOnAction(e -> {
            String mode = modeUser.getValue(); 
            String pin = pinField.getText();

            if (mode.equals("Kasir") && pin.equals("111")) new KasirView().show(stage);
            else if (mode.equals("Admin") && pin.equals("222")) new AdminView().show(stage);
            else if (mode.equals("Owner") && pin.equals("333")) new OwnerView().show(stage);
            else new Alert(Alert.AlertType.ERROR, "PIN Salah!").show();
        });

        root.getChildren().addAll(title, modeUser, pinField, btnLogin);
        stage.setScene(new Scene(root, 300, 250));
        stage.show(); // Menampilkan stage
    }
}