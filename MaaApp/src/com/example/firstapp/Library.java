package com.example.firstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

/**
 * 
 * The class of global constants and commonly used methods in the project
 * 
 */
public class Library {
	/* Library constants */
	public static String ERROR = "error";
	public static String EXIST = "exist";
	public static String serverUrl = "http://10.0.2.2:8080/MaaServer/";

	/* Library methods */
	/**
	 * Converts the string starting with numbers to the number it represents
	 * @param string A string starting with number characters
	 * @return the integer the string represents
	 */
	public static int parseInt(String string) {
		int index = 0;
		while (string.charAt(index) >= '0' && string.charAt(index) <= '9') {
			index++;
		}
		return Integer.parseInt(string.substring(0, index));
	}
	/**
	 * Creates an alert dialog showing the message given
	 * @param activity
	 * @param message the message appearing on the dialog
	 */
	public static void showAlert(Activity activity, String message) {
		Builder b = new AlertDialog.Builder(activity);
		b.setMessage(message);
		b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog d = b.create();
		d.show();
	}
}
