package com.application.loupmouton.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Environnement {

	private int width,height;	
	private ArrayList<Acteur> acteurs;
	private IntegerProperty nbToursProperty;

	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.nbToursProperty = new SimpleIntegerProperty(0);
		this.acteurs= new ArrayList<>();
	}

	public final IntegerProperty nbToursProperty(){
		return nbToursProperty;
	}

	public final int getNbToursProperty(){
		return this.nbToursProperty.getValue();
	}

	public final void setNbTours(int n){
		this.nbToursProperty.setValue(n);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Acteur> getActeurs() {
		return acteurs;
	}

	public Acteur getActeur(String id) {
		for(Acteur a:this.acteurs){
			if(a.getId().equals(id)){
				return a;
			}
		}
		return null;
	}


	public void ajouter(Acteur a){
		acteurs.add(a);
	}

	public boolean dansTerrain(int x, int y){
		return (0 <= x && x<this.width && 0<=y && y< this.height);
	}

	public void unTour(){
		// cela ne peut etre un foreach a cause des naissances 
		// modification de acteurs.
		//System.out.println("tour " + this.nbTours);
		for(int i=0;i<acteurs.size(); i++){
			Acteur a = acteurs.get(i);			
			a.agit();
		}
		for(int i=acteurs.size()-1; i>=0;i--){
			Acteur a = acteurs.get(i);
			if(!a.estVivant()){
				System.out.println("mort de : " + a);
				acteurs.remove(i);
			}
		}


		this.nbToursProperty.setValue(this.nbToursProperty.getValue() +1);
		System.out.println("tour " + this.nbToursProperty.getValue());
	}


	public void init(){
		for(int i=0; i<50;i++){
			this.ajouter(new Loup(this));
		}
		for(int i=0; i<100;i++){
			this.ajouter(new Mouton(this));
		}
	}

	@Override
	public String toString() {
		return "Environnement{" +
				"width=" + width +
				", height=" + height +
				", acteurs=" + acteurs +
				", nbTours=" + getNbToursProperty() +
				'}';
	}
}
