package fr.nelfdesign.oc_realestatemanager_p_9.utils;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by Nelfdesign at 30/01/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.utils
 */
public class UtilsTest {

    @Test
    public void convertDollarToEuro() {
        int dollar = 100;
        assertEquals(81, Utils.convertDollarToEuro(dollar));
    }

    @Test
    public void convertEuroToDollar() {
        int euro = 100;
        assertEquals(110, Utils.convertEuroToDollar(euro));
    }

    @Test
    public void getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        String date = dateFormat.format(new Date());
        assertEquals(date, Utils.getTodayDate());
    }
}