package maze.cli;
import java.util.Scanner;

import maze.logic.*;

public class main {
	public static void main(String[] args) {
		char[][] mapa = new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','D','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','E','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
			};
		
		Jogo j = new Jogo(mapa);
		j.lePosicoes(); 
		
		Scanner s = new Scanner(System.in);
		int opcao;
		
		System.out.println("Qual a estrategia pretendida (selecione a opcao):");
		System.out.println("1 - Dragao parado");
		System.out.println("2 - Dragao com movimentacao aleatoria");
		System.out.println("3 - Dragão com movimentacao aleatoria intercalada com dormir");
		
		opcao = s.nextInt();
		
		switch(opcao){
		case 1:
			j.setComportamentoDragao(opcao);
			break;
		case 2:
			j.setComportamentoDragao(opcao);
			break;
		case 3:
			j.setComportamentoDragao(opcao);
			break;
		default:
			break;
		}
		
		int continua = 0;
		
		while(continua == 0){
			j.imprime();
			continua = j.jogada(s);
		}
		
		s.close();
	}	
}