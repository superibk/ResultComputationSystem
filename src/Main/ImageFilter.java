package Main;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import org.h2.util.Utils;

public class ImageFilter extends FileFilter{
	

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
	        return true;
	    }

	    String extension = Utils.getExtension(file);
	    if (extension != null) {
	        if (extension.equals(Utils.tiff) ||
	            extension.equals(Utils.tif) ||
	            extension.equals(Utils.gif) ||
	            extension.equals(Utils.jpeg) ||
	            extension.equals(Utils.jpg) ||
	            extension.equals(Utils.png)) {
	                return true;
	        } else {
	            return false;
	        }
	    }

	    return false;
	}

	@Override
	public String getDescription() {
		
		return null;
	}
	
	public static class Utils {

	    public final static String jpeg = "jpeg";
	    public final static String jpg = "jpg";
	    public final static String gif = "gif";
	    public final static String tiff = "tiff";
	    public final static String tif = "tif";
	    public final static String png = "png";

	    /*
	     * Get the extension of a file.
	     */  
	    public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }
	}
	
	

}
