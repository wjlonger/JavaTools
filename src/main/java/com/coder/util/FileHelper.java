package com.coder.util;

import java.io.*;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;

/**
 * @author WJL
 */
public final class FileHelper {

	protected static ResourceBundle labels = ResourceBundle.getBundle("com.coder.util.FileHelper",  Locale.getDefault());

	public static void move(File from, File to) throws IOException {
		move(from, to, false);
	}

	public static void move(File from, File to, boolean overwrite) throws IOException {
		if (to.exists()){
			if (overwrite){
				if (!to.delete()){
					throw new IOException(
						MessageFormat.format(
							labels.getString("deleteerror"),
							(Object[])new String[] {
								to.toString()
							}
						)
					);
				}
			} else {
				throw new IOException(
					MessageFormat.format(
						labels.getString("alreadyexistserror"),
						(Object[])new String[] {
							to.toString()
						}
					)
				);
			}
		}

		if (from.renameTo(to)) {
			return;
		}

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			copy(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
			if (!from.delete()){
				throw new IOException(
					MessageFormat.format(
						labels.getString("deleteoriginalerror"),
						(Object[])new String[] {
							from.toString(),
							to.toString()
						}
					)
				);
			}
		} finally {
			if (in != null){
				in.close();
				in = null;
			}
			if (out != null){
				out.flush();
				out.close();
				out = null;
			}
		}
	}

	private final static int BUFFER_SIZE = 1024;

	private static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}
}
