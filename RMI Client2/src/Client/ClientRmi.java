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
		  private Dimension dim2 = new Dimension(50, 29);
		  private double chiffre1;
		  private boolean clicOperateur = false, update = true;
		  private String operateur = "";
	      Double T[]=new Double[2];
	      
		  private CalculRemot stub;
		  
		  public ClientRmi(){
		    this.setSize(250, 350);
		    this.setTitle("Calculette");
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    //On initialise le conteneur 
		      initComposant();
		    //On ajoute le conteneur
		    this.setContentPane(container);
		    this.setVisible(true);
		    try {
				stub=(CalculRemot)Naming.lookup("rmi://localhost:1099/CL") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		    //On parcourt le tableau initialisé
		    //afin de créer nos boutons
		    for(int i = 0; i < tab_string.length; i++){
		      tab_button[i] = new JButton(tab_string[i]);
		      tab_button[i].setPreferredSize(dim);
		      switch(i){
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
		  //Listener utilisé pour les chiffres
		  class ChiffreListener implements ActionListener {
		    public void actionPerformed(ActionEvent e){
		      //On affiche le chiffre additionnel dans le label
		    	 String str = ((JButton)e.getSource()).getText();
		    	 String T[] =new String[2]; 	  
				try {
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
		    	try {
		            double chiffre2=Double.parseDouble(ecran.getText());
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            if(clicOperateur) {
			               chiffre1 =stub.calcul(chiffre1, chiffre2, operateur);
			               ecran.setText(String.valueOf(chiffre1));
			            }
		            else {
			            clicOperateur=true;
		            }
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
		    	try {
		            double chiffre2=Double.parseDouble(ecran.getText());
		            System.out.println(chiffre2);
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            if(clicOperateur) {
			               chiffre1 =stub.calcul(chiffre1, chiffre2, operateur);
			               ecran.setText(String.valueOf(chiffre1));
			            }
			            else {
			            	clicOperateur=true;
			            }
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
		    	try {
		            double chiffre2=Double.parseDouble(ecran.getText());
		            System.out.println(chiffre2);
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            if(clicOperateur) {
		               chiffre1 =stub.calcul(chiffre1, chiffre2, operateur);
		               ecran.setText(String.valueOf(chiffre1));
		            }
		            else {
		            	clicOperateur=true;
		            }
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
		    	try {
		            double chiffre2=Double.parseDouble(ecran.getText());
		            System.out.println(chiffre2);
		            chiffre1=clicOperateur?chiffre1:chiffre2;
		            if(clicOperateur) {
			               chiffre1 =stub.calcul(chiffre1, chiffre2, operateur);
			               ecran.setText(String.valueOf(chiffre1));
			         }
			         else {
			            	clicOperateur=true;
			         }
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
