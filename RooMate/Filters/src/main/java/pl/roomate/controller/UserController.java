package pl.roomate.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.roomate.filters.UserRepository;
import pl.roomate.filters.UserSpecificationsBuilder;
import roomate.filters.model.UserProfile;

@Controller
public class UserController {
	private UserRepository  repository;
	
	@RequestMapping(method=RequestMethod.GET,value="/users")
	@ResponseBody
	public List<UserProfile> search(@RequestParam(value="search") String search){
		UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
         
        Specification<UserProfile> spec = builder.build();
        return repository.findAll(spec);
	}
}
