package poov.doacaovisual;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import poov.doacaovisual.modelo.Doador;
import poov.doacaovisual.modelo.dao.TipoSanguineo;

public class TelaCadastroDoadorController {

    @FXML
    private ToggleGroup RH;

    @FXML
    private ToggleGroup TipoSanguineo;

    @FXML
    private Button buttonCadastrarDoador;

    @FXML
    private Button buttonCancelarCadastrarDoador;

    @FXML
    private RadioButton radioButtonA;

    @FXML
    private RadioButton radioButtonAB;

    @FXML
    private RadioButton radioButtonB;

    @FXML
    private RadioButton radioButtonDesconhecido;

    @FXML
    private RadioButton radioButtonDesconhecidoRH;

    @FXML
    private RadioButton radioButtonNegativoRH;

    @FXML
    private RadioButton radioButtonO;

    @FXML
    private RadioButton radioButtonPositivoRH;

    @FXML
    private TextField textFieldCPFDoador;

    @FXML
    private TextField textFieldContatoDoador;

    @FXML
    private TextField textFieldNomeDoador;

    @FXML
    private TitledPane titledPaneTipoSanguineo;

    private boolean valido = false;

    public boolean isValido() {
        return valido;
    }

    public Doador getDoador() {
        return doador;
    }

    private Doador doador;

    public void limpar() {
        valido = false;
        textFieldNomeDoador.clear();
        textFieldCPFDoador.clear();
        textFieldContatoDoador.clear();
    }

    private boolean validarCampos() {
        return !textFieldNomeDoador.getText().isEmpty() &&
                !textFieldCPFDoador.getText().isEmpty()
                && !textFieldContatoDoador.getText().isEmpty();
    }

    @FXML
    void buttonCadastrarDoadorClicado(ActionEvent event) {
        if (validarCampos()) {
            valido = true;
            doador = new Doador(textFieldNomeDoador.getText(),
                    textFieldCPFDoador.getText(),
                    textFieldContatoDoador.getText());
            if (!radioButtonDesconhecido.isSelected()) {
                if (radioButtonA.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.A);
                } else if (radioButtonB.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.B);
                } else if (radioButtonAB.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.AB);
                } else if (radioButtonO.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.O);
                }
            }
            if ((!radioButtonDesconhecidoRH.isSelected())) {
                if (radioButtonNegativoRH.isSelected()) {
                    doador.setRh(poov.doacaovisual.modelo.dao.RH.NEGATIVO);
                } else if (radioButtonPositivoRH.isSelected()) {
                    doador.setRh(poov.doacaovisual.modelo.dao.RH.POSITIVO);
                }
            }
            ((Button) event.getSource()).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Um dos campos está vazio");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonCancelarCadastrarDoadorClicado(ActionEvent event) {
        valido = false;
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

}
