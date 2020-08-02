package com.txtlearn.blackjack01;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;
	//constructor
	public Deck(){
		//Crea una nueva baraja de naipes
		this.cards = new ArrayList<Card>();
	
	}
	
	//Agrega 52 cartas a un mazo
	public void createFullDeck(){
		//Generar Cartas
		//Bucle de Suits
		for(Suit cardSuit : Suit.values()){
			//Bucle de Values
			for(Value cardValue : Value.values()){
				//Agregar nueva Carta a la mezcla
				this.cards.add(new Card(cardSuit,cardValue));
			}
		}
	}
	
	
//Shuffle baraja de cartas
public void shuffle(){
	//Crea una nueva lista de arrays para guardar temporalmente las cartas barajadas
	ArrayList<Card> tmpDeck = new ArrayList<Card>();
	//Elija al azar del mazo anterior y copie valores al nuevo mazo
	Random random = new Random();
	int randomCardIndex = 0;
	int originalSize = this.cards.size();
	for(int i = 0; i<originalSize;i++){
		//genera un número aleatorio de acuerdo con int randomNum = rand.nextInt ((max - min) + 1) + min;
		randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
		//lanzar cartas al azar en una nueva baraja
		tmpDeck.add(this.cards.get(randomCardIndex));
		//eliminar recogido de la cubierta anterior
		this.cards.remove(randomCardIndex);
	}
	//establece this.deck en nuestro mazo recién barajado
	this.cards = tmpDeck;
}
	
	
	//Retira una carta del mazo
	public void removeCard(int i){
		this.cards.remove(i);
	}
	//Obtener carta del mazo
	public Card getCard(int i){
		return this.cards.get(i);
	}
	
	//Agregar carta al mazo
	public void addCard(Card addCard){
		this.cards.add(addCard);
	}
	
	//Roba una carta superior del mazo
	public void draw(Deck comingFrom){
		//Agrega una carta a este mazo desde el mazo del que provenga
		this.cards.add(comingFrom.getCard(0));
		//Retira la carta del mazo de donde viene
		comingFrom.removeCard(0);
	}
	
	//Se uso para imprimir la baraja
	public String toString(){
		String cardListOutput = "";
		int i = 0;
		for(Card aCard : this.cards){
			cardListOutput += "\n" + "-" + aCard.toString();
			i++;
		}
		return cardListOutput;
	}
	
	public void moveAllToDeck(Deck moveTo){
		int thisDeckSize = this.cards.size();
		//poner cartas en movimiento al mazo
		for(int i = 0; i < thisDeckSize; i++){
			moveTo.addCard(this.getCard(i));
		}
		//vaciar la cubierta
		for(int i = 0; i < thisDeckSize; i++){
			this.removeCard(0);
		}
	}
	
	public int deckSize(){
		return this.cards.size();
	}
	
	//Calcular el valor del mazo
	public int cardsValue(){
		int totalValue = 0;
		int aces = 0;
		//For cada carta en la baraja
		for(Card aCard : this.cards){
			//Switch para posibles valores
			switch(aCard.getValue()){
			case DOS: totalValue += 2; break;
			case TRES: totalValue += 3; break;
			case CUATRO: totalValue += 4; break;
			case CINCO: totalValue += 5; break;
			case SEIS: totalValue += 6; break;
			case SIETE: totalValue += 7; break;
			case OCHO: totalValue += 8; break;
			case NUEVE: totalValue += 9; break;
			case DIEZ: totalValue += 10; break;
			case JOKER: totalValue += 10; break;
			case REINA: totalValue += 10; break;
			case REY: totalValue += 10; break;
			case ACE: aces += 1; break;
			}			
		}
		
		//Determine el valor actual total con ases
		//Aces vale 11 o 1: si 11 superara 21, valiera 1
		for(int i = 0; i < aces; i++){
			//If ya tienen más de 10 y obtener un as valorado en 11 los elevaría a 22, por lo que el as vale uno
			if (totalValue > 10){
				totalValue += 1;
			}
			else{
				totalValue += 11;
			}
		}
		
		//Return para retornar valor 
		return totalValue;
	
	}
	
	
}
