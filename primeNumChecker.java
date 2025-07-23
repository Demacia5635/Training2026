import java.util.Scanner;

public class primeNumChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a non-zero positive integer: ");
            int number = scanner.nextInt();

            if (number == 0) {
                System.out.println("The number must be a non-zero positive integer.");
            } else if(number == 1) {
                System.out.println("You entered 1, which is not indentifiable as prime!");
            }
            else if(number > 1) {
                for(int i = 2; i<= number; i++) {
                    if(number % i == 0) {
                        if(i == number) {
                            System.out.println("The number " + number + " is prime.");
                        } else {
                            System.out.println("The number " + number + " is not prime.");
                        }
                    }
                }
            }
        } finally {
            scanner.close();
        }
    }
}
