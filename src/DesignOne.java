import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Concrete class that implement Strategy pattern
 */
public class DesignOne implements Design {

    private int pitWidth = 100;
    private int pitHeight = 110;
    private int mancalaWidth = 120;
    private int mancalaHeight = 260;

    /**
     * Getter for the pit shape
     * @return Shape
     */
    public Shape getPitShape() {
        return new RoundRectangle2D.Double(0, 0, pitWidth, pitHeight, 10, 10);
    }

    /**
     * Getter the stone shape
     * @return Shape
     */
    @Override
    public Shape getStoneShape(int x, int y) {
        return new Ellipse2D.Double(x, y, 12, 12);
    }

    /**
     * Getter the mancala shape
     * @return Shape
     */
    @Override
    public Shape getMancalaShape() {
        return new RoundRectangle2D.Double(0, 0, mancalaWidth, mancalaHeight, 10, 10);
    }

    /**
     * Getter the backgroundcolor
     * @return Color
     */
    @Override
    public Color getBackgroundColor() {
        return Color.WHITE;
    }

    /**
     * Getter the stone color
     * @return Color
     */
    @Override
    public Color getStoneColor() {
        return Color.WHITE;
    }

    /**
     * Getter pit color
     * @return Color
     */
    @Override
    public Color getPitColor() {
        return new Color(83, 188, 156);
    }

    /**
     * Getter for mancala color
     * @return Color
     */
    @Override
    public Color getMancalaColor() {
        return new Color(57, 138, 118);
    }

    /**
     * Getter for pit width
     * @return int pit width
     */
    @Override
    public int getPitWidth() {
        return pitWidth;
    }

    /**
     * Getter for pit height
     * @return int pit height
     */
    @Override
    public int getPitHeight() {
        return pitHeight;
    }
    /**
     * Getter for mancala width
      * @return int mancala width

     */
    @Override
    public int getMancalaWidth() {
        return mancalaWidth;
    }
    /**
     * Getter for mancala height
     * @return int mancala height
     */
    @Override
    public int getMancalaHeight() {
        return mancalaHeight;
    }

}
