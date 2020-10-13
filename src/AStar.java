import java.util.*;

public class AStar {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the dimension of Your 3D matrix");
		int x=sc.nextInt();
		int y=sc.nextInt();
		int z=sc.nextInt();
		cell grid[][][]=new cell[x][y][z];
		System.out.print("Enter Start index and end index");
		int stx=sc.nextInt();
		int sty=sc.nextInt();
		int stz=sc.nextInt();
		int enx=sc.nextInt();
		int eny=sc.nextInt();
		int enz=sc.nextInt();
		System.out.print("Enter the number of blocked cell");
		int blocked_num=sc.nextInt();
		System.out.print("Enter the blocked shells");
		int blocked[][][]=new int[blocked_num][3][1];
		int c=0;
		while(c!=blocked_num)
		{
			blocked[c][0][0]=sc.nextInt();
			blocked[c][1][0]=sc.nextInt();
			blocked[c][2][0]=sc.nextInt();
			c++;
		}
		a_star_implementation path_finder=new a_star_implementation();
		path_finder.astar(grid,stx,sty,stz,enx,eny,enz,blocked);

	}

}
