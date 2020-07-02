public class Apricorn extends Item{

   private String color;
   
   public Apricorn(String name, String type, int weight, String color){
   
      super(name,type,weight);
      this.color=color;
      
   }
   
   public String getColor(){
      return this.color;
   }
}