package codewars;

public class PawnDistance {
    private String color;
    private long distance;
    public PawnDistance(String s, long d) {
        color = s;
        distance = d;
    }

    public static PawnDistance redKnight(int knight, long pawn) {
        boolean finishesOnSameSquare=false;

        long caughtPawn=pawn*2;
        if(pawn%2==0){
            finishesOnSameSquare=true;
        }
        if((knight==0 && finishesOnSameSquare) || (knight==1 && !finishesOnSameSquare)){
            return new PawnDistance("White",caughtPawn);
        }
        return new PawnDistance("Black",caughtPawn);
    }
}
