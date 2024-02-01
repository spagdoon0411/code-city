import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileParser{
    private File file;
    private String fStr;
    private ArrayList<DeclaredPart> ooParts;
    public FileParser(String path){
        file = new File(path);
        try{
            Scanner sc = new Scanner(file);
            fStr = "";
            while(sc.hasNextLine()){
                fStr.concat(sc.nextLine()+"\n");
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.err.println("I literally have no idea where that file is");
        }
        removeStrComments();
        ooParts = new ArrayList<DeclaredPart>();
    }


    /**
     * just gets rid of all of the parts of fStr that should be commented out.
     * This is called by the constructor and that's why it's set to private.
     * Now we can just search through fStr for all of our checks and we don't
     * have to worry about comments getting in the way
     */
    private void removeStrComments(){
        int index = 0;
        int lastCommentStartInd = 0;
        while(index < fStr.length()-1){
            String indicator = fStr.substring(index, index+2);
            //check for single line comments and remove them
            if(indicator.equals("//")){
                lastCommentStartInd = index;
                while(index < fStr.length() && fStr.charAt(index) != '\n'){
                    index++;
                }
                index++;
                fStr = fStr.substring(0, lastCommentStartInd)+fStr.substring(index, fStr.length());
                index -= (index - lastCommentStartInd);
            }
            //check for multiline comments and remove them
            if(indicator.equals("/*")){
                lastCommentStartInd = index;
                String endIndicator = "";
                while(index < fStr.length()-1 && "*/".equals(endIndicator)){
                    endIndicator = fStr.substring(index, index+2);
                    index++;
                }
                index += 2;
                fStr = fStr.substring(0, lastCommentStartInd)+fStr.substring(index, fStr.length());
                index -= (index - lastCommentStartInd);
            }
            index++;
        }
    }

    /**
     * Counts the number of characters in the file.
     * Not necessary for the project, this is just an easy warm-up
     * for me to refamiliarize myself with how to get info from a file
     * @return number of characters excluding newlines
     */
    public int numCharacters(){
        int i = 0;
        try{
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                i += line.length();
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.err.println("Can't find file.");
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Identifies if the file has the .java extension
     * @return boolean value that is true iff file has a .java extension
     */
    public boolean isJavaFile(){
        String fName = file.getName();
        return fName.endsWith(".java");
    }

    /**
     * Gets the package of a java file. If there is no package present,
     * returns null.
     * @return the package name as a string
     */
    public String getPackageName(){
        String packageLine = null;
        try{
            Scanner fs = new Scanner(file);
            //The package assignment should always be the first line of the file
            packageLine = fs.nextLine();
            if(packageLine.startsWith("package")){
                int stopInd = packageLine.indexOf(';');
                packageLine = packageLine.substring(8, stopInd);
            }else{
                fs.close();
                return null;
            }
            fs.close();
        }catch(FileNotFoundException e){
            System.err.println("Can't find file.");
            e.printStackTrace();
        }
        return packageLine;
    }

    /**
     * Gets the package of a java file. If there is no package present,
     * returns null.
     * @return the package name as a string
     */
    public String getClassName(){
        String packageLine = null;
        try{
            Scanner fs = new Scanner(file);
            //Looking for the first occurrence of " class "

            packageLine = fs.nextLine();
            if(packageLine.startsWith("package")){
                int stopInd = packageLine.indexOf(';');
                packageLine = packageLine.substring(8, stopInd);
            }else{
                fs.close();
                return null;
            }
            fs.close();
        }catch(FileNotFoundException e){
            System.err.println("Can't find file.");
            e.printStackTrace();
        }
        return packageLine;
    }

    
    public int getNumMethods(){
        try{
            Scanner fs = new Scanner(file);
            while(fs.hasNextLine()){
                String line = fs.
            }
            fs.close();
        }catch(FileNotFoundException e){
            System.err.println("Can't find file.");
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return the number of constructors, methods, fields, and classes in the file
     */
    public int getNumOOParts(){
        int count = 0;
        
        try{
            Scanner fs = new Scanner(file);
            while(fs.hasNextLine()){
                String line = fs.nextLine();
                if(line.trim().startsWith("public") || line.trim().startsWith("private") 
                        || line.trim().startsWith("protected")){
                    count++;
                }
            }
            fs.close();
        }catch(FileNotFoundException e){
            System.err.println("Can't find file.");
            e.printStackTrace();
        }
        return count;
    }


    /**
     * Gets every location in a file where a field, method, constructor, or class is.
     * @return list of line numbers where a header is found.
     */
    public int[] getOOPartHeaders(){
        try{
            int count = getNumOOParts();
            int[] lineNums = new int[count];
            Scanner fs = new Scanner(file);
            int lineNumber = 1;
            int lineNumsInd = 0;
            while(fs.hasNextLine()){
                String line = fs.nextLine();
                if(line.trim().startsWith("public") || line.trim().startsWith("private") 
                        || line.trim().startsWith("protected")){
                    lineNums[lineNumsInd] = lineNumber;
                    lineNumsInd++;
                }
                lineNumber++;
            }
            fs.close();
            return lineNums;
        }catch(FileNotFoundException e){
            System.err.println("Can't find file.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO!!!
     * in java, there are 3 structures inside the class that could start
     * with: public, private, or protected. Those are fields, methods, and constructors.
     * To differentiate between them, methods always have parentheses somewhere before
     * they have a semicolon.
     * @return
     */
    public DeclaredPart decideOOPart

    //GETTER
    public File getFile(){
        return file;
    }
}
