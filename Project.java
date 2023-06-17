package AlgorithmProject;

class SnakeLadder{

    //instances variable 2D dimensions
    public int[][] ladders;
    public int[][] snakes;





    //return the ladders array to main
    public int[][] getLadders() {
        return ladders;
    }

    //return the snakes array to main
    public int[][] getSnakes() {
        return snakes;
    }



    //board to visualize the game and store ladders and snakes
    public int[] board = new int[100];



    static int a =0;

    public SnakeLadder(int[][] ladders,int[][] snakes){
        //initialize the arrays
        this.ladders = ladders;
        this.snakes = snakes;



        for (int i = 0; i < snakes.length; i++) {//(n^3)





            for (int k = 0; k <snakes[i].length ; k++) {//(n),numbers of elements in each row
                occurences(snakes[i][k]);//(n),checking if the square occupied by mouth or tail
            }//end inner loop

        }//end outer loop



        for (int i = 0; i < ladders.length; i++) {//(n^3)




            for (int k = 0; k <ladders[i].length ; k++) {//(n),numbers of elements in each row

                occurences(ladders[i][k]);//(n),checking if the square occupied by base or end


//                base = end;
            }//end inner loop


        }//end outer loop




    }
    public void occurences(int p){//(n),avoid more snakes or ladder in same square

        if(board[p] == 0){//if the square empty

            board[p] = p;


        }////end outer if-statement

        else if (board[p] == p) {//if it is occupied put it in the next


            if(p+1 == board.length) {

                System.out.println("you can't add at end");

            }//end if
            else {

            }
                a= p;

                while (board[a+1] != 0) {//(n)
                    a++;
                }////end while

            }//end else


            board[++a] = a;

    }//end the function



    public void printSnakes(){//(n^2),print the snakes
        System.out.print("B = [ ");

        for(int i=0; i< snakes.length; i++) {

            System.out.print("[");

            for(int j=0; j< snakes[i].length; j++) {//(n)

                System.out.print(snakes[i][j]);

                if(j+1 != snakes[i].length){//(n)

                    System.out.print(",");
                }
            }
            System.out.print("]");
            System.out.print("");
        }
        System.out.print(" ]");
        System.out.println();
    }


    public void printLadders(){//(n^2),print the ladders
        System.out.print("A = [ ");

        for(int i=0; i< ladders.length; i++) {//(n)

            System.out.print("[");

            for(int j=0; j< ladders[i].length; j++) {//(n)

                System.out.print(ladders[i][j]);

                if(j+1 != ladders[i].length){

                    System.out.print(",");
                }
            }
            System.out.print("]");
            System.out.print("");
        }
        System.out.print(" ]");
        System.out.println();

    }


    public int[] longestLadder() {//(n^2),return the place that is longest which will help us
        int positionOfLongestLadder = 0;
        int EndPositionOfLongestLadder = 0;
        int jumpLengthofLongestLadder = 0;


        for (int i = 0; i < ladders.length; i++) {//(n^2)
            int tempBase=0;
            int tempEnd=0;


            for (int j = 0; j < ladders[i].length; j++) {//(n)
                tempEnd = ladders[i][j];
                if(tempBase!= tempEnd && tempBase!=0 ){
                    if((tempEnd-tempBase) > jumpLengthofLongestLadder){//if the distance of the end - base > the jumpLengthofLongestLadder it means we have a longest ladder
                        jumpLengthofLongestLadder = tempEnd-tempBase;

                        positionOfLongestLadder = tempBase;

                        EndPositionOfLongestLadder = tempEnd;
                    }
                }

                tempBase = ladders[i][j];
            }//end inner loop
        }//end outer loop


        int[] array = new int[2];
        array[0] = positionOfLongestLadder;
        array[1] = EndPositionOfLongestLadder;
        return array;
    }


    public int nearestSnake(Player p1) {//(n),monitor if we faced a snake in our way so we would move away from him
        int positionOfNearest = 100;
        int tempMouth=0;

        for (int i = 0; i < snakes.length; i++) {//(n)
            for (int j = 0; j < 1; j++) {

                if(tempMouth != 0 &tempMouth < positionOfNearest& tempMouth> p1.getPosition()){//we are checking the next snakes and excluding the previous because we passed it
                    positionOfNearest = tempMouth;
                }
                tempMouth = snakes[i][j];

            }//end inner loop
            if(tempMouth != 0 &tempMouth < positionOfNearest& tempMouth> p1.getPosition()){//check for last once before leave

                positionOfNearest = tempMouth;
            }
        }//end outer loop



        return positionOfNearest;
    }

    public int[] nearestLadder(Player p1) {//(n^2),search for a ladder that can bring us closer to our longest ladder for instance
        int positionOfNearestBase = 100;
        int positionOfNearestEnd = 0;
        int tempBase=0;

        for (int i = 0; i < ladders.length; i++) {//(n^2)
            for (int j = 0; j < ladders[i].length; j++) {//(n)
                if(tempBase != 0 &tempBase < positionOfNearestBase& tempBase> p1.getPosition()){//we are checking the next ladder and excluding the previous because we passed it
                    positionOfNearestBase = tempBase;
                    positionOfNearestEnd = ladders[i][j];
                }
                tempBase = ladders[i][j];

            }
        }
        int[] array = new int[2];
        array[0] = positionOfNearestBase;
        array[1] = positionOfNearestEnd;
        return array;
    }




}



class Player{//class represents the game player

    public int position;//instance to know where he stopped at which square
    public String nameOfThePlayer;// we give the player any name

    public Player(String nameOfThePlayer){
        position = 0;
        this.nameOfThePlayer = nameOfThePlayer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getNameOfThePlayer() {
        return nameOfThePlayer;
    }
}





public class Problem {//the important class which is hold the algorithm
    static int dice=0;//number of the dices has been thrown


    public static int assistantLadder(int longestLadderPosition,int[] secondDestnation){//(1) constant,searching ladder could bring us closer to the longest ladder
        if(secondDestnation[1] >= longestLadderPosition || secondDestnation[0] == longestLadderPosition){//end of the assistant ladder does'nt skip the longest or the nearest ladder same the longest
            return 0;//return 0 which is indicated there is no assistant ladders
        }


        return 1;//return 1 which is indicated there is  assistant ladders
    }

    public static int Algorithm(int[] longestLadder,SnakeLadder sl,Player p1){//O(n^2),the algorithm which is executed the game
        System.out.println("------------Start the game------------");
        int firstDestination = longestLadder[0];//assign our first destination "the base of the longest ladder"
        int numberOfAssistantLadderWeClimb=0;//how many ladder we have used before the longest





        //finding assistant ladders
        int[] secondDestnation = sl.nearestLadder(p1);//(n^2)

        while ((assistantLadder(firstDestination, secondDestnation)) == 1) {//(n^2) ,while there are assistant ladders climb it
            preparing(secondDestnation[0], secondDestnation[1]-secondDestnation[0], sl, p1);//(n^2)
            numberOfAssistantLadderWeClimb++;

            secondDestnation = sl.nearestLadder(p1);//(n^2)
        }


        if(numberOfAssistantLadderWeClimb!=0){//check if we have used ladders while we walk
            System.out.println("the number of ladders we used before reaching the longest ladder "+numberOfAssistantLadderWeClimb);

        }


        preparing(firstDestination,longestLadder[1]-firstDestination,sl,p1);//(n^2),walk to the longest ladder


        System.out.println("remains just a few steps");
        int remainDestinationToWin = 100-p1.getPosition();//compute how many one step left to win
        walkAndCheck(remainDestinationToWin,sl,p1,100);//(n^2)

        System.out.println("You finally reach "+ p1.getPosition());
        //return the number of dices
        return dice;
    }





    public static void preparing(int destination, int jumpingStepsForTheLadder, SnakeLadder sl, Player p1){//O(n^2),method specifically detect the destination and reach it by  invocation another method
        int numberOfRemains=0;
        int tempDestination=destination;
        int newDestnaitaion =destination;
        if(destination <= p1.getPosition()){//we already went through it
            return;
        }
        while (tempDestination % 6 !=0 & tempDestination>6){//(1) constant because at most will decrease 5,make the destination a multiple of number six
            tempDestination--;
            numberOfRemains++;
        }
        if(numberOfRemains!= 0){
            newDestnaitaion = tempDestination+jumpingStepsForTheLadder+numberOfRemains;//add them to get the position to the destination
        }



        walkAndCheck(tempDestination, sl, p1, newDestnaitaion);//(n^2),because our distance now is multiple of six we can walk now



        if(numberOfRemains !=0) {//the numbers of remaining steps
            p1.setPosition(p1.getPosition() + numberOfRemains);
            dice++;
        }
        System.out.println(p1.nameOfThePlayer + " You reach Position of the ladder " + p1.getPosition());

        if(p1.getPosition() != jumpingStepsForTheLadder) {
            p1.setPosition(p1.getPosition() + jumpingStepsForTheLadder);//update the position of the player after jump the distance ot the ladder
            System.out.println(p1.nameOfThePlayer + " You climbed the ladder " + p1.getPosition());

        }

    }

    public static void walkAndCheck(int destination,SnakeLadder sl,Player p1,int position){//O(n^2),walking and dealing for most cases
        int numberOfRemainsSteps=0;


        if(destination<6){//means that less than six steps to win
            p1.setPosition(p1.getPosition()+destination);
            dice++;
            return;
        }
        while (destination % 6 !=0 & destination>6){//(1) constant because at most will decrease 5,this checking for the last invocation before win we need make the distance multiple of six
            destination--;
            numberOfRemainsSteps++;
        }

        int numberOfRolls = destination/6;//number of rolls we need to throw it to reach our distance


        int remainSteps;
        while(p1.getPosition() != position & (p1.getPosition()+6) != sl.nearestSnake(p1)& numberOfRolls!=0){//(n^2),walk if our way safety
            p1.setPosition(p1.getPosition()+6);
            dice++;
            numberOfRolls--;
        }
        if(numberOfRolls==0){
            System.out.println("after your first destination "+ p1.getPosition());

            if(numberOfRemainsSteps!= 0){//there are some steps left for the destination
                p1.setPosition(p1.getPosition()+numberOfRemainsSteps);
                dice++;
            }
            return;
        }



        if(p1.getPosition()>destination){//here if the destination was to the end point "special case"
            remainSteps = position-p1.getPosition() ;}
        else {
            remainSteps = destination-p1.getPosition();//here if the destination was to ladder

        }

        while (remainSteps != 0&& p1.getPosition() != destination){//(n^2),the second condition in case we walk to reach the base of the longest ladder

            if(remainSteps<=6 ){
                p1.setPosition(p1.getPosition()+remainSteps);
                dice++;
                return;
            }

            if(( p1.getPosition()+6) == sl.nearestSnake(p1)){//(n^2) the close steps are unsafe
                System.out.println(p1.getNameOfThePlayer()+" you at " +p1.getPosition()+" it seems in your next six step snake "+sl.nearestSnake(p1));

                p1.setPosition(p1.getPosition()+5);//stand one step before a snake
                remainSteps = remainSteps-5;
                dice++;



            }else {

                p1.setPosition(p1.getPosition()+6);//safe
                remainSteps = remainSteps-6;
                dice++;
            }
        }//end while


        if(numberOfRemainsSteps!= 0){//there are some steps left for the destination
            p1.setPosition(p1.getPosition()+numberOfRemainsSteps);
            dice++;
        }

    }//end the method

    public static void StartTheGame(SnakeLadder sl){
        dice=0;//make the number of dices equals zero
        Player p1 = new Player("Ahmed");//make object of the player
        sl.printLadders();
        sl.printSnakes();

        int[] longestLadder =sl.longestLadder();//return the base and end of the longest ladder

        System.out.println(p1.nameOfThePlayer+" need to walk "+longestLadder[0]+" square to reach the best ladders");



        System.out.println("Output = "+Algorithm(longestLadder,sl,p1));//start the game
        System.out.println();
    }

    public static void main(String [] args){
        System.out.println("Welcome");
        //First example input

        int[][] ladders1={{32,62},{42,68},{12,98}};
        int[][] snakes1={{95,13},{97,25},{93,37},{79,11},{75,19},{49,47},{67,17}};


        //Second example input

        int[][] ladders2={{8,52},{6,80},{26,42},{2,72}};
        int[][] snakes2={{51,19},{37,29},{81,3},{59,5},{79,23},{53,7},{43,33},{77,21}};
        //Third example input

        int[][] ladders3={{42,85},{18,27},{59,88}};
        int[][] snakes3={{93,13},{91,11},{63,44},{28,14},{45,22},{19,17},{5,4},{73,64},{83,25},{72,43},{98,26},{34,6},{35,23}};
        //each object for one game
        SnakeLadder s1 = new SnakeLadder(ladders1,snakes1);
        SnakeLadder s2 = new SnakeLadder(ladders2,snakes2);
        SnakeLadder s3 = new SnakeLadder(ladders3,snakes3);



        StartTheGame(s1);
        System.out.println("\n\n=====================================\n\n");

        StartTheGame(s2);
        System.out.println("\n\n=====================================\n\n");
        StartTheGame(s3);




    }
}
