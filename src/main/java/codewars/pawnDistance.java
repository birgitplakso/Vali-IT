package codewars;

public class pawnDistance {
    private String color;
    private long distance;
    public pawnDistance(String s, long d) {
        color = s;
        distance = d;
    }

    public static pawnDistance redKnight(int knight, long pawn) {
        boolean finishesOnSameSquare=false;

        long caughtPawn=pawn*2;
        if(pawn%2==0){
            finishesOnSameSquare=true;
        }
        if((knight==0 && finishesOnSameSquare) || (knight==1 && !finishesOnSameSquare)){
            return new pawnDistance("White",caughtPawn);
        }
        return new pawnDistance("Black",caughtPawn);
    }
}
