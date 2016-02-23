
public class Heroi {
	int x;
	int y;
	boolean armado;
	char simbolo;
	
	public Heroi(int x,int y,char simb){
		this.x=x;
		this.y=y;
		armado=false;
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
	
	
}
