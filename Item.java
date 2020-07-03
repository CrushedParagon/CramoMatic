import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Item{

   private String name;
   private String type;
   private int weight;
   private static int numItems = 0;
   private int id;
   private static Item[] globalList= new Item[5000];
   
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
   
   public static Item getFromList(int i){
      return globalList[i];
   }
}