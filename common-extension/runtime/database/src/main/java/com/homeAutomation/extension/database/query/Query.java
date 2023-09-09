package com.homeAutomation.extension.database.query;

import com.homeAutomation.extension.database.query.builder.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Getter
@AllArgsConstructor
public class Query {
    private final String where;

    @Getter(AccessLevel.NONE)
    private final String plainWhere;
    private final Object[] params;

    public static AdditionalWhereQueryBuilder builder(String paramName, Object value, Comparator comparator) {
        return new Builder(paramName, value, comparator);
    }

    public static AdditionalWhereQueryBuilder builder(String paramName, Object value) {
        return new Builder(paramName, value);
    }

    public static FromQueryBuilder builder() {
        return new Builder();
    }

    @NoArgsConstructor
    public static class Builder implements JoinQueryBuilder, GroupQueryBuilder, WhereQueryBuilder, AdditionalWhereQueryBuilder, FromQueryBuilder {
        private final List<Object> params = new ArrayList<>();
        private final StringBuilder where = new StringBuilder();
        private final StringBuilder join = new StringBuilder("");
        private String from = "";
        private String select = "";
        private String group = "";

        private boolean hasStarted = false;

        public Builder(String paramName, Object value) {
            this(paramName, value, Comparator.EQUALS);
        }

        public Builder(String paramName, Object value, Comparator comparator) {
            where(paramName, value, comparator);
        }

        @Override
        public AndQueryBuilder and(String paramName, Object value) {
            return and(paramName, value, Comparator.EQUALS);
        }

        @Override
        public AndQueryBuilder andContains(Set<String> columns, Object expected) {
            if (!CollectionUtils.isEmpty(columns)) {
                if (hasStarted) where.append(" and ");

                params.add(expected);
                where.append(" ?# ").append(Comparator.IN.symbol).append(" (").append(String.join(", ", columns)).append(")").append(" ");
                hasStarted = true;
            }
            return this;
        }

        @Override
        public AndQueryBuilder and(Query query) {
            if (query.params.length > 0) {
                if (hasStarted) where.append(" and ");

                where.append("(").append(query.plainWhere).append(")");
                params.addAll(Arrays.asList(query.getParams()));
                hasStarted = true;
            }
            return this;
        }

        @Override
        public AndQueryBuilder and(String paramName, Object value, Comparator comparator) {
            if (isValidString(value)) {
                if (hasStarted) where.append(" and ");

                params.add(value);
                where.append(paramName).append(" ").append(comparator.symbol).append(" ?#");
                hasStarted = true;
            }

            return this;
        }

        @Override
        public AndQueryBuilder andNull(String paramName) {
            if (hasStarted) where.append(" and ");
            where.append(paramName).append(" IS NULL");

            hasStarted = true;
            return this;
        }

        @Override
        public AndQueryBuilder andNotNull(String paramName) {
            if (hasStarted) where.append(" and ");
            where.append(paramName).append(" IS NOT NULL");
            hasStarted = true;
            return this;
        }

        @Override
        public AndQueryBuilder andNotDeleted() {
            if (hasStarted) where.append(" and ");
            where.append("deleted").append(" = false");
            hasStarted = true;
            return this;
        }

        @Override
        public OrQueryBuilder or(String paramName, Object value) {
            return or(paramName, value, Comparator.EQUALS);
        }

        @Override
        public OrQueryBuilder or(Query query) {
            if (query.params.length > 0) {
                if (hasStarted) where.append(" or ");
                where.append("(").append(query.plainWhere).append(")");
                params.addAll(Arrays.asList(query.getParams()));
                hasStarted = true;
            }
            return this;
        }

        @Override
        public OrQueryBuilder or(String paramName, Object value, Comparator comparator) {
            if (isValidString(value)) {
                if (hasStarted) where.append(" or ");
                params.add(value);
                where.append(paramName).append(" ").append(comparator.symbol).append(" ?#");
                hasStarted = true;
            }
            return this;
        }

        @Override
        public OrQueryBuilder orNull(String paramName) {
            if (hasStarted) where.append(" or ");
            where.append(paramName).append(" IS NULL");
            hasStarted = true;
            return this;
        }

        @Override
        public OrQueryBuilder orNotNull(String paramName) {
            if (hasStarted) where.append(" or ");
            where.append(paramName).append(" IS NOT NULL");
            hasStarted = true;
            return this;
        }

        @Override
        public JoinQueryBuilder join(String table, String key, String foreignKey) {
            return join(JoinType.INNER, table, key, foreignKey);
        }

        @Override
        public JoinQueryBuilder join(JoinType joinType, String table, String key, String foreignKey) {
            join.append(String.format(" %s JOIN %s ON %s = %s ", joinType.name(), table, key, foreignKey));
            return this;
        }

        @Override
        public GroupQueryBuilder select(String query) {
            select = "SELECT " + query;
            return this;
        }

        @Override
        public WhereQueryBuilder group(String query) {
            group = " GROUP BY " + query;
            return this;
        }

        @Override
        public JoinQueryBuilder from(String query) {
            from = " FROM " + query;
            return this;
        }

        @Override
        public Query build() {
            String whereParametrized = where.toString();
            for (int i = 1; i <= params.size(); i++) {
                whereParametrized = whereParametrized.replaceFirst("#", i + "");
            }
            if (!whereParametrized.isBlank() && !from.isBlank()) whereParametrized = " WHERE " + whereParametrized;

            return new Query(select + from + join + whereParametrized + group, where.toString(), params.toArray());
        }

        @Override
        public AdditionalWhereQueryBuilder where(String paramName, Object value, Comparator comparator) {
            if (isValidString(value)) {
                params.add(value);
                where.append(paramName).append(" ").append(comparator.symbol).append(" ?#");
                hasStarted = true;
            }
            return this;
        }

        @Override
        public AdditionalWhereQueryBuilder where(String paramName, Object value) {
            return where(paramName, value, Comparator.EQUALS);
        }

        @Override
        public AdditionalWhereQueryBuilder whereNull(String paramName) {
            where.append(paramName).append(" IS NULL");
            hasStarted = true;
            return this;
        }

        @Override
        public AdditionalWhereQueryBuilder whereNotNull(String paramName) {
            where.append(paramName).append(" IS NOT NULL");
            hasStarted = true;
            return this;
        }

        private boolean isValidString(Object value) {
            return !(value instanceof CharSequence && StringUtils.isBlank((CharSequence) value)) && Objects.nonNull(value);
        }
    }

    @AllArgsConstructor
    public enum Comparator {
        EQUALS("="),
        LESS_THAN("<"),
        LESS_THAN_EQUALS("<="),
        MORE_THAN(">"),
        MORE_THAN_EQUALS(">="),
        LIKE("LIKE"),
        IN("IN");

        private final String symbol;
    }

    public enum JoinType {
        INNER,
        LEFT,
        RIGHT,
        FULL
    }
}
