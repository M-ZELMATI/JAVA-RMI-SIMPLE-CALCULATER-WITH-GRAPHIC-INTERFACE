package Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Service.CalculRemot;

	public class ClientRmi extends JFrame {
		

		  private JPanel container = new JPanel();
		  //Tableau stockant les éléments à afficher dans la calculatrice
		  String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/",};
		  //Un bouton par élément à afficher
		  JButton[] tab_button = new JButton[tab_string.length];
		  private JLabel ecran = new JLabel();
		  private Dimension dim = new Dimension(50, 40);
		  private Dimension dim2 = new Dimension(50, 31);
		  private double chiffre1;
		  private boolean clicOperateur = false, update = true;
		  private String operateur = "";
		  private CalculRemot stub;
		  public ClientRmi(){
				

		    this.setSize(250, 350);
		    this.setTitle("Calculette");
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    //On initialise le conteneur avec tous les composants
		      initComposant();
		      //On ajoute le conteneur
		    this.setContentPane(container);
		    this.setVisible(true);
		  }

		  private void initComposant(){
		    //On définit la police d'écriture à utiliser
		    Font police = new Font("Arial", Font.BOLD, 20);
		    ecran = new JLabel("0");
		    ecran.setFont(police);
		    //On aligne les informations à droite dans le JLabel
		    ecran.setHorizontalAlignment(JLabel.RIGHT);
		    ecran.setPreferredSize(new Dimension(220, 20));
		    JPanel operateur = new JPanel();      
		    operateur.setPreferredSize(new Dimension(55, 225));
		    JPanel chiffre = new JPanel();
		    chiffre.setPreferredSize(new Dimension(165, 225));
		    JPanel panEcran = new JPanel();
		    panEcran.setPreferredSize(new Dimension(220, 30));
		    try {
				CalculRemot stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
		    	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			} 
		    //On parcourt le tableau initialisé
		    //afin de créer nos boutons
		    for(int i = 0; i < tab_string.length; i++){
		      tab_button[i] = new JButton(tab_string[i]);
		      tab_button[i].setPreferredSize(dim);
		      switch(i){
		        //Pour chaque élément situé à la fin du tableau
		        //et qui n'est pas un chiffre
		        //on définit le comportement à avoir grâce à un listener
		        case 11 :
		          tab_button[i].addActionListener(new EgalListener());
		          chiffre.add(tab_button[i]);
		          break;
		        case 12 :
		          tab_button[i].setForeground(Color.red);
		          tab_button[i].addActionListener(new ResetListener());
		          operateur.add(tab_button[i]);
		          break;
		        case 13 :
		          tab_button[i].addActionListener(new PlusListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 14 :
		          tab_button[i].addActionListener(new MoinsListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;    
		        case 15 :   
		          tab_button[i].addActionListener(new MultiListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 16 :
		          tab_button[i].addActionListener(new DivListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        default :
		          //Par défaut, ce sont les premiers éléments du tableau
		          //donc des chiffres, on affecte alors le bon listener
		          chiffre.add(tab_button[i]);
		          tab_button[i].addActionListener(new ChiffreListener());
		          break;
		      }
		    }
		    panEcran.add(ecran);
		    panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		    container.add(panEcran, BorderLayout.NORTH);
		    container.add(chiffre, BorderLayout.CENTER);
		    container.add(operateur, BorderLayout.EAST);
		   
		    
		  }

		  //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
		 

		  //Listener utilisé pour les chiffres
		  //Permet de stocker les chiffres et de les afficher
		  
		  class ChiffreListener implements ActionListener {
		    public void actionPerformed(ActionEvent e){
		      //On affiche le chiffre additionnel dans le label
		    	 String str = ((JButton)e.getSource()).getText();
		    	 System.out.println(str);
		    	 String T[] =new String[2];
		    	  
				try {
					CalculRemot stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
                    T=stub.ChiffreListener(update, ecran.getText() , str);
                  
					ecran.setText(T[0]);
					update=T[1].equals("1")?true:false;
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    

		      
		    	  
				  
			
		    }
		  }

		  //Listener affecté au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      try {
				CalculRemot stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
				if(clicOperateur){
				   chiffre1=stub.calcul(chiffre1, Double.valueOf(ecran.getText()).doubleValue(), operateur);
				   ecran.setText(""+chiffre1);
				   }
				else 
				   ecran.setText("0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      update = true;
		      clicOperateur = false;
		    }
		  }

		  //Listener affecté au bouton +
		  class PlusListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	Double T[]=new Double[2];
		    	try {
					CalculRemot stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
		            double chiffre2=Double.parseDouble(ecran.getText());
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            T=stub.OperateurListener(clicOperateur, chiffre1, chiffre2, operateur);
		            chiffre1=T[0];
		            if(clicOperateur) 
					  ecran.setText(String.valueOf(T[0]));
		              clicOperateur=(T[1]==1.0)?true:false;
				      operateur = "+";
				      update = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("call");
				}
		      
		    }
		  }

		  //Listener affecté au bouton -
		  class MoinsListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	Double T[]=new Double[2];
		    	try {
					CalculRemot stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
		            double chiffre2=Double.parseDouble(ecran.getText());
		            System.out.println(chiffre2);
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            T=stub.OperateurListener(clicOperateur, chiffre1, chiffre2, operateur);
		            System.out.println("T[0]:"+T[0]);
		            chiffre1=T[0];
		            if(clicOperateur) 
					  ecran.setText(String.valueOf(T[0]));
		            clicOperateur=(T[1]==1.0)?true:false;
				      operateur = "-";
				      update = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("call");
				}
		    }
		  }

		  //Listener affecté au bouton *
		  class MultiListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	Double T[]=new Double[2];
		    	try {
					CalculRemot stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
		            double chiffre2=Double.parseDouble(ecran.getText());
		            System.out.println(chiffre2);
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            T=stub.OperateurListener(clicOperateur, chiffre1, chiffre2, operateur);
		            System.out.println("T[0]:"+T[0]);
		            chiffre1=T[0];
		            if(clicOperateur) 
					  ecran.setText(String.valueOf(T[0]));
		            clicOperateur=(T[1]==1.0)?true:false;
				      operateur = "*";
				      update = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("call");
				}
		    }
		  }

		  //Listener affecté au bouton /
		  class DivListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	Double T[]=new Double[2];
		    	try {
					CalculRemot stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
		            double chiffre2=Double.parseDouble(ecran.getText());
		            System.out.println(chiffre2);
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            T=stub.OperateurListener(clicOperateur, chiffre1, chiffre2, operateur);
		            System.out.println("T[0]:"+T[0]);
		            chiffre1=T[0];
		            if(clicOperateur) 
					  ecran.setText(String.valueOf(T[0]));
		            clicOperateur=(T[1]==1.0)?true:false;
				      operateur = "/";
				      update = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("call");
				}
		    }
		  }

		  //Listener affecté au bouton de remise à zéro
		  class ResetListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      clicOperateur = false;
		      update = true;
		      chiffre1 = 0;
		      operateur = "";
		      ecran.setText("");
		    }
		  }      


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			ClientRmi CR=new ClientRmi();
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
