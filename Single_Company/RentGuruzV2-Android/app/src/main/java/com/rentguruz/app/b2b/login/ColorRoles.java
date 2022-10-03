package com.rentguruz.app.b2b.login;

import android.content.Context;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.rentguruz.app.b2b.model.base.UserData;

public  final class ColorRoles {
    public final static int accent = Integer.valueOf(UserData.UiColor.primary);
    private final int onAccent;
    private final int accentContainer;
    private final int onAccentContainer;
    private final int colorCustom1;

    public ColorRoles() {
       // accent = Integer.valueOf(UserData.UiColor.primary);
        onAccent = Integer.valueOf(UserData.UiColor.primary);
        accentContainer = Integer.valueOf(UserData.UiColor.primary);
        onAccentContainer = Integer.valueOf(UserData.UiColor.primary);
        colorCustom1 = Integer.valueOf(UserData.UiColor.primary);
    }


}
