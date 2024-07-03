package poov.doacaovisual;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import poov.doacaovisual.modelo.Doador;

public class TelaAlterarDoadorController {

    @FXML
    private ToggleGroup RH;

    @FXML
    private ToggleGroup TipoSanguineo;

    @FXML
    private Button buttonAlterarDoador;

    @FXML
    private Button buttonFecharAlterarDoador;

    @FXML
    private CheckBox checkBBoxConfirmacao;

    @FXML
    private RadioButton radioButtonA;

    @FXML
    private RadioButton radioButtonAB;

    @FXML
    private RadioButton radioButtonB;

    @FXML
    private RadioButton radioButtonDesconhecidoRH;

    @FXML
    private RadioButton radioButtonDesconhecidoTipo;

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
    private TitledPane titledPaneRH;

    @FXML
    private TitledPane titledPaneTipoSanguineo;

    // indica que os dados da janela sao validos
    private boolean valido = false;

    // guarda os dados entrados pelo usuario
    private Doador doador;

    @FXML
    void buttonAlterarDoadorClicado(ActionEvent event) {
        if (validarCampos()) {
            valido = true;
            doador.setNome(textFieldNomeDoador.getText());
            doador.setContato(textFieldContatoDoador.getText());
            doador.setCpf(textFieldCPFDoador.getText());

            if (doador.getTipoSanguineo().getDescricao().equals("O")) {
                radioButtonO.setSelected(true);
            } else if (doador.getTipoSanguineo().getDescricao().equals("A")) {
                radioButtonA.setSelected(true);
            } else if (doador.getTipoSanguineo().getDescricao().equals("AB")) {
                radioButtonAB.setSelected(true);
            } else if (doador.getTipoSanguineo().getDescricao().equals("B")) {
                radioButtonB.setSelected(true);
            }
            if (doador.getRh().getDescricao().equals("Negativo")) {
                radioButtonNegativoRH.setSelected(true);
            } else if (doador.getRh().getDescricao().equals("Positivo")) {
                radioButtonPositivoRH.setSelected(true);
            }

            if (!radioButtonDesconhecidoTipo.isSelected()) {
                if (radioButtonA.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.A);
                } else if (radioButtonB.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.B);
                } else if (radioButtonAB.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.AB);
                } else if (radioButtonO.isSelected()) {
                    doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.O);
                }
            } else {
                doador.setTipoSanguineo(poov.doacaovisual.modelo.dao.TipoSanguineo.DESCONHECIDO);
            }
            if (!radioButtonDesconhecidoRH.isSelected()) {
                if (radioButtonNegativoRH.isSelected()) {
                    doador.setRh(poov.doacaovisual.modelo.dao.RH.NEGATIVO);
                } else if (radioButtonPositivoRH.isSelected()) {
                    doador.setRh(poov.doacaovisual.modelo.dao.RH.POSITIVO);
                }
            } else {
                doador.setRh(poov.doacaovisual.modelo.dao.RH.DESCONHECIDO);
            }

            if (checkBBoxConfirmacao.isSelected()) {
                ((Button) event.getSource()).getScene().getWindow().hide();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Aviso");
                alert.setContentText("Corrija os valores");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Um dos campos está vazio");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonFecharAlterarDoadorClicado(ActionEvent event) {
        valido = false;
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    public boolean isValido() {
        return valido;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
        textFieldNomeDoador.setText(doador.getNome());
        textFieldContatoDoador.setText(doador.getContato());
        textFieldCPFDoador.setText(doador.getCpf());
        if (doador.getTipoSanguineo().getDescricao().equals("O")) {
            radioButtonO.setSelected(true);
        } else if (doador.getTipoSanguineo().getDescricao().equals("A")) {
            radioButtonA.setSelected(true);
        } else if (doador.getTipoSanguineo().getDescricao().equals("AB")) {
            radioButtonAB.setSelected(true);
        } else if (doador.getTipoSanguineo().getDescricao().equals("B")) {
            radioButtonB.setSelected(true);
        }
        if (doador.getRh().getDescricao().equals("Negativo")) {
            radioButtonNegativoRH.setSelected(true);
        } else if (doador.getRh().getDescricao().equals("Positivo")) {
            radioButtonPositivoRH.setSelected(true);
        }
        valido = false;
    }

    private boolean validarCampos() {
        return !textFieldNomeDoador.getText().isEmpty() &&
                !textFieldCPFDoador.getText().isEmpty()
                && !textFieldContatoDoador.getText().isEmpty();
    }

    public void limpar() {
        valido = false;
        textFieldNomeDoador.clear();
        textFieldCPFDoador.clear();
        textFieldContatoDoador.clear();
        radioButtonDesconhecidoRH.setSelected(true);
        radioButtonDesconhecidoTipo.setSelected(true);
    }
}
