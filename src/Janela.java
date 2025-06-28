import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Janela extends JFrame
{
	public static DocumentListener documentListener = new DocumentListener()
	{
		@Override
		public void removeUpdate(DocumentEvent event)
		{
			definirIncognita();
		}
		
		@Override
		public void insertUpdate(DocumentEvent event)
		{
			definirIncognita();
		}
		
		@Override
		public void changedUpdate(DocumentEvent event)
		{
			definirIncognita();
		}
	};
	
	public static JPanel panel;
	
	private static ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	private static Component components[];
	private static Grandeza grandeza;
	private static int cont, posIncognita, posAux;
	
	private Container contentPane = getContentPane();
	private JScrollPane scrollPane;
	private JButton botaoAdicionar, botaoClear;
	
	public Janela()
	{	
		contentPane.setBackground(Color.white);
		
		setLayout(null);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		scrollPane = new JScrollPane(panel);
		
		JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
		horizontalBar.setUnitIncrement(30);
		horizontalBar.setBlockIncrement(175);
		
		botaoAdicionar = new JButton("+");
		botaoAdicionar.setSize(60, 60);
		botaoAdicionar.setFocusPainted(false);
		
		botaoClear = new JButton("C");
		botaoClear.setSize(60, 60);
		botaoClear.setFocusPainted(false);
		
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
				
				// Aguarda a atualização do layout; Posiciona a barra de rolagem no final
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run() 
					{
						horizontalBar.setValue(horizontalBar.getMaximum() - horizontalBar.getVisibleAmount()); 
					}
				});
				
				definirIncognita();
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
					// remove todos os componentes depois do índice 2:
					if(i > 2)
						panel.remove(components[i]);
					
					// reinicia os títulos dos componentes do tipo Grandeza:
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
		
		// Método chamado quando a janela é alterada manualmente
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
			}
		});
		
		// Método chamado quando o estado da janela é alterado
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
		
		add(scrollPane);
		add(botaoAdicionar);
		add(botaoClear);
		setVisible(true);
	}
	
	private void posicionarComponentes()
	{
		scrollPane.revalidate();
		scrollPane.setSize(contentPane.getWidth(), 118);
		scrollPane.setLocation(contentPane.getWidth()/2 - scrollPane.getWidth()/2, contentPane.getHeight()/2 - (scrollPane.getHeight()/2 + (scrollPane.getHeight()*18/100)));
		
		botaoAdicionar.setLocation(getWidth()/2, scrollPane.getY() + scrollPane.getHeight() + 15);
		botaoClear.setLocation(getWidth()/2 - (botaoAdicionar.getWidth() + 19), scrollPane.getY() + scrollPane.getHeight() + 15);
	}
	
	public static void definirIncognita()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				textFields.clear();
				
				// adiciona todos os componentes do tipo JTextField em uma lista
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
					// se o campo estiver em branco, camposPreenchidos aumenta +1
					if(textFields.get(j).getText().isBlank() == false && textFields.get(j).getText().equals("X") == false)
					{
						camposPreenchidos++;
						
						// ao chegar no penúltimo campo, o último automaticamente se torna a incógnita
						if(camposPreenchidos == (textFields.size()-1))
						{
							for(int l = 0; l < textFields.size(); l++)
							{
								if(textFields.get(l).getText().isBlank() == true)
								{
									textFields.get(l).setEditable(false);
									textFields.get(l).setText("X");
									posIncognita = textFields.indexOf(textFields.get(l));
									
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
				
				if(camposPreenchidos <= (textFields.size()-2))
				{
					for(int m = 0; m < textFields.size(); m++)
					{
						// se o campo estiver definido como "X", ou não for editável:
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
					while(posIncognita >= textFields.size())
						posIncognita -= posAux;
					
					textFields.get(posIncognita).setEditable(false);
					textFields.get(posIncognita).setText("X");
				}
			}
		});
	}
	
	public static void main(String[] args)
	{
		new Janela();
	}
}