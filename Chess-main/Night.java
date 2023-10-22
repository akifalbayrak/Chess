public class Night extends Piece {
    // this method  take 2 param for create Night class

    public Night(int color, Square location) {
        super(color, location);
    }

        // this method checking move for Night

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int columnDistance = targetLocation.getColumnDistance(location);
        if ((Math.abs(rowDistance) == 2 && Math.abs(columnDistance) == 1)
                || (Math.abs(rowDistance) == 1 && Math.abs(columnDistance) == 2)) {
            validMove = targetLocation.isEmpty() || targetLocation.getPiece().getColor() != this.color;
        }
        return validMove;
    }

        // this method make move for Night

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
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}
