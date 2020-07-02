public class Item{

   private String name;
   private String type;
   private int weight;
   private static int numItems = 0;
   private int id;
   private static Item[] globalList= new Item[500];
   
   public Item(String name, String type, int weight){
      this.id=this.numItems;
      this.name=name;
      this.weight=weight;
      this.type=type;
      globalList[numItems]=this;
      numItems++;
   }
   
   public String getName(){
      return this.name;
   }
   
   public String getType(){
      return this.type;
   }
   
   public int getWeight(){
      return this.weight;
   }
   
   public static int searchList(String name){
      int toReturn=-1;
      for(int i=0; i<numItems; i++){
         if(globalList[i].name.equals(name)){
            toReturn=i;
            break;
         }
      }
      return toReturn;
   }
   
   public static String printList(){
      String toReturn="";
      for(int i=0; i<numItems; i++){
         toReturn+=globalList[i].toString();
         if(i!=numItems-1) toReturn+="\n";
      }
      return toReturn;
   }
   
   public String toString(){
      return ""+this.id+": "+this.name+", "+this.type+", "+this.weight;
   }
   
   public static void createAllItems(){
      new Item("Bug Memory", "Bug", 40);
   }
}