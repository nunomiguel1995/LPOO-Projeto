package maze.logic;

public class Espada extends Personagem{
	/**
	 * Cria uma espada no Ponto p com um simbolo
	 * @param p posição da espada
	 * @param simbolo simbolo da espada
	 */
	public Espada(Ponto p, char simbolo){
		super(p,simbolo);
	}
	
	/**
	 * Altera o simbolo da espada para um espaço em branco,
	 * que representa a sua remoção do labirinto.
	 */
	public void removeEspada(){
		this.setSimbolo(' ');
	}
}
