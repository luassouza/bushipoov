package poov.doacaovisual;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import poov.doacaovisual.modelo.Doacao;
import poov.doacaovisual.modelo.Doador;
import poov.doacaovisual.modelo.RH;
import poov.doacaovisual.modelo.TipoSanguineo;
import poov.doacaovisual.modelo.dao.DAOFactory;
import poov.doacaovisual.modelo.dao.DoacaoDAO;
import poov.doacaovisual.modelo.dao.DoadorDAO;

public class TelaDoadorPrincipalController implements Initializable {

    
    @FXML
    private Button buttonAlterarDoador;

    @FXML
    private Button buttonBuscarDoador;

    @FXML
    private Button buttonCadastrarDoacao;

    @FXML
    private Button buttonCadastrarDoador;

    @FXML
    private Button buttonLimparDoador;

    @FXML
    private Button buttonRemoverDoador;

    @FXML
    private Button buttonVerDoacao;

    @FXML
    private DatePicker datePickerDataInf;

    @FXML
    private DatePicker datePickerDataSup;

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
    private TableColumn<Doador, String> tableColumnCPF;

    @FXML
    private TableColumn<Doador, String> tableColumnCPFDoador;

    @FXML
    private TableColumn<Doador, Long> tableColumnCodigo;

    @FXML
    private TableColumn<Doador, Long> tableColumnCodigoDoacao;

    @FXML
    private TableColumn<Doador, String> tableColumnContato;

    @FXML
    private TableColumn<Doador, String> tableColumnContatoDoador;

    @FXML
    private TableColumn<Doador, String> tableColumnData;

    @FXML
    private TableColumn<Doador, String> tableColumnHora;

    @FXML
    private TableColumn<Doador, String> tableColumnNome;

    @FXML
    private TableColumn<Doador, String> tableColumnNomeDoador;

    @FXML
    private TableColumn<Doador, RH> tableColumnRH;

    @FXML
    private TableColumn<Doador, TipoSanguineo> tableColumnTipoSanguineo;

    @FXML
    private TableColumn<Doador, Double> tableColumnVolume;

    @FXML
    private TableView<Doador> tableViewDoador;

    @FXML
    private TextField textFieldCPFDoador;

    @FXML
    private TextField textFieldCodigoDoacao;

    @FXML
    private TextField textFieldCodigoDoador;

    @FXML
    private TextField textFieldContatoDoador;

    @FXML
    private TextField textFieldHoraInf;

    @FXML
    private TextField textFieldHoraSup;

    @FXML
    private TextField textFieldNomeDoador;

    @FXML
    private TextField textFieldVolumeInf;

    @FXML
    private TextField textFieldVolumeSup;

    @FXML
    private ToggleGroup tipoSanguineoToggleGroup2;

    private Doador doador;
    private DAOFactory daoFactory;

    public TelaDoadorPrincipalController() {
        System.out.println("Construtor da TelaDoadorPrincipalController executado");
        daoFactory = new DAOFactory();
    }

    // private Stage stageTelaDoadorPrincipal;
    // private TelaDoadorPrincipalController controllerTelaDoadorPrincipal;

    private Stage stageTelaCadastrarDoador;
    private TelaCadastrarDoadorController controllerTelaCadastrarDoador;

    private Stage stageTelaCadastrarDoacao;
    private TelaCadastrarDoacaoController controllerTelaCadastrarDoacao;

    private Stage stageTelaAlterarDoador;
    private TelaAlterarDoadorController controllerTelaAlterarDoador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // FXMLLoader loaderPrincipal = new FXMLLoader(
        //         getClass().getResource("/poov/doacaovisual/TelaDoadorPrincipal.fxml"));
        FXMLLoader loaderCadastrarDoador = new FXMLLoader(
                getClass().getResource("/poov/doacaovisual/TelaCadastrarDoador.fxml"));
        FXMLLoader loaderCadastrarDoacao = new FXMLLoader(
                getClass().getResource("/poov/doacaovisual/TelaCadastrarDoacao.fxml"));
        FXMLLoader loaderAlterarDoador = new FXMLLoader(
                getClass().getResource("/poov/doacaovisual/TelaAlterarDoador.fxml"));
        Parent root;
        try {
            // root = loaderPrincipal.load();
            // Scene scene = new Scene(root);
            // stageTelaDoadorPrincipal = new Stage();
            // stageTelaDoadorPrincipal.setScene(scene);
            // stageTelaDoadorPrincipal.setTitle("Tela Principal");
            // stageTelaDoadorPrincipal.getIcons().add(new Image(getClass().getResourceAsStream("/images/java.png")));
            // stageTelaDoadorPrincipal.initModality(Modality.WINDOW_MODAL);
            // controllerTelaDoadorPrincipal = loaderPrincipal.getController();

            root = loaderCadastrarDoador.load();
            Scene scene = new Scene(root);
            stageTelaCadastrarDoador = new Stage();
            stageTelaCadastrarDoador.setScene(scene);;
            stageTelaCadastrarDoador.setTitle("Cadastro do Doador");
            stageTelaCadastrarDoador.getIcons().add(new Image(getClass().getResourceAsStream("/images/java.png")));
            stageTelaCadastrarDoador.initModality(Modality.WINDOW_MODAL);
            controllerTelaCadastrarDoador = loaderCadastrarDoador.getController();

            root = loaderCadastrarDoacao.load();
            scene = new Scene(root);
            stageTelaCadastrarDoacao = new Stage();
            stageTelaCadastrarDoacao.setScene(scene);
            stageTelaCadastrarDoacao.setTitle("Cadastro da Doação");
            stageTelaCadastrarDoacao.getIcons().add(new Image(getClass().getResourceAsStream("/images/java.png")));
            stageTelaCadastrarDoacao.initModality(Modality.WINDOW_MODAL);
            controllerTelaCadastrarDoacao = loaderCadastrarDoacao.getController();

            root = loaderAlterarDoador.load();
            scene = new Scene(root);
            stageTelaAlterarDoador = new Stage();
            stageTelaAlterarDoador.setScene(scene);
            stageTelaAlterarDoador.setTitle("Alteração do Doador");
            stageTelaAlterarDoador.getIcons().add(new Image(getClass().getResourceAsStream("/images/java.png")));
            stageTelaAlterarDoador.initModality(Modality.WINDOW_MODAL);
            controllerTelaAlterarDoador = loaderAlterarDoador.getController();

            tableColumnNome.setCellValueFactory(new PropertyValueFactory<Doador, String>("nome"));
            tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Doador, Long>("codigo"));
            tableColumnCPF.setCellValueFactory(new PropertyValueFactory<Doador, String>("cpf"));
            tableColumnContato.setCellValueFactory(new PropertyValueFactory<Doador, String>("contato"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buttonAlterarDoadorClicado(ActionEvent event) {

    }

    @FXML
    void buttonBuscarDoadorClicado(ActionEvent event) {

    }

    @FXML
    void buttonCadastrarDoacaoClicado(ActionEvent event) {
        if (stageTelaCadastrarDoacao.getOwner() == null) {
            stageTelaCadastrarDoacao.initOwner(((Node) event.getSource()).getScene().getWindow());
        }
        controllerTelaCadastrarDoacao.limpar();
        stageTelaCadastrarDoacao.showAndWait();
        if (controllerTelaCadastrarDoacao.isValido()) {
            Doacao doacao = controllerTelaCadastrarDoacao.getDoacao();
            try {
                daoFactory.abrirConexao();
                DoacaoDAO dao = daoFactory.criarDoacaoDAO();
                dao.gravar(doacao);
                daoFactory.fecharConexao();
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        }
    }

    @FXML
    void buttonCadastrarDoadorClicado(ActionEvent event) {
        if (stageTelaCadastrarDoador.getOwner() == null) {
            stageTelaCadastrarDoador.initOwner(((Node) event.getSource()).getScene().getWindow());
        }
        controllerTelaCadastrarDoador.limpar();
        stageTelaCadastrarDoador.showAndWait();
        if (controllerTelaCadastrarDoador.isValido()) {
            Doador doador = controllerTelaCadastrarDoador.getDoador();
            try {
                daoFactory.abrirConexao();
                DoadorDAO dao = daoFactory.criarDoadorDAO();
                dao.gravar(doador);
                daoFactory.fecharConexao();
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        }
    }

    @FXML
    void buttonLimparDoadorClicado(ActionEvent event) {
        textFieldCodigoDoador.clear();
        textFieldNomeDoador.clear();
        textFieldCPFDoador.clear();
        textFieldContatoDoador.clear();
        buttonBuscarDoadorClicado(event);
    }

    @FXML
    void buttonRemoverDoadorClicado(ActionEvent event) {
        Doador doador = tableViewDoador.getSelectionModel().getSelectedItem();
        if (doador != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Você tem certeza que quer remover o doador " + doador.getNome() + "?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
                try {
                    daoFactory.abrirConexao();
                    DoadorDAO dao = daoFactory.criarDoadorDAO();
                    dao.remover(doador);
                    daoFactory.fecharConexao();
                } catch (SQLException e) {
                    DAOFactory.mostrarSQLException(e);
                }
                buttonBuscarDoadorClicado(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Selecione um doador para removê-lo");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonVerDoacaoClicado(ActionEvent event) {

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
