package codeswarm.io.perun.dto;

import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
@ToString(callSuper = true, exclude = {"roles" , "variants"})
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String token;
    //private SortedSet<Role> roles = new TreeSet<>();
    private Boolean enabled;
    private Boolean confirmed;
    //private Set<Variant> variants = new HashSet<>();

}
