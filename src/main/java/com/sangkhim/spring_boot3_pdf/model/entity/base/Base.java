package com.sangkhim.spring_boot3_pdf.model.entity.base;

import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

public class Base {
  @CreationTimestamp(source = SourceType.DB)
  private Instant createdOn;

  @UpdateTimestamp(source = SourceType.DB)
  private Instant lastUpdatedOn;
}
