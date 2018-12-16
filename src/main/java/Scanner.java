import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scanner {
    private Map map=new HashMap();
    public void scanner(String fileName){
        setMap();
        ArrayList<String> source=new ArrayList<String>();
        ArrayList<String> result=new ArrayList<String>();
        Input input=new Input();
        source=input.readFileByLines(fileName);
        int state=0;
        ArrayList<Character> recordTemp=new ArrayList<Character>();
        for(int lineIndex=0;lineIndex<source.size();lineIndex++){
            for(int charIndex=0;charIndex<source.get(lineIndex).length();charIndex++){
                char thisChar=source.get(lineIndex).charAt(charIndex);
                if((thisChar>=65&&thisChar<=90)||(thisChar>=97&&thisChar<=122)){
                    if(state==1||state==0){
                        recordTemp.add(thisChar);
                        state=1;
                    }
                    else if(state==12){
                        if(thisChar==110){
                            Token token=new Token(Integer.parseInt(map.get("\n").toString()),"\n");
                            result.add(token.getString());
                            recordTemp.clear();
                            state=0;
                        }
                        else if(thisChar==116){
                            Token token=new Token(Integer.parseInt(map.get("\t").toString()),"\t");
                            result.add(token.getString());
                            recordTemp.clear();
                            state=0;
                        }
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else{
                            token=new Token(202,recordString);
                        }
                        result.add(token.getString());
                        state=1;
                        recordTemp.clear();
                        recordTemp.add(thisChar);

                    }
                }
                else if(thisChar>=48&&thisChar<=57){
                    if(state==0||state==2||state==4){
                        recordTemp.add(thisChar);
                        state=2;
                    }
                    else if(state==1){
                        recordTemp.add(thisChar);
                        state=1;
                    }
                    else if(state==3){
                        recordTemp.add(thisChar);
                        state=3;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=2;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==45){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=4;
                    }
                    else if(state==4){
                        Token token=new Token(Integer.parseInt(map.get("--").toString()),"--");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=4;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==46){
                    if(state==3){
                        recordTemp.add(thisChar);
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString + String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=0;
                        recordTemp.clear();
                        token=new Token(Integer.parseInt(map.get("\\.").toString()),".");
                        result.add(token.getString());
                    }
                }
                else if(thisChar==47){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=5;
                    }
                    else if(state==5){
                        Token token=new Token(Integer.parseInt(map.get("//").toString()),"//");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else if(state==7){
                        Token token=new Token(Integer.parseInt(map.get("*/").toString()),"*/");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=5;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==43){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=6;
                    }
                    else if(state==6){
                        Token token=new Token(Integer.parseInt(map.get("++").toString()),"++");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=6;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==42){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=7;
                    }
                    else if(state==5){
                        Token token=new Token(Integer.parseInt(map.get("/*").toString()),"/*");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=7;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==60){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=8;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=8;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==62){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=9;
                    }
                    else if(state==4){
                        Token token=new Token(Integer.parseInt(map.get("->").toString()),"->");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=9;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==124){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=10;
                    }
                    else if(state==10){
                        Token token=new Token(Integer.parseInt(map.get("||").toString()),"||");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=10;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==38){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=11;
                    }
                    else if(state==11){
                        Token token=new Token(Integer.parseInt(map.get("&&").toString()),"&&");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=11;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==92){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=12;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=12;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }
                else if(thisChar==61){
                    if(state==0){
                        recordTemp.add(thisChar);
                        state=13;
                    }
                    else if(state==5){
                        Token token=new Token(Integer.parseInt(map.get("/=").toString()),"/=");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else if(state==6){
                        Token token=new Token(Integer.parseInt(map.get("+=").toString()),"+=");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else if(state==7){
                        Token token=new Token(Integer.parseInt(map.get("*=").toString()),"*=");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else if(state==8){
                        Token token=new Token(Integer.parseInt(map.get("<=").toString()),"<=");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else if(state==9){
                        Token token=new Token(Integer.parseInt(map.get(">=").toString()),">=");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else if(state==13){
                        Token token=new Token(Integer.parseInt(map.get("==").toString()),"==");
                        result.add(token.getString());
                        recordTemp.clear();
                        state=0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=13;
                        recordTemp.clear();
                        recordTemp.add(thisChar);
                    }
                }

                else if(map.containsKey(String.valueOf(thisChar))){
                    if(state==0) {
                        Token token = new Token(Integer.parseInt(map.get(String.valueOf(thisChar)).toString()), String.valueOf(thisChar));
                        result.add(token.getString());
                        recordTemp.clear();
                        state = 0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=0;
                        recordTemp.clear();
                        token = new Token(Integer.parseInt(map.get(String.valueOf(thisChar)).toString()), String.valueOf(thisChar));
                        result.add(token.getString());
                    }
                }
                else{
                    if(state==0) {
                        Token token = new Token(String.valueOf(thisChar) + "非法！");
                        result.add(token.getString());
                        recordTemp.clear();
                        state = 0;
                    }
                    else{
                        String recordString="";
                        Token token;
                        for(int tempIndex=0;tempIndex<recordTemp.size();tempIndex++){
                            recordString=recordString+String.valueOf(recordTemp.get(tempIndex));
                        }
                        if(map.containsKey(recordString)){
                            token=new Token(Integer.parseInt(map.get(recordString).toString()),recordString);
                        }
                        else if((recordTemp.get(0)>=65&&recordTemp.get(0)<=90)||(recordTemp.get(0)>=97&&recordTemp.get(0)<=122)){
                            token=new Token(201,recordString);
                        }
                        else if((recordTemp.get(0)>=48&&recordTemp.get(0)<=57)||recordTemp.get(0)==45){
                            token=new Token(202,recordString);
                        }
                        else{
                            token=new Token(recordString+"非法！");
                        }
                        result.add(token.getString());
                        state=0;
                        recordTemp.clear();
                        token = new Token(String.valueOf(thisChar) + "非法！");
                        result.add(token.getString());
                    }
                }
            }
        }
        Output output=new Output();
        output.output(fileName,result);
    }

    private void setMap(){
        map.put("const",1);
        map.put("long",2);
        map.put("float",3);
        map.put("double",4);
        map.put("void",5);
        map.put("main",6);
        map.put("if",10);
        map.put("else",11);
        map.put("break",14);
        map.put("int",15);
        map.put("char",16);
        map.put("include",17);
        map.put("for",18);
        map.put("while",20);
        map.put("printf",21);
        map.put("scanf",22);

        map.put("+",51);
        map.put("-",52);
        map.put("*",53);
        map.put("/",54);
        map.put("+=",55);
        map.put("-=",56);
        map.put("*=",57);
        map.put("++",58);
        map.put("--",59);
        map.put("<",60);
        map.put("<=",61);
        map.put(">",62);
        map.put(">=",63);
        map.put("->",64);
        map.put("=",65);
        map.put("(",66);
        map.put(")",67);
        map.put("#",68);
        map.put("{",69);
        map.put("}",70);
        map.put("[",71);
        map.put("]",72);
        map.put("==",73);
        map.put("/=",74);
        map.put("|",75);
        map.put("||",76);
        map.put("&",77);
        map.put("&&",78);
        map.put("!",79);
        map.put("!=",80);

        map.put(" ",101);
        map.put(";",102);
        map.put("\n",103);
        map.put("\t",104);
        map.put("'",105);
        map.put("\"",106);
        map.put(",",107);
        map.put("//",108);
        map.put("/*",109);
        map.put("*/",110);
        map.put("_",111);
        map.put("\\.",112);
    }

}
