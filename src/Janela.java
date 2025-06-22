import java.awt.Color;
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
	private Container contentPane = getContentPane();
	public static JPanel panel;
	private JScrollPane scrollPane;
	private JButton botao;
	
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
		
		botao = new JButton("+");
		botao.setSize(60, 60);
		botao.setFocusPainted(false);
		
		cont = 1;
		for(int i = 0; i < 2; i++)
		{
			if(i > 0 )
			{
				JLabel igual = new JLabel("=");
				panel.add(igual);
			}
			Grandeza grandeza = new Grandeza(cont);
			panel.add(grandeza);
			cont++;
		}
		
		botao.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				Grandeza.janelaOpcoes.dispose();
				
				JLabel igual = new JLabel("=");
				panel.add(igual);
				
				Grandeza grandeza = new Grandeza(cont);
				panel.add(grandeza);
				cont++;
				scrollPane.revalidate();
				
				// Aguarda a atualização do layout antes de executar
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run() 
					{
						horizontalBar.setValue(horizontalBar.getMaximum() - horizontalBar.getVisibleAmount()); 
					}
				});
			}
		});
		
		// Método chamado quando a janela é alterada manualmente
		addComponentListener(new ComponentAdapter()
		{
			@Override 
			public void componentResized(ComponentEvent event)
			{
				posicionarComponentes();
				Grandeza.janelaOpcoes.dispose();
			}
				
			@Override
			public void componentMoved(ComponentEvent e)
			{
				Grandeza.janelaOpcoes.dispose();
			}
		});
		
		// Método chamado quando o estado da janela é alterado
		addWindowStateListener(new WindowAdapter()
		{
			@Override
			public void windowStateChanged(WindowEvent event)
			{
				posicionarComponentes();
				Grandeza.janelaOpcoes.dispose();
			}
		});
		
		add(scrollPane);
		add(botao);
		setVisible(true);
	}
	
	private void posicionarComponentes()
	{
		scrollPane.revalidate();
		scrollPane.setSize(contentPane.getWidth(), 118);
		scrollPane.setLocation(contentPane.getWidth()/2 - scrollPane.getWidth()/2, contentPane.getHeight()/2 - (scrollPane.getHeight()/2 + (scrollPane.getHeight()*18/100)));
		
		botao.setLocation(getWidth()/2 - (botao.getWidth()/2 + 9), scrollPane.getY() + scrollPane.getHeight() + 15);
	}
	
	public static void main(String[] args)
	{
		new Janela();
	}
}