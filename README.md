**------------------------------------------ Selecting Technology Stack For Blog APP ------------------------------------------**

**Java Version** : Java 17+

**Java Framework** : Spring Boot, Spring Security, Spring Data JPA

**Token Based Authentication** : JWT

**Build Tool** : Maven

**IDE** : Intellij IDEA

**Server** : Tomcat Embedded Server

**DataBase** : MySQL Database 8.0.21

**Rest Client** : Postman


**---------------- Detailed Description Of Rest API With all The End Point Of Application ----------------**



**1.Post Management**

_Create Post (POST)_	          ->    /api/posts	          201(Created)

_Get Single Post (GET)_         ->		/api/posts/{id} 	    200(OK)

_Get All Posts (GET)_           ->		/api/posts            200(OK)

_Get All Posts With Pagination & Sorting (GET)_  ->     /api/posts?pageSize=5&pageNo=1&sortBy=firstName      200(OK)

_Update Post(PUT)_              ->		/api/posts/{id}	      200(OK)

_Delete Post(DELETE)_           -> 	 /api/posts/{id}	      200(OK)


**2.Comment Management**

_Create Comment (POST)_      ->    /api/posts/{post-id}/comments		                201(Created)  

_Get Single Comment(GET)_	   ->    /api/posts/{post-id}/comments/{comments-id}      200(OK)

_Get All Comment(GET)_	     ->    /api/posts/{post-id}/comments	                  200(OK)

_Update Comment(PUT)_	       ->    /api/posts/{post-id}/comments/{comments-id}	    200(OK)

_Delete Comment (DELETE)_    ->    /api/posts/{post-id}/comments/{comments-id}	    200(OK)

