package poov.doacaovisual;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import poov.doacaovisual.modelo.Doacao;

public class TelaDoacaoPrincipalController {

    @FXML
    private Button buttonBuscarDoador;

    @FXML
    private Button buttonLimparDoador;

    @FXML
    private RadioButton radioButtonA;

    @FXML
    private RadioButton radioButtonAB;

    @FXML
    private RadioButton radioButtonB;

    @FXML
    private RadioButton radioButtonNegativo;

    @FXML
    private RadioButton radioButtonO;

    @FXML
    private RadioButton radioButtonPositivo;

    @FXML
    private RadioButton radioButtonQualquerRH;

    @FXML
    private RadioButton radioButtonQualquerTipo;

    @FXML
    private TableView<?> tableViewDoador;

    @FXML
    private TextField textFieldCPFDoador;

    @FXML
    private TextField textFieldCodigoDoacao;

    @FXML
    private TextField textFieldCodigoDoador;

    @FXML
    private TextField textFieldContatoDoador;

    @FXML
    private DatePicker textFieldDataInf;

    @FXML
    private TextField textFieldHoraInf;

    @FXML
    private TextField textFieldHoraSup;

    @FXML
    private TextField textFieldNomeDoador;

    @FXML
    private TextField textFieldVolumeInf;

    @FXML
    private DatePicker textFieldVolumeSup;

    // indica que os dados da janela sao validos
    private boolean valido = false;

    // guarda os dados entrados pelo usuario
    private Doacao doacao;

    public Doacao getDoacao() {
        return doacao;
    }

    public boolean isValido() {
        return valido;
    }

    @FXML
    void buttonBuscarDoadorClicado(ActionEvent event) {

    }

    @FXML
    void buttonLimparDoadorClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonABClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonAClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonBClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonNegativoClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonOClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonPositivoClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonQualquerRHClicado(ActionEvent event) {

    }

    @FXML
    void radioButtonQualquerTipoClicado(ActionEvent event) {

    }

}
