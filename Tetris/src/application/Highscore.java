package application;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Highscore {
	private static final String FILE_NAME = "highscore.txt";
	
	public static void saveHighscore(int score){
		try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static int loadHighscore() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
            return 0; // no score yet
        }
		
		try(Scanner scanner = new Scanner(file)){
			if(scanner.hasNextInt()) {
				return scanner.nextInt();
			}
		}catch (IOException e) {
            e.printStackTrace();
        }
		return 0;
	}
}
