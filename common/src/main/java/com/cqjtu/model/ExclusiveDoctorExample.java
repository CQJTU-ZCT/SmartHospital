package com.cqjtu.model;

import java.util.ArrayList;
import java.util.List;

public class ExclusiveDoctorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExclusiveDoctorExample() {
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

        public Criteria andExclusiveDoctorIdIsNull() {
            addCriterion("exclusive_doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdIsNotNull() {
            addCriterion("exclusive_doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdEqualTo(String value) {
            addCriterion("exclusive_doctor_id =", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdNotEqualTo(String value) {
            addCriterion("exclusive_doctor_id <>", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdGreaterThan(String value) {
            addCriterion("exclusive_doctor_id >", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdGreaterThanOrEqualTo(String value) {
            addCriterion("exclusive_doctor_id >=", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdLessThan(String value) {
            addCriterion("exclusive_doctor_id <", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdLessThanOrEqualTo(String value) {
            addCriterion("exclusive_doctor_id <=", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdLike(String value) {
            addCriterion("exclusive_doctor_id like", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdNotLike(String value) {
            addCriterion("exclusive_doctor_id not like", value, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdIn(List<String> values) {
            addCriterion("exclusive_doctor_id in", values, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdNotIn(List<String> values) {
            addCriterion("exclusive_doctor_id not in", values, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdBetween(String value1, String value2) {
            addCriterion("exclusive_doctor_id between", value1, value2, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andExclusiveDoctorIdNotBetween(String value1, String value2) {
            addCriterion("exclusive_doctor_id not between", value1, value2, "exclusiveDoctorId");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("status_id is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("status_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(Integer value) {
            addCriterion("status_id =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(Integer value) {
            addCriterion("status_id <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(Integer value) {
            addCriterion("status_id >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_id >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(Integer value) {
            addCriterion("status_id <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(Integer value) {
            addCriterion("status_id <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<Integer> values) {
            addCriterion("status_id in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<Integer> values) {
            addCriterion("status_id not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(Integer value1, Integer value2) {
            addCriterion("status_id between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(Integer value1, Integer value2) {
            addCriterion("status_id not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andUseIdCardIsNull() {
            addCriterion("use_id_card is null");
            return (Criteria) this;
        }

        public Criteria andUseIdCardIsNotNull() {
            addCriterion("use_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andUseIdCardEqualTo(String value) {
            addCriterion("use_id_card =", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardNotEqualTo(String value) {
            addCriterion("use_id_card <>", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardGreaterThan(String value) {
            addCriterion("use_id_card >", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("use_id_card >=", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardLessThan(String value) {
            addCriterion("use_id_card <", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardLessThanOrEqualTo(String value) {
            addCriterion("use_id_card <=", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardLike(String value) {
            addCriterion("use_id_card like", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardNotLike(String value) {
            addCriterion("use_id_card not like", value, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardIn(List<String> values) {
            addCriterion("use_id_card in", values, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardNotIn(List<String> values) {
            addCriterion("use_id_card not in", values, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardBetween(String value1, String value2) {
            addCriterion("use_id_card between", value1, value2, "useIdCard");
            return (Criteria) this;
        }

        public Criteria andUseIdCardNotBetween(String value1, String value2) {
            addCriterion("use_id_card not between", value1, value2, "useIdCard");
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