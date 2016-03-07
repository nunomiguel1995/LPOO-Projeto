package maze.cli;
import java.util.Scanner;

import maze.logic.*;

public class main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int altura,largura, nDragoes, opcao;
		
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
		
	
		System.out.println("Escolha as dimensoes do labirinto:");
		System.out.print("Altura: ");
		altura=s.nextInt();
		System.out.print("Largura: ");
		largura=s.nextInt();
		System.out.print("Numero de Dragoes a enfrentar: ");
		nDragoes=s.nextInt();
		
		Jogo j = new Jogo(altura,largura,nDragoes); 
			
		System.out.println("Qual a estrategia pretendida (selecione a opcao):");
		System.out.println("1 - Dragao parado");
		System.out.println("2 - Dragao com movimentacao aleatoria");
		System.out.println("3 - Dragão com movimentacao aleatoria intercalada com dormir");
		
		opcao = s.nextInt();
		j.setComportamentoDragao(opcao);
	
		int continua = 0;
		while(continua == 0){
			j.imprime();
			continua = j.jogada(s);
		}
		
		s.close();
		
	}	
}