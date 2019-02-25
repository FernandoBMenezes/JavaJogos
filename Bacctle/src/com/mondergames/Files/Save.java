package com.mondergames.Files;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Save {
	public static String defaultLocal = getDefaultDisk() + "Users/"+ System.getProperty("user.name") +"/AppData/Roaming/Bacttle.save";
	public static MFile dpack;
	public static void defaultPacked() {
		dpack = new MFile(defaultLocal);
		if(!dpack.exist()) {
			dpack.create();
			dpack.write("phase:0;\n" + 
					    "speed:0.033\n" + 
					    "delayAttack:60\n" + 
					    "bacney:0\n");
		}
	}
	public static String getDefaultDisk() {
		for (Path root : FileSystems.getDefault().getRootDirectories()) {
			return root.toString().replace("\\", "/");
		}
		return "C:/";
	}
	public static void loadFolders() {
		defaultPacked();
		defaultValues();
	}
	public static void defaultValues() {

	}
	public static String[] getValue(String value) {
		String[] sv = {"" , ""};
		sv[0] = value;
		sv[1] = dpack.readLine(value);
		sv[1] = sv[1].replaceFirst(value, "");
		sv[1] = sv[1].replaceFirst(":", "");
		sv[1] = sv[1].replaceFirst(";", "");
		return sv;
	}
	public static void setValue(String prefix,String value) {
		String line1 = dpack.readLine(prefix);
		String[] line2 = line1.split(":");
		String linef = line1.replace(line2[1], value);
		dpack.write(dpack.readFile().replace(line1, linef));
	}
}
