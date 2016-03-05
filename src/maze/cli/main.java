package maze.cli;
import java.util.Scanner;

import maze.logic.*;

public class main {
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int opcao;
		
		System.out.println("Qual a estrategia pretendida (selecione a opcao):");
		System.out.println("1 - Dragao parado");
		System.out.println("2 - Dragao com movimentacao aleatoria");
		System.out.println("3 - Dragão com movimentacao aleatoria intercalada com dormir");
		
		opcao = s.nextInt();
		boolean dragaoParado = false, dragaoAdormece = false;
		
		switch(opcao){
		case 1:
			 dragaoParado = true;
			 dragaoAdormece = false;
			break;
		case 2:
			 dragaoParado = false;
			 dragaoAdormece = false;
			 break;
		case 3:
			 dragaoParado = false;
			 dragaoAdormece = true;
			break;			
		}
		
		Jogo j= new Jogo(dragaoParado, dragaoAdormece);
		
		j.getLabirinto().print();

		int gameOver=0;

		while(gameOver==0){
			gameOver = j.getLabirinto().jogada();
			j.getLabirinto().print();
		}

		s.close();
	}	
}