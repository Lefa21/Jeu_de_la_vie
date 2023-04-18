package com.application.loupmouton;
import com.application.loupmouton.modele.Acteur;
import com.application.loupmouton.modele.Environnement;
import com.application.loupmouton.modele.Loup;
import com.application.loupmouton.modele.Mouton;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Environnement environnement;
    private Loup loup;
    private Mouton mouton;

   @FXML TextField nbTour;

   @FXML
    private Pane PanneauJeu;

    @FXML
    private ToggleGroup groupeRadio;

    @FXML
    private RadioButton ButtonLoup;

    @FXML
    private RadioButton ButtonMouton;

    @FXML
    private TextField nbLM;

    @FXML
    private Label labelTour;

    @FXML
    private Slider sliderLoup;

    @FXML
    private Slider sliderMouton;

    @FXML
    private Label journée;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.environnement = new Environnement(300,300);
       // this.labelTour.textProperty().bind(this.environnement.nbToursProperty().asString());
       this.environnement.nbToursProperty().addListener((obs,old,nouv) -> labelTour.setText(nouv.toString()));
       sliderLoup.valueProperty().addListener(((observableValue, old, nouv) -> Loup.setReproduction(sliderLoup.getValue())));
        sliderMouton.valueProperty().addListener(((observableValue, old, nouv) -> Mouton.setReproduction(sliderMouton.getValue())));

    }
    @FXML
    void ajoutLM(ActionEvent event){
        int nbIndividus = (Integer.parseInt(nbLM.getText()));
        Toggle buttonSelect = groupeRadio.getSelectedToggle();
        if(buttonSelect.equals(ButtonLoup)){
            for (int i = 0; i < nbIndividus; i++) {
                Acteur loup = new Loup(this.environnement);
                System.out.println("je suis entrer dans la boucle Loup");
                this.environnement.ajouter(loup);
                creerSprite(loup);
                System.out.println(this.environnement.getActeurs());
                }
                }
        else if(buttonSelect.equals(ButtonMouton)){
            for (int i = 0; i < nbIndividus; i++) {
                Acteur mouton = new Mouton(this.environnement);
                System.out.println("je suis entrer dans la boucle Mouton");
                this.environnement.ajouter(mouton);
                creerSprite(mouton);
                System.out.println(this.environnement.getActeurs());
            }
        }
    }

    public void creerSprite(Acteur a){
        if(a instanceof Loup){
            System.out.println("je suis dans créer sprite loup");
            Circle r = new Circle(3);

            r.setFill(Color.RED);
            //r.setTranslateX(a.getX());
           // r.setTranslateY(a.getY());
            r.translateXProperty().bind(a.xProperty());
            r.translateYProperty().bind(a.yProperty());
            r.setId(a.getId());
            PanneauJeu.getChildren().add(r);
            System.out.println("le pane" + PanneauJeu.getChildren());
        }
        else if(a instanceof Mouton){
            Circle r = new Circle(2);

            System.out.println("je suis dans créer sprite Mouton");
            r.setFill(Color.WHITE);
            r.setTranslateX(a.getX());
            r.setTranslateY(a.getY());
            r.translateXProperty().bind(a.xProperty());
            r.translateYProperty().bind(a.yProperty());
            r.setId(a.getId());
            PanneauJeu.getChildren().add(r);
            System.out.println("le pane" + PanneauJeu.getChildren());
        }
    }

/*
    public void rafraichirAffichage(){
        System.out.println("je rentre dans la fonction rafraichir");
        for (int i = 0; i < this.environnement.getActeurs().size(); i++) {
            System.out.println("je rentre dans le for");
            if(PanneauJeu.lookup("#" + this.environnement.getActeurs().get(i).getId()) != null){
                System.out.println("je rentre dans le if");
                Node r = PanneauJeu.lookup("#" + this.environnement.getActeurs().get(i).getId());
                r.setTranslateX(this.environnement.getActeurs().get(i).getX());
                r.setTranslateY(this.environnement.getActeurs().get(i).getY());
            }
            else{
                System.out.println("je suis dans le else");
                 creerSprite(this.environnement.getActeurs().get(i));
                System.out.println("Le cercle : " + PanneauJeu.lookup("#" + this.environnement.getActeurs().get(i).getId()) + " bien était créer" );
            }
        }

        /*
        for (int i = 0; i < PanneauJeu.getChildren().size(); i++) {
            for (int j =0 ; j < this.environnement.getActeurs().size(); j++) {
                System.out.println("L'acteur : " + this.environnement.getActeurs().get(i).getId() + "est mort, on enleve son sprit" );
                Node r = PanneauJeu.lookup("#" + this.environnement.getActeurs().get(i).getId());
                PanneauJeu.getChildren().remove(r);
                System.out.println( "le cercle r à bien était supprimé si : " + PanneauJeu.lookup("#" + r.getId()));
            }
        }
    */


    @FXML
    void faireDesTours(ActionEvent event) {
        System.out.println("clic sur bouton lancer");
        int nombreTour = (Integer.parseInt(nbTour.getText()));
        for (int i = 0; i < nombreTour; i++) {
            System.out.println("tour numéro : " + (i+1));
            this.environnement.unTour();
        }

        if((this.environnement.nbToursProperty().getValue()%12) != 0){
            journée.setText("jour");
        }
        else{
            journée.setText("nuit");
        }
    }

    @FXML
    void unTour(ActionEvent event) {
        this.environnement.unTour();

        if((this.environnement.nbToursProperty().getValue()%12) !=0){
            journée.setText("jour");
        }
        else{
            journée.setText("nuit");
        }
       /* rafraichirAffichage(); */
    }
}
