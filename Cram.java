import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Cram{
   private final static double BALL_INCREMENT_BIG=6.175;
   private final static double BALL_INCREMENT_SMALL=0.25;
   //2D Array to store reward table
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
      CramGUI test=new CramGUI();
      //creates and displays GUI
      test.makeGUI();
   }
   
   public static void fillRewards() throws FileNotFoundException, IOException{
      rewards=new String[18][];//creates 2D array with 18 lines, one for each type
      //reads from the RewardList txt file to fill the array
      BufferedReader input=new BufferedReader(new FileReader("RewardList.txt"));
      String line=input.readLine();
      for(int i=0; i<18; i++){
         String[] row=line.split(";");
         rewards[i]=row;
         line=input.readLine();
      }
      input.close();
   }
   
   //debug command that prints the entire rewards array
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
   
   //main logic to find the proper reward
   public static String findReward(Item item1, Item item2, Item item3, Item item4){
      String toReturn="Something went wrong";
      String type=item1.getType();
      //for edge cases where the rewards are hard coded
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
            case "Dynite Ore": return "PP Up";
            default: break;
         }
      }
      //finds the proper row in the rewards array
      int typeIndex=-1;
      for(int i=0; i<18; i++){
         if(rewards[i][0].equals(type)){
            typeIndex=i;
            break;
         }
      }
      //sums the weights
      int sum=item1.getWeight()+item2.getWeight()+item3.getWeight()+item4.getWeight();
      //if sum=0 all the items are apricorns, goes to seperate methods
      if(sum==0){
         try{
            toReturn=apricornReward(item1,item2,item3,item4);
            }catch (Exception e){
               System.out.println(e);
            }
      }
      //sum of less than 20 indicates the first reward type (index 1 since 0 is taken by type)
      else if(sum<=20){
         toReturn=rewards[typeIndex][1];
      }
      //rest of the table follows a pattern of increasing by 10
      else{
         int remainder=sum-21;
         int index=Math.min(remainder/10+2,15);
         toReturn=rewards[typeIndex][index];
      }
      return toReturn;
   }
   
   //finds the reward given 4 apricorns
   public static String apricornReward(Item item1, Item item2, Item item3, Item item4) throws IOException, FileNotFoundException{
      BufferedReader input=new BufferedReader(new FileReader("BallBase.txt"));
      //creates an array that stores all ball types and base probabilities (mostly 0.0)
      String line=input.readLine();
      String[][] balls=new String[22][2];
      for(int i=0; i<22; i++){
         String[] row=line.trim().split("\\s");
         balls[i]=row;
         line=input.readLine();
      }
      
      input.close();
      //adjusts ball percents by the color of each apricorn
      adjustBalls(balls,item1);
      adjustBalls(balls,item2);
      adjustBalls(balls,item3);
      adjustBalls(balls,item4);
      return printBalls(balls);
   }
   
   //one huge switch statement that adjusts percents by a given amount based
   //on apricorn color. Calls addToArray since it's a String
   public static void adjustBalls(String[][] base, Item apricorn){
      switch (apricorn.getName()){
         case "Black Apricorn": addToArray(base[2],BALL_INCREMENT_BIG);//dusk ball
               addToArray(base[6],BALL_INCREMENT_BIG);//luxury ball
               addToArray(base[13],BALL_INCREMENT_SMALL);//heavy ball
               break;
         case "Blue Apricorn": addToArray(base[3],BALL_INCREMENT_BIG);//dive ball
               addToArray(base[7],BALL_INCREMENT_BIG);//net ball
               addToArray(base[14],BALL_INCREMENT_SMALL);//lure ball
               break;
         case "Green Apricorn": addToArray(base[4],BALL_INCREMENT_BIG);//ultra ball
               addToArray(base[8],BALL_INCREMENT_BIG);//nest ball
               addToArray(base[15],BALL_INCREMENT_SMALL);//friend ball
               break;
         case "Pink Apricorn": addToArray(base[4],BALL_INCREMENT_BIG);//ultra
               addToArray(base[9],BALL_INCREMENT_BIG);//heal ball
               addToArray(base[16],BALL_INCREMENT_SMALL);//love ball
               break;
         case "Red Apricorn": addToArray(base[4],BALL_INCREMENT_BIG);//ultra
               addToArray(base[10],BALL_INCREMENT_BIG);//repeat ball
               addToArray(base[17],BALL_INCREMENT_SMALL);//level ball
               break;
         case "White Apricorn": addToArray(base[5],BALL_INCREMENT_BIG);//premier ball
               addToArray(base[11],BALL_INCREMENT_BIG);//timer ball
               addToArray(base[18],BALL_INCREMENT_SMALL);//fast ball
               break;
         case "Yellow Apricorn": addToArray(base[4],BALL_INCREMENT_BIG);//ultra
               addToArray(base[12],BALL_INCREMENT_BIG);//quick ball
               addToArray(base[19],BALL_INCREMENT_SMALL);//moon ball
               break;
         default: System.out.println("?");

      }
   }
   
   //converts from String to double, adds an amount, then converts back
   //to String and replaces itself in the array
   public static void addToArray(String[] row, double add){
      double base=Double.parseDouble(row[1]);
      base+=add;
      row[1]=""+base;
   }
   
   //provides String representation of ball percents
   public static String printBalls(String[][] rate){
      String toPrint="";
      for(int i=0; i<22; i++){
         if(Double.parseDouble(rate[i][1]) != 0.0){
            toPrint+=rate[i][0]+", "+rate[i][1]+"%";
            if(i != 21){
               toPrint+="\n";
            }
         }
      }
      return toPrint;
   }
   
   //another debug print command
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

   
