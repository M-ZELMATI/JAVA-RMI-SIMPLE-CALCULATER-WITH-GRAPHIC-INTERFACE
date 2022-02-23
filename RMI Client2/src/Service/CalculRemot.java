package Service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface CalculRemot extends Remote{
	public double calcul(double chiffre1,double chiffre2,String operateur) throws RemoteException;
	public Double[] OperateurListener (boolean clicOperateur,double chiffre1,double chiffre2,String operateur) throws RemoteException;
	public String[] ChiffreListener(boolean update, String label,String str)  throws RemoteException;

}
