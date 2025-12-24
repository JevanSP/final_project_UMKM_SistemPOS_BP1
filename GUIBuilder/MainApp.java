import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Pengaturan awal Stage
        primaryStage.setTitle("Sistem POS UMKM");
        
        // Panggil tampilan Login pertama kali
        LoginView loginView = new LoginView();
        loginView.show(primaryStage); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}