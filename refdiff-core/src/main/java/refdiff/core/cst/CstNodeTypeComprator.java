package refdiff.core.cst;

import java.util.Comparator;
import java.util.Map;

public class CstNodeTypeComprator implements Comparator<CstNode>{
    
    @Override
    public int compare(CstNode n1, CstNode n2) {
        Map<String, Integer> ordering = Map.of(
            "Function", 1,
            "Class", 2,
            "File", 3);
        Integer n1KeyInt = ordering.get(n1.getType());
        Integer n2KeyInt = ordering.get(n2.getType());
        return (n1KeyInt > n2KeyInt) ? 1 : -1;
    }
}