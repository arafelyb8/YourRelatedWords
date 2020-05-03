package albert.rafels.yourrelatedwords;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.StringReader;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c =null;

    //private construct
    private DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);

    }

    //return a single instance of the database
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }

    //open database
    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    //close database
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    //query and return result from database. Query for english by passing spanish
    public String getSpanish(String word_english){
        c=db.rawQuery("Select spanish from Table1 where english = '"+word_english+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String word_spanish = c.getString(0);
            buffer.append(""+word_spanish);
        }
        return buffer.toString();
    }

    //get a random spanish word
    public String getRandomEnglish(){
        c=db.rawQuery("Select english from Table1 order by random() limit 1", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String word_spanish = c.getString(0);
            buffer.append(""+word_spanish);
        }
        return buffer.toString();
    }

}
