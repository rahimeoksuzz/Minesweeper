package Minesweeper;
import java.util.Random;

/*
 * This program generates a minesweeper board
 */

public class Minesweeper {

    boolean[][] mineField; // board with mines marked as true
    int rows; // number of rows of the board
    int cols; // number of columns of the board
    int numMines;

    /*
     * Class Constructor
     * 
     * @param int rows Number of rows of the board
     * 
     * @param int cols Number of columns of the board
     * 
     * @param int numMines Number of mines to be placed on the board
     * 
     */
    Minesweeper(int rows, int cols, int numMines) {

        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        mineField = new boolean[rows][cols];
        generateBoard();
    }

    /*
     * Generates the mineField
     * 
     * @param int numMines Number of mines to be placed on the mineField
     * 
     * @return boolean[][] Game board with mines placed at random position
     */
    public void generateBoard() {
       //first, each element of the field array was made false.
       int mines = 0; //eklenen mayın
       while(mines < numMines){
           Random r = new Random();
           int x =  r.nextInt(rows);
           int y =  r.nextInt(cols);
           if(mineField[x][y] == false){
               mineField[x][y] = true;
               mines++;
        }
    }
}

    /*
     * Creates the game
     * 
     * @return int[][] The board with cell values computed
     */
    public int[][] generateClues() {

        int[][] clues = new int[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0;  j < cols; j++){
                if(mineField[i][j] == true){
                    clues[i][j] = -1;
                }
                else
                    clues[i][j] = countMines(i, j);
            }
        }

        return clues;
    }

    /*
     * counts the number of adjacent mines to a given cell position
     * 
     * @param int r row position
     * 
     * @param int c column position
     * 
     * @return int number of mines in the surrounding cells
     */
    public int countMines(int r, int c) {
        int count = 0;
        for(int x = r-1; x <= r+1; x++){
            for(int y = c-1; y <= c+1; y++){
                if(!(x == r && y == c) && (x >= 0 && x < rows) && (y >= 0 && y < cols)){
                    if(mineField[x][y] == true){
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /*
     * prints the game mine is represented by *
     * 
     * @param int[][] game The game board
     */
    public static void printClues(int[][] clues) {

        for (int i = 0; i < clues.length; i++){
            for (int j = 0; j < clues[i].length; j++){
                if(clues[i][j] ==  -1){
                    System.out.print("  *");
                }
                else
                    System.out.print("  " + clues[i][j]);
            }
            System.out.println("\n");
        }
    }


    public static void main(String[] args) {
        
        Minesweeper m = new Minesweeper(12, 12, 36);
        //System.out.print(m.mineField);

        int[ ][ ] clues = m.generateClues();
        Minesweeper.printClues(clues);
        
    }

}
