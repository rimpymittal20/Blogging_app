package com.blogapp.demo.user;

import com.blogapp.demo.user.dtos.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //@Autowired
    //private UsersRepository usersRepository;

    private final UsersRepository usersRepository;
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity createUser(CreateUserRequest req)
    {
        var newUser=UserEntity.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .build();
        return usersRepository.save(newUser);
    }
    public UserEntity getUserById(Long authorId)
    {
        return usersRepository.findById(authorId).orElseThrow(()-> new UserNotFoundException(authorId));
    }
    public UserEntity getUserByUsername(String username)
    {
        return usersRepository.findByUsername(username);
    }

    public UserEntity loginUser(String username, String password)
    {
        var newUser = usersRepository.findByUsername(username);

        if(newUser==null)
        {
            throw new UserNotFoundException(username);
        }

        // TODO: match password
        return newUser;
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username)
        {
            super("user "+username+" not found");
        }
        public UserNotFoundException(Long authorId)
        {
            super("Author Id "+authorId+" not found");
        }
    }

 }
