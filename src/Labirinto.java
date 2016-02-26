public class Labirinto {
	private char layout[][]; //layout[linha][coluna]
	private Heroi heroi;

	public Labirinto(){
		layout = new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','D','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','E','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
		}; 
		setHeroi(new Heroi(1,1,'H'));
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

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	public void moveHeroi(int x, int y, char simbolo){
		layout[x][y] = simbolo;
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
