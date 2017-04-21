package com.com.sky.downloader.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lu.indexpagedemo.bean.AddressBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADDRESS_BEAN".
*/
public class AddressBeanDao extends AbstractDao<AddressBean, Long> {

    public static final String TABLENAME = "ADDRESS_BEAN";

    /**
     * Properties of entity AddressBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Addressid = new Property(0, Long.class, "addressid", true, "id");
        public final static Property RealName = new Property(1, String.class, "realName", false, "name");
        public final static Property Sex = new Property(2, boolean.class, "sex", false, "sex");
        public final static Property AreaDetile = new Property(3, String.class, "areaDetile", false, "address");
        public final static Property PhoneNum = new Property(4, String.class, "phoneNum", false, "phone");
        public final static Property Code = new Property(5, int.class, "code", false, "code");
        public final static Property IsDefult = new Property(6, boolean.class, "isDefult", false, "usable");
    }


    public AddressBeanDao(DaoConfig config) {
        super(config);
    }
    
    public AddressBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADDRESS_BEAN\" (" + //
                "\"id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: addressid
                "\"name\" TEXT," + // 1: realName
                "\"sex\" INTEGER NOT NULL ," + // 2: sex
                "\"address\" TEXT," + // 3: areaDetile
                "\"phone\" TEXT," + // 4: phoneNum
                "\"code\" INTEGER NOT NULL ," + // 5: code
                "\"usable\" INTEGER NOT NULL );"); // 6: isDefult
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADDRESS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AddressBean entity) {
        stmt.clearBindings();
 
        Long addressid = entity.getAddressid();
        if (addressid != null) {
            stmt.bindLong(1, addressid);
        }
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(2, realName);
        }
        stmt.bindLong(3, entity.getSex() ? 1L: 0L);
 
        String areaDetile = entity.getAreaDetile();
        if (areaDetile != null) {
            stmt.bindString(4, areaDetile);
        }
 
        String phoneNum = entity.getPhoneNum();
        if (phoneNum != null) {
            stmt.bindString(5, phoneNum);
        }
        stmt.bindLong(6, entity.getCode());
        stmt.bindLong(7, entity.getIsDefult() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AddressBean entity) {
        stmt.clearBindings();
 
        Long addressid = entity.getAddressid();
        if (addressid != null) {
            stmt.bindLong(1, addressid);
        }
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(2, realName);
        }
        stmt.bindLong(3, entity.getSex() ? 1L: 0L);
 
        String areaDetile = entity.getAreaDetile();
        if (areaDetile != null) {
            stmt.bindString(4, areaDetile);
        }
 
        String phoneNum = entity.getPhoneNum();
        if (phoneNum != null) {
            stmt.bindString(5, phoneNum);
        }
        stmt.bindLong(6, entity.getCode());
        stmt.bindLong(7, entity.getIsDefult() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AddressBean readEntity(Cursor cursor, int offset) {
        AddressBean entity = new AddressBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // addressid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // realName
            cursor.getShort(offset + 2) != 0, // sex
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // areaDetile
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phoneNum
            cursor.getInt(offset + 5), // code
            cursor.getShort(offset + 6) != 0 // isDefult
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AddressBean entity, int offset) {
        entity.setAddressid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRealName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.getShort(offset + 2) != 0);
        entity.setAreaDetile(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhoneNum(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCode(cursor.getInt(offset + 5));
        entity.setIsDefult(cursor.getShort(offset + 6) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AddressBean entity, long rowId) {
        entity.setAddressid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AddressBean entity) {
        if(entity != null) {
            return entity.getAddressid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AddressBean entity) {
        return entity.getAddressid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}