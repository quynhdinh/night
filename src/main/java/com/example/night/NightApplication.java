package com.example.night;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class NightApplication {

	@Bean
	CommandLineRunner runner(MessageRepository messageRepository) {
		return args -> {
			messageRepository.save(new Message("Hello from Java 17!"));
			messageRepository.save(new Message("Second message on startup 🚀"));
			messageRepository.save(new Message("Spring Boot + H2 = ❤️"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(NightApplication.class, args);
	}
}


@RestController
@RequestMapping("/")
class HomeController {
	@GetMapping
	public String index() {
		return "Hello, World";
	}
}

@Entity
class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	public Message(String content) {
		this.content = content;
	}
	public Message() {
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}

@Repository
interface MessageRepository extends JpaRepository<Message, Long> {
}


@Service
class MessageService {

	private final MessageRepository messageRepository;

	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public Message createMessage(String content) {
		Message message = new Message(content);
		return messageRepository.save(message);
	}

	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	public Optional<Message> getMessageById(Long id) {
		return messageRepository.findById(id);
	}

	public Message updateMessage(Long id, String newContent) {
		return messageRepository.findById(id)
				.map(message -> {
					message.setContent(newContent);
					return messageRepository.save(message);
				})
				.orElseThrow(() -> new RuntimeException("Message not found"));
	}

	public void deleteMessage(Long id) {
		messageRepository.deleteById(id);
	}
}

@RestController
@RequestMapping("/api/messages")
class MessageController {

	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
	public Message createMessage(@RequestBody String content) {
		return messageService.createMessage(content);
	}

	@GetMapping
	public List<Message> getAllMessages() {
		return messageService.getAllMessages();
	}

	@GetMapping("/{id}")
	public Message getMessageById(@PathVariable Long id) {
		return messageService.getMessageById(id)
				.orElseThrow(() -> new RuntimeException("Message not found"));
	}

	@PutMapping("/{id}")
	public Message updateMessage(@PathVariable Long id, @RequestBody String newContent) {
		return messageService.updateMessage(id, newContent);
	}

	@DeleteMapping("/{id}")
	public void deleteMessage(@PathVariable Long id) {
		messageService.deleteMessage(id);
	}
}

