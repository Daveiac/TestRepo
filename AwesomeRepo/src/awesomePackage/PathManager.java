package awesomePackage;


public class PathManager {
	private String standardPath = (PathManager.class.getProtectionDomain().getCodeSource().getLocation().getPath()).substring(1);
	
	public PathManager() {
		if (System.getProperty("os.name").equals("Linux")) {
			standardPath = "/" + standardPath.substring(0, standardPath.length() - 1);
		} else if (System.getProperty("os.name").toLowerCase().equals("mac")) {
			standardPath = "/" + standardPath.substring(0, standardPath.length() - 1);
		}
	}
	public String getImagePath(String path) {
		return standardPath +  "/../resources/images/" + path;
	}
	public String getLevelPath(String path) {
		return standardPath +  "/../resources/levels/" + path;
	}
}
