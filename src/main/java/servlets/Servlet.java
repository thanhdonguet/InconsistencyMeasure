package servlets;

import com.sun.jdi.IntegerValue;
import measures.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Servlet")
@MultipartConfig
public class Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedvalue = request.getParameter("code");
        Part uploadedPath = request.getPart("uploadedFile");



        KnowledgeBaseReader reader = new KnowledgeBaseReader();
        List<KnowledgeBase> kbs = reader.read(uploadedPath.getInputStream());

        String str = request.getParameter("kbInput");
        KnowledgeBase kc = null;
        if (!str.equals(""))
            kc = Function.toKnowledgeBase(str);

        String normal = request.getParameter("normInput");
        int norm = 0;
        if (!normal.equals(""))
            norm = Integer.valueOf(normal);

        String num = request.getParameter("numInput");
        int number = 0;
        if (!num.equals(""))
            number = Integer.valueOf(num);

        int i = Integer.valueOf(selectedvalue);
        String measurename = "";
        double measurevalue = 0;

        Map<KnowledgeBase, Result> resultMap = new HashMap<>();
        for (KnowledgeBase k : kbs) {

            switch (i) {
                case 1:
                    measurename = "Drastic Inconsistency Measure";
                    measurevalue = Calculation.DrasticIM(k);
                    break;
                case 2:
                    measurename = "MI Inconsistency Measure";
                    measurevalue = Calculation.MIIM(k);
                    break;
                case 3:
                    measurename = "SMI-c Inconsistency Measure";
                    measurevalue = Calculation.SMIcIM(k);
                    break;
                case 4:
                    measurename = "l-Inconsistency Measure";
                    measurevalue = Calculation.lIM(k);
                    break;
                case 5:
                    measurename = "X-Inconsistency Measure";
                    measurevalue = Calculation.XIM(k);
                    break;
                case 6:
                    measurename = "μ-Inconsistency Measure";
                    measurevalue = Calculation.μIM(k);
                    break;
                case 7:
                    measurename = "DM Inconsistency Measure";
                    measurevalue = Calculation.DMIM(k,kc,norm);
                    break;
                case 8:
                    measurename = "SUM Inconsistency Measure";
                    measurevalue = Calculation.SUMIM(k,kc,norm);
                    break;
                case 9:
                    measurename = "Shapley Inconsistency Value";
                    measurevalue = Calculation.ProbalilisticShapley(k,number);
                    break;
                case 10:
                    measurename = "SV-Inconsistency Measure";
                    measurevalue = Calculation.SVIM(k);
                    break;
            }
            Result result = new Result(measurename, measurevalue);
            resultMap.put(k, result);
        }

        System.out.println(resultMap);
        request.setAttribute("resultMap", resultMap);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
