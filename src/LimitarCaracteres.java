import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitarCaracteres extends PlainDocument
{
	private int limite;
	private boolean estaAtivado = true;
	
	public LimitarCaracteres(int limitacao)
	{
		limite = limitacao;
	}
	
	public void ativarDoc(boolean bool)
	{
		this.estaAtivado = bool;
	}
	
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException
	{
		if(str == null)
		{
			return;
		}
		else if((getLength() + str.length()) <= limite)
		{	
			if(limite == 30)
			{
				if(str.equals(","))
					str = ".";
				
				if(getLength() == 0 && str.equals("."))
					return;
				
				if(str.matches("[0-9.]") == false && estaAtivado == true)
					return;
				
				//	Impede a inserção de mais de duas vírgulas
				for(int i = 0; i <= getLength(); i++)
				{
					if(getText(i, 1).equals(str) && str.equals("."))
						return;
				}
			}
			
			super.insertString(offset, str, set);
		}
	}
}