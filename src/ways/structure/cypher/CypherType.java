/* Enum Cypher type
*  By: Devon Call
*/

package ways.structure.cypher;

public enum CypherType{
   ANOETIC, OCCULTIC;
   
   public int limit_value(){
      if(this == CypherType.ANOETIC){
         return 1;
      }
      return 2;
   }
}