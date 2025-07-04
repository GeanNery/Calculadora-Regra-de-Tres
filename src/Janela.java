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
	public static JPanel panel, panel2;
	
	private static Component components[];
	private static Grandeza grandeza, grandInc;
	private static int cont, posIncognita, posAux;
	
	private Container contentPane = getContentPane();
	private JScrollPane scrollPane;
	private JButton botaoAdicionar, botaoClear, botaoCalcular;
	private static JTextField incognita, outro;
	private static boolean inverterResultado;
	
	public Janela()
	{	
		contentPane.setBackground(Color.white);
		
		setTitle("Regra de Três Simples e Composta");
		setLayout(null);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		scrollPane = new JScrollPane(panel);
		scrollPane.setOpaque(true);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setSize(155, 135);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JScrollBar barraHorizontal = scrollPane.getHorizontalScrollBar();
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
		
		cont = 1;
		for(int i = 0; i < 2; i++)
		{
			if(i > 0 )
			{
				JLabel igual = new JLabel("=");
				panel.add(igual);
			}
			
			grandeza = new Grandeza(cont);
			panel.add(grandeza);
			cont++;
		}
		
		botaoAdicionar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(Grandeza.janelaOpcoes != null && Grandeza.janelaOpcoes.isDisplayable())
					Grandeza.janelaOpcoes.dispose();
				
				JLabel igual = new JLabel("=");
				panel.add(igual);
				
				grandeza = new Grandeza(cont);
				panel.add(grandeza);
				cont++;
				
				scrollPane.revalidate();
				scrollPane.repaint();
				definirIncognita();
				
				//	Aguarda a atualização do layout; Posiciona a barra de rolagem no final
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
				if(Grandeza.janelaOpcoes != null && Grandeza.janelaOpcoes.isDisplayable())
					Grandeza.janelaOpcoes.dispose();
				
				components = panel.getComponents();				
				for(int i = 0; i < components.length; i++)
				{
					//	Remove todos os componentes depois do índice 2:
					if(i > 2)
						panel.remove(components[i]);
					
					//	Reinicia os títulos dos componentes do tipo Grandeza:
					if(components[i] instanceof Grandeza && i % 2 == 0)
					{
						grandeza = (Grandeza) components[i];
						
						if(i < 1)
							grandeza.titulo.setText("GRANDEZA  " + (i + 1));
						else
							grandeza.titulo.setText("GRANDEZA  " + (i));
						
						grandeza.A1.setText("");
						grandeza.B1.setText("");
					}
				}
				
				cont = panel.getComponentCount();
				scrollPane.revalidate();
				scrollPane.repaint();
			}
		});
		
		botaoCalcular.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{	
				int a2 = 1;
				int b2 = 1;
				
				components = panel.getComponents();
				for(int i = 0; i < components.length; i++)
				{
					if(components[i] instanceof Grandeza)
					{
						grandeza = (Grandeza) components[i];
						
						if(grandeza.A1.getText().equals("X") || grandeza.B1.getText().equals("X"))
						{
							grandInc = grandeza;
							
							if(grandInc.A1.getText().equals("X"))
							{
								incognita = grandInc.A1;
								outro = grandInc.B1;
								inverterResultado = false;
							}
							else if(grandInc.B1.getText().equals("X"))
							{
								outro = grandInc.A1;
								incognita = grandInc.B1;
								inverterResultado = true;
							}
						}
						else
						{
							a2 *= Integer.valueOf(grandeza.A1.getText());
							b2 *= Integer.valueOf(grandeza.B1.getText());
						}
					}
				}
				
				if(inverterResultado == true)
					System.out.println(String.valueOf(b2 * Integer.valueOf(outro.getText()) / a2));
				else
					System.out.println(String.valueOf(a2 * Integer.valueOf(outro.getText()) / b2));
			}
		});
		
		//	Método chamado quando a janela é alterada manualmente
		addComponentListener(new ComponentAdapter()
		{
			@Override 
			public void componentResized(ComponentEvent event)
			{
				if(Grandeza.janelaOpcoes != null && Grandeza.janelaOpcoes.isDisplayable())
					Grandeza.janelaOpcoes.dispose();
				
				posicionarComponentes();
			}
				
			@Override
			public void componentMoved(ComponentEvent event)
			{
				if(Grandeza.janelaOpcoes != null && Grandeza.janelaOpcoes.isDisplayable())
					Grandeza.janelaOpcoes.dispose();
				
				reiniciarTitulo();
			}
		});
		
		//	Método chamado quando o estado da janela é alterado
		addWindowStateListener(new WindowAdapter()
		{
			@Override
			public void windowStateChanged(WindowEvent event)
			{
				if(Grandeza.janelaOpcoes != null && Grandeza.janelaOpcoes.isDisplayable())
					Grandeza.janelaOpcoes.dispose();
				
				posicionarComponentes();
			}
		});
		
		gbc.insets = new Insets(3, 8, 3, 8);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		panel2.add(botaoClear, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		panel2.add(botaoAdicionar, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2.add(botaoCalcular, gbc);
		
		add(scrollPane);
		add(panel2);
		setVisible(true);
	}
	
	public static void definirIncognita()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				ArrayList<JTextField> textFields = new ArrayList<JTextField>();
				
				//	Adiciona todos os componentes do tipo JTextField em uma lista
				components = panel.getComponents();
				for(int i = 0; i < components.length; i++)
				{
					if(components[i] instanceof Grandeza)
					{
						grandeza = (Grandeza)components[i];
						textFields.add(grandeza.A1);
						textFields.add(grandeza.B1);
					}
				}
				
				int camposPreenchidos = 0;
				for(int j = 0; j < textFields.size(); j++)
				{	
					//	Se o campo estiver em branco, camposPreenchidos aumenta +1
					if(textFields.get(j).getText().isBlank() == false && textFields.get(j).getText().equals("X") == false)
					{
						camposPreenchidos++;
						
						//	Ao chegar no penúltimo campo, o último automaticamente se torna a incógnita
						if(camposPreenchidos == (textFields.size()-1))
						{
							for(int l = 0; l < textFields.size(); l++)
							{
								if(textFields.get(l).getText().isBlank() == true)
								{
									textFields.get(l).setEditable(false);
									textFields.get(l).setText("X");
									posIncognita = textFields.indexOf(textFields.get(l));  // Salva o valor do índice da incógnita
									
									if(textFields.get(l) == grandeza.A1)
										posAux = 2;
									else if(textFields.get(l) == grandeza.B1)
										posAux = 1;
	
									return;
								}
							}
						}
					}
				}
				
				/*	Se o usuário apagar um campo de texto, o programa vasculha a lista textFields em busca de um
					campo definido com o valor "X". Se ele for encontrado, ele é reiniciado e se torna editável novamente.  */
				if(camposPreenchidos <= (textFields.size()-2))
				{
					for(int m = 0; m < textFields.size(); m++)
					{
						if(textFields.get(m).getText().equals("X") || textFields.get(m).isEditable() == false)
						{
							textFields.get(m).setEditable(true);
							textFields.get(m).setText("");
							return;
						}
					}
				}
				else if(camposPreenchidos == textFields.size())
				{
					/*	Se o usuário deletar a grandeza com a incógnita, e as restantes estiverem com todos os seus campos
						preenchidos, a posição da incógnita é escolhida de acordo com o campo A ou B preenchido da última vez.  */
					
					while(posIncognita >= textFields.size())
						posIncognita -= posAux;
					
					textFields.get(posIncognita).setEditable(false);
					textFields.get(posIncognita).setText("X");
				}
			}
		});
	}
	
	private void reiniciarTitulo()
	{
		components = panel.getComponents();
		
		for(int i = 0; i < components.length; i++)
		{
			if(components[i] instanceof Grandeza)
			{
				grandeza = (Grandeza)components[i];
				
				if(grandeza.titulo.getText().isBlank())
					grandeza.reiniciarTitulo();
			}
		}
	}
	
	private void posicionarComponentes()
	{	
		scrollPane.setSize(contentPane.getWidth(), 118);
		scrollPane.setLocation(contentPane.getWidth()/2 - scrollPane.getWidth()/2, contentPane.getHeight()/2 - (scrollPane.getHeight()/2 + 40));
		scrollPane.revalidate();
		scrollPane.repaint();
		
		panel2.setLocation(getWidth()/2 - (panel2.getWidth()/2 + 9), scrollPane.getY() + scrollPane.getHeight());
		reiniciarTitulo();
	}
	
	public static void main(String[] args)
	{
		new Janela();
	}
}