package maze.logic;

public class Heroi extends Personagem {
	private boolean armado;

	public Heroi(int x,int y,char simb,boolean armado){
		super(x,y,simb);
		this.armado=armado;
	}

	public void setArmado(boolean armado) {
		this.armado = armado;
	}

	public boolean getArmado(){
		return armado;
	}
	
	public void setPosicaoHeroi(Ponto p){
		setPosicao(p);
	}
	
	public Ponto getPosicaoHeroi(){
		return getPosicao();
	}
	
	public void armaHeroi(Espada espada){
		Ponto posEspada = espada.getPosicao();
		Ponto posHeroi = getPosicao();
		
		if(posEspada.equals(posHeroi)){
			this.setSimbolo('A');
			this.setArmado(true);
		}		
	}
	
	public int moveHeroi(Labirinto labirinto, String movimento){
		boolean parede = false;
		boolean saida = false;
		
		int heroiX = getPosicao().getX(), heroiY = getPosicao().getY();
		
		switch(movimento){
		case "cima":			
			heroiX -= 1;
			if(labirinto.getSimbolo(new Ponto(heroiX,heroiY)) == 'X')
				parede=true;
			else if(labirinto.getSimbolo(new Ponto(heroiX,heroiY)) == 'S')
				saida=true;
			else{
				labirinto.setSimbolo(new Ponto(heroiX + 1, heroiY), ' ');
			}
			break;
		case "baixo":
			heroiX += 1;
			if(labirinto.getSimbolo(new Ponto(heroiX,heroiY)) == 'X')
				parede=true;
			else if(labirinto.getSimbolo(new Ponto(heroiX,heroiY)) == 'S')
				saida=true;
			else
				labirinto.setSimbolo(new Ponto(heroiX - 1, heroiY), ' ');
			break;
		case "esquerda":
			heroiY -= 1;
			if(labirinto.getSimbolo(new Ponto(heroiX, heroiY)) == 'X')
				parede=true;
			else if(labirinto.getSimbolo(new Ponto(heroiX, heroiY)) == 'S')
				saida=true;
			else
				labirinto.setSimbolo(new Ponto(heroiX, heroiY + 1), ' ');
			break;
		case "direita":
			heroiY += 1;
			if(labirinto.getSimbolo(new Ponto(heroiX, heroiY)) == 'X')
				parede=true;
			else if(labirinto.getSimbolo(new Ponto(heroiX, heroiY)) =='S')
				saida=true;
			else
				labirinto.setSimbolo(new Ponto(heroiX, heroiY - 1), ' ');
			break;	
		}
		
		if(!parede && !saida){
			setPosicao(new Ponto(heroiX,heroiY));
			labirinto.setPosHeroi(new Ponto(heroiX,heroiY));
		}
		
		if(saida)
			return 1;
		
		return 0;
	}
	
	public int enfrentaDragao(Dragao dragao){
		Ponto posHeroi = getPosicao();
		Ponto posDragao = dragao.getPosicao();
		
		boolean combate = ( (posDragao.getX() == posHeroi.getX() + 1 && posDragao.getY() == posHeroi.getY())
				|| (posDragao.getX() == posHeroi.getX() - 1 && posDragao.getX() == posHeroi.getX()) 
				|| (posDragao.getY() == posHeroi.getY() + 1 && posDragao.getX() == posHeroi.getX())
				|| (posDragao.getY() == posHeroi.getY() - 1 && posDragao.getX() == posHeroi.getX()));
		
		if(combate){
			if(this.getArmado())
				return 1;
			else if(dragao.getAdormecido())
				return 0;
			else
				return -1;
		}
		else
			return 0;
	}
}
