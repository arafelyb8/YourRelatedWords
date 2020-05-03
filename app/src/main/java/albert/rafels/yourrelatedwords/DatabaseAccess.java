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

    //query english return spanish
    public String getSpanish(String word_english){
        c=db.rawQuery("Select spanish from Table1 where english = '"+word_english+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String word = c.getString(0);
            buffer.append(""+word);
        }
        return buffer.toString();
    }
    //query spanish return english
    public String getEnglish(String word_spanish){
        c=db.rawQuery("Select english from Table1 where spanish = '"+word_spanish+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String word = c.getString(0);
            buffer.append(""+word);
        }
        return buffer.toString();
    }

    //get a random english word
    public String getRandomEnglish(){
        c=db.rawQuery("Select english from Table1 order by random() limit 1", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String word = c.getString(0);
            buffer.append(""+word);
        }
        return buffer.toString();
    }
    //get a random spanish word
    public String getRandomSpanish(){
        c=db.rawQuery("Select spanish from Table1 order by random() limit 1", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String word = c.getString(0);
            buffer.append(""+word);
        }
        return buffer.toString();
    }

}
