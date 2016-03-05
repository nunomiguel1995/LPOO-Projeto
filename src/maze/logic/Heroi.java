package maze.logic;

public class Heroi extends Personagem {
	private boolean armado;

	public Heroi(int x,int y,char simb){
		super(x,y,simb);
		this.armado = false;
	}

	public void setArmado(boolean armado) {
		this.armado = armado;
	}

	public boolean getArmado(){
		return armado;
	}
	
	/**
	 * Arma o heroi com a espada 
	 * @param espada Espada a armar o heroi
	 */
	public void armaHeroi(Espada espada){
		if(espada.getPosicao().equals(getPosicao())){
			setSimbolo('A');
			this.setArmado(true);
		}		
	}
	
	/**
	 * Forca o movimento do heroi.
	 * @param labirinto Labirinto no qual se encontra o heroi
	 * @param movimento Direccao do movimento
	 * @return 1 se o heroi puder sair do labirinto e 0 caso contrario
	 */
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
			labirinto.setPosHeroi(getPosicao());
		}
		
		if(saida)
			return 1;
		
		return 0;
	}
	
	/**
	 * Força o Heroi a enfrentar o dragao
	 * @param dragao Dragao a enfrentar
	 * @return 0 se nao houver combate, 1 se ganhar e -1 se perder
	 */
	public int enfrentaDragao(Dragao dragao){
		Ponto posHeroi = getPosicao();
		Ponto posDragao = dragao.getPosicao();
		
		boolean combate = ( (posDragao.getX() == posHeroi.getX() + 1 && posDragao.getY() == posHeroi.getY())
				|| (posDragao.getX() == posHeroi.getX() - 1 && posDragao.getX() == posHeroi.getX()) 
				|| (posDragao.getY() == posHeroi.getY() + 1 && posDragao.getX() == posHeroi.getX())
				|| (posDragao.getY() == posHeroi.getY() - 1 && posDragao.getX() == posHeroi.getX()));
		
		if(combate){
			if(armado)
				return 1;
			else if(dragao.getAdormecido())
				return 0;
			else
				return -1;
		}else{
			return 0;
		}
	}
}
