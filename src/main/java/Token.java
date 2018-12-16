public class Token {
     private int code;
     private String content;
     private String error;

     public Token(int code,String content){
         this.code=code;
         this.content=content;
         this.error=null;
     }

     public Token(String error){
         this.error=error;
         this.code=0;
         this.content=null;
     }

     public String getString(){
         if(error==null){
             return "("+content+","+code+")";
         }
         else{
             return error;
         }
     }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
