package fr.nelfdesign.oc_realestatemanager_p_9.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fr.nelfdesign.oc_realestatemanager_p_9.R;

/**
 * Created by Philippe on 21/02/2018.
 */

public class Utils {

    /**
     * Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *Le taux du dollars ayant changé j'ai adapté la valeur
     * @param dollars price in dollars
     * @return price in Euros
     */
    public static int convertDollarToEuro(int dollars) {
        return (int) Math.round(dollars * 0.89);
    }

    /**
     * Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @param euro price in Euro
     * @return price in Dollars
     */
    public static int convertEuroToDollar(int euro) {
        return (int) Math.round(euro * 1.13);
    }

    /**
     * Conversion de la date d'aujourd'hui en un format plus approprié
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @return date formatted
     */
    public static String getTodayDate() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        return dateFormat.format(new Date());
    }

    /**
     * Vérification de la connexion réseau
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @param context
     * @return
     */
    public static Boolean isInternetAvailable(Context context) {
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifi != null && wifi.isWifiEnabled();
    }

    public static void makeSnackbar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static String formatNumber(Double number){
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(2);
        f.setGroupingSize(3);
        return f.format(number);
    }

    /**
     *
     * @param text tobe checked
     * @return true if able
     */
    public static boolean checkEditTextInput(String text){
        return text != null && !text.equals("");
    }


    public static void stateChip(Chip chip, Context context) {
        if (chip.isChecked()) {
            chip.setTextColor(ContextCompat.getColor(context, R.color.accent));
            chip.setChipIconTintResource(R.color.accent);
        } else {
            chip.setTextColor(ContextCompat.getColor(context, R.color.icons));
            chip.setChipIconTintResource(R.color.icons);
        }
    }

    public static int checkData(String text){
        int number;
        if (!text.equals("")){
            number = Integer.parseInt(text);
        }else{
            number = 0;
        }
        return number;
    }

    public static int checkMaxData(String text){
        int number;
        if (text.equals("")){
            number = Integer.MAX_VALUE;
        }else number = Math.min(Integer.parseInt(text), Integer.MAX_VALUE);
        return number;
    }

}
