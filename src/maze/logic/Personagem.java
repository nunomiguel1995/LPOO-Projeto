package maze.logic;

public class Personagem {
	private int x;
	private int y;
	private char simbolo;
	
	public Personagem(int x,int y,char simbolo){
		this.x=x;
		this.y=y;
		this.simbolo=simbolo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
}
