package com.techtalentsouth.techtalentblog.blogposts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*7. A controller is going to be responsible for handling our web page request. So make sure you have an annotation controller.*/

@Controller
public class BlogPostController {

	/* Tells SpringBoot to set this automatically via INJECTION */
	@Autowired /* 11. This is step 11 after page BlogPostRepository. */
	private BlogPostRepository blogPostRepository;

	/* 15 */
	private static List<BlogPost> posts = new ArrayList<>();

	/*
	 * 8. Now we need a method that returns a web-page, we want the page to respond
	 * to any request from. So we use GetMapping.
	 */
	@GetMapping(value = "/")
	public String index(Model model) { /* Model acts like a hashMap */
		posts.removeAll(posts);
		Iterable<BlogPost> iterable = blogPostRepository.findAll(); /* returns your post */

		Iterator<BlogPost> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			posts.add(iterator.next());
		}

		model.addAttribute("posts", posts);
		return "blogpost/index";
	}

	@GetMapping(value = "/blogposts/new")
	public String newBlog(BlogPost post) {
		// post.setAuthor("Adrienne");
		return "blogpost/new";
	}

	/* 12. */
	@PostMapping(value = "/blogposts")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		/*
		 * This will have everything that is field out by the user and once entered it
		 * will be saved in the database.
		 */
		BlogPost savedBlogPost = blogPostRepository.save(blogPost);

		model.addAttribute("blogPost", savedBlogPost);
		return "blogpost/result"; /* We need a result page, which will be created. */

	}

	@GetMapping(value = "/blogposts/{id}")
	public String editPostWithid(@PathVariable Long id, Model model) {
		/*
		 * Now we are going to use the id to load the blog post from the database table
		 * that has that id.
		 */
		Optional<BlogPost> post = blogPostRepository.findById(id);
		if (post.isPresent()) {
			BlogPost actualPost = post.get();
			model.addAttribute("blogPost", actualPost);
		}
		return "blogpost/edit";
	}

	@RequestMapping(value = "/blogposts/update/(id)")
	public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
		Optional<BlogPost> post = blogPostRepository.findById(id);
		if (post.isPresent()) {
			BlogPost actualPost = post.get();
			actualPost.setTitle(blogPost.getTitle());
			actualPost.setAuthor(blogPost.getAuthor());
			actualPost.setBlogEntry(blogPost.getBlogEntry());
			blogPostRepository.save(actualPost);
			model.addAttribute("blogPost", actualPost);
		}
		return "blogpost/result";
	}

	@RequestMapping(value = "/blogposts/delete/{id}")
	public String deletePostById(@PathVariable Long id) {
		blogPostRepository.deleteById(id);
		return "blogpost/delete";
	}
}
