package com.tuannv007.gplxb2;

import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.tuannv007.gplxb2.data.model.Question;

import java.util.Random;

import androidx.databinding.BindingAdapter;

public class BindingUtils {
    @BindingAdapter("setTextHtml")
    public static void setTextHtml(TextView view, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
        } else {
            view.setText(Html.fromHtml(text));
        }
    }

}
