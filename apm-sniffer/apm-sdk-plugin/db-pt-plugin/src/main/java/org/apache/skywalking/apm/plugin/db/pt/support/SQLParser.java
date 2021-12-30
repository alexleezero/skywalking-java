package org.apache.skywalking.apm.plugin.db.pt.support;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLDropTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;

import java.util.List;

import static org.apache.skywalking.apm.plugin.db.pt.DatabasePTPluginConfig.Plugin.DBPT.SHADOW_TABLE_SUFFIX;

/**
 * @author lijian
 * @since 2021/12/30
 */
public class SQLParser {

    /**
     * convert original sql to shadow sql
     * @param sql original sql
     * @return shadow sql
     */
    public static String convert2Shadow(String sql, DbType dbType) {
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        for (SQLStatement stmt : stmtList) {
            stmt.accept(new SQLASTVisitorAdapter() {
                @Override
                public boolean visit(SQLAlterTableStatement x) {
                    alterTableName(x.getTableSource());
                    return super.visit(x);
                }

                @Override
                public boolean visit(SQLCreateTableStatement x) {
                    alterTableName(x.getTableSource());
                    return super.visit(x);
                }

                @Override
                public boolean visit(SQLDropTableStatement x) {
                    for (SQLExprTableSource tableSource : x.getTableSources()) {
                        alterTableName(tableSource);
                    }
                    return super.visit(x);
                }

                @Override
                public boolean visit(SQLDeleteStatement x) {
                    alterTableName((SQLExprTableSource) x.getTableSource());
                    return super.visit(x);
                }

                @Override
                public boolean visit(SQLUpdateStatement x) {
                    alterTableName((SQLExprTableSource) x.getTableSource());
                    return super.visit(x);
                }

                @Override
                public boolean visit(SQLInsertStatement x) {
                    alterTableName(x.getTableSource());
                    return super.visit(x);
                }

                @Override
                public boolean visit(SQLSelectStatement x) {
                    SQLSelect select = x.getSelect();
                    SQLSelectQuery query = select.getQuery();
                    SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) query;
                    SQLTableSource from = queryBlock.getFrom();
                    SQLExprTableSource sqlExprTableSource = (SQLExprTableSource) from;
                    SQLIdentifierExpr expr = (SQLIdentifierExpr) sqlExprTableSource.getExpr();
                    if (!expr.getName().contains(SHADOW_TABLE_SUFFIX)) {
                        expr.setName(expr.getName() + SHADOW_TABLE_SUFFIX);
                    }
                    return super.visit(x);
                }
            });
        }
        return SQLUtils.toSQLString(stmtList, dbType);
    }

    private static void alterTableName(SQLExprTableSource tableSource) {
        SQLIdentifierExpr expr = (SQLIdentifierExpr) tableSource.getExpr();
        if (!expr.getName().endsWith(SHADOW_TABLE_SUFFIX)) {
            expr.setName(expr.getName() + SHADOW_TABLE_SUFFIX);
        }
    }

}
