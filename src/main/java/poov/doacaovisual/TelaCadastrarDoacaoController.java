package poov.doacaovisual;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import poov.doacaovisual.modelo.Doacao;

public class TelaCadastrarDoacaoController {

    @FXML
    private Button buttonCadastrarDoacao;

    @FXML
    private Button buttonFecharTela;

    @FXML
    private DatePicker datePickerDataDoacao;

    @FXML
    private Label labelCPFDoacao;

    @FXML
    private Label labelCodigoDoacao;

    @FXML
    private Label labelNomeDoacao;

    @FXML
    private TextField textFieldHoraDoacao;

    @FXML
    private TextField textFieldVolume;

    // indica que os dados da janela sao validos
    private boolean valido = false;

    // guarda os dados entrados pelo usuario
    private Doacao doacao;

    @FXML
    void buttonCadastrarDoacaoClicado(ActionEvent event) {
        if (validarCampos()) {
            valido = true;
            // Converte o volume para Double
            Double volume = Double.parseDouble(textFieldVolume.getText());

            // Converte a hora de doação para LocalTime
            LocalTime horaDoacao = LocalTime.parse(textFieldHoraDoacao.getText());

            // Obtém a data de doação do DatePicker
            LocalDate dataDoacao = datePickerDataDoacao.getValue();

            // Cria a nova doação com os tipos corretos
            doacao = new Doacao(volume, dataDoacao, horaDoacao);
            ((Button) event.getSource()).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Um dos campos está vazio");
            alert.showAndWait();
        }
    }

    private boolean validarCampos() {
        return !textFieldVolume.getText().isEmpty() &&
                !textFieldHoraDoacao.getText().isEmpty() &&
                !(datePickerDataDoacao.getValue() == null);
    }

    @FXML
    void buttonFecharTelaClicado(ActionEvent event) {

    }

    public void limpar() {
        valido = false;
        textFieldHoraDoacao.clear();
        textFieldVolume.clear();
        datePickerDataDoacao.setValue(null);
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public boolean isValido() {
        return valido;
    }
}
