import java.util.Scanner;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args)
    {
        Scanner x = new Scanner(System.in); 
        System.out.println("Enter numbers with spaces in between each (greater than 0)");
        String response = x.nextLine(); 
        System.out.println("Enter goal sum");
        int response2 = x.nextInt();
        String[] tempNums = response.split(" ");
        ArrayList<Integer> nums = new ArrayList(); 
        for(int i = 0; i < tempNums.length; i++)
        {
            nums.add(Integer.parseInt(tempNums[i]));
        }
        ArrayList<TwentyFour> temp = new ArrayList(); 
        TwentyFourList solutions = new TwentyFourList(temp);
        TwentyFour temp24 = new TwentyFour(new ArrayList<Integer>(), new ArrayList<String>());
        solutions.solve(nums, response2, temp24);
        System.out.println("The solutions are:");
        for(int i = 0; i < solutions.getList().size(); i++)
        {
            System.out.println(solutions.getList().get(i).toString(response2));
        }
    }
}