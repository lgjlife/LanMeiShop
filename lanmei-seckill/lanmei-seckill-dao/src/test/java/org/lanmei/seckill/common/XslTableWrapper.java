package org.lanmei.seckill.common;

import org.dbunit.dataset.AbstractTable;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultTableMetaData;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.springframework.util.StringUtils;
import org.unitils.core.UnitilsException;

class XslTableWrapper extends AbstractTable {  
    private ITable delegate;  
    private String tableName;  
  
    public XslTableWrapper(String tableName, ITable table) {  
        this.delegate = table;  
        this.tableName = tableName;  
    }  
    public int getRowCount() {  
        return delegate.getRowCount();  
    }  
  
    public ITableMetaData getTableMetaData() {  
        ITableMetaData meta = delegate.getTableMetaData();  
        try {  
            return new DefaultTableMetaData(tableName, meta.getColumns(), meta.getPrimaryKeys());  
        } catch (DataSetException e) {  
            throw new UnitilsException("Don't get the meta info from  " + meta, e);  
        }  
    }  
  
    public Object getValue(int row, String column) throws DataSetException {  
        Object delta = delegate.getValue(row, column);  
        if (delta instanceof String) {  
            if (StringUtils.isEmpty((String) delta)) {  
                return null;  
            }  
        }  
        return delta;  
    }  
  
} 