
public abstract class Piece {
    protected Square location;
    protected int color;
    public Piece(int cl, Square lc) {
        this.location = lc;
        this.color = cl;
    }
    public int getColor(){
        return color;
    }
    public abstract boolean canMove(String to);
    public abstract void move(String to);
}
