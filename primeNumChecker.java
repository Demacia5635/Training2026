import java.util.Scanner;

public class primeNumChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a non-zero positive integer: ");
            int number = scanner.nextInt();
            boolean isnotprimeassum = true;

            if (number == 0) {
                System.out.println("The number must be a non-zero positive integer.");
            } else if(number == 1) {
                System.out.println("You entered 1, which is not indentifiable as prime!");
            }
            else if(number > 1) {
                int[] divisors = new int[number];
                int divisorCount = 0;

                for (int i = 1; i <= number; i++) {
                    if (number % i == 0) {
                        divisors[divisorCount++] = i;
                    }
                }

                System.out.print("The number " + number + " is divisible by: ");
                for (int i = 0; i < divisorCount; i++) {
                    System.out.print(divisors[i] + (i < divisorCount - 1 ? ", " : "\n"));
                }
                for(int i = 2; i<= number; i++) {
                    if(number % i == 0 && isnotprimeassum == true) {
                        if(i == number) {
                            System.out.println("The number " + number + " is prime.");
                        } else {
                            System.out.println("The number " + number + " is not prime.");
                            isnotprimeassum = false;
                        }
                    }
                }
            }
        } finally {
            scanner.close();
        }
    }
}
