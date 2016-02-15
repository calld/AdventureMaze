/* Element object display
*  By: Devon Call
*/

package ways.display;

import javax.swing.*;

public class ElementDisplay extends JFrame{

   public ElementDisplay(){
      setTitle("Element Display");
      setSize(600, 400);
      setLocation(10, 200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public static void main(String[] args){
      JFrame f = new ElementDisplay();
      f.show();
   }
}