import java.util.*;
import java.io.*;
class puzzle{
    static void println(int inp)
    {
        System.out.print(inp);
        System.out.print("\r\n");        
    }
    static void println(String inp)
    {
        System.out.print(inp);
        System.out.print("\r\n");
    }
    static void println()
    {
     System.out.print("\r\n");    
    }
    static int[][] matrix_fill(int row,int col){
    int n=1;
    int [][]mat =new int[row][col];
    for(int i=0;i<row;i++)
    {
        for(int j=0;j<col;j++)
        {
            
            mat[i][j]=n++;
            if(n>(row*col)/2)
            {
                n=1;
            }
        }
    }
        return mat;
    }
    static void display_board(int [][]mat,boolean [][]isOpen,int cur_row,int cur_col)
    {
        println("Memory Game");
        println("Use arrow keys to move");
        println("Use arrow keys to move");
        
        for(int row=0;row<4;row++)
        {  println("+---+---+---+---+");
            for(int col=0;col<4;col++)
            {
                System.out.print("|");
                if(isOpen[row][col]==false)
                {
                    if(row==cur_row&&col==cur_col)
                    {
                        System.out.print("[ ]");
                    }
                    else
                    System.out.print("   ");
                }
                else{
                 if(row==cur_row&&col==cur_col)
                 {
                     System.out.printf("[%2d]",mat[row][col]);
                 }
                 else{
                     System.out.printf("2%d ",mat[row][col]);
                 }}
                 
                
            }
            System.out.print("|");
            System.out.println();
        }
        println("+----+----+----+");
    }
static void enableRawMode() throws IOException, InterruptedException {
        // disable canonical mode, disable echo
        Runtime.getRuntime().exec(new String[]{"sh","-c","stty raw -echo </dev/tty"}).waitFor();
    }

    static void disableRawMode() throws IOException, InterruptedException {
        // restore terminal
        Runtime.getRuntime().exec(new String[]{"sh","-c","stty sane </dev/tty"}).waitFor();
    }
    static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    static void game_play(int[][] mat,boolean[][] isOpen) throws Exception
    {
        int work_row=0;
        int work_col=0;
        int cal_row;
        int cal_col;
        int row,col;
        while (true){
            clear();
            display_board(mat,isOpen,work_row,work_col);
            int inp1=System.in.read();
            if(inp1==27)
            {
                int inp2=System.in.read();
                if(inp2==91)
                {
                    int inp3=System.in.read();
                    int [][] dir={{-1,0},{1,0},{0,1},{0,-1}};
                    int dir_ind=inp3-65;
                    cal_row=work_row+dir[dir_ind][0];
                    cal_col=work_col+dir[dir_ind][1];
                    if(cal_row>=0&&cal_row<4&&cal_col>=0&&cal_col<4)
                    {
                        work_row=cal_row;
                        work_col=cal_col;
                    }
                }
            }
        
        else{
            if(inp1==' ')
            {
                isOpen[work_row][work_col]=true;
            }
            else if(inp1=='s'||inp1=='S')
            break;
        }}
        
    }
    public static void main(String[] args) throws Exception
    {
        int [][]mat=new int[4][4];
        boolean [][] isOpen= new boolean[4][4];
        enableRawMode();
        mat=matrix_fill(4,4);
        display_board(mat,isOpen,0,0);
        game_play(mat,isOpen);
        disableRawMode();
    }
}