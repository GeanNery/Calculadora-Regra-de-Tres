import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class JanelaOpcoes extends JFrame
{
	public JanelaOpcoes(Grandeza grandeza, Dimension sizeGrandeza)
	{
		setSize(65, 34);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setLocation(grandeza.getLocationOnScreen().x + (sizeGrandeza.width - getWidth()), grandeza.getLocationOnScreen().y - getHeight());
		
		setAlwaysOnTop(true);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		
		JButton remover = new JButton();
				remover.setPreferredSize(new Dimension(25, 23));
				remover.setFocusPainted(false);
		
		JButton inverter = new JButton();
				inverter.setPreferredSize(new Dimension(25, 23));
				inverter.setFocusPainted(false);
		
		if(Janela.painelGrandezas.getComponentCount() > 3)
		{
			remover.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Remove a grandeza e o sinal de igualdade ao lado
					int indice = Janela.painelGrandezas.getComponentZOrder(grandeza);
					
					Janela.painelGrandezas.remove(grandeza);
					if(indice > 0)
						Janela.painelGrandezas.remove(indice - 1);
					else
						Janela.painelGrandezas.remove(indice);
					
					Janela.painelGrandezas.revalidate();
					Janela.painelGrandezas.repaint();
					Janela.definirIncognita();
					dispose();
				}
			});
		}
    	
		add(inverter);
		add(remover);
		setVisible(true);
	}
}