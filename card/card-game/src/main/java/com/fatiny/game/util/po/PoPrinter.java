package com.fatiny.game.util.po;


import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.fatiny.game.util.DirUtils;
import com.fatiny.game.util.StringHelper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import lombok.extern.slf4j.Slf4j;

/**
 * PO打印机4.2
 */
@Slf4j
public class PoPrinter {

	/**
	 * 打印
	 *
	 * @param ds
	 *            数据源
	 * @param printPath
	 *            打印路径
	 * @param packageName
	 *            希望打印的包名
	 *
	 * 打印结果会按照TbName命名
	 */
	public static void print(DataSource ds, String printPath,
							 String packageName) throws Exception {

		try {
			Connection conn = ds.getConnection();
			//ResultSet tbRs = conn.getMetaData().getTables(null, null, null, null);
			ResultSet tbRs = conn.getMetaData().getTables("lszj_game", null, "%", new String[] { "TABLE" });
			List<String> tbNames = new LinkedList<>();
			while(tbRs.next()) {
//				tbNames.add(tbRs.getString(3));
				tbNames.add(tbRs.getString("TABLE_NAME"));
			}
			for (String tbName : tbNames) {

				ResultSet rs = conn.getMetaData().getColumns("lszj_game", null, tbName, null);
				ResultSet keys = conn.getMetaData().getPrimaryKeys("lszj_game", null, tbName);

				String poName = StringHelper.underlineToUpperCamal(tbName);
				String basePoName = "Tb" + poName + "Po";

				List<String> colNames = new ArrayList<>();
				List<String> colTypes = new ArrayList<>();
				List<String> staticColNames = new ArrayList<>();
				List<String> remarks = new ArrayList<>();
				List<String> defaults = new ArrayList<>();
				List<String> keyColNames = new ArrayList<>();
				List<String> keyColNames2 = new ArrayList<>();
				
				while (rs.next()) {
					String cname = rs.getString("COLUMN_NAME");
					String ctype = rs.getString("TYPE_NAME");
					
					try {
						ctype = sqlTypeToJavaType(ctype);
						
					} catch (Exception e) {
						log.error("e:{}, cname:{} , poName:{}", e, cname, poName);
					}
					
					colNames.add(StringHelper.underlineToLowerCamal(cname));
					colTypes.add(ctype);
					staticColNames.add("PROP_"+StringHelper.camalToUnderline(cname).toUpperCase());
					remarks.add(rs.getString("REMARKS"));

					String def = rs.getString("COLUMN_DEF");
					if (def != null) {
						if (ctype.contains("Long")) {
							def += "L";
						}
						else if (ctype.contains("Boolean")) {
							def = "false";
						}
						else if (ctype.contains("String")) {
							def = "\"" + def + "\"";
						}
					}

					defaults.add(def);
				}
				
				// 主键
				while (keys.next()) {
					String keyColName = keys.getString("COLUMN_NAME");
					keyColNames.add(keyColName);
					keyColNames2.add(StringHelper.underlineToLowerCamal(keyColName));
				}

				// 检查路径
				DirUtils.mkdir(printPath + File.separator + "base");

				// 开始打印base类
				BufferedWriter writer = Files.newWriter(new File(printPath + File.separator + "base" + File.separator + basePoName + ".java"), Charsets.UTF_8);
				PrintWriter out = new PrintWriter(writer);
				out.println("package " + packageName +";");
				out.println();
				out.println("import com.fatiny.core.data.*;");
				out.println();
				out.println("/**");
				out.println(" * this class file is auto output by com.fatiny.game.util.po.PoPrinter");
				out.println(" * @author Jeremy");
				out.println(" * @see com.fatiny.game.util.po.PoPrinter");
				out.println(" */");
				out.println("public abstract class " + basePoName + " extends BasePo {");
				out.println();
//				// 打印静态字段
//				for (int i = 0; i < staticColNames.size(); i++) {
//					out.println("\tpublic static final String " + staticColNames.get(i) + " = \"" + colNames.get(i) + "\";");
//				}
				out.println();
				// 打印对象字段
				for (int i = 0; i < colNames.size(); i++) {
					out.println("\t/** "+remarks.get(i)+" */");
					out.println("\tprivate " + colTypes.get(i) + " " + colNames.get(i) + " = " + defaults.get(i) + ";" );
				}
				out.println();
				
				
				List<String> getterNames = new ArrayList<>();
				// 打印getset
				for (int i = 0; i < colNames.size(); i++) {
					String getterName = " get" + StringHelper.upFirst1(colNames.get(i)) + "()";
					getterNames.add(getterName);
					out.println("\t/** get "+ remarks.get(i) + " */");
					out.println("\tpublic " + colTypes.get(i) + getterName + " {");
					out.println("\t\treturn " + colNames.get(i) + ";");
					out.println("\t}");
					out.println();
					
					out.println("\t/** set " + remarks.get(i) + " */");
					out.println("\tpublic void set" + StringHelper.upFirst1(colNames.get(i)) + "(" + colTypes.get(i) + " " + colNames.get(i) + ") {");
					out.println("\t\tthis." + colNames.get(i) + " = " + colNames.get(i) + ";");
					out.println("\t}");
					out.println();
					
					if (colTypes.get(i).contains("Short")) {
						getterName = " is" + StringHelper.upFirst1(colNames.get(i)) + "()";
						out.println("\tpublic " + colTypes.get(i) + getterName + "{");
						out.println("\t\treturn " + colNames.get(i) + ";");
						out.println("\t}");
						out.println();
					}
				}
				
				
				// 打印props()
				out.println("\t@Override");
				out.println("\tpublic String[] props() {");
				out.println("\t\treturn new String[] {\"`" + StringHelper.join("`\", \"`", colNames) + "`\"};");
				out.println("\t}");
				out.println();

				// 打印propValues()
				out.println("\t@Override");
				out.println("\tpublic Object[] propValues() {");
				out.println("\t\treturn new Object[] {" + StringHelper.join(",", getterNames) + " };");
				out.println("\t}");
				out.println();
				
				// 打印ids()
				out.println("\t@Override");
				out.println("\tpublic String[] ids() {");
				out.println("\t\treturn new String[] {\"`" + StringHelper.join("`\", \"`", keyColNames) + "`\"};");
				out.println("\t}");

				// 打印idValues()
				out.println("\t@Override");
				out.println("\tpublic Object[] idValues() {");
				out.println("\t\treturn new Object[] { " + StringHelper.join(", ", keyColNames2) + " };");
				out.println("\t}");
				
				
				out.println("}");
				out.close();
				writer.close();

				File poFile = new File(printPath + File.separator + poName + ".java");
//				if(!poFile.exists()) {
					
					writer = Files.newWriter(poFile, Charsets.UTF_8);
					out = new PrintWriter(writer);
					out.println("package " + packageName + ";");
					out.println();
					out.println("import " + packageName + "." + basePoName + ";");
					out.println("import com.good.data.*;");
					out.println();
					out.println("/**");
					out.println(" * this class file is auto output by net.good321.frame.db.tool.POPrinter");
					out.println(" * @author chao");
					out.println(" * @see net.good321.frame.db.tool.PoPrinter");
					out.println(" */");
					out.println("@PO(\"" + tbName + "\")");
					out.println("public class " + poName + " extends " + basePoName + " {");
					out.println();

					// 空构造方法
					out.println("\tpublic " + poName + "() {");
					out.println();
					out.println("\t}");
					out.println();
					
					
					// 打印key()标识
					out.println("\t@Override");
					out.println("\tpublic long key() {");
					out.println("\t\t//TODO 第一主键的数据值, 例如playerId");
					out.println("\t\treturn ");
					out.println("\t}");
					out.println();
					
					// 打印cacheId()
					out.println("\t@Override");
					out.println("\tpublic String cacheId() {");
					out.println("\t\t//TODO 缓存的二级ID, 如果不是一对多关系的return null即可");
					out.println("\t\treturn ");
					out.println("\t}");
					out.println();
					
					// 打印keyColumnName
					out.println("\t@Override");
					out.println("\tpublic String keyColumn() {");
					out.println("\t\t//TODO 第一主键的数据库列名");
					out.println("\t\treturn ");
					out.println("\t}");
					out.println();
					
					out.println("}");
					out.close();
					writer.close();
//				}
				System.out.println("生成表：" + conn.getCatalog() + "." + tbName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static String sqlTypeToJavaType(String type){

		if(type.equals("BIGINT")){
			return "java.lang.Long";
		}
		if(type.equals("SMALLINT")){
			return "java.lang.Short";
		}
		if(type.contains("INT")){
			return "java.lang.Integer";
		}
		if(type.equals("FLOAT")){
			return "java.lang.Float";
		}
		if(type.equals("DOUBLE")){
			return "java.lang.Float";
		}
		if(type.contains("CHAR") || type.contains("TEXT")){
			return "java.lang.String";
		}
		if(type.contains("BINARY") || type.contains("BLOB")){
			return "byte[]";
		}
		if(type.contains("DATE") || type.contains("TIME")){
			return "java.util.Date";
		}
		if (type.contains("BIT")) {
			return "java.lang.Boolean";
		}
		
		// 其他类型拜托不要用了
		throw new RuntimeException("unsupported type = " + type);
	}

}