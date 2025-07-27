import java.util.Scanner;

public class Second_lesson {

    public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);
         //question 1
         System.out.println("volt");
         double volt = scanner.nextDouble();
         int scaller= 0;
         while(volt<12.6)
         {
            volt+=0.06+(0.05)*(13-volt);
            scaller++;
            
         }
         System.out.println("number of scallers nessesery: " + scaller);
         while(volt<12.6)
         {
            if(scaller%3==0)
            {
                System.out.println(volt);
            }
           
         }

        //question 2
         double[] array= new double[1000];
         for(int i=0;i<array.length;i++)
         {
            array[i]=Math.random()*1000;
         }
         double max=array[0];
         double min= array[0];
         for(int i=0;i<array.length;i++)
         {
            if(array[i]>max)
            {
                max=array[i];
            }
            if(array[i]<min)
            {
                min=array[i];
            }
        }
            System.out.println("max: " + max); 
            System.out.println("min: " + min);
            double sum=0;
            for(int i=0;i<array.length;i++)
         {
            sum+=array[i];
         }
         System.out.println("the average is: " + sum/array.length);
         System.out.println("the average without max and min number: " + (sum-min-max)/array.length);
         int count=0;
         for(int i=0;i<array.length;i++)
         {
            if(array[i]>1.5||array[i]<sum*0.25)
            {
                count++;
            }       
         }


            System.out.println("the number of values answering the condition :"+ count);
            double[] newArray= new double[count];
           for (int i=0; i<array.length;i++)
            {
                for (int j=0;j<newArray.length;j++)
                {
                if(array[i]>1.5||array[i]<sum*0.25)
                {
                    newArray[j]=array[i];
                }
                }
            }
            for (int j=0;j<newArray.length;j++)
            {
                System.out.println(newArray[j]);
            }
            
         scanner.close();
    }
}
