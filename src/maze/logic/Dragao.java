package maze.logic;

import java.util.Random;

public class Dragao extends Personagem{
	private static Random movimentoDragao = new Random(); 
	private static Random adormece = new Random();
	
	private boolean vivo;
	private boolean cimaEspada;
	private boolean adormecido;
	private int movimento;

	/**
	 * Constr�i um drag�o no Ponto p com o simbolo
	 * @param p Ponto
	 * @param simbolo simbolo do drag�o
	 */
	public Dragao(Ponto p, char simbolo){
		super(p,simbolo);
		this.vivo = true;
		this.cimaEspada = false;
	}
	
	/**
	 * Verifica o estado de vida do drag�o
	 * @return true se estiver vivo
	 */
	public boolean isVivo(){
		return this.vivo;
	}
	
	/**
	 * Mata o drag�o
	 */
	public void setMorto(){
		this.vivo = false;
	}
	
	/**
	 * Devolve o estado de cima da espada do drag�o
	 * @return true se o drag�o estiver em cima da espada
	 */
	public boolean getCimaEspada(){
		return this.cimaEspada;
	}
	
	/**
	 * Altera o estado de cima da espada do drag�o
	 * @param cimaEspada novo estado de cima da espada
	 */
	public void setCimaEspada(boolean cimaEspada){
		this.cimaEspada = cimaEspada;
	}
	
	/**
	 * Devolve o estado de adormecido do drag�o
	 * @return true se o drag�o estiver adormecido
	 */
	public boolean getAdormecido(){
		return adormecido;
	}
	
	/**
	 * Retorna o n�mero correspondente ao movimento do drag�o
	 * @return n�mero correspondente ao movimento do drag�o
	 */
	public int getMovimento(){
		return movimento;
	}
	
	/**
	 * Move o drag�o aleatoriamente no labirinto
	 * 
	 * @param labirinto labirinto do jogo
	 */
	public void moveDragao(Labirinto labirinto){
		movimento = movimentoDragao.nextInt(6-1)+1;

		int dragaoX = getPosicao().getX(), dragaoY = getPosicao().getY();
		
		switch(movimento){
		case 1:
			break;
		case 2: //cima
			dragaoX -= 1;
			if(labirinto.getConteudo(new Ponto(dragaoX, dragaoY)) == 'X')
				break;
			else{
				labirinto.setConteudo(new Ponto(dragaoX + 1, dragaoY), ' ');
				setPosicao(new Ponto(dragaoX, dragaoY));
			}
			break;
		case 3: //baixo
			dragaoX += 1;
			if(labirinto.getConteudo(new Ponto(dragaoX,dragaoY)) == 'X')
				break;
			else{
				labirinto.setConteudo(new Ponto(dragaoX - 1, dragaoY), ' ');
				setPosicao(new Ponto(dragaoX, dragaoY));
			}
			break;
		case 4: // esquerda
			dragaoY -= 1;
			if(labirinto.getConteudo(new Ponto(dragaoX,dragaoY)) == 'X')
				break;
			else{
				labirinto.setConteudo(new Ponto(dragaoX, dragaoY + 1), ' ');
				setPosicao(new Ponto(dragaoX, dragaoY));
			}
			break;
		case 5:
			dragaoY += 1;
			if(labirinto.getConteudo(new Ponto(dragaoX,dragaoY)) == 'X')
				break;
			else{
				labirinto.setConteudo(new Ponto(dragaoX, dragaoY - 1), ' ');
				setPosicao(new Ponto(dragaoX, dragaoY));
			}
			break;
		default:
			break;
		}				
	}
	
	/**
	 * Verifica se o drag�o se encontra em cima da espada, e altera o seu simbolo caso
	 * seja verdade
	 * @param espada espada do labirinto
	 */
	public void dragaoCimaEspada(Espada espada){
		Ponto posEspada = espada.getPosicao();
		Ponto posDragao = getPosicao();

		boolean emCima = this.getCimaEspada();
		
		if(posEspada.equals(posDragao) && emCima==false ){
			this.setCimaEspada(true);
			this.setSimbolo('F');
		}
		
		if((posDragao.getX() != posEspada.getX() || posDragao.getY() != posEspada.getY()) && emCima==true ){
			this.setCimaEspada(false);
			this.setSimbolo('D');
		}
	}
	
	/**
	 * Altera o estado de adormecido ou acordado do drag�o aleatoriamente
	 */
	public void adormeceOuAcorda(){
		int i = adormece.nextInt(6-1)+1;
		if((i % 2) == 0){
			if(adormecido){
				adormecido=false;
				this.setSimbolo('D');
			}
			else{
				adormecido=true;
				this.setSimbolo('d');
			}
		}
	}
}
