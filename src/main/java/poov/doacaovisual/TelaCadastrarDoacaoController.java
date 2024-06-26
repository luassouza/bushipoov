package poov.doacaovisual;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    }

    @FXML
    void buttonFecharTelaClicado(ActionEvent event) {

    }

    public Doacao getDoacao() {
        return doacao;
    }

    public boolean isValido() {
        return valido;
    }
}
