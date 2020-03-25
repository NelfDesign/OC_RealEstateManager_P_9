package fr.nelfdesign.oc_realestatemanager_p_9.utils;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import org.junit.Test;

import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils.*;
import static org.junit.Assert.*;

/**
 * Created by Nelfdesign at 30/01/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.utils
 */
public class UtilsTest {

    @Test
    public void convertDollarToEuroTest() {
        int dollar = 100;
        assertEquals(81, convertDollarToEuro(dollar));
    }

    @Test
    public void convertEuroToDollarTest() {
        int euro = 100;
        assertEquals(110, convertEuroToDollar(euro));
    }

    @Test
    public void getTodayDateTest() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        String date = dateFormat.format(new Date());
        assertEquals(date, getTodayDate());
    }

    @Test
    public void formatNumberTest(){
        char espace = new DecimalFormatSymbols().getGroupingSeparator();
        Double number = 112.2569;
        Double number2 = 112000.25;

        assertEquals("112,26", formatNumber(number));
        assertEquals("112"+ espace +"000,25", formatNumber(number2));
        assertNotEquals("112,256", formatNumber(number));
    }

    @Test
    public void checkEditTextInputTest(){
        String text = "";
        String text2 = "Bonjour";

        assertTrue(checkEditTextInput(text2));
        assertFalse(checkEditTextInput(text));
    }

    @Test
    public void checkDataTest(){
       String text = "125";
       String text2 = "";

       assertEquals(125 , checkData(text));
       assertEquals(0 , checkData(text2));
       assertNotEquals(125 , checkData(text2));
    }

    @Test
    public void checkMaxDataTest(){
        String text = "125";
        String text2 = "";

        assertEquals(125 , checkMaxData(text));
        assertEquals(Integer.MAX_VALUE , checkMaxData(text2));
    }

    @Test
    public void buildTextAddressTest(){
        String street = "21 rue Mazat";
        String town = "Marseille";

        assertEquals( "21 rue Mazat, Marseille", buildTextAddress(street, town));
        assertNotEquals( "21rueMazat,Marseille", buildTextAddress(street, town));
    }

    @Test
    public void makeStreetStringTest(){
        String street = "21 rue Mazat";
        String town = "Marseille";

        assertEquals("21+rue+mazat,marseille", makeStreetString(street, town));
        assertNotEquals("21+rue+mazat,marseille+", makeStreetString(street, town));
        assertNotEquals("21+rue+Mazat,Marseille+", makeStreetString(street, town));
    }
}