package com.example.notes;


import android.app.Instrumentation;
import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@Metadata(
        mv = {1, 9, 0},
        k = 1,
        d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"},
        d2 = {"Lcom/example/notes/ExampleInstrumentedTest;", "", "()V", "useAppContext", "", "app_debugAndroidTest"}
)
public final class ExampleInstrumentedTest {
    @Test
    public final void useAppContext() {
        Instrumentation var10000 = InstrumentationRegistry.getInstrumentation();
        Intrinsics.checkNotNullExpressionValue(var10000, "InstrumentationRegistry.getInstrumentation()");
        Context appContext = var10000.getTargetContext();
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        Assert.assertEquals("com.example.notes", appContext.getPackageName());
    }
}
