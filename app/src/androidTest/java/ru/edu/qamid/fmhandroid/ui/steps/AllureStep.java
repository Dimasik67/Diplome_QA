package ru.edu.qamid.fmhandroid.ui.steps;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Allure.StepContext;
import java.util.function.Supplier;
import kotlin.Unit;

/**
 * Явное логирование шагов Allure для Java-кода.
 *
 * <p>В allure-kotlin аннотация {@code @Step} не обрабатывается (aspect support не перенесён),
 * поэтому шаги нужно оборачивать в {@link Allure#step(String, kotlin.jvm.functions.Function1)}.
 */
public final class AllureStep {

    private AllureStep() {
    }

    /** Выполнить действие как шаг Allure. */
    public static void run(String name, Runnable action) {
        Allure.step(name, (StepContext ctx) -> {
            action.run();
            return Unit.INSTANCE;
        });
    }

    /** Выполнить действие с результатом как шаг Allure. */
    public static <T> T call(String name, Supplier<T> action) {
        return Allure.step(name, (StepContext ctx) -> action.get());
    }
}
