package com.sangkhim.spring_boot3_pdf.model.entity;

import com.sangkhim.spring_boot3_pdf.model.entity.base.Base;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author extends Base implements Serializable {

  private static final long serialVersionUID = 7156526077883281623L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "author")
  List<Post> postList;
}
