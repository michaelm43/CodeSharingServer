package smartspace.layout;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import smartspace.infra.UsersService;

@RestController
public class UserController {
	
	private UsersService usersService;

	@Autowired
	public UserController(UsersService usersService) {
		this.usersService = usersService;
	}
	

	@RequestMapping(
			path="/admin/users/{email}",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] newElement (
			@RequestBody UserBoundary[] users, 
			@PathVariable("email") String adminEmail) {
		
		return this.usersService.newUsers(Arrays.asList(users)
				.stream()
				.map(UserBoundary::convertToEntity)
				.collect(Collectors.toList()))
				.stream()
				.map(UserBoundary::new)
				.collect(Collectors.toList())
				.toArray(new UserBoundary[0]);
	}

	@RequestMapping(
			path="/admin/users/{email}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getUsersUsingPagination (
			@PathVariable("email") String adminEmail,
			@RequestParam(name="size", required=false, defaultValue="10") int size,
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		return 
			this.usersService
			.getUsersUsingPagination(size, page)
			.stream()
			.map(UserBoundary::new)
			.collect(Collectors.toList())
			.toArray(new UserBoundary[0]);
	}
	
	

}
