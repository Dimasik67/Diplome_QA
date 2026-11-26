package ru.edu.qamid.fmhandroid.ui.data;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.UiAutomation;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityEvent;

import androidx.test.platform.app.InstrumentationRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ловит текстовые Toast через accessibility-события системы.
 *
 * <p>Начиная с Android 11 (API 30) при targetSdk >= 30 текст Toast рисуется
 * системным процессом, поэтому такое окно не видно Espresso. Событие
 * TYPE_NOTIFICATION_STATE_CHANGED приходит от системы и доступно через
 * UiAutomation instrumentation-теста.</p>
 */
public final class ToastRecorder {
    private static final long DEFAULT_TIMEOUT_MS = 5000L;

    private static final List<String> MESSAGES =
            Collections.synchronizedList(new ArrayList<String>());

    private static final UiAutomation.OnAccessibilityEventListener LISTENER = event -> {
        if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            return;
        }
        for (CharSequence text : event.getText()) {
            addMessage(text);
        }
        Parcelable data = event.getParcelableData();
        if (data instanceof Notification) {
            Notification notification = (Notification) data;
            addMessage(notification.tickerText);
            Bundle extras = notification.extras;
            if (extras != null) {
                addMessage(extras.getCharSequence(Notification.EXTRA_TEXT));
                addMessage(extras.getCharSequence(Notification.EXTRA_TITLE));
                addMessage(extras.getCharSequence(Notification.EXTRA_TITLE_BIG));
                addMessage(extras.getCharSequence(Notification.EXTRA_BIG_TEXT));
            }
        }
    };

    private static boolean started;

    private ToastRecorder() {
    }

    public static synchronized void start() {
        if (started) {
            clear();
            return;
        }
        UiAutomation uiAutomation = InstrumentationRegistry.getInstrumentation().getUiAutomation();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 0;
        uiAutomation.setServiceInfo(info);
        uiAutomation.setOnAccessibilityEventListener(LISTENER);
        started = true;
        clear();
    }

    public static synchronized void stop() {
        if (!started) {
            return;
        }
        UiAutomation uiAutomation = InstrumentationRegistry.getInstrumentation().getUiAutomation();
        uiAutomation.setOnAccessibilityEventListener(null);
        started = false;
        clear();
    }

    public static void clear() {
        synchronized (MESSAGES) {
            MESSAGES.clear();
        }
    }

    public static void checkMessage(String message) {
        checkMessage(message, DEFAULT_TIMEOUT_MS);
    }

    public static void checkMessage(String message, long timeoutMs) {
        if (!started) {
            throw new AssertionError("ToastRecorder не запущен: вызовите ToastRecorder.start() в @Before");
        }
        long endTime = System.currentTimeMillis() + timeoutMs;
        do {
            if (containsMessage(message)) {
                return;
            }
            SystemClock.sleep(50L);
        } while (System.currentTimeMillis() < endTime);

        throw new AssertionError("Toast с текстом <" + message + "> не отображался в течение "
                + timeoutMs + " мс. Получено: " + snapshot());
    }

    private static void addMessage(CharSequence text) {
        if (text != null && text.length() > 0) {
            MESSAGES.add(text.toString());
        }
    }

    private static boolean containsMessage(String expected) {
        synchronized (MESSAGES) {
            for (String actual : MESSAGES) {
                if (actual.contains(expected)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String snapshot() {
        synchronized (MESSAGES) {
            return MESSAGES.toString();
        }
    }
}
