package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher;

public class QuotespagePage {
    public ViewInteraction openQuotespage;
    public ViewInteraction mainMenuButton;
    public ViewInteraction mainMenuElementNews;
    public ViewInteraction mainMenuElementMainPage;
    public ViewInteraction expandQuote8Button;
    public ViewInteraction quote8Card;
    public ViewInteraction quote8Text;
    public ViewInteraction listOfQuotes;
    private static final String MENU_ITEM_NEWS = "Новости";
    private static final String MENU_ITEM_MAIN_PAGE = "Главная";
    private static final String MISSION_TEXT = "Главное - жить любя";
    public static final String QUOTE8_TEXT = "Важен каждый!";
    public static final String ALL_QUOTE_TEXT =
            "\"Каждый, кто оказывается в стенах хосписа, имеет огромное значение в жизни хосписа и его подопечных\"";


    public QuotespagePage() {
        openQuotespage = onView(allOf(
                withId(R.id.our_mission_title_text_view),
                withText(MISSION_TEXT)
        ));
        mainMenuButton = onView(withId(R.id.main_menu_image_button));
        mainMenuElementNews = onView(withText(MENU_ITEM_NEWS)).inRoot(isPlatformPopup());

        mainMenuElementMainPage = onView(withText(MENU_ITEM_MAIN_PAGE)).inRoot(isPlatformPopup());
        quote8Card = onView(quote8CardMatcher());
        expandQuote8Button = onView(allOf(
                withId(R.id.our_mission_item_open_card_image_button),
                isDescendantOfA(quote8CardMatcher())
        ));
        quote8Text = onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                withText(ALL_QUOTE_TEXT),
                isDescendantOfA(quote8CardMatcher())
        ));
        listOfQuotes = onView(CustomViewMatcher.recyclerViewSizeMatcher(8));

    }

    private static org.hamcrest.Matcher<android.view.View> quote8CardMatcher() {
        return allOf(
                withId(R.id.our_mission_item_material_card_view),
                hasDescendant(allOf(
                        withId(R.id.our_mission_item_title_text_view),
                        withText(QUOTE8_TEXT)
                ))
        );
    }

    private void scrollToQuote8() {
        onView(withId(R.id.our_mission_item_list_recycler_view)).perform(
                RecyclerViewActions.scrollTo(hasDescendant(withText(QUOTE8_TEXT)))
        );
    }

    public void getQuotespage() { openQuotespage.check(matches(isDisplayed())); }
    public void getQuote8Card() {
        scrollToQuote8();
        quote8Card.check(matches(isDisplayed()));
    }
    public void clickExpandQuote8Button() {
        scrollToQuote8();
        expandQuote8Button.perform(click());
    }
    public void getQuote8Text() {
        scrollToQuote8();
        quote8Text.check(matches(isDisplayed()));
    }
    public void clickMainMenuButton() {mainMenuButton.perform(click()); }
    public void clickMainMenuElementNews() {
        mainMenuElementNews.check(matches(isDisplayed()));
        mainMenuElementNews.perform(click());
    }

    public void clickMainMenuElementMainPage() {
        mainMenuElementMainPage.check(matches(isDisplayed()));
        mainMenuElementMainPage.perform(click());
    }

    public void getListOfQuotes() { listOfQuotes.check(matches(isDisplayed())); }
}
