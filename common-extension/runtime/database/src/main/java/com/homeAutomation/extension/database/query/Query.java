package com.homeAutomation.extension.database.query;

import com.homeAutomation.extension.database.pagination.PageRequest;
import com.homeAutomation.extension.database.query.builder.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    public static BaseQueryBuilder builder() {
        return new Builder();
    }

    @NoArgsConstructor
    public static class Builder implements JoinQueryBuilder, ThenGroupQueryBuilder, BaseQueryBuilder, AdditionalWhereQueryBuilder, ThenOrderQueryBuilder, BuildQueryBuilder, OrderQueryBuilder {
        private final List<Object> params = new ArrayList<>();
        private final StringBuilder where = new StringBuilder();
        private final StringBuilder join = new StringBuilder();
        private final StringBuilder order = new StringBuilder();
        private final StringBuilder group = new StringBuilder();
        private String from = "";
        private String select = "";

        public Builder(String paramName, Object value) {
            this(paramName, value, Comparator.EQUALS);
        }

        public Builder(String paramName, Object value, Comparator comparator) {
            where(paramName, value, comparator);
        }

        private void appendToWhereIfStarted(String str) {
            if (where.length() == 0) return;

            where.append(str);
        }

        @Override
        public AndQueryBuilder and(String paramName, Object value) {
            return and(paramName, value, Comparator.EQUALS);
        }

        @Override
        public AndQueryBuilder and(String paramName, Object value, boolean isNullCheck) {
            if (isNullCheck && value == null) {
                return andNull(paramName);
            }
            return and(paramName, value);
        }

        @Override
        public AndQueryBuilder andContains(Collection<String> columns, Object expected) {
            if (!CollectionUtils.isEmpty(columns)) {
                appendToWhereIfStarted(" and ");

                params.add(expected);
                where.append(" ?# ").append(Comparator.IN.symbol).append(" (").append(String.join(", ", columns)).append(")").append(" ");
            }
            return this;
        }

        @Override
        public <T> AndQueryBuilder andContains(String paramName, Collection<T> values) {
            if (!CollectionUtils.isEmpty(values)) {
                appendToWhereIfStarted(" and ");

                params.add(values);
                where.append(paramName).append(" ").append(Comparator.IN.symbol).append(" ?# ");
            }
            return this;
        }

        @Override
        public AndQueryBuilder and(Query query) {
            if (query.params.length > 0) {
                appendToWhereIfStarted(" and ");

                where.append("(").append(query.plainWhere).append(")");
                params.addAll(Arrays.asList(query.getParams()));
            }
            return this;
        }

        @Override
        public AndQueryBuilder and(String paramName, Object value, Comparator comparator) {
            if (isValidString(value)) {
                appendToWhereIfStarted(" and ");

                params.add(value);
                where.append(paramName).append(" ").append(comparator.symbol).append(" ?#");
            }

            return this;
        }

        @Override
        public AndQueryBuilder andNot(String paramName, Object value) {
            return and(paramName, value, Comparator.NOT_EQUALS);
        }

        @Override
        public AndQueryBuilder andNot(String paramName, Object value, boolean isNullCheck) {
            if (isNullCheck && value == null) {
                return andNull(paramName);
            }
            return and(paramName, value);
        }

        @Override
        public AndQueryBuilder andNull(String paramName) {
            appendToWhereIfStarted(" and ");
            where.append(paramName).append(" IS NULL");
            return this;
        }

        @Override
        public AndQueryBuilder andNotNull(String paramName) {
            appendToWhereIfStarted(" and ");
            where.append(paramName).append(" IS NOT NULL");
            return this;
        }

        @Override
        public OrQueryBuilder or(String paramName, Object value) {
            return or(paramName, value, Comparator.EQUALS);
        }

        @Override
        public OrQueryBuilder or(String paramName, Object value, boolean isNullCheck) {
            if (isNullCheck && value == null) {
                return orNull(paramName);
            }
            return or(paramName, value);
        }

        @Override
        public OrQueryBuilder or(Query query) {
            if (query.params.length > 0) {
                appendToWhereIfStarted(" or ");
                where.append("(").append(query.plainWhere).append(")");
                params.addAll(Arrays.asList(query.getParams()));
            }
            return this;
        }

        @Override
        public OrQueryBuilder or(String paramName, Object value, Comparator comparator) {
            if (isValidString(value)) {
                appendToWhereIfStarted(" or ");
                params.add(value);
                where.append(paramName).append(" ").append(comparator.symbol).append(" ?#");
            }
            return this;
        }

        @Override
        public OrQueryBuilder orNot(String paramName, Object value) {
            return or(paramName, value, Comparator.NOT_EQUALS);
        }

        @Override
        public OrQueryBuilder orNot(String paramName, Object value, boolean isNullCheck) {
            if (isNullCheck && value == null) {
                return orNull(paramName);
            }
            return or(paramName, value);
        }

        @Override
        public OrQueryBuilder orNull(String paramName) {
            appendToWhereIfStarted(" or ");
            where.append(paramName).append(" IS NULL");
            return this;
        }

        @Override
        public OrQueryBuilder orNotNull(String paramName) {
            appendToWhereIfStarted(" or ");
            where.append(paramName).append(" IS NOT NULL");
            return this;
        }

        @Override
        public AndQueryBuilder orContains(Collection<String> columns, Object expected) {
            if (!CollectionUtils.isEmpty(columns)) {
                appendToWhereIfStarted(" or ");

                params.add(expected);
                where.append(" ?# ").append(Comparator.IN.symbol).append(" (").append(String.join(", ", columns)).append(")").append(" ");
            }
            return this;
        }

        @Override
        public <T> AndQueryBuilder orContains(String paramName, Collection<T> values) {

            if (!CollectionUtils.isEmpty(values)) {
                appendToWhereIfStarted(" or ");

                params.add(values);
                where.append(paramName).append(" ").append(Comparator.IN.symbol).append(" ?# ");
            }
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
        public WhereQueryBuilder select(String query) {
            select = "SELECT " + query;
            return this;
        }

        @Override
        public WhereQueryBuilder select(String... params) {
            select = "SELECT " + String.join(", ", params);
            return this;
        }

        @Override
        public ThenGroupQueryBuilder group(String query) {
            if (group.length() > 0) {
                group.delete(0, group.length());
            }
            group.append(" GROUP BY ").append(query);
            return this;
        }

        @Override
        public ThenGroupQueryBuilder thenGroupBy(String query) {
            group.append(query);
            return this;
        }

        @Override
        public JoinQueryBuilder from(String query) {
            from = " FROM " + query;
            return this;
        }

        @Override
        public Query build() {
            return this.build(0);
        }

        @Override
        public Query build(int offset) {
            String whereParametrized = where.toString();
            for (int i = 1; i <= params.size(); i++) {
                whereParametrized = whereParametrized.replaceFirst("#", String.valueOf(i + offset));
            }
            if (!whereParametrized.isBlank() && !from.isBlank()) {
                whereParametrized = " WHERE " + whereParametrized;
            }

            return new Query(select + from + join + whereParametrized + group + order, where.toString(), params.toArray());
        }

        @Override
        public AdditionalWhereQueryBuilder where(String paramName, Object value, Comparator comparator) {
            if (isValidString(value)) {
                params.add(value);
                where.append(paramName).append(" ").append(comparator.symbol).append(" ?#");
            }
            return this;
        }

        @Override
        public AdditionalWhereQueryBuilder where(String paramName, Object value) {
            return where(paramName, value, Comparator.EQUALS);
        }

        @Override
        public AdditionalWhereQueryBuilder where(String paramName, Object value, boolean isNullCheck) {
            if (isNullCheck && value == null) {
                return whereNull(paramName);
            }
            return where(paramName, value);
        }

        @Override
        public AdditionalWhereQueryBuilder whereNull(String paramName) {
            where.append(paramName).append(" IS NULL");
            return this;
        }

        @Override
        public AdditionalWhereQueryBuilder whereNotNull(String paramName) {
            where.append(paramName).append(" IS NOT NULL");
            return this;
        }

        private boolean isValidString(Object value) {
            return !(value instanceof CharSequence && StringUtils.isBlank((CharSequence) value)) && Objects.nonNull(value);
        }

        @Override
        public ThenOrderQueryBuilder orderBy(String paramName) {
            return orderBy(paramName, OrderType.ASC);
        }

        @Override
        public ThenOrderQueryBuilder orderByDesc(String paramName) {
            return orderBy(paramName, OrderType.DESC);
        }

        private ThenOrderQueryBuilder orderBy(String paramName, OrderType type) {
            order.append(" ORDER BY ").append(paramName).append(" ").append(type);
            return this;
        }

        @Override
        public ThenOrderQueryBuilder thenOrderBy(String paramName) {
            return thenOrderBy(paramName, OrderType.ASC);
        }

        @Override
        public ThenOrderQueryBuilder thenOrderByDesc(String paramName) {
            return thenOrderBy(paramName, OrderType.DESC);
        }

        @Override
        public ThenOrderQueryBuilder orderBy(PageRequest pageRequest) {
            if (StringUtils.isBlank(pageRequest.getOrderBy())) {
                return this;
            }

            List<String> order = new ArrayList<>(Arrays.asList(pageRequest.getOrderBy().replaceAll("\\s", "").split(",")));

            Optional<OrderType> typeSafe = OrderType.getTypeSafe(order.remove(order.size() - 1));

            if (typeSafe.isEmpty()) {
                return this;
            }

            order = order.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());

            if (order.isEmpty()) {
                return this;
            }

            orderBy(order.remove(0), typeSafe.get());

            for (String s : order) {
                thenOrderBy(s, typeSafe.get());
            }

            return this;
        }

        private ThenOrderQueryBuilder thenOrderBy(String paramName, OrderType type) {
            order.append(", ").append(paramName).append(" ").append(type);
            return this;
        }
    }

    @AllArgsConstructor
    public enum Comparator {
        EQUALS("="), NOT_EQUALS("!="), LESS_THAN("<"), LESS_THAN_EQUALS("<="), MORE_THAN(">"), MORE_THAN_EQUALS(">="), LIKE("LIKE"), IN("IN");

        private final String symbol;
    }

    public enum JoinType {
        INNER, LEFT, RIGHT, FULL
    }

    public enum OrderType {
        ASC, DESC;

        public static Optional<OrderType> getTypeSafe(String value) {
            try {
                return Optional.of(valueOf(value.toUpperCase()));
            } catch (Exception e) {
                return Optional.empty();
            }
        }
    }
}
