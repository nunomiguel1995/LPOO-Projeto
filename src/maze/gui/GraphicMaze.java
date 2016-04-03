package maze.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

import maze.logic.Jogo;
import maze.logic.Jogo.Direcao;
import maze.logic.Jogo.EstadoJogo;


public class GraphicMaze extends JFrame implements KeyListener, MouseListener{

	private GraphicPanel panel;
	private CostumizeMaze draw;
	private JFrame frame= new JFrame();
	private JPanel costumize= new JPanel();
	private JPanel main = new JPanel();
	private JPanel panels= new JPanel();
	private JTextField fldLargura;
	private JTextField fldNumeroDragoes;
	private JTextField fldAltura;
	JComboBox<String> cbComponentes;
	private Jogo j;
	CardLayout windows= new CardLayout();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GraphicMaze();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GraphicMaze() {
		panels.setLayout(windows);
		
		initialize();
		
		panels.add(main,"main");
		panels.add(costumize, "costumize");
		windows.show(panels, "main");
		frame.add(panels);
		frame.setVisible(true);
		
		addKeyListener(this);
		addMouseListener(this);
	}

	private void initialize() {	
		
		frame.setBounds(100, 100, 931, 732);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLayout(null);
		costumize.setLayout(null);
		
		JLabel Dimensão = new JLabel("Dimens\u00E3o");
		Dimensão.setBounds(41, 35, 123, 14);
		main.add(Dimensão);
		
		JLabel NumeroDragoes = new JLabel("N\u00FAmero de Drag\u00F5es");
		NumeroDragoes.setBounds(41, 71, 123, 14);
		main.add(NumeroDragoes);
		
		fldLargura = new JTextField();
		fldLargura.setText("11");
		fldLargura.setBounds(193, 32, 33, 20);
		main.add(fldLargura);
		fldLargura.setColumns(10);
		
		fldNumeroDragoes = new JTextField();
		fldNumeroDragoes.setText("1");
		fldNumeroDragoes.setBounds(193, 68, 63, 20);
		main.add(fldNumeroDragoes);
		fldNumeroDragoes.setColumns(10);
		
		JLabel TipoDragoes = new JLabel("Tipo de Drag\u00F5es");
		TipoDragoes.setBounds(41, 108, 123, 14);
		main.add(TipoDragoes);
		
		String[] tipoDragoes= {"Estáticos", "Movimentação aleatória", "Movimentação e adormecer"};
		final JComboBox cbTipoDragoes = new JComboBox(tipoDragoes);
		cbTipoDragoes.setBounds(193, 105, 257, 20);
		main.add(cbTipoDragoes);
		
		final JLabel GameState = new JLabel("Pode gerar um novo Labirinto!");
		GameState.setBounds(41, 668, 279, 14);
		main.add(GameState);
		
		final JButton btnCima = new JButton("Cima");
		final JButton btnEsquerda = new JButton("Esquerda");
		final JButton btnDireita = new JButton("Direita");
		final JButton btnBaixo = new JButton("Baixo");
		
		
		btnCima.setBounds(739, 181, 79, 33);
		main.add(btnCima);
		btnCima.setEnabled(false);
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.ESQUERDA);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado() == "")
					GameState.setText("Pode jogar!");
				else{
					JOptionPane.showMessageDialog(main, j.imprimeEstado());
					GameState.setText(j.imprimeEstado());
				}
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		btnEsquerda.setBounds(681, 225, 93, 33);
		main.add(btnEsquerda);
		btnEsquerda.setEnabled(false);
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.CIMA);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado() == "")
					GameState.setText("Pode jogar!");
				else{
					JOptionPane.showMessageDialog(main, j.imprimeEstado());
					GameState.setText(j.imprimeEstado());
				}
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		btnDireita.setBounds(795, 225, 93, 33);
		main.add(btnDireita);
		btnDireita.setEnabled(false);
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.BAIXO);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado() == "")
					GameState.setText("Pode jogar!");
				else{
					JOptionPane.showMessageDialog(main, j.imprimeEstado());
					GameState.setText(j.imprimeEstado());
				}
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		btnBaixo.setBounds(739, 267, 79, 33);
		main.add(btnBaixo);
		btnBaixo.setEnabled(false);
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.DIREITA);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado() == "")
					GameState.setText("Pode jogar!");
				else{
					JOptionPane.showMessageDialog(frame, j.imprimeEstado());
					GameState.setText(j.imprimeEstado());
				}
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		JButton btnGerarLabirinto = new JButton("Gerar novo Labirinto");
		btnGerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int altura,largura, nDragoes;
				String opcao;
				
				try{
				altura= Integer.parseInt(fldAltura.getText());
				largura= Integer.parseInt(fldLargura.getText());
				nDragoes= Integer.parseInt(fldNumeroDragoes.getText());
				opcao= (String) cbTipoDragoes.getSelectedItem();
				
				if(altura<= 3|| largura <= 3 || nDragoes <= 0 
						|| altura > 21 || largura > 25 || (altura % 2) == 0 || (largura %2) == 0)
					throw new IllegalArgumentException();
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(main, "Formato nao valido");
					return;
				}
				catch(IllegalArgumentException ex){
					JOptionPane.showMessageDialog(main, "Números inválidos");
					return;
				}
				j = new Jogo(altura,largura,nDragoes);
				
				switch(opcao){
				case "Estáticos": 
					j.setComportamentoDragao(1);
					break;
				case "Movimentação aleatória":
					j.setComportamentoDragao(2);
					break;
				case "Movimentação e adormecer":
					j.setComportamentoDragao(3);
					break;
				}
				
				panel = new GraphicPanel();
				panel.setBounds(37, 133, 634, 525);
				main.add(panel);
				panel.setJogo(j);
				panel.requestFocus();
				panel.repaint();
				
				GameState.setText("Pode jogar!");
				
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnDireita.setEnabled(true);
				btnEsquerda.setEnabled(true);
			}
		});
		btnGerarLabirinto.setBounds(698, 26, 162, 33);
		main.add(btnGerarLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(698, 78, 162, 44);
		main.add(btnTerminarPrograma);

		fldAltura = new JTextField();
		fldAltura.setText("11");
		fldAltura.setBounds(240, 32, 32, 20);
		main.add(fldAltura);
		fldAltura.setColumns(10);	
		
		JButton btnDesenharLabirinto = new JButton("Desenhar Labirinto");
		btnDesenharLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int altura,largura, nDragoes;
				String opcao;			
				try{
				altura= Integer.parseInt(fldAltura.getText());
				largura= Integer.parseInt(fldLargura.getText());
				nDragoes= Integer.parseInt(fldNumeroDragoes.getText());
				opcao= (String) cbTipoDragoes.getSelectedItem();
				
				if(altura<= 3|| largura <= 3 || nDragoes <= 0 
						|| altura > 21 || largura > 25 || (altura % 2) == 0 || (largura %2) == 0)
					throw new IllegalArgumentException();
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(main, "Formato nao valido");
					return;
				}
				catch(IllegalArgumentException ex){
					JOptionPane.showMessageDialog(main, "Números inválidos");
					return;
				}
				draw = new CostumizeMaze(altura,largura,nDragoes,opcao);
				draw.setBounds(37, 133, 634, 525);
				costumize.add(draw);
				windows.show(panels, "costumize");
				draw.requestFocus();
				draw.repaint();		
			}
		});
		btnDesenharLabirinto.setBounds(498, 26, 162, 33);
		main.add(btnDesenharLabirinto);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(681,133,130,50);
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windows.show(panels, "main");
				j= draw.getJogo();
				panel = new GraphicPanel();
				panel.setBounds(37, 133, 634, 525);
				main.add(panel);
				panel.setJogo(j);
				panel.requestFocus();
				panel.repaint();
				
				GameState.setText("Pode jogar!");
				
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnDireita.setEnabled(true);
				btnEsquerda.setEnabled(true);
			}
		});	
		costumize.add(btnConcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(681,193,130,50);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windows.show(panels,"main");
			}
		});	
		costumize.add(btnCancelar);
		
		JLabel CompDesenho = new JLabel("Componente a desenhar");
		CompDesenho.setBounds(37, 30, 200, 30);
		costumize.add(CompDesenho);
		
		String[] componentes= {"Parede", "Chão", "Heroi","Dragao","Espada","Saida"};
		cbComponentes = new JComboBox<String>(componentes);
		cbComponentes.setBounds(237, 35 , 257, 20);
		costumize.add(cbComponentes);
		cbComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s= (String) cbComponentes.getSelectedItem();
				draw.setSelection(s);
			}
		});	
	}

	@Override
	public void keyPressed(KeyEvent k) {
		panel.keyPressed(k);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		draw.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}
}