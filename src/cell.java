
public class cell {
	int heurstic=0;
	int finalvalue=0;
	int i;
	int j;
	int k;
	cell parentCell;
    cell(int i,int j,int k)
	{
		this.i=i;
		this.j=j;
		this.k=k;
	}
    @Override
    public String toString(){
        return "["+this.i+", "+this.j+", " +this.k+"]";
    }

}
