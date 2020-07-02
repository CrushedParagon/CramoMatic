public class Cram{
   public static void main(String[] args){
   try{
      Item.createAllItems();
      }catch (Exception e){
         
      }
      System.out.println(Item.printList());
   }
}