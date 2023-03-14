package plus.log.api.parameter;


import org.springframework.data.jpa.domain.Specification;
import plus.log.api.util.StringUtil;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 1900581010229669687L;

    private List<SearchCriteria> list;

    public GenericSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                if( criteria.getValue() instanceof Date )
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()), (Date) criteria.getValue()));
                else
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                if( criteria.getValue() instanceof Date )
                    predicates.add(builder.lessThan(root.get(criteria.getKey()), (Date) criteria.getValue()));
                else
                    predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                if( criteria.getValue() instanceof Date )
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Date)criteria.getValue()));
                else
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                if( criteria.getValue() instanceof Date )
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Date)criteria.getValue()));
                else
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                List<String> stringList = StringUtil.stringToStringListByDelimiter(criteria.getValue().toString(),";");
                Expression<String> exp = root.get(criteria.getKey());
                Predicate predicate = exp.in(stringList);
                predicates.add(predicate);
            } else if (criteria.getOperation().equals(SearchOperation.GROUP_OR)){
                var stringList = StringUtil.stringToStringListByDelimiter(criteria.getKey(),";");
                var predicateList = new ArrayList<Predicate>();
                stringList.forEach(s -> {
                    Predicate predicate = builder.like(builder.lower(root.get(s)), "%" + criteria.getValue().toString().toLowerCase() + "%");
                    predicateList.add(predicate);
                });
                predicates.add(builder.or(predicateList.toArray(predicateList.toArray(new Predicate[]{}))));

            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}