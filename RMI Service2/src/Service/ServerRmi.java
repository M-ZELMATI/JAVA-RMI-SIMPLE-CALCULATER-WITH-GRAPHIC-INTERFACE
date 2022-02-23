package Service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import Service.ImplCalcule;
public class ServerRmi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          try {
			LocateRegistry.createRegistry(1099);
			 ImplCalcule odCalcule= new ImplCalcule();
	          Naming.rebind("rmi://localhost:1099/CL",odCalcule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}

}
