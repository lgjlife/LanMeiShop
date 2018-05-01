package org.lanmei.seckill.common;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import org.unitils.core.UnitilsException;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;


public class MultiSchemaXlsDataSetFactory implements DataSetFactory {
  protected String defaultSchemaName;
 
  public void init(Properties configuration, String s) {
    this.defaultSchemaName = s;
  }
 
  public MultiSchemaDataSet createDataSet(File... dataSetFiles) {
    try {
      MultiSchemaXlsDataSetReader xlsDataSetReader = new MultiSchemaXlsDataSetReader(defaultSchemaName);
      return xlsDataSetReader.readDataSetXls(dataSetFiles);
    } catch (Exception e) {
      throw new UnitilsException("创建数据集失败：" + Arrays.toString(dataSetFiles), e);
    }
  }
 
  public String getDataSetFileExtension() {
    return "xls";
  }
}
