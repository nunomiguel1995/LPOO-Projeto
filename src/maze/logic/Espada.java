package maze.logic;

public class Espada extends Personagem{
	public Espada(){
		super(new Ponto(0,0),'E');
	}
	
	public Espada(Ponto p, char simbolo){
		super(p,simbolo);
	}
}
