package Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CalculRemot extends Remote{
	public double calcul(double chiffre1,double chiffre2,String operateur) throws RemoteException;
	public String[] ChiffreListener(boolean update, String label,String str)  throws RemoteException;

}
