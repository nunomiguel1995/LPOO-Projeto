package maze.logic;

import java.util.Scanner;

public class Jogo {
	private Labirinto labirinto;

	public Jogo(boolean dragaoParado, boolean dragaoAdormece){
		this.labirinto = new Labirinto(dragaoParado, dragaoAdormece);
	}

	public Jogo(char[][] layout, boolean dragaoParado, boolean dragaoAdormece){
		this.labirinto = new Labirinto(layout,dragaoParado, dragaoAdormece);
	}
	
	public Labirinto getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	public Heroi getHeroi() {
		return labirinto.getHeroi();
	}

	public void setHeroi(Heroi heroi) {
		this.labirinto.setHeroi(heroi);
	}

	public Espada getEspada() {
		return labirinto.getEspada();
	}

	public void setEspada(Espada espada) {
		this.labirinto.setEspada(espada);
	}

	public Dragao getDragao() {
		return labirinto.getDragao();
	}

	public void setDragao(Dragao dragao) {
		this.labirinto.setDragao(dragao);
	}
}
