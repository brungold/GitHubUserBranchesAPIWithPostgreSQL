package com.githubuserbranchesapi.repository;

import com.githubuserbranchesapi.domain.model.Repo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GithubRepoRepository extends Repository<Repo, Long>{
    List<Repo> findAll(Pageable pageable);

    List<Repo> findByOwner(String owner);

    List<Repo> findByOwnerAndName(String owner, String name);

    Optional<Repo> findById(Long id);

    Repo save(Repo repo);

    void deleteById(Long id);
}
