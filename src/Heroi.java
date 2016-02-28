
public class Heroi {
	private int x;
	private int y;
	private boolean armado;
	private char simbolo;
	
	public Heroi(int x,int y,char simb,boolean armado){
		this.x=x;
		this.y=y;
		this.armado=armado;
		simbolo= simb;
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

	public boolean isArmado() {
		return armado;
	}

	public void setArmado(boolean armado) {
		this.armado = armado;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	public boolean getArmado(){
		return armado;
	}
}
