package fr.nelfdesign.oc_realestatemanager_p_9.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.DetailProperty
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.DetailProperty.Companion.PROPERTY_ID
import timber.log.Timber

/**
 * Created by Nelfdesign at 11/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.notification
 */
class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val propertyId = intent.getIntExtra(PROPERTY_ID, 1)
        startIntent(context, propertyId)
    }

    private fun startIntent(context : Context, propertyId : Int) {
        val intent = Intent(context, DetailProperty::class.java)
        intent.putExtra(PROPERTY_ID, propertyId)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}