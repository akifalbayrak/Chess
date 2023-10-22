public class Queen extends Piece {
    // this method  take 2 param for create Queen class

    public Queen(int color, Square location) {
        super(color, location);
    }

        // this method checking move for Queen

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        if (this.location.isAtSameColumn(targetLocation) || this.location.isAtSameRow(targetLocation)
                || this.location.isAtSameDiagonal(targetLocation)) {
            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
            for (Square square : between) {
                if (!square.isEmpty()) {
                    return false;
                }
            }
            validMove = targetLocation.isEmpty() || targetLocation.getPiece().getColor() != this.color;
        }
        return validMove;
    }

        // this method make move for Queen

    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    // this method return string and use it for player if it is upper its means white else it means black
    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "Q" : "q";
    }
}
