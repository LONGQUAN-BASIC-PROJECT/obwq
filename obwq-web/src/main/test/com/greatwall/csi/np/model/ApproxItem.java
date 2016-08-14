package  com.greatwall.csi.np.model;

   import  java.util.ArrayList;
   import  java.util.HashMap;

   public   class  ApproxItem {
       public   int  getMonth() {
           return  month;
      }

       public   void  setMonth( int  month) {
           this .month  =  month;
      }

       public   double  getExpose() {
           return  expose;
      }

       public   void  setExpose( double  expose) {
           this .expose  =  expose;
      }

       public  ArrayList getList() {
           return  list;
      }

       public  HashMap getMap() {

           return  map;
      }

       public   void  setList(ArrayList list) {
           this .list  =  list;
      }

       public   void  setMap(HashMap map) {
           this .map  =  map;
      }

       private   int  month;
       private   double  expose;
       private  ArrayList list;
       private  HashMap map;
  }