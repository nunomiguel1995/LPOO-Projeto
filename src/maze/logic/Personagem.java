package maze.logic;

public class Personagem {
	private Ponto posicao;
	private char simbolo;
	
	/**
	 * Constrói uma personagem no Ponto p e representada pelo simbolo.
	 * @param p Ponto da personagem
	 * @param simbolo simbolo da personagem
	 */
	public Personagem(Ponto p, char simbolo){
		this.posicao = p;
		this.simbolo = simbolo;
	}

	/**
	 * Devolve a posição da personagem
	 * @return posição da personagem
	 */
	public Ponto getPosicao() {
		return posicao;
	}

	/**
	 * Altera a posição da personagem
	 * @param posicao nova posição da personagem
	 */
	public void setPosicao(Ponto posicao) {
		this.posicao = posicao;
	}

	/**
	 * Devolve o simbolo da personagem
	 * @return simbolo da personagem
	 */
	public char getSimbolo() {
		return simbolo;
	}

	/**
	 * Altera o simbolo da personagem
	 * @param simbolo novo simbolo da personagem
	 */
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
}
