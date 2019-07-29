package com.fatiny.core.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.fatiny.core.util.HexUtil;

/**
 * sql构造
 * 
 * @author chao
 */
public class SqlHelper {
	
	public static String selectAll(String tbName){
		return "SELECT * FROM " + tbName;
	}

	public static String select(String tbName, String[] idNames){
		StringBuilder sb = new StringBuilder("SELECT * FROM `").append(tbName).append("` WHERE ");
		sb.append(idNames[0]).append("=?");
		for (int i = 1; i < idNames.length; i++) {
			sb.append(" and ").append(idNames[i]).append("=?");
		}

		return sb.toString();
	}

	public static String deleteAll(String tbName){
		return "DELETE FROM " + tbName;
	}

	public static String delete(String tbName, String[] idNames){
		StringBuilder sb = new StringBuilder("DELETE FROM `").append(tbName).append("` WHERE ");
		sb.append(idNames[0]).append("=?");
		for (int i = 1; i < idNames.length; i++) {
			sb.append(" and ").append(idNames[i]).append("=?");
		}

		return sb.toString();
	}
	

	public static String update(String tbName, String[] colNames, String[] idNames){
		StringBuilder sb = new StringBuilder("UPDATE `").append(tbName).append("` SET ");
		sb.append(colNames[0]).append("=?");
		for (int i = 1; i < colNames.length; i++) {
			sb.append(",").append(colNames[i]).append("=?");
		}
		sb.append(" WHERE ");
		sb.append(idNames[0]).append("=?");
		for (int i = 1; i < idNames.length; i++) {
			sb.append(" and ").append(idNames[i]).append("=?");
		}
		return sb.toString();
	}

	
	public static String insert(String tbName, String[] colNames){
		StringBuilder sb = new StringBuilder("INSERT INTO `").append(tbName).append("`");
		StringBuilder cols = new StringBuilder();
		cols.append(colNames[0]);
		for (int i = 1; i < colNames.length; i++) {
			cols.append(",").append(colNames[i]);
		}
		sb.append(" (").append(cols).append(") VALUES (?");
		for (int i = 1; i < colNames.length; i++) {
			sb.append(",?");
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	public static String replace(String tbName, String[] colNames){
		StringBuilder sb = new StringBuilder("REPLACE INTO `").append(tbName).append("`");
		StringBuilder cols = new StringBuilder();
		cols.append(colNames[0]);
		for (int i = 1; i < colNames.length; i++) {
			cols.append(",").append(colNames[i]);
		}
		sb.append(" (").append(cols).append(") VALUES (?");
		for (int i = 1; i < colNames.length; i++) {
			sb.append(",?");
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	public static String spliceSql(String prepareSql, String searchMark, Object[] replacements) {
		if (StringUtils.isEmpty(prepareSql) || StringUtils.isEmpty(searchMark) || replacements == null) {
			return prepareSql;
		}
		int start = 0;
		int end = prepareSql.indexOf(searchMark, start);
		if (end == -1) {
			return prepareSql;
		}
		
		final int sqlLength = prepareSql.length() * 2; 
		final StringBuilder buf = new StringBuilder(sqlLength);

		for (Object element : replacements) {
			Object fieldVal = toFormatObj(element); 
			buf.append(prepareSql.substring(start, end)).append(fieldVal);
			start = end + searchMark.length();
			end = prepareSql.indexOf(searchMark, start);
		}

		buf.append(prepareSql.substring(start));
		return buf.toString();
	}
	

	private static Object toFormatObj(Object element) {
		if (element instanceof String) {
			return "'" + element + "'";
		} else if (element instanceof Date) {
			return getFormatDate((Date) element);
		} else if (element instanceof byte[]) {
			return HexUtil.bytesToHex((byte[]) element);
		}
		return element;
	}
	
	
	private static String getFormatDate(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("''yyyy-MM-dd HH:mm:ss''");
		return f.format(date);
	}
	
	
	public static void main(String[] args) {
		String sql = "INSERT INTO `player` VALUES(?,?,?,?,?,?);";
		byte[] bytes = new byte[] { };
		Date now = new Date();
		Object[] params = { 1, "username", "黑", 12345.003f, now, bytes };
		String newSql = spliceSql(sql, "?", params);
		System.out.println(sql);
		System.out.println(newSql);
		
		SimpleDateFormat ddf = new SimpleDateFormat("''yyyy-MM-dd HH:mm:ss''", Locale.US);
		System.out.println(ddf.format(now));
	}
	
}
