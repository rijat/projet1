package bean;

import javax.ejb.Remote;

@Remote
public interface MemoireSessionBeanRemote {
	public void putDefinition(String name, String valeur);
	public String getDefinition(String name);

}
