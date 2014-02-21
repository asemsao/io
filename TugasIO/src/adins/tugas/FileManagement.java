package adins.tugas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManagement {
	
	public static void dirList(String pathDir) {
		File path = new File(pathDir);
		System.out.println();
		System.out.println(path);
		System.out.println("-----------------");
		System.out.println("Directory List :");
		System.out.println("-----------------");
		String[] list = path.list();
		for (int i = 0; i < list.length; i++) {
			System.out.print("|_");
			System.out.println(list[i]);
		}
		path = null;
		System.out.println();
		System.out.print("Input File Name : ");
	}

	public static void readFile(String pathDir) {
		System.out.println();
		File file = new File(pathDir);

		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("File '"+pathDir+"' does not exist!... ");
		}

		BufferedReader input = new BufferedReader(fileReader);
		String message = null;
		try {
			System.out.println("-----------------");
			if (pathDir.equals("temp_file.txt")) {
				System.out.println("New File");
			} else {
				System.out.println(pathDir);
				System.out.println("Opening file.... ");
			}
			System.out.println("-----------------");
			while ((message = input.readLine()) != null) {
				System.out.println(message);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	public static void writeFile(String write, String pathFile) {
		StringBuffer content = new StringBuffer();
		content.append(write);
		File file = new File(pathFile);
		FileWriter fileWriter = null;
		PrintWriter out = null;
		try {
			fileWriter = new FileWriter(file, true);
			out = new PrintWriter(new BufferedWriter(fileWriter));
			for (int i = 0; i < 1; i++)
				out.println(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean renameFile(String oldFile, String newFile) {
		File file = new File(oldFile);
		boolean isRename = false;
		if (file.renameTo(new File(newFile))) {
			file.renameTo(new File(newFile));
			isRename = true;
		} else {
			isRename = false;
		}
		return isRename;
	}

	public static boolean deleteFile(String newFile) {
		File file = new File(newFile);
		boolean isDelete = false;
		if (file.delete()) {
			isDelete = true;
		} else {
			isDelete = false;
		}
		return isDelete;
	}
}
