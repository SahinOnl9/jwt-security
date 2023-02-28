package org.websec.jwtsecurity.repository;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.websec.jwtsecurity.model.Book;
import org.websec.jwtsecurity.model.SearchCriteria;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BookDao {

	private final EntityManager entityManager;

	public List<Book> findBooksBySearchCriteria(Pageable pageable, SearchCriteria searchCriteria) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> root = criteriaQuery.from(Book.class);

		List<Predicate> predicates = buildCriteriaBasedPredicate(searchCriteria, criteriaBuilder, root);
		criteriaQuery.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
		System.out.println("Page: " + pageable.getPageNumber());
		System.out.println("Size: " + pageable.getPageSize());
		typedQuery.setFirstResult(pageable.getPageNumber());
		typedQuery.setMaxResults(pageable.getPageSize());

		return typedQuery.getResultList();
	}

	private List<Predicate> buildCriteriaBasedPredicate(SearchCriteria criteria, CriteriaBuilder cb, Root<Book> root) {
		List<Predicate> predicates = new LinkedList<>();
		if (criteria == null) {
			System.out.println("No search criteria");
			return predicates;
		}

		if (!StringUtils.isBlank(criteria.getSearchTerm())) {
			System.out.println("Search term is present");
			Predicate titlePredicate = cb.like(root.get("title"), "%" + criteria.getSearchTerm() + "%");
			Predicate authorPredicate = cb.like(root.get("author"), "%" + criteria.getSearchTerm() + "%");
			predicates.add(cb.or(titlePredicate, authorPredicate));
		}
		if (!StringUtils.isBlank(criteria.getCategory())) {
			System.out.println("Search category is present");
			predicates.add(cb.equal(root.get("category"), criteria.getCategory().toUpperCase()));
		}
		if (criteria.getRating() != null) {
			System.out.println("Search by rating");
			predicates.add(cb.equal(root.get("rating"), criteria.getRating()));
		}
		return predicates;
	}
}
