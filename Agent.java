import java.util.ArrayList;
import java.util.Iterator;
public class Agent{

    private ArrayList<Client> clients;

    public Agent(){
	clients = new ArrayList<Client>();
    }

    public int getNumberOfClients(){
	return clients.size();
    }

    public String getClientInfo(int clientNumber){
    	Client c = clients.get(clientNumber);
	String text = "";
	text += "Name: " + c.getName() + "\n";
	text += "Funds: " + c.getFunds() + "\n";
	return text;
    }
    
    public String getListOfClientNames(){
	String text = "";
	Iterator<Client> it = clients.iterator();
	while (it.hasNext()){
	    Client c = it.next();
	    text += c.getName() + "\n";
	}
	return text;
    }
    
    public void addClient(Client c){
	clients.add(c);
    }

	public void removeClient(int index){
		clients.remove(index);
	}

	public int giveClientIndex(String name){
		int index = -1;
		Iterator<Client> it = clients.iterator();
		while (it.hasNext()){
	    	Client c = it.next();
	    	if (c.getName().equals(name)){
			index =clients.indexOf(c);
			return index;
		    }
		}
		return index;
	}

	public Client getClient(int index){
		return clients.get(index);
	}
   
    public boolean deposit(String clientName, int amount){
	Iterator<Client> it = clients.iterator();
	boolean found = false;	
	while (it.hasNext()){
	    Client c = it.next();
	    if (c.getName().equals(clientName)){
		found = true;
		c.deposit(amount);
	    }
	}
	return found;
    }

	public boolean withdraw(String clientName, int amount){
		Iterator<Client> it = clients.iterator();
		boolean found = false;	
		while (it.hasNext()){
			Client c = it.next();
			if (c.getName().equals(clientName)){
			found = true;
			c.withdraw(amount);
			}
		}
		return found;
	}
	
}
