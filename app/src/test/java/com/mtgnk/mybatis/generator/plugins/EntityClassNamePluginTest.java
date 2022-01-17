package com.mtgnk.mybatis.generator.plugins;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.runtime.dynamic.sql.IntrospectedTableMyBatis3DynamicSqlImplV2;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ymitsugi
 */
class EntityClassNamePluginTest {

    @Test
    void initialized() {
        EntityClassNamePlugin entityClassNamePlugin = new EntityClassNamePlugin();
        IntrospectedTable table = new IntrospectedTableMyBatis3DynamicSqlImplV2();
        table.setBaseRecordType("test");
        entityClassNamePlugin.initialized(table);
        assertEquals("testEntity",table.getBaseRecordType());
    }
}