package Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JLabel;

public class ImplCalcule extends UnicastRemoteObject implements CalculRemot {




	protected ImplCalcule() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public double calcul(double chiffre1, double chiffre2, String operateur) throws RemoteException  {
		// TODO Auto-generated method stub
		double chiffre=0.0;
		if(operateur.equals("+")){
		      chiffre = chiffre1 + 
		    		  chiffre2;
		    }
		    if(operateur.equals("-")){
		      chiffre = chiffre1 - 
		    		  chiffre2;
		    }          
		    if(operateur.equals("*")){
		      chiffre = chiffre1 * 
		    		  chiffre2;
		    }     
		    if(operateur.equals("/")){
		      try{
		        chiffre = chiffre1 / 
		        		chiffre2;
		      } catch(ArithmeticException e) {
		        return 0;
		      }
		    }
		    return chiffre;
	}
	@Override
	public Double[] OperateurListener(boolean clicOperateur, double chiffre1, double chiffre2, String operateur) throws RemoteException {
		// TODO Auto-generated method stub
		Double T[]=new Double[2];
		Double chiffre=(Double)null;
		 if(clicOperateur){
		        chiffre=this.calcul(chiffre1, chiffre2, operateur);  
		        
		      }
		      else{
		    	chiffre=chiffre1;
		        clicOperateur = true;
		        //ecran.setText("0");
		      }
		//Double T[]=new Double[2];
	      //if(clicOperateur){
	        
	      //}
	      //else{
	        //chiffre1 = chiffre2;
	        //clicOperateur = true;
	      //}
	    T[0]=chiffre;
	    T[1]=clicOperateur?1.0:0.0;
	    return T;
	}

	@Override
	public String[] ChiffreListener(boolean update, String label, String str) throws RemoteException {
		// TODO Auto-generated method stub
		String T[] =new String[2];
		String str1 = str;
		boolean update1=update;
		System.out.println(label);
		 if(update){
		        update1 = false;
		      }
		      else{
		        if(!label.equals("0")){
		        	str1 = label + str;
		        }
		      }
		T[0]=str1;
		T[1]= update1==true?"1":"0";

		 return T;
	}

	
}
