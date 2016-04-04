package maze.logic;

public class Espada extends Personagem{
	/**
	 * Cria uma espada no Ponto p com um simbolo
	 * @param p posi��o da espada
	 * @param simbolo simbolo da espada
	 */
	public Espada(Ponto p, char simbolo){
		super(p,simbolo);
	}
	
	/**
	 * Altera o simbolo da espada para um espa�o em branco,
	 * que representa a sua remo��o do labirinto.
	 */
	public void removeEspada(){
		this.setSimbolo(' ');
	}
}
