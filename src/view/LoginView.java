package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UserService;

public class LoginView {
    private static final UserService userService = new UserService();
    private final LoginController controller = new LoginController(userService);

    public LoginView() {}
    public LoginView(UserService service) {}

    public void start(Stage stage) {
        Label title = new Label("Login");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField cpfField = new TextField();
        cpfField.setPromptText("CPF");
        PasswordField password = new PasswordField();
        password.setPromptText("Senha");

        Button loginBtn = new Button("Entrar");
        Button registerBtn = new Button("Cadastrar");
        Button forgotPasswordBtn = new Button("Esqueci a senha"); // Novo botão

        loginBtn.setOnAction(e -> controller.login(cpfField.getText(), password.getText(), stage));
        registerBtn.setOnAction(e -> controller.goToRegister(stage));
        forgotPasswordBtn.setOnAction(e -> {
            // Ação meramente ilustrativa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Esqueci a senha");
            alert.setHeaderText(null);
            alert.setContentText("Funcionalidade de recuperação de senha não implementada.");
            alert.showAndWait();
        });

        VBox box = new VBox(10, title, cpfField, password, loginBtn, registerBtn, forgotPasswordBtn); // Adicionei o novo botão
        box.setPadding(new Insets(30));
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: linear-gradient(to bottom, #d0eaff, #ffffff);");

        stage.setScene(new Scene(box, 300, 350)); // Aumentei um pouco a altura
        stage.setTitle("Login");
        stage.show();
    }

    public static void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
    public static void showSuccess(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}