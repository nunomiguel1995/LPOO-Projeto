package maze.logic;

import java.util.Random;
import java.util.Stack;

public class LabirintoRandom {
	private char lab[][];
	private boolean celulasVisitadas[][];
	private Stack<Ponto> path;
	private Ponto guia;
	private Ponto saida;
	private Espada espada;
	private Dragao dragao[];
	private Heroi heroi;
	
	private void saidaRandom(Random r){
		saida=new Ponto(0,0);
		
		//criar a distancia da saida em relação ao canto 
		// De forma a que a saida nunca coincida com este
		int distY, distX;
		do{
			distY=r.nextInt(lab.length-2)+1;
		}while(distY %2 == 0);
		do{
			distX=r.nextInt(lab[0].length-2)+1;
		}while(distX %2 == 0);
		
		//Escolher o lado em que a saida fica
		switch(r.nextInt(4)){
		case 0: //topo
			saida.setX(distX);
			break;
		case 1: //lado direito
			saida.setX(lab[0].length - 1);
			saida.setY(distY);
			break;
		case 2: //baixo
			saida.setX(distX);
			saida.setY(lab.length-1);
			break;
		case 3:
			saida.setX(distX);
			break;
		default:
			saida.setX(1);
			break;
		}
		lab[saida.getY()][saida.getX()]='S';
	}
	
	private void inicializarGuia(){
		int guiaX, guiaY;
		Ponto p= new Ponto(saida.getX(),saida.getY());
		
		if(saida.getX()==0)
			p.setX(p.getX()+1);
		else if(saida.getX() == lab.length -1)
			p.setX(p.getX()-1);
		else if(saida.getY() == 0)
			p.setY(p.getY()+1);
		else
			p.setY(p.getY()-1);
		
		guiaX= (p.getX()-1)/2;
		guiaY= (p.getY()-1)/2;
		
		guia= new Ponto(guiaX,guiaY);
	}
	
	private void marcarGuiaVisitado(){
		celulasVisitadas[guia.getY()][guia.getX()]=true;
	}
	
	private void adicionarGuiaPath(){
		path.push(new Ponto(guia.getX(),guia.getY()));
	}
	
	//Baixo(0), Esquerda(1), Direita(2), Cima(3)
	private boolean celulaVizinhaVisitada(int direcao){
		switch(direcao){
		case 0:
			return celulasVisitadas[guia.getY()+1][guia.getX()];
		case 1:
			return celulasVisitadas[guia.getY()][guia.getX()-1];
		case 2:
			return celulasVisitadas[guia.getY()][guia.getX()+1];
		case 3:
			return celulasVisitadas[guia.getY()-1][guia.getX()];
		default:
			break;
		}
		return false;
	}
	
	private boolean movimentaGuia(int direcao){
		switch(direcao){
		case 0:
			if(guia.getY()+1 >= (lab.length -1)/2)
				return false;
			break;
		case 1:
			if(guia.getX()-1 <0)
				return false;
			break;
		case 2:
			if(guia.getX()+1 >= (lab[0].length -1)/2)
				return false;
			break;
		case 3:
			if(guia.getY() -1 < 0)
				return false;
			break;
		default:
			break;
		}

		return !celulaVizinhaVisitada(direcao);
	}
	
	private void atualizaGuia(int direcao){
		switch(direcao){
		case 0:
			guia.setY(guia.getY()+1);
			break;
		case 1:
			guia.setX(guia.getX()-1);
			break;
		case 2:
			guia.setX(guia.getX()+1);
			break;
		case 3:
			guia.setY(guia.getY()-1);
			break;
		default:
			break;
		}
	}
	
	private boolean guiaPodeMexer(){
		boolean b=false;
		
		for(int i=0; i<4; i++){
			if(movimentaGuia(i))
				b=true;
		}
		return b;
	}
	
	private boolean heroiAdjacenteDragao(int heroiX, int heroiY){
		boolean adj=false;
		for(int i=0; i < dragao.length;i++){
			Ponto posDragao = dragao[i].getPosicao();		
			if((posDragao.getX() == heroiX + 1 && posDragao.getY() == heroiY)
					|| (posDragao.getX() == heroiX - 1 && posDragao.getY() == heroiY) 
					|| (posDragao.getY() == heroiY + 1 && posDragao.getX() == heroiX)
					|| (posDragao.getY() == heroiY - 1 && posDragao.getX() == heroiX)){
				adj=true;
				break;
			}
		}
		return adj;
	}
	public LabirintoRandom(int largura, int altura, int nDragoes){
		Random r= new Random();
		lab= new char[altura][largura];
		dragao= new Dragao[nDragoes];
		
		celulasVisitadas= new boolean[altura-2][largura-2];
		
		for(int i=0;i < altura; i++){
			for(int j=0; j< largura; j++){
				if(i % 2 !=0 && j%2 != 0){
					lab[i][j]=' ';
				}
				else
					lab[i][j]='X';
			}
		}
		
		for(int i=0; i< celulasVisitadas.length;i++){
			for(int j=0; j< celulasVisitadas[i].length;j++){
				celulasVisitadas[i][j]=false;
			}
		}
		
		saidaRandom(r);
		inicializarGuia();
		marcarGuiaVisitado();
		path= new Stack<Ponto>();
		adicionarGuiaPath();
		
		while(!path.empty()){
			while(!guiaPodeMexer()){
				path.pop();
				if(path.empty())
					break;
				else
					guia= path.peek();				
			}
			
			if(path.empty())
				break;
			
			int direcao;
			do{
				direcao= r.nextInt(4);
			}while(!movimentaGuia(direcao));
			
			switch(direcao){
			case 0: //baixo
				lab[(guia.getY()+1)*2][guia.getX()*2 + 1]= ' ';
				break;
			case 1: //esquerda
				lab[guia.getY()*2 + 1][guia.getX()*2]=' ';
				break;
			case 2:
				lab[guia.getY()*2 + 1][(guia.getX()+1)*2]=' ';
				break;
			case 3:
				lab[guia.getY()*2][guia.getX()*2 +1]= ' ';
				break;
			default:
				break;
			}
			
			atualizaGuia(direcao);
			marcarGuiaVisitado();
			adicionarGuiaPath();
		}
		
		 //Cria Dragao Aleatório
		for(int i=0;i< nDragoes;i++){
			boolean dragaoSet=false;
			int dragaox, dragaoy;
			
			do{
				dragaox=r.nextInt(altura-2)+1;
				dragaoy=r.nextInt(largura-2)+1;
				if(lab[dragaox][dragaoy]== ' ')
					dragaoSet=true;
			}while(!dragaoSet);
			
			dragao[i]= new Dragao(new Ponto(dragaox,dragaoy),'D');
			lab[dragaox][dragaoy]=dragao[i].getSimbolo();
		}
		
		//Cria Espada Aleatoria
		boolean espadaSet=false;
		int espadaX, espadaY;
		do{
			espadaX=r.nextInt(altura-2)+1;
			espadaY=r.nextInt(largura-2)+1;
			if(lab[espadaX][espadaY]==' ')
				espadaSet=true;
		}while(!espadaSet);
		espada= new Espada(new Ponto(espadaX,espadaY),'E');
		lab[espadaX][espadaY]=espada.getSimbolo();
		
		//Cria Heroi Aleatorio
		boolean heroiSet=false;
		int heroiX, heroiY;
		do{
			heroiX=r.nextInt(altura-2)+1;
			heroiY=r.nextInt(largura-2)+1;
			if(lab[heroiX][heroiY]==' ' && !heroiAdjacenteDragao(heroiX,heroiY))
				heroiSet=true;
		}while(!heroiSet);
		heroi=new Heroi(new Ponto(heroiX,heroiY),'H');
		lab[heroiX][heroiY]=heroi.getSimbolo();
	}
	
	public char[][] getLab() {
		return lab;
	}

	public Espada getEspada() {
		return espada;
	}

	public Dragao[] getDragao() {
		return dragao;
	}

	public Heroi getHeroi() {
		return heroi;
	}

}
