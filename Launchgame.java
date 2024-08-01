package TictacToeProject;
import java.util.Scanner;
import java.util.Random;
class TicTacToe
{
	static char [][]board;
	public TicTacToe()
	{
		board =new char[3][3];
		initBoard();
	}
	void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j]=' ';
			}
		}
	}
	static void dispBoard()

	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j] +" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
		
	}
	static void placeMark(int row,int col,char mark)
	{
		if(row>=0&&row<=2&&col>=0&&col<=2)
		{
			board[row][col]=mark;
		}
		else
		{
			System.out.println("Invalid input");
		}
	}
	static boolean checkcolWin()
	{
		for(int j=0;j<=2;j++)
		{
			if( board[0][j]!=' '&&board[0][j]==board[1][j]&&board[1][j]==board[2][j])
			{
			 return true;	
			}
		}
		return false;

	}
	static boolean checkrowWin()
	{
		for(int i=0;i<=2;i++)
		{
			if(board[i][0]!=' '&&board[i][0]==board[i][1]&&board[i][1]==board[i][2])
			{
			 return true;	
			}
		}
		return false;

	}
    static boolean checkdiagWin()
    {
    	if((board[0][0]!=' '&&board[0][0]==board[1][1]&&board[1][1]==board[2][2])||(board[0][2]!=' '&&board[0][2]==board[1][1]&&board[1][1]==board[2][0]))
    	{
    		return true;
    	}
    	return false;
    }
    static boolean checkDraw()
    {
    	for(int i=0;i<=2;i++)
    	{
    		for(int j=0;j<=2;j++)
    		{
    			if(board[i][j]==' ')
    			{
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
abstract class Player
{
	String Name;
	char mark;
	abstract void makeMove();
	boolean isValidmove(int row,int col)
	{
		if(row>=0&&row<=2&&col>=0&&col<=2)
		{
			if(TicTacToe.board[row][col]==' ')
			{
				return true;
			}
		}
		return false;
	}
	
}
class HumanPlayer extends Player
{
	
	HumanPlayer(String Name,char mark)
	{
		this.Name=Name;
		this.mark=mark;
	}
	void makeMove() 
	{
		Scanner sc=new Scanner(System.in);
		int row,col;
		do
		{
			System.out.println("Enter row and col");
			row=sc.nextInt();
		    col=sc.nextInt();
		}while(!isValidmove(row,col));
		TicTacToe.placeMark(row, col, mark);
	}
	
}
class AIPlayer extends Player
{
	
	AIPlayer(String Name,char mark)
	{
		this.Name=Name;
		this.mark=mark;
	}
	void makeMove() 
	{
		Scanner sc=new Scanner(System.in);
		int row,col;
		do
		{
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isValidmove(row,col));
		TicTacToe.placeMark(row, col, mark);
	}
}
public class Launchgame{
	public static void main(String[] args) {
	  TicTacToe t=new TicTacToe();
	  Scanner sc=new Scanner(System.in);
	  System.out.println("Enter the Player 1 and Player 2 Name");
	  String player1=sc.nextLine();
	  String player2=sc.nextLine();
      HumanPlayer p1= new HumanPlayer(player1,'X');
      HumanPlayer p2 =new HumanPlayer(player2,'O');
      Player cp;
      cp=p1;
      while(true)
      {
    	  System.out.println(cp.Name+" turn");
          cp.makeMove();
          TicTacToe.dispBoard();
          if(TicTacToe.checkcolWin()||TicTacToe.checkrowWin()||TicTacToe.checkdiagWin())
          {
        	  System.out.println(cp.Name+" has Won the Game");
        	  break;
          }
          else if(TicTacToe.checkDraw())
          {
        	  System.out.println( "The Game is Draw");
        	  break;
          }
          else
          {
        	  if(cp==p1)
        	  {
        		  cp=p2;
        	  }
        	  else
        	  {
        		  cp=p1;
        	  }
          }

      }
  }

}

}
