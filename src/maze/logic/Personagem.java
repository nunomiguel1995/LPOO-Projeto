package maze.logic;

public class Personagem {
	private char simbolo;
	private Ponto p;
	
	public Personagem(int x,int y,char simbolo){
		this.p = new Ponto(x,y);
		this.simbolo = simbolo;
	}

	public Ponto getPosicao(){
		return p;
	}

	public void setPosicao(Ponto p){
		this.p = p;
	}
	
	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
}
