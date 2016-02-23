
public class Labirinto {
	
		char layout [][];
		Heroi heroi;
		
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
			heroi= new Heroi(1,1,'H');
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
