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
      System.out.println(Item.searchList("Yellow Apricorn"));
      Item item1=Item.getFromList(288);
      Item item2=Item.getFromList(40);
      System.out.println(findReward(item1,item2,item1,item1));

   
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
      if(item1==item3 && item3==item4){
         switch (item1.getName()){
            case "Tiny Mushroom": return "Big Mushroom";
            case "Pearl": return "Big Pearl";
            case "Stardust": return "Star Piece";
            case "Big Mushroom": return "Balm Mushroom";
            case "Nugget": return "Big Nugget";
            case "Big Pearl": return "Pearl String";
            case "Star Piece": return "Comet Shard";
            case "Rare Candy": return "Ability Capsule";
            case "Bottle Cap": return "Gold Bottle Cap";
            default: break;
         }
      }
      int typeIndex=-1;
      for(int i=0; i<18; i++){
         if(rewards[i][0].equals(type)){
            typeIndex=i;
            break;
         }
      }
      int sum=item1.getWeight()+item2.getWeight()+item3.getWeight()+item4.getWeight();
      if(sum==0){
         try{
            toReturn=apricornReward(item1,item2,item3,item4);
            }catch (Exception e){
               System.out.println(e);
            }
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
   
   public static String apricornReward(Item item1, Item item2, Item item3, Item item4) throws IOException, FileNotFoundException{
      BufferedReader input=new BufferedReader(new FileReader("BallBase.txt"));
      String line=input.readLine();
      String[][] balls=new String[22][2];
      for(int i=0; i<22; i++){
         String[] row=line.trim().split("\\s");
         balls[i]=row;
         line=input.readLine();
      }
      
      input.close();
      adjustBalls(balls,item1);
      adjustBalls(balls,item2);
      adjustBalls(balls,item3);
      adjustBalls(balls,item4);
      return printBalls(balls);
   }
   
   public static void adjustBalls(String[][] base, Item apricorn){
      switch (apricorn.getName()){
         case "Black Apricorn": addToArray(base[2],6.175);//dusk ball
               addToArray(base[6],6.175);//luxury ball
               addToArray(base[13],0.25);//heavy ball
               break;
         case "Blue Apricorn": addToArray(base[3],6.175);//dive ball
               addToArray(base[7],6.175);//net ball
               addToArray(base[14],0.25);//lure ball
               break;
         case "Green Apricorn": addToArray(base[4],6.175);//ultra ball
               addToArray(base[8],6.175);//nest ball
               addToArray(base[15],0.25);//friend ball
               break;
         case "Pink Apricorn": addToArray(base[4],6.175);//ultra
               addToArray(base[9],6.175);//heal ball
               addToArray(base[16],0.25);//love ball
               break;
         case "Red Apricorn": addToArray(base[4],6.175);//ultra
               addToArray(base[10],6.175);//repeat ball
               addToArray(base[17],0.25);//level ball
               break;
         case "White Apricorn": addToArray(base[5],6.175);//premier ball
               addToArray(base[11],6.175);//timer ball
               addToArray(base[18],0.25);//fast ball
               break;
         case "Yellow Apricorn": addToArray(base[4],6.175);//ultra
               addToArray(base[12],6.175);//quick ball
               addToArray(base[19],0.25);//moon ball
               break;
         default: System.out.println("?");

      }
   }
   
   public static void addToArray(String[] row, double add){
      double base=Double.parseDouble(row[1]);
      base+=add;
      row[1]=""+base;
   }
   
   public static String printBalls(String[][] rate){
      String toPrint="";
      for(int i=0; i<22; i++){
         if(Double.parseDouble(rate[i][1]) != 0.0){
            toPrint+=rate[i][0]+","+rate[i][1]+"%";
            if(i != 21){
               toPrint+="\n";
            }
         }
      }
      return toPrint;
   }
   
   public static void printArray(String[][] array){
      String toPrint="";
      for(int i=0; i<array.length; i++){
         for(int j=0; j<array[i].length; j++){
            toPrint+=array[i][j];
         }
      }
      System.out.println(toPrint);
   }
}

   
