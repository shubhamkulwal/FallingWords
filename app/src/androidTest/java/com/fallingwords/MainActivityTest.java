package com.fallingwords;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;
import com.fallingwords.model.WordItem;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;


/**
 * Created by b_ashish on 10/08/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * A dummy test that just checks that the Instrumentation was correctly injected
     */
    @Test
    public void dummyTest() {
        // Check that Instrumentation was correctly injected in setUp()
        assertThat(InstrumentationRegistry.getInstrumentation(), notNullValue());
    }


    @Test
    public void populateScoreCardTest() {
        MainActivity mainActivity = mActivityRule.getActivity();
        TextView scoreCard = (TextView) mainActivity.findViewById(R.id.score_card_label);
        TextView gameStatus = (TextView) mainActivity.findViewById(R.id.game_over_status);

        WordSession session = WordGameHolder.getInstance().getSession();

        assertThat(scoreCard, notNullValue());
        assertThat(gameStatus, notNullValue());
        assertThat(session, notNullValue());
    }

    @Test
    public void checkPreconditions() {
        // Check fragments exists
        assertNotNull(mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.description_fragment));

        assertNotNull(mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.word_list_fragment));

        WordListFragment wordListFragment = (WordListFragment) mActivityRule.getActivity()
                .getFragmentManager().findFragmentById(R.id.word_list_fragment);

        ViewMatchers.assertThat("The list fragment model should be an instance of WordItem",
                wordListFragment.getListView().getSelectedItem(), instanceOf(WordItem.class));
    }

    @Test
    public void descriptionFragmentWelcomeSection_IsVisible() {
        onView(withId(R.id.welcome_container)).check(matches(isDisplayed()));
    }


    @Test
    public void descriptionFragmentGameSection_IsNotVisible() {
        onView(withId(R.id.game_container)).check(matches(not(isDisplayed())));
    }

    @Test
    public void descriptionFragmentResultSection_IsNotVisible() {
        onView(withId(R.id.result_container)).check(matches(not(isDisplayed())));
    }

}
