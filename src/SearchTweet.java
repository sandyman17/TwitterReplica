import javax.swing.*;

/**
 * SearchTweet
 *
 * Serves the view portion of the MVC paradigm, controls the overall look (front end) of the SearchTweet tab.
 *
 * @author Purdue CS
 *
 * @version April 20, 2020
 *
 */
public class SearchTweet {
    private JTextField searchTermTextField;
    private JButton searchButton;
    private JPanel mainPanel;
    private JTextArea idTextArea;

    /** Empty constructor for SearchTweet.java. You are not required to change this. */
    public SearchTweet() {
    }

    /** Public accessor methods for the private variables in SearchTweet.java */

    public JTextArea getidTextArea() { return this.idTextArea; }

    public JTextField getSearchTermTextField() {
        return searchTermTextField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JPanel getMainPanel() { return this.mainPanel; }
}

