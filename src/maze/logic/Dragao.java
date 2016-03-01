package maze.logic;

public class Dragao extends Personagem {
	boolean vivo;
	
	public Dragao(int x, int y, char simbolo){
		super(x,y,simbolo);
		vivo=true;
	}
	
	public boolean getVivo(){
		return vivo;
	}

	public void setVivo(boolean vivo){
		this.vivo=vivo;
	}
	
}
