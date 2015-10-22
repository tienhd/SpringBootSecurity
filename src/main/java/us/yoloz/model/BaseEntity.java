package us.yoloz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: tienhd
 * Date: 9/20/15
 * Time: 12:16 PM
 */
@MappedSuperclass
public abstract class BaseEntity
{
    @Id
    @Column(name = "uuid", unique = true, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    @JsonProperty("hiddenUUID")
    private UUID uuid;

    @JsonProperty("uuid")
    @Transient
    private UUID visibleUUID;

    @Version
    @Column(name = "version", nullable = false, columnDefinition = "bigint not null default 0")
    protected Long version;

    @Column (name = "created_date", nullable = false, columnDefinition = "timestamp not null default now()")
    protected Date createdDate;

    @Column (name = "updated_date", nullable = false, columnDefinition = "timestamp not null default now()")
    protected Date updatedDate;

    @Column (name = "deleted", nullable = false, columnDefinition = "boolean not null default false")
    protected boolean deleted = false;

    @PostLoad
    public void onBaseAfterLoad() {
        visibleUUID = uuid;
    }


    @PrePersist
    protected void onBaseCreate() {
        Date now = new Date();
        if (createdDate == null) {
            createdDate = now;
        }

        updatedDate = now;
    }

    @PreUpdate
    protected void onBaseUpdate() {
        updatedDate = new Date();
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public UUID getVisibleUUID()
    {
        return visibleUUID;
    }

    public void setVisibleUUID(UUID visibleUUID)
    {
        this.visibleUUID = visibleUUID;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate()
    {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }
}
