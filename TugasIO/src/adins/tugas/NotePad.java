package adins.tugas;

import java.io.File;
import java.util.Scanner;

public class NotePad {
	public void start() {
		Scanner sc = new Scanner(System.in);
		String menu = "";
		String pathDir = "";
		String answer = "";
		String savePath = "";
		int validMenu = 0;
		int lengthDir = 0;
		String str = "";
		boolean isValidMenu = false;
		boolean isSave = true;
		String fileName = "";
		while (true) {
			printMenu();
			do {
				System.out.print("Choose Menu :");
				try {
					menu = sc.nextLine();
					validMenu = Integer.parseInt(menu);
					isValidMenu = false;

					switch (validMenu) {
					case 1: {
						System.out.print("Input Path Directory: ");
						pathDir = sc.nextLine();

						if (new File(pathDir).isFile()) {
							FileManagement.readFile(pathDir);

							do {
								String writeFile = sc.nextLine();
								if (writeFile.equals("save")) {
									isSave = false;
								} else {
									FileManagement
											.writeFile(writeFile, pathDir);
									isSave = true;
								}
							} while (isSave);

						} else if (new File(pathDir).isDirectory()) {
							FileManagement.dirList(pathDir);
							fileName = sc.nextLine();
							lengthDir = pathDir.length();
							if (pathDir.substring(lengthDir - 1).equals("/")
									|| pathDir.substring(lengthDir - 2).equals(
											"\\\\")) {
								pathDir = pathDir + fileName;
							} else {
								pathDir = pathDir + "/" + fileName;
							}

							FileManagement.readFile(pathDir);
							do {
								String writeFile = sc.nextLine();
								if (writeFile.equals("save")) {
									isSave = false;
								} else {
									FileManagement
											.writeFile(writeFile, pathDir);
									isSave = true;
								}
							} while (isSave);
						} else {
							System.out.println("Path Directory '"+pathDir+"' is invalid!... ");
							str = sc.nextLine();
						}

						break;
					}
					case 2: {
						FileManagement.deleteFile("temp_file.txt");
						do {
							String writeFile = sc.nextLine();
							if (writeFile.equals("save")) {
								System.out.print("Input Path Directory: ");
								pathDir = sc.nextLine();

								isSave = false;
								if (!(new File(pathDir).exists())) {
									boolean isExist = false;
									do {
										System.out
												.print("Folder '"
														+ pathDir
														+ "' does not exist. Do You want to create it? (y/n) ");
										answer = sc.nextLine();
										if (answer.equalsIgnoreCase("y")) {
											isExist = false;

											if (new File(pathDir).mkdirs()) {
												new File(pathDir).mkdirs();
												System.out
														.println("Folder '"+pathDir+"' created!... ");
												FileManagement.dirList(pathDir);
												fileName = sc.nextLine();
												lengthDir = pathDir
														.length();
												if (pathDir.substring(
														lengthDir - 1).equals(
														"/")
														|| pathDir.substring(
																lengthDir - 2)
																.equals("\\\\")) {
													pathDir = pathDir
															+ fileName;
												} else {
													pathDir = pathDir + "/"
															+ fileName;
												}

												FileManagement.renameFile(
														"temp_file.txt",
														pathDir);
												System.out
														.println("File '"+pathDir+"' successfully saved..");
												str = sc.nextLine();
											} else {
												System.out
														.println("Path Directory '"+pathDir+"' is invalid!... ");
												str = sc.nextLine();
												isExist = false;
												isSave = true;
												FileManagement
														.readFile("temp_file.txt");
											}
										} else if (answer.equalsIgnoreCase("n")) {
											isExist = false;
											isSave = true;
											FileManagement
													.readFile("temp_file.txt");
										} else {
											isExist = true;
										}
									} while (isExist);

								} else {
									boolean isFileSaved = false;
									do {
										savePath = pathDir;
										FileManagement.dirList(pathDir);
										fileName = sc.nextLine();
										lengthDir = savePath.length();
										if (savePath.substring(lengthDir - 1)
												.equals("/")
												|| savePath.substring(
														lengthDir - 2).equals(
														"\\\\")) {
											savePath = savePath + fileName;
										} else {
											savePath = savePath + "/"
													+ fileName;
										}

										if (FileManagement.renameFile(
												"temp_file.txt", savePath)) {
											FileManagement.renameFile(
													"temp_file.txt", savePath);
											System.out
													.println("File '"+savePath+"' successfully saved..");
											str = sc.nextLine();
											isFileSaved = false;
										} else {
											System.out
													.print("File '"
															+ savePath
															+ "' already exist. Do You want to replace it? (y/n) ");
											answer = sc.nextLine();
											if (answer.equalsIgnoreCase("y")) {
												FileManagement
														.deleteFile(savePath);
												System.out.println(savePath);
												FileManagement.renameFile(
														"temp_file.txt",
														savePath);
												System.out
														.println("File '"+savePath+"' replaced..");
												str = sc.nextLine();
												isFileSaved = false;
											} else {
												isFileSaved = true;
											}
										}

									} while (isFileSaved);

								}

							} else {
								FileManagement.writeFile(writeFile,
										"temp_file.txt");
								isSave = true;
							}
						} while (isSave);
						break;
					}
					case 3: {
						System.out.println("Notepad Closed! Thank You..");
						System.exit(0);
						break;
					}
					default: {
						isValidMenu = true;
						break;
					}

					}
				} catch (NumberFormatException e) {
					isValidMenu = true;
				} catch (Exception e) {
					str = sc.nextLine();
					isValidMenu = false;
				}

			} while (isValidMenu);
		}
	}

	private static void printMenu() {
		System.out.println("===============");
		System.out.println("Menu");
		System.out.println("===============");
		System.out.println();
		System.out.println("1) Read File");
		System.out.println("2) New File");
		System.out.println("3) Exit");
	}

}
