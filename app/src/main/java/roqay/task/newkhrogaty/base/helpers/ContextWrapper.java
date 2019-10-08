package roqay.task.newkhrogaty.base.helpers;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

public class ContextWrapper extends android.content.ContextWrapper {

    public ContextWrapper(Context base) {
        super(base);
    }

    public static ContextWrapper wrap(Context context, Locale newLocale) {
        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();

        /*if (BuildUtils.isAtLeast24Api()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(newLocale);
            }

            LocaleList localeList = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                localeList = new LocaleList(newLocale);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocaleList.setDefault(localeList);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocales(localeList);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(configuration);
            }

        } else if (BuildUtils.isAtLeast17Api()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(newLocale);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(configuration);
            }

        } else {
            configuration.locale = newLocale;
            res.updateConfiguration(configuration, res.getDisplayMetrics());
        }*/

        return new ContextWrapper(context);
    }
}
