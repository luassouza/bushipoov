package poov.doacaovisual;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import poov.doacaovisual.modelo.Doacao;
import poov.doacaovisual.modelo.Doador;
import poov.doacaovisual.modelo.TipoSanguineo;

public class TelaCadastrarDoadorController {

    @FXML
    private Button buttonCadastrarDoador;

    @FXML
    private Button buttonFecharTela;

    @FXML
    private RadioButton radioButtonBusA;

    @FXML
    private RadioButton radioButtonBusAB;

    @FXML
    private RadioButton radioButtonBusB;

    @FXML
    private RadioButton radioButtonBusMinus;

    @FXML
    private RadioButton radioButtonBusO;

    @FXML
    private RadioButton radioButtonBusPlus;

    @FXML
    private RadioButton radioButtonBusRHDesconhecido;

    @FXML
    private RadioButton radioButtonBusSangueDesconhecido;

    @FXML
    private TextField textFieldCPFDoador;

    @FXML
    private TextField textFieldContatoDoador;

    @FXML
    private TextField textFieldNomeDoador;

    // indica que os dados da janela sao validos
    private boolean valido = false;

    // guarda os dados entrados pelo usuario
    private Doador doador;

    public TelaCadastrarDoadorController() {
        System.out.println("Construtor da TelaCadastrarDoadorController executado");
    }

    @FXML
    void buttonCadastrarDoadorClicado(ActionEvent event) {
        if (validarCampos()) {
            valido = true;
            doador = new Doador(textFieldNomeDoador.getText(), textFieldCPFDoador.getText(),
                    textFieldContatoDoador.getText());
            ((Button) event.getSource()).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Um dos campos está vazio");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonFecharTelaClicado(ActionEvent event) {
        valido = false;
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    public Doador getDoador() {
        return doador;
    }

    public boolean isValido() {
        return valido;
    }

    private boolean validarCampos() {
        return !textFieldNomeDoador.getText().isEmpty() &&
                !textFieldCPFDoador.getText().isEmpty() &&
                !textFieldContatoDoador.getText().isEmpty();
    }

    public void limpar() {
        valido = false;
        textFieldNomeDoador.clear();
        textFieldCPFDoador.clear();
        textFieldContatoDoador.clear();
    }

    @FXML
    void radioButtonBusABClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBusAClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBusBClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBusMinusClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBusOClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBusPlusClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBusRHDesconhecidoClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBusSangueDesconhecidoClicado(ActionEvent event) {

    }

}
