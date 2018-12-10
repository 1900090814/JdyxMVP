package text.jdyx.com.jdyx.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 全局保存返回的tokenID，用来验证
 * Created by Administrator on 2018/1/22.
 */

public class SharedPreferencesApp {

    //存储的sharedpreferences文件名
    private static final String FILE_NAME = "save_file_name";
    private static final String TIME = "time";
    private SharedPreferencesApp(){}

    //存入
    public static void setedit(Context context,String id,Object object){
        @SuppressLint("WrongConstant") SharedPreferences     sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String simpleName = object.getClass().getSimpleName();
        if ("Integer".equals(simpleName)){
            edit.putInt(id, (Integer)object);
        }else if ("Boolean".equals(simpleName)){
            edit.putBoolean(id, (Boolean)object);
        }else if ("String".equals(simpleName)){
            edit.putString(id, (String)object);
        }else if ("Float".equals(simpleName)){
            edit.putFloat(id, (Float)object);
        }else if ("Long".equals(simpleName)){
            edit.putLong(id, (Long)object);
        }

        edit.commit();
    }

    /**
     * 从文件中读取数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Object getData(Context context, String key, Object defValue){

        String type = defValue.getClass().getSimpleName();
        if (context!=null){
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            if (sharedPreferences!=null){
                //defValue为为默认值，如果当前获取不到数据就返回它
                if ("Integer".equals(type)){
                    return sharedPreferences.getInt(key, (Integer)defValue);
                }else if ("Boolean".equals(type)){
                    return sharedPreferences.getBoolean(key, (Boolean)defValue);
                }else if ("String".equals(type)){
                    return sharedPreferences.getString(key, (String)defValue);
                }else if ("Float".equals(type)){
                    return sharedPreferences.getFloat(key, (Float)defValue);
                }else if ("Long".equals(type)){
                    return sharedPreferences.getLong(key, (Long)defValue);
                }}
        }


        return null;
    }
    //删除存储的值
    public static void getDelete(Context context){
        @SuppressLint("WrongConstant")
        SharedPreferences     sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND);
        sharedPreferences.edit().clear().commit();

    }






    //删除指定的时间值
    public static void setDelete(Context context,String str){
        @SuppressLint("WrongConstant")
        SharedPreferences   sharedPreferences=context.getSharedPreferences(TIME,Context.MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(str);
        edit.commit();
    }
    //存入时间
    public static void setTime(Context context,String id,Object object){
        @SuppressLint("WrongConstant")
        SharedPreferences    sharedPreferences=context.getSharedPreferences(TIME,Context.MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String simpleName = object.getClass().getSimpleName();
        if ("Integer".equals(simpleName)){
            edit.putInt(id, (Integer)object);
        }else if ("Boolean".equals(simpleName)){
            edit.putBoolean(id, (Boolean)object);
        }else if ("String".equals(simpleName)){
            edit.putString(id, (String)object);
        }else if ("Float".equals(simpleName)){
            edit.putFloat(id, (Float)object);
        }else if ("Long".equals(simpleName)){
            edit.putLong(id, (Long)object);
        }

        edit.commit();
    }
    //删除存储的所有时间值
    public static void getDeleteTime(Context context){
        @SuppressLint("WrongConstant")
        SharedPreferences    sharedPreferences=context.getSharedPreferences(TIME,Context.MODE_APPEND);
        sharedPreferences.edit().clear().commit();

    }
    /**
     * 从文件中读取时间数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Object getTime(Context context, String key, Object defValue){

        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(TIME, Context.MODE_PRIVATE);

        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)){
            return sharedPreferences.getInt(key, (Integer)defValue);
        }else if ("Boolean".equals(type)){
            return sharedPreferences.getBoolean(key, (Boolean)defValue);
        }else if ("String".equals(type)){
            return sharedPreferences.getString(key, (String)defValue);
        }else if ("Float".equals(type)){
            return sharedPreferences.getFloat(key, (Float)defValue);
        }else if ("Long".equals(type)){
            return sharedPreferences.getLong(key, (Long)defValue);
        }

        return null;
    }
}
