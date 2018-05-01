package org.lanmei.seckill.common;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.unitils.core.UnitilsException;
import org.unitils.dbmaintainer.locator.ClassPathDataLocator;
import org.unitils.dbmaintainer.locator.resourcepickingstrategie.ResourcePickingStrategie;
import org.unitils.dbunit.DbUnitModule;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.DbUnitDatabaseConnection;
import org.unitils.dbunit.util.MultiSchemaDataSet;
 
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
public class MyDbUnitModule extends DbUnitModule {
 
  //完善DbUnitDatabaseConnection连接信息
  @Override
  public DbUnitDatabaseConnection getDbUnitDatabaseConnection(final String schemaName) {
    DbUnitDatabaseConnection result = dbUnitDatabaseConnections.get(schemaName);
    if (result != null) {
      return result;
    }
 
    result = super.getDbUnitDatabaseConnection(schemaName);
 
    result.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
    result.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
    return result;
  }
 
  //Excel预处理操作，将@DataSet注释读取的文件返回给DataSetFactory进行处理
  @Override
  protected File handleDataSetResource(ClassPathDataLocator locator, String nameResource, ResourcePickingStrategie strategy, Class<?> testClass) {
    String cloneResource = new String(nameResource);
    String packageName = testClass.getPackage() != null?testClass.getPackage().getName():"";
    String tempName = "";
    if(cloneResource.startsWith(packageName.replace(".", "/"))) {
      cloneResource = tempName = cloneResource.substring(packageName.length());
    } else if(cloneResource.startsWith(packageName)) {
      cloneResource = tempName = cloneResource.substring(packageName.length() + 1);
    } else {
      tempName = cloneResource;
    }
 
    InputStream in = locator.getDataResource(packageName.replace(".", "/") + "/" + tempName, strategy);
    File resolvedFile = null;
    if(in == null) {
      resolvedFile = this.getDataSetResolver().resolve(testClass, cloneResource);
      if(resolvedFile == null) {
        throw new UnitilsException("DataSetResource file with name '" + nameResource + "' cannot be found");
      }
    }
 
    return resolvedFile;
  }
 
  //调用DataSetFactory.createDataSet()向数据库中注入Excel数据后，直接返回DataSet，不对DataSet执行清零操作
  @Override
  protected MultiSchemaDataSet getDataSet(Class<?> testClass, String[] dataSetFileNames, DataSetFactory dataSetFactory) {
    List<File> dataSetFiles = new ArrayList<File>();
 
    ResourcePickingStrategie resourcePickingStrategie = getResourcePickingStrategie();
 
    for (String dataSetFileName : dataSetFileNames) {
      File dataSetFile = handleDataSetResource(new ClassPathDataLocator(), dataSetFileName, resourcePickingStrategie, testClass);
      dataSetFiles.add(dataSetFile);
    }
 
    MultiSchemaDataSet dataSet = dataSetFactory.createDataSet(dataSetFiles.toArray(new File[dataSetFiles.size()]));
    return dataSet;
  }
}
