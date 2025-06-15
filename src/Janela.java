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
import javax.swing.JScrollPane;

public class Janela extends JFrame
{
	public int sizeX = 600, sizeY = 500;
	private int cont;
	
	private Container contentPane = getContentPane();
	private JScrollPane scrollPane;
	private JButton botao = new JButton();
	
	public Janela()
	{	
		contentPane.setBackground(Color.white);
		
		setLayout(null);
		setSize(sizeX, sizeY);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));	
		scrollPane = new JScrollPane(panel);
		
		botao.setText("+");
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
		
		// Método chamado quando a janela é redimensionada manualmente
		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent event)
			{
				reposicionarComponentes();
			}
		});
		
		// Método chamado quando o estado da janela é alterado
		addWindowStateListener(new WindowAdapter()
		{
			@Override
			public void windowStateChanged(WindowEvent event)
			{
				reposicionarComponentes();
			}
		});
		
		botao.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				JLabel igual = new JLabel("=");
				panel.add(igual);
				Grandeza grandeza = new Grandeza(cont);
				panel.add(grandeza);
				cont++;
				
				scrollPane.revalidate();
			}
		});
		
		add(scrollPane);
		add(botao);
		setVisible(true);
	}
	
	public void reposicionarComponentes()
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