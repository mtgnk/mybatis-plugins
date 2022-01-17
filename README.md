## mybatis generator plugin
mybatis generator pluginです。

## 利用例
build.gradleでlibraryを読み込み、mybatis generatorの設定を行います。
DBはpostgresを例とした場合、以下のように書きます。
```build.gradle

configurations {
    mybatisGenerator
}

dependencies {
    // 参照プロジェクト
    // postgres
    runtimeOnly "org.postgresql:postgresql:42.3.1"

    // mybatisクラス自動生成定義
    mybatisGenerator group: "org.postgresql", name: "postgresql" version:42.3.1
    mybatisGenerator group: "org.mybatis.generator", name: "mybatis-generator-core" version:1.4.0
    mybatisGenerator group: "com.mtgnk", name: "mybatis_plugins", version: 0.0.1
}

task scdbExampleGenerator(dependsOn: ":core:generator-plugins:build" ) {
  doLast {
    ant.taskdef(name: "mbgenerator", classname: "org.mybatis.generator.ant.GeneratorAntTask", classpath: configurations.scdbExampleGenerator.asPath)
    // generator configを指定する
    ant.mbgenerator(overwrite: true, configfile: "/src/main/resources/mybatis/generatorConfig.xml", verbose: true)
  }
}


```

上記で指定したmybatisgeneratorのgeneratorConfig.xmlは以下のように設定します。
```generatorConfig.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>

    <context id="postgresTables" targetRuntime="MyBatis3DynamicSql">
        <property name="javaFileEncoding" value="UTF-8"/>
        <!-- plugin として読みこみます start-->
        <plugin type="com.mtgnk.mybatis.generator.plugins.EntityClassNamePlugin"/>
        <!-- plugin として読みこみます end-->

        <commentGenerator>
            <property name="addRemarkComments" value="true"/>
            <property name="suppressDate" value="true"/>
        </commentGenerator>

        <jdbcConnection driverClass="org.postgresql.Driver" connectionURL="jdbc:postgresql://localhost:15432/test_db"
                        userId="admin" password="admin"></jdbcConnection>

        <javaTypeResolver>
            <property name="useJSR310Types" value="true"/>
        </javaTypeResolver>

        <javaModelGenerator
                targetPackage="com.mtgnk.autogen"
                targetProject="autogen/src/main/java">
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <javaClientGenerator
                targetPackage="com.mtgnk.autogen"
                targetProject="autogen/src/main/java">
        </javaClientGenerator>

        <!-- table -->
        <table tableName="User" modelType="flat">
            <property name="useActualColumnNames" value="false"/>
            <generatedKey column="user_id" sqlStatement="JDBC" identity="true"/>
        </table>
    </context>

</generatorConfiguration>

```

