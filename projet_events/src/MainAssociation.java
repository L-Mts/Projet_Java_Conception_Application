import java.io.IOException;

/**
 * Classe main.
 *
 * @author Eric Cariou
 *
 */
public class MainAssociation {
  
  /**
   *fonction main.
   *
   *@param args string
   *
   */
  public static void main(String[] args) {
    System.out.println("Ca marche !");
    System.out.println("\nAppuyez sur Entr�e pour terminer le programme ...");
    try {
      System.in.read();
    } catch (IOException e) {
      System.err.println("Vous avez r�ussi � casser le clavier : " + e);
    }
  }
  
}
