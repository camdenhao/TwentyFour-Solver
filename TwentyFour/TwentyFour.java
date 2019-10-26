import java.util.ArrayList;
public class TwentyFour
{
    ArrayList<Integer> numbers;
    ArrayList<String> operations;
    public TwentyFour(ArrayList<Integer> nums, ArrayList<String> ops) //stores ordered list of numbers and corresponding operations 
    {
        numbers = nums;
        operations = ops;
    }
    public ArrayList<Integer> getNums()
    {
        return numbers; 
    }
    public ArrayList<String> getOps()
    {
        return operations; 
    }
    public void addNum(int x)
    {
        numbers.add(0, x); //because the "solve" function in TwentyFourList works backwards, need to create our "order" by adding to the front
    }
    public void addOp(String x)
    {
        operations.add(0, x);
    }
    public String toString(int sum)
    {
        String temp = ""; 
        for(int i = 0; i < operations.size(); i++)
        {
            temp += numbers.get(i) +  " "; 
            temp += operations.get(i) + " ";
        }
        temp += numbers.get(numbers.size() - 1) + " = " + sum;
        return temp; 
    }
}