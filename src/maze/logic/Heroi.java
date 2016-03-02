package maze.logic;

public class Heroi extends Personagem {
	private boolean armado;

	public Heroi(int x,int y,char simb,boolean armado){
		super(x,y,simb);
		this.armado=armado;
	}

	public boolean isArmado() {
		return armado;
	}

	public void setArmado(boolean armado) {
		this.armado = armado;
	}

	public boolean getArmado(){
		return armado;
	}
	
	public void armaHeroi(Espada espada){
		int espadaX= espada.getX(),heroix=this.getX();
		int espadaY= espada.getY(),heroiy=this.getY();
		if(heroix==espadaX && heroiy==espadaY){
			this.setSimbolo('A');
			this.setArmado(true);
		}		
	}
	
	public int moveHeroi(Labirinto labirinto, String movimento){
		boolean parede= false;
		boolean saida=false;
		int heroix=this.getX();
		int heroiy=this.getY();
		
		switch(movimento){
		case "cima":			
			heroix= heroix-1;
			if(labirinto.getSimbolo(heroix,heroiy)=='X')
				parede=true;
			else if(labirinto.getSimbolo(heroix,heroiy)=='S')
				saida=true;
			else
				labirinto.setSimbolo(heroix+1, heroiy, ' ');
			break;
		case "baixo":
			heroix= heroix+1;
			if(labirinto.getSimbolo(heroix,heroiy)=='X')
				parede=true;
			else if(labirinto.getSimbolo(heroix,heroiy)=='S')
				saida=true;
			else
				labirinto.setSimbolo(heroix-1, heroiy, ' ');
			break;
		case "esquerda":
			heroiy=heroiy-1;
			if(labirinto.getSimbolo(heroix,heroiy)=='X')
				parede=true;
			else if(labirinto.getSimbolo(heroix,heroiy)=='S')
				saida=true;
			else
				labirinto.setSimbolo(heroix, heroiy+1, ' ');
			break;
		case "direita":
			heroiy=heroiy+1;
			if(labirinto.getSimbolo(heroix,heroiy)=='X')
				parede=true;
			else if(labirinto.getSimbolo(heroix,heroiy)=='S')
				saida=true;
			else
				labirinto.setSimbolo(heroix, heroiy-1, ' ');
			break;	
		}
		
		if(!parede && !saida){
			this.setX(heroix);
			this.setY(heroiy);
		}
		if(saida)
			return 1;
		
		return 0;
	}
	
	public int enfrentaDragao(Dragao dragao){
		int heroiX=this.getX(), dragaoX=dragao.getX();
		int heroiY=this.getY(), dragaoY=dragao.getY();
		boolean combate= ( (dragaoX==heroiX+1 && dragaoY==heroiY) || (dragaoX== heroiX-1 && dragaoY==heroiY) || (dragaoY == heroiY+1 && dragaoX==heroiX)|| (dragaoY== heroiY-1 && dragaoX==heroiX));
		
		if(combate){
			if(this.getArmado())
				return 1;
			else if(dragao.getAdormecido())
				return 0;
			else
				return -1;
		}
		else
			return 0;
	}
}
