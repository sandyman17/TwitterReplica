import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddController
 *
 * [your documentation here]
 *
 * @author Jacob Sandefur
 *
 * @version 4/28/2020
 *
 */
public final class AddController {

    private TwitterModel twitterModel;
    private AddTweet addTweet;

    /** Implement the AddController constructor per the instructions in the handout
     * @param twitterModel TwitterModel object connecting the model and controller portions of the MVC paradigm.
     * @param addTweet AddTweet object connecting the view and controller portions of the MVC paradigm.
     */

    public AddController(TwitterModel twitterModel, AddTweet addTweet) throws IllegalArgumentException {
        this.twitterModel = twitterModel;
        this.addTweet = addTweet;

        ActionListener tweetAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAddButtonSemantics();
            }
        };
        ActionListener clearAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getClearButtonSemantics();
            }
        };

        addTweet.getClearButton().addActionListener(clearAL);
        addTweet.getTweetButton().addActionListener(tweetAL);

        if (twitterModel == null) {
            throw new IllegalArgumentException("There is a null argument!");
        }
        if (addTweet == null) {
            throw new IllegalArgumentException("There is a null Argument!");
        }

    }

    /** A method used to verify if a given string is strictly containing numeric values.
     * @param aString A string to verify
     * @return boolean
     */
    public static boolean isNumeric(String aString) {
        if (aString == null) {
            return false;
        } else if (aString.isEmpty()) {
            return false;
        } else if (aString.indexOf(".") != aString.lastIndexOf(".")) {
            return false;
        } else {
            int counter = 0;
            for ( char c : aString.toCharArray()) {
                if ( Character.isDigit(c)) {
                    counter++;
                }
            }

            return counter == aString.length();

        }
    }

    /** A method to control specific GUI component actions. */

    private void getAddButtonSemantics() {
        if (addTweet.getidTextField().getText().equals("")) {
            JOptionPane.showMessageDialog(addTweet.getMainPanel(),
                    "The specified ID is not a valid number!",
                    "OOPS!",
                    JOptionPane.WARNING_MESSAGE);
        } else if (Integer.parseInt(addTweet.getidTextField().getText()) > -1) {
            if (twitterModel.searchByID(addTweet.getidTextField().getText()).isPresent()) {
                JOptionPane.showMessageDialog(addTweet.getMainPanel(),
                        "That ID has already been used!",
                        "OOPS!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                Tweet newTweet = new Tweet(addTweet.getidTextField().getText(), addTweet.getBodyTextField().getText());
                twitterModel.add(newTweet);
                JOptionPane.showMessageDialog(addTweet.getMainPanel(),
                        "Yay! Your tweet was sent successfully!",
                        "YAY!",
                        JOptionPane.INFORMATION_MESSAGE);
                addTweet.getidTextField().requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(addTweet.getMainPanel(),
                    "The specified ID is not a valid number!",
                    "OOPS!",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    /** A method to control specific GUI component actions. */

    public void getClearButtonSemantics() {
        addTweet.getBodyTextField().setText(null);
        addTweet.getidTextField().setText(null);
        addTweet.getidTextField().requestFocus();
    }
}
