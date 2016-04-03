package maze.logic;

import maze.logic.Jogo.Direcao;

public class Heroi extends Personagem{
	private boolean armado;
	
	/**
	 * Constrói um herói no Ponto com o simbolo
	 * @param p Ponto
	 * @param simbolo simbolo do herói
	 */
	public Heroi(Ponto p, char simbolo){
		super(p,simbolo);
		this.armado = false;
	}

	/**
	 * Verifica se o herói se encontra armado
	 * @return true se o herói estiver armado
	 */
	public boolean isArmado() {
		return this.armado;
	}

	/**
	 * Altera o estado de armado do herói
	 * @param estaArmado novo estado de armado do herói
	 */
	public void setArmado(boolean estaArmado) {
		this.armado = estaArmado;
	}
	
	/**
	 * Verifica se é possível armar o herói e arma-o
	 * 
	 * @param e Espada a armar o herói
	 * @param l Labirinto
	 */
	public void armaHeroi(Espada e,Labirinto l){
		if(e.getPosicao().equals(getPosicao()) && armado==false){
			setSimbolo('A');
			this.setArmado(true);
			e.removeEspada();
		}		
	}

	/**
	 * Movimenta o herói no labirinto na direção definida pelo utilizador
	 * @param l Labirinto
	 * @param direcao direção a movimentar o herói
	 * @return 1 se a saída estiver aberta e 0 se estiver fechada
	 */
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
	
	/**
	 * Verifica , se for possível, o resultado do confronto entre o herói e o dragão
	 * 
	 * @param dragao Dragão a ser enfrentado
 	 * @return 1 se o herói derrotar o dragão, 0 se o herói não estiver armado e o dragão estiver a dormir
 	 * e -1 se o herói for derrotao pelo dragão
	 */
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
