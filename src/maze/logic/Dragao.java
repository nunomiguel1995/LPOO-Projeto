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
	 * Constrói um dragão no Ponto p com o simbolo
	 * @param p Ponto
	 * @param simbolo simbolo do dragão
	 */
	public Dragao(Ponto p, char simbolo){
		super(p,simbolo);
		this.vivo = true;
		this.cimaEspada = false;
	}
	
	/**
	 * Verifica o estado de vida do dragão
	 * @return true se estiver vivo
	 */
	public boolean isVivo(){
		return this.vivo;
	}
	
	/**
	 * Mata o dragão
	 */
	public void setMorto(){
		this.vivo = false;
	}
	
	/**
	 * Devolve o estado de cima da espada do dragão
	 * @return true se o dragão estiver em cima da espada
	 */
	public boolean getCimaEspada(){
		return this.cimaEspada;
	}
	
	/**
	 * Altera o estado de cima da espada do dragão
	 * @param cimaEspada novo estado de cima da espada
	 */
	public void setCimaEspada(boolean cimaEspada){
		this.cimaEspada = cimaEspada;
	}
	
	/**
	 * Devolve o estado de adormecido do dragão
	 * @return true se o dragão estiver adormecido
	 */
	public boolean getAdormecido(){
		return adormecido;
	}
	
	/**
	 * Retorna o número correspondente ao movimento do dragão
	 * @return número correspondente ao movimento do dragão
	 */
	public int getMovimento(){
		return movimento;
	}
	
	/**
	 * Move o dragão aleatoriamente no labirinto
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
	 * Verifica se o dragão se encontra em cima da espada, e altera o seu simbolo caso
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
	 * Altera o estado de adormecido ou acordado do dragão aleatoriamente
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
