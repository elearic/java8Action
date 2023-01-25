package ericzz.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class SqlUtils {
	/**
	 * 生成占位符
	 * 
	 * @param paramLen
	 * @return
	 */
	public static String genPlaces(int paramLen) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < paramLen; i++) {
			if (i == paramLen - 1) {
				result.append("?");
			} else {
				result.append("?,");
			}
		}
		return result.toString();
	}

	/**
	 * 分割表字段
	 * 
	 * @param columns
	 * @param speator
	 * @return
	 */
	public static String columnsSpeator(List<String> columns, char speator) {
		return StringUtils.join(columns, speator);
	}

	/**
	 * 分割表字段
	 * 
	 * @param columns
	 * @return
	 */
	public static String columnsSpeator(List<String> columns) {
		return StringUtils.join(columns, ',');
	}

	/**
	 * 构建查询语句
	 * 
	 * @param tableName
	 * @param columns
	 * @param conditions
	 * @return
	 */
	public static String buildSelectSql(String tableName, List<String> columns, Map<String, Object> conditions) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(columnsSpeator(columns)).append(" from").append(" ").append(tableName).append(" ");
		sql.append(conditionSql(conditions));
		return sql.toString();
	}

	public static String buildSelectSql(String tableName, List<String> columns) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(columnsSpeator(columns)).append(" from").append(" ").append(tableName).append(" ");
		return sql.toString();
	}

	/**
	 * 拼装条件语句 只支持 =
	 * 
	 * @param conditions
	 * @return
	 */
	public static String conditionSql(Map<String, Object> conditions) {
		StringBuffer sql = new StringBuffer();
		if (conditions != null && !conditions.isEmpty()) {
			sql.append("where ");
			int index = 0;
			Set<Map.Entry<String, Object>> entries = conditions.entrySet();
			for (Map.Entry<String, Object> entry : entries) {
				String key = entry.getKey();
				Object value = entry.getValue();
				sql.append(key).append(" ").append("=").append(" ").append(value);
				if (index != conditions.size() - 1) {
					sql.append(" and ");
				}
				index++;
			}
		}
		return sql.toString();
	}

	/**
	 * 删除语句
	 * 
	 * @param tableName
	 * @param conditions
	 * @return
	 */
	public static String buildDeleteSql(String tableName, Map<String, Object> conditions) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete ").append(" from ").append(tableName).append(" ");
		sql.append(conditionSql(conditions));
		return sql.toString();
	}

	/**
	 * 清空表sql
	 * 
	 * @param tableName
	 * @return
	 */
	public static String truncateTableSql(String tableName) {
		return "truncate table " + tableName + "";
	}

	/**
	 * 复制表数据
	 * 
	 * @param tableName
	 * @param rowdatas
	 * @param columns
	 * @return
	 */
	public static List<Object[]> copyRowsData(String tableName, List<Map<String, Object>> rowdatas,
			List<String> columns, Map<String, String> transColumns, Map<String, Object> defauleValues) {
		List<Object[]> argsList = Lists.newArrayList();
		for (Map<String, Object> item : rowdatas) {
			Object[] row = new Object[columns.size()];
			for (int i = 0; i < columns.size(); i++) {
				String columnName = columns.get(i);
				if (StringUtils.equals(columnName, "course_id") && item.containsKey("course_static_id")) {
					row[i] = item.get("course_static_id");
				} else if (StringUtils.equals(columnName, "id") && item.containsKey(tableName + "_id")) {
					row[i] = item.get(tableName + "_id");
				} else {
					row[i] = item.get(columnName);
				}
				if (transColumns != null && !transColumns.isEmpty()) {
					if (transColumns.containsKey(columnName)) {
						row[i] = item.get(transColumns.get(columnName));
					}
				}
				if (defauleValues != null && !defauleValues.isEmpty()) {
					if (defauleValues.containsKey(columnName)) {
						row[i] = defauleValues.get(columnName);
					}
				}
			}
			argsList.add(row);
		}
		return argsList;
	}

	/**
	 * 构建查询语句
	 * 
	 * @param tableName
	 * @param columns
	 * @return
	 */
	public static String buildInsertSql(String tableName, List<String> columns) {
		String insertSql = "insert into " + tableName + " (" + columnsSpeator(columns) + ") " + "values ("
				+ genPlaces(columns.size()) + ")";
		return insertSql;
	}

	/**
	 * 迁移表
	 * 
	 * @param sourceTableName
	 *            原表
	 * @param sourceTableColumns
	 *            原表字段
	 * @param targetTableName
	 *            目标表
	 * @param targetableColumns
	 *            目标迁移字段
	 */
	public static void tableFullCopay(JdbcTemplate sourceJdbc, String sourceTableName, List<String> sourceTableColumns,
			JdbcTemplate targetJdbc, String targetTableName, List<String> targetableColumns,
			Map<String, String> transColumns, Map<String, Object> defauleValues) {
		Long startTime = System.currentTimeMillis();
		log.info("开始迁移表【{}】", sourceTableName);
		String querySql = SqlUtils.buildSelectSql(sourceTableName, sourceTableColumns, null);
		List<Map<String, Object>> migrateList = sourceJdbc.queryForList(querySql);
		if (CollectionUtils.isNotEmpty(migrateList)) {
			log.info("本次迁移数据为【{}】", migrateList.size());
			targetJdbc.update(SqlUtils.truncateTableSql(targetTableName));
			List<Object[]> argsList = SqlUtils.copyRowsData(sourceTableName, migrateList, targetableColumns,
					transColumns, defauleValues);
			String insertSql = SqlUtils.buildInsertSql(targetTableName, targetableColumns);
			targetJdbc.batchUpdate(insertSql, argsList);
			log.info("本次迁移数据完成【{}】,耗时【{}】", sourceTableName, System.currentTimeMillis() - startTime);
		} else {
			log.info("表 【{}】无需迁移", sourceTableName);
		}
	}

	/**
	 *
	 * @param sourceJdbc
	 * @param sourceTableName
	 * @param sourceTableColumns
	 * @param targetJdbc
	 * @param targetTableName
	 * @param targetableColumns
	 */
	public static void tableFullCopay(JdbcTemplate sourceJdbc, String sourceTableName, List<String> sourceTableColumns,
			JdbcTemplate targetJdbc, String targetTableName, List<String> targetableColumns,
			Map<String, String> transColumns) {
		tableFullCopay(sourceJdbc, sourceTableName, sourceTableColumns, targetJdbc, targetTableName, targetableColumns,
				transColumns, null);
	}

	/**
	 *
	 * @param sourceJdbc
	 * @param sourceTableName
	 * @param sourceTableColumns
	 * @param targetJdbc
	 * @param targetTableName
	 * @param targetableColumns
	 */
	public static void tableFullCopay(JdbcTemplate sourceJdbc, String sourceTableName, List<String> sourceTableColumns,
			JdbcTemplate targetJdbc, String targetTableName, List<String> targetableColumns) {
		tableFullCopay(sourceJdbc, sourceTableName, sourceTableColumns, targetJdbc, targetTableName, targetableColumns,
				null, null);
	}

	/**
	 * @param sourceJdbc
	 * @param targetJdbc
	 * @param tableName
	 * @param columns
	 */
	public static void tableFullCopay(JdbcTemplate sourceJdbc, JdbcTemplate targetJdbc, String tableName,
			List<String> columns) {
		tableFullCopay(sourceJdbc, tableName, columns, targetJdbc, tableName, columns, null, null);
	}

	public static void main(String[] args) {

	}
}
