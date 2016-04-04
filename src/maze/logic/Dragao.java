package maze.logic;

import java.util.Random;

public class Dragao extends Personagem{
	private static Random movimentoDragao = new Random(); 
	private static Random adormece = new Random();
	
	private boolean vivo;
	private boolean cimaEspada;
	private boolean adormecido;
	private int movimento;

	public Dragao(Ponto p, char simbolo){
		super(p,simbolo);
		this.vivo = true;
		this.cimaEspada = false;
		this.adormecido=false;
	}
	
	public boolean isVivo(){
		return this.vivo;
	}
	
	public void setMorto(){
		this.vivo = false;
	}
	
	public boolean getCimaEspada(){
		return this.cimaEspada;
	}
	
	public void setCimaEspada(boolean cimaEspada){
		this.cimaEspada = cimaEspada;
	}
	
	public boolean getAdormecido(){
		return adormecido;
	}
	
	public int getMovimento(){
		return movimento;
	}
	
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
