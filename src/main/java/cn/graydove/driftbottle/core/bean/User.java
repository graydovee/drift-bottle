package cn.graydove.driftbottle.core.bean;

/**
 * 
 * @author HUIHUI
 *
 */
public class User {
	private int uid;
	private String uname;
	private String pwd;
	private int sex;
	private java.sql.Date birth;
	private int pickUpTimes;
	private int throwTimes;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public java.sql.Date getBirth() {
		return birth;
	}
	public void setBirth(java.sql.Date birth) {
		this.birth = birth;
	}
	public int getPickUpTimes() {
		return pickUpTimes;
	}
	public void setPickUpTimes(int pickUpTimes) {
		this.pickUpTimes = pickUpTimes;
	}
	public int getThrowTimes() {
		return throwTimes;
	}
	public void setThrowTimes(int throwTimes) {
		this.throwTimes = throwTimes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + pickUpTimes;
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + sex;
		result = prime * result + throwTimes;
		result = prime * result + uid;
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
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
		User other = (User) obj;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
		if (pickUpTimes != other.pickUpTimes)
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (sex != other.sex)
			return false;
		if (throwTimes != other.throwTimes)
			return false;
		if (uid != other.uid)
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd + ", sex=" + sex + ", birth=" + birth
				+ ", pickUpTimes=" + pickUpTimes + ", throwTimes=" + throwTimes + "]";
	}
	
	
	
}
