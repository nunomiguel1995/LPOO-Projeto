import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Labirinto lab= new Labirinto();
		Heroi heroi = lab.getHeroi();
		String movimento;
		
		lab.print();
		
		System.out.println("Mover herói: ");
		movimento = s.next();
		
		int x = heroi.getX(),y = heroi.getY();
		char[][] layout = lab.getLayout();
		
		switch(movimento){
		case "N":
			layout[x][y] = ' ';
			if(y-1>=1) heroi.setY(y-1);
			layout[heroi.getX()][heroi.getY()] = heroi.getSimbolo();
			break;
		case "S":
			layout[x][y] = ' ';
			if(y+1<=9) heroi.setY(y+1);
			layout[heroi.getX()][heroi.getY()] = heroi.getSimbolo();
			break;
		case "E":
			layout[x][y] = ' ';
			if(x+1<=9) heroi.setY(x+1);
			layout[heroi.getX()][heroi.getY()] = heroi.getSimbolo();
			break;
		case "O":
			layout[x][y] = ' ';
			if(x-1>=1) heroi.setY(x-1);
			layout[heroi.getX()][heroi.getY()] = heroi.getSimbolo();
			break;
		default:
			break;
		}
		
		lab.moveHeroi(heroi.getX(), heroi.getY(), heroi.getSimbolo());
		lab.print();
	}
}