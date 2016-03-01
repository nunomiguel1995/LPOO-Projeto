package maze.cli;
import java.util.Scanner;

import maze.logic.*;

public class main {
	public static void main(String[] args) {
		Jogo j=new Jogo();

		j.getLabirinto().print();

		int gameOver=0;

		while(gameOver==0){
			gameOver = j.moveHeroi();
			j.getLabirinto().print();
		}

	}	
}