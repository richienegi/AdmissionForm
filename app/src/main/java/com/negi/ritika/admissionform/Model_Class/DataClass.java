package com.negi.ritika.admissionform.Model_Class;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class DataClass {
    public static String month[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    public static ArrayList<String> courses = new ArrayList<>();
    public static ArrayList<Bitmap> docs = new ArrayList<>();

    public static Bitmap user_image;

    public static String user_f_name="";
    public static String user_m_name="";
    public static String user_l_name="";
    public static String father_f_name="";
    public static String father_m_name="";
    public static String father_l_name="";
    public static String mother_f_name="";
    public static String mother_m_name="";
    public static String mother_l_name="";
    public static String user_gender="";
    public static String user_dob="";
    public static String user_mobile="";
    public static String user_whatsapp="";
    public static String user_address ="";
    public static String user_email="";
    public static String father_mobile="";
    public static String mother_mobile="";
    public static String user_qualification="";
    public static String user_school="";
    public static String user_passing="";
    public static String company_name="";
    public static String designation="";
    public static String website="";
    public static String college_name="";
    public static String college_address="";
    public static String passout_or_student="";
    public static String source="";
    public static String total_fees="";
    public static String reg_fees="";
    public static String installments="";


    public static void clearAllData()
    {
        courses.clear();
        docs.clear();
        user_f_name="";
        user_m_name="";
        user_l_name="";
        father_f_name="";
        father_m_name="";
        father_l_name="";
        mother_f_name="";
        mother_m_name="";
        mother_l_name="";
        user_gender="";
        user_dob="";
        user_mobile="";
        user_whatsapp="";
        user_address ="";
        user_email="";
        father_mobile="";
        mother_mobile="";
        user_qualification="";
        user_school="";
        user_passing="";
        company_name="";
        designation="";
        website="";
        college_name="";
        college_address="";
        passout_or_student="";
        source="";
    }
}
