package maze.logic;

public class Labirinto {
	private char[][] mapa;	
	private Heroi heroi;
	private Espada espada;
	private Dragao dragao[];
	private int nDragoes;
	
	/**
	 * Constrói um labirinto vazio
	 */
	public Labirinto(){
		this.mapa = new char[][] {{'X'}};
	}
	
	/**
	 * Constrói um labirinto com altura, largura e o nº de dragões definido pelo utilizador
	 * @param altura altura do labirinto
	 * @param largura largura do labirinto
	 * @param nDragoes nº de dragões do labirinto
	 */
	public Labirinto(int altura, int largura, int nDragoes){
		LabirintoRandom l= new LabirintoRandom(altura, largura,nDragoes);
		mapa=l.getLab();
		espada=l.getEspada();
		heroi=l.getHeroi();
		dragao=l.getDragao().clone();
		this.nDragoes=nDragoes;
	}
	
	/**
	 * Constrói um labirinto a partir de um mapa pré-definido
	 * @param mapa mapa do labirinto
	 */
	public Labirinto(char[][] mapa){
		this.mapa = mapa;
	}
	
	/**
	 * Devolve o herói do labirinto
	 * @return herói do labirinto
	 */
	public Heroi getHeroi() {
		return this.heroi;
	}

	/**
	 * Altera o herói do labirinto
	 * @param heroi novo herói
	 */
	public void setHeroi(Heroi heroi) {
		this.mapa[heroi.getPosicao().getX()][heroi.getPosicao().getY()] = heroi.getSimbolo();
		this.heroi = heroi;
	}

	/**
	 * Devolve a espada do labirinto
	 * @return espada do labirinto
	 */
	public Espada getEspada() {
		return this.espada;
	}
	public void setNumeroDragoes(int n){
		this.nDragoes=n;
	}

	/**
	 * Altera a espada do labirinto
	 * @param espada nova espada
	 */
	public void setEspada(Espada espada) {		
		this.mapa[espada.getPosicao().getX()][espada.getPosicao().getY()] = espada.getSimbolo();
		this.espada = espada;
	}

	/**
	 * Altera os dragõs do labirinto
	 * @param dragao novos dragões
	 */
	public void setDragao(Dragao[] dragao){
		this.dragao = dragao.clone();
	}
	
	/**
	 * Devolve os dragões do labirinto sob forma de array
	 * @return dragões de labirinto
	 */
	public Dragao[] getDragao() {
		return this.dragao;
	}

	/**
	 * Devolve o mapa do labirinto
	 * @return mapa do labirinto
	 */
	public char[][] getMapa(){
		return this.mapa;
	}
	
	/**
	 * Altera o mapa do labirinto
	 * @param mapa novo mapa do labirinto
	 */
	public void setMapa(char[][] mapa){
		this.mapa = mapa;
	}
	
	/**
	 * Devolve o conteúdo do mapa num determinado ponto
	 * @param p Ponto 
	 * @return conteúdo do mapa no Ponto
	 */
	public char getConteudo(Ponto p){
		return this.mapa[p.getX()][p.getY()];
	}
	
	/**
	 * Altera o conteúdo do mapa num determinado ponto
	 * @param p Ponto
	 * @param simbolo novo conteúdo do mapa
	 */
	public void setConteudo(Ponto p, char simbolo){
		this.mapa[p.getX()][p.getY()] = simbolo;
	}
	
	/**
	 * Devolve a posição do herói
	 * @return posição do herói
	 */
	public Ponto getPosHeroi() {
		return this.heroi.getPosicao();
	}

	/**
	 * Altera a posição do herói
	 * @param posHeroi nova posição do herói
	 */
	public void setPosHeroi(Ponto posHeroi) {
		this.heroi.setPosicao(posHeroi);
	}

	/**
	 * Devolve a posição da espada
	 * @return posição da espada
	 */
	public Ponto getPosEspada() {
		return this.espada.getPosicao();
	}

	/**
	 * Altera a posição da espada
	 * @param posEspada nova posição da espada
	 */
	public void setPosEspada(Ponto posEspada) {
		this.espada.setPosicao(posEspada);
	}

	/**
	 * Atualiza os conteúdos do labirinto para as posições de cada Personagem
	 */
	public void atualizaLabirinto(){
		mapa[espada.getPosicao().getX()][espada.getPosicao().getY()]=espada.getSimbolo();
		for(int i=0; i<nDragoes;i++){
			mapa[dragao[i].getPosicao().getX()][dragao[i].getPosicao().getY()]=dragao[i].getSimbolo();
		}	
		mapa[heroi.getPosicao().getX()][heroi.getPosicao().getY()]=heroi.getSimbolo();
	}
}
