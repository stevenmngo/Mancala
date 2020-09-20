import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;

/**
 * Pits Icon for drawing the stone in the pit
 */
public class Pits implements Icon {
    private Design design;
    private int pitNumber;
    private int stoneNumber;
    private int xx = 14;
    private int yy = -5;

    /**
     * Pits Icon Constructor
     * @param Design
     * @param pitnumber
     * @param stoneNumber
     */
    public Pits(Design design, int pitNumber, int stoneNumber) {
        this.design = design;
        this.pitNumber = pitNumber;
        this.stoneNumber = stoneNumber;

    }

    
    /**
     * Get the Icon Shape given the number of stone
     * @param numStonw
     * @return Shape of the stone at the correct
     */
    public Shape stonePosition(int numStone) {

        if (numStone % 5 == 0) {

            xx = 14;
            yy += 16;
        }
        if (numStone % 5 != 0) {
            xx += 16;
        }
        int x = xx;
        int y = yy;

        return design.getStoneShape(x, y);

    }

    /**
     * Contain method given the point
     * @param point
     */
    public boolean contains(Point2D point) {
        return design.getPitShape().contains(point);
    }

    /**
     * Setter for Pit number and Stone number
     *
     * @param pitnum
     * @param stonenum
     */
    public void setUpdate(int pitnum, int stonenum) {
        pitNumber = pitnum;
        stoneNumber = stonenum;
    }

    /**
     * Paint method
     * @param component c
     * @param Graphics g
     * @param int x
     * @param int y
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {

        Graphics2D g2 = (Graphics2D) g;
        if (pitNumber != 6 && pitNumber != 13) {
            g2.setColor(design.getPitColor());
            g2.fill(design.getPitShape());

            for (int i = 0; i < stoneNumber; i++) {
                g2.setColor(design.getStoneColor());
                g2.fill(stonePosition(i));
            }

        } else {
            g2.setColor(design.getMancalaColor());
            g2.fill(design.getMancalaShape());
            for (int i = 0; i < stoneNumber; i++) {
                g2.setColor(design.getStoneColor());
                g2.fill(stonePosition(i));
            }
        }

    }

    /**
     * Getter for the mancala shape width
     * @return int icon width
     */
    @Override
    public int getIconWidth() {
        if (pitNumber == 6 || pitNumber == 13)
            return design.getMancalaWidth();
        return design.getPitWidth();
    }

    /**
     * Getter for the mancala shape height
     * @return int icon height
     */
    @Override
    public int getIconHeight() {
        if (pitNumber == 6 || pitNumber == 13)
            return design.getMancalaHeight();
        return design.getPitHeight();
    }

}
