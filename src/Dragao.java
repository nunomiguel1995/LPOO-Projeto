
public class Dragao {
	int x;
	int y;
	char simbolo;
	boolean vivo;
	
	public Dragao(int x, int y, char simbolo){
		this.x=x;
		this.y=y;
		this.simbolo=simbolo;
		vivo=true;
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
	public boolean getVivo(){
		return vivo;
	}
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	public void setVivo(boolean vivo){
		this.vivo=vivo;
	}
	
}
