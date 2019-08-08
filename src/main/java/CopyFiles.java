import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 
 * This class copies all files from many different folders into one folder.
 * @author faraz
 *
 */
public class CopyFiles {
	public static void main(String[] args) throws IOException {
		Path sourcepath = Paths.get("/home/faraz/Desktop/googlephotos");
		Path destinationepath = Paths.get("/home/faraz/Desktop/allfilesinonefolder/");
		Files.walk(sourcepath).filter(Files::isRegularFile).filter(file -> notJsonFile(file))
				.forEach(source -> copy(source, destinationepath));
	}

	private static boolean notJsonFile(Path file) {
		return !file.getFileName().toString().substring(file.getFileName().toString().lastIndexOf(".")).equals(".json");
	}

	private static void copy(Path source, Path dest) {
		try {
			dest = Paths.get(dest.toString().concat(File.separator + source.getFileName().toString()));
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
