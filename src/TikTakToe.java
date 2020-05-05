import java.util.*;

public class TikTakToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();        //store positions in ArrayList
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void printGameBoard(char[][] gameBoard) {    //static need not an instance of the class

        for (char[] row : gameBoard) {  //for each row inside gameBoard
            for (char c : row) {      //for each element inside the row

                System.out.print(c);
            }

            System.out.println();   //go to a new line after each row
        }
    }

    public static void placePiece(char[][]gameBoard, int position, String user){

        char symbol = 'X';   //Initialize default 'X'

        if(user.equals("player")){

            symbol = 'X';
            playerPositions.add(position);               //add position to ArrayList

        } else if(user.equals("cpu")){

           symbol = 'O';
           cpuPositions.add(position);                   //add position to ArrayList
        }

        switch(position){                               //switch for placements 1-9
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    public static String checkWinner(){

        List topRow = Arrays.asList(1,2,3);         //List winning combos of "position"
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List crossOne = Arrays.asList(1,5,9);
        List crossTwo = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(crossOne);
        winning.add(crossTwo);

        for(List list: winning){

            if(playerPositions.containsAll(list)){

                return "Congratulations, you won!";

            } else if(cpuPositions.containsAll(list)){

                return "CPU wins! Sorry.";

            } else if(playerPositions.size() + cpuPositions.size() == 9){

                return "Tie!";

            }
        }
        return "";
    }


        public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},      //Layout of the gameBoard
                {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);                                                       //print the gameBoard

            while(checkWinner().equals("")) {                                            //Run the game until a result is found

                Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter your placement 1-9: ");
                int playerPosition = keyboard.nextInt();

                while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {  //Making sure you dont place over yourself or over a cpu position
                        System.out.println("Invalid Placement! Try again: ");
                        playerPosition = keyboard.nextInt();

                }

                placePiece(gameBoard, playerPosition, "player");  //Player's turn (always goes first)

                if(checkWinner().equals("")) {              //if no conclusion is found, run the cpu's turn

                    Random randNumber = new Random();
                    int cpuPosition = randNumber.nextInt(9) + 1;

                    while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {  //Making sure the cpu doesnt place over itself or over you
                        cpuPosition = randNumber.nextInt(9) + 1;

                    }

                    placePiece(gameBoard, cpuPosition, "cpu");        //CPU's turn
                    printGameBoard(gameBoard);

                    System.out.println(checkWinner());

                } else if (!checkWinner().equals("")){              //if a conclusion is found, print the conclusion

                    printGameBoard(gameBoard);
                    System.out.println();
                    System.out.println(checkWinner());

                }

            }

        }
}



