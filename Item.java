import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Item{
   
   //self explanatory instance variables
   private String name;
   private String type;
   private int weight;
   private static int numItems = 0;
   private int id;
   private final static int ITEM_COUNT=289;
   private static Item[] globalList= new Item[ITEM_COUNT];
   
   //constructor
   public Item(String name, String type, int weight){
      this.id=this.numItems;
      this.name=name;
      this.weight=weight;
      this.type=type;
      globalList[id]=this;
      numItems++;
   }
   
   //a bunch of ordinary accessor methods
   public static Item[] giveList(){
      return globalList;
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
   
   //debug command that searches the list and returns an index
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
   
   //debug command that prints all items
   public static String printList(){
      String toReturn="";
      for(int i=0; i<numItems; i++){
         toReturn+=globalList[i].id+": "+globalList[i].name+", "+globalList[i].type+", "+globalList[i].weight;
         if(i!=numItems-1) toReturn+="\n";
      }
      return toReturn;
   }
   
   //prints the name of the item
   public String toString(){
      return this.name;
   }
   
   //crates all the items so they go in the static array, reads from the txt to get the info
   public static void createAllItems() throws FileNotFoundException, IOException{
      BufferedReader items=new BufferedReader(new FileReader("ItemList.txt"));
      String line=items.readLine();
      while(line != null){
         String[] elements=line.split(";");
         String Name=elements[0].trim();
         String Type=elements[1].trim();
         int Weight=Integer.parseInt(elements[2].trim());
        new Item(Name,Type,Weight);
         line=items.readLine();
      }
      items.close();
   }
   
   //gets Item from the static array given an index
   public static Item getFromList(int i){
      return globalList[i];
      
   }
}