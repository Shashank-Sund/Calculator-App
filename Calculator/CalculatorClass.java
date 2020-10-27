
package Calculator;
import java.util.*;

public class CalculatorClass {

    public ArrayList<Integer> numbers = new ArrayList<>();
    public ArrayList<Character> signs = new ArrayList<>();
    
    //Memory for original input
    public ArrayList<Integer> numbersFile = new ArrayList<>();
    public ArrayList<Character> signsFile = new ArrayList<>();
    
    public CalculatorClass(){}
    
    public void addToNumList(int num)
    {
        this.numbers.add(num);
        this.numbersFile.add(num);
    }
    
    public void addToSignList(char sign)
    {
        this.signs.add(sign);
        this.signsFile.add(sign);
    }
    
    public int calculate()
    {
        int answer = 0;
        
        for (int k=0; k<this.signs.size();k++)
        {
            int temp = 0;
            
            if(this.signs.get(k) == '*')
            {
                temp += (this.numbers.get(k) * this.numbers.get(k+1));
                this.numbers.set(k, temp);
                this.numbers.remove(k+1);
                this.signs.remove(k);
            }  
        }
        
        for (int i=0; i<signs.size();i++)
        {
            int temp2 = 0;
            
            if(signs.get(i) == '/')
            {
                temp2 += (numbers.get(i) / numbers.get(i+1));
                numbers.set(i, temp2);
                numbers.remove(i+1);
                signs.remove(i);
            }  
        }
        
        for (int j=0; j<signs.size();j++)
        {
            int temp3 = 0;
            
            if(signs.get(j) == '+')
            {
                temp3 += (numbers.get(j) + numbers.get(j+1));
                numbers.set(j, temp3);
                numbers.remove(j+1);
                signs.remove(j);
            }  
        }
        
        for (int x=0; x<signs.size();x++)
        {
            int temp4 = 0;
            
            if(signs.get(x) == '-')
            {
                temp4 += (numbers.get(x) - numbers.get(x+1));
                numbers.set(x, temp4);
                numbers.remove(x+1);
                signs.remove(x);
            }  
        }
        
        answer = this.numbers.get(0);
        return answer;
        
    }
}
