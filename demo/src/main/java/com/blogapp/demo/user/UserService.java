package com.blogapp.demo.user;

import com.blogapp.demo.user.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //@Autowired
    //private UsersRepository usersRepository;

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    public UserService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserEntity> getUsers() {
        return usersRepository.findAll();
    }

    public UserEntity createUser(CreateUserRequest req)
    {
        var newUser=modelMapper.map(req, UserEntity.class);

        //replacing all code with modelMapper(mapping of DTOs into Entity)
//        var newUser=UserEntity.builder()
//                .username(req.getUsername())
//                .email(req.getEmail())
//                .build();
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
