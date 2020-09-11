import java.io.File;
import java.nio.file.*;

public class fileArranger {
    public static void main(String[] args) {
        String path = "C:\\Users\\mahes\\Downloads";
        String destination = "C:\\Users\\mahes\\Desktop\\New folder\\";
        getExtensions(destination, path);
    }

    private static void getExtensions(String destination, String file_path) {
        File folder = new File(file_path);
        try {
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    boolean status = fileHandler(file, destination);
                    if (!status) {
                        System.out.println("You got a problem dear");
                        break;
                    }
                } else if (file.isDirectory()) {
                    String temp = file.getName();
                    temp = file_path + "\\" + temp;
                    getExtensions(destination, temp);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Folder is empty kid");
        }
    }

    private static boolean checkIfDirExist(String dest, String name) {
        String dest_add = dest + "\\" + name;
        Path path = Paths.get(dest_add);
        if (!Files.exists(path)) {
            File file = new File(dest_add);
            boolean isCreated = file.mkdirs();
            if (!isCreated) {
                System.out.println("Folder not getting created");
            }
        }
        return true;
    }

    private static boolean copyFiles(File file, String dest, String extension) {
        Path source = Paths.get(file.toString());
        String destination = file.getName();
        destination = dest + extension + "\\" + destination;
        File check = new File(destination);
        if (check.exists()) {
            System.out.println(check.getName());
            return true;
        }
        Path target = Paths.get(destination);
        try {
            Files.copy(source, target);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    private static boolean fileHandler(File file, String destination) {
        String a = file.getName();
        String extension = a.substring(a.lastIndexOf(".") + 1);
        if (extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("png"))
            extension = "Images";
        else if (extension.equalsIgnoreCase("zip"))
            extension = "ZIP files";
        else if (extension.equalsIgnoreCase("exe") || extension.equalsIgnoreCase("msi"))
            extension = "Installer";
        else if (extension.equalsIgnoreCase("C"))
            extension = "C codes";
        else if (extension.equalsIgnoreCase("pdf"))
            extension = "PDF doc";
        else if (extension.equalsIgnoreCase("java"))
            extension = "Java codes";
        else if (extension.equalsIgnoreCase("py"))
            extension = "Python codes";
        else if (extension.equalsIgnoreCase("mp3"))
            extension = "Music";
        else if (extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("m4a")
                || extension.equalsIgnoreCase("avi"))
            extension = "Videos";
        else if (extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx"))
            extension = "Word Files";
        else if (extension.equalsIgnoreCase("ppt") || extension.equalsIgnoreCase("pptx"))
            extension = "Powerpoint slides";
        else if (extension.equalsIgnoreCase("xlsx"))
            extension = "Excel Files";
        else if (extension.equalsIgnoreCase("txt"))
            extension = "Notepad files";

        boolean status = checkIfDirExist(destination, extension);
        if (status) {
            boolean file_status = copyFiles(file, destination, extension);
            if (!file_status) {
                System.out.println("Kid there is a problem in copying files");
            }
        }
        return true;
    }
}