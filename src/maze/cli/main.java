package maze.cli;
import java.util.Scanner;

import maze.logic.*;
import maze.logic.Jogo.Direcao;
import maze.logic.Jogo.EstadoJogo;

public class main {
	static Scanner s = new Scanner(System.in);
	private static Jogo j;
	
	public static void main(String[] args) {
		initializeGame();
		
		while(j.getEstado() != EstadoJogo.PERDEU && j.getEstado() != EstadoJogo.GANHOU){
			System.out.print(j.toString());
			iteration();
		}		
		s.close();		
	}
	
	public static void initializeGame(){
		int altura,largura, nDragoes, opcao;
		try{		
		System.out.println("Escolha as dimensoes do labirinto:");
		System.out.print("Altura: ");
		altura=s.nextInt();
		System.out.print("Largura: ");
		largura=s.nextInt();
		System.out.print("Numero de Dragoes a enfrentar: ");
		nDragoes=s.nextInt();
		
		if(altura<= 3|| largura <= 3 || nDragoes <= 0 
							||(altura % 2) == 0 || (largura %2) == 0)
			throw new IllegalArgumentException();
		
		j = new Jogo(altura,largura,nDragoes);
 		
		System.out.println("Qual a estrategia pretendida (selecione a opcao):");
		System.out.println("1 - Dragao parado");
		System.out.println("2 - Dragao com movimentacao aleatoria");
		System.out.println("3 - Dragão com movimentacao aleatoria intercalada com dormir");
		
		opcao = s.nextInt();
		if(opcao!=1 && opcao!=2 && opcao!=3)
			throw new IllegalArgumentException();
		j.setComportamentoDragao(opcao);

		}catch(IllegalArgumentException e){
			System.out.println("Valores Invalidos!");
		}		
	}
	
	public static void iteration(){
		int direcao;
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
}