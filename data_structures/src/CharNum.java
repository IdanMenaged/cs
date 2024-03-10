
public class CharNum
{
    private int num;   // a number 
    private char tav;  // a chararcter

    // constructors
    public CharNum(char tav, int num)
    {
        this.tav = tav;
        this.num = num;
    }
    
    public CharNum(CharNum cn)
    {
        this.tav = cn.tav;
        this.num = cn.num;
    }

    // getters
    public char getTav()
    {
        return this.tav;
    }
    
    public int getNum()
    {
        return this.num;
    }
    
}
