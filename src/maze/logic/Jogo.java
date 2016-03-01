package maze.logic;

import java.util.Scanner;

public class Jogo {
	private Labirinto labirinto;
	private Heroi heroi;
	private Espada espada;
	private Dragao dragao;
	
	public Jogo(){
		labirinto = new Labirinto();
		heroi = new Heroi(1,1,'H',false);
		espada= new Espada(1,2,'E');
		dragao= new Dragao(3,1,'D');
		
		labirinto.setHeroi(heroi);
		labirinto.setEspada(espada);
		labirinto.setDragao(dragao);		
	}
	
	public Labirinto getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	public Heroi getHeroi() {
		return heroi;
	}

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	public Espada getEspada() {
		return espada;
	}

	public void setEspada(Espada espada) {
		this.espada = espada;
	}

	public Dragao getDragao() {
		return dragao;
	}

	public void setDragao(Dragao dragao) {
		this.dragao = dragao;
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
					labirinto.setSimbolo(dragaoX,dragaoY,' ');
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
				boolean arm= heroi.getArmado();
				if(armaHeroi(heroix,heroiy)){
					labirinto.setHeroi(new Heroi(heroix,heroiy,'A',true));
					heroi= new Heroi(heroix,heroiy,'A',true);
				}				
				else{
					labirinto.setHeroi(new Heroi(heroix,heroiy,simb,arm));	
					heroi= new Heroi(heroix,heroiy,simb,arm);
				}
					
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
}
