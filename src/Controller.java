
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controller class connect View and Model and add action to interact between
 * them
 */
public class Controller {
    private View view;
    private Model model;

    /**
     * Controller constructor creates actions to performs when an user input value
     * 
     * @param View
     * @param Model
     */
    public Controller(View vie, Model mod) {
        view = vie;
        model = mod;

        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                view.mancalaRepaint();
                for (int i = 0; i < 12; i++) {
                    view.labelRePaint(i);
                }
                view.setPlayerlable();
                view.repaintUndo();

            }
        };
        model.addChangeListener(listener);

    }

}
