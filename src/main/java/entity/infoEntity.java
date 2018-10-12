package entity;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info", schema = "public")
public class infoEntity {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "url", nullable = false)
	private String url;
	
	@Column(name = "thread", nullable = false)
	private String thread;
	
	@Column(name = "author", nullable = false)
	private String author;
	
	@Column(name = "views")
	private Short views;
	
	@Column(name = "clips")
	private Short clips;
	
	@Column(name = "comments")
	private Short comments;
	
	@Column(name = "points")
	private Short points;
	
	@Column(name = "receiveDate")
	private Timestamp receiveDate;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id= id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url= url;
	}
	public String getThread() {
		return thread;
	}
	
	public void setThread(String thread) {
		this.thread= thread;
	}
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author= author;
	}
	public Short getViews() {
		return views;
	}
	
	public void setViews(Short views) {
		this.views= views;
	}
	public Short getClips() {
		return clips;
	}
	
	public void setClips(Short clips) {
		this.clips= clips;
	}
	public Short getComments() {
		return comments;
	}
	
	public void setComments(Short comments) {
		this.comments= comments;
	}
	public Short getPoints() {
		return points;
	}
	
	public void setPoints(Short points) {
		this.points= points;
	}
	
	public Timestamp getDate() {
		return receiveDate;
	}
	
	public void setDate(Timestamp receiveDate) {
		this.receiveDate= receiveDate;
	}
	 @Override
	    public int hashCode() {
	        return Objects.hash(url,author,thread);
	    }
}
