
/**
 * MancalaTest Class containing the main method.
 * 
 * @author Steven Ngo
 * 
 */

public class Demo {
    // method
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(view, model);
    }

}
