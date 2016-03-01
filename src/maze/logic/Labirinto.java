package maze.logic;

public class Labirinto {
	private char layout[][]; //layout[linha][coluna]
	

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

	public char[][] getLayout() {
		return layout;
	}
	
	public void setLayout(char[][] layout) {
		this.layout = layout;
	}

	public void setHeroi(Heroi heroi) {
		layout[heroi.getX()][heroi.getY()]= heroi.getSimbolo();
	}
	
	public void setEspada(Espada espada){
		layout[espada.getX()][espada.getY()]=espada.getSimbolo();
	}
	
	public void setDragao(Dragao dragao){
		layout[dragao.getX()][dragao.getY()]=dragao.getSimbolo();
	}
	
	public char getSimbolo(int x, int y){
		return layout[x][y];
	}
	
	public void setSimbolo(int x, int y, char simb){
		layout[x][y]=simb;
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
