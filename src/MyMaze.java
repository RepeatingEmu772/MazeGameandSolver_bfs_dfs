// Names: Aparna Lohmor ; Manan Mrig
// x500s: lohmo001 ; mrig0001
import java.util.Random;

public class MyMaze {
    Cell[][] maze; // Cell Array of the newMaze (newMaze is the new MyMaze object within it is an array called maze which has all rows and cols of newMaze)
    int startRow;
    int endRow;


    public MyMaze(int rows, int cols, int startRow, int endRow) { //CONSTRUCTOR
        this.maze = new Cell[rows][cols];
        //this.maze[rows][cols] = new Cell();
        this.startRow = startRow;
        this.endRow = endRow;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new Cell();
            }
        }
    }

    public void neighbor() {

    }


    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols, int startRow, int endRow) {
        int[] topIndex;
        int[][] possibleNeighborsArray; //array of top, bottom, left , right neighbors or the topIndex(corresponding cell)
        possibleNeighborsArray = new int[4][2];

        boolean[] dir = new boolean[]{false, false, false, false};

        MyMaze newMaze = new MyMaze(rows, cols, startRow, endRow);
        Stack1Gen newStack = new Stack1Gen();
        newStack.push(new int[]{startRow, 0});
        newMaze.maze[startRow][0].setVisited(true);

//        int[] temp =(int[])newStack.top();
//        System.out.println(temp[1]);


        while (newStack.isEmpty() != true) { // loop until the stack is empty
            int countNeighbors = 0;
            topIndex = (int[]) newStack.top(); //return top index off the stack without removing it from stack
            //System.out.println(topIndex[0]);
            int rowTopIndex = topIndex[0];
//            System.out.println("rowTopIndex:" + rowTopIndex);
            int colTopIndex = topIndex[1];
//            System.out.println("colTopIndex:" + colTopIndex);

//for top index above the topIndex
            if ((rowTopIndex - 1) >= 0) {
//                Cell topNeighbor = newMaze.maze[rowTopIndex-1][colTopIndex];
//                System.out.println("inner loop for top");
                if (newMaze.maze[rowTopIndex - 1][colTopIndex].getVisited() == false) {
                    possibleNeighborsArray[countNeighbors] = new int[]{rowTopIndex - 1, colTopIndex};
                    countNeighbors += 1;
//                    System.out.println("for top neighbor count :" + countNeighbors);
                }

            }

//for bottom index below the topIndex
            if ((rowTopIndex + 1) < rows) {
//                Cell bottomNeighbor = newMaze.maze[rowTopIndex+1][colTopIndex];
//                System.out.println("inner loop for bottom");
                if (newMaze.maze[rowTopIndex + 1][colTopIndex].getVisited() == false) {
                    possibleNeighborsArray[countNeighbors] = new int[]{rowTopIndex + 1, colTopIndex};
                    countNeighbors += 1;
//                    System.out.println("for bottom neighbor count:" + countNeighbors);
                }
            }


            //for right index right to the topIndex
            if (colTopIndex + 1 < cols) {
//                Cell rightNeighbor = newMaze.maze[rowTopIndex][colTopIndex+1];
//                System.out.println("inner loop for right");
                if (newMaze.maze[rowTopIndex][colTopIndex + 1].getVisited() == false) {
                    possibleNeighborsArray[countNeighbors] = new int[]{rowTopIndex, colTopIndex + 1};
                    countNeighbors += 1;
//                    System.out.println("for right neighbor count:" + countNeighbors);
                }
            }

//for left index left to the topIndex
            if ((colTopIndex - 1) >= 0) {
//                Cell leftNeighbor = newMaze.maze[rowTopIndex][colTopIndex-1];
//                System.out.println("inner loop for left");
                if (newMaze.maze[rowTopIndex][colTopIndex - 1].getVisited() == false) {
                    possibleNeighborsArray[countNeighbors] = new int[]{rowTopIndex, colTopIndex - 1};
                    countNeighbors += 1;
//                    System.out.println("for left neighbor count:" + countNeighbors);
                }
            }
            if (countNeighbors == 0) { //If the current cell does not have any un-visited neighbors, then it is a dead end.  Popthe corresponding index from the top of the stack.
               newStack.pop();
            }
            else {
                Random randNeighbor = new Random();
                int upperbound = countNeighbors;
//                System.out.println("upperbound:" + upperbound);
                int int_randNeighbor = randNeighbor.nextInt(upperbound); //  numbers between 0,1,2,3
//            System.out.println(int_randNeighbor);
                int[] randomNeighborGenerated = possibleNeighborsArray[int_randNeighbor];
                newStack.push(randomNeighborGenerated);
                newMaze.maze[randomNeighborGenerated[0]][randomNeighborGenerated[1]].setVisited(true);

//Remove the wall between the current cell and the neighbor cell.
                if (rowTopIndex > randomNeighborGenerated[0]) {
                    newMaze.maze[randomNeighborGenerated[0]][randomNeighborGenerated[1]].setBottom(false);
                }

                if(rowTopIndex<randomNeighborGenerated[0]){
                    newMaze.maze[rowTopIndex][colTopIndex].setBottom(false);
                }

                if(colTopIndex>randomNeighborGenerated[1]){
                    newMaze.maze[randomNeighborGenerated[0]][randomNeighborGenerated[1]].setRight(false);
                }

                if(colTopIndex<randomNeighborGenerated[1]){
                    newMaze.maze[rowTopIndex][colTopIndex].setRight(false);
                }

            } //else

//            newMaze.printMaze();

        } //while loop

        for(int i = 0; i<rows;i++){ //set the visited attribute of each maze cell to false before returning the newMaze
            for (int j = 0; j<cols; j++){
                newMaze.maze[i][j].setVisited(false);
            }
        }

        return newMaze; //return the newMaze
    }




    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {

        System.out.print("|");
        for(int d=0; d<this.maze[0].length;d++){
            System.out.print("---|");
        }
        System.out.println();
        for(int i=0;i<this.maze.length;i++){
            if(this.startRow==i){
                System.out.print(" ");
            }
            else{
                System.out.print(("|"));
            }
            for(int j=0; j<this.maze[i].length;j++){
                //here
                if(i==endRow) {
                    //System.out.println(i);
                    if (j == this.maze[0].length - 1) {
                        if (this.maze[i][j].getVisited() == true) {
                            System.out.print(" *  ");
                            break;
                        } else if (this.maze[i][j].getVisited() == false) {
                            System.out.print("    ");
                            break;
                        }
                    }

                }

                if (this.maze[i][j].getVisited() == true) {
                    if (this.maze[i][j].getRight() == true) {
                        System.out.print(" * |");
                    } else {
                        System.out.print(" *  ");
                    }
                } else if (this.maze[i][j].getVisited() == false) {
                    if (this.maze[i][j].getRight() == true) {
                        System.out.print("   |");
                    } else {
                        System.out.print("    ");
                    }
                }

            }
            System.out.println("");
            System.out.print("|");
            for(int k=0; k<this.maze[i].length;k++){
                if(this.maze[i][k].getBottom()==true){
                    System.out.print("---|");
                }
                else {
                    System.out.print("   |");
                }
            }
            System.out.println();
        }

    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        Q2Gen<int[]> q = new Q2Gen<>();
        //Queue<int[]> q = new LinkedList<>();
        int l = this.maze.length; //r
        int b = this.maze[0].length;//c
        q.add(new int[]{this.startRow, 0});


        while (q.length() != 0) {

            int[] top = q.remove();
            int r = top[0];
            int c = top[1];

            maze[r][c].setVisited(true);

            if (r == endRow && c == b - 1) {
                return;
            } else {
                if (r - 1 >= 0 && r - 1 < l && c >= 0 && c < b) { //top
                    if(maze[r-1][c].getVisited()==false && maze[r-1][c].getBottom()==false){
                        q.add(new int[]{r-1,c});
                    }
                }
                if (r + 1 >= 0 && r + 1 < l && c >= 0 && c < b) {//bottom
                    if(maze[r+1][c].getVisited()==false && maze[r][c].getBottom()==false){
                        q.add(new int[]{r+1,c});
                    }
                }
                if (r >= 0 && r < l && c - 1 >= 0 && c - 1 < b) {//left
                    if(maze[r][c-1].getVisited()==false && maze[r][c-1].getRight()==false){
                        q.add(new int[]{r,c-1});
                    }
                }
                if (r >= 0 && r < l && c + 1 >= 0 && c + 1 < b) {//right
                    if(maze[r][c+1].getVisited()==false && maze[r][c].getRight()==false){
                        q.add(new int[]{r,c+1});
                    }
                }
            }
            //printMaze();
        }
    }

    public static void main(String[] args){
        /* Any testing can be put in this main function */
        MyMaze myMaze = makeMaze(5,20,0,2);
        //myMaze.printMaze();
        myMaze.solveMaze();
        myMaze.printMaze();

    }
}
