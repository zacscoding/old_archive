package dto;

import java.io.Serializable;
import java.util.Date;

public class ScheduleVO implements Serializable{
   private static final long serialVersionUID = 1L;
   private String groupId;
   private int writer;
   private String content;
   private Date regDate;
   private Date date;
   
   //삽입 관련 생성자
   public ScheduleVO(String groupId,int writer,String content,Date regDate,Date date) {      
      this.groupId = groupId;
      this.writer = writer;
      this.content = content;
      this.regDate = regDate;
      this.date = date;
   }
   
   //getter   
   public String getGroupId() {
      return groupId;
   }
   public int getWriter() {
      return writer;
   }
   public String getContent() {
      return content;
   }
   public Date getRegDate() {
      return regDate;
   }
   public Date getDate() {
      return date;
   }
   
   @Override
   public String toString(){
      return groupId+"\t"+content+"\n";
   }
   
   
}