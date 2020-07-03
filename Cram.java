import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Cram{
   private static String rewards[][];
   public static void main(String[] args){
      try{
        Item.createAllItems();
      }catch (Exception e){
         
      }
      
      try{
         fillRewards();
      }catch (Exception e){
         System.out.println(e);
      }

   
   }
   
   public static void fillRewards() throws FileNotFoundException, IOException{
      rewards=new String[18][];
      BufferedReader input=new BufferedReader(new FileReader("RewardList.txt"));
      String line=input.readLine();
      for(int i=0; i<18; i++){
         String[] row=line.split(";");
         rewards[i]=row;
         line=input.readLine();
      }
   }
   
   public static String printRewards(){
      String toPrint="";
      for(int i=0; i<rewards.length; i++){
         toPrint+="\n";
         for(int j=0; j<rewards[i].length; j++){
            toPrint+=rewards[i][j];
            if(j!=rewards[i].length-1){
               toPrint+=", ";
            }
         }
      }
      return toPrint;
   }
   
   public static String findReward(Item item1, Item item2, Item item3, Item item4){
      String toReturn="Something went wrong";
      String type=item1.getType();
      int typeIndex=-1;
      for(int i=0; i<18; i++){
         if(rewards[i][0].equals(type)){
            typeIndex=i;
            break;
         }
      }
      int sum=item1.getWeight()+item2.getWeight()+item3.getWeight()+item4.getWeight();
      if(sum==0){
      
      }
      else if(sum<=20){
         toReturn=rewards[typeIndex][1];
      }
      else{
         int remainder=sum-21;
         int index=remainder/10+2;
         toReturn=rewards[typeIndex][index];
      }
      return toReturn;
   }
}