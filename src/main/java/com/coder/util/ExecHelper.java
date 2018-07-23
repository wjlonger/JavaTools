package com.coder.util;

import java.io.*;

public final class ExecHelper {

	public static ExecHelper exec(String[] cmdarray) throws IOException {
		return new ExecHelper(Runtime.getRuntime().exec(cmdarray), null);
	}

	public static ExecHelper exec(String[] cmdarray, String[] envp) throws IOException {
		return new ExecHelper(Runtime.getRuntime().exec(cmdarray, envp), null);
	}

	public static ExecHelper exec(String[] cmdarray, String[] envp, File dir) throws IOException {
		return new ExecHelper(Runtime.getRuntime().exec(cmdarray, envp), null);
	}

	public static ExecHelper exec(String[] cmdarray, String charset) throws IOException {
		return new ExecHelper(Runtime.getRuntime().exec(cmdarray), charset);
	}

	public static ExecHelper exec(String[] cmdarray, String[] envp, String charset) throws IOException {
		return new ExecHelper(Runtime.getRuntime().exec(cmdarray, envp), charset);
	}

	public static ExecHelper exec(String[] cmdarray, String[] envp, File dir, String charset) throws IOException {
		return new ExecHelper(Runtime.getRuntime().exec(cmdarray, envp), charset);
	}

	public static ExecHelper execUsingShell(String command) throws IOException {
		return execUsingShell(command, null);
	}

	public static ExecHelper execUsingShell(String command, String charset) throws IOException {
		if (command == null) {
			throw new NullPointerException();
		}
		String[] cmdarray;
		String os = System.getProperty("os.name");
		if (os.equals("Windows 95") || os.equals("Windows 98") || os.equals("Windows ME")){
			cmdarray = new String[]{"command.exe", "/C", command};
		} else if (os.startsWith("Windows")){
			cmdarray = new String[]{"cmd.exe", "/C", command};
		} else {
			cmdarray = new String[]{"/bin/sh", "-c", command};
		}
		return new ExecHelper(Runtime.getRuntime().exec(cmdarray), charset);
	}

	private ExecHelper(Process process, String charset) throws IOException {
		StringBuffer output = new StringBuffer();
		StringBuffer error = new StringBuffer();

		Reader stdout;
		Reader stderr;

		if (charset == null){
			// This is one time that the system charset is appropriate,
			// don't specify a character set.
			stdout = new InputStreamReader(process.getInputStream());
			stderr = new InputStreamReader(process.getErrorStream());
		} else {
			stdout = new InputStreamReader(process.getInputStream(), charset);
			stderr = new InputStreamReader(process.getErrorStream(), charset);
		}
		char[] buffer = new char[1024];

		boolean done = false;
		boolean stdoutclosed = false;
		boolean stderrclosed = false;
		while (!done){
			boolean readSomething = false;
			// read from the process's standard output
			if (!stdoutclosed && stdout.ready()){
				readSomething = true;
				int read = stdout.read(buffer, 0, buffer.length);
				if (read < 0){
					readSomething = true;
					stdoutclosed = true;
				} else if (read > 0){
					readSomething = true;
					output.append(buffer, 0, read);
				}
			}
			// read from the process's standard error
			if (!stderrclosed && stderr.ready()){
				int read = stderr.read(buffer, 0, buffer.length);
				if (read < 0){
					readSomething = true;
					stderrclosed = true;
				} else if (read > 0){
					readSomething = true;
					error.append(buffer, 0, read);
				}
			}
			// Check the exit status only we haven't read anything,
			// if something has been read, the process is obviously not dead yet.
			if (!readSomething){
				try {
					this.status = process.exitValue();
					done = true;
				} catch (IllegalThreadStateException itx){
					// Exit status not ready yet.
					// Give the process a little breathing room.
					try {
						Thread.sleep(100);
					} catch (InterruptedException ix){
						process.destroy();
						throw new IOException("Interrupted - processes killed");
					}
				}
			}
		}

		this.output = output.toString();
		this.error = error.toString();
	}

	private String output;

	public String getOutput(){
		return output;
	}

	private String error;

	public String getError(){
		return error;
	}

	private int status;

	public int getStatus(){
		return status;
	}
}

