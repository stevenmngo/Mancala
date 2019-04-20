import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * Concrete class that implement Strategy pattern
 */
public class DesignTwo implements Design {
    int pitHeight = 110;
    int pitWidth = 100;
    int mancalaHeight = 260;
    int mancalaWidth = 100;

    /**
     * Getter for the pit shape
     * @return Shape
     */
    @Override
    public Shape getPitShape() {
        return new RoundRectangle2D.Double(0, 0, pitWidth, pitHeight, 30, 30);
    }

    /**
     * Getter the stone shape
      * @return Shape
     */
    @Override
    public Shape getStoneShape(int x, int y) {
        return new RoundRectangle2D.Double(x, y, 10, 10,2,2);
    }

    /**
     * Getter the Mancala shape
      * @return Shape
     */
    @Override
    public Shape getMancalaShape() {
        return new RoundRectangle2D.Double(0, 0, mancalaWidth, mancalaHeight, 30, 30);
    }

    /**
     * Getter the back ground color
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
        return new Color(8, 122, 177);
    }

    /**
     * Getter for mancala color
     * @return C
     */
    @Override
    public Color getMancalaColor() {
        return new Color(7, 104, 152);
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
