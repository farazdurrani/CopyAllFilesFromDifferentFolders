import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 
 * This class copies all files from many different folders into one folder.
 * 
 * @author faraz
 *
 */
public class CopyFiles {

    private static final String JSON = ".json";
    private static final String HTML = ".html";
    private static final String DOT = ".";
    private static final String FILE_SEPERATOR = File.separator;

    public static void main(String[] args) throws IOException {
	Path sourcePath = Paths.get("/home/faraz/Desktop/google");
	Path destinationePath = Paths.get("/home/faraz/Desktop/pics_vids_from_abbo_phone/");
	Files.walk(sourcePath).filter(Files::isRegularFile)
	        .filter(file -> notJsonOrHtmlFile(file))
	        .forEach(source -> copy(source, destinationePath));
    }

    private static boolean notJsonOrHtmlFile(final Path file) {
	return !file.getFileName().toString()
	        .substring(file.getFileName().toString().lastIndexOf(DOT)).equals(JSON)
	        && !file.getFileName().toString()
	                .substring(file.getFileName().toString().lastIndexOf(DOT))
	                .equals(HTML);
    }

    private static void copy(final Path sourcePath, Path destinationPath) {
	try {
	    Files.copy(sourcePath, createDestinationPath(sourcePath, destinationPath),
	            StandardCopyOption.REPLACE_EXISTING);
	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage(), e);
	}
    }

    private static Path createDestinationPath(Path sourcepath, Path destinationpath) {
	return Paths.get(destinationpath.toString()
	        .concat(FILE_SEPERATOR + sourcepath.getFileName().toString()));
    }
}
