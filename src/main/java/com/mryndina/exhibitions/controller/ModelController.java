package com.mryndina.exhibitions.controller;

import com.mryndina.exhibitions.entity.Book;
import com.mryndina.exhibitions.service.AuthorService;
import com.mryndina.exhibitions.service.ModelService;
import com.mryndina.exhibitions.service.CategoryService;
import com.mryndina.exhibitions.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/modeller")
public class ModelController {

	private final ModelService modelService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;

	@Autowired
	public ModelController(ModelService modelService, AuthorService authorService, CategoryService categoryService,
						   PublisherService publisherService) {
		this.modelService = modelService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@RequestMapping("/model")
	public String findAllBooks(Model model) {
		final List<Book> books = modelService.findAllBooks();

		model.addAttribute("books", books);
		return "list-models";
	}

	@RequestMapping("/searchBook")
	public String searchBook(@Param("keyword") String keyword, Model model) {
		final List<Book> books = modelService.searchBooks(keyword);

		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		return "list-models";
	}

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") int id, Model model) {
		final Book book = modelService.findBookById(id);

		model.addAttribute("book", book);
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Book book, Model model) {
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "add-model";
	}

	@RequestMapping("/add-model")
	public String createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-model";
		}

		modelService.createBook(book);
		model.addAttribute("book", modelService.findAllBooks());
		return "redirect:/modeller/model";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		final Book book = modelService.findBookById(id);

		model.addAttribute("book", book);
		return "update-book";
	}

	@RequestMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") int id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setId(id);
			return "update-book";
		}

		modelService.updateBook(book);
		model.addAttribute("book", modelService.findAllBooks());
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") int id, Model model) {
		modelService.deleteBook(id);

		model.addAttribute("book", modelService.findAllBooks());
		return "redirect:/modeller/model";
	}

}
