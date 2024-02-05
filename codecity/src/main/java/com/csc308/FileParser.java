package com.csc308;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.*;


public class FileParser extends VoidVisitorAdapter<Void>{
    private File file;

    private int numMethods;
    private List<MethodDeclaration> methods;
    private List<FieldDeclaration> fields;
    private List<FileInfo> infos;

    private int numAttributes;
    private String className;
    private String packageName;

    public FileParser(String path){
        numMethods = 0;
        methods = new ArrayList<>();
        fields = new ArrayList<>();
        infos = new ArrayList<>();
        numAttributes = 0;
        try{
            file = new File(path);
            Path fPath = Path.of(path);
            if(file.exists() && Files.isRegularFile(fPath) && isJavaFile()){
                FileInputStream fIn = new FileInputStream(path);

                JavaParser jp = new JavaParser();
                CompilationUnit cu = jp.parse(fIn).getResult().get();
                this.visit(cu, null);
                numMethods = methods.size();
                numAttributes = fields.size();
                infos.add(new FileInfo(packageName, className, numMethods, numAttributes));
            }else if(file.exists() && Files.isDirectory(fPath)){
                //If the File object represents a directory, recursively get all subfiles, parse them, and collect FileInfos.
                File[] subFiles = file.listFiles();
                for(File f: subFiles){
                    FileParser fp = new FileParser(f.getPath());
                    for(FileInfo fi : fp.getFileInfos()){
                        infos.add(fi);
                    }
                }
            }else{
                System.out.println("Non-java file encountered: "+file.getName());
            }
        }catch(IOException e){
            e.printStackTrace();
            System.err.println("No file big sad :(");
        }
    }

    @Override
    public void visit(MethodDeclaration md, Void arg){
        super.visit(md, arg);
        methods.add(md);
    }

    @Override
    public void visit(FieldDeclaration fd, Void arg){
        super.visit(fd, arg);
        fields.add(fd);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration coid, Void arg){
        super.visit(coid, arg);
        className = coid.getNameAsString();
    }

    @Override
    public void visit(PackageDeclaration pd, Void arg){
        super.visit(pd, arg);
        packageName = pd.getNameAsString();
    }

    /**
     * Identifies if the file has the .java extension
     * @return boolean value that is true iff file has a .java extension
     */
    public boolean isJavaFile(){
        String fName = file.getName();
        return fName.endsWith(".java");
    }

    @Override
    public String toString(){
        return "Class name: "+className+"\n"+
                "Package name: "+packageName+"\n"+
                "# Methods: "+numMethods+"\n"+
                "# Attributes: "+numAttributes+"";
    }

    //GETTERS

    public List<FileInfo> getFileInfos(){
        return infos;
    }
}
