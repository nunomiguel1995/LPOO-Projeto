package maze.cli;
import java.util.Scanner;

import maze.logic.*;
import maze.logic.Jogo.Direcao;
import maze.logic.Jogo.EstadoJogo;

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
	
		int direcao;
		while(j.getEstado() != EstadoJogo.PERDEU && j.getEstado() != EstadoJogo.GANHOU){
			j.imprime();
			System.out.println("1 - Cima");
			System.out.println("2 - Baixo");
			System.out.println("3 - Esquerda");
			System.out.println("4 - Direita");
			System.out.print("Escolha a direcao: ");	
			direcao = s.nextInt();
			System.out.println();
			switch(direcao){
			case 1: j.jogada(Direcao.CIMA); break;
			case 2: j.jogada(Direcao.BAIXO); break;
			case 3: j.jogada(Direcao.ESQUERDA); break;
			case 4: j.jogada(Direcao.DIREITA); break;
			default: break;
			}
			if(j.getEstado() != EstadoJogo.INICIAL)
				System.out.println(j.imprimeEstado());
		}		
		s.close();		
	}
}