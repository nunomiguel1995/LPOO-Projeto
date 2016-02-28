import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Labirinto lab= new Labirinto();

		lab.print();

		int gameOver=0;

		while(gameOver==0){
			gameOver = lab.moveHeroi();
			lab.print();
		}

	}	
}