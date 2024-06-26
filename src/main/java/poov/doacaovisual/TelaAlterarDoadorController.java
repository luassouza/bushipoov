package poov.doacaovisual;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import poov.doacaovisual.modelo.Doador;

public class TelaAlterarDoadorController {

    @FXML
    private Button buttonAlterarDoador;

    @FXML
    private Button buttonFecharTela;

    @FXML
    private CheckBox checkButtonCorreto;

    @FXML
    private RadioButton radioButtonAltA;

    @FXML
    private RadioButton radioButtonAltAB;

    @FXML
    private RadioButton radioButtonAltB;

    @FXML
    private RadioButton radioButtonAltDesconhecido;

    @FXML
    private RadioButton radioButtonAltMinus;

    @FXML
    private RadioButton radioButtonAltO;

    @FXML
    private RadioButton radioButtonAltPlus;

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

    @FXML
    void buttonAlterarDoadorClicado(ActionEvent event) {
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

    public void setDoador(Doador doador) {
        this.doador = doador;
        textFieldNomeDoador.setText(doador.getNome());
        textFieldCPFDoador.setText(doador.getCpf());
        textFieldContatoDoador.setText(doador.getContato());
        valido = false;
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
    void checkButtonCorretoClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAltABClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAltAClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAltBClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAltDesconhecidoClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAltMinusClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAltOClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAltPlusClicado(ActionEvent event) {

    }

}
