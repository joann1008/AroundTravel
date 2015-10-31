package com.extra.aroundtravel.db;

import com.extra.aroundtravel.db.entity.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SqlManager {
	private SqlHelper helper;
	private SQLiteDatabase db;

	public SqlManager(Context context) {
		helper = new SqlHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}

	public void save(User user) {
		db.beginTransaction();
		try{
			db.execSQL("INSERT INTO user VALUES(?, ?, ?, ?)",new Object[]{user.getId(), user.getUsername(), user.getPassword(),user.getGroupid()});
			db.setTransactionSuccessful();
		}finally{
			db.endTransaction();
		}
	}
	
	public boolean getUserByUnamePwd(String username,String password){
		db.beginTransaction();
//		User u = null;
		Cursor c = null;
		try{
			c = db.rawQuery("SELECT * FROM user where username = ? and password = ?",new String[]{username,password});
		}finally{
			db.endTransaction();
		}
		
		if(c != null && c.getCount() > 0){
			c.close();
			return true;
//			u = new User();
//			System.out.println("====================================================================" + c.getColumnIndex("_id"));
//			u.setId(c.getInt(0));
//			u.setUsername(username);
//			u.setPassword(password);
//			u.setGroupid(c.getInt(c.getColumnIndex("groupid")));
		}
		return false;
	}
}
