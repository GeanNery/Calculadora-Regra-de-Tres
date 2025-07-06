import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Janela extends JFrame
{
	public static JPanel painelGrandezas, painelBotoes;
	
	private static int posicao;
	private static boolean estaCalculando;
	
	private Container contentPane = getContentPane();
	private JScrollPane scrollPane;
	private JButton botaoAdicionar, botaoClear, botaoCalcular;
	private int cont;
	
	public Janela()
	{	
		contentPane.setBackground(Color.white);
		
		setTitle("Regra de Três Simples e Composta");
		setLayout(null);
		setSize(600, 500);
		setMinimumSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		painelGrandezas = new JPanel();
		painelGrandezas.setBackground(Color.white);
		painelGrandezas.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		painelBotoes = new JPanel();
		painelBotoes.setLayout(new GridBagLayout());
		painelBotoes.setSize(155, 135);
		GridBagConstraints gbc = new GridBagConstraints();
		
		scrollPane = new JScrollPane(painelGrandezas);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		JScrollBar barraHorizontal = scrollPane.getHorizontalScrollBar();
		barraHorizontal.setPreferredSize(new Dimension(10, 10));
		barraHorizontal.setUnitIncrement(30);
		barraHorizontal.setBlockIncrement(175);
		
		botaoAdicionar = new JButton("+");
		botaoAdicionar.setPreferredSize(new Dimension(60, 60));
		botaoAdicionar.setFocusPainted(false);
		
		botaoClear = new JButton("C");
		botaoClear.setPreferredSize(new Dimension(60, 60));
		botaoClear.setFocusPainted(false);
		
		botaoCalcular = new JButton("Calcular");
		botaoCalcular.setPreferredSize(new Dimension(120, 60));
		botaoCalcular.setFocusPainted(false);
		
		criarGrandeza(2);
		
		botaoAdicionar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				Grandeza.fecharJanela();
				criarGrandeza(1);
				
				//	Aguarda a atualização do layout e posiciona a barra de rolagem no final
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run() 
					{
						barraHorizontal.setValue(barraHorizontal.getMaximum() - barraHorizontal.getVisibleAmount()); 
					}
				});
			}
		});
		
		botaoClear.addActionListener(new ActionListener()
		{		
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Grandeza.fecharJanela();	
				painelGrandezas.removeAll();
				criarGrandeza(2);
			}
		});
		
		botaoCalcular.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{	
				JTextField incognita = null, outro = null;
				String resultado;
				int valorA = 1, valorB = 1;
				boolean inverterCalculo = false;
				
				estaCalculando = true;	
				for(Component component : painelGrandezas.getComponents())
				{
					if(component instanceof Grandeza)
					{
						Grandeza g = (Grandeza)component;
						
						if(g.A1.getText().equals("X") || g.A1.isEditable() == false)
						{
							incognita = g.A1;
							outro = g.B1;
							inverterCalculo = false;
						}
						else if(g.B1.getText().equals("X") || g.B1.isEditable() == false)
						{
							incognita = g.B1;
							outro = g.A1;
							inverterCalculo = true;
						}
						else
						{
							valorA *= Integer.valueOf(g.A1.getText());
							valorB *= Integer.valueOf(g.B1.getText());
						}
					}
				}
				
				//	Garante que o cálculo será cruzado
				if(inverterCalculo == true)
					resultado = String.valueOf(valorB * Integer.valueOf(outro.getText()) / valorA);
				else
					resultado = String.valueOf(valorA * Integer.valueOf(outro.getText()) / valorB);
				
				incognita.setText(resultado);
				estaCalculando = false;
			}
		});
		
		//	Método chamado quando a janela é alterada manualmente
		addComponentListener(new ComponentAdapter()
		{
			@Override 
			public void componentResized(ComponentEvent event)
			{
				Grandeza.fecharJanela();
				posicionarComponentes();
				reiniciarTitulos();
			}
				
			@Override
			public void componentMoved(ComponentEvent event)
			{
				Grandeza.fecharJanela();
				reiniciarTitulos();
			}
		});
		
		//	Método chamado quando o estado da janela é alterado
		addWindowStateListener(new WindowAdapter()
		{
			@Override
			public void windowStateChanged(WindowEvent event)
			{
				Grandeza.fecharJanela();
				posicionarComponentes();
				reiniciarTitulos();
			}
		});
		
		gbc.insets = new Insets(3, 8, 3, 8);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		painelBotoes.add(botaoClear, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		painelBotoes.add(botaoAdicionar, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		painelBotoes.add(botaoCalcular, gbc);
		
		add(scrollPane);
		add(painelBotoes);
		setVisible(true);
	}
	
	private void posicionarComponentes()
	{	
		scrollPane.setSize(contentPane.getWidth(), 126);
		scrollPane.setLocation(contentPane.getWidth()/2 - scrollPane.getWidth()/2, contentPane.getHeight()/2 - (scrollPane.getHeight()/2 + 40));
		scrollPane.revalidate();
		scrollPane.repaint();
		painelBotoes.setLocation(getWidth()/2 - (painelBotoes.getWidth()/2 + 9), scrollPane.getY() + scrollPane.getHeight());
	}
	
	private void criarGrandeza(int quant)
	{
		for(int i = 0; i < quant; i++)
		{
			if(painelGrandezas.getComponentCount() != 0)
			{
				JLabel igual = new JLabel("=");
				painelGrandezas.add(igual);
			}
			else
				cont = 1;
			
			Grandeza g = new Grandeza(cont);
			painelGrandezas.add(g);
			cont++;
		}
		
		scrollPane.revalidate();
		scrollPane.repaint();
		definirIncognita();
	}
	
	private void reiniciarTitulos()
	{
		for(Component component : painelGrandezas.getComponents())
		{
			if(component instanceof Grandeza)
			{
				Grandeza g = (Grandeza)component;
				
				if(g.titulo.getText().isBlank())
					g.inserirTitulo();
			}
		}
	}
	
	public static void definirIncognita()
	{
		if(estaCalculando == true)
			return;
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				ArrayList<JTextField> textFields = new ArrayList<JTextField>();
				Grandeza g = null;
				int camposPreenchidos = 0, posicaoAux = 0;
				
				//	Adiciona todos os JTextFields em uma lista:
				for(Component component : painelGrandezas.getComponents())
				{	
					if(component instanceof Grandeza)
					{	
						g = (Grandeza)component;
						textFields.add(g.A1);
						textFields.add(g.B1);
					}
				}
				
				// Verifica a quantidade de campos preenchidos:
				for(JTextField textfield : textFields)
				{	
					if(textfield.getText().isBlank() == false && textfield.getText().equals("X") == false && textfield.isEditable() == true)
						camposPreenchidos++;
				}
				
				//	Preenchimento automático:
				if(camposPreenchidos == textFields.size()-1)  // Se o penúltimo campo for preenchido, o último se torna a incógnita.
				{
					for(JTextField textfield : textFields)
					{
						if(textfield.getText().isBlank() == true || (textfield.isEditable() == false && textfield.getText().equals("X") == false))
						{
							textfield.setEditable(false);
							textfield.setText("X");
							posicao = textFields.indexOf(textfield);
							
							if(textfield == g.A1)
								posicaoAux = 2;
							else if(textfield == g.B1)
								posicaoAux = 1;
							
							return;
						}
					}
				}
				else if(camposPreenchidos <= textFields.size()-2)  // Se o usuário apagar o penúltimo campo, a incógnita é apagada.
				{
					for(JTextField textfield : textFields)
					{
						if(textfield.getText().equals("X") || textfield.isEditable() == false)
						{
							textfield.setEditable(true);
							textfield.setText("");
							return;
						}
					}
				}
				else if(camposPreenchidos == textFields.size())  // Se a grandeza contendo a incógnita for deletada, o "X" vai para a próxima disponível.
				{
					while(posicao >= textFields.size())
						posicao -= posicaoAux;
					
					textFields.get(posicao).setEditable(false);
					textFields.get(posicao).setText("X");
				}
			}
		});
	}
	
	public static void main(String[] args)
	{
		new Janela();
	}
}