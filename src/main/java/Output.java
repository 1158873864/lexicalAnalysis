import java.io.*;
import java.util.ArrayList;

public class Output {
     public void output(String fileName, ArrayList<String> content){
         String[] splitName=fileName.split("\\.");
         File file =new File(splitName[0]+"-result.txt");
         try {
             if(!file.exists()){
                 file.createNewFile();
             }
             BufferedWriter writer  = new BufferedWriter(new FileWriter(file,true));
             for(int i=0;i<content.size();i++){
                 writer.write(content.get(i)+"\n");
             }
             writer.flush();
             writer.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
