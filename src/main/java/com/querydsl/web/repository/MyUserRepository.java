package com.querydsl.web.repository;

import com.querydsl.web.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.querydsl.web.entity.QMyUser;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

public interface MyUserRepository extends JpaRepository<MyUser, Long>, QueryDslPredicateExecutor<MyUser>, QuerydslBinderCustomizer<QMyUser> {
    
    @Override
    default public void customize(final QuerydslBindings bindings, final QMyUser root) {
        bindings.bind(String.class)
          .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.email);
    }

}
