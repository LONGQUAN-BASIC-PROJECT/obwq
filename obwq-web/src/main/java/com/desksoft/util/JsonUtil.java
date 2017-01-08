package com.desksoft.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by guoxing.zgx on 2017/1/7.
 */
public class JsonUtil<T> {

    public   T  parseCont(String content,Class cs) throws  Exception {

        JSONObject object =   JSONObject.parseObject(content);

        Boolean isSuccess = object.getBoolean("success");
        if(!isSuccess){
            System.out.print("return is false");
        }


        Object  c = object.get("result");
        if(c instanceof JSONObject){
            JSONObject jb = (JSONObject) c ;
            return (T)processOneObject(jb,cs) ;

        }else if (c instanceof JSONArray){
            JSONArray array = (JSONArray)c ;
            if(array.size() < 1){
                return null ;
            }
            List list = new ArrayList() ;

            Iterator<Object>  itr = array.iterator();
            while (itr.hasNext()){
                JSONObject jb = (JSONObject) itr.next();
                Object r =   processOneObject(jb,cs) ;
                if(r != null){
                    list.add(r);
                }
            }

            return (T)list ;
        }

        return null ;
    }

    private Object processOneObject(JSONObject jSONObject , Class cs) throws Exception {
        Object returnObj  = cs.newInstance();
        Method[] mes = cs.getDeclaredMethods();

        for(String s : jSONObject.keySet()){

            Object value = jSONObject.get(s);
            for(Method m : mes){
                String mname = m.getName();
                if(mname.startsWith("get")){
                    continue;
                }
                if(mname.toLowerCase().indexOf(s.toLowerCase()) == -1){
                    continue;
                }

                if(value == null ){
                    break;
                }

                Class<?>[]  ca = m.getParameterTypes();
                if(ca.length < 1){
                    break;
                }


                value = changeSwitch(ca[0],value);
                m.invoke(returnObj,value);
            }
        }

        Method mcreate =   cs.getSuperclass().getDeclaredMethod("setGmtCreate",Date.class);
        Date dd  = new Date((Long)jSONObject.get("gmtCreate")) ;
        mcreate.invoke(returnObj,dd);



        Method mmodify =   cs.getSuperclass().getDeclaredMethod("getModify",Date.class);
        Date dmodify  = new Date((Long)jSONObject.get("getModify")) ;
        mcreate.invoke(returnObj,dmodify);


        return returnObj ;
    }



    private static Object changeSwitch(final Class<?> type, Object v) throws IllegalArgumentException, IllegalAccessException {
        final Object val;
        if( boolean.class.isAssignableFrom(type)
                || Boolean.class.isAssignableFrom(type)) {
            val = Boolean.valueOf(v.toString());
        } else if ( int.class.isAssignableFrom(type)
                || Integer.class.isAssignableFrom(type)) {
            val = Integer.valueOf(v.toString());
        } else if ( long.class.isAssignableFrom(type)
                || Long.class.isAssignableFrom(type)) {
            val = Long.valueOf(v.toString());
        } else if ( short.class.isAssignableFrom(type)
                || Short.class.isAssignableFrom(type)) {
            val = Short.valueOf(v.toString());
        } else if ( float.class.isAssignableFrom(type)
                || Float.class.isAssignableFrom(type)) {
            val = Float.valueOf(v.toString());
        } else if ( double.class.isAssignableFrom(type)
                || Double.class.isAssignableFrom(type)) {
            val = Double.valueOf(v.toString());
        } else if ( char.class.isAssignableFrom(type)
                || Character.class.isAssignableFrom(type)) {
            val = Character.valueOf(v.toString().charAt(0));
        } else if ( byte.class.isAssignableFrom(type)
                || Byte.class.isAssignableFrom(type)) {
            val = Byte.valueOf(v.toString());
        } else if ( String.class.isAssignableFrom(type) ) {
            val = v;
        } else if ( Date.class.isAssignableFrom(type) ) {
            val = new Date(v.toString());
        } else {
            // do nothing...
            val = null;
        }//if

        if( null != val )
            return val ;

        return val;
    }

}
