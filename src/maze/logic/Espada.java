package maze.logic;

public class Espada extends Personagem{
	public Espada(Ponto p, char simbolo){
		super(p,simbolo);
	}
	
	public void removeEspada(){
		this.setSimbolo(' ');
	}
}
