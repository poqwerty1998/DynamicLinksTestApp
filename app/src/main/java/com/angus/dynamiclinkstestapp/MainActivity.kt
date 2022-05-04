package com.angus.dynamiclinkstestapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(
                this
            ) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    // link data
                    deepLink = pendingDynamicLinkData.link
                }

                if (deepLink != null) {
                    // first parameter
                    val msgParameter = deepLink.getQueryParameters("msg")?.get(0)
                    Toast.makeText(this, msgParameter, Toast.LENGTH_LONG).show()
                }

                // Handle the deep link. For example, open the linked
                // content, or apply promotional credit to the user's
                // account.
                // ...

                // ...
            }
            .addOnFailureListener(
                this
            ) { e -> Log.w("ERROR", "getDynamicLink:onFailure", e) }
    }
}