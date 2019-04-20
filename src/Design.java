import java.awt.Color;
import java.awt.Shape;

/** 
 * Design interface for using strategy pattern
 */
public interface Design {

	public Shape getPitShape();
	public Shape getStoneShape(int x,int y);
	public Shape getMancalaShape();
	public Color getBackgroundColor();
	public Color getStoneColor();
	public Color getPitColor();
	public Color getMancalaColor();
	public int getPitWidth();
	public int getPitHeight();
	public int getMancalaWidth();
	public int getMancalaHeight();
	
}
