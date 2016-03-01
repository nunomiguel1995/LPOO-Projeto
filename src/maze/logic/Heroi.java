package maze.logic;

public class Heroi extends Personagem {
	private boolean armado;

	public Heroi(int x,int y,char simb,boolean armado){
		super(x,y,simb);
		this.armado=armado;
	}

	public boolean isArmado() {
		return armado;
	}

	public void setArmado(boolean armado) {
		this.armado = armado;
	}

	public boolean getArmado(){
		return armado;
	}
}
