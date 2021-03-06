package com.jmvincenti.marvelcharacters.ui.utils

import android.content.Context
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri


class UiUtils {
    companion object {
        fun openLink(context : Context, url : String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }
    }
}