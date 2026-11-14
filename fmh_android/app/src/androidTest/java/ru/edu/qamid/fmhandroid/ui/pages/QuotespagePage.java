package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonId;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.AllOf;

import ru.edu.qamid.fmhandroid.R;
import ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher;

public class QuotespagePage {
    public ViewInteraction openQuotespage;
    public ViewInteraction mainMenuButton;
    public ViewInteraction mainMenuElementNews;
    public ViewInteraction mainMenuElementMainPage;
    public ViewInteraction expandQuote8Button;
    public ViewInteraction qoute8Card;
    public ViewInteraction quote8Text;
    public ViewInteraction listOfQuotes;
    private static final String MENU_ITEM_NEWS = "Новости";
    private static final String MENU_ITEM_MAINPAGE = "Главная";
    private static final String MISSION_TEXT = "Главное - жить любя";
    public static final String QUOTE8_TEXT = "Важен каждый!";
    public static final String All_QUOTE_TEXT = "Каждый, кто оказывается в стенах хосписа, имеет огромное значение в жизни хосписа и его подопечных";


    public QuotespagePage() {
        openQuotespage = onView(allOf(
                withId(R.id.our_mission_title_text_view),
                withText(MISSION_TEXT)
        ));
        mainMenuButton = onView(withId(R.id.main_menu_image_button));
        mainMenuElementNews = onView(AllOf.allOf(
                withButtonId(R.id.main_menu_image_button),
                withButtonText(MENU_ITEM_NEWS)));
                withButtonText(MENU_ITEM_ABOUTAPP)));
        mainMenuElementMainPage = onView(AllOf.allOf(
                withButtonId(R.id.main_menu_image_button),
                withButtonText(MENU_ITEM_MAINPAGE)));
        qoute8Card = onView(allOf(
                childAtPosition(withId(R.id.our_mission_item_list_recycler_view), 7),
                withId(R.id.our_mission_item_material_card_view),
                withText(QUOTE8_TEXT)
        ));
        expandQuote8Button = onView(allOf(
                childAtPosition(withId(R.id.our_mission_item_list_recycler_view), 7),
                withId(R.id.our_mission_item_open_card_image_button)
        ));
        quote8Text = onView(allOf(
                childAtPosition(withId(R.id.our_mission_item_list_recycler_view), 7),
                withId(R.id.our_mission_item_description_text_view),
                withText(All_QUOTE_TEXT)
        ));
        listOfQuotes = onView(CustomViewMatcher.recyclerVievSizeMatcher(8));

    }

    public void getQuotespage() { openQuotespage.check(matches(isDisplayed())); }
    public void getQuotes8Card() { qoute8Card.check(matches(isDisplayed())); }
    public void clickExpandQuote8Button() { expandQuote8Button.perform(click()); }
    public void getQuote8Text() { quote8Text.check(matches(isDisplayed())); }
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
