import java.util.PriorityQueue;

public class a_star_implementation {
	
	static int straight=100;
	static int diagonal2=140;
	static int diagonal3=172;
	
	static boolean closed[][][];
	static PriorityQueue<cell> open =new PriorityQueue<>((Object o1, Object o2) -> {
        cell c1 = (cell)o1;
        cell c2 = (cell)o2;

        return c1.finalvalue<c2.finalvalue?-1:
                c1.finalvalue>c2.finalvalue?1:0;
});
    
	public void astar(cell[][][] grid, int stx, int sty, int stz, int enx, int eny, int enz, int[][][] blocked) 
	{
        closed=new boolean[grid.length][grid[0].length][grid[0][0].length];
        for(int i=0;i<grid.length;++i){
           for(int j=0;j<grid[0].length;++j){
        	   for(int k=0;k<grid[0][0].length;++k) {
               grid[i][j][k] = new cell(i,j,k);
               grid[i][j][k].heurstic = Math.abs(i-enx)+Math.abs(j-eny)+ Math.abs(k-enz);
               
              }
           }
         }
        grid[stx][sty][stz].finalvalue = 0;
        for(int i=0;i<blocked.length;i++)
        {
        	grid[blocked[i][0][0]][blocked[i][1][0]][blocked[i][2][0]]=null;
        }
        a_star_solver(grid,stx,sty,stz,enx,eny,enz);
        
        
        if(closed[enx][eny][enz]){
        	
             System.out.println("Path: ");
             System.out.println();
             cell current = grid[enx][eny][enz];
             System.out.print(current);
             
             while(current.parentCell!=null)
             {
                 System.out.print(" -> "+current.parentCell);
                 current = current.parentCell;
             } 
            System.out.println();
            System.out.println();
            
          
        }
        else System.out.println("No possible path");

}

	private void a_star_solver(cell[][][] grid, int stx, int sty, int stz, int enx, int eny, int enz) {
		
		open.add(grid[stx][sty][stz]);
		cell current;
		while(true)
		{
			
			current=open.poll();
			if(current==null){
				break;
			}
			
			closed[current.i][current.j][current.k]=true;
			if(current.equals(grid[enx][eny][enz])){
                return; 
            }
			cell t;
			if(current.i-1>=0)
			{
				t = grid[current.i-1][current.j][current.k];
                checkAndUpdateCost(current, t, current.finalvalue+straight); 
                if(current.k-1>=0)
                {
                t = grid[current.i-1][current.j][current.k-1];
                checkAndUpdateCost(current, t, current.finalvalue+diagonal2);
                }
                if(current.k+1<grid[0][0].length) {
                t = grid[current.i-1][current.j][current.k+1];
                checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                }

                if(current.j-1>=0)
                {                      
                    t = grid[current.i-1][current.j-1][current.k];
                    checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                    if(current.k-1>=0)
                    {
                    	 t = grid[current.i-1][current.j-1][current.k-1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                    if(current.k+1<grid[0][0].length)
                    {
                    	 t = grid[current.i-1][current.j-1][current.k+1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                }
                if(current.j+1<grid[0].length)
                {                      
                    t = grid[current.i-1][current.j+1][current.k];
                    checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                    if(current.k-1>=0)
                    {
                    	 t = grid[current.i-1][current.j+1][current.k-1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                    if(current.k+1<grid[0][0].length)
                    {
                    	 t = grid[current.i-1][current.j+1][current.k+1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                }
             }
			if(current.i+1<grid.length)
			{
				t = grid[current.i+1][current.j][current.k];
                checkAndUpdateCost(current, t, current.finalvalue+straight); 
                if(current.k-1>=0)
                {
                t = grid[current.i+1][current.j][current.k-1];
                checkAndUpdateCost(current, t, current.finalvalue+diagonal2);
                }
                if(current.k+1<grid[0][0].length) {
                t = grid[current.i+1][current.j][current.k+1];
                checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                }

                if(current.j-1>=0)
                {                      
                    t = grid[current.i+1][current.j-1][current.k];
                    checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                    if(current.k-1>=0)
                    {
                    	 t = grid[current.i+1][current.j-1][current.k-1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                    if(current.k+1<grid[0][0].length)
                    {
                    	 t = grid[current.i+1][current.j-1][current.k+1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                }
                if(current.j+1<grid[0].length)
                {                      
                    t = grid[current.i+1][current.j+1][current.k];
                    checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                    if(current.k-1>=0)
                    {
                    	 t = grid[current.i+1][current.j+1][current.k-1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                    if(current.k+1<grid[0][0].length)
                    {
                    	 t = grid[current.i+1][current.j+1][current.k+1];
                         checkAndUpdateCost(current, t, current.finalvalue+diagonal3); 
                    }
                }
             }
			if(current.j-1>=0){                      
				t = grid[current.i][current.j-1][current.k];
                checkAndUpdateCost(current, t, current.finalvalue+straight);
                if(current.k-1>=0)
                {
                	 t = grid[current.i][current.j-1][current.k-1];
                     checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                }
                if(current.k+1<grid[0][0].length)
                {
                	 t = grid[current.i][current.j-1][current.k+1];
                     checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                }
            }
			if(current.j+1<grid[0].length){                      
				t = grid[current.i][current.j+1][current.k];
                checkAndUpdateCost(current, t, current.finalvalue+straight);
                if(current.k-1>=0)
                {
                	 t = grid[current.i][current.j+1][current.k-1];
                     checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                }
                if(current.k+1<grid[0][0].length)
                {
                	 t = grid[current.i][current.j+1][current.k+1];
                     checkAndUpdateCost(current, t, current.finalvalue+diagonal2); 
                }
             }
			 if(current.k-1>=0)
			 {
		        t = grid[current.i][current.j][current.k-1];
                checkAndUpdateCost(current, t, current.finalvalue+straight);
		     }
			 if(current.k+1<grid[0][0].length)
			 {
			 	t = grid[current.i][current.j][current.k+1];
                checkAndUpdateCost(current, t, current.finalvalue+straight);
		     }
			 
		  }

       }
		
		
    private void checkAndUpdateCost(cell current, cell t, int cost) {
	
    	  if(t == null || closed[t.i][t.j][t.k])
    		  return;
    	  
          int t_final_cost = t.heurstic+cost;
          
          boolean inOpen = open.contains(t);
          if(!inOpen || t_final_cost<t.finalvalue){
        	  
              t.finalvalue = t_final_cost;
              t.parentCell = current;
              
              if(!inOpen)
            	  open.add(t);
          }
	}
}
