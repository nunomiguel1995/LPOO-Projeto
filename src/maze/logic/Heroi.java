package maze.logic;

import maze.logic.Jogo.Direcao;

public class Heroi extends Personagem{
	private boolean armado;
	
	public Heroi(){
		super(new Ponto(0,0),'H');
		this.armado = false;
	}
	
	public Heroi(Ponto p, char simbolo){
		super(p,simbolo);
		this.armado = false;
	}

	public boolean isArmado() {
		return this.armado;
	}

	public void setArmado(boolean estaArmado) {
		this.armado = estaArmado;
	}
	
	public void armaHeroi(Espada e,Labirinto l){
		if(e.getPosicao().equals(getPosicao()) && armado==false){
			setSimbolo('A');
			this.setArmado(true);
			e.removeEspada();
		}		
	}

	public int moveHeroi(Labirinto l, Direcao direcao){
		boolean parede = false;
		boolean saida = false;
		
		int heroiX = getPosicao().getX(), heroiY = getPosicao().getY();
		
		
		if(direcao == Direcao.CIMA){			
			heroiX -= 1;
			if(l.getConteudo(new Ponto(heroiX,heroiY)) == 'X')
				parede = true;
			else if(l.getConteudo(new Ponto(heroiX,heroiY)) == 'S')
				saida = true;
			else{
				l.setConteudo(new Ponto(heroiX + 1, heroiY), ' ');
			}
		}
		else if(direcao== Direcao.BAIXO){
			heroiX += 1;
			if(l.getConteudo(new Ponto(heroiX,heroiY)) == 'X')
				parede = true;
			else if(l.getConteudo(new Ponto(heroiX,heroiY)) == 'S')
				saida = true;
			else
				l.setConteudo(new Ponto(heroiX - 1, heroiY), ' ');
		}
		else if(direcao == Direcao.ESQUERDA){
			heroiY -= 1;
			if(l.getConteudo(new Ponto(heroiX, heroiY)) == 'X')
				parede = true;
			else if(l.getConteudo(new Ponto(heroiX, heroiY)) == 'S')
				saida = true;
			else
				l.setConteudo(new Ponto(heroiX, heroiY + 1), ' ');
		}
		else if(direcao == Direcao.DIREITA){
			heroiY += 1;
			if(l.getConteudo(new Ponto(heroiX, heroiY)) == 'X')
				parede = true;
			else if(l.getConteudo(new Ponto(heroiX, heroiY)) == 'S')
				saida = true;
			else
				l.setConteudo(new Ponto(heroiX, heroiY - 1), ' ');	
		}
		
		if(!parede && !saida){
			setPosicao(new Ponto(heroiX,heroiY));
			l.setPosHeroi(getPosicao());
		}
		
		if(saida){
			return 1;
		}
		
		return 0;
	}
	
	public int enfrentaDragao(Dragao dragao){
		Ponto posHeroi = getPosicao();
		Ponto posDragao = dragao.getPosicao();
		
		boolean combate = ( (posDragao.getX() == posHeroi.getX() + 1 && posDragao.getY() == posHeroi.getY())
				|| (posDragao.getX() == posHeroi.getX() - 1 && posDragao.getY() == posHeroi.getY()) 
				|| (posDragao.getY() == posHeroi.getY() + 1 && posDragao.getX() == posHeroi.getX())
				|| (posDragao.getY() == posHeroi.getY() - 1 && posDragao.getX() == posHeroi.getX()));
		
		if(combate){
			if(this.armado)
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
