package poov.doacaovisual;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import poov.doacaovisual.filtro.DoacaoFilter;
import poov.doacaovisual.filtro.DoadorFilter;
import poov.doacaovisual.modelo.Doacao;
import poov.doacaovisual.modelo.Doador;
import poov.doacaovisual.modelo.dao.DAOFactory;
import poov.doacaovisual.modelo.dao.DoacaoDAO;
import poov.doacaovisual.modelo.dao.DoadorDAO;
import poov.doacaovisual.modelo.dao.RH;
import poov.doacaovisual.modelo.dao.TipoSanguineo;

public class TelaPrincipalController implements Initializable {

    @FXML
    private ToggleGroup toggleGroupRHDoacao;

    @FXML
    private ToggleGroup toggleGroupRHDoador;

    @FXML
    private ToggleGroup toggleGrouptipoSanguineoDoacao;

    @FXML
    private ToggleGroup toggleGrouptipoSanguineoDoador;

    @FXML
    private Button buttonAlterarDoador;

    @FXML
    private Button buttonBUscarDoacao;

    @FXML
    private Button buttonBUscarDoador;

    @FXML
    private Button buttonCadastrarDoacoesDoador;

    @FXML
    private Button buttonCadastrarDoador;

    @FXML
    private Button buttonLimparDoacao;

    @FXML
    private Button buttonLimparDoador;

    @FXML
    private Button buttonRemoverDoador;

    @FXML
    private Button buttonVerDoacoesDoador;

    @FXML
    private DatePicker datePickerDataMax;

    @FXML
    private DatePicker datePickerDataMin;

    @FXML
    private RadioButton radioButtonA;

    @FXML
    private RadioButton radioButtonA1;

    @FXML
    private RadioButton radioButtonAB;

    @FXML
    private RadioButton radioButtonAB1;

    @FXML
    private RadioButton radioButtonB;

    @FXML
    private RadioButton radioButtonB1;

    @FXML
    private RadioButton radioButtonNegativo;

    @FXML
    private RadioButton radioButtonNegativo1;

    @FXML
    private RadioButton radioButtonO;

    @FXML
    private RadioButton radioButtonO1;

    @FXML
    private RadioButton radioButtonPositivo;

    @FXML
    private RadioButton radioButtonPositivo1;

    @FXML
    private RadioButton radioButtonQualquerUm;

    @FXML
    private RadioButton radioButtonQualquerUm1;

    @FXML
    private RadioButton radioButtonQualquerUmRH;

    @FXML
    private RadioButton radioButtonQualquerUmRH1;

    @FXML
    private TableColumn<Doador, String> tableColumnCPFDoador;

    @FXML
    private TableColumn<Doacao, String> tableColumnCPFDoadorDoacao;

    @FXML
    private TableColumn<Doacao, Long> tableColumnCodigoDoacao;

    @FXML
    private TableColumn<Doador, Long> tableColumnCodigoDoador;

    @FXML
    private TableColumn<Doador, String> tableColumnContatoDoador;

    @FXML
    private TableColumn<Doacao, String> tableColumnContatoDoadorDoacao;

    @FXML
    private TableColumn<Doacao, LocalDate> tableColumnData;

    @FXML
    private TableColumn<Doacao, LocalTime> tableColumnHora;

    @FXML
    private TableColumn<Doador, String> tableColumnNomeDoador;

    @FXML
    private TableColumn<Doacao, String> tableColumnNomeDoadorDoacao;

    @FXML
    private TableColumn<Doador, RH> tableColumnRHDoador;

    @FXML
    private TableColumn<Doador, TipoSanguineo> tableColumnTipoSanguineoDoador;

    @FXML
    private TableColumn<Doacao, Double> tableColumnVolume;

    @FXML
    private TableView<Doador> tableViewDoador;

    @FXML
    private TableView<Doacao> tableViewDoacao;

    @FXML
    private TextField textFieldCPFDoador;

    @FXML
    private TextField textFieldCPFDoadorDoacao;

    @FXML
    private TextField textFieldCodigoDoacao;

    @FXML
    private TextField textFieldCodigoDoador;

    @FXML
    private TextField textFieldCodigoDoadorDoacao;

    @FXML
    private TextField textFieldContatoDoador;

    @FXML
    private TextField textFieldContatoDoadorDoacao;

    @FXML
    private TextField textFieldHoraMax;

    @FXML
    private TextField textFieldHoraMin;

    @FXML
    private TextField textFieldNomeDoador;

    @FXML
    private TextField textFieldNomeDoadorDoacao;

    @FXML
    private TextField textFieldVolumeDoacaoMax;

    @FXML
    private TextField textFieldVolumeDoacaoMin;

    @FXML
    private TitledPane titledPaneRHDoacao;

    @FXML
    private TitledPane titledPaneRHDoador;

    @FXML
    private TitledPane titledPaneTipoSanguineoDoacao;

    @FXML
    private TitledPane titledPaneTipoSanguineoDoador;

    @FXML
    private TabPane tabTelaPrincipal;

    private Stage stageTelaCadastrarDoacao;
    private TelaCadastroDoacaoController controllerTelaCadastroDoacao;
    private Doacao doacao;
    private DAOFactory daoFactory;

    private Stage stageTelaCadastrarDoador;
    private TelaCadastroDoadorController controllerTelaCadastroDoador;
    private Doador doador;
    private Stage stageTelaAlterarDoador;
    private TelaAlterarDoadorController controllerTelaAlterarDoador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        daoFactory = new DAOFactory();
        FXMLLoader loaderCadastroDoacao = new FXMLLoader(
                getClass().getResource("/poov/doacaovisual/telaCadastroDoacao.fxml"));
        FXMLLoader loaderCadastroDoador = new FXMLLoader(
                getClass().getResource("/poov/doacaovisual/telaCadastroDoador.fxml"));
        FXMLLoader loaderAlterar = new FXMLLoader(
                getClass().getResource("/poov/doacaovisual/telaAlterarDoador.fxml"));
        Parent root;

        try {
            root = loaderCadastroDoacao.load();
            Scene scene = new Scene(root);
            stageTelaCadastrarDoacao = new Stage();
            stageTelaCadastrarDoacao.setScene(scene);
            stageTelaCadastrarDoacao.setTitle("Cadastro de doação");
            stageTelaCadastrarDoacao.getIcons().add(new Image(getClass().getResourceAsStream("/images/java.png")));
            stageTelaCadastrarDoacao.initModality(Modality.WINDOW_MODAL);
            controllerTelaCadastroDoacao = loaderCadastroDoacao.getController();

            tableColumnCodigoDoacao.setCellValueFactory(new PropertyValueFactory<Doacao, Long>("codigo"));
            tableColumnVolume.setCellValueFactory(new PropertyValueFactory<Doacao, Double>("volume"));
            tableColumnData.setCellValueFactory(new PropertyValueFactory<Doacao, LocalDate>("data"));
            tableColumnHora.setCellValueFactory(new PropertyValueFactory<Doacao, LocalTime>("hora"));
            tableColumnNomeDoadorDoacao.setCellValueFactory(
                    cellData -> new SimpleStringProperty(cellData.getValue().getDoador().getNome()));
            tableColumnCPFDoadorDoacao.setCellValueFactory(
                    cellData -> new SimpleStringProperty(cellData.getValue().getDoador().getCpf()));
            tableColumnContatoDoadorDoacao.setCellValueFactory(
                    cellData -> new SimpleStringProperty(cellData.getValue().getDoador().getContato()));

            /////////////////////////////////////////////////////////////////////////////
            root = loaderCadastroDoador.load();
            scene = new Scene(root);
            stageTelaCadastrarDoador = new Stage();
            stageTelaCadastrarDoador.setScene(scene);
            stageTelaCadastrarDoador.setTitle("Cadastro de doador");
            stageTelaCadastrarDoador.getIcons().add(new Image(getClass().getResourceAsStream("/images/java.png")));
            stageTelaCadastrarDoador.initModality(Modality.WINDOW_MODAL);
            controllerTelaCadastroDoador = loaderCadastroDoador.getController();

            root = loaderAlterar.load();
            scene = new Scene(root);
            stageTelaAlterarDoador = new Stage();
            stageTelaAlterarDoador.setScene(scene);
            stageTelaAlterarDoador.setTitle("Alteração de doador");
            stageTelaAlterarDoador.getIcons().add(new Image(getClass().getResourceAsStream("/images/java.png")));
            stageTelaAlterarDoador.initModality(Modality.WINDOW_MODAL);
            controllerTelaAlterarDoador = loaderAlterar.getController();

            tableColumnCodigoDoador.setCellValueFactory(new PropertyValueFactory<Doador, Long>("codigo"));
            tableColumnNomeDoador.setCellValueFactory(new PropertyValueFactory<Doador, String>("nome"));
            tableColumnCPFDoador.setCellValueFactory(new PropertyValueFactory<Doador, String>("cpf"));
            tableColumnContatoDoador.setCellValueFactory(new PropertyValueFactory<Doador, String>("contato"));
            tableColumnTipoSanguineoDoador
                    .setCellValueFactory(new PropertyValueFactory<Doador, TipoSanguineo>("tipoSanguineo"));
            tableColumnRHDoador.setCellValueFactory(new PropertyValueFactory<Doador, RH>("rh"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buttonAlterarDoadorClicado(ActionEvent event) {
        Doador doador = tableViewDoador.getSelectionModel().getSelectedItem();
        if (doador != null) {
            if (stageTelaAlterarDoador.getOwner() == null) {
                stageTelaAlterarDoador.initOwner(((Node) event.getSource()).getScene().getWindow());
            }
            controllerTelaAlterarDoador.limpar();
            controllerTelaAlterarDoador.setDoador(doador);
            stageTelaAlterarDoador.showAndWait();
            if (controllerTelaAlterarDoador.isValido()) {
                doador = controllerTelaAlterarDoador.getDoador();
                try {
                    daoFactory.abrirConexao();
                    DoadorDAO dao = daoFactory.criarDoadorDAO();
                    dao.atualizar(doador);
                    daoFactory.fecharConexao();
                } catch (SQLException e) {
                    DAOFactory.mostrarSQLException(e);
                }
                buttonBUscarDoadorClicado(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Selecione um doador para alterá-lo");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonBUscarDoadorClicado(ActionEvent event) {
        DoadorFilter filtro = new DoadorFilter();
        if (!textFieldCodigoDoador.getText().trim().isEmpty()) {
            filtro.setCodigo(Long.parseLong(textFieldCodigoDoador.getText()));
        }
        if (!textFieldNomeDoador.getText().trim().isEmpty()) {
            filtro.setNome(textFieldNomeDoador.getText());
        }
        if (!textFieldCPFDoador.getText().trim().isEmpty()) {
            filtro.setCpf((textFieldCPFDoador.getText()));
        }
        if (!textFieldContatoDoador.getText().trim().isEmpty()) {
            filtro.setContato(textFieldContatoDoador.getText());
        }
        if (!radioButtonQualquerUm.isSelected()) {
            if (radioButtonA.isSelected()) {
                filtro.setTipoSanguineo(TipoSanguineo.A);
            } else if (radioButtonB.isSelected()) {
                filtro.setTipoSanguineo(TipoSanguineo.B);
            } else if (radioButtonAB.isSelected()) {
                filtro.setTipoSanguineo(TipoSanguineo.AB);
            } else if (radioButtonO.isSelected()) {
                filtro.setTipoSanguineo(TipoSanguineo.O);
            }
        } else if (radioButtonQualquerUm.isSelected()) {
            filtro.setTipoSanguineo(TipoSanguineo.DESCONHECIDO);
        }
        if (!radioButtonQualquerUmRH.isSelected()) {
            if (radioButtonNegativo.isSelected()) {
                filtro.setRh(RH.NEGATIVO);
            } else if (radioButtonPositivo.isSelected()) {
                filtro.setRh(RH.POSITIVO);
            }
        } else if (radioButtonQualquerUmRH.isSelected()) {
            filtro.setRh(RH.DESCONHECIDO);
        }
        try {
            daoFactory.abrirConexao();
            DoadorDAO dao = daoFactory.criarDoadorDAO();
            List<Doador> doadores = dao.buscar(filtro);
            tableViewDoador.getItems().clear();
            tableViewDoador.getItems().addAll(doadores);
            daoFactory.fecharConexao();
        } catch (SQLException e) {
            DAOFactory.mostrarSQLException(e);
        }
    }

    @FXML
    void buttonBuscarDoacaoClicado(ActionEvent event) {
        DoacaoFilter filtro = new DoacaoFilter();
        if (!textFieldCodigoDoadorDoacao.getText().isBlank()) {
            Doador doador = new Doador();
            doador.setCodigo(Long.parseLong(textFieldCodigoDoadorDoacao.getText()));
            filtro.setDoador(doador);
        }
        try {
            daoFactory.abrirConexao();
            DoacaoDAO dao = daoFactory.criarDoacaoDAO();
            List<Doacao> doacoes = dao.buscar(filtro);
            tableViewDoacao.getItems().clear();
            tableViewDoacao.getItems().addAll(doacoes);
            daoFactory.fecharConexao();
        } catch (SQLException e) {
            DAOFactory.mostrarSQLException(e);
        }
    }

    @FXML
    void buttonCadastrarDoacoesDoadorClicado(ActionEvent event) {
        Doador doador = tableViewDoador.getSelectionModel().getSelectedItem();
        if (doador != null) {
            if (stageTelaCadastrarDoacao.getOwner() == null) {
                stageTelaCadastrarDoacao.initOwner(((Node) event.getSource()).getScene().getWindow());
            }
            controllerTelaCadastroDoacao.setDoador(doador);
            stageTelaCadastrarDoacao.showAndWait();
            Doacao doacao = controllerTelaCadastroDoacao.getDoacao();
            doacao.setDoador(doador);
            try {
                daoFactory.abrirConexao();
                DoacaoDAO dao = daoFactory.criarDoacaoDAO();
                dao.gravar(doacao);
                daoFactory.fecharConexao();
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Selecione um doador para cadastrar uma doação");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonCadastrarDoadorClicado(ActionEvent event) {
        if (stageTelaCadastrarDoador.getOwner() == null) {
            stageTelaCadastrarDoador.initOwner(((Node) event.getSource()).getScene().getWindow());
        }
        controllerTelaCadastroDoador.limpar();
        stageTelaCadastrarDoador.showAndWait();
        if (controllerTelaCadastroDoador.isValido()) {
            Doador doador = controllerTelaCadastroDoador.getDoador();
            try {
                daoFactory.abrirConexao();
                DoadorDAO dao = daoFactory.criarDoadorDAO();
                dao.gravar(doador);
                daoFactory.fecharConexao();
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        }
        buttonBUscarDoadorClicado(event);
    }

    @FXML
    void buttonLimparDoacaoClicado(ActionEvent event) {
        textFieldCodigoDoacao.clear();
        textFieldCPFDoadorDoacao.clear();
        textFieldCodigoDoadorDoacao.clear();
        textFieldContatoDoadorDoacao.clear();
        textFieldNomeDoadorDoacao.clear();
        textFieldVolumeDoacaoMin.clear();
        textFieldVolumeDoacaoMax.clear();
        textFieldHoraMin.clear();
        textFieldHoraMax.clear();
        toggleGroupRHDoacao.selectToggle(radioButtonQualquerUmRH1);
        toggleGrouptipoSanguineoDoacao.selectToggle(radioButtonQualquerUm1);
        buttonBuscarDoacaoClicado(event);
    }

    @FXML
    void buttonLimparDoadorClicado(ActionEvent event) {
        textFieldCodigoDoador.clear();
        textFieldNomeDoador.clear();
        textFieldCPFDoador.clear();
        textFieldContatoDoador.clear();
        toggleGrouptipoSanguineoDoador.selectToggle(radioButtonQualquerUm);
        toggleGroupRHDoador.selectToggle(radioButtonQualquerUmRH);
        buttonBUscarDoadorClicado(event);
    }

    @FXML
    void buttonRemoverDoadorClicado(ActionEvent event) {
        Doador doador = tableViewDoador.getSelectionModel().getSelectedItem();
        if (doador != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Você tem certeza que quer remover o doador " +
                    doador.getNome() + "?");
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
                buttonBUscarDoadorClicado(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Selecione um doador para removê-la");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonVerDoacoesDoadorClicado(ActionEvent event) {
        Doador doador = tableViewDoador.getSelectionModel().getSelectedItem();
        if (doador != null) {
            textFieldCodigoDoadorDoacao.setText("" + doador.getCodigo());
            buttonBuscarDoacaoClicado(event);
            tabTelaPrincipal.getSelectionModel().select(1);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso");
            alert.setContentText("Selecione um doador para ver suas doações");
            alert.showAndWait();
        }
    }

}
