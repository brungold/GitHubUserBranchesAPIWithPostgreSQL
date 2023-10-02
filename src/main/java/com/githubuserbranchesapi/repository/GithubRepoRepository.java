package com.githubuserbranchesapi.repository;

import com.githubuserbranchesapi.domain.model.Repo;
import org.springframework.data.repository.Repository;

public interface GithubRepoRepository extends Repository<Repo, Long>{
}
