import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
* @problem Ancient Berland Circus
* @url http://codeforces.com/contest/1/problem/C
* @description http://www.coltonstack.com/stack.do?pclass_id=3&stackpost_id=35
* @author coltonoppa
*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] crds = new double[3][2];
        for (int i = 0; i < 3; i++) {
            crds[i][0] = sc.nextDouble();
            crds[i][1] = sc.nextDouble();
        }
        
        double[] center = getCenter(crds);
        double[] degrees = new double[3];
        for (int i = 0; i < 3; i++)
            degrees[i] = Math.toDegrees(Math.atan2(crds[i][1] - center[1], crds[i][0] - center[0]));
        
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.FLOOR);
        double n = 3.0;
        Outer: while (n <= 100) {
            String fmt1 = df.format(Math.abs(Math.sin(Math.toRadians((n * (degrees[1] - degrees[0]))/2))));
            String fmt2 = df.format(Math.abs(Math.sin(Math.toRadians((n * (degrees[2] - degrees[1]))/2))));
            String fmt3 = df.format(Math.abs(Math.sin(Math.toRadians((n * (degrees[0] - degrees[2]))/2))));
            if (fmt1.equals("0") && fmt2.equals("0") && fmt3.equals("0"))
                break;
            n++;
        }
        
        double radius = Math.sqrt(Math.pow(crds[0][0] - center[0], 2.0) + Math.pow(crds[0][1] - center[1], 2.0));
        double side = 2.0 * radius * Math.sin(Math.toRadians(180.0/n));
        double apothem = side / (2.0 * Math.tan(Math.toRadians(180.0/n)));
        double perimeter = n * side;
        double area = (apothem * perimeter) / 2.0;
        
        DecimalFormat dfans = new DecimalFormat("#.######");
        System.out.println(dfans.format(area));
    }
    
    static double[] getCenter(double[][] crds) {
        double[][] midpoints = new double[2][2];
        midpoints[0][0] = (crds[1][0] + crds[0][0]) / 2;
        midpoints[0][1] = (crds[1][1] + crds[0][1]) / 2;
        midpoints[1][0] = (crds[2][0] + crds[1][0]) / 2;
        midpoints[1][1] = (crds[2][1] + crds[1][1]) / 2;
        
        double[] slopes = new double[2];
        slopes[0] = (crds[1][1] - crds[0][1]) / (crds[1][0] - crds[0][0]);
        slopes[1] = (crds[2][1] - crds[1][1]) / (crds[2][0] - crds[1][0]);
        slopes[0] = (-1/slopes[0]);
        slopes[1] = (-1/slopes[1]);
        double[] b = new double[2];
        for (int i = 0; i < 2; i++) {
            if (Double.isFinite(slopes[i]))
                b[i] = midpoints[i][1] - slopes[i] * midpoints[i][0];
            else
                b[i] = slopes[i];
        }
        
        double[] center = new double[2];
        if (Double.isFinite(slopes[0]) && Double.isFinite(slopes[1])) {
            center[0] = ((b[0] - b[1]) * -1) / (slopes[0] - slopes[1]);
            center[1] = slopes[0] * center[0] + b[0];
        } else {
            int f = Double.isFinite(slopes[0]) ? 0 : 1;
            int i = f == 0 ? 1 : 0;
            center[0] = midpoints[i][0];
            center[1] = slopes[f] * center[0] + b[f];
        }
        
        return center;
    }
}