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
	
	/**
	 * Cria uma saída numa posição aleatória
	 * @param r instancia Random
	 */
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
	
	/**
	 * Coloca uma guia na célula adjacente à saída
	 */
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
	
	/**
	 * Altera o estado de uma célula para visitada
	 */
	private void marcarGuiaVisitado(){
		celulasVisitadas[guia.getY()][guia.getX()]=true;
	}
	
	/**
	 * Adiciona a uma stack a posição da célula do caminho do labirinto
	 */
	private void adicionarGuiaPath(){
		path.push(new Ponto(guia.getX(),guia.getY()));
	}
	
	/**
	 * Verifica se a célula vizinha já foi visitada.
	 * @param direcao direção a movimentar a guia
	 * @return true se a célula já tiver sido visitada
	 */
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
	
	/**
	 * Movimenta a guia numa direção aleatória
	 * @param direcao direção a movimentar a guia
	 * @return false se a célula já tiver sido visitada
	 */
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
	
	/**
	 * Atualiza as coordenadas da guia dependendo da direção para a qual se moveu
	 * @param direcao direção a mover a guia
	 */
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
	
	/**
	 * Verifica se é possível movimentar a guia
	 * @return true se é possível
	 */
	private boolean guiaPodeMexer(){
		boolean b=false;
		
		for(int i=0; i<4; i++){
			if(movimentaGuia(i))
				b=true;
		}
		return b;
	}
	
	/**
	 * Verifica se a posição inicial do herói é adjacente ao dragão
	 * 
	 * @param heroiX Abcissa da posição do herói
	 * @param heroiY Ordenada da posição do herói
	 * @return true se o herói estiver adjacente ao dragão
	 */
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
	
	/**
	 * Constrói um labirinto aleatório a partir de uma largura, altura e nº de dragões
	 * @param largura largura do labirinto
	 * @param altura altura do labirinto
	 * @param nDragoes nº de dragões do labirinto
	 */
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
	
	/**
	 * Devolve o mapa do labirinto
	 * @return mapa do labirinto
	 */
	public char[][] getLab() {
		return lab;
	}

	/**
	 * Devolve a espada do labirinto
	 * @return espada do labirinto
	 */
	public Espada getEspada() {
		return espada;
	}

	/**
	 * Devolve os dragões do labirinto sob forma de array
	 * @return dragões do labirinto
	 */
	public Dragao[] getDragao() {
		return dragao;
	}

	/**
	 * Devolve o herói do labirinto
	 * @return herói do labirinto
	 */
	public Heroi getHeroi() {
		return heroi;
	}

}
