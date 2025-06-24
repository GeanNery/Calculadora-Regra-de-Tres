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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Janela extends JFrame
{
	public static JPanel panel;
	
	private Container contentPane = getContentPane();
	private Grandeza grandeza;
	private JScrollPane scrollPane;
	private JButton botaoAdicionar, botaoReset;
	private int cont;
	
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
		
		botaoReset = new JButton("C");
		botaoReset.setSize(60, 60);
		botaoReset.setFocusPainted(false);
		
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
			}
		});
		
		botaoReset.addActionListener(new ActionListener()
		{		
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(Grandeza.janelaOpcoes != null && Grandeza.janelaOpcoes.isDisplayable())
					Grandeza.janelaOpcoes.dispose();
				
				// Remove as grandezas do JPanel e reinicia os títulos
				Component components[] = panel.getComponents();
				for(int i = 0; i < components.length; i++)
				{
					if(i > 2)
						panel.remove(components[i]);
					
					if(components[i] instanceof Grandeza && i % 2 == 0)
					{
						Grandeza g = (Grandeza)components[i];
						
						if(i < 1)
							g.titulo.setText("GRANDEZA  " + (i + 1));
						else
							g.titulo.setText("GRANDEZA  " + (i));
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
		add(botaoReset);
		setVisible(true);
	}
	
	private void posicionarComponentes()
	{
		scrollPane.revalidate();
		scrollPane.setSize(contentPane.getWidth(), 118);
		scrollPane.setLocation(contentPane.getWidth()/2 - scrollPane.getWidth()/2, contentPane.getHeight()/2 - (scrollPane.getHeight()/2 + (scrollPane.getHeight()*18/100)));
		
		botaoAdicionar.setLocation(getWidth()/2, scrollPane.getY() + scrollPane.getHeight() + 15);
		botaoReset.setLocation(getWidth()/2 - (botaoAdicionar.getWidth() + 19), scrollPane.getY() + scrollPane.getHeight() + 15);
	}
	
	public static void main(String[] args)
	{
		new Janela();
	}
}