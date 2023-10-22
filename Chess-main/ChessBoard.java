import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static final int WHITE = 1;
    public static final int BLACK = 0;
    private Square[][] board;
    private boolean whitePlaying;

    // this class using whiteplayin for which people turn and creating square[][] list, and create square for every part of chessboard
    public ChessBoard() {
        this.whitePlaying = true;
        board = new Square[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Square(this, j, i);
            }
        }

        // Set up the white pieces
        board[0][0].setPiece(new Rook(WHITE, board[0][0]));
        board[0][1].setPiece(new Night(WHITE, board[0][1]));
        board[0][2].setPiece(new Bishop(WHITE, board[0][2]));
        board[0][3].setPiece(new Queen(WHITE, board[0][3]));
        board[0][4].setPiece(new King(WHITE, board[0][4]));
        board[0][5].setPiece(new Bishop(WHITE, board[0][5]));
        board[0][6].setPiece(new Night(WHITE, board[0][6]));
        board[0][7].setPiece(new Rook(WHITE, board[0][7]));
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(WHITE, board[1][i]));
        }

        // Set up the black pieces
        board[7][0].setPiece(new Rook(BLACK, board[7][0]));
        board[7][1].setPiece(new Night(BLACK, board[7][1]));
        board[7][2].setPiece(new Bishop(BLACK, board[7][2]));
        board[7][3].setPiece(new Queen(BLACK, board[7][3]));
        board[7][4].setPiece(new King(BLACK, board[7][4]));
        board[7][5].setPiece(new Bishop(BLACK, board[7][5]));
        board[7][6].setPiece(new Night(BLACK, board[7][6]));
        board[7][7].setPiece(new Rook(BLACK, board[7][7]));
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn(BLACK, board[6][i]));
        }

        // Set up the empty squares
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setPiece(null);
            }
        }
    }

    // this method check is game ended used 2 const for any piece of left for black or white pieces if it is 0 return true and game is end
    public boolean isGameEnded() {
        boolean whitePiecesExist = false;
        boolean blackPiecesExist = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Square square = board[i][j];
                if (!square.isEmpty()) {
                    if (square.getPiece().getColor() == WHITE) {
                        whitePiecesExist = true;
                    } else if (square.getPiece().getColor() == BLACK) {
                        blackPiecesExist = true;
                    }
                }
            }
        }
        return !whitePiecesExist || !blackPiecesExist;
    }

    // this method return whiteplaying
    public boolean isWhitePlaying() {
        return whitePlaying;
    }

    // this method return piece from board 
    public Piece getPieceAt(String from) {
        int x = from.charAt(0) - 'a';
        int y = (from.charAt(1) - '1');
        return board[y][x].getPiece();
    }

    // this method looks like upper one but difference is this method does not return piece value, only board return
    public Square getSquareAt(String location) {
        int x = location.charAt(0) - 'a';
        int y = location.charAt(1) - '1';
        return board[y][x];
    }

    // this method list betten of squares
    public Square[] getSquaresBetween(Square location, Square targetLocation) {
        List<Square> squaresBetween = new ArrayList<>();
        int y1 = location.getX();
        int x1 = location.getY();
        int y2 = targetLocation.getX();
        int x2 = targetLocation.getY();

        if (x1 == x2) { // vertical movement
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            for (int y = minY + 1; y < maxY; y++) {
                squaresBetween.add(board[x1][y]);
            }
        } else if (y1 == y2) { // horizontal movement
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            for (int x = minX + 1; x < maxX; x++) {
                squaresBetween.add(board[x][y1]);
            }
        } else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) { // diagonal movement
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            for (int i = 1; i < maxX - minX; i++) {
                if (x2 > x1 && y2 > y1) { // up-right diagonal
                    squaresBetween.add(board[minX + i][minY + i]);
                } else if (x2 > x1 && y2 < y1) { // down-right diagonal
                    squaresBetween.add(board[minX + i][maxY - i]);
                } else if (x2 < x1 && y2 > y1) { // up-left diagonal
                    squaresBetween.add(board[maxX - i][minY + i]);
                } else if (x2 < x1 && y2 < y1) { // down-left diagonal
                    squaresBetween.add(board[maxX - i][maxY - i]);
                }
            }
        }
        return squaresBetween.toArray(new Square[0]);
    }

    // this method switch black to white and white to black
    public void nextPlayer() {
        if (isWhitePlaying()) {
            setWhitePlaying(false);
        } else {
            setWhitePlaying(true);
        }
    }

    // this method change whiteplaying value
    public void setWhitePlaying(boolean whitePlaying) {
        this.whitePlaying = whitePlaying;
    }

    // this method return square from the board
    public Square getSquare(int x, int y) {
        return board[x][y];
    }

    // this method showing string,this means chess game is here and look from here to take input and play game
    public String toString() {
        String result = "    A   B   C   D   E   F   G   H\n";
        ; // Column labels
        result += "  ---------------------------------\n";
        for (int i = 0; i < 8; i++) {
            result += i+1 + " |"; // Row number
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    result += " " + board[i][j].getPiece() + " |";
                } else {
                    result += "   |";
                }

            }
            result += " " + (i+1) + "\n";
            result += "  ---------------------------------\n";

        }
        result += "    A   B   C   D   E   F   G   H\n"; // Column labels
        return result;
    }

}
