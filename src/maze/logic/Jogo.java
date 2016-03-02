package maze.logic;

import java.util.Scanner;

public class Jogo {
	private Labirinto labirinto;
	private Heroi heroi;
	private Espada espada;
	private Dragao dragao;
	
	private boolean dragaoParado;
	private boolean dragaoAdormece;

	public Jogo(boolean dragaoParado, boolean dragaoAdormece){
		labirinto = new Labirinto();
		
		heroi = new Heroi(1,1,'H',false);
		espada= new Espada(1,2,'E');
		dragao= new Dragao(3,1,'D',false,false);
	
		labirinto.setHeroi(heroi);
		labirinto.setEspada(espada);
		labirinto.setDragao(dragao);		
		
		this.dragaoParado=dragaoParado;
		this.dragaoAdormece=dragaoAdormece;
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
	
	public void atualizaLabirinto(){
		if(dragao.getVivo())
			labirinto.setDragao(dragao);
		if(!dragao.getCimaEspada() && !heroi.getArmado())
			labirinto.setEspada(espada);
		labirinto.setHeroi(heroi);
	}

	

	//Verifica se o heroi combate o dragão(está em posição adjacente)
	//Caso combata: ganha se estiver armado, perde se não


		public int jogada(){
			Scanner s = new Scanner(System.in);
			String movimento;

			System.out.println("Mover herói: ");
			movimento = s.nextLine();
			
			if(heroi.moveHeroi(labirinto, movimento)==1){
				if(!dragao.getVivo()){
					System.out.println("Parabens, ganhou o jogo!");
					return 1;
				}
				else
					System.out.println("Antes de sair tem de derrotar o Dragao");
			}

			heroi.armaHeroi(espada);
			
			if(dragao.getVivo()){
				if(dragaoAdormece)
					dragao.adormeceOuAcorda();
				
				if(!dragao.getAdormecido() && !dragaoParado){
					dragao.moveDragao(labirinto);
					dragao.dragaoCimaEspada(espada);
				}
				
				int combate= heroi.enfrentaDragao(dragao);
				switch(combate){
				case 1:
					dragao.setSimbolo(' ');
					labirinto.setDragao(dragao);
					dragao.setVivo(false);
					System.out.println("Derrotou o Dragao!");
					break;
				case -1:
					System.out.println("Game Over! Foi derrotado pelo Dragao!");
					return -1;
				}
			}
			atualizaLabirinto();
			return 0;
		}
}
