package cn.graydove.driftbottle.core.bean;

/**
 * 
 * @author HUIHUI
 *
 */
public class Bottle {
	private int id;
	private String message;
	private int kinds;
	private int state;
	private java.sql.Date createTime;
	private int uid;
	private java.sql.Date updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getKinds() {
		return kinds;
	}
	public void setKinds(int kinds) {
		this.kinds = kinds;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public java.sql.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.sql.Date createTime) {
		this.createTime = createTime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public java.sql.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.sql.Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + id;
		result = prime * result + kinds;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + state;
		result = prime * result + uid;
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bottle other = (Bottle) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (kinds != other.kinds)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (state != other.state)
			return false;
		if (uid != other.uid)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bottle [id=" + id + ", message=" + message + ", kinds=" + kinds + ", state=" + state + ", createTime="
				+ createTime + ", uid=" + uid + ", updateTime=" + updateTime + "]";
	}
	
	
	
}
