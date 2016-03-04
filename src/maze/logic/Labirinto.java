package maze.logic;

public class Labirinto {
	private char layout[][]; //layout[linha][coluna]
	private Ponto posHeroi;
	private Ponto posEspada;
	private Ponto posDragao;
	
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
	}

	public Ponto getPosHeroi() {
		return posHeroi;
	}

	public void setPosHeroi(Ponto posHeroi) {
		this.posHeroi = posHeroi;
	}

	public Ponto getPosEspada() {
		return posEspada;
	}

	public void setPosEspada(Ponto posEspada) {
		this.posEspada = posEspada;
	}

	public Ponto getPosDragao() {
		return posDragao;
	}

	public void setPosDragao(Ponto posDragao) {
		this.posDragao = posDragao;
	}

	public char[][] getLayout() {
		return layout;
	}
	
	public void setLayout(char[][] layout) {
		this.layout = layout;
	}

	public void setHeroi(Heroi heroi) {
		layout[heroi.getPosicaoHeroi().getX()][heroi.getPosicaoHeroi().getY()] = heroi.getSimbolo();
		posHeroi = heroi.getPosicao();
	}
	
	public void setEspada(Espada espada){
		layout[espada.getPosicaoEspada().getX()][espada.getPosicaoEspada().getY()] = espada.getSimbolo();
		posEspada = espada.getPosicao();
	}
	
	public void setDragao(Dragao dragao){
		layout[dragao.getPosicaoDragao().getX()][dragao.getPosicaoDragao().getY()] = dragao.getSimbolo();
		posDragao = dragao.getPosicao();
	}
	
	public char getSimbolo(Ponto p){
		return layout[p.getX()][p.getY()];
	}
	
	public void setSimbolo(Ponto p, char simb){
		layout[p.getX()][p.getY()] = simb;
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
