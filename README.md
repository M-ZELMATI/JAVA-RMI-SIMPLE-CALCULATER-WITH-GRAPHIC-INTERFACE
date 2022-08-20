# JAVA-RMI-SIMPLE-CALCULATOR-WITH-GRAPHIC-INTERFACE
SIMPLE APPLICATION JAVA RMI SIMPLE CALCULATOR WITH GRAPHIC INTERFACE USING ECLIPSE
## Code Interface Execution:
![image description](assets/7.PNG)<br/>

## RMi definition:
RMI (remote method invocation) est spécifique au langage Java. C’est un ensemble de classes et d’outils de mise en œuvre permettant à des objets distribués sur différents sites ou sur une même plate-forme de communiquer entre eux. On rappelle que les objets Java sont exécutés par la machine virtuelle Java appelée la JVM (Java virtual machine )

L'appel des méthodes à distance ou méthode d’objet distribuées a été introduit pour la première fois en 1997 dans Java Development Kit (JDK) 1.1 et est largement utilisé dans le calcul d'objets distribués. Il effectue l'équivalence orientée objet des appels de procédure distante

## Les composants de coté serveur et coté client
La méthode Java RMI se base sur  une  L’architecture client-serveur.  
Quelles les composants de chaque coté ? <br/>
__Coté Serveur :__ <br/>
*Une interface qui contient les méthodes qui peuvent être appelées à distance. <br/>
*L’écriture d'une classe qui implémente cette interface <br/>
*L’écriture d'une classe qui instanciera l'objet et l'enregistrer dans rmi registre.<br/>
__Coté Client :__
*Une interface qui contient les méthodes qui peuvent être appelées à distance. <br/>
*L’écriture d’une classe qui nous permettons d’accès à l'objet distant.<br/>

__Les étapes d’un appel de méthode distante :__<br/>
![image description](assets/1.PNG)<br/>

![image description](assets/2.PNG)<br/>

__les étapes que vous devrez suivre pour écrire une application Java RMI__ <br/>
![image description](assets/3.PNG)<br/>

__Les classes et les interfaces utilisés dans une application java RMI__<br/>

![image description](assets/4.PNG)<br/>
![image description](assets/5.PNG)<br/>
![image description](assets/6.PNG)<br/>

##Conclusion :
Comme nous venons de le voir, RMI permet aux objets Java et à leurs méthodes d’une application répartie de devenir distants relativement facilement.












