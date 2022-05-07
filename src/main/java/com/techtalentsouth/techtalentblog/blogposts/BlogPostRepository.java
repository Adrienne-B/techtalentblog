package com.techtalentsouth.techtalentblog.blogposts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost,Long> {
	/*The first parameter is the type that will be stored in the data base, the second parameter is the type that is sued for the primary key, which is also in the BlogPost page.*/
	/*Additional methods that aren't provided by CrudRepository that we want to perform-on the database goes in her.e*/
	/*We do not add methods with any name we want to do anything, instead the methods are scanned by Spring Boot when loading this file to determine queries we want to do on the database.*/
	/*Remember we do not have anything inside of the function b/c the blogpostrepository is an inheritant of Crudrep.*/

}

