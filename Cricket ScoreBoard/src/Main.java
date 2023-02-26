public class Main {
    public static void main(String[] args) {
       float a=1f;
        for(int i=0;i<5;i++){
            a=Float.parseFloat(String.format("%.1f",(a-0.1f)));
           System.out.println(a);
       }
    }
}