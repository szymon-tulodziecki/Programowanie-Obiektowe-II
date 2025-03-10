package lab_2;


public class zad_1 {
  public static int silnia(int n){
     int silnia = 1;
     while(n > 1){
      silnia *= n;
      n--;
     }
     return silnia;
  }
  public static void main(String[] args){
        // Błędy kompilacji:
        /*
         *  Niezgodne typy danych etc. 
         *  
         */
         //
         
         //Błedny wynik:
        System.out.println(Math.tan(Math.toRadians(Math.PI/2)));
         
         // Wyjątek: 

         /*
          *  System.out.println(Math.abs(10 / 0));
          */

        int n = 13;
        System.out.println(silnia(n));
        //NaN:
        /*
         *  System.out.println(Math.sqrt(-64));
         System.out.println(Math.log(-10));
         */

         //Infinity:  
         /*
          *          System.out.println(5.0 / 0.0);

          */

        //-Infinity:
        /*
         *         System.out.println(-5.0 / 0.0);

         */
     }
}
