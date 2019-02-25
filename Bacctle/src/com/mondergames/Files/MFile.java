package com.mondergames.Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class MFile {
	private String local;
	private File file;

	/** @author Fernando B.M(Monderdragon) */
	public MFile(String localFile) {
		this.setLocal(localFile);
		this.setFile(new File(localFile));
	}

	/** @return if exist file */
	public boolean exist() {
		return this.getFile().exists();
	}

	/** @return this MFile */
	public MFile getMFile() {
		return this;
	}

	/** @return the MFile of location */
	public static MFile getMFile(String localFile) {
		return new MFile(localFile);
	}

	/** @return the file */
	public File getFile() {
		return this.file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/** @return the local */
	public String getLocal() {
		return this.local;
	}

	/**
	 * @param local
	 *            the local to set
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/** @return the isDir true = is Dir; false = isn't Dir; */
	public boolean isDir() {
		if (this.exist()) {
			return this.getFile().isDirectory();
		} else {
			throw new NullPointerException("File doesn't exist");
		}
	}

	/**
	 * Write String in file
	 * 
	 * @param string
	 *            to write in the file
	 */
	public void write(String string) throws NullPointerException {
		if (this.exist()) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(this.getLocal()));
				writer.write(string);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException("File doesn't exist");
		}
	}

	/**
	 * @param replace
	 *            arg1 to arg2
	 */
	public void replace(String arg1, String arg2) throws NullPointerException {
		if (this.exist()) {
			String s = (readFile() + "").replace(arg1, arg2);
			this.write(s);
		} else {
			throw new NullPointerException("File doesn't exist");
		}
	}

	/**
	 * @param replace
	 *            all arg1 to arg2
	 */
	public void replaceAll(String arg1, String arg2) throws NullPointerException {
		if (this.exist()) {
			String s = (readFile() + "").replaceAll(arg1, arg2);
			this.write(s);
		} else {
			throw new NullPointerException("File doesn't exist");
		}
	}

	/** @return all lines of file; */
	public String readFile() throws NullPointerException {
		if (this.exist()) {
			try {
				FileReader FRead = new FileReader(this.getFile());
				BufferedReader Br = new BufferedReader(FRead);
				StringBuffer read = new StringBuffer();
				String line;
				while ((line = Br.readLine()) != null) {
					read.append(line);
					read.append("\n");
				}
				FRead.close();
				return read.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			throw new NullPointerException("File doesn't exist");
		}
	}

	/**
	 * @param prefix
	 *            arg to search to return line
	 * @return String specific line of file; null: if line = null
	 */
	public String readLine(String prefix) throws NullPointerException {
		if (this.exist()) {
			try {
				FileReader FRead = new FileReader(this.getFile());
				BufferedReader Br = new BufferedReader(FRead);
				StringBuffer read = new StringBuffer();
				String line;
				while ((line = Br.readLine()) != null) {
					if (line.startsWith(prefix)) {
						read.append(line);
					}
				}
				FRead.close();
				return read.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			throw new NullPointerException("File doesn't exist");
		}
	}
	/** Create File */ //FIXME PRECISA SUPORTAR PASTAS COM "." NO NOME
	public void create() {
		if(this.getLocal().contains(".")) {
			Formatter b;
			try {
				b = new Formatter(this.getLocal());
				b.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		this.getFile().mkdirs();
		}
	}
	/** Delete File */
	public void delete() throws NullPointerException {
		if (this.exist()) {
			delete(this.getFile());
		} else {
			throw new NullPointerException("File doesn't exist");
		}
	}

	/** Delete File */
	public static void delete(File fl) throws NullPointerException {
		if (fl.exists() == true) {
			if (fl.isDirectory()) {
				String[] children = fl.list();
				for (int i = 0; i < children.length; i++) {
					delete(new File(fl, children[i]));
				}
			}
			fl.delete();
		}
	}
}
