package cz.muni.fi.pa165.entity;

/**
 * Hello world!
 *
 */

@Entity
@Table(name="User")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    @Pattern(regexp=".+@.+\\....?")
    @NotNull
    private String email;


}
