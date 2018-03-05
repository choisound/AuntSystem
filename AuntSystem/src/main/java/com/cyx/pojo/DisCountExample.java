package com.cyx.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DisCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DisCountExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andDiscountIdIsNull() {
            addCriterion("discount_id is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIdIsNotNull() {
            addCriterion("discount_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountIdEqualTo(String value) {
            addCriterion("discount_id =", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotEqualTo(String value) {
            addCriterion("discount_id <>", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdGreaterThan(String value) {
            addCriterion("discount_id >", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdGreaterThanOrEqualTo(String value) {
            addCriterion("discount_id >=", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdLessThan(String value) {
            addCriterion("discount_id <", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdLessThanOrEqualTo(String value) {
            addCriterion("discount_id <=", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdLike(String value) {
            addCriterion("discount_id like", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotLike(String value) {
            addCriterion("discount_id not like", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdIn(List<String> values) {
            addCriterion("discount_id in", values, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotIn(List<String> values) {
            addCriterion("discount_id not in", values, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdBetween(String value1, String value2) {
            addCriterion("discount_id between", value1, value2, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotBetween(String value1, String value2) {
            addCriterion("discount_id not between", value1, value2, "discountId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyIsNull() {
            addCriterion("discount_money is null");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyIsNotNull() {
            addCriterion("discount_money is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyEqualTo(String value) {
            addCriterion("discount_money =", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyNotEqualTo(String value) {
            addCriterion("discount_money <>", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyGreaterThan(String value) {
            addCriterion("discount_money >", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("discount_money >=", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyLessThan(String value) {
            addCriterion("discount_money <", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyLessThanOrEqualTo(String value) {
            addCriterion("discount_money <=", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyLike(String value) {
            addCriterion("discount_money like", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyNotLike(String value) {
            addCriterion("discount_money not like", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyIn(List<String> values) {
            addCriterion("discount_money in", values, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyNotIn(List<String> values) {
            addCriterion("discount_money not in", values, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyBetween(String value1, String value2) {
            addCriterion("discount_money between", value1, value2, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyNotBetween(String value1, String value2) {
            addCriterion("discount_money not between", value1, value2, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeIsNull() {
            addCriterion("discount_time is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeIsNotNull() {
            addCriterion("discount_time is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeEqualTo(Date value) {
            addCriterionForJDBCDate("discount_time =", value, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("discount_time <>", value, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("discount_time >", value, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("discount_time >=", value, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeLessThan(Date value) {
            addCriterionForJDBCDate("discount_time <", value, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("discount_time <=", value, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeIn(List<Date> values) {
            addCriterionForJDBCDate("discount_time in", values, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("discount_time not in", values, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("discount_time between", value1, value2, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("discount_time not between", value1, value2, "discountTime");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitIsNull() {
            addCriterion("discount_limit is null");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitIsNotNull() {
            addCriterion("discount_limit is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitEqualTo(String value) {
            addCriterion("discount_limit =", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitNotEqualTo(String value) {
            addCriterion("discount_limit <>", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitGreaterThan(String value) {
            addCriterion("discount_limit >", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitGreaterThanOrEqualTo(String value) {
            addCriterion("discount_limit >=", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitLessThan(String value) {
            addCriterion("discount_limit <", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitLessThanOrEqualTo(String value) {
            addCriterion("discount_limit <=", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitLike(String value) {
            addCriterion("discount_limit like", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitNotLike(String value) {
            addCriterion("discount_limit not like", value, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitIn(List<String> values) {
            addCriterion("discount_limit in", values, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitNotIn(List<String> values) {
            addCriterion("discount_limit not in", values, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitBetween(String value1, String value2) {
            addCriterion("discount_limit between", value1, value2, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountLimitNotBetween(String value1, String value2) {
            addCriterion("discount_limit not between", value1, value2, "discountLimit");
            return (Criteria) this;
        }

        public Criteria andIsuseIsNull() {
            addCriterion("isuse is null");
            return (Criteria) this;
        }

        public Criteria andIsuseIsNotNull() {
            addCriterion("isuse is not null");
            return (Criteria) this;
        }

        public Criteria andIsuseEqualTo(Integer value) {
            addCriterion("isuse =", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotEqualTo(Integer value) {
            addCriterion("isuse <>", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThan(Integer value) {
            addCriterion("isuse >", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThanOrEqualTo(Integer value) {
            addCriterion("isuse >=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThan(Integer value) {
            addCriterion("isuse <", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThanOrEqualTo(Integer value) {
            addCriterion("isuse <=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseIn(List<Integer> values) {
            addCriterion("isuse in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotIn(List<Integer> values) {
            addCriterion("isuse not in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseBetween(Integer value1, Integer value2) {
            addCriterion("isuse between", value1, value2, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotBetween(Integer value1, Integer value2) {
            addCriterion("isuse not between", value1, value2, "isuse");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}