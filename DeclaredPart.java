
public class DeclaredPart {
    
    public OOPart partType;
    public int numLines;
    public String name;
    public int headerLine;

    public DeclaredPart(int headerLine){
        this.headerLine = headerLine;
        numLines = 0;
        name = "";
        partType = OOPart.UNKNOWN;
    }
}
