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
	
	public void moveDragao(Labirinto labirinto){
		int movimento = movimentoDragao.nextInt(6-1)+1;
		int dragaoX = getX();
		int dragaoY = getY();

		switch(movimento){
		case 1:
			break;
		case 2: //cima
			dragaoX -= 1;
			if(labirinto.getSimbolo(dragaoX,dragaoY)=='X')
				break;
			else{
				labirinto.setSimbolo(dragaoX+1, dragaoY, ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setX(dragaoX);
			}
			break;
		case 3: //baixo
			dragaoX += 1;
			if(labirinto.getSimbolo(dragaoX,dragaoY)=='X')
				break;
			else{
				labirinto.setSimbolo(dragaoX-1, dragaoY, ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setX(dragaoX);
			}
			break;
		case 4: // esquerda
			dragaoY -= 1;
			if(labirinto.getSimbolo(dragaoX,dragaoY)=='X')
				break;
			else{
				labirinto.setSimbolo(dragaoX, dragaoY+1, ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setY(dragaoY);
			}
			break;
		case 5:
			dragaoY += 1;
			if(labirinto.getSimbolo(dragaoX,dragaoY)=='X')
				break;
			else{
				labirinto.setSimbolo(dragaoX, dragaoY-1, ' ');
				labirinto.setDragao(new Dragao(dragaoX,dragaoY,'D',cimaEspada,adormecido));
				setY(dragaoY);
			}
			break;	
		}				
	}
	
	public void dragaoCimaEspada(Espada espada){
		int dragaoX=this.getX(), espadaX=espada.getX();
		int dragaoY=this.getY(), espadaY=espada.getY();
		boolean emCima=this.getCimaEspada();
		
		if(dragaoX==espadaX && dragaoY==espadaY && emCima==false ){
			this.setCimaEspada(true);
			this.setSimbolo('F');
		}
		
		if((dragaoX!=espadaX || dragaoY!=espadaY) && emCima==true ){
			this.setCimaEspada(false);
			this.setSimbolo('D');
		}
	}
	
	public void adormeceOuAcorda(){
		int i= adormece.nextInt(6-1)+1;
		switch(i){
		case 1:
		case 3:
		case 5:
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