package bw.com.lx_yk_1.UserDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bw.com.lx_yk_1.Person.NewsPerson;
import bw.com.lx_yk_1.SqlietOpenHelper;

public class UserDao {

    private final SQLiteDatabase db;

    public UserDao(Context context) {
        SqlietOpenHelper cd = new SqlietOpenHelper(context);
                db = cd.getWritableDatabase();

    }
    public void insert(String title,String summary,String url){
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("summary",summary);
        values.put("url",url);
        db.insert("user",null,values);
    }
    public List<NewsPerson.DataBean> query(){
        Cursor query = db.query("uesr", null, null, null, null, null, null);
        List<NewsPerson.DataBean> list = new ArrayList<>();
        while (query.moveToNext()){
            String title = query.getString(query.getColumnIndex("title"));
            String summary = query.getString(query.getColumnIndex("summary"));
            String url = query.getString(query.getColumnIndex("url"));
            list.add(new NewsPerson.DataBean(null,title,summary,url));
        }
        return list;
    }

}
