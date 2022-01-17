package com.mtgnk.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * 自動生成テーブルにsuffixとしてEntityをつけるplugin.
 *
 * @author ymitsugi
 */
public class EntityClassNamePlugin extends PluginAdapter {

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public void initialized(IntrospectedTable table) {
    String name = table.getBaseRecordType();
    table.setBaseRecordType(name + "Entity");
  }

}
