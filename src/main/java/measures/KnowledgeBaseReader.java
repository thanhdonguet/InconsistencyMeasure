package measures;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseReader {
    public List<KnowledgeBase> read(InputStream pis) throws IOException
    {
        List<KnowledgeBase> kbs = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(pis));
            String str;
            while ((str = in.readLine()) != null) {
                KnowledgeBase k = new KnowledgeBase();
                String[] contraints = str.split(" ");
                for (String c : contraints) {
                    String[] elements = c.split(":");
                    Constraint c1 = new Constraint(elements[0], Double.valueOf(elements[1]));
                    k.add(c1);
                }
                kbs.add(k);
            }
        }
        catch (IOException e) {
        }
        return kbs;
    }
}