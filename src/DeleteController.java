import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * DeleteController
 *
 * [your documentation here]
 *
 * @author Jacob Sandefur
 *
 * @version 4/28/2020
 *
 */
public final class DeleteController {

    private TwitterModel twitterModel;
    private DeleteTweet deleteTweet;

    /** Implement the DeleteController constructor per the instructions in the handout
     * @param twitterModel TwitterModel object connecting the model and controller portions of the MVC paradigm.
     * @param deleteTweet DeleteTweet object connecting the view and controller portions of the MVC paradigm.
     */

    public DeleteController(TwitterModel twitterModel, DeleteTweet deleteTweet) throws IllegalArgumentException {
        this.deleteTweet = deleteTweet;
        this.twitterModel = twitterModel;

        if (twitterModel == null) {
            throw new IllegalArgumentException("There is a null argument!");
        }
        if (deleteTweet == null) {
            throw new IllegalArgumentException("There is a null argument!");
        }

        ActionListener deleteAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDeleteButtonSemantics();
            }
        };
        ActionListener clearAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getClearButtonSemantics();
            }
        };
        deleteTweet.getDeleteButton().addActionListener(deleteAL);
        deleteTweet.getClearButton().addActionListener(clearAL);
    }

    /** A method to control specific GUI component actions. */

    private void getDeleteButtonSemantics() {
        if (deleteTweet.getidTextField().getText().equals("")) {
            JOptionPane.showMessageDialog(deleteTweet.getMainPanel(),
                    "Fill out the ID before continuing!",
                    "OOPS!",
                    JOptionPane.WARNING_MESSAGE);
        } else if (Integer.parseInt(deleteTweet.getidTextField().getText()) < 0) {
            JOptionPane.showMessageDialog(deleteTweet.getMainPanel(),
                    "Enter a valid ID!",
                    "OOPS!",
                    JOptionPane.WARNING_MESSAGE);
        } else if (twitterModel.searchByID(deleteTweet.getidTextField().getText()).isEmpty()) {
            JOptionPane.showMessageDialog(deleteTweet.getMainPanel(),
                    "The ID you searched for does not exist!",
                    "OOPS!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            twitterModel.remove(twitterModel.searchByID(deleteTweet.getidTextField().getText()).get());
            JOptionPane.showMessageDialog(deleteTweet.getMainPanel(),
                    "The tweet has been successfully deleted!",
                    "YAY!",
                    JOptionPane.INFORMATION_MESSAGE);
        }



    }

    /** A method to control specific GUI component actions. */

    private void getClearButtonSemantics() {
        deleteTweet.getidTextField().setText(null);
        deleteTweet.getidTextField().requestFocus();

    }
}

