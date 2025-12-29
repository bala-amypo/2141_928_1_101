package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
                
                    private String name;
                        
                            @Column(unique = true)
                                private String email;
                                    
                                        private String password;
                                            
                                                @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
                                                    @Enumerated(EnumType.STRING)
                                                        private Set<Role> roles;
                                                            
                                                                private LocalDateTime createdAt;
                                                                }