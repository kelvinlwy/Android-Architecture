package com.androidarchitecture.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "TEST_ITEM".
 */
public class TestItemDao extends AbstractDao<TestItem, Long> {

    public static final String TABLENAME = "TEST_ITEM";

    public TestItemDao(DaoConfig config) {
        super(config);
    }


    public TestItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"TEST_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"USER_ID\" INTEGER NOT NULL ," + // 1: userId
                "\"ID\" INTEGER NOT NULL ," + // 2: id
                "\"TITLE\" TEXT NOT NULL ," + // 3: title
                "\"BODY\" TEXT NOT NULL );"); // 4: body
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TEST_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TestItem entity) {
        stmt.clearBindings();

        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getUserId());
        stmt.bindLong(3, entity.getId());
        stmt.bindString(4, entity.getTitle());
        stmt.bindString(5, entity.getBody());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TestItem entity) {
        stmt.clearBindings();

        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getUserId());
        stmt.bindLong(3, entity.getId());
        stmt.bindString(4, entity.getTitle());
        stmt.bindString(5, entity.getBody());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public TestItem readEntity(Cursor cursor, int offset) {
        TestItem entity = new TestItem( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
                cursor.getInt(offset + 1), // userId
                cursor.getInt(offset + 2), // id
                cursor.getString(offset + 3), // title
                cursor.getString(offset + 4) // body
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, TestItem entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.getInt(offset + 1));
        entity.setId(cursor.getInt(offset + 2));
        entity.setTitle(cursor.getString(offset + 3));
        entity.setBody(cursor.getString(offset + 4));
    }

    @Override
    protected final Long updateKeyAfterInsert(TestItem entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }

    @Override
    public Long getKey(TestItem entity) {
        if (entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TestItem entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /**
     * Properties of entity TestItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property UserId = new Property(1, Integer.class, "userId", false, "USER_ID");
        public final static Property Id = new Property(2, Integer.class, "id", false, "ID");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Body = new Property(4, String.class, "body", false, "BODY");
    }

}
