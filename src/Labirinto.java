import java.util.Scanner;

public class Labirinto {
	private char layout[][]; //layout[linha][coluna]
	private Heroi heroi;

	public Labirinto(){
		layout = new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
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
		layout[heroi.getX()][heroi.getY()]= heroi.getSimbolo();
	}

	
	public void moveHeroi(){
		Scanner s = new Scanner(System.in);
		String movimento;
		int heroix= heroi.getX();
		int heroiy=heroi.getY();
		
		System.out.println("Mover herói: ");
		movimento = s.next();
		s.close();
		
		boolean parede= false;
		switch(movimento){
		case "cima":
			heroix= heroix-1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else
				layout[heroix+1][heroiy]=' ';
			break;
		case "baixo":
			heroix= heroix+1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else
				layout[heroix-1][heroiy]=' ';
			break;
		case "esquerda":
			heroiy=heroiy-1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else
				layout[heroix][heroiy+1]=' ';
			break;
		case "direita":
			heroiy=heroiy+1;
			if(layout[heroix][heroiy]=='X')
				parede=true;
			else
				layout[heroix][heroiy-1]=' ';
			break;		
		}
		
		if(!parede){
			char simb= heroi.getSimbolo();
			setHeroi(new Heroi(heroix,heroiy,simb));
		}
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
