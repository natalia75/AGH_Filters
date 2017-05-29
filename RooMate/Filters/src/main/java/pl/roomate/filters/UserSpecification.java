package pl.roomate.filters;

import javax.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

import roomate.filters.model.UserProfile;

public class UserSpecification implements Specification<UserProfile> {

	private SearchCriteria criteria;
	
	
	
	public UserSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}



	@Override
	public Predicate toPredicate(Root<UserProfile> root, CriteriaQuery<?> query, CriteriaBuilder builder){
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
		return null;
	}
	
}
