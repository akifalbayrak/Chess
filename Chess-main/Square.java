import java.util.ArrayList;

public class Square {
    private ChessBoard board;
    private Piece piece;
    private int x;
    private int y;

    // this class takes board x and y to use for methods
    public Square(ChessBoard bo, int xCord, int yCord) {
        this.board = bo;
        this.x = xCord;
        this.y = yCord;
    }

    // this method take string for parameter and convert it for board square part
    public Square getSquareAt(String to) {
        int x = to.charAt(0) - 'a';
        int y = Integer.parseInt(to.substring(1)) - 1;
        return board.getSquare(y, x);
    }

    // this method return row distance
    public int getRowDistance(Square location) {
        return (this.getY() - location.getY());
    }

    // this method return column distance
    public int getColumnDistance(Square location) {
        return (this.getX() - location.getX());
    }

    // this method take this and target compare x value it each other 
    public boolean isAtSameColumn(Square targetLocation) {
        return this.getX() == targetLocation.getX();
    }

    // this method take 2 parameter and return array with type of list of square
    public Square[] getSquaresBetween(Square location, Square targetLocation) {
        int x1 = location.getX();
        int y1 = location.getY();
        int x2 = targetLocation.getX();
        int y2 = targetLocation.getY();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        ArrayList<Square> squares = new ArrayList<>();
        while (true) {
            if (x1 == x2 && y1 == y2)
                break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
            squares.add(board.getSquare(x1, y1));
        }
        return squares.toArray(new Square[squares.size()]);
    }

    // this method compare column with this and parameter
    public boolean isNeighborColumn(Square targetLocation) {
        return Math.abs(this.getY() - targetLocation.getY()) == 1;
    }

    // this method return piece
    public Piece getPiece() {
        return piece;
    }

    // this method return boolean and checking board last row for white and black
    public boolean isAtLastRow(int color) {
        return (this.getY() == (color == ChessBoard.WHITE ? 7 : 0));
    }

    // this method put queen for pawn in the last row
    public void putNewQueen(int color) {
        Queen queen = new Queen(color, this.board.getSquare(this.getY(), this.getX()));
        this.piece = queen;
    }

    // this method use for changing or adding piece
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // this method clear piece
    public void clear() {
        piece = null;
    }

    // this method compare for row
    public boolean isAtSameRow(Square targetLocation) {
        return this.getY() == targetLocation.getY();
    }

    public boolean isAtSameDiagonal(Square targetLocation) {
        return Math.abs(this.getX() - targetLocation.getX()) == Math.abs(this.getY() - targetLocation.getY());
    }

    // this method return board and this method initialized in the top with empty set
    public ChessBoard getBoard() {
        return board;
    }

    // this method return bool for is piece empty
    public boolean isEmpty() {
        return piece == null;
    }

    // return x
    public int getX() {
        return x;
    }

    // return y
    public int getY() {
        return y;
    }

}