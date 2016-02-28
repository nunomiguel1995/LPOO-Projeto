import java.util.Scanner;

public class Labirinto {
	private char layout[][]; //layout[linha][coluna]
	private Heroi heroi;
	private Espada espada;
	private Dragao dragao;

	public Labirinto(){
		layout = new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
		}; 
		setHeroi(new Heroi(1,1,'H',false));
		setEspada(new Espada(1,2,'E'));
		setDragao(new Dragao(3,1,'D'));
	}

	public char[][] getLayout() {
		return layout;
	}


	public void setLayout(char[][] layout) {
		this.layout = layout;
	}

	public Heroi getHeroi() {
		return heroi;
	}
	
	public Espada getEspada(){
		return espada;
	}
	
	public Dragao getDragao(){
		return dragao;
	}

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
		layout[heroi.getX()][heroi.getY()]= heroi.getSimbolo();
	}
	
	public void setEspada(Espada espada){
		this.espada=espada;
		layout[espada.getX()][espada.getY()]=espada.getSimbolo();
	}
	
	public void setDragao(Dragao dragao){
		this.dragao=dragao;
		layout[dragao.getX()][dragao.getY()]=dragao.getSimbolo();
	}

	public boolean armaHeroi(int x,int y){
		int espadaX= espada.getX();
		int espadaY= espada.getY();
		if(x==espadaX && y==espadaY)
			return true;
		else 
			return false;		
	}
	
	//Verifica se o heroi combate o dragão(está em posição adjacente)
	//Caso combata: ganha se estiver armado, perde se não
	public int enfrentaDragao(){
		int heroiX=heroi.getX(), dragaoX=dragao.getX();
		int heroiY=heroi.getY(), dragaoY=dragao.getY();
		boolean combate= ( (dragaoX==heroiX+1 && dragaoY==heroiY) || (dragaoX== heroiX-1 && dragaoY==heroiY) || (dragaoY == heroiY+1 && dragaoX==heroiX)|| (dragaoY== heroiY-1 && dragaoX==heroiX));
		
		if(combate){
			if(heroi.getArmado()){
				layout[dragaoX][dragaoY]=' ';
				dragao.setVivo(false);
				return 1;
			}
			else
				return -1;
		}
		else
			return 0;
	}
	
	public int moveHeroi(){
		Scanner s = new Scanner(System.in);
		String movimento;
		int heroix= heroi.getX();
		int heroiy=heroi.getY();
		
		System.out.println("Mover herói: ");
		movimento = s.nextLine();
		
		boolean parede= false;
		boolean saida=false;
		switch(movimento){
		case "cima":
			heroix= heroix-1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else if(layout[heroix][heroiy]=='S')
				saida=true;
			else
				layout[heroix+1][heroiy]=' ';
			break;
		case "baixo":
			heroix= heroix+1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else if(layout[heroix][heroiy]=='S')
				saida=true;
			else
				layout[heroix-1][heroiy]=' ';
			break;
		case "esquerda":
			heroiy=heroiy-1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else if(layout[heroix][heroiy]=='S')
				saida=true;
			else
				layout[heroix][heroiy+1]=' ';
			break;
		case "direita":
			heroiy=heroiy+1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else if(layout[heroix][heroiy]=='S')
				saida=true;
			else
				layout[heroix][heroiy-1]=' ';
			break;	
		case "sair":
			return -1;
		}
		
		if(saida){
			if(!dragao.getVivo()){
				System.out.println("Parabens, ganhou o jogo!");
				return 1;
			}
			else
				System.out.println("Antes de sair tem de derrotar o Dragao");
		}
		
		if(!parede && !saida){
			char simb=heroi.getSimbolo();
			if(armaHeroi(heroix,heroiy))
				setHeroi(new Heroi(heroix,heroiy,'A',true));
			else
				setHeroi(new Heroi(heroix,heroiy,simb,true));		
		}
		
		if(dragao.getVivo()){
			int combate= enfrentaDragao();
			switch(combate){
			case 1:
				System.out.println("Derrotou o Dragao!");
				break;
			case -1:
				System.out.println("Game Over! Foi derrotado pelo Dragao!");
				return -1;
			}
		}
		
		return 0;
	}
	
	public void print(){
		for(int i=0;i<layout.length;i++){
			for(int j=0; j< layout[0].length; j++){
				System.out.print(layout[i][j]+ " ");
			}
			System.out.println();
		}
	}				
}
