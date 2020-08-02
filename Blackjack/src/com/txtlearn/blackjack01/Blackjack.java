package com.txtlearn.blackjack01;
/**
 *
 * @author GUSTAVOCASTILLO-2"B"-ISC
 */

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args){
		
		System.out.println("Bienvenido a Blackjack!");
		
		//playingDeck será el mazo que tiene el crupier
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		//playerCards Serán las cartas que el jugador tenga en su mano.
		Deck playerCards = new Deck();
		
		double playerMoney = 1000.0;
		//dealerCards serán las cartas que el crupier tenga en sus manos
		Deck dealerCards = new Deck();
		
		//Scanner for user input
		Scanner userInput = new Scanner(System.in);
		
		//Juega mientras el jugador tiene dinero
		//Bucle de juego
while(playerMoney>0){
	//Take Bet
	System.out.println("Tienes $" + playerMoney + ", ¿Cuánto te gustaría apostar?");
	double playerBet = userInput.nextDouble();
	boolean endRound = false;
	if(playerBet > playerMoney){
		//Romper si apuestan demasiado
		System.out.println("No puedes apostar más de lo que tienes.");
		break;
	}
	
	System.out.println("La Partida a empezado...");
	//El jugador recibe dos cartas
	playerCards.draw(playingDeck);
	playerCards.draw(playingDeck);
	
	//El crupier recibe dos cartas
	dealerCards.draw(playingDeck);
	dealerCards.draw(playingDeck);
			
			//Bucle para nuevas cartas
			while(true)
			{
				//Mostrar cartas de jugador
				System.out.println("Tu mano:" + playerCards.toString());
				
				//Mostrar valor
				System.out.println("Tu mano está actualmente valorada en: " + playerCards.cardsValue());
				
				//Mostrar tarjetas del oponente
				System.out.println("Mano del oponente: " + dealerCards.getCard(0).toString() + " y su valor es: " + dealerCards.cardsValue());
				
				//Que quieren hacer ellos
				System.out.println("¿Te gustaría (1) tomar otra carta o (2) quedar asi");
				int response = userInput.nextInt();	
				//Golpea
				if(response == 1){
					playerCards.draw(playingDeck);
					System.out.println("Haz dibujado un: " + playerCards.getCard(playerCards.deckSize()-1).toString());
					//SI LA MANO SE PASA 21
					if(playerCards.cardsValue() > 21){
						System.out.println("Tu Mano Actualmente esta valorado en: " + playerCards.cardsValue());
                                                System.out.println("Haz Perdido ");
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				
				//Se retira 
				if(response == 2){
                                   if((dealerCards.cardsValue() < playerCards.cardsValue())&&endRound == false){
						System.out.println("Tu Mano Actualmente esta valorado en: " + playerCards.cardsValue());
                                                System.out.println("Haz Ganado ");
						playerMoney += playerBet;
						endRound = true;
						break;
					} 
					
				}
				
			}
				
			//Revelar tarjetas del crupier
			System.out.println("Tus Cartas:" + dealerCards.toString());
			//Vea si el crupier tiene más puntos que el jugador
			if((dealerCards.cardsValue() < playerCards.cardsValue())&&endRound == false){
				System.out.println("Haz ganado " + dealerCards.cardsValue() + " a " + playerCards.cardsValue());
				playerMoney *= playerBet;
				endRound = true;
			}
			//Crupier llega a 16 puestos a 17
			while((dealerCards.cardsValue() < 17) && endRound == false){
				dealerCards.draw(playingDeck);
				System.out.println("Haz dibujado: " + dealerCards.getCard(dealerCards.deckSize()-1).toString());
			}
			//Mostrar el valor del oponente
			System.out.println("Valor de la mano del oponente: " + dealerCards.cardsValue());
			//Determine if dealer busted
			if((dealerCards.cardsValue()> 21)&& endRound == false){
				System.out.println("Mano del oponente. ¡Tú ganas!");
				playerMoney += playerBet;
				endRound = true;
			}
			//Determinar si empujar
			if((dealerCards.cardsValue() == playerCards.cardsValue()) && endRound == false){
				System.out.println("Empujar.");
				endRound = true;
			}
			//Determinar si la jugador(a) gana
			if((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false){
				System.out.println("Tú ganas la mano.");
				playerMoney += playerBet;
				endRound = true;
			}
			else if(endRound == false) //crupier gana
			{
				System.out.println("Te he ganado.");
				playerMoney -= playerBet;
			}

			//Fin de la mano: vuelve a poner las cartas en el mazo
			playerCards.moveAllToDeck(playingDeck);
			dealerCards.moveAllToDeck(playingDeck);
			System.out.println("Fin de la mano.");
			
		}
		//El juego ha terminado
		System.out.println("¡Juego terminado! Perdiste todo tu dinero. :");
		
		//Cerrar escáner
		userInput.close();
		
	}
	
	
}
