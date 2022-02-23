package Service;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


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
	public String[] ChiffreListener(boolean update, String label, String str) throws RemoteException {
		// TODO Auto-generated method stub
		String T[] =new String[2];
		String str1 = str;
		boolean update1=update;
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
