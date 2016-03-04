package maze.logic;

import java.util.Random;

public class Dragao extends Personagem {
	private boolean vivo;
	private boolean cimaEspada;
	private boolean adormecido;
	
	private static Random movimentoDragao = new Random(); 
	private static Random adormece = new Random();
	
	public Dragao(int x, int y, char simbolo,boolean cimaEspada,boolean adormecido){
		super(x,y,simbolo);
		vivo=true;
		this.cimaEspada=cimaEspada;
		this.adormecido=adormecido;
	}
	
	public boolean getVivo(){
		return vivo;
	}

	public void setVivo(boolean vivo){
		this.vivo=vivo;
	}
	
	public boolean getCimaEspada(){
		return cimaEspada;
	}
	
	public void setCimaEspada(boolean cimaEspada){
		this.cimaEspada=cimaEspada;
	}
	
	public boolean getAdormecido(){
		return adormecido;
	}
	
	public Ponto getPosicaoDragao(){
		return getPosicao();
	}
	
	public void moveDragao(Labirinto labirinto){
		int movimento = movimentoDragao.nextInt(6-1)+1;

		int dragaoX = getPosicao().getX(), dragaoY = getPosicao().getY();

		switch(movimento){
		case 1:
			break;
		case 2: //cima
			dragaoX -= 1;
			if(labirinto.getSimbolo(new Ponto(dragaoX, dragaoY)) == 'X')
				break;
			else{
				labirinto.setSimbolo(new Ponto(dragaoX + 1, dragaoY), ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setPosicao(new Ponto(dragaoX, dragaoY));
				labirinto.setPosDragao(new Ponto(dragaoX,dragaoY));
			}
			break;
		case 3: //baixo
			dragaoX += 1;
			if(labirinto.getSimbolo(new Ponto(dragaoX,dragaoY)) == 'X')
				break;
			else{
				labirinto.setSimbolo(new Ponto(dragaoX - 1, dragaoY), ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setPosicao(new Ponto(dragaoX, dragaoY));
				labirinto.setPosDragao(new Ponto(dragaoX,dragaoY));
			}
			break;
		case 4: // esquerda
			dragaoY -= 1;
			if(labirinto.getSimbolo(new Ponto(dragaoX,dragaoY)) == 'X')
				break;
			else{
				labirinto.setSimbolo(new Ponto(dragaoX, dragaoY + 1), ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setPosicao(new Ponto(dragaoX, dragaoY));
				labirinto.setPosDragao(new Ponto(dragaoX,dragaoY));
			}
			break;
		case 5:
			dragaoY += 1;
			if(labirinto.getSimbolo(new Ponto(dragaoX,dragaoY)) == 'X')
				break;
			else{
				labirinto.setSimbolo(new Ponto(dragaoX, dragaoY - 1), ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setPosicao(new Ponto(dragaoX, dragaoY));
				labirinto.setPosDragao(new Ponto(dragaoX,dragaoY));
			}
			break;	
		}				
	}
	
	public void dragaoCimaEspada(Espada espada){
		Ponto posEspada = espada.getPosicaoEspada();
		Ponto posDragao = getPosicao();

		boolean emCima=this.getCimaEspada();
		
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
		int i= adormece.nextInt(6-1)+1;
		switch(i){
		case 1:	case 3:	case 5:
			break;
		case 2:
		case 4:
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