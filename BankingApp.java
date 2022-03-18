/**
* ECS414U - Object Oriented Programming
* Queen Mary University of London, 2021/22.
* <p>
* Week 5 lab session.
*/

import java.awt.*;
import java.awt.event.*;


public class BankingApp extends Frame{

    /*
     * We will use this to print messages to the user.
     */
    private static TextArea infoArea = new TextArea("BankingApp 0.5");

    public static void print(String text){
	infoArea.setText(text);
    }    
    //---

    
    private Agent agent;
    private Panel clientButtonsPanel;


    /**
     * This method prints the names of all clients.
     */
    public void printClients(){
	String text = agent.getListOfClientNames();
	print(text);
    }

    /**
     * This method prints the information of the client with the given index.
     */
    public void printClientInfo(int index){
	String text = agent.getClientInfo(index);
	print(text);	
    }
    
    /**
     * This method takes all the necessary steps when a client is added. 
     */
    public void addClient(String name){
      agent.addClient(new Client(name));

	  // Uncomment for R3
	  int numClients = agent.getNumberOfClients();
	  Button btn = new Button("Client " + numClients);
	  final int NUM = agent.getNumberOfClients();
	  btn.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	      printClientInfo(NUM - 1);
	    }
	  });
	clientButtonsPanel.add(btn);
	this.setVisible(true); // Just to refresh the frame, so that the button shows up
    }

	public void addButton(Client c, int num){  
		Button btn = new Button("Client " + (num+1));
		btn.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			printClientInfo(num);
		  }
		});
	  clientButtonsPanel.add(btn);
	  this.setVisible(true); // Just to refresh the frame, so that the button shows up
	  }
  

	public void removeClient(String name){
		int index = agent.giveClientIndex(name);
		if ((index + 1) > agent.getNumberOfClients() ){
			agent.removeClient(index);
			clientButtonsPanel.remove(index);
		}
		else if (index == -1){
			print("Error. Client not found.");
		}
		else{
			agent.removeClient(index);
			clientButtonsPanel.remove(index);
			for(int i = 0; i < (agent.getNumberOfClients()-index); i++){
				int num = index+i;
				clientButtonsPanel.remove(num);
				addButton(agent.getClient(num),num);
			}
		}

	}
    
    public BankingApp(){

		this.agent = new Agent();	
		this.setLayout(new FlowLayout());
	
	// Make this button work
        Button reportButton=new Button("Print client list");
        reportButton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            printClients();
          } 
          });
        this.add(reportButton); 
        

	// Make this button work
        Button addClientButton=new Button("Add client");
		addClientButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {		
				Prompt acp = new Prompt();

				//...
				print("Give client name:");
				final TextField TF = new TextField(22);
				acp.add(TF);
				acp.addSubmitListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
							addClient(TF.getText());
				}
				});


				acp.activate();
			}
			});

			this.add(addClientButton); 
		
		// Make this button work
		Button depositButton = new Button("Deposit");
		depositButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			Prompt p = new Prompt();
			print("Enter the name of the client and the amount you want to deposit:");
			final TextField NAME = new TextField(10);
			p.add(NAME);
			final TextField AMOUNT = new TextField(10);
			p.add(AMOUNT);
			p.addSubmitListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				if (!agent.deposit(NAME.getText(), Integer.parseInt(AMOUNT.getText()))){
					print("Error. Client not found.");
				}
				else{
					print("The money has been successfully deposited.");
				}
				}
			});
				
			p.activate();
			}
		});
		this.add(depositButton);

		Button withdrawButton = new Button("Withdraw");
		withdrawButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Prompt w = new Prompt();
				print("Give the name and the client and the amount you want to withdraw from their account.");
				final TextField NAME = new TextField(20);	
				w.add(NAME);
				final TextField AMOUNT = new TextField(20);
				w.add(AMOUNT);
				w.addSubmitListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (!agent.withdraw(NAME.getText(),Integer.parseInt(AMOUNT.getText()))){
							print("Error. Client not found.");
						}
						else{
							print("Successful withdrawal.");
						}
					}});
				w.activate();
			}});

			this.add(withdrawButton);

			Button removeButton = new Button("Remove");
			removeButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Prompt r = new Prompt();
					print("Give the name of the client you want to remove:");
					final TextField NAME = new TextField(20);	
					r.add(NAME);
					r.addSubmitListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							removeClient(NAME.getText());
						}
					});
					r.activate();
				}
			});

			this.add(removeButton);



	// Output console
	infoArea.setEditable(false);
	this.add(infoArea);	

	// Client button panel
	// Uncomment for R3	
	clientButtonsPanel = new Panel();
	clientButtonsPanel.setLayout(new GridLayout(0,1));
	clientButtonsPanel.setVisible(true);
	this.add(clientButtonsPanel);

	
	// We add a couple of clients of testing purposes
	this.addClient("Alice Alison");
	this.addClient("Bob Robertson");

	// This is just so the X button closes our app
	WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc); 

	this.setSize(500,500);// Self explanatory
	this.setLocationRelativeTo(null); // Centers the window on the screen
	this.setVisible(true);// Self explanatory

    }
    
    public static void main(String[] args){
	new BankingApp();
    }
}
