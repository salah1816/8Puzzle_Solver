package Model;

import Model.Actions.Action;
import Model.Recherche.DFSDrias;
import Model.Recherche.Largeur;
import Model.Recherche.Recherche;
import Model.Taquin.InitEtat;
import Model.Taquin.Taquin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    public static Taquin etatFinal;
    private static final int n = 3;
    protected static Taquin etatInitial;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("MainView.fxml"));
        Scene scene=new Scene(fxmlLoader.load(), 900, 500);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        stage.setTitle("NPuzzle-Solver");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("jigsaw.png")));
        //stage.getIcons().add(new Image( "file:jigsaw.png"));
        stage.show();
    }

    public static void main(String[] args) {
//
//        etatInitial = InitEtat.getEtatInitial();
//        etatInitial.afficherTaquin();
//
//        etatFinal = InitEtat.getEtatFinal();
//        etatFinal.afficherTaquin();

//		Recherche recherche0 = new Largeur(etatInitial, etatFinal);
//		long time = TimeTest.testTime(recherche0);
//		System.out.println("the Algorithm BFS was done in : "+time+" milliseconds");
//
//        Recherche recherche1 = new DFSDrias(etatInitial, etatFinal,4);
//////        List<Action> l=recherche1.run();
//////        if(l.isEmpty()) System.out.println("there is no solution");
//        time = TimeTest.testTime(recherche1);
//        System.out.println("the Algorithm DFS was done in : "+time+" milliseconds");

//        Recherche recherche2 = new A(etatInitial, etatFinal,new Manhatten());
//
//        time = TimeTest.testTime(recherche2);
//        System.out.println("the Algorithm A star was done in : "+time+" milliseconds");


//		// get right statical data about node develope et exploré
//		SaveResults saveResults = new SaveResults(etatInitial, recherche.getSolution(), time, recherche.getOuvertSize(), recherche.getFermerSize(), recherche.toString());
//		saveResults.save();

//        Experementing experementing = new Experementing(InitEtat.getTestingData());
//        experementing.runExperiments();
        launch();
    }


    public static int getN() {
        return n;
    }


}


// 7+6+5+4+3+2+1














