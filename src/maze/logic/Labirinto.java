package maze.logic;

public class Labirinto {
	private char[][] mapa;
	
	private Heroi heroi;
	private Espada espada;
	private Dragao dragao;
	
	public Labirinto(){
		this.mapa = new char[][] {{'X'}};
	}
	
	public Labirinto(char[][] mapa){
		this.mapa = mapa;
	}
	
	public Heroi getHeroi() {
		return this.heroi;
	}

	public void setHeroi(Heroi heroi) {
		this.mapa[heroi.getPosicao().getX()][heroi.getPosicao().getY()] = heroi.getSimbolo();
		this.heroi = heroi;
	}

	public Espada getEspada() {
		return this.espada;
	}

	public void setEspada(Espada espada) {		
		this.mapa[espada.getPosicao().getX()][espada.getPosicao().getY()] = espada.getSimbolo();
		this.espada = espada;
	}

	public Dragao getDragao() {
		return this.dragao;
	}

	public void setDragao(Dragao dragao) {
		this.mapa[dragao.getPosicao().getX()][dragao.getPosicao().getY()] = dragao.getSimbolo();
		this.dragao = dragao;
	}

	public char[][] getMapa(){
		return this.mapa;
	}
	
	public void setMapa(char[][] mapa){
		this.mapa = mapa;
	}
	
	public char getConteudo(Ponto p){
		return this.mapa[p.getX()][p.getY()];
	}
	
	public void setConteudo(Ponto p, char simbolo){
		this.mapa[p.getX()][p.getY()] = simbolo;
	}
	
	public Ponto getPosHeroi() {
		return this.heroi.getPosicao();
	}

	public void setPosHeroi(Ponto posHeroi) {
		this.heroi.setPosicao(posHeroi);
	}

	public Ponto getPosEspada() {
		return this.espada.getPosicao();
	}

	public void setPosEspada(Ponto posEspada) {
		this.espada.setPosicao(posEspada);
	}

	public Ponto getPosDragao() {
		return this.dragao.getPosicao();
	}

	public void setPosDragao(Ponto posDragao) {
		this.dragao.setPosicao(posDragao);
	}
	
	public void atualizaLabirinto(){
		if(getDragao().isVivo()){
			setDragao(getDragao());
		}
		setHeroi(getHeroi());
	}
}
