import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class JanelaOpcoes extends JFrame
{
	public JanelaOpcoes(Grandeza grandeza, Dimension sizeGrandeza)
	{
		setAlwaysOnTop(true);
		setSize(65, 34);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setLocation(grandeza.getLocationOnScreen().x + (sizeGrandeza.width - getWidth()), grandeza.getLocationOnScreen().y - (getHeight() -1));
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		setUndecorated(true);
		
		// Botões:
			JButton remover = new JButton();
			remover.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			remover.setPreferredSize(new Dimension(25, 23));
			remover.setFocusPainted(false);
			remover.setIcon(new ImageIcon(getClass().getResource("/icones/apagar.png")));
			remover.setPressedIcon(new ImageIcon(getClass().getResource("/icones/apagarSelected.png")));
			remover.setToolTipText("Remover grandeza");
			
			JButton inverter = new JButton();
			inverter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));		
			inverter.setPreferredSize(new Dimension(25, 23));
			inverter.setFocusPainted(false);
			inverter.setIcon(new ImageIcon(getClass().getResource("/icones/inverter.png")));
			inverter.setPressedIcon(new ImageIcon(getClass().getResource("/icones/inverterSelected.png")));
			inverter.setToolTipText("Inverter cálculo");
		
		if(Janela.painelGrandezas.getComponentCount() > 3)
		{
			remover.setEnabled(true);
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
		else
		{
			remover.setEnabled(false);
		}
		
		if(grandeza.A1.getText().isBlank() == false && grandeza.B1.getText().isBlank() == false && grandeza.temIncognita == false)
		{
			inverter.setEnabled(true);
			inverter.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					grandeza.inverterGrandeza();
				}
			});
		}
		else 
		{	
			inverter.setEnabled(false);
		}
    	
		add(inverter);
		add(remover);
		setVisible(true);
	}
}