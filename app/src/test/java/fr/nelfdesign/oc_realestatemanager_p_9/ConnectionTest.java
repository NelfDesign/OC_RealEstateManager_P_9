package fr.nelfdesign.oc_realestatemanager_p_9;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowNetworkInfo;

import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.MainActivity;
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Nelfdesign at 25/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9
 */
@RunWith(RobolectricTestRunner.class)
public class ConnectionTest {

    private ShadowNetworkInfo shadowOfActiveNetworkInfo;
    private ActivityController<MainActivity> activity;

    @Before
    public void setUp() throws Exception {
        ConnectivityManager connectivityManager = getConnectivityManager();
        shadowOfActiveNetworkInfo = Shadows.shadowOf(connectivityManager.getActiveNetworkInfo());
        activity = Robolectric.buildActivity(MainActivity.class);
    }

    @Test
    public void testConnectionEnable() {
        shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.CONNECTED);
        assertTrue(Utils.isInternetAvailable(activity.get()));
    }

    @Test
    public void testConnectionDisable(){
        shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.DISCONNECTED);
        assertTrue(Utils.isInternetAvailable(activity.get()));
    }

    private ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
