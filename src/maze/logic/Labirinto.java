package maze.logic;

public class Labirinto {
	private char[][] mapa;	
	private Heroi heroi;
	private Espada espada;
	private Dragao dragao[];
	private int nDragoes;
	
	/**
	 * Constr�i um labirinto vazio
	 */
	public Labirinto(){
		this.mapa = new char[][] {{'X'}};
	}
	
	/**
	 * Constr�i um labirinto com altura, largura e o n� de drag�es definido pelo utilizador
	 * @param altura altura do labirinto
	 * @param largura largura do labirinto
	 * @param nDragoes n� de drag�es do labirinto
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
	 * Constr�i um labirinto a partir de um mapa pr�-definido
	 * @param mapa mapa do labirinto
	 */
	public Labirinto(char[][] mapa){
		this.mapa = mapa;
	}
	
	/**
	 * Devolve o her�i do labirinto
	 * @return her�i do labirinto
	 */
	public Heroi getHeroi() {
		return this.heroi;
	}

	/**
	 * Altera o her�i do labirinto
	 * @param heroi novo her�i
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
	 * Altera os drag�s do labirinto
	 * @param dragao novos drag�es
	 */
	public void setDragao(Dragao[] dragao){
		this.dragao = dragao.clone();
	}
	
	/**
	 * Devolve os drag�es do labirinto sob forma de array
	 * @return drag�es de labirinto
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
	 * Devolve o conte�do do mapa num determinado ponto
	 * @param p Ponto 
	 * @return conte�do do mapa no Ponto
	 */
	public char getConteudo(Ponto p){
		return this.mapa[p.getX()][p.getY()];
	}
	
	/**
	 * Altera o conte�do do mapa num determinado ponto
	 * @param p Ponto
	 * @param simbolo novo conte�do do mapa
	 */
	public void setConteudo(Ponto p, char simbolo){
		this.mapa[p.getX()][p.getY()] = simbolo;
	}
	
	/**
	 * Devolve a posi��o do her�i
	 * @return posi��o do her�i
	 */
	public Ponto getPosHeroi() {
		return this.heroi.getPosicao();
	}

	/**
	 * Altera a posi��o do her�i
	 * @param posHeroi nova posi��o do her�i
	 */
	public void setPosHeroi(Ponto posHeroi) {
		this.heroi.setPosicao(posHeroi);
	}

	/**
	 * Devolve a posi��o da espada
	 * @return posi��o da espada
	 */
	public Ponto getPosEspada() {
		return this.espada.getPosicao();
	}

	/**
	 * Altera a posi��o da espada
	 * @param posEspada nova posi��o da espada
	 */
	public void setPosEspada(Ponto posEspada) {
		this.espada.setPosicao(posEspada);
	}

	/**
	 * Atualiza os conte�dos do labirinto para as posi��es de cada Personagem
	 */
	public void atualizaLabirinto(){
		mapa[espada.getPosicao().getX()][espada.getPosicao().getY()]=espada.getSimbolo();
		for(int i=0; i<nDragoes;i++){
			mapa[dragao[i].getPosicao().getX()][dragao[i].getPosicao().getY()]=dragao[i].getSimbolo();
		}	
		mapa[heroi.getPosicao().getX()][heroi.getPosicao().getY()]=heroi.getSimbolo();
	}
}
