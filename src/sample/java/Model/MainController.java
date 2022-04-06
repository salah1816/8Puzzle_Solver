package Model;


import Model.Actions.Action;
import Model.Recherche.A;
import Model.Recherche.DFSDrias;
import Model.Recherche.Heuristique.Manhatten;
import Model.Recherche.Heuristique.NumberWrongPos;
import Model.Recherche.Largeur;
import Model.Recherche.Recherche;
import Model.Taquin.Taquin;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static Model.Main.etatFinal;
import static Model.Main.etatInitial;

public class MainController implements Initializable {
    public static Alert D = new Alert(Alert.AlertType.ERROR, "You must have 9 digits", ButtonType.OK);
    public static Alert S = new Alert(Alert.AlertType.INFORMATION, "You have achieved the goal", new ButtonType("Great"));
    public static List<Action> contreSolution;
    public static int indexAction=-1;
    public static Taquin taquin=null;
    public Recherche recherche;
    static int size=Main.getN();
    @FXML
    private Button BtnRun,BtnEnd,BtnNext,BtnPre;
    @FXML
    private Pane pane0,pane1,pane2,pane3,pane4,pane5,pane6,pane7,pane8;
    @FXML
    private ChoiceBox<String> searchMethod;

    @FXML
    private ComboBox ComboHeur;
    @FXML
    private Label LabelExpandNodes,LabelRunTime,LabelSearchDeep;

    @FXML
    private Label c1,c2,c3,c4,c5,c6,c7,c8,c9;

    @FXML
    private TextField TFinit,TFfinal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchMethod.getItems().addAll("BFS","DFS","A*");
        Platform.runLater(() -> { // to put the label for choiceBox
            SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) searchMethod.getSkin();
            for (Node child : skin.getChildren()) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    if (label.getText().isEmpty()) {
                        label.setText("search method?");
                    }
                    return;
                }
            }
        });
        ComboHeur.getItems().addAll("Manhattan","MalPlacee");
        ComboHeur.setValue("Manhattan");
        ComboHeur.setVisible(false);
        BtnNext.setDisable(true);
        BtnPre.setDisable(true);
        BtnEnd.setVisible(false);
        initializePanes();
        //searchMethod.setValue("A*");
        fillMatrix();
        TFinit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                if (oldValue.length()==Math.pow(size,2)) emptyMatrix();
                else if(newValue.length()==Math.pow(size,2)) fillMatrix();
                System.out.println(" Text Changed to  " + newValue + ")\n");
            }
        });
        searchMethod.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(taquin != null) removePane(taquin.getCaseVide().cellNumber());
                fillMatrix();
                if(Objects.equals(newValue, "A*")) ComboHeur.setVisible(true);
                else ComboHeur.setVisible(false);
            }
        });

    }
    public void run(Event e) throws CloneNotSupportedException {

        if(!searchMethod.getSelectionModel().isEmpty()){
            etatInitial=new Taquin(TFinit.getText(),size);
            taquin=(Taquin) etatInitial.clone();
            taquin.afficherTaquin();
            addPane(taquin.getCaseVide().cellNumber());
            etatFinal=new Taquin(TFfinal.getText(),size);
            etatFinal.afficherTaquin();
            switch (searchMethod.getSelectionModel().getSelectedItem()) {
                case "BFS":
                    Recherche recherche0 = new Largeur(Main.etatInitial, etatFinal);
                    long time = TimeTest.testTime(recherche0);
                    LabelRunTime.setText(time + " msec");
                    LabelSearchDeep.setText(String.valueOf(recherche0.getSolution().size()));
                    LabelExpandNodes.setText(String.valueOf(recherche0.getFermerSize() + recherche0.getOuvertSize()));
                    recherche=recherche0;
                    break;
                case "DFS":
                    Recherche recherche1 = new DFSDrias(etatInitial, etatFinal, 10);
                    time = TimeTest.testTime(recherche1);
                    LabelRunTime.setText(time + " msec");
                    LabelSearchDeep.setText(String.valueOf(recherche1.getSolution().size()));
                    LabelExpandNodes.setText(String.valueOf(recherche1.getFermerSize() + recherche1.getOuvertSize()));
                    recherche=recherche1;
                    break;
                case "A*":
                    Recherche recherche2;
                    if ("Manhattan".equals(ComboHeur.getSelectionModel().getSelectedItem())) {
                        recherche2 = new A(etatInitial, etatFinal, new Manhatten());
                    } else recherche2 = new A(etatInitial, etatFinal, new NumberWrongPos());
                    time = TimeTest.testTime(recherche2);
                    LabelRunTime.setText(time + " msec");
                    System.out.println("recherche2.getSolution() = "+recherche2.getSolution());
                    LabelSearchDeep.setText(String.valueOf(recherche2.getSolution().size()));
                    LabelExpandNodes.setText(String.valueOf(recherche2.getFermerSize() + recherche2.getOuvertSize()));
                    recherche=recherche2;
                    break;
            }
            indexAction=0;
            contreSolution=Recherche.getContreDirectionSolution(recherche.getSolution());
            TFinit.editableProperty().setValue(false);
            TFfinal.editableProperty().setValue(false);
            BtnNext.setDisable(false);
            BtnPre.setDisable(false);
            BtnEnd.setVisible(true);
        }
    }

    private void initializePanes() {
        pane0.setVisible(false);
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
    }
    public void addPane(int i){
        switch (i){
            case 0: pane0.setVisible(true); break;
            case 1: pane1.setVisible(true); break;
            case 2: pane2.setVisible(true); break;
            case 3: pane3.setVisible(true); break;
            case 4: pane4.setVisible(true); break;
            case 5: pane5.setVisible(true); break;
            case 6: pane6.setVisible(true); break;
            case 7: pane7.setVisible(true); break;
            case 8: pane8.setVisible(true); break;
        }
    }
    public void removePane(int i){
        switch (i){
            case 0: pane0.setVisible(false); break;
            case 1: pane1.setVisible(false); break;
            case 2: pane2.setVisible(false); break;
            case 3: pane3.setVisible(false); break;
            case 4: pane4.setVisible(false); break;
            case 5: pane5.setVisible(false); break;
            case 6: pane6.setVisible(false); break;
            case 7: pane7.setVisible(false); break;
            case 8: pane8.setVisible(false); break;
        }
    }
    //public boolean checkInput(String v)
    public void next(){
        if(indexAction>=0 && indexAction<recherche.getSolution().size()){
            removePane(taquin.getCaseVide().cellNumber());
            recherche.getSolution().get(indexAction).action(taquin);
            addPane(taquin.getCaseVide().cellNumber());
            setMatrix(taquin.getTaquinStr());
            indexAction++;
        }else if(indexAction==recherche.getSolution().size())
            S.show();
    }
    public void previous(){

        if(indexAction>0){
            removePane(taquin.getCaseVide().cellNumber());
            contreSolution.get(indexAction-1).action(taquin);
            setMatrix(taquin.getTaquinStr());
            addPane(taquin.getCaseVide().cellNumber());
            indexAction--;
        }
    }
    public void end(){
        TFinit.editableProperty().setValue(true);
        TFfinal.editableProperty().setValue(true);
        indexAction=-1;
        BtnNext.setDisable(true);
        BtnPre.setDisable(true);
        removePane(taquin.getCaseVide().cellNumber());
        fillMatrix();
        LabelRunTime.setText("0");
        LabelSearchDeep.setText("0");
        LabelExpandNodes.setText("0");
    }

    public void setMatrix(String str){
        str=str.replace('0',' ');
        if(str.length()==(size*size)){
            c1.setText(String.valueOf(str.charAt(0)));
            c2.setText(String.valueOf(str.charAt(1)));
            c3.setText(String.valueOf(str.charAt(2)));
            c4.setText(String.valueOf(str.charAt(3)));
            c5.setText(String.valueOf(str.charAt(4)));
            c6.setText(String.valueOf(str.charAt(5)));
            c7.setText(String.valueOf(str.charAt(6)));
            c8.setText(String.valueOf(str.charAt(7)));
            c9.setText(String.valueOf(str.charAt(8)));

        }else TFinit.setOnAction(actionEvent -> {
            D.show();
        });
    }
    public void fillMatrix(){
        String str=TFinit.getText();
        setMatrix(str);
    }
    public void emptyMatrix(){
        c1.setText("");
        c2.setText("");
        c3.setText("");
        c4.setText("");
        c5.setText("");
        c6.setText("");
        c7.setText("");
        c8.setText("");
        c9.setText("");
    }
    public void entered(Event e) {
        ((Node) e.getSource()).setScaleX(1.1);
        ((Node) e.getSource()).setScaleY(1.1);
    }

    public void exited(Event e) {
        ((Node) e.getSource()).setScaleX(1);
        ((Node) e.getSource()).setScaleY(1);
    }
}