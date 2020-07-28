import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * SearchController
 *
 * [your documentation here]
 *
 * @author Jacob Sandefur
 *
 * @version 4/28/2020
 *
 */
public class SearchController {

    private TwitterModel twitterModel;
    private SearchTweet searchTweet;

    /** Implement the SearchController constructor per the instructions in the handout
     * @param twitterModel TwitterModel object connecting the model and controller portions of the MVC paradigm.
     * @param searchTweet SearchTweet object connecting the view and controller portions of the MVC paradigm.
     */

    public SearchController(TwitterModel twitterModel, SearchTweet searchTweet) throws IllegalArgumentException {
        this.twitterModel = twitterModel;
        this.searchTweet = searchTweet;

        if (twitterModel == null) {
            throw new IllegalArgumentException("An argument is null!");
        }
        if (searchTweet == null) {
            throw new IllegalArgumentException("An argument is null!");
        }

        ActionListener searchAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSearchButtonSemantics();
            }
        };

        searchTweet.getSearchButton().addActionListener(searchAL);
    }

    /**
     * Gets the output {@code String} for the specified product list.
     * @param tweetList the product list to be converted to an output {@code String}
     * @return the output {@code String} for the specified product list
     */
    private String getOutputString(List<? extends Tweet> tweetList) {
        StringBuilder stringBuilder = new StringBuilder();
        String outString;

        for (Tweet aTweet : tweetList) {
            stringBuilder.append(aTweet).append("\n\n");
        }
        outString = stringBuilder.toString();

        if (outString.length() > 0) {
            outString = stringBuilder.substring(0, stringBuilder.length() - 2);
        }
        return outString;
    }

    /** Searches our TweetList for a given phrase, returning a List containing all Tweets that apply
     * @param body String of text being searched for
     * @return list of applicable Tweets.
     */
    private List searchForString(String body) {
        List<Tweet> list = this.twitterModel.searchByText(body);
        return list;
    }

    /** A method to control specific GUI component actions. */

    private void getSearchButtonSemantics() {
        searchTweet.getidTextArea().setText(null);
        if (searchTweet.getSearchTermTextField().getText().equals("")) {
            JOptionPane.showMessageDialog(searchTweet.getMainPanel(),
                    "Please enter a phrase to search!",
                    "OOPS!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            ArrayList<Tweet> tweetList = (ArrayList<Tweet>) twitterModel.searchByText(
                    searchTweet.getSearchTermTextField().getText()
            );
            if (tweetList.size() > 0) {
                for (Tweet tweet : tweetList) {
                    searchTweet.getidTextArea().append(tweet.getID() + "\n");
                }
                JOptionPane.showMessageDialog(searchTweet.getMainPanel(),
                        "We found " + tweetList.size() + " tweets that contain that phrase!",
                        "YAY!",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(searchTweet.getMainPanel(),
                        "We couldn't find any tweets that contain that phrase!",
                        "OOPS!",
                        JOptionPane.WARNING_MESSAGE);

            }
        }

    }

}

