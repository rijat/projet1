package client;

import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import bean.MemoireSessionBeanRemote;

public class Client {

	public static void main(String[] args) {
	try {
		Context context = new InitialContext();
		afficheContext(context,"");
		Context context2 = (Context) context.lookup("11.dico.ejb");
		afficheContext(context2,"11.dico.ejb");
//		String jndiName = "4.deuxSessionBean/DeuxiemeSessionBean!bean.DeuxiemeSessionBeanRemote";
//		DeuxiemeSessionBeanRemote bean = (DeuxiemeSessionBeanRemote) context.lookup(jndiName);
		String jndiName = "MemoireSessionBean!bean.MemoireSessionBeanRemote";
		MemoireSessionBeanRemote bean = (MemoireSessionBeanRemote) context2.lookup(jndiName);
		bean.putDefinition("a", "aaa");
		bean.putDefinition("b", "bbb");
		bean.putDefinition("c", "ccc");
		// System.out.print(bean.getDefinition("a"));
		
		Scanner scanner =new Scanner(System.in);
		String requete = null;
		String name = null;
		String value = null;
		
		while (true){
			System.out.println("> ajouter / lire");
			requete = scanner.nextLine();
			
			switch(requete){
			
			case "ajouter":
				System.out.println("> nom");
				name = scanner.nextLine();
				System.out.println("> définition");
				value = scanner.nextLine();
				bean.putDefinition(name, value);
				break;
				
			case "lire":
				System.out.println("> nom");
				name = scanner.nextLine();
				System.out.println("Définition de " + name + " : "
				+ bean.getDefinition(name));
				break;
			}
			
			
		}
		
		
	} 
	catch(NamingException ne) { System.out.println(ne); }
	}
	public static void afficheContext(Context ctx, String prefix) throws NamingException {
		System.out.println("\n=================== "+prefix);
		NamingEnumeration<NameClassPair> list = ctx.list(prefix);
		while (list.hasMore()) { 
		    NameClassPair nc = list.next();
		    System.out.println(nc.getName() + "\t" + nc.getClassName());
		}
		System.out.println("====================");
	}
}
