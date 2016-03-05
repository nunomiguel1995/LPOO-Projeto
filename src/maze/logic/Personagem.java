package maze.logic;

public class Personagem {
	private Ponto posicao;
	private char simbolo;
	
	public Personagem(Ponto p, char simbolo){
		this.posicao = p;
		this.simbolo = simbolo;
	}

	public Ponto getPosicao() {
		return posicao;
	}

	public void setPosicao(Ponto posicao) {
		this.posicao = posicao;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
}
