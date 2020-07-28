import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * EditController
 *
 * [your documentation here]
 *
 * @author Jacob Sandefur
 *
 * @version 4/28/2020
 *
 */
public final class EditController {

    private TwitterModel twitterModel;
    private EditTweet editTweet;

    /** Implement the AddController constructor per the instructions in the handout
     * @param twitterModel TwitterModel object connecting the model and controller portions of the MVC paradigm.
     * @param editTweet EditTweet object connecting the view and controller portions of the MVC paradigm.
     */

    public EditController(TwitterModel twitterModel, EditTweet editTweet) throws IllegalArgumentException {
        this.editTweet = editTweet;
        this.twitterModel = twitterModel;

        if (twitterModel == null) {
            throw new IllegalArgumentException("An argument is null!");
        }
        if (editTweet == null) {
            throw new IllegalArgumentException("An argument is null!");
        }
        ActionListener editAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getEditButtonSemantics();
            }
        };
        ActionListener clearAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getClearButtonSemantics();
            }
        };
        editTweet.getEditButton().addActionListener(editAL);
        editTweet.getClearButton().addActionListener(clearAL);
    }

    /** A method used to update a specific Tweet's body string.
     * @param aTweet Tweet object containing an ID and body String
     * @param body String
     */
    private void updateBody(Tweet aTweet, String body) {
        if (body.equals("")) {
            JOptionPane.showMessageDialog(
                    this.editTweet.getMainPanel(),
                    "This message body is empty. Maybe you should instead delete it?",
                    "Message Board",
                    JOptionPane.ERROR_MESSAGE);
            this.editTweet.getBodyTextField().requestFocus();
        } else {
            aTweet.setBody(body);
            JOptionPane.showMessageDialog(
                    this.editTweet.getMainPanel(),
                    "This message was properly updated!",
                    "Message Board",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /** A method to control specific GUI component actions. */

    private void getEditButtonSemantics() {
        Optional<Tweet> tweet = twitterModel.searchByID(editTweet.getidTextField().getText());
        if (tweet.isEmpty()) {
            JOptionPane.showMessageDialog(editTweet.getMainPanel(),
                    "A message with the given ID does not exist!",
                    "OOPS",
                    JOptionPane.WARNING_MESSAGE);
        } else if (editTweet.getBodyTextField().getText().equals("")) {
            JOptionPane.showMessageDialog(editTweet.getMainPanel(),
                    "If you are going to make the tweet empty...why not delete it?",
                    "WHY?",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            tweet.get().setBody(editTweet.getBodyTextField().getText());
            JOptionPane.showMessageDialog(editTweet.getMainPanel(),
                    "You have successfully edited the tweet!",
                    "YAY!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /** A method to control specific GUI component actions. */

    private void getClearButtonSemantics() {
        editTweet.getBodyTextField().setText(null);
        editTweet.getidTextField().setText(null);
        editTweet.getidTextField().requestFocus();

    }

}

