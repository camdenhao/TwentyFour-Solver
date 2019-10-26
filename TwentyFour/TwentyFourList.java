import java.util.Scanner; 
import java.util.ArrayList;
public class TwentyFourList
{
    ArrayList<TwentyFour> list; 
    
    public TwentyFourList(ArrayList<TwentyFour> l)
    {
        list = l; 
    }
    public void solve(ArrayList<Integer> numbers, int goalSum, TwentyFour current24) //recursively goes through each combination of numbers and operators
    {
        if(numbers.size() == 1) //checks if has reached the last number
        {                       //performs operations on the ordered numbers and checks if desired sum is reached, if so adds this instance to the list
            current24.addNum(numbers.get(0));
            int counter = current24.getNums().get(0);
            boolean brokenDiv = true; //used to avoid division errors with ints 
            for(int j = 0; j < current24.getOps().size(); j++)
            {
                if(current24.getOps().get(j) == "+")
                {
                    counter += current24.getNums().get(j+1);
                }   
                else if(current24.getOps().get(j) == "-")
                {
                    counter -= current24.getNums().get(j+1);
                }  
                else if(current24.getOps().get(j) == "*")
                {
                    counter *= current24.getNums().get(j+1);
                }    
                else if(current24.getOps().get(j) == "/")
                {
                    if(counter % current24.getNums().get(j+1) == 0)
                    {
                        counter = counter / current24.getNums().get(j+1);
                    }
                    else
                    {
                        brokenDiv = false;
                    }
                }   
            }
            if(counter == goalSum && brokenDiv == true)
            {
                list.add(current24);
            }
        }
        else  //recursively obtains each possible order of numbers and operations 
        {     //takes one number, adds it to the "order", creates four different instances with each operation, then calls "solve" again
              //so that it will get each possible order starting with that number, with each possible operation combination. 
            for(int i = 0; i < numbers.size(); i++)
            {
                ArrayList<Integer> temp = new ArrayList();
                for(int j = 0; j < numbers.size(); j++)
                {
                    temp.add(numbers.get(j));
                }
                boolean canDiv = false; 
                TwentyFour added = new TwentyFour(copyNums(current24.getNums()), copyOps(current24.getOps())); //makes new instance of 24 that can
                added.addOp("+"); //add operation to this instance                                            be carried to nexts call
                added.addNum(numbers.get(i)); //add the number
                TwentyFour subbed = new TwentyFour(copyNums(current24.getNums()), copyOps(current24.getOps()));
                subbed.addOp("-");
                subbed.addNum(numbers.get(i));
                TwentyFour multed = null;
                if(goalSum % numbers.get(i) == 0) //have to check if division is possible, otherwise rounding errors like 5/4 = 1 could occur
                {
                    canDiv = true;
                    multed = new TwentyFour(copyNums(current24.getNums()), copyOps(current24.getOps()));
                    multed.addOp("*");
                    multed.addNum(numbers.get(i));
                }      
                TwentyFour dived = new TwentyFour(copyNums(current24.getNums()), copyOps(current24.getOps()));
                dived.addOp("/");
                dived.addNum(numbers.get(i));
                temp.remove(i); //"remove" this number so next call of "solve" will exclude it 
                solve(temp, goalSum, added); //
                solve(temp, goalSum, subbed);
                solve(temp, goalSum, dived); 
                if(canDiv)
                {
                    solve(temp, goalSum, multed); 
                }
                
            }
        }
        
    }
    public ArrayList<TwentyFour> getList()
    {
        return list; 
    }
    public ArrayList<Integer> copyNums(ArrayList<Integer> temp) //copies TwentyFour when creating new instances in "solve" so multiple
    {                                                           //variable names aren't pointing towards the same thing data
        ArrayList<Integer> copy = new ArrayList();
        for(int i = 0; i < temp.size(); i++)
        {
            copy.add(temp.get(i));
        }
        return copy; 
    }
    public ArrayList<String> copyOps(ArrayList<String> temp)
    {
        ArrayList<String> copy = new ArrayList();
        for(int i = 0; i < temp.size(); i++)
        {
            copy.add(temp.get(i));
        }
        return copy; 
    }
}